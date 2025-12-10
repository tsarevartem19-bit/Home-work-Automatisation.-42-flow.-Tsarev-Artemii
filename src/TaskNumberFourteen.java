import java.util.Arrays;
public class TaskNumberFourteen {
static int len = 5;
static  int initialValue = 6;
public static void main(String[] args){
    printMassive(len,initialValue);
}
public static void printMassive(int len,int initialValue){
    int [] massive = new int [len];
    Arrays.fill(massive, initialValue);
    System.out.println(Arrays.toString(massive));
}
}
