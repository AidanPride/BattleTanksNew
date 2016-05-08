package game;

import game.interfaces.Tank;
import game.tanks.BT7;
import game.tanks.T34;
import game.tanks.Tiger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class StartGUI extends JPanel {
    private Tank tank;
    private final String T34String = "T34";
    private final String TigerString = "Tiger";
    private final String BT7String = "BT7";

    public StartGUI() {
        JFrame frame = new JFrame("BATTLE FIELD");
        frame.setMinimumSize(new Dimension(300, 300));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.setContentPane(createStartMenu());
        frame.pack();
        frame.setVisible(true);

    }

    private JPanel createStartMenu() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        JRadioButton T34Button = new JRadioButton(T34String);
        T34Button.setActionCommand(T34String);
        T34Button.setSelected(true);

        JRadioButton TigerButton = new JRadioButton(TigerString);
        TigerButton.setActionCommand(TigerString);

        JRadioButton BT7Button = new JRadioButton(BT7String);
        BT7Button.setActionCommand(BT7String);

        ButtonGroup group = new ButtonGroup();
        group.add(T34Button);
        group.add(TigerButton);
        group.add(BT7Button);


        JPanel radioPanel = new JPanel(new GridLayout(0, 1));
        radioPanel.add(T34Button);
        radioPanel.add(TigerButton);
        radioPanel.add(BT7Button);

        JButton fight = new JButton("Fight");
        fight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (T34Button.isSelected()) {
                    tank = new T34();
                }
                if (TigerButton.isSelected()) {
                    tank = new Tiger();
                }
                if (BT7Button.isSelected()) {
                    tank = new BT7();
                }
            }

        });

        panel.add(radioPanel);
        panel.add(fight);


        return panel;
    }

    public Tank getTank() {
        return tank;
    }
}
