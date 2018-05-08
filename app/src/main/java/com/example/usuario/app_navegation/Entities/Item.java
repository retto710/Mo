package com.example.usuario.app_navegation.Entities;

import com.orm.SugarRecord;

import org.json.JSONException;
import org.json.JSONObject;

public class Item extends SugarRecord<Item> {

    private int userId;
    private int categoryId;
    private String userName;
    private String name;
    private String description;
    private Double referencialValue;
    private Boolean tradable;
    private String status;

    public Item() {
    }


    public int getuserId() {
        return userId;
    }

    public void setuserId(int id) {
        this.userId = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getReferencialValue() {
        return referencialValue;
    }

    public void setReferencialValue(Double referencialValue) {
        this.referencialValue = referencialValue;
    }

    public Boolean getTradable() {
        return tradable;
    }

    public void setTradable(Boolean tradable) {
        this.tradable = tradable;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toJson(){
        String jsonText = null;

        JSONObject js = new JSONObject();

        try{
            js.put("id",getId());
            js.put("userId",getuserId());
            js.put("categoryId",getCategoryId());
            js.put("name",getName());
            js.put("description",getDescription());
            js.put("referencialValue",getReferencialValue());
            js.put("tradable",getTradable());
            js.put("status",getStatus());
            jsonText = js.toString();
        }catch (JSONException e){

            e.printStackTrace();
        }
        return jsonText;
    }

}
