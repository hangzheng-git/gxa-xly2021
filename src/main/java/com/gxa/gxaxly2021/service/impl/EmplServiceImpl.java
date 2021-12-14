package com.gxa.gxaxly2021.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gxa.gxaxly2021.dao.EmplRoleDao;
import com.gxa.gxaxly2021.dto.LayDTO;
import com.gxa.gxaxly2021.dto.ResultDTO;
import com.gxa.gxaxly2021.entity.Empl;
import com.gxa.gxaxly2021.dao.EmplDao;
import com.gxa.gxaxly2021.param.EmplPageParam;
import com.gxa.gxaxly2021.param.EmplPwdParam;
import com.gxa.gxaxly2021.entity.EmplRole;
import com.gxa.gxaxly2021.service.EmplService;
import com.gxa.gxaxly2021.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

/**
 * 员工
 * 表(Empl)表服务实现类
 *
 * @author makejava
 * @since 2021-08-15 21:48:35
 */
@Service("emplService")
public class EmplServiceImpl implements EmplService {
    @Autowired
    private EmplDao emplDao;
    @Autowired
    EmplRoleDao emplRoleDao;
    @Autowired
    HttpSession session;

    /**
     * 通过ID查询单条数据
     *
     * @param emplId 主键
     * @return 实例对象
     */
    @Override
    public Empl queryById(Integer emplId) {
        return this.emplDao.queryById(emplId);
    }


    @Override
    public ResultDTO findById(Integer emplId) {
        Empl empl = emplDao.queryById(emplId);
        return new ResultDTO(200,"",empl);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Empl> queryAllByLimit(int offset, int limit) {
        return this.emplDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param empl 实例对象
     * @return 实例对象
     */
    @Override
    public Empl insert(Empl empl) {
        this.emplDao.insert(empl);
        return empl;
    }

    /**
     * 修改数据
     *
     * @param empl 实例对象
     * @return 实例对象
     */
    @Override
    public Empl update(Empl empl) {
        this.emplDao.update(empl);
        return this.queryById(empl.getEmplId());
    }

    @Override
    public ResultDTO deleteByIds(String[] ids) {
        emplDao.deleteAllByIds(ids);
        return new ResultDTO(200,"删除成功");
    }

    /**
     * 通过主键删除数据
     *
     * @param emplId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer emplId) {
        return this.emplDao.deleteById(emplId) > 0;
    }

    @Override
    public LayDTO listData(EmplPageParam param) {
        //加入分页插件
        PageHelper.startPage(param.getPage(),param.getLimit());
        // 先去查询数据库
        List<Empl> empls = this.emplDao.findAll();
        //查询总人数
        PageInfo<Empl> emplPageInfo = new PageInfo<>(empls);
        long num = emplPageInfo.getTotal();
        return new LayDTO(num, empls);
    }
    /**
     * 获取最新的员工编号的方法
     * @return
     */
    private String getNewEmplCode(){
        // 查询最新的员工
        Empl lastEmpl = this.emplDao.findLastEmpl();
        // 获取员工编号
        String oldEmplCode = lastEmpl.getEmplCode();
        // 先截取数字部分  EMPL_1001
        Integer code = Integer.valueOf(oldEmplCode.substring(5));
        // 自增
        code++;
        return "EMPL_"+code;
    }

    /**
     * 新增数据
     *
     * @param empl 实例对象
     * @param roleIds 角色ID数组
     * @return 实例对象
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ResultDTO insert(Empl empl, String[] roleIds) {
        List<Empl> all = emplDao.findAllEmpl();
        for (Empl empl1 : all) {
            if (empl1.getEmplAccount().equals(empl.getEmplAccount())){
                return new ResultDTO(1001,"该账户已存在");
            }
        }
        // 先处理员工自增的编号 =》 先找到最新的一个员工，获取员工编号=》自增
        empl.setEmplCode(getNewEmplCode());
        // 再处理员工的初始密码
        empl.setEmplPwd(MD5Util.MD55("111111"));
        // 再处理员工的添加时间
        empl.setCreateTime(new Timestamp(System.currentTimeMillis()));
        empl.setIsDeleted(0);
        this.emplDao.insert(empl);

        // 添加员工和角色的关系数据
        // 一个数据是员工的ID
        // 一个数据是角色ID
        for (String roleId : roleIds) {
            emplRoleDao.insert(new EmplRole(empl.getEmplId(), Integer.valueOf(roleId)));
        }

        return new ResultDTO(200, "添加成功！");

    }

    @Override
    public ResultDTO edit(Empl empl, String[] roleIds,String oldEmplAccount) {

        //先判断修改的用户名是否已经存在
        //首先判断用户是否修改了账户，没有修改就跳过
        if (!oldEmplAccount.equals(empl.getEmplAccount())){
            List<Empl> empls = emplDao.findAllEmpl();
            for (Empl empl1 : empls) {
                if (empl.getEmplAccount().equals(empl1.getEmplAccount())){
                    return new ResultDTO(1001,"该账户已存在");
                }
            }
        }
        Empl dbEmpl = emplDao.findByAccount(oldEmplAccount);

        empl.setEmplCode(dbEmpl.getEmplCode());
        empl.setLastLoginTime(dbEmpl.getLastLoginTime());
        empl.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        empl.setCreateTime(dbEmpl.getCreateTime());
        empl.setEmplPwd(MD5Util.MD55(empl.getEmplPwd()));
        empl.setEmplId(dbEmpl.getEmplId());
        empl.setOperatorId((Integer) session.getAttribute("emplId"));
        empl.setIsDeleted(0);

        //更新前先删除数据
        emplRoleDao.deleteByEmpleId(empl.getEmplId());
        //更新关系表
        for (String roleId : roleIds) {
            emplRoleDao.insert(new EmplRole(empl.getEmplId(), Integer.valueOf(roleId)));
        }
        emplDao.update(empl);
        return new ResultDTO(200, "修改成功");
    }

    /**
     * 通过session来找到用户
     * @return
     */
    @Override
    public ResultDTO findBySession() {
        Integer emplId = (Integer) session.getAttribute("emplId");
        Empl empl = emplDao.queryById(emplId);
        return new ResultDTO(200,"",empl);
    }
    /**
     * 用户自己修改信息
     * 非管理员修改用户信息
     */
    @Override
    public ResultDTO personEdit(Empl empl) {
        Empl dbEmpl = emplDao.findByAccount(empl.getEmplAccount());
        System.out.println(empl);
        dbEmpl.setEmplPwd(MD5Util.MD55(empl.getEmplPwd()));
        dbEmpl.setEmplName(empl.getEmplName());
        dbEmpl.setEmplPhone(empl.getEmplPhone());
        dbEmpl.setEmplAccount(empl.getEmplAccount());
        dbEmpl.setEmplGender(empl.getEmplGender());
        dbEmpl.setEmplRemark(empl.getEmplRemark());
        emplDao.update(dbEmpl);
        return new ResultDTO(200,"修改成功");
    }

    @Override
    public ResultDTO updatePwd(EmplPwdParam pwdParam) {

        Empl dbEmpl = emplDao.findByAccount(pwdParam.getEmplAccount());
        if (dbEmpl == null) {
            return new ResultDTO(1001, "该用户不存在！");
        }
        if (!pwdParam.getNewPwd().equals(pwdParam.getRePwd())){
            return new ResultDTO(1005,"两次密码不一致");
        }
        if (!dbEmpl.getEmplPwd().equals(MD5Util.MD55(pwdParam.getOldPwd()))){
            return new ResultDTO(1002,"密码错误");
        }
        //将新密码付给员工对象
        dbEmpl.setEmplPwd(MD5Util.MD55(pwdParam.getNewPwd()));
        emplDao.update(dbEmpl);
        return new ResultDTO(200,"修改成功");
    }

    @Override
    public ResultDTO updateStatus(Empl empl, HttpSession session) {
        //设置修改时间
        empl.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        //设置操作人id
        empl.setOperatorId((Integer) session.getAttribute("emplId"));
        emplDao.update(empl);
        return new ResultDTO(200,"");
    }
}
