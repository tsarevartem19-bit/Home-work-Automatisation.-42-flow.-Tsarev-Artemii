package org.example;
import java.time.LocalDate;

public class Product {
   String name;
   LocalDate date;
   String manufacturer;
   String country;
   int price;
   boolean statusBooking;

   public Product(String name,LocalDate date,String manufacturer,String country,int price,boolean statusBooking){
      this.name = name;
     this.date = date;
      this.manufacturer = manufacturer;
      this.country = country;
      this.price = price;
      this.statusBooking = statusBooking;
   }
   public void printInfo(){
      System.out.println("Название: " + name);
      System.out.println("Дата производства: " + date);
      System.out.println("Производитель: " + manufacturer);
      System.out.println("Страна происхождения: " + country);
      System.out.println("Цена: " + price);
      System.out.println("Cостояние бронирования покупателем: " + statusBooking);
   }
}