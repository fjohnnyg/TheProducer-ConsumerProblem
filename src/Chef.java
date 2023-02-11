import java.util.concurrent.ThreadLocalRandom;

public class Chef implements Runnable {

    private int platesAmount;
    private Restaurant restaurant;
    private String name;

    public Chef (int platesAmount, Restaurant restaurant, String name) {
        this.platesAmount = platesAmount;
        this.restaurant = restaurant;
        this.name = name;
    }

    @Override
    public void run() {
        while (platesAmount > 0) {
            if (restaurant.getMaxNumOfPlates() == restaurant.getDeque().size()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            Plates plate = cookSomething();
            restaurant.send(plate);
//            System.out.println(getName() + " cooked a " + plate);
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

    public String getName() {
        return name;
    }

    public int getChefPlatesAmount() {
        return platesAmount;
    }
}
