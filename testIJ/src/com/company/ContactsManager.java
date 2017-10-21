package com.company;

public class ContactsManager {
    // Fields:
    Contact [] myFriends;
    int friendsCount;
    // Constructor:
    ContactsManager(){
        this.friendsCount = 0;
        this.myFriends = new Contact[500];
    }

    void addContact(Contact contact){
        myFriends[friendsCount] = contact;
        friendsCount++;
    }

    Contact searchContact(String searchName){
        for(int i=0; i<friendsCount; i++){
            if(myFriends[i].getName().equals(searchName)){
                System.out.println("found");
                return myFriends[i];
            }
        }
        return null;
    }
}
