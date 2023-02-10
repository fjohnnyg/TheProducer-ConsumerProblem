import java.util.Deque;

public class Client implements Runnable {

    private Deque<Plates> deque;
    private int platesAmount;
    private Restaurant restaurant;
    public Client (Deque<Plates> deque, int platesAmount, Restaurant restaurant) {
        this.deque = deque;
        this.platesAmount = platesAmount;
        this.restaurant = restaurant;
    }
    @Override
    public void run() {
        restaurant.receive();
    }
}
