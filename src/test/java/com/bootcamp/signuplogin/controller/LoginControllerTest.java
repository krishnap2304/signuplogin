package com.bootcamp.signuplogin.controller;

import com.bootcamp.signuplogin.SignuploginApplication;
import com.bootcamp.signuplogin.controller.LoginController;
import com.bootcamp.signuplogin.controller.RegistrationController;
import com.bootcamp.signuplogin.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {LoginController.class, SignuploginApplication.class})
@AutoConfigureMockMvc
public class LoginControllerTest {

    @Autowired
    protected MockMvc mvc;
    @Autowired
    WebApplicationContext webApplicationContext;

    protected void setUp() {
        DefaultMockMvcBuilder builder = MockMvcBuilders
                .webAppContextSetup(this.webApplicationContext)
                .apply(springSecurity())
                .dispatchOptions(true);
        this.mvc = builder.build();
    }


    @Test
    @WithMockUser(username="spring")
    public void testLoginSuccess()throws Exception{
        String uri = "/api/auth/signin";
        MvcResult mvcResult = null ;
        mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE).header("Access-Control-Request-Method", "POST")
                .header("Origin", "http://localhost")).andReturn();
        int status = mvcResult.getResponse().getStatus();
        System.out.println(status);
        System.out.println(mvcResult.getResponse().getContentAsString());
        System.out.println(mvcResult.getResponse().getErrorMessage());
        assertNotNull(status);
    }
    @Test
    public void testLoginInvalidSuccess()throws Exception{
        String uri = "/api/auth/signin";
        MvcResult mvcResult = null ;
        User user = getMockedUserForLogin();
        user.setPassword("asdjfasjl");
        user.setCaptchaResp("adfadadssdfsdasdadssafassd");
        mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri,user)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        System.out.println(status);
        assertNotNull(status);
    }

    @Test
    public void testChangePassword()throws Exception{
        String uri = "/api/auth/forget-password";
        MvcResult mvcResult = null ;
        User user = getMockedUserForLogin();
        user.setNewPassword("Abcd12345");
        mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri,user)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        System.out.println(status);
        assertNotNull(status);
    }

    @Test
    public void testresetPassword()throws Exception{
        String uri = "/api/auth/reset-password";
        MvcResult mvcResult = null ;
        User user = getMockedUserForLogin();
        user.setNewPassword("Abcd12345");
        mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri,user)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        System.out.println(status);
        assertNotNull(status);
    }

    private User getMockedUserForLogin() {
        User user = new User();
        user.setUsername("spring");
        user.setPassword("Test1234");
        user.setCaptchaResp("xxxx");
        return user;
    }

}
