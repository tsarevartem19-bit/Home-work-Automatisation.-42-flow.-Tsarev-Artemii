package org.example;

public class Park {
    private String name;

    public  Park(String name){
        this.name = name;
    }

    public void namePark(){
        System.out.println("Название аттракциона: " + name);
    }

    public class Info {
        String info;
        String time;
        int price;


        public Info(String info, String time, int price) {
            this.info = info;
            this.time = time;
            this.price = price;
        }

        public void printInfoPark() {
            System.out.println("Информация об аттракционах: " + info);
            System.out.println("Время работы аттракциона: " + time);
            System.out.println("Стоимость: " + price);
        }
    }
}
