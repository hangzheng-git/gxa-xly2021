package com.gxa.gxaxly2021.param;

import lombok.Data;

@Data
public class EmplPageParam {
    //默认第一条数据开始
    private Integer page = 1;
    //10条一页
    private Integer limit = 10;
}
