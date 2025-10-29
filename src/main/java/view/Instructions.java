package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Instructions extends JFrame {
    public Instructions() {
        setTitle("Instructions");
        setSize(300, 200); // Set the size of the instructions window
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Instructions", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        JLabel instructionLabel = new JLabel("<html><body>Match 3 or more colors based on the threshold you choose to score.<br>Click 'New Game' to start.</body></html>", SwingConstants.CENTER);
        add(instructionLabel, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(this::backPressed);
        add(backButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void backPressed(ActionEvent e) {
        this.dispose(); // Close the instructions window
        // If you need to do any additional cleanup or state management, do it here
    }
}
