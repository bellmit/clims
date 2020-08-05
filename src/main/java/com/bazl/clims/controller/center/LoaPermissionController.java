package com.bazl.clims.controller.center;

import com.bazl.clims.controller.BaseController;
import com.bazl.clims.model.LoaPermission;
import com.bazl.clims.model.TreeData;
import com.bazl.clims.service.LoaPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequestMapping("/manage")
public class LoaPermissionController extends BaseController {

    @Autowired
    private LoaPermissionService loaPermissionService;

    @RequestMapping("/selectPermissionList")
    @ResponseBody
    public List<TreeData> selectPermissionList() {
        return loaPermissionService.selectPermissionList();
    }
    @RequestMapping("/toPermissionManagementPage")
    public String toPermissionManagementPage() {
        return "manage/permissionManagement";
    }

    @RequestMapping("/savePermission")
    @ResponseBody
    public Object addMenu(LoaPermission permission) {
        int i = loaPermissionService.saveMenu(permission);
        TreeData data = new TreeData();
        data.setId(permission.getPermissionId());
        data.setpId(permission.getParentId());
        data.setTree_url(permission.getPermissionLink());
        data.setName(permission.getPermissionName());
        data.setActiveflag(permission.getActiveFlag());
        data.setRootflag(permission.getRootFlag());
        data.setDeleteflag(permission.getDeleteflag());
        data.setOrderNo(permission.getOrderNo());
        data.setSubsystemFlag(permission.getSubsystemFlag());
        return data;
    }

    @RequestMapping("/delPermission")
    @ResponseBody
    public Object delMenu(LoaPermission permission) {
        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
        try {
            int i = loaPermissionService.delPermission(permission);
            map.put(STATUS, true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put(STATUS, false);
        }
        return map;
    }

}
