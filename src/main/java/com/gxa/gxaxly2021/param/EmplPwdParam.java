package com.gxa.gxaxly2021.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmplPwdParam {
    private String emplAccount;
    private String oldPwd;
    private String newPwd;
    private String rePwd;
}
