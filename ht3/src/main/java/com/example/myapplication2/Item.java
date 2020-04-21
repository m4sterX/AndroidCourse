package com.example.myapplication2;


import java.util.Objects;

public class Item {
    private String name;
    private String email;
    private int setSRC;

    public Item(String name, String email, int setSRC) {
        this.name = name;
        this.email = email;
        this.setSRC = setSRC;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSetSRC() {
        return setSRC;
    }

    public void setSetSRC(int setSRC) {
        this.setSRC = setSRC;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(name, item.name) &&
                Objects.equals(email, item.email) &&
                Objects.equals(setSRC, item.setSRC);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, setSRC);
    }
}