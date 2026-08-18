public class BoxNotFoundException extends RuntimeException {
    public BoxNotFoundException(String target) {
        super("ไม่พบกล่อง: " + target);
    }
}