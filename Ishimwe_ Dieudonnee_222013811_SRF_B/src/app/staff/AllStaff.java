package app.staff;

import app.layout.ContentPane;
import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import  java.util.List;

public class AllStaff extends JPanel {

    private JProgressBar fetchIndicator;
    private DefaultTableModel tableModel;
    private JTable staffTable;

    StaffDAO staffDAO;

    ContentPane contentPane;

    public AllStaff(ContentPane contentPane) {
        this.contentPane = contentPane;
        init();
    }

    private void init() {

        staffDAO = new StaffDAO();

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

        loadStaff();

        SwingWorker<Void, Void> backReload = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                while (true) {
                    fetchIndicator.setIndeterminate(true);
                    Thread.sleep(5000);
                    SwingUtilities.invokeLater(() -> {
                        reloadStaff();
                        fetchIndicator.setVisible(false);
                    });
                }
            }
        };

        backReload.execute();


        staffTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(staffTable);


        listStaff.add(title);
        listStaff.add(fetchIndicator);
        listStaff.add(scrollPane, "grow, h 400!");

        add(listStaff);


    }

    public void loadStaff() {
        List<Staff> staffList = staffDAO.getAllStaff();
        for (Staff staff : staffList) {
            tableModel.addRow(new Object[] {
                    staff.getFname() + staff.getLname(),
                    staff.getPhone(),
                    staff.getEmail()
            });
        }
    }

    public void reloadStaff() {
        tableModel.setRowCount(0);

        List<Staff> staffList = staffDAO.getAllStaff();
        for (Staff staff : staffList) {
            tableModel.addRow(new Object[] {
                    staff.getFname() +" "+ staff.getLname(),
                    staff.getPhone(),
                    staff.getEmail()
            });
        }

    }

}
