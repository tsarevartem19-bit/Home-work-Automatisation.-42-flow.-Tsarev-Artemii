public class TaskNumberThirteen {
static  int [] [] arr = new int [4] [4];
public static void main(String[] args){
    fillDiagonal();
    printMassive();
}
public static void fillDiagonal(){
    for(int i = 0; i < arr.length; i++)
        arr[i][i] = 1;
}
public static void printMassive(){
    for (int i = 0; i< arr.length; i++){
        for (int j = 0; j < arr[i].length; j++){
            System.out.print(arr[i][j]);}
            System.out.println(" ");}
}
}
