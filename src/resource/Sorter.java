package resource;

import java.util.ArrayList;
import java.util.Random;

public class Sorter {

    /**
     * Sorts a certain range of an array list.
     * @param list the list to be sorted
     * @param begin the beginning of the range to be sorted
     * @param end the end of the range to be sorted
     * @param <T> the type of the data stored in the array list
     */
    public static <T extends Comparable<T>> void sort(ArrayList<T> list, int begin, int end) {
        if(end - begin <= 10) insertionSort(list);
        else {
            quickSort(list, begin, end);
        }
    }

    /**
     * Sorts an array list. If the size of the list is smaller than 10, the list will be sorted using insertion sort.
     * @param list the list to be sorted
     * @param <T> the type of the data stored in the array list
     */
    public static <T extends Comparable<T>> void sort(ArrayList<T> list) {
        int size = list.size();
        if(size <= 10) insertionSort(list);
        else {
            quickSort(list, 0, size - 1);
        }
    }

    /**
     * Performs insertion sort on an array list.
     * @param list the list to be sorted
     * @param <T> the type of the data stored in the array list
     */
    private static <T extends Comparable<T>> void insertionSort(ArrayList<T> list) {
        int index = 1;
        while (index < list.size()) {
            T element = list.get(index);
            int newIndex = index;

            while (newIndex > 0 && list.get(newIndex - 1).compareTo(element) > 0) {
                list.set(newIndex, list.get(newIndex - 1));
                newIndex--;
            }

            list.set(newIndex, element);
            index++;
        }
    }

    private static <T extends Comparable<T>> void quickSort(ArrayList<T> list, int begin, int end) {
        if (begin < end) {
            int pivot = partition(list, begin, end);
            if (pivot > begin) {
                quickSort(list, begin, pivot - 1);
            }
            if (pivot < end) {
                quickSort(list, pivot + 1, end);
            }
        }
    }

    private static <T extends Comparable<T>> int partition(ArrayList<T> list, int start, int end) {
        int pi = choosePivot(start, end);

        // New position for pivot element
        int last = end;

        // Move pivot element to the right edge of the array
        swap(list, pi, end);
        end--;

        while (start <= end) {
            if (list.get(start).compareTo(list.get(last)) < 0) {
                // Place smaller elements to the left
                start++;
            } else {
                swap(list, start, end);
                end--;
            }
        }

        // Move pivot element to its correct position
        swap(list, start, last);

        return start;
    }


    private static <T extends Comparable<T>> void swap(ArrayList<T> list, int x, int y) {
        T temp;
        temp = list.get(x);
        list.set(x, list.get(y));
        list.set(y, temp);
    }

    /**
     * Chooses a random pivot index between two integers
     * @param lowerBound the lower bound
     * @param upperBound the upper bound
     * @return an integer > lowerBound and < upperBound
     */
    private static int choosePivot(int lowerBound, int upperBound) {
        return new Random().nextInt(upperBound - lowerBound) + lowerBound;
    }


}
