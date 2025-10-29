package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import controller.GameController;

public class MainMenu extends JFrame {

    private GameController gameController;

    public MainMenu() {
        gameController = new GameController(); // Create a single instance of GameController

        setTitle("Color Buster");
        setSize(400, 300); // Adjust the size as necessary
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.RED); // Set the background color to red for the content pane

        // Title label setup
        JLabel titleLabel = new JLabel("Color Buster", SwingConstants.CENTER);
        titleLabel.setOpaque(true);
        titleLabel.setBackground(Color.RED);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // Button panel setup
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); // Make panel transparent
        buttonPanel.setLayout(new GridLayout(1, 2, 10, 10)); // 1 row, 2 columns

        // New Game button setup
        JButton newGameButton = new JButton("New Game");
        newGameButton.addActionListener(this::newGamePressed);
        buttonPanel.add(newGameButton);

        // Instructions button setup
        JButton instructionsButton = new JButton("Instructions");
        instructionsButton.addActionListener(e -> showInstructions());
        buttonPanel.add(instructionsButton);

        // Center panel setup
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false); // Make panel transparent
        centerPanel.setBackground(Color.RED); // In case opaque is set to true, ensure background is red
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        centerPanel.add(buttonPanel, gbc);

        // Add centerPanel to JFrame
        add(centerPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void newGamePressed(ActionEvent e) {
        this.setVisible(false); // Hide the main menu
        gameController.startNewGame(); // Use the existing gameController to start a new game
    }

    private void showInstructions() {
        Instructions instructions = new Instructions();
        instructions.setVisible(true);
    }
}
