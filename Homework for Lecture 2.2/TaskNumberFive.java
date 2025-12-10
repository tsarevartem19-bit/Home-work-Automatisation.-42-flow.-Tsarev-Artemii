public  class TaskNumberFive {
public static void main(String[] args){
    System.out.println(checkSum(5,15));
}
public static boolean checkSum(int a, int b){
    int sum = a + b;
    if (sum >= 10 && sum <= 20){
        return true;
    }
    else{
        return false;
    }
}
}

