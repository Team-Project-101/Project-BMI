import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HealthAppGUI {
    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("BMI Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(7, 1));  // Adjusting the layout to fit 7 components
// Create the labels and text fields
        JLabel heightLabel = new JLabel("Enter your height in meters:");
        JTextField heightField = new JTextField();
        JLabel weightLabel = new JLabel("Enter your weight in kilograms:");
        JTextField weightField = new JTextField();

        // Create the button
        JButton calculateButton = new JButton("Calculate");

        // Create the result label
        JLabel resultLabel = new JLabel("");
        JLabel adviceLabel = new JLabel(""); // Use HTML for multiline text

        // Add components to the frame
        frame.add(heightLabel);
        frame.add(heightField);
        frame.add(weightLabel);
        frame.add(weightField);
        frame.add(calculateButton);
        frame.add(resultLabel);
        frame.add(adviceLabel);
        // Define the action for the button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double height = Double.parseDouble(heightField.getText());
                    double weight = Double.parseDouble(weightField.getText());

                    // Calculate BMI
                    double bmi = calculateBMI(weight, height);
                    resultLabel.setText("Your BMI is: " + String.format("%.2f", bmi));

                    // Determine BMI category and provide meal plan and exercise
                    String category = determineBMICategory(bmi);
                    String advice = getMealPlanAndExercise(category);
                    adviceLabel.setText("<html>" + advice.replace("\n", "<br>") + "</html>");

                } catch (NumberFormatException ex) {
                    resultLabel.setText("Please enter valid numbers.");
                    adviceLabel.setText("");
                }
            }
        });

        // Show the frame
        frame.setVisible(true);
    }

