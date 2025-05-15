public class Timer implements Runnable {

    private final long startTime;
    public Timer(long startTime) {
        this.startTime = startTime;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            long now = System.currentTimeMillis();
            double elapsed = (now - startTime) / 1000.0;

            System.out.printf("\r⏱️ Czas trwania: %.3f sekundy ⏱️", elapsed);

            try {
                Thread.sleep(100); }
            catch (InterruptedException e) {
                break;
            }
        }
    }
}