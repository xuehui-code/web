package lesson2;

public class InterruptTest2 {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i=0;i<10000&&!Thread.currentThread().isInterrupted();i++){
                        //!Thread.currentThread().isInterrupted()   没有被中断
                        System.out.println(i);
                        //模拟中断
                        Thread.sleep(1000);
                        //通过标志位自行实现，无法解决线程阻塞导致无法中断
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                /*for (int i = 0; i < 10000 && !Thread.currentThread().isInterrupted(); i++) {
                    //!Thread.currentThread().isInterrupted()   没有被中断
                    System.out.println(i);
                    //模拟中断
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }*/
                //Thread.interrupt();  返回当前线程中断标志位 重置中断标志位

            }
        });
        t.start();//线程启动 中断标志位为false
        System.out.println("t start");
        Thread.sleep(5000);
        t.interrupt();
        //如果t线程阻塞   并且重置t线程的中断标志位
        //告诉t线程要中断了(设置t线程的中断标志位为true) 由t自行决定是否中断
        System.out.println("t stop");
    }
}
