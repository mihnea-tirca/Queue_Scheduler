import java.util.ArrayList;
import java.util.List;

public interface Strategy {
    void addTask(ArrayList<Server> servers, Task t);
}
