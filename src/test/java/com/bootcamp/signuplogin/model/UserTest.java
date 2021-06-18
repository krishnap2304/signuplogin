package com.bootcamp.signuplogin.model;

import org.junit.Test;

import java.util.HashSet;

public class UserTest {
    User user;
    private void setUp(){
        user = new User();
        HashSet<Role> roles = new HashSet<>();
        user.setCaptchaResp("test");
        user.setUsername("testuser");
        user.setPassword("Test1234");
        roles.add(new Role(RoleEnum.ROLE_USER));
        user.setRoles(roles);
        user.setEmail("testuser@gmail.com");
        user.setNewPassword("Abcd12345");
        user.setResetPasswordToken("resetpwd token");
    }

    @Test
    public void test(){
        user.getCaptchaResp();
        user.getEmail();
        user.getUsername();
        user.getPassword();
        user.getNewPassword();
        user.getResetPasswordToken();
        user.getRoles();
    }

}
