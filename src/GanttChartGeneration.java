import javax.swing.*;
import java.awt.*;
import java.awt.GridLayout;

public class GanttChartGeneration {
    private JFrame frame;
    private JPanel progressBar; // Renamed from ganttChartPanel
    private JPanel unitBar;
    private int[][] ganttData;

    public GanttChartGeneration(int[][] ganttData) {
        this.ganttData = ganttData;

        // Print ganttData to console
        printGanttData();

        // Create the main frame
        frame = new JFrame("Gantt Chart");
        frame.setSize(1100, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame on the screen

        // Set the frame's content pane to white
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setLayout(null); // Use null layout for absolute positioning

        // Create the Gantt chart panel (now called progress bar)
        progressBar = new JPanel();
        progressBar.setBackground(Color.GRAY);

        // Calculate the position to center the panel horizontally and raise it vertically
        int x = (1000 - 900) / 2;
        int y = 100;
        progressBar.setBounds(x, y, 1000, 100);

        // Create the unit bar panel
        unitBar = new JPanel();
        unitBar.setBackground(Color.LIGHT_GRAY);
        unitBar.setBounds(x, y + 110, 1000, 50); // Position it 10px below the progress bar

        // Create and position the "FCFS" label
        JLabel fcfsLabel = new JLabel("FCFS");
        fcfsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        fcfsLabel.setBounds(x, y - 30, 100, 20); // Positioned above the raised panel

        // Add the FCFS label, progress bar, and unit bar to the frame
        frame.add(fcfsLabel);
        frame.add(progressBar);
        frame.add(unitBar);

        // Call FCFS method to populate the bars
        FCFS();

        // Make the frame visible
        frame.setVisible(true);
    }

    // You can add methods here to implement the FCFS algorithm and draw the Gantt chart

    private void printGanttData() {
        System.out.println("Gantt Data in GanttChartGeneration:");
        System.out.println("------------------------------------");
        System.out.printf("%-10s %-15s %-15s%n", "Process", "Arrival Time", "Burst Time");
        System.out.println("------------------------------------");
        for (int i = 0; i < ganttData.length; i++) {
            System.out.printf("%-10s %-15d %-15d%n", 
                "P" + (i + 1), 
                ganttData[i][0], // Arrival Time
                ganttData[i][1]); // Burst Time
        }
        System.out.println("------------------------------------");
    }

    private void FCFS() {
        //calculate the process order and print it
        int[] processOrder = calculateProcessOrder();
        
        System.out.println("Process Order:");
        for (int i = 0; i < processOrder.length; i++) {
            System.out.println("Process " + (processOrder[i] + 1) + 
                               " (Arrival Time: " + ganttData[processOrder[i]][0] + ")");
        }
        
        // Calculate and print total time
        int totalTime = totalTime();
        System.out.println("\nTotal time: " + totalTime);
        
        // Set up GridLayout for progress bar and unit bar
        progressBar.setLayout(new GridLayout(1, totalTime, 0, 0));
        unitBar.setLayout(new GridLayout(1, totalTime, 0, 0));
        
        // Create units for progress bar and unit bar
        for (int i = 0; i < totalTime; i++) {
            JPanel progressUnit = new JPanel();
            progressUnit.setBackground(Color.LIGHT_GRAY);
            progressBar.add(progressUnit);
            
            JPanel unitBarUnit = new JPanel();
            unitBarUnit.setBackground(Color.WHITE);
            unitBar.add(unitBarUnit);
        }
        
        // Refresh the layout
        progressBar.revalidate();
        unitBar.revalidate();
        
        // TODO: Add process visualization to the progress bar
        // TODO: Add time markers to the unit bar
    }

    private int[] calculateProcessOrder() {
        int[] order = new int[ganttData.length];
        for (int i = 0; i < order.length; i++) {
            order[i] = i;
        }

        //bubble sort
        for (int i = 0; i < order.length - 1; i++) {
            for (int j = 0; j < order.length - i - 1; j++) {
                if (shouldSwap(order[j], order[j + 1])) {
                    int temp = order[j];
                    order[j] = order[j + 1];
                    order[j + 1] = temp;
                }
            }
        }

        return order;
    }

    //swap the order of the processes based on the arrival time and the process number
    private boolean shouldSwap(int index1, int index2) {
        // Compare arrival times
        if (ganttData[index1][0] != ganttData[index2][0]) {
            return ganttData[index1][0] > ganttData[index2][0];
        }
        // If arrival times are equal, compare process numbers
        return index1 > index2;
    }

    private int totalTime() {
        int[] processOrder = calculateProcessOrder();
        int totalTime = 0;
        int currentTime = 0;
        int idleTime = 0;

        for (int i = 0; i < processOrder.length; i++) {
            int processIndex = processOrder[i];
            int arrivalTime = ganttData[processIndex][0];
            int burstTime = ganttData[processIndex][1];

            if (arrivalTime > currentTime) {
                // CPU is idle
                idleTime += arrivalTime - currentTime;
                currentTime = arrivalTime;
            }

            // Add the burst time of the process
            currentTime += burstTime;
        }

        totalTime = currentTime;

        // Print the results
        System.out.println("Total Time (including idle): " + totalTime);
        System.out.println("Idle Time: " + idleTime);
        System.out.println("Total Burst Time: " + (totalTime - idleTime));

        return totalTime;
    }

    
}
