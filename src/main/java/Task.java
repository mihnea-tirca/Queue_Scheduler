public class Task implements Comparable<Task> {
    int id;
    int arrivalTime;
    int processingTime;

    Task() {
        id = arrivalTime = processingTime = 0;
    }

    @Override
    public int compareTo(Task o) {
        return arrivalTime - o.arrivalTime;
    }

    @Override
    public String toString() {
        return "(" + id + ", " + arrivalTime + ", " + processingTime + ")";
    }
}
