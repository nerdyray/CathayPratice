import java.util.Random;
public class mathPratice {
    private int a;
    private int b;
    public void comparaMeth(){
        for(int i=0 ; i<3;i++){
            a=(int)(Math.random()*100);
            b=(int)(Math.random()*100);
            
            if(a>b){
                System.out.println("a "+a+" is greater than "+b+" b");
            }else if(b>a){
                System.out.println("b "+b+" is greater than "+a+" a");
            }else{
                System.out.println("a "+a+" is equal to "+b+" b");
            }
        }
    }
    
    public void randomNexint(){
        Random rand= new Random();
            for(int i=0;i<3;i++){
                a=rand.nextInt((100));
                b=rand.nextInt((100));
                if(a>b){
                System.out.println("a "+a+" is greater than "+b+" b");
            }else if(b>a){
                System.out.println("b "+b+" is greater than "+a+" a");
            }else{
                System.out.println("a "+a+" is equal to "+b+" b");
            }
        }
        System.err.println(" ");
    }
    public static void main(String[] args) {
        mathPratice mp = new mathPratice();
        mp.randomNexint();
        mp.comparaMeth();
    }

}
