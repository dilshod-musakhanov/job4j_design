package ru.job4j.gc.prof;

import java.util.Random;
import java.util.Scanner;

public class ArraySorter {
    RandomArray randomArray = new RandomArray(new Random());
    BubbleSort bubbleSort = new BubbleSort();
    InsertSort insertSort = new InsertSort();
    MergeSort mergeSort = new MergeSort();
    private static final int ELEMENTS = 250000;
    private static final int EXIT = 5;
    private static final String LOG_SORTER = "logArraySorter.txt";

    public void createArrayAction() {
        randomArray.insert(ELEMENTS);
        System.out.printf("Array with %s created%n",
                ELEMENTS
        );
    }

    public void bubbleSortAction() {
        bubbleSort.sort(randomArray);
        System.out.printf(
                "Results of Bubble Sort operation can be found in %s%n",
                LOG_SORTER
        );
    }

    public void insertSortAction() {
        insertSort.sort(randomArray);
        System.out.printf(
                "Results of Insert Sort operation can be found in %s%n",
                LOG_SORTER
        );
    }

    public void mergeSortAction() {
        mergeSort.sort(randomArray);
        System.out.printf(
                "Results of Merge Sort operation can be found in %s%n",
                LOG_SORTER
        );
    }


    public void run() {
        boolean activeMenu = true;
        boolean isArrayCreated = false;
        while (activeMenu) {
            System.out.printf(
                    "===Choose number from Menu===%n"
                            + "1. Create Array %n"
                            + "2. Bubble Sort %n"
                            + "3. Insert Sort %n"
                            + "4. Merge Sort %n"
                            + "5. Exit %n"
                            + "===Enter number===%n"
            );
            Scanner scanner = new Scanner(System.in);
            int number = Integer.parseInt(scanner.nextLine());
            if (EXIT == number) {
                activeMenu = false;
                System.out.println("You exit from app successfully!");
            }
            if (0 > number || EXIT < number) {
                System.out.println("Select number only from Menu!");
            }
            if (!isArrayCreated && 1 != number && EXIT != number) {
                System.out.println(
                        "First create array and only then select any sort operation!"
                );
            }
            if (isArrayCreated && 2 == number) {
                bubbleSortAction();
            }
            if (isArrayCreated && 3 == number) {
                insertSortAction();
            }
            if (isArrayCreated && 4 == number) {
                mergeSortAction();
            }
            if (1 == number) {
                isArrayCreated = true;
                createArrayAction();
            }
        }
    }

    public static void main(String[] args) {
        ArraySorter arraySorter = new ArraySorter();
        arraySorter.run();
    }
}
