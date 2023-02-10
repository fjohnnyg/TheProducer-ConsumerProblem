import java.util.ArrayDeque;
import java.util.Deque;

public class Chef implements Runnable {

    private Deque<Plates> deque;
    private int platesAmount;
    private Restaurant restaurant;

    public Chef (Deque<Plates> deque, int platesAmount, Restaurant restaurant) {
        this.deque = deque;
        this.platesAmount = platesAmount;
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        Plates plate = cookSomething();
        restaurant.send(plate);
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
