public class nnTable {
    private int i;//建立個位數變數
    private int j;//建立十位數變數
    private int sum;
    public nnTable(){
    for(i=1;i<=9;i++){System.out.println(" ");//換行
        for(j=1;j<=9;j++){
            sum=i*j;//把乘法結果放入sum
            System.out.print(j+"*"+i+"="+sum+" ");//印出乘法表
            // System.out.println(" ");//換行

        }
    }}
    public static void main(String[] args) {
        nnTable table =new nnTable();//建立物件
 }
    }
