package com.bootcamp.signuplogin.request;

public class LoginRequest {
    private String username;
    private String password;

    public boolean isPerf() {
        return isPerf;
    }

    public void setPerf(boolean perf) {
        isPerf = perf;
    }

    private boolean isPerf;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    private String newPassword;

    public String getCaptchaResp() {
        return captchaResp;
    }

    public void setCaptchaResp(String captchaResp) {
        this.captchaResp = captchaResp;
    }

    private String captchaResp;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
