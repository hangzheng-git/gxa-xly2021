package com.gxa.gxaxly2021.service.impl;

import com.github.pagehelper.PageInfo;
import com.gxa.gxaxly2021.dto.LayDTO;
import com.gxa.gxaxly2021.dto.ResultDTO;
import com.gxa.gxaxly2021.entity.Dept;
import com.gxa.gxaxly2021.dao.DeptDao;
import com.gxa.gxaxly2021.entity.Empl;
import com.gxa.gxaxly2021.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

/**
 * 组织
 * 结构表(部门表)(Dept)表服务实现类
 *
 * @author makejava
 * @since 2021-08-14 17:01:45
 */
@Service("deptService")
public class DeptServiceImpl implements DeptService {
    @Resource
    private DeptDao deptDao;
    @Autowired
    HttpSession session;

    /**
     * 通过ID查询单条数据
     *
     * @param deptId 主键
     * @return 实例对象
     */
    @Override
    public Dept queryById(Integer deptId) {
        return this.deptDao.queryById(deptId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Dept> queryAllByLimit(int offset, int limit) {
        return this.deptDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param dept 实例对象
     * @return 实例对象
     */
    @Override
    public ResultDTO insert(Dept dept) {
        List<Dept> deptList = deptDao.findAll();
        for (Dept dept1 : deptList) {
            if(dept1.getDeptSimpleName().equals(dept.getDeptSimpleName())){
                return new ResultDTO(1001,"部门已经存在了");
            }
        }
        //添加时间
        dept.setCreateTime(new Timestamp(System.currentTimeMillis()));
        //部门编号
        dept.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        dept.setOperatorId((Integer) session.getAttribute("emplId"));
        dept.setDeptCode(getNewDeptCode());
        dept.setIsDeleted(0);
        this.deptDao.insert(dept);
        return new ResultDTO(200,"添加成功");
    }
    /**
     * 获取最新的员工编号的方法
     * @return
     */
    private String getNewDeptCode(){
        // 查询最新的员工
        Dept lastDept = this.deptDao.findLastDept();
        // 获取员工编号
        String oldDeptCode = lastDept.getDeptCode();
        // 先截取数字部分  DEPT_1001
        Integer code = Integer.valueOf(oldDeptCode.substring(5));
        // 自增
        code++;
        return "DEPT_"+code;
    }

    /**
     * 修改数据
     *
     * @param dept 实例对象
     * @return 实例对象
     */
    @Override
    public ResultDTO update(Dept dept) {
        Dept oldDept = deptDao.queryById(dept.getDeptId());
        oldDept.setDeptSimpleName(dept.getDeptSimpleName());
        oldDept.setDeptDesc(dept.getDeptDesc());
        oldDept.setDeptCode(dept.getDeptCode());
        oldDept.setDeptFullName(dept.getDeptFullName());
        oldDept.setParentId(dept.getParentId());
        //
        oldDept.setOperatorId((Integer) session.getAttribute("emplId"));
        //修改更新时间
        oldDept.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        this.deptDao.update(oldDept);
        return new ResultDTO(200,"修改成功");
    }

    /**
     * 通过主键删除数据
     *
     * @param deptId 主键
     * @return 是否成功
     */
    @Override
    public ResultDTO deleteById(Integer deptId) {
        if (deptDao.deleteById(deptId)==1){
            return new ResultDTO(200,"删除成功");
        }
        return new ResultDTO(1001,"删除失败");
    }

    @Override
    public ResultDTO getAllDept() {
        List<Dept> deptList =  deptDao.findAll();
        return new ResultDTO(200,"success",deptList);
    }

    @Override
    public LayDTO listDate() {
        List<Dept> deptList = deptDao.findAll();
        PageInfo<Dept> deptPageInfo = new PageInfo<>(deptList);
        long num = deptPageInfo.getTotal();
        return new LayDTO(num, deptList);
    }
}
