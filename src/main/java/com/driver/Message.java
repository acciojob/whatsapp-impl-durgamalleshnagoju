package com.driver;

import java.util.Date;

public class Message {
    private int id = 0;
    private String content;
    private Date timestamp;

    public Message(String content){
        this.id ++;
        this.content = content;
        this.timestamp = new Date();
    }
    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getContent(){
        return this.content;
    }
    public void setContent(String content){
        this.content = content;
    }

    public Date getTimestamp(){
        return this.timestamp;
    }

}
