import java.util.Scanner;

public class Sorting {
    public interface PivotStrategy {
        void preparePivot(int[] numbers, int left, int right);
    }

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

    public static class MidElementPivotStrategy implements PivotStrategy {
        public void preparePivot(int[] numbers, int left, int right) {
            int pivot = findMedianIndexOfThree(numbers, left, getMidPoint(left, right), right);
            swap(numbers, left, pivot);
        }

        int findMedianIndexOfThree(int[] numbers, int left, int mid, int right) {
            int leftVal = numbers[left];
            int midVal = numbers[mid];
            int rightVal = numbers[right];
            
            if (isValueInBetween(leftVal, midVal, rightVal) ||
                isValueInBetween(leftVal, rightVal, midVal)) {
                return left;
            } else if (isValueInBetween(midVal, leftVal, rightVal) ||
                       isValueInBetween(midVal, rightVal, leftVal)) {
                return mid;
            } else {
                return right;
            }
        }

        private boolean isValueInBetween(int value, int lower, int larger) {
            return lower <= value && value <= larger;
        }

        int getMidPoint(int left, int right) {
            return (left + right) / 2;
        }
    }

    public static final PivotStrategy PIVOT_IS_MID_ELEMENT = new MidElementPivotStrategy();



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
        System.out.println(getNumberOfComparisons(PIVOT_IS_FIRST_ELEMENT, loadNumbers()));
        System.out.println(getNumberOfComparisons(PIVOT_IS_LAST_ELEMENT, loadNumbers()));
        System.out.println(getNumberOfComparisons(PIVOT_IS_MID_ELEMENT, loadNumbers()));
    }

    private static int getNumberOfComparisons(PivotStrategy pivotStrategy, int[] numbers) {
        Sorting sorting = new Sorting(pivotStrategy);
        sorting.quickSort(numbers);
        return sorting.getNumberOfComparisons();
    }

    private static int[] loadNumbers() {
        int[] numbers = new int[10000];

        Scanner scanner = new Scanner(Inversions.class.getResourceAsStream("QuickSort.txt"));
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.parseInt(scanner.nextLine());
        }

        if (scanner.hasNext()) throw new IllegalStateException("check the array size");
        return numbers;
    }
}
