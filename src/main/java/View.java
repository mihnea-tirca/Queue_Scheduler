import javax.swing.*;
import java.awt.*;

public class View extends JFrame{
    JPanel contentPanel;


    JPanel inputPanel;

    JPanel noServersPanel;
    JLabel noServersLabel;
    JTextField noServersTextField;

    JPanel noTasksPanel;
    JLabel noTasksLabel;
    JTextField noTasksTextField;

    JPanel minArrivalTimePanel;
    JLabel minArrivalTimeLabel;
    JTextField minArrivalTimeTextField;

    JPanel maxArrivalTimePanel;
    JLabel maxArrivalTimeLabel;
    JTextField maxArrivalTimeTextField;

    JPanel minProcessingTimePanel;
    JLabel minProcessingTimeLabel;
    JTextField minProcessingTimeTextField;

    JPanel maxProcessingTimePanel;
    JLabel maxProcessingTimeLabel;
    JTextField maxProcessingTimeTextField;

    JPanel maxTimePanel;
    JLabel maxTimeLabel;
    JTextField maxTimeTextField;

    JButton runButton;

    JPanel outputPanel;

    JPanel eventLogPanel;
    JLabel eventLogLabel;
    JTextArea eventLogTextArea;
    JScrollPane eventLogScrollPane;

    Controller controller = new Controller(this);

    View(String name){
        super(name);
        initGui();
    }

    void initGui(){
        setSize(1000, 600);
        this.contentPanel = new JPanel(new GridLayout(1, 2));
        this.setContentPane(contentPanel);
        initInputPanel();
        initOutputPanel();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void initInputPanel(){
        inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));

        noServersPanel = new JPanel();
        noServersPanel.setLayout(new GridLayout(2, 1));
        noServersLabel = new JLabel("Number of Servers");
        noServersPanel.add(noServersLabel);
        noServersTextField = new JTextField();
        noServersPanel.add(noServersTextField);
        noServersPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        inputPanel.add(noServersPanel);

        noTasksPanel = new JPanel();
        noTasksPanel.setLayout(new GridLayout(2, 1));
        noTasksLabel = new JLabel("Number of Tasks");
        noTasksPanel.add(noTasksLabel);
        noTasksTextField = new JTextField();
        noTasksPanel.add(noTasksTextField);
        noTasksPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        inputPanel.add(noTasksPanel);

        minArrivalTimePanel = new JPanel();
        minArrivalTimePanel.setLayout(new GridLayout(2, 1));
        minArrivalTimeLabel = new JLabel("Minimum Arrival Time");
        minArrivalTimePanel.add(minArrivalTimeLabel);
        minArrivalTimeTextField = new JTextField();
        minArrivalTimePanel.add(minArrivalTimeTextField);
        minArrivalTimePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        inputPanel.add(minArrivalTimePanel);

        maxArrivalTimePanel = new JPanel();
        maxArrivalTimePanel.setLayout(new GridLayout(2, 1));
        maxArrivalTimeLabel = new JLabel("Maximum Arrival Time");
        maxArrivalTimePanel.add(maxArrivalTimeLabel);
        maxArrivalTimeTextField = new JTextField();
        maxArrivalTimePanel.add(maxArrivalTimeTextField);
        maxArrivalTimePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        inputPanel.add(maxArrivalTimePanel);

        minProcessingTimePanel = new JPanel();
        minProcessingTimePanel.setLayout(new GridLayout(2, 1));
        minProcessingTimeLabel = new JLabel("Minimum Processing Time");
        minProcessingTimePanel.add(minProcessingTimeLabel);
        minProcessingTimeTextField = new JTextField();
        minProcessingTimePanel.add(minProcessingTimeTextField);
        minProcessingTimePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        inputPanel.add(minProcessingTimePanel);

        maxProcessingTimePanel = new JPanel();
        maxProcessingTimePanel.setLayout(new GridLayout(2, 1));
        maxProcessingTimeLabel = new JLabel("Maximum Processing Time");
        maxProcessingTimePanel.add(maxProcessingTimeLabel);
        maxProcessingTimeTextField = new JTextField();
        maxProcessingTimePanel.add(maxProcessingTimeTextField);
        maxProcessingTimePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        inputPanel.add(maxProcessingTimePanel);

        maxTimePanel = new JPanel();
        maxTimePanel.setLayout(new GridLayout(2, 1));
        maxTimeLabel = new JLabel("Maximum Simulation Time");
        maxTimePanel.add(maxTimeLabel);
        maxTimeTextField = new JTextField();
        maxTimePanel.add(maxTimeTextField);
        inputPanel.add(maxTimePanel);

        runButton = new JButton("Run");
        runButton.setActionCommand("Run");
        runButton.addActionListener(controller);
        inputPanel.add(runButton);

        // Date pre-scrise in GUI pentru testare mai rapida
        noTasksTextField.setText("50");
        noServersTextField.setText("5");
        maxTimeTextField.setText("60");
        minArrivalTimeTextField.setText("2");
        maxArrivalTimeTextField.setText("40");
        minProcessingTimeTextField.setText("1");
        maxProcessingTimeTextField.setText("7");

        contentPanel.add(inputPanel);
    }

    void initOutputPanel(){
        outputPanel = new JPanel();
        outputPanel.setLayout(new GridLayout(1, 1));

        eventLogPanel = new JPanel();
        eventLogPanel.setLayout(new BoxLayout(eventLogPanel, BoxLayout.Y_AXIS));
        eventLogLabel = new JLabel("Event Log");
        eventLogPanel.add(eventLogLabel);
        eventLogTextArea = new JTextArea();
        eventLogTextArea.setEditable(false);
        eventLogPanel.add(eventLogTextArea);
        eventLogScrollPane = new JScrollPane(eventLogTextArea);
        eventLogPanel.add(eventLogScrollPane);
        eventLogPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        outputPanel.add(eventLogPanel);

        contentPanel.add(outputPanel);
    }
}
