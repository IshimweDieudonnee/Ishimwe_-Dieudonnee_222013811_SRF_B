package app.student;

import app.layout.ContentPane;
import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AllStudents extends JPanel {

    ContentPane contentPane;
    private JProgressBar fetchIndicator;
    private JTable studentsTable;
    private DefaultTableModel tableModel;

    public AllStudents(ContentPane contentPane) {
        this.contentPane = contentPane;
        init();
    }

    private void init() {
        setLayout(new MigLayout("insets 5, fill, wrap", "[]", "[top, grow]"));

        fetchIndicator = new JProgressBar();
        fetchIndicator.setIndeterminate(true);


        JPanel listStudents = new JPanel(new MigLayout("wrap, insets 5, fill"));
        listStudents.setPreferredSize(new Dimension(800, getHeight()));

        JLabel title = new JLabel("Students List");
        title.putClientProperty(FlatClientProperties.STYLE, ""+ "font: bold +10");

        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableModel.addColumn("Full Names");
        tableModel.addColumn("Class");
        tableModel.addColumn("Gender");
        tableModel.addColumn("SMode");

        tableModel.addRow(new Object[]{
                "Nishimwe Prosper",
                "S1",
                "Male",
                "Boarding"
        });tableModel.addRow(new Object[]{
                "Nishimwe Prosper",
                "S1",
                "Male",
                "Boarding"
        });

        studentsTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(studentsTable);


        listStudents.add(title);
        listStudents.add(fetchIndicator);
        listStudents.add(scrollPane, "grow, h 400!");

        add(listStudents);

    }

}
