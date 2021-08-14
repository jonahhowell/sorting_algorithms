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

    private static void heapify(int[] nums, int index, int range) {
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

    private static void buildMaxHeap(int [] nums) {
        // Builds a max heap out of an unsorted array. Used to prime an array for heap sort.
        int current = (nums.length - 2) / 2;
        if (current == -1) return;
        while (current >= 0) {
            heapify(nums, current, nums.length);
            current -= 1;
        }
    }

    public static void heapSort(int[] nums) {
        /*
         Always runs in O(N * log(N))
         No extra space needed, however it's not stable due to heap structure.
         First a max heap is built.
         Second it loops: the biggest element is popped off the heap and swapped with
         the last element in the heap, and the biggest element is now excluded. The
         remaining numbers are heapified, and the process repeats until all numbers
         are sorted.
        */

        int range = nums.length;
        int temp;
        buildMaxHeap(nums);
        while (range > 0) {
            temp = nums[range - 1];
            nums[range - 1] = nums[0];
            nums[0] = temp;
            range -= 1;
            heapify(nums, 0, range);
        }
    }

    public static void quickSort(int[] nums) {
        /*
        Recursive sorting method that puts pivot element in correct
        position with all elements on left < pivot and all elements
        on right > pivot.

        It repeats on left and right sides and recurses until
        the entire array is sorted.
        */
        quickSort_R(nums, 0, nums.length - 1);
    }

    public static void quickSort_R(int[] nums, int left, int right) {
        if (left >= right) return;
        int pivot = partition(nums, left, right);
        quickSort_R(nums, left, pivot - 1);
        quickSort_R(nums, pivot + 1, right);
    }

    public static int partition(int[] nums, int left, int right) {
        /*
         Returns index of pivot element
         All elements lower than the pivot are moved to the left
         All elements greater than the pivot are moved to the right

         Pivot being the rightmost element is just for simplicity,
         but this leaves the door open to select a different pivot if
         I decide to change it later.
        */
        int pivot = right;
        int i = left;
        int j = right - 1;
        int temp;
        while (i < j) {
            if (nums[i] <= nums[pivot]) {
                i++;
            }
            else if (nums[j] > nums[pivot]) {
                j--;
            }
            else if (nums[i] > nums[j]) {
                temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        if (nums[i] > nums[pivot]) {
            temp = nums[i];
            nums[i] = nums[pivot];
            nums[pivot] = temp;
            pivot = i;
        }
        return pivot;
    }
}
