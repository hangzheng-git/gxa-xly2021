package com.gxa.gxaxly2021.service;

import com.gxa.gxaxly2021.dao.EmplDao;
import com.gxa.gxaxly2021.dto.ResultDTO;
import com.gxa.gxaxly2021.entity.Empl;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    /**
     * 登录抽血方法
     * @param empl
     * @return
     */
    ResultDTO login(Empl empl);
}
