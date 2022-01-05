package com.pro.starter.logger.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author lijunping on 2022/1/5
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserModel implements Serializable {
    private static final long serialVersionUID = -558786543946486488L;

    private Long uid;

    private String username;

    private String mobile;

}
