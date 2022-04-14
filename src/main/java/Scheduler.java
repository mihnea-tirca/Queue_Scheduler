import java.util.ArrayList;

public class Scheduler {
    ArrayList<Server> servers;
    ArrayList<Thread> threads;
    Strategy strategy;

    Scheduler(int numberOfServers){
        servers = new ArrayList<>();
        threads = new ArrayList<>();
        strategy = new ConcreteStrategyTime();
        for(int i = 0; i < numberOfServers; i++){
            Server server = new Server();
            servers.add(server);
            threads.add(new Thread(server));
        }
    }

    void changeStrategy(SelectionPolicy policy){
        if(policy == SelectionPolicy.SHORTEST_QUEUE){
            strategy = new ConcreteStrategyQueue();
        }
        if(policy == SelectionPolicy.SHORTEST_TIME){
            strategy = new ConcreteStrategyTime();
        }
    }

    void dispatchTask(Task t){
        strategy.addTask(servers, t);
    }

    void runThreads(){
        for(Thread thread : threads){
            thread.start();
        }
    }
}
