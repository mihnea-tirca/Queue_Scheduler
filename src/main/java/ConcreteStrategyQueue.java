import java.util.ArrayList;

public class ConcreteStrategyQueue implements Strategy{

    @Override
    public void addTask(ArrayList<Server> servers, Task t) {
        int minQueueSize = servers.get(0).tasks.size();
        Server minServer = servers.get(0);
        for(Server server : servers){
            if(minQueueSize > server.tasks.size()){
                minServer = server;
                minQueueSize = server.tasks.size();
            }
        }
        minServer.addTask(t);
    }
}
