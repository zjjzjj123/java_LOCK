package AC;

import java.util.Arrays;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
            int size = Integer.valueOf(sc.nextLine());
            int[] arr = new int[size];
            for(int i=0; i<size; i++)
            {
                arr[i] = sc.nextInt();
        }
        int target = sc.nextInt();
        System.out.println(search(arr,target));  //或者换成return里面的额
    }

    public static int search(int[] nums, int target) {
        return searchL(nums, 0, nums.length - 1, target);
    }
    public static int searchL(int[] nums, int lo, int hi, int target) {
        if (lo > hi) return -1;
        int mid = lo + (hi - lo >> 1);
        if (nums[mid] == target) return mid;
        else if (nums[mid] > target) return searchL(nums, lo, mid - 1, target);
        else return searchL(nums, mid + 1, hi, target);
    }
}
