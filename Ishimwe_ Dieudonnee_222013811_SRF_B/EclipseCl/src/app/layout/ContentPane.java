package app.layout;

import app.classes.VAClasses;
import app.staff.AddStaff;
import app.staff.AllStaff;
import app.student.AddStudentPanel;
import app.student.AllStudents;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class ContentPane extends JPanel {

    JPanel content;
    private CardLayout cardLayout;

    JPanel cardPanel;


    public ContentPane() {
        init();
    }

    private void init() {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        setLayout(new BorderLayout());

        content = new JPanel(new MigLayout());
        content.add(cardPanel);


        add(content, BorderLayout.CENTER);

        AllStudents allStudents = new AllStudents(this);
        AddStudentPanel addStudentPanel = new AddStudentPanel(this);
        VAClasses vaClasses = new VAClasses(this);
        AddStaff addStaff = new AddStaff(this);
        AllStaff allStaff = new AllStaff(this);

        addPanel("allStd", allStudents);
        addPanel("addStd", addStudentPanel);
        addPanel("vaClass", vaClasses);
        addPanel("addStf", addStaff);
        addPanel("allStf", allStaff);
    }

    public void addPanel(String panelName, JPanel panel) {
        cardPanel.add(panel, panelName);
    }

    public void switchPanel(String panelName) {
        cardLayout.show(cardPanel, panelName);
    }

}
