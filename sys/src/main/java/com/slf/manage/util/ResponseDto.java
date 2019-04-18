package com.slf.manage.util;

import com.netflix.ribbon.proxy.annotation.Http;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * 数据返回对象
 * Created by jdshao on 2017/7/28
 */

@Getter
@Setter
@NoArgsConstructor
public class ResponseDto {

    /**
     * 状态码
     */
    private int status;

    /**
     * 数据信息
     */
    private String message;

    /**
     * 数据对象
     */
    private Object data;

    public ResponseDto(HttpStatus httpStatus, Object data) {
        this.status = httpStatus.value();
        this.message = httpStatus.name();
        this.data = data;
    }
}
