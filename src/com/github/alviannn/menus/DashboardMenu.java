package com.github.alviannn.menus;

import com.github.alviannn.Main;
import com.github.alviannn.models.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class DashboardMenu extends JFrame implements ActionListener {

    private final User currentUser;

    public JLabel rowValue, usernameValue, emailValue;
    public JTable userTable;
    public JButton logoutBtn, deleteBtn;

    public DashboardMenu(User currentUser) {
        this.currentUser = currentUser;

        this.setTitle("User Profiles: " + currentUser.getUsername());
        this.setSize(1000, 800);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.buildFrame();

        this.setVisible(true);
    }

    private JPanel createTablePanel(int baseMargin) {
        String[] columns = {"Username", "Email", "Password"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        List<User> userList = Main.USER_LIST;
        for (User user : userList) {
            Object[] data = {user.getUsername(), user.getEmail(), user.getPassword()};
            model.addRow(data);
        }

        userTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(userTable);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(
                0,
                baseMargin,
                10,
                baseMargin
        ));
        centerPanel.add(scrollPane);

        return centerPanel;
    }

    private JPanel createBottomPanel(int baseMargin) {
        JPanel dataViewPanel = new JPanel(new GridLayout());
        JPanel controlViewPanel = new JPanel(new GridLayout());

        JPanel wrappedDataPanel = new JPanel(new GridLayout(3, 2));
        JPanel wrappedControlPanel = new JPanel(new GridLayout(2, 1, 0, 20));

        JLabel rowLabel = new JLabel("Row:");
        JLabel usernameLabel = new JLabel("Username:");
        JLabel emailLabel = new JLabel("Email address:");

        rowValue = new JLabel();
        usernameValue = new JLabel();
        emailValue = new JLabel();

        wrappedDataPanel.add(rowLabel);
        wrappedDataPanel.add(rowValue);

        wrappedDataPanel.add(usernameLabel);
        wrappedDataPanel.add(usernameValue);

        wrappedDataPanel.add(emailLabel);
        wrappedDataPanel.add(emailValue);

        logoutBtn = new JButton("LOGOUT");
        deleteBtn = new JButton("DELETE");

        logoutBtn.setPreferredSize(new Dimension(0, 30));
        logoutBtn.addActionListener(this);
        deleteBtn.addActionListener(this);

        wrappedControlPanel.add(logoutBtn);
        wrappedControlPanel.add(deleteBtn);

        wrappedDataPanel.setBorder(BorderFactory.createEmptyBorder(
                baseMargin,
                baseMargin,
                baseMargin,
                baseMargin
        ));
        wrappedControlPanel.setBorder(BorderFactory.createEmptyBorder(
                baseMargin,
                baseMargin * 4,
                baseMargin,
                baseMargin * 4
        ));

        dataViewPanel.add(wrappedDataPanel);
        controlViewPanel.add(wrappedControlPanel);

        dataViewPanel.setBorder(BorderFactory.createTitledBorder("Data View"));
        controlViewPanel.setBorder(BorderFactory.createTitledBorder("Control View"));

        JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 10, 0));

        bottomPanel.add(dataViewPanel);
        bottomPanel.add(controlViewPanel);

        bottomPanel.setBorder(BorderFactory.createEmptyBorder(
                0,
                baseMargin,
                baseMargin,
                baseMargin
        ));

        return bottomPanel;
    }

    private void buildFrame() {
        this.setLayout(new BorderLayout());

        int baseMargin = 40;
        JLabel titleLabel = new JLabel("USER PROFILES", JLabel.CENTER);

        titleLabel.setFont(new Font(null, Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(
                baseMargin,
                0,
                baseMargin,
                0
        ));

        JPanel tablePanel = this.createTablePanel(baseMargin);
        JPanel bottomPanel = this.createBottomPanel(baseMargin);

        this.add(titleLabel, BorderLayout.NORTH);
        this.add(tablePanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == logoutBtn) {
            this.dispose();
            new LoginMenu();
        } else if (e.getSource() == deleteBtn) {

        }
    }

}
