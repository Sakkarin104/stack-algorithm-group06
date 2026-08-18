public class MovedBox {
    private final Box box;
    private final WarehouseStack stack;

    public MovedBox(Box box, WarehouseStack stack) {
        this.box = box;
        this.stack = stack;
    }

    public Box box() {
        return box;
    }

    public WarehouseStack stack() {
        return stack;
    }
}