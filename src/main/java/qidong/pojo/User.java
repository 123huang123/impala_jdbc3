package qidong.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User{

    private Long id;

    //姓名
    private String name;

    //手机号
    private String phone;

    //性别 0 女 1 男
    private String sex;

    //身份证号
    private String idNumber;

    //头像
    private String avatar;

    //状态 0:禁用，1:正常
    private Integer status;
}