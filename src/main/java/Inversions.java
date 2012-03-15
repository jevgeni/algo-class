import java.util.Scanner;

public class Inversions {
    public static void main(String[] args) {
        int[] numbers = new int[100000];

        Scanner scanner = new Scanner(Inversions.class.getResourceAsStream("IntegerArray.txt"));
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.parseInt(scanner.nextLine());
        }


        int[] warmup = new int[numbers.length];
        System.out.print("Warming up JVM");
        for (int i = 0; i < 2000; i++) {
            if (i % 100 == 0) System.out.print('.');
            System.arraycopy(numbers, 0, warmup, 0, warmup.length);
            mergeSort(warmup);
        }
        System.out.println("\nJVM warmed up!");

        System.out.println("Running inversion count");
        long start = System.currentTimeMillis();
        long inversions = mergeSort(numbers);
        long end = System.currentTimeMillis();
        System.out.println("Time: " + (end - start) + "ms");
        System.out.println("Answer: " + inversions);
    }

    private static long mergeSort(int[] array) {
        return mergeSort(array, 0, array.length - 1);
    }

    private static long mergeSort(int[] array, int start, int end) {
        if (start >= end) return 0;

        int mid = start + (end - start) / 2;
        return mergeSort(array, start, mid) +
               mergeSort(array, mid + 1, end) +
               merge(array, start, mid, end);
    }

    private static long merge(int[] array, int start, int mid, int end) {
        int[] helper = new int[end - start + 1];

        int left = start;
        int right = mid + 1;
        int current = 0;
        long inversions = 0;
        
        while (left <= mid && right <= end) {
            if (array[left] <= array[right]) {
                helper[current++] = array[left++];
            }
            else {
                helper[current++] = array[right++];
                inversions += mid + 1 - left;
            }
        }

        while(left <= mid) helper[current++] = array[left++];
        while(right <= end) helper[current++] = array[right++];

        System.arraycopy(helper, 0, array, start, helper.length);
        return inversions;
    }
}
