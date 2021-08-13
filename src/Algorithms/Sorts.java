package Algorithms;

public class Sorts {

    public static void insertionSort(int[] nums) {
        // Runs in O(N^2) on average
        // O(N) best case on sorted list implementation is more emergent here than
        // in bubble sort.

        // 1: For loop iterates to new, unsorted element; prior elements are sorted.
        for (int i = 0; i < nums.length; i++) {
        // 2: int j serves as a pointer element for the unsorted element.
            int j = i;
        // 3: While loop swaps nums[j] with nums[j - 1]. Terminates either when
        //    j is not greater than zero (first element) or the previous element is smaller.
            while ((j > 0) && (nums[j] < nums[j-1])) {
                int temp = nums[j];
                nums[j] = nums[j - 1];
                nums[j - 1] = temp;
                j -= 1;
            }
        }
    }

    public static void bubbleSort(int[] nums) {
        // Runs in O(N^2) on average.


        int end = nums.length - 1;
        int temp;
        // 1: End is a valid index that points to the place where the next biggest element goes.
        while (end > 0) {
        // 2: Swaps elements if current is bigger than next.
            for (int i = 0; i < end; i++) {
                if (nums[i] > nums[i + 1]) {
                    temp = nums[i];
                    nums[i] = nums[i + 1];
                    nums[i + 1] = temp;
                }
            }
            end -= 1;
        }
    }

    public static void bubbleSortII(int[] nums) {
        // If there is no swap in a pass, all the elements are in the correct position.
        // Allows for O(N) time on an already sorted list.
        int end = nums.length - 1;
        int temp;
        boolean hasSwapped;
        // 1: End is a valid index that points to the place where the next biggest element goes.
        while (end > 0) {
            // 2: Swaps elements if current is bigger than next.
            hasSwapped = false;
            for (int i = 0; i < end; i++) {
                if (nums[i] > nums[i + 1]) {
                    temp = nums[i];
                    nums[i] = nums[i + 1];
                    nums[i + 1] = temp;
                    hasSwapped = true;
                }
            }
            if (hasSwapped == false) {
                break;
            }
            end -= 1;
        }
    }

    public static void selectionSort(int[] nums) {
        int min;
        int temp;
        for (int i = 0; i < nums.length - 1; i++) {
            min = i;
            for (int j = i; j < nums.length; j++) {
                if (nums[j] < nums[min]) {
                    min = j;
                }
                temp = nums[min];
                nums[min] = nums[i];
                nums[i] = temp;
            }
        }
    }

    public static void heapify(int[] nums, int index, int range) {
        // Brings heap order to int current.
        // Will only consider items in range where range = nums.length is all items.
        int current = index;
        int swap;
        int temp;
        while ((current * 2) + 1 < range) {
            swap = current * 2 + 1;
            if (swap + 1 < range) {
                if ((nums[swap] > nums[current]) && (nums[swap] >= nums[swap + 1])) {
                    temp = nums[swap];
                    nums[swap] = nums[current];
                    nums[current] = temp;
                    current = swap;
                }
                else if ((nums[swap + 1] > nums[current]) && (nums[swap + 1] >= nums[swap])) {
                    swap += 1;
                    temp = nums[swap];
                    nums[swap] = nums[current];
                    nums[current] = temp;
                    current = swap;
                }
                else break;
            }
            else {
                if (nums[current] < nums[swap]) {
                    temp = nums[current];
                    nums[current] = nums[swap];
                    nums[swap] = temp;
                    current = swap;
                }
                else break;
            }
        }
    }

    public static void buildMaxHeap(int [] nums) {
        // Builds a max heap out of an unsorted array. Used to prime an array for heap sort.
        int current = (nums.length - 2) / 2;
        if (current == -1) return;
        while (current >= 0) {
            heapify(nums, current, nums.length);
            current -= 1;
        }
    }

    public static void heapSort(int[] nums) {
        // 
    }
}
