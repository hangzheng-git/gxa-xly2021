package com.gxa.gxaxly2021.service.impl;

import com.github.pagehelper.PageInfo;
import com.gxa.gxaxly2021.dto.LayDTO;
import com.gxa.gxaxly2021.dto.ResultDTO;
import com.gxa.gxaxly2021.entity.Empl;
import com.gxa.gxaxly2021.entity.Role;
import com.gxa.gxaxly2021.dao.RoleDao;
import com.gxa.gxaxly2021.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色
 * 表(Role)表服务实现类
 *
 * @author makejava
 * @since 2021-08-16 15:17:10
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleDao roleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    @Override
    public Role queryById(Integer roleId) {
        return this.roleDao.queryById(roleId);
    }


    /**
     * 查询所有的角色
     *
     * @return
     */
    @Override
    public ResultDTO getAllRoles() {
        List<Role> roles = this.roleDao.findAll();
        return new ResultDTO(200, "success", roles);
    }

    /**
     * 显示所有角色
     * @return
     */
    @Override
    public LayDTO listAllRoles() {
        List<Role> roles = this.roleDao.listAllRole();
        PageInfo<Role> rolePageInfo = new PageInfo<>(roles);
        long num = rolePageInfo.getTotal();
        return new LayDTO(num,roles);
    }



    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Role> queryAllByLimit(int offset, int limit) {
        return this.roleDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    @Override
    public Role insert(Role role) {
        this.roleDao.insert(role);
        return role;
    }

    /**
     * 修改数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    @Override
    public Role update(Role role) {
        this.roleDao.update(role);
        return this.queryById(role.getRoleId());
    }

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer roleId) {
        return this.roleDao.deleteById(roleId) > 0;
    }
}
