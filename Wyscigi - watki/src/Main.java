import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int count = getCount();

        Thread[] threads = new Thread[count];
        Car[] cars = new Car[count];
        long start = System.currentTimeMillis();

        Thread timer = new Thread(new Timer(start));
        timer.start();

        for (int i = 0; i < count; i++) {
            cars[i] = new Car("Samochód " + (i + 1), i, start, cars);
        }

        for (int i = 0; i < count; i++) {
            threads[i] = new Thread(cars[i]);
            threads[i].start();
        }

        for (Thread t : threads) {
            try {
                t.join(); }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        timer.interrupt();
        System.out.println("\nWyścig zakończony!");
    }

    private static int getCount() {
        Scanner scanner = new Scanner(System.in);
        int count = 0;

        while (count <= 0) {
            System.out.print("Podaj liczbę samochodów: ");
            if (scanner.hasNextInt()) {
                count = scanner.nextInt();
                if (count <= 0) {
                    System.out.println("Liczba musi być większa od 0.");
                }
            } else {
                System.out.println("To nie jest liczba całkowita.");
                scanner.next();
            }
        }
        return count;
    }
}



