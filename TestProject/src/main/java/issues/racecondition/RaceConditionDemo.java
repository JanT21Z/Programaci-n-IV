package issues.racecondition;

public class RaceConditionDemo {
    public static void main(String[] args) throws InterruptedException {
        Contador contador = new Contador();

        Thread t1 = new Thread(new HiloContador(contador), "hilo1");
        Thread t2 = new Thread(new HiloContador(contador), "hilo2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Valor final del contador: " + contador.getContador()); // Debería ser 2000
    }
}
