package com.example.demo.dao;

import com.example.demo.entity.OmsOrder;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * è®¢å•è¡¨(OmsOrder)表数据库访问层
 *
 * @author makejava
 * @since 2020-02-18 16:46:44
 */
public interface OmsOrderDao extends Mapper<OmsOrder> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    OmsOrder queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<OmsOrder> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param omsOrder 实例对象
     * @return 对象列表
     */
    List<OmsOrder> queryAll(OmsOrder omsOrder);

    /**
     * 新增数据
     *
     * @param omsOrder 实例对象
     * @return 影响行数
     */
    int insert(OmsOrder omsOrder);

    /**
     * 修改数据
     *
     * @param omsOrder 实例对象
     * @return 影响行数
     */
    int update(OmsOrder omsOrder);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}