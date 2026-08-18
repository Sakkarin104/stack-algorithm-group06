import java.util.ArrayList;
import java.util.List;

public class RetrievalAlgorithmB {

    public boolean retrieve(Warehouse wh, String target) {
        WarehouseStack source = wh.findStackContaining(target);

        if (source == null) {
            throw new BoxNotFoundException(target);
        }

        List<MovedBox> moved = new ArrayList<>();

        boolean found = retrieveRecursive(source, wh, target, moved);

        if (found) {
            restore(moved, source);
        } else {
            rollback(moved, source);
        }

        return found;
    }

    private boolean retrieveRecursive(
            WarehouseStack source,
            Warehouse wh,
            String target,
            List<MovedBox> moved) {

        if (source.isEmpty()) {
            return false;
        }

        if (source.peek().getId().equals(target)) {
            source.pop();
            return true;
        }

        Box box = source.pop();
        WarehouseStack dest = findBestFit(wh, source);

        if (dest == null) {
            source.push(box);
            return false;
        }

        dest.push(box);
        moved.add(new MovedBox(box, dest));

        return retrieveRecursive(source, wh, target, moved);
    }

    private WarehouseStack findBestFit(
            Warehouse wh, WarehouseStack source) {

        WarehouseStack best = null;
        int minRemaining = Integer.MAX_VALUE;

        for (WarehouseStack stack : wh.getStacks()) {
            if (stack == source || !stack.hasSpace()) {
                continue;
            }

            int remaining = stack.remainingSpace() - 1;

            if (remaining < minRemaining) {
                minRemaining = remaining;
                best = stack;
            }
        }

        return best;
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