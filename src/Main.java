import javax.swing.plaf.TableHeaderUI;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) {
        Deque<Plates> deque = new ArrayDeque<>(10);

        Restaurant restaurant = new Restaurant(deque);

        Thread chef = new Thread(new Chef(deque, 3, restaurant));
        Thread chef1 = new Thread(new Chef(deque, 4, restaurant));
        Thread client = new Thread(new Client(deque, 2, restaurant));
        Thread client1 = new Thread(new Client(deque, 4, restaurant));

        chef.start();
        chef1.start();

        client.start();
        client1.start();
    }
}
