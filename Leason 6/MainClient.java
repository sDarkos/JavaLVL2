public class MainClient {
    public static void main(String[] args) {
        new Thread(Client::new).start();
    }
}
