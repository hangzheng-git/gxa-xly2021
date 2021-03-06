package com.gxa.gxaxly2021.service.impl;

import com.gxa.gxaxly2021.entity.Permission;
import com.gxa.gxaxly2021.dao.PermissionDao;
import com.gxa.gxaxly2021.service.PermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 权限
 * 表(Permission)表服务实现类
 *
 * @author makejava
 * @since 2021-08-16 21:38:43
 */
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {
    @Resource
    private PermissionDao permissionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param permissionId 主键
     * @return 实例对象
     */
    @Override
    public Permission queryById(Integer permissionId) {
        return this.permissionDao.queryById(permissionId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Permission> queryAllByLimit(int offset, int limit) {
        return this.permissionDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param permission 实例对象
     * @return 实例对象
     */
    @Override
    public Permission insert(Permission permission) {
        this.permissionDao.insert(permission);
        return permission;
    }

    /**
     * 修改数据
     *
     * @param permission 实例对象
     * @return 实例对象
     */
    @Override
    public Permission update(Permission permission) {
        this.permissionDao.update(permission);
        return this.queryById(permission.getPermissionId());
    }

    /**
     * 通过主键删除数据
     *
     * @param permissionId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer permissionId) {
        return this.permissionDao.deleteById(permissionId) > 0;
    }
}
