package com.gxa.gxaxly2021.service;

import com.gxa.gxaxly2021.dto.LayDTO;
import com.gxa.gxaxly2021.dto.ResultDTO;
import com.gxa.gxaxly2021.entity.Empl;
import com.gxa.gxaxly2021.param.EmplPageParam;
import com.gxa.gxaxly2021.param.EmplPwdParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 员工
 * 表(Empl)表服务接口
 *
 * @author makejava
 * @since 2021-08-15 21:48:34
 */
public interface EmplService {

    /**
     * 通过ID查询单条数据
     *
     * @param emplId 主键
     * @return 实例对象
     */
    Empl queryById(Integer emplId);

    /**
     * 通过ID查询单条数据
     * @param emplId
     * @return
     */
    ResultDTO findById(Integer emplId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Empl> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param empl 实例对象
     * @return 实例对象
     */
    Empl insert(Empl empl);

    /**
     * 修改数据
     *
     * @param empl 实例对象
     * @return 实例对象
     */
    Empl update(Empl empl);

    ResultDTO deleteByIds(String[] ids);

    /**
     * 通过主键删除数据
     *
     * @param emplId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer emplId);
    /**
     * 员工列表数据
     * @return
     */
    LayDTO listData(EmplPageParam param);
    /**
     * 新增数据
     *
     * @param empl 实例对象
     * @param roleIds 角色ID数组
     * @return 实例对象
     */
    ResultDTO insert(Empl empl, String[] roleIds);

    ResultDTO edit(Empl empl,String[] roleIds,String oldEmplAccount);

    /**
     * 通过session来找到用户
     * @return
     */
    ResultDTO findBySession();
    /**
     * 用户自己修改信息
     * 非管理员修改用户信息
     */
    ResultDTO personEdit(Empl empl);


    /**
     * 修改密码
     * @param pwdParam
     * @return
     */
    ResultDTO updatePwd(EmplPwdParam pwdParam);

    ResultDTO updateStatus(Empl empl, HttpSession session);

}
