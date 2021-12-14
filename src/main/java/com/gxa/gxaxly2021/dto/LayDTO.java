package com.gxa.gxaxly2021.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LayDTO {
    /**
     * 状态码
     * */
    private Integer code;
    /**
     * 提示信息
     * */
    private String msg;
    /**
     * 数据的总的条数
     */
    private Long count;
    /**
     * 返回数据
     */
    private Object data;
    public LayDTO(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public LayDTO(Long count, Object data) {
        this.code = 0;
        this.msg = "";
        this.count = count;
        this.data = data;
    }
}
