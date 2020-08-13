package org.augustus.entity.dto;

import lombok.Data;

/**
 * @author LinYongJin
 * @date 2020/8/13 10:38
 */
@Data
public class Result<T> {

    private Integer code;

    private T data;

    private String message;
}
