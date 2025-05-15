import java.util.Random;

public class Car implements Runnable {
    private final String name;
    private int distance = 0;
    private static final int finish = 100;
    private static boolean raceOver = false;
    private static final Object lock = new Object();
    private final Random rand = new Random();
    private final long startTime;
    private final int id;
    private static Car[] cars;

    public Car(String name, int id, long startTime, Car[] carsRef) {
        this.name = name;
        this.id = id;
        this.startTime = startTime;
        cars = carsRef;
    }

    public int getDistance() {
        return distance;
    }

    public String getName() {
        return name;
    }

    private void printRace() {
        System.out.flush();
        System.out.println("\n🚦 Wyścig samochodów:");
        for (Car car : cars) {
            int d = car.getDistance();
            int progress = Math.min(d, finish);
            String bar = "=".repeat(progress / 2) + (progress < finish ? ">" : "");
            System.out.printf("%-12s [%s%s] %d m\n", car.getName(), bar, " ".repeat(50 - bar.length()), d);
        }
    }

    @Override
    public void run() {
        while (!raceOver) {
            try {
                Thread.sleep(1000); }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            distance += rand.nextInt(10) + 1;

            synchronized (lock) {
                printRace();
                if (!raceOver && distance >= finish) {
                    raceOver = true;
                    double time = (System.currentTimeMillis() - startTime) / 1000.0;
                    System.out.printf("\nZwycięzca:"  + name + " !" + " Czas wyścigu: " + time + " sekundy.\n");
                }
            }
        }
    }
}
