import java.util.concurrent.ThreadLocalRandom;

public class Chef implements Runnable {

    private int platesAmount;
    private Restaurant restaurant;

    public Chef(int platesAmount, Restaurant restaurant) {
        this.platesAmount = platesAmount;
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        while (platesAmount > 0) {
            Plates plate = cookSomething();
            restaurant.send(plate);
            platesAmount--;
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Plates cookSomething() {
        int randNum = (int) (Math.random() * 6 + 1);
        Plates plate = null;
        switch (randNum) {
            case 1:
                plate = Plates.RICE;
                break;
            case 2:
                plate = Plates.PASTA;
                break;
            case 3:
                plate = Plates.BEEF;
                break;
            case 4:
                plate = Plates.FISH;
                break;
            case 5:
                plate = Plates.SOUP;
                break;
            case 6:
                plate = Plates.DESERT;
                break;
        }
        return plate;
    }
}
