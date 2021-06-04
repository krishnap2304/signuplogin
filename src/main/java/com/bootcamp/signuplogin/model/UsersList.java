package com.bootcamp.signuplogin.model;

import java.util.ArrayList;
import java.util.List;

public class UsersList {
    public List<User> getList() {
        return list;
    }

    public void setList(List<User> list) {
        this.list = list;
    }

    List<User> list = new ArrayList<>();
}
