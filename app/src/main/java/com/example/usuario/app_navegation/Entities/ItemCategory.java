package com.example.usuario.app_navegation.Entities;

import android.print.PrinterId;

public class ItemCategory {

    private int id;
    private String description;

    public ItemCategory(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
