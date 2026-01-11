package com.csii.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @description com.csii.pojo
 * @author: chengyu
 * @date: 2025-12-23 22:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class CommonResult<T> {

    private Integer code;//返回状态码
    private String  message;//返回是否调用成功
    private  T data; //返回的数据

    public CommonResult(Integer code, String message) {
        this(code,message,null);
    }
}
