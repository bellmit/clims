package com.bazl.clims.controller.center;

import com.bazl.clims.controller.BaseController;
import com.bazl.clims.model.LoaRole;
import com.bazl.clims.service.LoaRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequestMapping("manage")
public class LoaRoleController extends BaseController {

    @Autowired
    private LoaRoleService loaRoleService;

    //这个方法是点击权限管理出来的页面的数据 还有点击添加会出来的菜单的数据 没选择的菜单数据
    @RequestMapping("/loaRoleManage")
    public String loaRoleManage(LoaRole amRole, Model model){
        List<LoaRole> loaRoles = loaRoleService.selectAll();
        model.addAttribute("loaRoles",loaRoles);
        return "manage/loaRoleManage";
    }

    //这个是点击权限管理里面 的编辑的的时候 回显的指定id的权限和对应的菜单
    @RequestMapping("/selectRoleInfoById")
    @ResponseBody
    public Object selectRoleInfoById(LoaRole loaRole){
        ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
        try {
            map.put("loaRole",loaRoleService.selectByPrimaryKey(loaRole.getRoleId()));
            map.put("menuList",loaRoleService.selectMenuInfo(loaRole));
            map.put(STATUS,true);
        }catch (Exception e){
            e.printStackTrace();
            map.clear();
            map.put(STATUS,false);
        }
        return map;
    }

    //保存的方法的controller层
    @RequestMapping("/saveRoleInfo")
    @ResponseBody
    public Object saveRoleInfo(
            @RequestParam(value = "menus[]",defaultValue = "") List<String> menus,LoaRole loaRole){
        ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
        try{
            loaRoleService.insertRoleInfo(loaRole,menus);
            map.put(STATUS,true);
        }catch (Exception e){
            e.printStackTrace();
            map.put(STATUS,false);
        }
        return map;
    }

    //修改方法的controller层
    @RequestMapping("/updateRoleInfo")
    @ResponseBody
    public Object updateRoleInfo(
            @RequestParam(value = "menus[]",defaultValue = "") List<String> menus,LoaRole loaRole){
        ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
        try {
            loaRoleService.updateRoleInfo(loaRole,menus);
            map.put(STATUS,true);
        }catch (Exception e){
            e.printStackTrace();
            map.put(STATUS,false);
        }
        return map;
    }

    //删除的controller层
    @RequestMapping("/delRoleInfo")
    @ResponseBody
    public Object delRoleInfo(String roleId){
        ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
        try{
            loaRoleService.delRoleInfo(roleId);
            map.put(STATUS,true);
        }catch (Exception e){
            e.printStackTrace();
            map.put(STATUS,false);
        }
        return map;
    }
}
