package cn.Hlmove.entities;

import lombok.Data;

import java.sql.Timestamp;

/**
 * 用户信息表实体类
 * t_user_user
 */
@Data
public class TUserUserEntity {
    private Integer userid;
    private Integer usergroupid;
    private String username;
    private String userpwd;
    private String gender;
    private Timestamp regtime;
    private Timestamp modifytime;
    private String description;
    private String usergroupname;
}
