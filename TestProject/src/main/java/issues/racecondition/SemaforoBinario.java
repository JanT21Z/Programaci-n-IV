package issues.racecondition;

public class SemaforoBinario {
    private boolean locked = false;

    public synchronized void acquire() throws InterruptedException {
        while (locked) {
            wait();
        }
        locked = true;
    }

    public synchronized void release() {
        locked = false;
        notify();
    }
}
