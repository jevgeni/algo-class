import java.util.Scanner;

public class Sorting {
    public static final PivotStrategy PIVOT_IS_FIRST_ELEMENT = new PivotStrategy() {
        public void preparePivot(int[] numbers, int left, int right) {
            // do nothing, partition by default uses first element
        }
    };

    public static final PivotStrategy PIVOT_IS_LAST_ELEMENT = new PivotStrategy() {
        public void preparePivot(int[] numbers, int left, int right) {
            swap(numbers, left, right);
        }
    };

    public static final PivotStrategy PIVOT_IS_MID_ELEMENT = new PivotStrategy() {
        public void preparePivot(int[] numbers, int left, int right) {
            swap(numbers, left, right);
        }
    };


    private int numberOfComparisons;
    private PivotStrategy strategy;

    public Sorting(PivotStrategy strategy) {
        this.strategy = strategy;
    }

    public void quickSort(int[] numbers) {
        numberOfComparisons = 0;
        quickSort(numbers, 0, numbers.length - 1);
    }

    private void quickSort(int[] numbers, int left, int right) {
        if (right - left < 1) return;
        numberOfComparisons += right - left;

        strategy.preparePivot(numbers, left, right);
        int split = partition(numbers, left, right);
        quickSort(numbers, left, split - 1);
        quickSort(numbers, split + 1, right);

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

    protected static void swap(int[] numbers, int i, int j) {
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

        Sorting sorting = new Sorting(PIVOT_IS_FIRST_ELEMENT);
        sorting.quickSort(numbers);
        System.out.println(sorting.getNumberOfComparisons());
    }

    public interface PivotStrategy {
        void preparePivot(int[] numbers, int left, int right);
    }
}
