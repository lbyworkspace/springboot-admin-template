package com.lby.template.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lby.template.enums.ResponseEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * Author: laishao
 * Date: 2022/5/11
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseVO<T> implements Serializable {

    /**
     * 异常码
     */
    private Integer code;

    /**
     * 描述
     */
    private String msg;

    /**
     * 数据
     */
    private T data;

    public ResponseVO(ResponseEnum responseEnum) {
        this.code = responseEnum.getCode();
        this.msg = responseEnum.getMsg();
    }

    public ResponseVO(ResponseEnum responseEnum, T data) {
        this.code = responseEnum.getCode();
        this.msg = responseEnum.getMsg();
        this.data = data;
    }

    public static ResponseVO success(){
        return new ResponseVO(ResponseEnum.OK);
    }

    public static <T> ResponseVO<T> success(T data){
        return new ResponseVO<T>(ResponseEnum.OK, data);
    }

    public static ResponseVO error(ResponseEnum responseEnum){
        return new ResponseVO(responseEnum);
    }

}
