package Algorithms;

public class Sorts {

    public static void insertionSort(int[] nums) {
        /*
         Sorted partition on left, unsorted partition on right. Swaps the furthest element until
         it is sorted. The first element is sorted by default.

         Runs in O(N^2) on average
         O(N) best case on sorted list implementation is more emergent here than
         in bubble sort.
        */
        for (int i = 1; i < nums.length; i++) {
            int j = i;
            while ((j > 0) && (nums[j] < nums[j-1])) {
                int temp = nums[j];
                nums[j] = nums[j - 1];
                nums[j - 1] = temp;
                j -= 1;
            }
        }
    }

    public static void bubbleSort(int[] nums) {
        /*
         Sorted partition on right, unsorted on left.
         Works by passing through array, and if the adjacent element is smaller, swap.
         By one pass biggest element will be on end.
         Repeats until list is sorted.

         Runs in O(N^2) on average.
         O(N) best case requires tracker to see if elements were swapped on a pass.
        */
        int end = nums.length - 1;
        int temp;
        while (end > 0) {
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
        while (end > 0) {
            hasSwapped = false;
            for (int i = 0; i < end; i++) {
                if (nums[i] > nums[i + 1]) {
                    temp = nums[i];
                    nums[i] = nums[i + 1];
                    nums[i + 1] = temp;
                    hasSwapped = true;
                }
            }
            if (!hasSwapped) {
                break;
            }
            end -= 1;
        }
    }

    public static void selectionSort(int[] nums) {
        /*
         Sorted partition on left, unsorted on right.
         Works by finding smallest element in array. Once found, it's swapped with the first
         element of the unsorted partition. The element is now sorted. This repeats until
         the whole array is sorted.

         This algorithm is always O(N^2) runtime.
        */
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
        /*
         Brings heap order to int current.
         Will only consider items in range
         put in nums.length to consider all of the array as a binary heap.
         The index argument is for where to heapify.

         This method is private because it's only meant to work in the very specific context
         of buildMaxHeap and heapSort. Will not be used for other functionality.
        */
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
         No extra space needed, however it's not stable due to heap structure.
         First a max heap is built.
         Second it loops: the biggest element is popped off the heap and swapped with
         the last element in the heap, and the biggest element is now excluded. The
         remaining numbers are heapified, and the process repeats until all numbers
         are sorted.

         Always runs in O(N*logN).
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

        Average run for quick sort is O(N * logN). For this one it's likely to reach
        O(N^2) worst case performance since the pivot is always the rightmost element.
        If a sorted list is passed as an argument it wouldn't divide evenly.
        */
        quickSort_R(nums, 0, nums.length - 1);
    }

    private static void quickSort_R(int[] nums, int left, int right) {
        if (left >= right) return;
        int pivot = partition(nums, left, right);
        quickSort_R(nums, left, pivot - 1);
        quickSort_R(nums, pivot + 1, right);
    }

    private static int partition(int[] nums, int left, int right) {
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

    public static void mergeSort(int[] nums) {
        /*
         Splits up array into sorted arrays of size 1, then merges them into a final
         array with the contents of the original, then overwrites the original.

         Always O(N * logN). Uses more memory than heap sort, but this is a stable algorithm.
         To my knowledge, this is the general algorithm recommended for big unsorted datasets.
        */
        if (nums.length <= 1) return;
        int[] sortedVals = mergeSort_R(nums, 0, nums.length - 1);
        System.arraycopy(sortedVals, 0, nums, 0, nums.length);
    }

    private static int[] mergeSort_R(int[] nums, int left, int right) {
        if (left == right) {
            return new int[] {nums[left]};
        }
        int mid = left + (right - left) / 2;
        int[] nums1 = mergeSort_R(nums, left, mid);
        int[] nums2 = mergeSort_R(nums, mid + 1, right);
        int[] combinedNums = merge(nums1, nums2);
        return combinedNums;
    }

    private static int[] merge(int[] nums1, int[] nums2) {
        // Makes a new list and returns sorted contents of nums1 and nums2.
        // Could be used independently, but this implementation is just for merge sort.
        int[] combinedNums = new int[nums1.length + nums2.length];
        int i = 0;
        int j = 0;
        int k = 0;
        while ((i < nums1.length) && (j < nums2.length)) {
            if (nums2[j] < nums1[i]) {
                combinedNums[k] = nums2[j];
                k++;
                j++;
            }
            else {
                combinedNums[k] = nums1[i];
                k++;
                i++;
            }
        }
        while (i < nums1.length) {
            combinedNums[k] = nums1[i];
            k++;
            i++;
        }
        while (j < nums2.length) {
            combinedNums[k] = nums2[j];
            k++;
            j++;
        }
        return combinedNums;
    }
}
