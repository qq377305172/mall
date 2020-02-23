package com.example.demo.dao;

import com.example.demo.entity.PmsBaseCatalog3;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (PmsBaseCatalog3)表数据库访问层
 *
 * @author makejava
 * @since 2020-02-18 16:46:44
 */
public interface PmsBaseCatalog3Dao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PmsBaseCatalog3 queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<PmsBaseCatalog3> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param pmsBaseCatalog3 实例对象
     * @return 对象列表
     */
    List<PmsBaseCatalog3> queryAll(PmsBaseCatalog3 pmsBaseCatalog3);

    /**
     * 新增数据
     *
     * @param pmsBaseCatalog3 实例对象
     * @return 影响行数
     */
    int insert(PmsBaseCatalog3 pmsBaseCatalog3);

    /**
     * 修改数据
     *
     * @param pmsBaseCatalog3 实例对象
     * @return 影响行数
     */
    int update(PmsBaseCatalog3 pmsBaseCatalog3);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    List<PmsBaseCatalog3> queryByParent(Integer id);
}