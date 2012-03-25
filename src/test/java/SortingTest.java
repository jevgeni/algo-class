import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SortingTest {

    private final Sorting sorting = new Sorting();

    @Test
    public void countComparisonsFor_2_elements_array() throws Exception {
        int[] array = {2, 1};
        sorting.quicksort(array);
        assertEquals(Arrays.asList(1, 2), asList(array));
        assertEquals(1, sorting.getNumberOfComparisons());
    }

    @Test
    public void countComparisonsFor_3_elements_array() throws Exception {
        int[] array = {3, 2, 1};
        sorting.quicksort(array);
        assertEquals(3, sorting.getNumberOfComparisons());
        assertEquals(Arrays.asList(1, 2, 3), asList(array));
    }

    @Test
    public void countComparisonsFor_4_elements_array() throws Exception {
        int[] array = {4, 3, 2, 1};
        sorting.quicksort(array);
        assertEquals(6, sorting.getNumberOfComparisons());
        assertEquals(Arrays.asList(1, 2, 3, 4), asList(array));
    }

    @Test
    public void resetNumberOfComparisonsEachQuickSort() throws Exception {
        sorting.quicksort(new int[] {2, 1});
        sorting.quicksort(new int[] {2, 1});
        sorting.quicksort(new int[] {2, 1});
        assertEquals(1, sorting.getNumberOfComparisons());
    }

    private List<Integer> asList(int[] array) {
        ArrayList<Integer> list = new ArrayList<Integer>(array.length);
        for (int i : array) {
            list.add(i);
        }
        return list;
    }
}
