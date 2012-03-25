import java.util.Scanner;

public class Sorting {
    public static void main(String[] args) {
        int[] numbers = new int[10000];

        Scanner scanner = new Scanner(Inversions.class.getResourceAsStream("QuickSort.txt"));
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.parseInt(scanner.nextLine());
        }

        if (scanner.hasNext()) throw new IllegalStateException("check the array size");

        System.out.println(new Sorting().quicksort(numbers));
    }


    public long quicksort(int[] numbers) {
        return quicksort(numbers, 0, numbers.length - 1);
    }

    private long quicksort(int[] numbers, int left, int right) {
        if (right - left < 1) return 0;

        preparePivot(numbers, left, right);
        int split = partition(numbers, left, right);

        int comparisons = right - left;
        comparisons += quicksort(numbers, left, split - 1);
        comparisons += quicksort(numbers, split + 1, right);
        return comparisons;
    }

    protected void preparePivot(int[] numbers, int left, int right) {
    }

    private int partition(int[] numbers, int left, int right) {
        int pivot = numbers[left];
        int i = left + 1;
        for(int j = left + 1; j <= right; j++) {
            if (numbers[j] < pivot) {
                swap(numbers, i, j);
                i++;
            }
        }
        swap(numbers, left, i - 1);
        return i - 1;
    }

    protected void swap(int[] numbers, int i, int j) {
        int temp = numbers[j];
        numbers[j] = numbers[i];
        numbers[i] = temp;
    }
}
