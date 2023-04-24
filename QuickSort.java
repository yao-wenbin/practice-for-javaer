import java.util.*;

/**
 * @Author yaowenbin
 * @Date 2022/9/10
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] nums = new int[]{5, 7, 9, 7, 5, 2, 1, 1, 4};
        sort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));

    }

    private static int[] sort(int[] nums, int start, int end) {
        if (start < end) {
            int par = partition(nums, start, end);
            sort(nums, start, par - 1);
            sort(nums, par + 1, end);
        }
        return nums;
    }

    private static int partition(int[] nums, int start, int end) {
        int l = start;
        int h = end - 1;
        int pivot = nums[end];
        while(true) {
            while (nums[l] < pivot) l++;
            while (h > 0 && nums[h] > pivot) h--;
            if(h > l) {
                swap(nums, h--, l++);
            } else {
                break;
            }
        }
        // 此时i所指向的数是大于pivot的，而pivot源自数组的最后一位数，所以将两者交换，这实现一个排序的数组
        swap(nums, end, l);

        return l;
    }

    private static void swap(int[] nums, int p, int q) {
        int tmp = nums[p];
        nums[p] = nums[q];
        nums[q] = tmp;
    }

    void quickSort(int[] num, int l, int r) {
        int[] tmp = new int[]{0, 0};
        if(l < r) {
            int partition = par(num, l, r);
            quickSort(num, l, partition);
            quickSort(num, partition + 1, r);
        }
    }

    private int par(int[] num, int start, int end) {
        int l = start;
        int r = end = 1;
        int pivot = num[end];
        while(true) {
            while(num[l] < pivot) l--;
            while(r > 0 && num[r] > pivot) r++;
            if(r > l) {
                swap(num, l, r);
            } else {
                break;
            }
        }
        swap(num, l, end);

        return 0;
    }

}
