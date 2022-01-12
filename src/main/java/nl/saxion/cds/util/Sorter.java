package nl.saxion.cds.util;

import java.util.*;

public class Sorter {
    // int value that is used to determine which sorting algorithm to use
    private static final int THRESHOLD = 10;

    private Sorter() {

    }

    /**
     * Sorts a List. If the size of the list is smaller than a predefined threshold, the list will be sorted using
     * insertion sort.
     *
     * @param list the list to be sorted
     * @param <T>  the type of the data stored in the list
     */
    public static <T extends Comparable<T>> void sort(List<T> list) {
        int size = list.size();
        if (size <= THRESHOLD) insertionSort(list);
        else {
            quickSort(list, 0, size - 1);
        }
    }

    /**
     * Sorts a List using a custom comparator. If the size of the list is smaller than a predefined threshold, the list
     * will be sorted using insertion sort.
     *
     * @param list the list to be sorted
     * @param <T>  the type of the data stored in the list
     */
    public static <T extends Comparable<T>> void sort(List<T> list, Comparator<T> comparator) {
        if (comparator == null) {
            sort(list);
        } else {
            int size = list.size();
            if (size <= THRESHOLD) {
                insertionSort(list, comparator);
            } else {
                quickSort(list, 0, size - 1, comparator);
            }
        }
    }

    /**
     * Performs insertion sort on a List using its elements' natural comparator.
     *
     * @param list the list to be sorted
     * @param <T>  the type of the data stored in the list
     */
    private static <T extends Comparable<T>> void insertionSort(List<T> list) {
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

    /**
     * Performs insertion sort on a List using a custom comparator.
     *
     * @param list       the list to be sorted
     * @param comparator the comparator to be used for sorting the list
     * @param <T>        the type of the data stored in the list
     */
    private static <T extends Comparable<T>> void insertionSort(List<T> list, Comparator<T> comparator) {
        int index = 1;
        while (index < list.size()) {
            T element = list.get(index);
            int newIndex = index;

            while (newIndex > 0 && comparator.compare(list.get(newIndex - 1), element) > 0) {
                list.set(newIndex, list.get(newIndex - 1));
                newIndex--;
            }

            list.set(newIndex, element);
            index++;
        }
    }

    /**
     * Perform quick sort on a specified range within a List using its elements' natural comparator.
     *
     * @param list  the list to be sorted
     * @param begin the beginning of the range
     * @param end   the end of the range
     * @param <T>   the type of the data stored in the list
     */
    private static <T extends Comparable<T>> void quickSort(List<T> list, int begin, int end) {
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

    /**
     * Perform quick sort on a specified range within a List using a custom comparator.
     *
     * @param list       the list to be sorted
     * @param begin      the beginning of the range
     * @param end        the end of the range
     * @param comparator the comparator to be used for sorting the list
     * @param <T>        the type of the data stored in the list
     */
    private static <T extends Comparable<T>> void quickSort(List<T> list, int begin, int end, Comparator<T> comparator) {
        if (begin < end) {
            int pivot = partition(list, begin, end, comparator);
            if (pivot > begin) {
                quickSort(list, begin, pivot - 1, comparator);
            }
            if (pivot < end) {
                quickSort(list, pivot + 1, end, comparator);
            }
        }
    }

    /**
     * Helper method for quickSort(). Partitions a given List around a pivot using a custom comparator.
     *
     * @param list  the list to be partitioned
     * @param start the beginning of the range to be partitioned
     * @param end   the beginning of the range to be partitioned
     * @param <T>   the type of the data stored in the array list
     * @return an integer representing the index of the pivot
     */
    private static <T extends Comparable<T>> int partition(List<T> list, int start, int end, Comparator<T> comparator) {
        int pi = choosePivot(start, end);

        // New position for pivot element
        int last = end;

        // Move pivot element to the right edge of the array
        swap(list, pi, end);
        end--;

        while (start <= end) {
            if (comparator.compare(list.get(start), list.get(last)) < 0) {
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

    /**
     * Helper method for quickSort(). Partitions a given List around a pivot using the natural comparator of the elements
     * within the list.
     *
     * @param list  the list to be partitioned
     * @param start the beginning of the range to be partitioned
     * @param end   the beginning of the range to be partitioned
     * @param <T>   the type of the data stored in the array list
     * @return an integer representing the index of the pivot
     */
    private static <T extends Comparable<T>> int partition(List<T> list, int start, int end) {
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

    /**
     * Helper method for partition(). Swaps the position of two elements within a List.
     *
     * @param list the List in which the two elements will be swapped
     * @param x    index of the first element
     * @param y    index of the second element
     * @param <T>  the type of the data stored in the list
     */
    private static <T extends Comparable<T>> void swap(List<T> list, int x, int y) {
        T temp;
        temp = list.get(x);
        list.set(x, list.get(y));
        list.set(y, temp);
    }

    /**
     * Chooses a random pivot index between two integers.
     *
     * @param lowerBound the lower bound
     * @param upperBound the upper bound
     * @return an integer > lowerBound and < upperBound
     */
    private static int choosePivot(int lowerBound, int upperBound) {
        return new Random().nextInt(upperBound - lowerBound) + lowerBound;
    }


}
