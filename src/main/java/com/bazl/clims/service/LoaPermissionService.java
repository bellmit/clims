    package com.bazl.clims.service;

    import com.bazl.clims.model.LoaPermission;
    import com.bazl.clims.model.TreeData;
    import org.springframework.stereotype.Repository;

    import java.util.List;

    @Repository
    public interface LoaPermissionService {
        int deleteByPrimaryKey(String permissionId);

        int insert(LoaPermission record);

        LoaPermission selectByPrimaryKey(String permissionId);

        List<LoaPermission> selectAll();

        int updateByPrimaryKey(LoaPermission record);

        /**
         *根据角色id获取权限
         * @param roleId
         * @return
         */
        public List<LoaPermission> listByRoleId(String roleId);


        List<LoaPermission> selectBy(String roleId);

        List<TreeData> selectPermissionList();

        int saveMenu(LoaPermission permission);

        int delPermission(LoaPermission permission);
    }