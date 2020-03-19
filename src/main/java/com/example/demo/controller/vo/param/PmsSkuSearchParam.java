package com.example.demo.controller.vo.param;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Admin
 * @title: PmsSkuSearchParam
 * @projectName demo
 * @description: TODO
 * @date 2020/3/1 14:15
 */
@Getter
@Setter
@ToString
public class PmsSkuSearchParam implements Serializable {
    private static final long serialVersionUID = -6442436258508958707L;
    private String keyword;
    private Long catalog3Id;
    private Long[] valueId;
    private int pageNumber = 1;
    private int pageSize = 12;
}
