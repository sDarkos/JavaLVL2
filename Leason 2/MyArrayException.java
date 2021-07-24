public class MyArrayException extends RuntimeException{
    public MyArrayException(String message) {
        super(message);
    }

    public MyArrayException(String message, Throwable cause) {
        super(message, cause);
    }
}
