package issues.deadlock;

import issues.racecondition.SemaforoBinario;

public class DeadlockDemo {

    public static void main(String[] args) throws InterruptedException {
        Object ob1 = new Object();
        Object ob2 = new Object();
        Object ob3 = new Object();

        SemaforoBinario semaphore = new SemaforoBinario();

        Thread t1 = new Thread(new SyncThread(ob1, ob2, semaphore), "hilo1");
        Thread t2 = new Thread(new SyncThread(ob2, ob3, semaphore), "hilo2");
        Thread t3 = new Thread(new SyncThread(ob3, ob1, semaphore), "hilo3");

        t1.start();
        Thread.sleep(5000);
        t2.start();
        Thread.sleep(5000);
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println("Finalizado");
    }

}

class SyncThread implements Runnable {

    private Object ob1;
    private Object ob2;
    private SemaforoBinario semaphore;

    public SyncThread(Object ob1, Object ob2, SemaforoBinario semaphore) {
        this.ob1 = ob1;
        this.ob2 = ob2;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        try {
            System.out.println(name + " tratando de adquirir semáforo");
            semaphore.acquire();
            System.out.println(name + " semáforo adquirido");

            System.out.println(name + " generando lock en " + ob1);
            synchronized (ob1) {
                System.out.println("lock generado en " + ob1);
                work();
                System.out.println(name + " generando lock en " + ob2);
                synchronized (ob2) {
                    System.out.println(name + " lock generado en " + ob2);
                    work();
                }
                System.out.println(name + " lock liberado en " + ob2);
            }
            System.out.println(name + " lock liberado en " + ob1);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
            System.out.println(name + " semáforo liberado");
        }
        System.out.println("Finalizo ejecución");
    }

    private void work(){
        try{
            Thread.sleep(2000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
