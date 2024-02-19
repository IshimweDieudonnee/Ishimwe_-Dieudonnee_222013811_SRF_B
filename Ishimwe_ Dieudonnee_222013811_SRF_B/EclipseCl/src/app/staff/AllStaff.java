package app.staff;

import app.layout.ContentPane;
import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AllStaff extends JPanel {

    private JProgressBar fetchIndicator;
    private DefaultTableModel tableModel;
    private JTable staffTable;

    ContentPane contentPane;

    public AllStaff(ContentPane contentPane) {
        this.contentPane = contentPane;
        init();
    }

    private void init() {

        setLayout(new MigLayout("insets 5, fill, wrap", "[]", "[top, grow]"));

        fetchIndicator = new JProgressBar();
        fetchIndicator.setIndeterminate(true);


        JPanel listStaff = new JPanel(new MigLayout("wrap, insets 5, fill"));
        listStaff.setPreferredSize(new Dimension(800, getHeight()));

        JLabel title = new JLabel("Staff List");
        title.putClientProperty(FlatClientProperties.STYLE, ""+ "font: bold +10");

        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableModel.addColumn("Full Names");
        tableModel.addColumn("Phone");
        tableModel.addColumn("Email");

        tableModel.addRow(new Object[]{
                "Nishimwe Prosper",
                "079154300",
                "prosper.rk1@gmail.com"
        });

        staffTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(staffTable);


        listStaff.add(title);
        listStaff.add(fetchIndicator);
        listStaff.add(scrollPane, "grow, h 400!");

        add(listStaff);


    }

}
