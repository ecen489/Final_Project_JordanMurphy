package com.example.mini_project1_jordan_murphy;

import java.io.Serializable;

public class order implements Serializable {

    String item;

    public order() {}
    public void setOrder(String item){
        this.item = item;
    }

    public String getitem()       { return item; }
}
