package org.example;

public class MainPhoneBook {
        public static void main(String[] args) {
            PhoneBook phoneBook = new PhoneBook();

            phoneBook.add("Иванов", "89992183615");
            phoneBook.add("Иванов", "89111248665");
            phoneBook.add("Петров", "+79991571425");
            phoneBook.add("Царев", "8800553535");
            phoneBook.add("Ковалев", "+79111471568");
            phoneBook.add("Митрофанов", "55555");
            phoneBook.add("Сидоров", "55555");

            System.out.println("Телефоны Иванова: " + phoneBook.get("Иванов"));
            System.out.println("Телефоны Сидорова: " + phoneBook.get("Сидоров"));
            System.out.println("Телефон Царева: " + phoneBook.get("Царев"));
        }
    }

