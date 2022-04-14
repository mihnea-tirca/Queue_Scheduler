import java.util.ArrayList;

public class ConcreteStrategyTime implements Strategy{

    @Override
    public void addTask(ArrayList<Server> servers, Task t) {
        int minWaitingPeriod = servers.get(0).waitingPeriod.intValue();
        Server minServer = servers.get(0);
        for(Server server : servers){
            if(minWaitingPeriod > server.waitingPeriod.intValue()){
                minServer = server;
                minWaitingPeriod = server.waitingPeriod.intValue();
            }
        }
        minServer.addTask(t);
    }
}
