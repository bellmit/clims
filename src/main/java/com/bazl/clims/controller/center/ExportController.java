package com.bazl.clims.controller.center;

import com.bazl.clims.common.Constants;
import com.bazl.clims.controller.BaseController;
import com.bazl.clims.model.DictItem;
import com.bazl.clims.model.LoaUserInfo;
import com.bazl.clims.model.vo.AmPersonalInfoVo;
import com.bazl.clims.model.vo.LimsCaseInfoVo;
import com.bazl.clims.service.AmPersonalInfoService;
import com.bazl.clims.service.LimsCaseInfoService;
import com.bazl.clims.service.OrgInfoService;
import com.bazl.clims.utils.DictUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.el.parser.ParseException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by zhangbo on 2020/3/10.
 */
@Controller
@RequestMapping("/center")
public class ExportController extends BaseController {

    @Autowired
    LimsCaseInfoService limsCaseInfoService;
    @Autowired
    OrgInfoService orgInfoService;
    @Autowired
    AmPersonalInfoService amPersonalInfoService;

    /**
     * 案件信息导出
     * @param request
     * @param query
     * @return
     */
    @RequestMapping({"/exportCase"})
    public void exportCase(HttpServletRequest request, HttpServletResponse response, LimsCaseInfoVo query) {
        List<LimsCaseInfoVo> caseInfoList = new ArrayList<>();
        try {
            query = resetCaseInfoQuery(query);

            Subject subject = SecurityUtils.getSubject();
            LoaUserInfo loaUserInfo = (LoaUserInfo) subject.getPrincipal();

            String userOrgId = loaUserInfo.getOrgId();
            if ((StringUtils.isNotBlank(userOrgId)) && (userOrgId.contains("110023"))) {
                userOrgId = "110230000000";
            }
            query.setUserOrdId(userOrgId);
            query.setAcceptOrgId(userOrgId);


            List<AmPersonalInfoVo> amPersonalInfoVoList = this.amPersonalInfoService.queryAmPersonalInfoVoList(loaUserInfo.getOrgId());

            //判断受理人id是否为空
            if (StringUtils.isNotBlank(query.getAcceptorId())) {
                //查询全部
                if(Constants.SELECT_ACCEPTOR_ID.equals(query.getAcceptorId())){
                    query.setAcceptorId(null);
                }else {
                    query.setAcceptorId(query.getAcceptorId());
                }

            }else {

                if (!CollectionUtils.isEmpty(amPersonalInfoVoList)) {
                    for (AmPersonalInfoVo amPersonalInfoVo : amPersonalInfoVoList) {
                        if (amPersonalInfoVo.getEntity().getPersonalId().equals(loaUserInfo.getPersonalId())) {
                            query.setAcceptorId(loaUserInfo.getPersonalId());
                            break;
                        }
                    }
                }
            }

            query.setOrderByClause("lc.case_no desc nulls last, lci.ACCEPT_DATETIME asc");

            caseInfoList = this.limsCaseInfoService.selectVOPaginationExportList(query);

            Iterator localIterator1;
            LimsCaseInfoVo limsCaseInfoVo;
            if ((null != caseInfoList) && (caseInfoList.size() > 0)) {
                for (localIterator1 = caseInfoList.iterator(); localIterator1.hasNext(); ) {
                    limsCaseInfoVo = (LimsCaseInfoVo) localIterator1.next();
                    if (!CollectionUtils.isEmpty(amPersonalInfoVoList)) {
                        for (AmPersonalInfoVo amPersonalInfoVo : amPersonalInfoVoList) {
                            if (amPersonalInfoVo.getEntity().getPersonalId().equals(limsCaseInfoVo.getAcceptorId())) {
                                limsCaseInfoVo.setAcceptorName(amPersonalInfoVo.getEntity().getFullName());
                                break;
                            }
                        }
                    }
                }
            }

            DictItem dictItem = new DictItem();
            //案件性质
            dictItem.setDictTypeCode(Constants.CASE_PROPERTY);
            List<DictItem> casePropertyList = DictUtil.getDictList(dictItem);
            for(LimsCaseInfoVo caseInfo:caseInfoList){
                for(DictItem caseProperty:casePropertyList){
                    if (caseInfo.getEntity().getCaseProperty().equals(caseProperty.getDictCode())){
                        caseInfo.getEntity().setCaseProperty(caseProperty.getDictName());
                    }
                }
            }


        } catch (Exception ex) {
            this.logger.info("导出查询案件信息失败:" + ex);
        }

        String templateFilePath = request.getServletContext().getRealPath("templates/exportCaseExcel.xls");
        HSSFWorkbook workbook = null;
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        try {
            FileInputStream fis = new FileInputStream(templateFilePath);
            workbook = new HSSFWorkbook(new POIFSFileSystem(fis));
            HSSFSheet sheet = workbook.getSheetAt(0);
            HSSFRow row = null;
            HSSFCell cell = null;

            int startRow = 2;
            int idx = 0;
            for (LimsCaseInfoVo caseInfo:caseInfoList) {
                row = sheet.getRow(startRow + idx);
                if (row == null) {
                    row = sheet.createRow(startRow + idx);
                }
                //序号
                cell = row.getCell(0);
                if (cell == null) {
                    cell = row.createCell(0);
                }
                cell.setCellValue(idx+1);

                //现勘勘验号（K号）
                cell = row.getCell(1);
                if (cell == null) {
                    cell = row.createCell(1);
                }
                if(StringUtils.isNotBlank(caseInfo.getEntity().getCaseXkNo())){
                    cell.setCellValue(caseInfo.getEntity().getCaseXkNo());
                }else{
                    cell.setCellValue("无");
                }

                //案件A号
                cell = row.getCell(2);
                if (cell == null) {
                    cell = row.createCell(2);
                }
                cell.setCellValue(caseInfo.getEntity().getXkANo());

                //案件受理号
                cell = row.getCell(3);
                if (cell == null) {
                    cell = row.createCell(3);
                }
                cell.setCellValue(caseInfo.getEntity().getCaseNo());

                //案件名称
                cell = row.getCell(4);
                if (cell == null) {
                    cell = row.createCell(4);
                }
                cell.setCellValue(caseInfo.getEntity().getCaseName());

                //案件详情
                cell = row.getCell(5);
                if (cell == null) {
                    cell = row.createCell(5);
                }
                cell.setCellValue(caseInfo.getEntity().getCaseBrief());

                //案件性质--------------
                cell = row.getCell(6);
                if (cell == null) {
                    cell = row.createCell(6);
                }
                cell.setCellValue(caseInfo.getEntity().getCaseProperty());

                //委托单位
                cell = row.getCell(7);
                if (cell == null) {
                    cell = row.createCell(7);
                }
                cell.setCellValue(caseInfo.getDelegateOrgName());

                //委托时间
                cell = row.getCell(8);
                if (cell == null) {
                    cell = row.createCell(8);
                }
                if (null != caseInfo.getDelegateDatetime()){
                    cell.setCellValue(sd.format(caseInfo.getDelegateDatetime()));
                }else{
                    cell.setCellValue("");
                }

                //受理时间
                cell = row.getCell(9);
                if (cell == null) {
                    cell = row.createCell(9);
                }
                if (null != caseInfo.getAcceptDatetime()){
                    cell.setCellValue(sd.format(caseInfo.getAcceptDatetime()));
                }else{
                    cell.setCellValue("");
                }

                //受理人
                cell = row.getCell(10);
                if (cell == null) {
                    cell = row.createCell(10);
                }
                cell.setCellValue(caseInfo.getAcceptorName());

                idx++;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=" + sdf.format(new Date()) + URLEncoder.encode("_案件信息列表.xls", "utf-8"));
            workbook.write(response.getOutputStream());
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("导出Excel错误！", ex);
        }

    }


    private LimsCaseInfoVo resetCaseInfoQuery(LimsCaseInfoVo query) throws ParseException {

        if (StringUtils.isBlank(query.getEntity().getCaseStatus())) {
             query.getEntity().setCaseStatus(null);
        } else {
            query.getEntity().setCaseStatus(query.getEntity().getCaseStatus());
        }

        if (StringUtils.isBlank(query.getEntity().getCaseName())) {
            query.getEntity().setCaseName(null);
        } else {
            query.getEntity().setCaseName((query.getEntity()).getCaseName().trim());
        }
        if (StringUtils.isBlank(query.getDelegateOrgCode())) {
            query.setDelegateOrgCode(null);
        } else {
            query.setDelegateOrgCode(query.getDelegateOrgCode().trim());
        }
        if (StringUtils.isBlank(query.getEntity().getCaseNo())) {
            query.getEntity().setCaseNo(null);
        } else {
            query.getEntity().setCaseNo(query.getEntity().getCaseNo().trim());
        }
        if (StringUtils.isBlank(query.getEntity().getCaseXkNo())) {
            query.getEntity().setCaseXkNo(null);
        } else {
            query.getEntity().setCaseXkNo( query.getEntity().getCaseXkNo().trim());
        }
        if (StringUtils.isBlank(query.getEntity().getCaseProperty())) {
           query.getEntity().setCaseProperty(null);
        } else {
           query.getEntity().setCaseProperty(query.getEntity().getCaseProperty().trim());
        }
        if (StringUtils.isBlank(query.getEntity().getCaseType())) {
            query.getEntity().setCaseType(null);
        } else {
            query.getEntity().setCaseType(query.getEntity().getCaseType().trim());
        }
        if (StringUtils.isBlank(query.getDelegator1Name())) {
            query.setDelegator1Name(null);
        } else {
            query.setDelegator1Name(query.getDelegator1Name().trim());
        }
        if (null == query.getDelegateStartDatetime()) {
            query.setDelegateStartDatetime(null);
        } else {
            query.setDelegateStartDatetime(query.getDelegateStartDatetime());
        }
        if (null == query.getDelegateEndDatetime()) {
            query.setDelegateEndDatetime(null);
        } else {
            query.setDelegateEndDatetime(query.getDelegateEndDatetime());
        }
        if (query.getStatus() == null) {
            query.setStatus("02");
        } else if (query.getStatus() == "") {
            query.setStatus(null);
        } else {
            query.setStatus(query.getStatus());
        }
        if (StringUtils.isBlank(query.getPersonName())) {
            query.setPersonName(null);
        } else {
            query.setPersonName(query.getPersonName().trim());
        }
        if (StringUtils.isBlank(query.getPersonIdCard())) {
            query.setPersonIdCard(null);
        } else {
            query.setPersonIdCard(query.getPersonIdCard().trim());
        }
        if (StringUtils.isBlank(query.getAcceptorId())) {
            query.setAcceptorId(null);
        } else {
            query.setAcceptorId(query.getAcceptorId().trim());
        }

        /*if (StringUtils.isBlank(query.getSampleNo())) {
            query.setSampleNo(null);
        } else {
            query.setSampleNo(query.getSampleNo());
        }*/

        return query;
    }

}
