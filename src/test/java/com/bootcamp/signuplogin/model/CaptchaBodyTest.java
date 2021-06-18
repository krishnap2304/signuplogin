package com.bootcamp.signuplogin.model;

import org.junit.Test;

public class CaptchaBodyTest {

    private CaptchaBody         captchaBody = new CaptchaBody();
    private void setUp(){
        captchaBody.setCaptchaGenerated("generated captcha");
        captchaBody.setCaptchaGenerated("entered captcha");
    }

   @Test
    public void test(){
        captchaBody.getCaptchaEntered();
        captchaBody.getCaptchaEntered();
    }
}
