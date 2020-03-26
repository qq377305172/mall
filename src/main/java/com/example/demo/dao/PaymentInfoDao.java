package com.example.demo.dao;

import com.example.demo.entity.PaymentInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * æ”¯ä»˜ä¿¡æ¯è¡¨(PaymentInfo)表数据库访问层
 *
 * @author makejava
 * @since 2020-02-18 16:46:44
 */
public interface PaymentInfoDao extends Mapper<PaymentInfo> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PaymentInfo queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<PaymentInfo> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param paymentInfo 实例对象
     * @return 对象列表
     */
    List<PaymentInfo> queryAll(PaymentInfo paymentInfo);

    /**
     * 新增数据
     *
     * @param paymentInfo 实例对象
     * @return 影响行数
     */
    int insert(PaymentInfo paymentInfo);

    /**
     * 修改数据
     *
     * @param paymentInfo 实例对象
     * @return 影响行数
     */
    int update(PaymentInfo paymentInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}