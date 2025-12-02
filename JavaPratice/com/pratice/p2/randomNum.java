package com.pratice.p2;

import java.util.Random;
import java.util.Arrays;

//產生隨機號碼並放入陣列
public class randomNum {

    Random rand = new Random();
    private final int[] nums = new int[6];

    public randomNum() {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = rand.nextInt((49));
        }
    }
//排列數字

    public void arrangeNum() {
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
