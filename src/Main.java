
public class Main {
    public static void main(String[] args) {

        Restaurant restaurant = new Restaurant(10);

        Thread chef = new Thread(new Chef(5, restaurant, "Ramsey"));
        chef.setName("Ramsey");
        Thread chef1 = new Thread(new Chef(9, restaurant, "Jamie"));
        chef1.setName("Jamie");
        Thread client = new Thread(new Client(5, restaurant, "John"));
        client.setName("John");
        Thread client1 = new Thread(new Client(8, restaurant, "Doe"));
        client1.setName("Doe");

        chef.start();
        chef1.start();

        client.start();
        client1.start();


    }
}
