package org.example;

public class MainPark {
    public static void main(String[] args) {
        Park park = new Park("Летающая тарелка.");
        park.namePark();
        Park.Info[] parksInfo = new Park.Info[1];
        parksInfo[0] = park.new Info("Аттракцион в виде летающей тарелки","10:00 - 18:00",  250 );
        parksInfo[0].printInfoPark();
    }

}
