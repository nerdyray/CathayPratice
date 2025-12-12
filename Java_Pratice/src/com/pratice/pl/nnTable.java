package com.pratice.pl;



public class nnTable {
    public static void main(String[] args) {
        for (int i = 1; i <= 9; i++) {
            System.out.println(" ");//換行
            for (int j = 2; j <= 9; j++) {
                int product = i * j;//把乘法結果放入sum
                System.out.print(j + "*" + i + "=" + (product >= 10 ? "" : " ")+ product + " ");//印出乘法表
                // System.out.println(" ");//換行
            }
        }
    }
}
