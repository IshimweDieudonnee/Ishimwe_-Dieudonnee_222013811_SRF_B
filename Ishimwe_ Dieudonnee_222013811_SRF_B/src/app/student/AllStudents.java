package app.student;

import app.layout.ContentPane;
import app.staff.Staff;
import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AllStudents extends JPanel {

    ContentPane contentPane;
    StudentDAO studentDAO;
    private JProgressBar fetchIndicator;
    private JTable studentsTable;
    private DefaultTableModel tableModel;

    public AllStudents(ContentPane contentPane) {
        studentDAO = new StudentDAO();
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
        loadStudents();

        SwingWorker<Void, Void> backReload = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                while (true) {
                    fetchIndicator.setIndeterminate(true);
                    Thread.sleep(5000);
                    SwingUtilities.invokeLater(() -> {
                        reloadStudents();
                        fetchIndicator.setVisible(false);
                    });
                }
            }
        };

        backReload.execute();


        studentsTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(studentsTable);


        listStudents.add(title);
        listStudents.add(fetchIndicator);
        listStudents.add(scrollPane, "grow, h 400!");

        add(listStudents);

    }

    public void loadStudents() {
        List<Student> studentList = studentDAO.getStudents();
        for (Student student : studentList) {
            tableModel.addRow(new Object[] {
                    student.getFname() +" "+ student.getLname(),
                    student.getsClass(), student.getSex(),
                    student.getStudyMode()
            });
        }
    }

    public void reloadStudents() {
        tableModel.setRowCount(0);

        List<Student> studentList = studentDAO.getStudents();
        for (Student student : studentList) {
            tableModel.addRow(new Object[] {
                    student.getFname() +" "+ student.getLname(),
                    student.getsClass(), student.getSex(),
                    student.getStudyMode()
            });
        }
    }

}
