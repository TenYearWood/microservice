package com.csii.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description com.csii.pojo
 * @author: chengyu
 * @date: 2025-12-23 22:19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Payment implements Serializable {

    private Long id;

    private  String serial;
}
