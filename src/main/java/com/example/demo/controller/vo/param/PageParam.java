package com.example.demo.controller.vo.param;

import lombok.*;

import java.io.Serializable;

/**
 * @author Whyn
 * @date 2020/2/19 20:26
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PageParam implements Serializable {
    private static final long serialVersionUID = -818587765597181418L;
    private Integer pageNumber = 1;
    private Integer pageSize = 100;
}
