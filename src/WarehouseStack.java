import java.util.ArrayDeque;
import java.util.Deque;

public class WarehouseStack {
    private final Deque<Box> boxes = new ArrayDeque<>();
    private final int capacity;

    public WarehouseStack(int capacity) {
        this.capacity = capacity;
    }

    public void push(Box box) {
        if (!hasSpace()) {
            throw new StackFullException("Stack เต็ม");
        }
        boxes.push(box);
    }

    public Box pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack ว่าง");
        }
        return boxes.pop();
    }

    public Box peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack ว่าง");
        }
        return boxes.peek();
    }

    public boolean isEmpty() {
        return boxes.isEmpty();
    }

    public boolean hasSpace() {
        return boxes.size() < capacity;
    }

    public int remainingSpace() {
        return capacity - boxes.size();
    }

    public Deque<Box> getBoxes() {
        return boxes;
    }
}