import java.util.Arrays;
public class TaskNumberEleven {
static int[] arr = new int [100];
public static void main(String[] args) {
    for (int i = 0; i < arr.length; i++)
        arr[i] = i + 1;
    System.out.println(Arrays.toString(arr));
}
}
