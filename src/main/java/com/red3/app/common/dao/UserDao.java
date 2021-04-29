package com.red3.app.common.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.red3.app.common.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Entity com.red3.app.common.entity.User
 */
@Mapper
public interface UserDao extends BaseMapper<User> {

}