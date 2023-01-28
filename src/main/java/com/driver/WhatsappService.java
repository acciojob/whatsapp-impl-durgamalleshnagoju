package com.driver;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WhatsappService {

    WhatsappRepository whatsappRepository = new WhatsappRepository();

    public String createUser(String name, String mobile){
        User user = new User(name, mobile);
        HashSet<String > userMobile = whatsappRepository.getUserMobile();
        if(userMobile.contains(mobile)){
            return "User already exists";
        }
        whatsappRepository.setUserMobile(mobile);
        return "SUCCESS";
    }
    public Group createGroup(List<User> users){
        int numberOfParticipants = users.size();
        if(numberOfParticipants <2){
            return null;
        }
        String groupName;
        if(numberOfParticipants ==2){
            groupName = users.get(1).getName();
        } else {
            groupName = "Group "+(whatsappRepository.getCustomGroupCount()+1);
            whatsappRepository.setCustomGroupCount();
        }
        // adding the group to repository
        HashMap<Group, List<User>> map = whatsappRepository.getGroupUserMap();
        HashMap<Group, User> adminMap = whatsappRepository.getAdminMap();

        Group group = new Group(groupName, numberOfParticipants);
        map.put(group, users);
        adminMap.put(group, users.get(0));
        return group;
    }

    public int createMessage(String content){
        Message message = new Message(1, content);
        return message.getId();
    }

    public int sendMessage(Message message, User sender, Group group) throws Exception{
        if(!isValidGroup(group)){
            throw new RuntimeException("Group does not exist");
        }
        if(!isValidUser(sender, group)){
            throw new RuntimeException("You are not allowed to send message");
        }
        whatsappRepository.updateSenderMap(message, sender);
        whatsappRepository.updateGroupMessageMap(group,message);
        return whatsappRepository.numberOfMessages(group);
    }

    public String changeAdmin(User approver, User user, Group group) throws Exception{
        if(!isValidGroup(group)){
            //throw new RuntimeException("Group does not exist");
            return "Group does not exist";
        }
        if(!whatsappRepository.getAdminMap().get(group).equals(approver)){
            //throw new RuntimeException("Approver does not have rights");
            return "Approver does not have rights";
        }
        if(!isValidUser(user, group)){
            throw new RuntimeException("User is not a participant");
        }
        whatsappRepository.setAdminMap(group,user);
        return "SUCCESS";
    }

    public int removeUser(User user) throws Exception{
        return 0;
    }

    public String findMessage(Date start, Date end, int K) throws Exception{
        return " ";
    }
    public boolean isValidGroup(Group group){
        HashMap<Group, List<User>> map = whatsappRepository.getGroupUserMap();
        return map.containsKey(group);
    }
    public boolean isValidUser(User newUser, Group group){
        HashMap<Group, List<User>> map = whatsappRepository.getGroupUserMap();
        List<User> users = map.get(group);
        for(User user : users){
            if(user.equals(newUser)){
                return true;
            }
        }
        return false;
    }
}
