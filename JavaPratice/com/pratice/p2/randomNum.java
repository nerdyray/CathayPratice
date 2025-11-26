package com.pratice.p2;
import java.util.Random;
import java.util.Arrays;

//產生隨機號碼並放入陣列
public class randomNum {
    Random rand=new Random();
    private int [] nums=new int[6];

    public randomNum(){
        for(int i =0;i<nums.length;i++){
            nums[i]=rand.nextInt((49));
        }
    }

//排列數字
    public void arrangeNum(){
        Arrays.sort(nums);
    //     for(int i=0;i<nums.length;i++) {            
    //             if(nums[i]>nums[i]){
    //                 int temp = nums [i];
    //                 nums[i] = nums [i+1];
    //                 nums [i] = temp;
    //             }
    //     }
        for(int num:nums){
        System.out.println(num);
    }
}}