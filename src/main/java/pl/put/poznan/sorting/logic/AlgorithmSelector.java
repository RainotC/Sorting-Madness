package pl.put.poznan.sorting.logic;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/*
It's a class that contains functions for executing sorts. It probably will need a lot of changes to match structure of the rest of
the code, but it can be used for test and creating blueprint of executing sort functions
 */
public class AlgorithmSelector {
    public void selector() {
        Scanner myScanner = new Scanner(System.in);
/*        System.out.println("How big array?");
        int size=0;
        try {
            size = Integer.parseInt(myScanner.nextLine());
        }
        catch(Exception e) {
            System.out.println("Input must be a positive integer");
        }
        try{
            if (size <= 0) {
            }
        }
        catch(Exception e) {
            System.out.println("Input must be a positive integer");
        }*/

        //parameters for testing
        int size = 100000;
        boolean showSorted = false;

        int[] arr = new int[size];
        randomArray(arr);
        int[] arrBogo = arr.clone();
        int[] arrBubble = arr.clone();
        int[] arrMerge = arr.clone();
        int[] arrQuick = arr.clone();
        int[] arrSelection = arr.clone();
        int[] arrShell = arr.clone();
        System.out.println("Time of (in milliseconds):");
        System.out.println("QuickSort " + getQuickSort(arrQuick, showSorted));
        System.out.println("MergeSort " + getMergeSort(arrMerge, showSorted));
        System.out.println("ShellSort " + getShellSort(arrShell, showSorted));
        System.out.println("SelectionSort " + getSelectionSort(arrSelection,  showSorted));
        System.out.println("BubbleSort " + getBubbleSort(arrBubble, showSorted));
        System.out.println("BogoSort " + getBogoSort(arrBogo, showSorted));
    }


    public static void randomArray(int[] arr) {
        Random rd = new Random(); // creating Random object
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rd.nextInt();
        }
    }

    public static long getBogoSort(int[] arr, boolean showSorted) {
        BogoSort sorter = new BogoSort();
        long time = sorter.sort(arr,0, arr.length-1);
        if (showSorted) System.out.println(Arrays.toString(arr));
        return time;
    }

    public static long getBubbleSort(int[] arr, boolean showSorted) {
        BubbleSort sorter = new BubbleSort();
        long time = sorter.sort(arr,0, arr.length-1);
        if (showSorted) System.out.println(Arrays.toString(arr));
        return time;
    }

    public static long getMergeSort(int[] arr, boolean showSorted) {
        MergeSort sorter = new MergeSort();
        long time = sorter.sort(arr,0, arr.length-1);
        if (showSorted) System.out.println(Arrays.toString(arr));
        return time;
    }

    public static long getQuickSort(int[] arr, boolean showSorted) {
        QuickSort sorter = new QuickSort();
        long time = sorter.sort(arr,0, arr.length-1);
        if (showSorted) System.out.println(Arrays.toString(arr));
        return time;
    }

    public static long getSelectionSort(int[] arr, boolean showSorted) {
        SelectionSort sorter = new SelectionSort();
        long time = sorter.sort(arr,0, arr.length-1);
        if (showSorted) System.out.println(Arrays.toString(arr));
        return time;
    }

    public static long getShellSort(int[] arr, boolean showSorted) {
        ShellSort sorter = new ShellSort();
        long time = sorter.sort(arr,0, arr.length-1);
        if (showSorted) System.out.println(Arrays.toString(arr));
        return time;
    }

}

