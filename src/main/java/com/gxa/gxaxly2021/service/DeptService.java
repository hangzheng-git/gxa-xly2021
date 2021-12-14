package com.gxa.gxaxly2021.service;

import com.gxa.gxaxly2021.dto.LayDTO;
import com.gxa.gxaxly2021.dto.ResultDTO;
import com.gxa.gxaxly2021.entity.Dept;

import java.util.List;

/**
 * 组织
 * 结构表(部门表)(Dept)表服务接口
 *
 * @author makejava
 * @since 2021-08-14 17:01:44
 */
public interface DeptService {

    /**
     * 通过ID查询单条数据
     *
     * @param deptId 主键
     * @return 实例对象
     */
    Dept queryById(Integer deptId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Dept> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param dept 实例对象
     * @return 实例对象
     */
    ResultDTO insert(Dept dept);

    /**
     * 修改数据
     * @param dept
     * @return
     */
    ResultDTO update(Dept dept);

    /**
     * 通过主键删除数据
     *
     * @param deptId 主键
     * @return 是否成功
     */
    ResultDTO deleteById(Integer deptId);

    ResultDTO getAllDept();

    LayDTO listDate();

}
