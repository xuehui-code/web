package lesson2;

public class YieldTest1 {
    public static void main(String[] args) {
        for (int i=0;i<20;i++){
            final int n = i;   //子线程
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {  //内部类使用外部变量 必须final修饰
                    try {
                        Thread.sleep(3000);
                        System.out.println(String.valueOf(n));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            t.start();
        }
        //main线程
        System.out.printf("OK");
    }
}
