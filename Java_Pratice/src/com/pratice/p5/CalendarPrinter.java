package com.pratice.p5;

import java.util.Calendar;
import java.util.Scanner;

public class CalendarPrinter {

    
    public static  void printMonth(int month) {
        Calendar calendar = Calendar.getInstance();
        int year = 2025;
        // int month = scanner.nextInt(); // 11月 (0-based index)
        // 設定為該月第一天
        calendar.set(year, month, 1);
        int firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK); // 1是週日, 7是週六
        // TODO 加入空字串Padding依據第一天的數字決定前面要空幾格
        // 取得該月天數
        //get方法找到正確的月份
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        
        // 輸出標頭 "日 一 二 三 四 五 六"
        System.out.println("   日  一  二  三  四  五  六");
        
        // 輸出第一行的空白格
        // TODO 用計算代替迭代
        for (int i = 1; i < firstDayOfWeek; i++) {
            System.out.print("    ");
        }
        
        // 迴圈印出日期
        for (int day = 1; day <= daysInMonth; day++) {
            System.out.printf("%4d", day);
            
            // 如果是週日就換行
            if ((day + firstDayOfWeek - 1) % 7 == 0) {
                System.out.println();
            }
        }
        System.out.println(); // 結尾換行
    }
    
    public static void chooseMonth() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("請輸入要查詢的月份 (1~12)：");
        int month = scanner.nextInt();
        scanner.close();

        if (month < 1 || month > 12) {
            System.out.println("請輸入正確的 1~12 月");
            return;
        }

        printMonth(month-1); //把月份傳進 printMonth
        //要記得減一
    }

    public static void main(String[] args) {
        
        CalendarPrinter.chooseMonth();
    }
}
