import java.util.concurrent.ThreadLocalRandom;

public class Client implements Runnable {

    private int platesAmount;
    private Restaurant restaurant;

    public Client(int platesAmount, Restaurant restaurant) {
        this.platesAmount = platesAmount;
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        while (platesAmount > 0) {
            restaurant.receive();
            platesAmount--;
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
