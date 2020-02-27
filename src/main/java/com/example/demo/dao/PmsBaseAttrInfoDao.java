package com.example.demo.dao;

import com.example.demo.entity.PmsBaseAttrInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * å±žæ€§è¡¨(PmsBaseAttrInfo)表数据库访问层
 *
 * @author makejava
 * @since 2020-02-18 16:46:44
 */
public interface PmsBaseAttrInfoDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PmsBaseAttrInfo queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<PmsBaseAttrInfo> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param pmsBaseAttrInfo 实例对象
     * @return 对象列表
     */
    List<PmsBaseAttrInfo> queryAll(PmsBaseAttrInfo pmsBaseAttrInfo);

    /**
     * 新增数据
     *
     * @param pmsBaseAttrInfo 实例对象
     * @return 影响行数
     */
    int insert(PmsBaseAttrInfo pmsBaseAttrInfo);

    /**
     * 修改数据
     *
     * @param pmsBaseAttrInfo 实例对象
     * @return 影响行数
     */
    int update(PmsBaseAttrInfo pmsBaseAttrInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    List<PmsBaseAttrInfo> queryAllByCatalog(Long catalog3Id);
}