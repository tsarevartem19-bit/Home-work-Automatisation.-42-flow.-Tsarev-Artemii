public class TaskNumberSix {
public static void main(String[] args){
    checkNumber(-1);
}
public static void checkNumber(int a){
    if (a >= 0){
        System.out.println("Число: " + a + " положительное");
    }
    else{
        System.out.println("Число: "+ a + " отрицательное");
    }
}
}
