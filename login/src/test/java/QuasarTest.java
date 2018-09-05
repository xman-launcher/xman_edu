import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.strands.Strand;
import co.paralleluniverse.strands.channels.Channel;
import co.paralleluniverse.strands.channels.Channels;
import co.paralleluniverse.strands.concurrent.CountDownLatch;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 18-3-7
 * Time: 上午11:29
 * 协程生产者消费者实例
 */
public class QuasarTest {
    private static void printer(Channel in) throws InterruptedException, SuspendExecution {
        Integer v;
        while ((v = (Integer)in.receive()) != null) {
            System.out.println(v);
        }


    }


    public static void main(String[] args) throws ExecutionException, InterruptedException,
            SuspendExecution {


//        //定义两个Channel
//
//
//        Channel naturals = Channels.newChannel(-1);
//
//
//        Channel squares = Channels.newChannel(-1);
//
//
//        //运行两个Fiber实现.
//
//
//        new Fiber(() ->{
//            for (int i =0;i<10;i++)
//                naturals.send(i);
//                naturals.close();
//
//        }).start();
//        new Fiber(()-> {
//            Integer v;
//            while((v=(Integer)naturals.receive())!=null)
//                squares.send(v);
//                squares.close();
//
//        }).start();
//
//
//        printer(squares);

        // 协程测试本机一个协程占用8KB（感觉很多），开100万会崩
//        int FiberNumber = 1_000_0;
//        CountDownLatch latch = new CountDownLatch(1);
//        AtomicInteger counter = new AtomicInteger(0);
//
//        for (int i = 0; i < FiberNumber; i++) {
//            new Fiber(() -> {
//            counter.incrementAndGet();
//            if (counter.get() == FiberNumber) {
//                System.out.println("done");
//            }
//            Strand.sleep(1000000);
//            }).start();
//        }
//        latch.await();


    }


}


