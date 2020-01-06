package com.itworkorder.sqlgen.controller.user;

import com.itworkorder.sqlgen.common.Result;
import org.springframework.stereotype.Controller;
import com.itworkorder.sqlgen.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yucheng.liu@ucarinc.com
 * Date: 2020-1-2  17:05
 * @version 1.0
 */
@Controller
@RequestMapping("/api/user")
public class UserController {
    @RequestMapping("/login")
    @ResponseBody
    public Result login() {
        return Result.getSuccessRe();
    }

    @RequestMapping("/info")
    @ResponseBody
    public Result info() {
        User temp = new User();
        temp.setName("范闲");
        Set<String> roles = new HashSet<>();
        roles.add("admin");
        roles.add("editor");
        temp.setRoles(roles);
        return Result.getSuccessRe(temp);
    }


}
