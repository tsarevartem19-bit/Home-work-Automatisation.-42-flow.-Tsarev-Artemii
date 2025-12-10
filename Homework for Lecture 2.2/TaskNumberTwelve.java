import java.util.Arrays;
public class TaskNumberTwelve {
static int [] arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
public static void main(String[] args){
    for (int i = 0; i < arr.length; i++)
        if (arr[i] < 6){
            arr[i] = arr[i] * 2;
        }
        else{}
    System.out.println(Arrays.toString(arr));
}
}
