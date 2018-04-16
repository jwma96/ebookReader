package com.example.d.ebookreader.model;

/**
 * Created by d on 2018/4/16.
 */

public class bookFile {
    private String name;
    private int imageId;
    private boolean isCho;
    public bookFile(String name,int imageId,boolean isCho){
        this.name=name;
        this.imageId=imageId;
        this.isCho=isCho;
    }
    public String getName(){
        return name;
    }
    public int getImageId(){
        return imageId;
    }
    public boolean getIsCho(){
        return isCho;
    }
}
