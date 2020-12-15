package lesson2;

public class InterruptTest {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                //Thread.interrupt();  返回当前线程中断标志位 重置中断标志位
                for(int i=0;i<10;i++){
                    System.out.println(i+":"+Thread.interrupted());
                }
            }
        });
        t.start();//线程启动 中断标志位为false
        System.out.println("t start");
        t.interrupt();
        //如果t线程阻塞   并且重置t线程的中断标志位
        //告诉t线程要中断了(设置t线程的中断标志位为true) 由t自行决定是否中断
        System.out.println("t stop");
    }
}
