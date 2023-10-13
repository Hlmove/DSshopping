package cn.Hlmove.entities;

import lombok.Data;

import java.sql.Timestamp;

/**
 * 管理员信息表的实体类
 * t_admin_admin
 */
@Data
public class TAdminAdminEntity {
    private Integer adminid;
    private Integer admingroupid;
    private String adminname;
    private String adminpwd;
    private String gender;
    private Timestamp regtime;
    private Timestamp modifytime;
    private String description;
    private String admingroupname;

}
