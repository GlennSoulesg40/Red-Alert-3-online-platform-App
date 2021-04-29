package com.red3.app.common.controller;

import com.red3.app.common.service.AUserService;
import com.red3.app.common.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: com.red3.app.common.controller.UserController
 * @description: TODO
 * @author: zxl
 * @create: 2021-04-30 0:13
 */
@RestController
public class UserController {
    @Autowired
    public AUserService aUserService;
    @RequestMapping("/test")
    public R test(){
        return R.ok();
    }
}
