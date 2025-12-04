package com.pratice.p2;

import java.util.Random;
import java.util.Arrays;

//產生隨機號碼並放入陣列
public class randomNum {

    static Random rand = new Random();
    static private final int[] nums = new int[6];

    public static void randomNum() {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = rand.nextInt((49)) + 1;
        }
    }
//排列數字

    public static void arrangeNum() {
        System.out.print("排列前: ");

        for (int num : nums) {
            System.out.print(" " + num);
        }
        System.out.println();
        Arrays.sort(nums);
        System.out.print("排列後: ");

        for (int num : nums) {
            System.out.print(" " + num);
        }
    }
}
