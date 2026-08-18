import java.util.ArrayList;
import java.util.List;

public class Warehouse {
    private final List<WarehouseStack> stacks = new ArrayList<>();

    public void addStack(WarehouseStack stack) {
        stacks.add(stack);
    }

    public List<WarehouseStack> getStacks() {
        return stacks;
    }

    public WarehouseStack findStackContaining(String id) {
        for (WarehouseStack stack : stacks) {
            for (Box box : stack.getBoxes()) {
                if (box.getId().equals(id)) {
                    return stack;
                }
            }
        }
        return null;
    }

    public void validate() {
        if (stacks.isEmpty()) {
            throw new IllegalStateException("ไม่มี Stack");
        }
    }
}