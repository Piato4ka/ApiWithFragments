package com.example.offerwall;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pojo {

    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    private int dataListNumberObj;

    public List<Datum> getData() {
        return data;
    }

    public int getDataListNumberObj() {
        return dataListNumberObj;
    }

    public void setDataListNumberObj(int dataListNumberObj) {
        this.dataListNumberObj = dataListNumberObj;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

}