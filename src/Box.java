public class Box {
    private final String id;
    private final String category;
    private final long arrivalTime;
    private final int priority;

    public Box(String id, String category, long arrivalTime, int priority) {
        this.id = id;
        this.category = category;
        this.arrivalTime = arrivalTime;
        this.priority = priority;
    }

    public String getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public long getArrivalTime() {
        return arrivalTime;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return "Box[" + id + "]";
    }
}