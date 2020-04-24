package com.example.myapplication2;

import com.example.myapplication2.entity.Contact;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private static final Store INST = new Store();

    private final List<Contact> contacts = new ArrayList<>();

    private Store() {
    }

    public static Store getStore() {
        return INST;
    }

    public void add(Contact contact) {
        this.contacts.add(contact);
    }

    public List<Contact> getAll() {
        return this.contacts;
    }

    public int size() {
        return this.contacts.size();
    }

    public Contact get(int index) {
        return this.contacts.get(index);
    }

    public void delete(int index) {
        this.contacts.remove(index);
    }
}
