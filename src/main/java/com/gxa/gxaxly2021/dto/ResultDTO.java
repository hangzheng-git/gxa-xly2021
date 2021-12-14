package com.gxa.gxaxly2021.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultDTO {
    /**
     * 状态码
     * */
    private Integer code;
    /**
     * 提示信息
     * */
    private String msg;
    /**
     * 返回数据
     */
    private Object data;

    public ResultDTO(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


}
