package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        ContactsManager cm = new ContactsManager();
        Contact c1 = new Contact();
        c1.set("n1","e1","019865314");
        cm.addContact(c1);

        Contact c2 = new Contact();
        c2.set("n2","e2","0198675314");
        cm.addContact(c2);
        //Contact cc = new Contact();
        Contact cc = cm.searchContact("n1");
        if(cc!=null) {
            cc.print();
        }
    }
}
