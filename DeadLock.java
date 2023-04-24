import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author yaowenbin
 * @Date 2022/9/7
 */
public class DeadLock {
    public static void main(String[] args) throws Exception{
        Lock lock1 = new ReentrantLock();
        Lock lock2 = new ReentrantLock();
        Thread thread1 = new Thread(() -> {
            lock1.lock();
            try{
                Thread.sleep(1000);
                lock2.lock();
                try{
                    System.out.println("get Lock2 successfully!");
                }finally {
                    lock2.unlock();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally{
                lock1.unlock();
            }
        });

        Thread thread2 = new Thread(() -> {
            lock2.lock();
            try{
                Thread.sleep(1000);
                lock1.lock();
                try{
                    System.out.println("get Lock2 successfully!");
                }finally {
                    lock1.unlock();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally{
                lock2.unlock();
            }
        });
        thread1.start();
        thread2.start();

    }
}
