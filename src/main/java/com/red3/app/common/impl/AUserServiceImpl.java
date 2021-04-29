package com.red3.app.common.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.red3.app.common.dao.UserDao;
import com.red3.app.common.entity.User;
import com.red3.app.common.service.AUserService;
import org.springframework.stereotype.Service;

/**
 * @className: com.red3.app.common.impl.AUserServiceImpl
 * @description: TODO
 * @author: zxl
 * @create: 2021-04-30 0:12
 */
@Service("AUserService")
public class AUserServiceImpl extends ServiceImpl<UserDao, User> implements AUserService {
}
