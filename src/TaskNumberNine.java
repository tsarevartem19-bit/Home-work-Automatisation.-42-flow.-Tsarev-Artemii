public class TaskNumberNine {
public static void main(String[] args){
    System.out.println(yearIsLeapOrNot(2025));
}
public static boolean yearIsLeapOrNot(int a){
    if (a % 400 == 0){
        return  true;
    }
    else if (a % 100 == 0){
        return  false;
    }
    else if (a % 4 == 0){
        return  true;
    }
    else{
        return false;
    }
}
}
