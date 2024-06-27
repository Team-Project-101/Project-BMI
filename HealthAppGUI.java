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
        
            public void actionPerformed(ActionEvent e) {
                try {
                    double height = Double.parseDouble(heightField.getText());
                    double weight = Double.parseDouble(weightField.getText());
                     if (height > 4.0) {
               throw new IllegalArgumentException("Height exceeds the maximum limit of 4 meters.");

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
                catch (IllegalArgumentException ex) {
                   resultLabel.setText("Invalid category");
                   adviceLabel.setText("");
               }
            }
        });

        // Show the frame
        frame.setVisible(true);
    }
public static double calculateBMI(double weight, double height) {
        return weight / (height * height);
    }
    public static String determineBMICategory(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi >= 18.5 && bmi < 24.9) {
            return "Normal weight";
        } else if (bmi >= 25 && bmi < 29.9) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    public static String getMealPlanAndExercise(String category) {
        switch (category) {
            case "Underweight":
                return "Meal Plan: High-calorie foods, frequent meals, protein-rich foods, whole grains, healthy fats.\nExercise: Strength training exercises to build muscle mass.";
            case "Normal weight":
                return "Meal Plan: Balanced diet including fruits, vegetables, lean proteins, whole grains.\nExercise: Regular cardio and strength training exercises to maintain weight.";
            case "Overweight":
                return "Meal Plan: Low-calorie foods, more fruits and vegetables, lean proteins, limit sugar and fat intake.\nExercise: Cardio exercises like running, cycling, and strength training.";
            case "Obese":
                return "Meal Plan: Very low-calorie foods, high-fiber foods, avoid sugary and fatty foods.\nExercise: Start with low-impact exercises like walking or swimming, gradually increase intensity.";
            default:
                return "Invalid category.";
        }
    }

