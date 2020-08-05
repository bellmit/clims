package com.bazl.clims.utils;


import com.bazl.clims.model.DictItem;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 字典项公共类
 * Created by hj on 2018/12/24.
 */
public class DictUtil {

    //根据code获取相应的字典数据
    public static List<DictItem> getDictList(DictItem dictItem){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        List<DictItem> list =  new ArrayList<>();

        HttpSession session = request.getSession();//获取session
        List<DictItem> listItem = (List<DictItem>)session.getAttribute("listDictItem");

        if(listItem != null){
            if(listItem.size() > 0){
                for(int i= 0;i < listItem.size();i++ ){
                    DictItem items = new DictItem();
                    if(dictItem.getDictTypeCode() != null){
                        if(dictItem.getDictTypeCode().equals(listItem.get(i).getDictTypeCode())){
                            items = listItem.get(i);
                            list.add(items);
                        }
                    }
                }
            }
        }
        return list;

    }

}
