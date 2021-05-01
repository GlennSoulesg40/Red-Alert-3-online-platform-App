package com.red3.app.common.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.red3.app.common.entity.User;

import com.red3.app.common.service.AUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * @className: com.red3.app.common.impl.UserDetailServiceImpl
 * @description: TODO
 * @author: zxl
 * @create: 2021-04-30 0:42
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    AUserService aUserService;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = aUserService.getOne(
                Wrappers.<User>lambdaQuery()
                        .eq(User::getUser_name, s)
        );

        if(user==null)
            return null;
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        Arrays.stream(user.getIdentity().split(",")).forEach(e->{
            authorities.add(new SimpleGrantedAuthority(e));
        });
        return new org.springframework.security.core.userdetails.User(s,passwordEncoder.encode(user.getPassword()),authorities);

    }
}
