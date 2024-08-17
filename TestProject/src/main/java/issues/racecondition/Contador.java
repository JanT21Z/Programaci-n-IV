package issues.racecondition;

import issues.racecondition.SemaforoBinario;

public class Contador {
    private int contador = 0;
    private final SemaforoBinario semaphore = new SemaforoBinario();

    public void incrementarContador() {
        try {
            semaphore.acquire();
            contador++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    public int getContador() {
        return contador;
    }
}
