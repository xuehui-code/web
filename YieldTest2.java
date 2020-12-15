package lesson2;

public class YieldTest2 {
    public static void main(String[] args) throws InterruptedException {
        for (int i=0;i<20;i++){
            final int n = i;
            //new Thread 操作耗时！
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {  //内部类使用外部变量 必须final修饰
                    System.out.println(String.valueOf(n));
                }
            });
            t.start();
            t.join();
        }

        //使用run()方法activeCount()>2，使用debug()方法使用activeCount()>1  可直接用join()方法代替
        /*while (Thread.activeCount()>1){
            Thread.yield();//让当前线程让步 从运行态转变为就绪态
        }*/

        System.out.println("OK");  //无睡眠 所有结果几乎同时执行完成
    }

}
