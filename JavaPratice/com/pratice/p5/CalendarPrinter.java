package com.pratice.p5;
import java.util.Calendar;
import java.util.Scanner;

public class CalendarPrinter {
    Scanner scanner = new Scanner(System.in);

    public void printMonth(int month){
        Calendar calendar = Calendar.getInstance();
        int year = 2025;
        // int month = scanner.nextInt(); // 11月 (0-based index)
        // 設定為該月第一天
        calendar.set(year, month, 1);
        int firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK); // 1是週日, 7是週六

        // 取得該月天數
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        // 輸出標頭 "日 一 二 三 四 五 六"
        System.out.println("   日  一  二  三  四  五  六");

        // 輸出第一行的空白格
        for (int i = 1; i < firstDayOfWeek; i++) {
            System.out.printf("    ");
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
        public void chooseMonth(){
        System.out.print("請輸入要查詢的月份 (1~12)：");
        int month = scanner.nextInt();

        if (month < 1 || month > 12) {
            System.out.println("請輸入正確的 1~12 月");
            return;
        }

        printMonth(month); // ⭐ 把月份傳進 printMonth
    }
    public static void main(String[] args) {
        CalendarPrinter cp =new CalendarPrinter();
        cp.chooseMonth();
    }
}
