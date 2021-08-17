public class MainServer {
    public static void main(String[] args) {
        new Thread(Server::new).start();
    }
}
