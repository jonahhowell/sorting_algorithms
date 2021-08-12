package Algorithms;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] nums = {5, 8, 24, 57, 10, -40, 65, 73, 11, 13};
        Sorts.selectionSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
