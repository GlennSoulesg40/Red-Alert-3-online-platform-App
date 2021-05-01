package com.red3.app.common.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @TableName user
 */
@Table(name="user")
@Data
public class User implements Serializable, UserDetails {
    /**
     * 
     *
     * @mbg.generated Fri Apr 30 00:08:22 CST 2021
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 用户名
     *
     * @mbg.generated Fri Apr 30 00:08:22 CST 2021
     */
    @TableField(value = "user_name")
    private String user_name;

    /**
     * 密码
     *
     * @mbg.generated Fri Apr 30 00:08:22 CST 2021
     */
    private String password;

    /**
     * QQ
     *
     * @mbg.generated Fri Apr 30 00:08:22 CST 2021
     */
    @TableField(value = "QQ")
    private String QQ;

    /**
     * 电话
     *
     * @mbg.generated Fri Apr 30 00:08:22 CST 2021
     */
    private String tel;

    /**
     * 头像链接
     *
     * @mbg.generated Fri Apr 30 00:08:22 CST 2021
     */
    private String head_image;

    /**
     * 登录时间
     *
     * @mbg.generated Fri Apr 30 00:08:22 CST 2021
     */
    private Date login_time;


    /**
     * 身份字段
     */
    private String identity;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table user
     *
     * @mbg.generated Fri Apr 30 00:08:22 CST 2021
     */
    private static final long serialVersionUID = 1L;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}