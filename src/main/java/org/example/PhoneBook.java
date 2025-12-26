package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneBook {
    private Map<String, List<String>> phoneBook = new HashMap<>();


    public  void add(String lastName, String phoneNumber){
        List<String> phones = phoneBook.getOrDefault(lastName, new ArrayList<>());
        phones.add(phoneNumber);
        phoneBook.put(lastName, phones);
    }

    public List<String> get(String lastName) {
        return phoneBook.getOrDefault(lastName, new ArrayList<>());
    }
}
