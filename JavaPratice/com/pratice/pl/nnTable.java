
public class nnTable {
    public static void main(String[] args) {
        for (int i = 1; i <= 9; i++) {
            System.out.println(" ");//換行
            for (int j = 1; j <= 9; j++) {
                int sum = i * j;//把乘法結果放入sum
                System.out.print(j + "*" + i + "=" + (sum >= 10 ? "" : " ")+ sum + " ");//印出乘法表
                // System.out.println(" ");//換行
            }
        }
    }
}
