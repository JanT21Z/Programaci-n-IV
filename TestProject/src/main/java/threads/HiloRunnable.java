package threads;

public class HiloRunnable implements Runnable, Testinterface {
    @Override
    public void run() {
        System.out.println("Executing thread" + Thread.currentThread().getName() );
    }
}
