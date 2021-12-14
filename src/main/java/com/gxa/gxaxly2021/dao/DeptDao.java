package com.gxa.gxaxly2021.dao;

import com.gxa.gxaxly2021.entity.Dept;
import com.gxa.gxaxly2021.entity.Empl;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 组织
 * 结构表(部门表)(Dept)表数据库访问层
 *
 * @author makejava
 * @since 2021-08-14 17:01:43
 */
public interface DeptDao {
    /**
     * 自定义查询
     * @return
     */
    List<Dept> findAll();


    /**
     * 通过ID查询单条数据
     *
     * @param deptId 主键
     * @return 实例对象
     */
    Dept queryById(Integer deptId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Dept> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param dept 实例对象
     * @return 对象列表
     */
    List<Dept> queryAll(Dept dept);

    /**
     * 新增数据
     *
     * @param dept 实例对象
     * @return 影响行数
     */
    int insert(Dept dept);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Dept> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Dept> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Dept> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Dept> entities);

    /**
     * 修改数据
     *
     * @param dept 实例对象
     * @return 影响行数
     */
    int update(Dept dept);

    /**
     * 通过主键删除数据
     *
     * @param deptId 主键
     * @return 影响行数
     */
    int deleteById(Integer deptId);


    Dept findLastDept();
}

