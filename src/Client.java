import java.util.concurrent.ThreadLocalRandom;

public class Client implements Runnable {

    private int platesAmount;
    private Restaurant restaurant;
    private String name;
    public Client (int platesAmount, Restaurant restaurant, String name) {
        this.platesAmount = platesAmount;
        this.restaurant = restaurant;
        this.name = name;
    }
    @Override
    public void run() {
        while (platesAmount > 0) {
            if (restaurant.getMaxNumOfPlates() - restaurant.getDeque().size() < 1) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            restaurant.receive();
//            System.out.println(getName() + " ate a " + plate);
            platesAmount--;
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String getName() {
        return name;
    }

    public int getClientPlatesAmount() {
        return platesAmount;
    }
}
