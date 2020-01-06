package com.itworkorder.sqlgen.pojo;

import java.util.Set;

/**
 * @author yucheng.liu@ucarinc.com
 * Date: 2020-1-2  17:03
 * @version 1.0
 */
public class User {

    private Set<String> roles;
    private String name;
    private String avatar;
    private String introduction;

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
