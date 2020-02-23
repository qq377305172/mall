package com.example.demo.dao;

import com.example.demo.entity.PmsBrand;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * å“ç‰Œè¡¨(PmsBrand)表数据库访问层
 *
 * @author makejava
 * @since 2020-02-18 16:46:44
 */
public interface PmsBrandDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PmsBrand queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<PmsBrand> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param pmsBrand 实例对象
     * @return 对象列表
     */
    List<PmsBrand> queryAll(PmsBrand pmsBrand);

    /**
     * 新增数据
     *
     * @param pmsBrand 实例对象
     * @return 影响行数
     */
    int insert(PmsBrand pmsBrand);

    /**
     * 修改数据
     *
     * @param pmsBrand 实例对象
     * @return 影响行数
     */
    int update(PmsBrand pmsBrand);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}