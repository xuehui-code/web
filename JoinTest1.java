package lesson2;

public class JoinTest1 {
    public static void main(String[] args) throws InterruptedException {
        //t和main同时并发并行的执行 但main正处于运行态执行代码，很快执行后续代码
        //打印main和t 应该是随机打印，但先打印main的几率更高
        //
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {  //内部类使用外部变量 必须final修饰
                System.out.println(String.valueOf("t"));
            }
        });//申请系统创建线程t
        t.start();//申请系统执行线程t  创建态转变为就绪态 由系统决定什么时候转变为运行态
        t.join();//当前线程main无条件等待，直到t线程执行完，当前线程再往后执行
        System.out.println("main");
    }
}
