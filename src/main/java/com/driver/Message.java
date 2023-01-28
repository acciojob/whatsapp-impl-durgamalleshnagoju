package com.driver;

import java.util.Date;

public class Message {
    private int id ;
    private String content;
    private Date timestamp;

    public Message(int id){
        this.id = id;
        this.timestamp = new Date();
    }

    public Message(int id , String content){
        this.id = id;
        this.content = content;
        this.timestamp = new Date();
    }
    public Message(int id, String content, Date timestamp){
        this.id = id;
        this.timestamp = timestamp;
        this.content = content;
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
