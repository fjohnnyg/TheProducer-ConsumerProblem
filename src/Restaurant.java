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


/*    private int checkNumOfPlates() {
        int numOfPlates = 0;
        for (int i = 0; i < deque.size(); i++) {
            if (deque.element() != null)
                numOfPlates++;
        }
        return numOfPlates;
    }*/

    private void setChefFlag() {
        chefFlag = deque.size() != maxNumOfPlates;
    }
    private  void  setClientFlag() {
        clientFlag = !deque.isEmpty();
    }
    public synchronized void send (Plates plate) {
        while (!chefFlag) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        deque.addLast(plate);
        System.out.println(Thread.currentThread().getName() + " cooked " + plate);
        System.out.println(this.deque);
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
        System.out.println(this.deque);
        setClientFlag();
        notifyAll();
        return plateReceived;
    }

    public Deque<Plates> getDeque() {
        return deque;
    }

    public int getMaxNumOfPlates() {
        return maxNumOfPlates;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "deque=" + deque +
                '}';
    }
}
