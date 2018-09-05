import java.util.concurrent.SynchronousQueue;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 18-3-7
 * Time: 上午11:29
 * 阻塞队列生产者消费者实例
 */
public class QueueTest {
    public static void main(String[] args) throws Exception {
        final SynchronousQueue<Integer> queue = new SynchronousQueue<>();
        Thread putThread = new Thread(()->{
            System.out.println("put thread start");
            try {
                queue.put(1);
            } catch (InterruptedException e) {
            }
            System.out.println("put thread end");
        });

        Thread takeThread  = new Thread(()->{
            System.out.println("take  thread start");
            try {
                System.out.println("take from putThread: " + queue.take());
            } catch (InterruptedException e) {
            }
            System.out.println("take  thread end");
        });

        putThread.start();
        Thread.sleep(1000);
        takeThread.start();



    }

}
