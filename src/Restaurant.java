import java.util.Deque;

public class Restaurant {

    private Deque<Plates> deque;
    boolean flag = true;
    private Plates plate;

    public Restaurant(Deque<Plates> deque) {
        this.deque = deque;
    }


    private int checkNumOfPlates() {
        int numOfPlates = 0;
        for (int i = 0; i < deque.size(); i++) {
            if (deque.element() != null)
                numOfPlates++;
        }
        return numOfPlates;
    }

    private void setFlag () {
        if (deque.size() == checkNumOfPlates())
            flag = true;
        if (checkNumOfPlates() == 0)
            flag = false;
    }
    public synchronized void send (Plates plate) {
        while (flag == false) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        deque.add(plate);
        setFlag();
        notifyAll();
    }

    public synchronized void receive() {
        while (flag == true) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            deque.pollLast();
            setFlag();
            notifyAll();
        }
    }
}
