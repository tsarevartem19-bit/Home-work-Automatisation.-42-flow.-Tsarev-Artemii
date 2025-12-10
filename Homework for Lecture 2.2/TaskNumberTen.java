public class TaskNumberTen {

    static int[] arr = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
    public static void main(String[] args) {
        changeArray();
    }

    public static void changeArray() {
        for (int j = 0; j < arr.length; j++) {
            if (arr[j] == 1) {
                arr[j] = 0;
                System.out.print(arr[j] + " ");
            } else {
                arr[j] = 1;
                System.out.print(arr[j] + " ");
            }
        }
    }
}
