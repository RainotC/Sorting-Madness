package pl.put.poznan.sorting.logic;

import net.bytebuddy.TypeCache;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SortingMadnessTest {
    @org.junit.jupiter.api.Test
    void sort_BogoSort_Asc_NoTimeLimit_5Elements() {
        String[] algorithms = {"bogo sort"};
        SortingMadness sortingMadness = new SortingMadness(algorithms);
        int[] arr = {5, 3, 1, 4, 2};
        long timeLimit = -1;
        List<Result> result = sortingMadness.sort(arr, 0, "asc", timeLimit);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, result.get(0).getSortedArray());
    }


    @org.junit.jupiter.api.Test
    void sort_BogoSort_Desc_NoTimeLimit_8Elements() {
        String[] algorithms = {"bogo sort"};
        SortingMadness sortingMadness = new SortingMadness(algorithms);
        int[] arr = {1, 6,2, 3, 4, 5, 1, 3};
        long timeLimit = -1;
        List<Result> result = sortingMadness.sort(arr, 0, "asc", timeLimit);
        assertArrayEquals(new int[]{1, 1, 2, 3, 3, 4, 5, 6}, result.get(0).getSortedArray());
    }


    @org.junit.jupiter.api.Test
    void sort_BubbleSort_Asc_NoTimeLimit_5Elements() {
        String[] algorithms = {"bubble sort"};
        SortingMadness sortingMadness = new SortingMadness(algorithms);
        int[] arr = {5, 3, 1, 4, 2};
        long timeLimit = -1;
        List<Result> result = sortingMadness.sort(arr, 0, "asc", timeLimit);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, result.get(0).getSortedArray());
    }

    @org.junit.jupiter.api.Test
    void sort_BubbleSort_Asc_TimeLimit_100Elements() {
        String[] algorithms = {"bubble sort"};
        SortingMadness sortingMadness = new SortingMadness(algorithms);
        int[] arr = {783, 562, 464, 297, 839, 448, 297, 415, 369, 678, 890, 152, 319, 744, 703, 484, 181, 45, 40, 923, 551, 966, 804, 653, 457, 740, 947, 390, 33, 587, 817, 750, 979, 965, 842, 531, 306, 864, 758, 420, 937, 474, 413, 828, 744, 312, 918, 177, 491, 621, 914, 526, 619, 123, 117, 97, 668, 604, 707, 85, 990, 944, 520, 593, 913, 609, 976, 244, 405, 1, 13, 831, 671, 375, 794, 6, 759, 705, 231, 375, 977, 470, 155, 197, 335, 329, 576, 794, 308, 494, 894, 417, 232, 439, 989, 590, 508, 445, 550, 319};
        long timeLimit = 100000000;
        List<Result> result = sortingMadness.sort(arr, 0, "asc", timeLimit);
        assertArrayEquals(new int[]{1, 6, 13, 33, 40, 45, 85, 97, 117, 123, 152, 155, 177, 181, 197, 231, 232, 244, 297, 297, 306, 308, 312, 319, 319, 329, 335, 369, 375, 375, 390, 405, 413, 415, 417, 420, 439, 445, 448, 457, 464, 470, 474, 484, 491, 494, 508, 520, 526, 531, 550, 551, 562, 576, 587, 590, 593, 604, 609, 619, 621, 653, 668, 671, 678, 703, 705, 707, 740, 744, 744, 750, 758, 759, 783, 794, 794, 804, 817, 828, 831, 839, 842, 864, 890, 894, 913, 914, 918, 923, 937, 944, 947, 965, 966, 976, 977, 979, 989, 990}, result.get(0).getSortedArray());
    }
    @org.junit.jupiter.api.Test
    void sort_MergeSort_Asc_NoTimeLimit_100Elements() {
        String[] algorithms = {"merge sort"};
        SortingMadness sortingMadness = new SortingMadness(algorithms);
        int[] arr = {198, 424, 570, 809, 71, 135, 540, 744, 771, 452, 10, 978, 780, 759, 710, 715, 166, 342, 870, 249, 450, 890, 773, 321, 444, 8, 579, 403, 3, 454, 756, 552, 387, 21, 146, 643, 267, 253, 120, 671, 587, 162, 660, 116, 128, 935, 122, 101, 34, 545, 579, 427, 402, 454, 574, 955, 343, 54, 544, 24, 491, 718, 645, 399, 911, 818, 1000, 338, 354, 900, 178, 131, 799, 41, 180, 376, 392, 987, 849, 572, 65, 558, 363, 570, 866, 984, 505, 308, 601, 58, 677, 989, 495, 673, 336, 834, 397, 802, 169, 267};
        long timeLimit = -1;
        List<Result> result = sortingMadness.sort(arr, 0, "asc", timeLimit);
        assertArrayEquals(new int[]{3, 8, 10, 21, 24, 34, 41, 54, 58, 65, 71, 101, 116, 120, 122, 128, 131, 135, 146, 162, 166, 169, 178, 180, 198, 249, 253, 267, 267, 308, 321, 336, 338, 342, 343, 354, 363, 376, 387, 392, 397, 399, 402, 403, 424, 427, 444, 450, 452, 454, 454, 491, 495, 505, 540, 544, 545, 552, 558, 570, 570, 572, 574, 579, 579, 587, 601, 643, 645, 660, 671, 673, 677, 710, 715, 718, 744, 756, 759, 771, 773, 780, 799, 802, 809, 818, 834, 849, 866, 870, 890, 900, 911, 935, 955, 978, 984, 987, 989, 1000}, result.get(0).getSortedArray());
    }

    @org.junit.jupiter.api.Test
    void sort_MergeSort_QuickSort_BubbleSort(){
        String[] algorithms = {"merge sort", "quick sort", "bubble sort"};

        SortingMadness sortingMadness = new SortingMadness(algorithms);
        int[] arr = {198, 424, 570, 809, 71, 135, 540, 744, 771, 452, 10, 978, 780, 759, 710, 715, 166, 342, 870, 249, 450, 890, 773, 321, 444, 8, 579, 403, 3, 454, 756, 552, 387, 21, 146, 643, 267, 253, 120, 671, 587, 162, 660, 116, 128, 935, 122, 101, 34, 545, 579, 427, 402, 454, 574, 955, 343, 54, 544, 24, 491, 718, 645, 399, 911, 818, 1000, 338, 354, 900, 178, 131, 799, 41, 180, 376, 392, 987, 849, 572, 65, 558, 363, 570, 866, 984, 505, 308, 601, 58, 677, 989, 495, 673, 336, 834, 397, 802, 169, 267};
        long timeLimit = -1;
        List<Result> result = sortingMadness.sort(arr, 0, "asc", timeLimit);
        assertArrayEquals(new int[]{3, 8, 10, 21, 24, 34, 41, 54, 58, 65, 71, 101, 116, 120, 122, 128, 131, 135, 146, 162, 166, 169, 178, 180, 198, 249, 253, 267, 267, 308, 321, 336, 338, 342, 343, 354, 363, 376, 387, 392, 397, 399, 402, 403, 424, 427, 444, 450, 452, 454, 454, 491, 495, 505, 540, 544, 545, 552, 558, 570, 570, 572, 574, 579, 579, 587, 601, 643, 645, 660, 671, 673, 677, 710, 715, 718, 744, 756, 759, 771, 773, 780, 799, 802, 809, 818, 834, 849, 866, 870, 890, 900, 911, 935, 955, 978, 984, 987, 989, 1000},
                result.get(2).getSortedArray());
    }

    @org.junit.jupiter.api.Test
    void sort_QuickSort_BubbleSort_ShellSort_SelectionSort(){
        String[] algorithms = {"quick sort", "bubble sort", "shell sort", "selection sort"};

        SortingMadness sortingMadness = new SortingMadness(algorithms);
        int[] arr = {198, 424, 570, 809, 71, 135, 540, 744, 771, 452, 10, 978, 780, 759, 710, 715, 166, 342, 870, 249, 450, 890, 773, 321, 444, 8, 579, 403, 3, 454, 756, 552, 387, 21, 146, 643, 267, 253, 120, 671, 587, 162, 660, 116, 128, 935, 122, 101, 34, 545, 579, 427, 402, 454, 574, 955, 343, 54, 544, 24, 491, 718, 645, 399, 911, 818, 1000, 338, 354, 900, 178, 131, 799, 41, 180, 376, 392, 987, 849, 572, 65, 558, 363, 570, 866, 984, 505, 308, 601, 58, 677, 989, 495, 673, 336, 834, 397, 802, 169, 267};
        long timeLimit = -1;
        List<Result> result = sortingMadness.sort(arr, 0, "asc", timeLimit);
        assertArrayEquals(new int[]{3, 8, 10, 21, 24, 34, 41, 54, 58, 65, 71, 101, 116, 120, 122, 128, 131, 135, 146, 162, 166, 169, 178, 180, 198, 249, 253, 267, 267, 308, 321, 336, 338, 342, 343, 354, 363, 376, 387, 392, 397, 399, 402, 403, 424, 427, 444, 450, 452, 454, 454, 491, 495, 505, 540, 544, 545, 552, 558, 570, 570, 572, 574, 579, 579, 587, 601, 643, 645, 660, 671, 673, 677, 710, 715, 718, 744, 756, 759, 771, 773, 780, 799, 802, 809, 818, 834, 849, 866, 870, 890, 900, 911, 935, 955, 978, 984, 987, 989, 1000},
                result.get(3).getSortedArray());
    }

    void sort_All_CheckShellSort(){
        String[] algorithms = {"quick sort", "bubble sort", "selection sort", "bogo sort", "merge sort", "shell sort"};

        SortingMadness sortingMadness = new SortingMadness(algorithms);
        int[] arr = {872, 855, 303, 560, 167, 675, 799, 472, 761, 440, 973, 812, 390, 498, 733, 121, 274, 406, 84, 469, 458, 360, 955, 671, 95, 564, 210, 94, 805, 885, 178, 10, 530, 700, 582, 594, 820, 734, 318, 913, 442, 777, 710, 226, 245, 346, 112, 611, 723, 273};
        long timeLimit = -1;
        List<Result> result = sortingMadness.sort(arr, 0, "asc", timeLimit);
        assertArrayEquals(new int[]{10, 84, 94, 95, 112, 121, 167, 178, 210, 226, 245, 273, 274, 303, 318, 346, 360, 390, 406, 440, 442, 458, 469, 472, 498, 530, 560, 564, 582, 594, 611, 671, 675, 700, 710, 723, 733, 734, 761, 777, 799, 805, 812, 820, 855, 872, 885, 913, 955, 973},
                result.get(5).getSortedArray());
    }
}