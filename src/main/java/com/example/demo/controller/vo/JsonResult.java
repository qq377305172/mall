package com.example.demo.controller.vo;

import lombok.*;

import java.io.Serializable;

/**
 * @author Whyn
 * @date 2020/2/19 20:44
 */
@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JsonResult implements Serializable {
    private Integer code = null;
    private String msg = null;
    private Object data = null;

    public JsonResult success(Object data) {
        return new JsonResult(200, "成功", data);
    }

    public JsonResult fail(String msg) {
        return new JsonResult(500, msg, null);
    }
}
