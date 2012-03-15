import java.util.Arrays;

public class Inversions {
    public static void main(String[] args) {
        int[] numbers = {1, 3, 4, 6, 2, 0, 1, 3, 4, 6, 2, 0};
        
        mergeSort(numbers);
        System.out.println(Arrays.toString(numbers));
    }

    private static void mergeSort(int[] array) {
        mergeSort(array, 0, array.length - 1);
    }

    private static void mergeSort(int[] array, int start, int end) {
        if (start < end) {
            int mid = start + (end - start) / 2;
            mergeSort(array, start, mid);
            mergeSort(array, mid + 1, end);
            merge(array, start, mid, end);
        }
    }

    private static void merge(int[] array, int start, int mid, int end) {
        int[] helper = new int[end - start + 1];

        int left = start;
        int right = mid + 1;
        int current = 0;
        
        while (left <= mid && right <= end) {
            if (array[left] <= array[right])
                helper[current++] = array[left++];
            else
                helper[current++] = array[right++];
        }

        while(left <= mid) helper[current++] = array[left++];
        while(right <= end) helper[current++] = array[right++];

        System.arraycopy(helper, 0, array, start, helper.length);
    }
}
