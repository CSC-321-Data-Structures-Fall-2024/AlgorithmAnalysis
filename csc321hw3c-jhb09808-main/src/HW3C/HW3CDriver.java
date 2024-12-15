package HW3C;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Collections;

/**
 * HW3CDriver class to perform experimental analysis on ArrayList and LinkedList operations.
 * Name: Jerome Bustarga
 * ID: JHB09808
 * Date: 10/08/2024
 */
public class HW3CDriver {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Initial list size: ");
        int initialSize = scanner.nextInt();
        scanner.close();

        // Array of list sizes, increasing by factors of 2
        int[] sizes = new int[5];
        for (int i = 0; i < 5; i++) {
            sizes[i] = initialSize * (int) Math.pow(2, i);
        }

        // Print table headers
        System.out.printf("\n%-10s %-25s %-25s %-25s %-25s %-25s %-25s\n", 
            "List Size", "ArrayList addInOrder", "LinkedList addInOrder", 
            "ArrayList addInReverseOrder", "LinkedList addInReverseOrder", 
            "ArrayList addInRandomOrder", "LinkedList addInRandomOrder");

        // Perform operations and measure time for each list size
        for (int size : sizes) {
            List<Integer> arrayListOrder = new ArrayList<>();
            List<Integer> arrayListReverse = new ArrayList<>();
            List<Integer> arrayListRandom = new ArrayList<>();
            List<Integer> linkedListOrder = new LinkedList<>();
            List<Integer> linkedListReverse = new LinkedList<>();
            List<Integer> linkedListRandom = new LinkedList<>();

            long arrayListOrderTime = measureTime(() -> addInOrder(arrayListOrder, size));
            long linkedListOrderTime = measureTime(() -> addInOrder(linkedListOrder, size));
            long arrayListReverseTime = measureTime(() -> addInReverseOrder(arrayListReverse, size));
            long linkedListReverseTime = measureTime(() -> addInReverseOrder(linkedListReverse, size));
            long arrayListRandomTime = measureTime(() -> addInRandomOrder(arrayListRandom, size));
            long linkedListRandomTime = measureTime(() -> addInRandomOrder(linkedListRandom, size));

            // Print results for current list size
            System.out.printf("%-10d %-25d %-25d %-25d %-25d %-25d %-25d\n", 
                size, arrayListOrderTime, linkedListOrderTime, 
                arrayListReverseTime, linkedListReverseTime, 
                arrayListRandomTime, linkedListRandomTime);
        }
    }

    /**
     * Adds numbers in order from 0 to n-1 to the provided list using binary search for insertion.
     * @param numbers The list to add numbers to.
     * @param n The number of elements to add.
     */
    public static void addInOrder(List<Integer> numbers, int n) {
        for (int i = 0; i < n; i++) {
            int index = Collections.binarySearch(numbers, i);
            if (index < 0) index = -index - 1;
            numbers.add(index, i);
        }
    }

    /**
     * Adds numbers in reverse order from n-1 to 0 to the provided list using binary search for insertion.
     * @param numbers The list to add numbers to.
     * @param n The number of elements to add.
     */
    public static void addInReverseOrder(List<Integer> numbers, int n) {
        for (int i = n - 1; i >= 0; i--) {
            int index = Collections.binarySearch(numbers, i);
            if (index < 0) index = -index - 1;
            numbers.add(index, i);
        }
    }

    /**
     * Adds n random numbers to the provided list using binary search for insertion.
     * Uses the full range of int values for random numbers.
     * @param numbers The list to add numbers to.
     * @param n The number of elements to add.
     */
    public static void addInRandomOrder(List<Integer> numbers, int n) {
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            int randomNum = random.nextInt();
            int index = Collections.binarySearch(numbers, randomNum);
            if (index < 0) index = -index - 1;
            numbers.add(index, randomNum);
        }
    }

    /**
     * Measures the time taken to execute a task.
     * @param task The task to be executed.
     * @return The duration in milliseconds.
     */
    public static long measureTime(Runnable task) {
        long startTime = System.currentTimeMillis();
        task.run();
        long endTime = System.currentTimeMillis();
        return endTime - startTime; // Duration in milliseconds
    }
}
