import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Controller implements ActionListener {

    View view;

    Controller(View view) {this.view = view;}

    boolean isInteger(String s){
        try{
            Integer.parseInt(s);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    boolean validInput(){
        return isInteger(view.noServersTextField.getText()) &&
                isInteger(view.noTasksTextField.getText()) &&
                isInteger(view.minArrivalTimeTextField.getText()) &&
                isInteger(view.maxArrivalTimeTextField.getText()) &&
                isInteger(view.minProcessingTimeTextField.getText()) &&
                isInteger(view.maxProcessingTimeTextField.getText()) &&
                isInteger(view.maxTimeTextField.getText());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String button =e.getActionCommand();

        if(Objects.equals(button, "Run")){
            if(validInput()){
                int noServers = Integer.parseInt(view.noServersTextField.getText());
                int noTasks = Integer.parseInt(view.noTasksTextField.getText());
                int minArrivalTime = Integer.parseInt(view.minArrivalTimeTextField.getText());
                int maxArrivalTime = Integer.parseInt(view.maxArrivalTimeTextField.getText());
                int minProcessingTime = Integer.parseInt(view.minProcessingTimeTextField.getText());
                int maxProcessingTime = Integer.parseInt(view.maxProcessingTimeTextField.getText());
                int maxTime = Integer.parseInt(view.maxTimeTextField.getText());

                SimulationManager simulationManager = new SimulationManager(noServers, noTasks, minArrivalTime,
                        maxArrivalTime, minProcessingTime, maxProcessingTime, maxTime, view);

                view.eventLogTextArea.setText(simulationManager.getLogNow());
                Thread thread = new Thread(simulationManager);
                thread.start();
            }
            else{
                view.eventLogTextArea.setText("INPUT IS NOT VALID");
            }
        }
    }
}
