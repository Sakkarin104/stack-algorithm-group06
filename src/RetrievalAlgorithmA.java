import java.util.ArrayList;
import java.util.List;

public class RetrievalAlgorithmA {

    public boolean retrieve(Warehouse wh, String target) {
        WarehouseStack source = wh.findStackContaining(target);

        if (source == null) {
            throw new BoxNotFoundException(target);
        }

        List<MovedBox> moved = new ArrayList<>();

        while (!source.peek().getId().equals(target)) {
            Box box = source.pop();
            WarehouseStack dest = findFirstAvailable(wh, source);

            if (dest == null) {
                source.push(box);
                rollback(moved, source);
                return false;
            }

            dest.push(box);
            moved.add(new MovedBox(box, dest));
        }

        source.pop();
        restore(moved, source);

        return true;
    }

    private WarehouseStack findFirstAvailable(
            Warehouse wh, WarehouseStack source) {

        for (WarehouseStack stack : wh.getStacks()) {
            if (stack != source && stack.hasSpace()) {
                return stack;
            }
        }

        return null;
    }

    private void restore(List<MovedBox> moved,
            WarehouseStack source) {

        for (int i = moved.size() - 1; i >= 0; i--) {
            MovedBox m = moved.get(i);
            m.stack().pop();
            source.push(m.box());
        }
    }

    private void rollback(List<MovedBox> moved,
            WarehouseStack source) {
        restore(moved, source);
    }
}