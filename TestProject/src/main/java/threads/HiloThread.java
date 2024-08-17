package threads;

public class HiloThread extends  Thread{

    @Override
    public void run() {
        System.out.println("Executing thread" + currentThread().getName() );
    }
}
