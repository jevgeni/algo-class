import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SortingTest {

    private Sorting sorting = new Sorting(Sorting.PIVOT_IS_FIRST_ELEMENT);

    @Test
    public void countComparisonsFor_2_elements_array() throws Exception {
        int[] array = {2, 1};
        sorting.quickSort(array);
        assertEquals(Arrays.asList(1, 2), asList(array));
        assertEquals(1, sorting.getNumberOfComparisons());
    }

    @Test
    public void countComparisonsFor_3_elements_array() throws Exception {
        int[] array = {3, 2, 1};
        sorting.quickSort(array);
        assertEquals(3, sorting.getNumberOfComparisons());
        assertEquals(Arrays.asList(1, 2, 3), asList(array));
    }

    @Test
    public void countComparisonsFor_4_elements_array() throws Exception {
        int[] array = {4, 1, 3, 2};
        sorting.quickSort(array);
        assertEquals(5, sorting.getNumberOfComparisons());
        assertEquals(Arrays.asList(1, 2, 3, 4), asList(array));
    }

    @Test
    public void countComparisonsWhenPivotIsLastElement() throws Exception {
        sorting = new Sorting(Sorting.PIVOT_IS_LAST_ELEMENT);
        int[] array = {4, 1, 3, 2};

        sorting.quickSort(array);

        assertEquals(4, sorting.getNumberOfComparisons());
        assertEquals(Arrays.asList(1, 2, 3, 4), asList(array));
    }

    @Test
    public void detectMidPointForMidPointPivotStrategy() throws Exception {
        Sorting.MidElementPivotStrategy strategy = new Sorting.MidElementPivotStrategy();
        assertEquals(3, strategy.getMidPoint(3, 4)); // 3 (mid), 4
        assertEquals(6, strategy.getMidPoint(3, 10)); // 3, 4, 5, 6, 7, 8, 9, 10
        assertEquals(5, strategy.getMidPoint(3, 7)); // 3, 4, 5 (mid), 6, 7
        assertEquals(11, strategy.getMidPoint(10, 13)); // 10, 11 (mid), 12, 13
    }

    @Test
    public void findMedianOfThree() throws Exception {
        Sorting.MidElementPivotStrategy strategy = new Sorting.MidElementPivotStrategy();
        assertEquals(0, strategy.findMedianIndexOfThree(asArray(2, 3, 1), 0, 1, 2));
        assertEquals(0, strategy.findMedianIndexOfThree(asArray(2, 1, 3), 0, 1, 2));
        assertEquals(1, strategy.findMedianIndexOfThree(asArray(1, 2, 3), 0, 1, 2));
        assertEquals(1, strategy.findMedianIndexOfThree(asArray(3, 2, 1), 0, 1, 2));
        assertEquals(2, strategy.findMedianIndexOfThree(asArray(1, 3, 2), 0, 1, 2));
        assertEquals(2, strategy.findMedianIndexOfThree(asArray(3, 1, 2), 0, 1, 2));
    }

    @Test
    public void countComparisonsWhenPivotIsMidElement() throws Exception {
        sorting = new Sorting(Sorting.PIVOT_IS_MID_ELEMENT);
        int[] array = {4, 1, 5, 6, 3, 2};

        sorting.quickSort(array);

        assertEquals(8, sorting.getNumberOfComparisons());
        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6), asList(array));
    }

    @Test
    public void resetNumberOfComparisonsEachQuickSort() throws Exception {
        sorting.quickSort(new int[]{2, 1});
        sorting.quickSort(new int[]{2, 1});
        sorting.quickSort(new int[]{2, 1});
        assertEquals(1, sorting.getNumberOfComparisons());
    }

    private List<Integer> asList(int[] array) {
        ArrayList<Integer> list = new ArrayList<Integer>(array.length);
        for (int i : array) {
            list.add(i);
        }
        return list;
    }

    private int[] asArray(int... array) {
        return array;
    }
}
