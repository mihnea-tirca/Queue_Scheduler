import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class SimulationManager implements Runnable{
    // data read from GUI
    int maxTime;
    int minArrivalTime;
    int maxArrivalTime;
    int minProcessingTime;
    int maxProcessingTime;
    int numberOfServers;
    int numberOfTasks;
    SelectionPolicy selectionPolicy;
    double averageWaitingTime;
    double averageProcessingTime;
    int peakHour;
    String fullLog;

    View view;
    // entity responsible with queue management and client distribution
    Scheduler scheduler;
    // pool of tasks
    ArrayList<Task> generatedTasks;

    SimulationManager(int numberOfServers, int numberOfTasks, int minArrivalTime, int maxArrivalTime,
                             int minProcessingTime, int maxProcessingTime, int maxTime, View view){
        this.numberOfServers = numberOfServers;
        this.numberOfTasks = numberOfTasks;
        this.minArrivalTime = minArrivalTime;
        this.maxArrivalTime = maxArrivalTime;
        this.minProcessingTime = minProcessingTime;
        this.maxProcessingTime = maxProcessingTime;
        this.maxTime = maxTime;
        this.view = view;

        this.selectionPolicy = SelectionPolicy.SHORTEST_TIME;
        this.averageWaitingTime = 0;
        this.averageProcessingTime = 0;
        this.peakHour = 0;
        this.fullLog = "";
        this.generatedTasks = null;
        this.scheduler = new Scheduler(numberOfServers);
        generateNRandomTasks();
    }

    void generateNRandomTasks(){
        generatedTasks = new ArrayList<>();
        int totalWaitingTime = 0;
        int totalProcessingTime = 0;
        for(int i = 0; i < numberOfTasks; i++){
            Task t = new Task();
            t.id = i + 1;
            t.arrivalTime = minArrivalTime + (int) (Math.random() * (maxArrivalTime - minArrivalTime));
            t.processingTime = minProcessingTime + (int) (Math.random() * (maxProcessingTime - minProcessingTime));
            generatedTasks.add(t);
            totalWaitingTime += t.arrivalTime;
            totalProcessingTime += t.processingTime;
        }
        Collections.sort(generatedTasks);
        averageWaitingTime = (double) totalWaitingTime / numberOfTasks;
        averageProcessingTime = (double) totalProcessingTime / numberOfTasks;
    }

    boolean allQueuesFinished(){
        if(!generatedTasks.isEmpty())
            return false;
        for(Server s : scheduler.servers){
            if(s.tasks.size() != 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void run() {
        int timeNow = 0, totalWaitingPeriodNow, maxWaitingPeriod = 0;
        scheduler.runThreads();
        while(timeNow < maxTime && !allQueuesFinished()){
            timeNow++;
            String timeInfo = "\n Time: " + timeNow + "\n";
            ArrayList<Task> newGeneratedTasks = new ArrayList<>();
            for(Task t : generatedTasks){
                if(t.arrivalTime == timeNow){
                    t.processingTime++; // cand il pun in server, la timpul t.arrivalTime nu vreau sa se decrementeze processingTime
                    scheduler.dispatchTask(t);
                }
                else{
                    newGeneratedTasks.add(t);
                }
                generatedTasks = newGeneratedTasks;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            totalWaitingPeriodNow = 0;
            for(Server s : scheduler.servers){
                totalWaitingPeriodNow += s.waitingPeriod.get();
            }

            if(maxWaitingPeriod < totalWaitingPeriodNow){
                maxWaitingPeriod = totalWaitingPeriodNow;
                peakHour = timeNow;
            }

            timeInfo += getLogNow();
            fullLog += timeInfo;

            view.eventLogTextArea.setText(fullLog);
        }
        fullLog += "Average Waiting Time: " + averageWaitingTime + "\n";
        fullLog += "Average Processing Time: " + averageProcessingTime + "\n";
        fullLog += "Peak Hour: " + peakHour + "\n";
        view.eventLogTextArea.setText(fullLog);

        writeToTextFile(fullLog);
    }

    String getLogNow(){
        String log = "";
        log += "Waiting Clients:\n";
        for(Task t : generatedTasks) {
            log += t.toString() + " ";
        }
        log += "\n";
        int queueNo = 1;
        for(Server s : scheduler.servers){
            log += "Queue " + queueNo + ": ";
            if(s.toString().isEmpty()){
                log += "closed";
            }
            else{
                log += s.toString();
            }
            queueNo++;
            log += "\n";
        }
        log += "\n";
        return log;
    }

    void writeToTextFile(String fullLog){
        new File("Simulation Log.txt");
        try {
            FileWriter fileWriter = new FileWriter("Simulation Log.txt");
            fileWriter.write(fullLog);
            fileWriter.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
