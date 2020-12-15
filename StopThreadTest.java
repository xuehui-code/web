package lesson2;

public class StopThreadTest {

    private static  boolean STOP = false;

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                //执行任务，耗时可能比较长
                try {
                    for (int i=0;i<10000&&!STOP;i++){    //且！STOP即为STOP为true
                        System.out.println(i);
                        Thread.sleep(1000);
                        //通过标志位自行实现，无法解决线程阻塞导致无法中断
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        System.out.println("t start"); //先执行当前线程 输出 t start
        //让t线程中断
        Thread.sleep(5000);   //等5s去执行t线程，执行了5s  然后返回当前线程输出 t  stop
        STOP = true;
        System.out.println("t stop");
    }
}
