package com.example.demo.dao;

import com.example.demo.entity.PmsBaseAttrValue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * å±žæ€§å€¼è¡¨(PmsBaseAttrValue)表数据库访问层
 *
 * @author makejava
 * @since 2020-02-18 16:46:44
 */
public interface PmsBaseAttrValueDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PmsBaseAttrValue queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<PmsBaseAttrValue> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param pmsBaseAttrValue 实例对象
     * @return 对象列表
     */
    List<PmsBaseAttrValue> queryAll(PmsBaseAttrValue pmsBaseAttrValue);

    /**
     * 新增数据
     *
     * @param pmsBaseAttrValue 实例对象
     * @return 影响行数
     */
    int insert(PmsBaseAttrValue pmsBaseAttrValue);

    /**
     * 修改数据
     *
     * @param pmsBaseAttrValue 实例对象
     * @return 影响行数
     */
    int update(PmsBaseAttrValue pmsBaseAttrValue);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    int delete(PmsBaseAttrValue deleteValues);
}