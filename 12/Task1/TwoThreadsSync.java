public class TwoThreadsSync {
    private static final Object lock = new Object();
    private static boolean firstThreadTurn = true;
    
    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        synchronized (lock) {
                            while (!firstThreadTurn) {
                                lock.wait();
                            }
                            System.out.println("Поток1");
                            firstThreadTurn = false;
                            lock.notifyAll();
                            Thread.sleep(500);
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        synchronized (lock) {
                            while (firstThreadTurn) {
                                lock.wait();
                            }
                            System.out.println("Поток2");
                            firstThreadTurn = true;
                            lock.notifyAll();
                            Thread.sleep(500);
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        
        thread1.start();
        thread2.start();
    }
}