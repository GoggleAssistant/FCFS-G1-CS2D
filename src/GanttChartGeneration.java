import javax.swing.*;
import java.awt.*;

public class GanttChartGeneration {
    private JFrame frame;
    private JPanel ganttChartPanel;
    private int[][] ganttData;

    public GanttChartGeneration(int[][] ganttData) {
        this.ganttData = ganttData;

        // Create the main frame
        frame = new JFrame("Gantt Chart");
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame on the screen

        // Set the frame's content pane to white
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setLayout(null); // Use null layout for absolute positioning

        // Create the Gantt chart panel
        ganttChartPanel = new JPanel();
        ganttChartPanel.setBackground(Color.GRAY);

        // Calculate the position to center the panel
        int x = (1000 - 900) / 2;
        int y = (600 - 100) / 2;
        ganttChartPanel.setBounds(x, y, 900, 100);

        // Create and position the "FCFS" label
        JLabel fcfsLabel = new JLabel("FCFS");
        fcfsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        fcfsLabel.setBounds(x, y - 30, 100, 20); // Positioned above and aligned with the left of the panel

        // Add the FCFS label and Gantt chart panel to the frame
        frame.add(fcfsLabel);
        frame.add(ganttChartPanel);

        // TODO: Implement FCFS algorithm and draw Gantt chart here using ganttData

        // Make the frame visible
        frame.setVisible(true);
    }

    // You can add methods here to implement the FCFS algorithm and draw the Gantt chart
}
