package lesson6;

public class SingnalTest {

    public static void main(String[] args) throws InterruptedException {
        //竞争class对象锁
        synchronized (SingnalTest.class) {
            //当前线程释放对象锁
//            SingnalTest.class.wait();
            //通知调用同一个对象的wait()阻塞的线程（唤醒），唤醒后竞争对象锁
            SingnalTest.class.notify();
        }//释放对象锁
    }
}
