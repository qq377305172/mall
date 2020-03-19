package com.example.demo.controller.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Admin
 * @title: PmsSearchCrumb
 * @projectName demo
 * @description: 面包屑
 * @date 2020/3/2 14:20
 */
@Getter
@Setter
@ToString
public class PmsSearchCrumb implements Serializable {
    private static final long serialVersionUID = 506354899429153984L;
    private Long valueId;
    private String valueName;
    private String urlParam;
}
