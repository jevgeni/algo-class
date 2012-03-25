import java.util.Scanner;

public class Sorting {
    private int numberOfComparisons;

    public void quicksort(int[] numbers) {
        numberOfComparisons = 0;
        quicksort(numbers, 0, numbers.length - 1);
    }

    private void quicksort(int[] numbers, int left, int right) {
        if (right - left < 1) return;

        preparePivot(numbers, left, right);
        int split = partition(numbers, left, right);

        numberOfComparisons += right - left;

        quicksort(numbers, left, split - 1);
        quicksort(numbers, split + 1, right);
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

    public int getNumberOfComparisons() {
        return numberOfComparisons;
    }


    public static void main(String[] args) {
        int[] numbers = new int[10000];

        Scanner scanner = new Scanner(Inversions.class.getResourceAsStream("QuickSort.txt"));
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.parseInt(scanner.nextLine());
        }

        if (scanner.hasNext()) throw new IllegalStateException("check the array size");

        Sorting sorting = new Sorting();
        sorting.quicksort(numbers);
        System.out.println(sorting.getNumberOfComparisons());
    }
}
