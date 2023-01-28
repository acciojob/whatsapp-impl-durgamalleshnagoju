package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class WhatsappRepository {

    //Assume that each user belongs to at most one group
    //You can use the below mentioned hashmaps or delete these and create your own.
    private HashMap<Group, List<User>> groupUserMap;
    private HashMap<Group, List<Message>> groupMessageMap;
    private HashMap<Message, User> senderMap;
    private HashMap<Group, User> adminMap;
    private HashSet<String> userMobile;
    private int customGroupCount;
    private int messageId;

    public WhatsappRepository(){
        this.groupMessageMap = new HashMap<Group, List<Message>>();
        this.groupUserMap = new HashMap<Group, List<User>>();
        this.senderMap = new HashMap<Message, User>();
        this.adminMap = new HashMap<Group, User>();
        this.userMobile = new HashSet<>();
        this.customGroupCount = 0;
        this.messageId = 0;
    }

    public HashMap<Group, List<User>> getGroupUserMap() {
        return groupUserMap;
    }

    public void UpdateGroupUserMap(Group group, User user) {
        if((groupUserMap.get(group).size()) == 0){
            List<User> list = new ArrayList<>();
            list.add(user);
            groupUserMap.put(group, list);
            return;
        }
        groupUserMap.get(group).add(user);
    }

    public HashMap<Group, List<Message>> getGroupMessageMap() {
        return groupMessageMap;
    }

    public void updateGroupMessageMap(Group group, Message message) {
        if((groupMessageMap.get(group).size())==0){
            List<Message> list = new ArrayList<>();
            list.add(message);
            groupMessageMap.put(group, list);
            return;
        }
        this.groupMessageMap.get(group).add(message);
    }

    public HashMap<Message, User> getSenderMap() {
        return senderMap;
    }

    public void updateSenderMap(Message message, User user) {
        this.senderMap.put(message, user);
    }

    public HashMap<Group, User> getAdminMap() {
        return adminMap;
    }

    public void setAdminMap(Group group, User user) {
        this.adminMap.put(group, user);
    }

    public HashSet<String> getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String mobile) {
        this.userMobile.add(mobile);
    }

    public int getCustomGroupCount() {
        return customGroupCount;
    }

    public void setCustomGroupCount() {
        this.customGroupCount++;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int numberOfMessages(Group group){
        return this.groupMessageMap.get(group).size();
    }
}
