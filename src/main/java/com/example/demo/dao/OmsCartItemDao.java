package com.example.demo.dao;

import com.example.demo.entity.OmsCartItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * è´­ç‰©è½¦è¡¨(OmsCartItem)表数据库访问层
 *
 * @author makejava
 * @since 2020-02-18 16:46:42
 */
public interface OmsCartItemDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    OmsCartItem queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<OmsCartItem> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param omsCartItem 实例对象
     * @return 对象列表
     */
    List<OmsCartItem> queryAll(OmsCartItem omsCartItem);

    /**
     * 新增数据
     *
     * @param omsCartItem 实例对象
     * @return 影响行数
     */
    int insert(OmsCartItem omsCartItem);

    /**
     * 修改数据
     *
     * @param omsCartItem 实例对象
     * @return 影响行数
     */
    int update(OmsCartItem omsCartItem);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    OmsCartItem queryOne(OmsCartItem omsCartItem);

    int updateCheckedStatus(@Param("omsCartItem") OmsCartItem omsCartItem);
}