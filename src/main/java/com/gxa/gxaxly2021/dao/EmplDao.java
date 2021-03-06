package com.gxa.gxaxly2021.dao;

import com.gxa.gxaxly2021.entity.Empl;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 员工
 * 表(Empl)表数据库访问层
 *
 * @author makejava
 * @since 2021-08-15 21:48:33
 */
public interface EmplDao {
    Empl findByAccount( @Param("emplAccount") String emplAccount);

    /**
     * 通过ID查询单条数据
     *
     * @param emplId 主键
     * @return 实例对象
     */
    Empl queryById(Integer emplId);
    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Empl> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param empl 实例对象
     * @return 对象列表
     */
    List<Empl> queryAll(Empl empl);

    /**
     * 新增数据
     *
     * @param empl 实例对象
     * @return 影响行数
     */
    int insert(Empl empl);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Empl> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Empl> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Empl> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Empl> entities);

    /**
     * 修改数据
     *
     * @param empl 实例对象
     * @return 影响行数
     */
    int update(Empl empl);

    /**
     * 通过主键删除数据
     *
     * @param emplId 主键
     * @return 影响行数
     */
    int deleteById(Integer emplId);
    /**
     * 批量删除-假删除
     * @param ids
     */
    void deleteAllByIds(@Param("ids") String[] ids);
    /**
     * 查询所有的未删除员工数据
     * @return
     */
    List<Empl> findAll();
    /**
     * 出现所有员（包括被标记为删除）
     *
     */
    List<Empl> findAllEmpl();
    /**
     * 查询最新的员工
     * @return
     */
    Empl findLastEmpl();

}

