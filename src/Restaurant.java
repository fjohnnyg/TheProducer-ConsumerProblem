import java.util.ArrayDeque;
import java.util.Deque;

public class Restaurant {

    private Deque<Plates> deque;
    boolean chefFlag = true;
    boolean clientFlag = false;
    final private int maxNumOfPlates;

    public Restaurant(int maxNumOfPlates) {
        this.deque = new ArrayDeque<>(maxNumOfPlates);
        this.maxNumOfPlates = maxNumOfPlates;
    }

    private void setChefFlag() {
        chefFlag = deque.size() != maxNumOfPlates;
    }

    private void setClientFlag() {
        clientFlag = !deque.isEmpty();
    }

    public synchronized void send(Plates plate) {
        while (!chefFlag) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        deque.addLast(plate);
        System.out.println(Thread.currentThread().getName() + " cooked " + plate);
        System.out.println(this);
        setChefFlag();
        notifyAll();
    }

    public synchronized Plates receive() {
        Plates plateReceived;
        while (!clientFlag) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            setClientFlag();
        }
        plateReceived = deque.removeFirst();
        System.out.println(Thread.currentThread().getName() + " ate " + plateReceived);
        System.out.println(this);
        setClientFlag();
        notifyAll();
        return plateReceived;
    }


    @Override
    public String toString() {
        return "Available plates: " +
                deque;
    }
}
