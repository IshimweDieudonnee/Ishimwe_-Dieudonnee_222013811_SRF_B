package app.student;

import app.classes.SClass;
import app.layout.ContentPane;
import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AddStudentPanel extends JPanel {

    private JTextField fname, lname, email, nationality;
    private JComboBox sex, studyMode, classes;
    private JButton save,reset;
    private final String sexItems[] = {"Male", "Female", "Prefer not to say"};
    private final String studyModeItems[] = {"Day", "Boarding"};

    ContentPane contentPane;
    StudentDAO studentDAO;

    public AddStudentPanel(ContentPane contentPane) {
        this.contentPane = contentPane;
        studentDAO = new StudentDAO();
        init();
    }

    private void init() {



        setLayout(new MigLayout("insets 5, fill,wrap" ,"[]", "[top]"));

        fname = new JTextField();
        lname = new JTextField();
        email = new JTextField();
        nationality = new JTextField();

        sex = new JComboBox(sexItems);
        studyMode = new JComboBox(studyModeItems);
        classes = new JComboBox();

        List<SClass> classList = studentDAO.getClasses();
        for (SClass sClass : classList) {
            classes.addItem(sClass.getTitle());
        }

        save = new JButton("Save");
        reset = new JButton("Reset");

        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetFields();
            }
        });

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(areFieldsEmpty()) {
                    JOptionPane.showMessageDialog(null,"ERROR: All fields are required");
                }else {
                String ifname = fname.getText();
                String ilname = lname.getText();
                String iemail = email.getText();
                String inationality = nationality.getText();

                String isex = sex.getSelectedItem().toString();
                String istudyMode = studyMode.getSelectedItem().toString();
                String iclass = classes.getSelectedItem().toString();

                studentDAO.insertStudent(new Student(ifname, ilname, iemail, isex, inationality, istudyMode, iclass));
                resetFields();
                }
            }
        });

        fname.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "First Name");
        lname.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Last Name");
        email.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Email");
        nationality.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nationality");

        JPanel panelTitle = new JPanel(new MigLayout("insets 5 10 5 10"));
        panelTitle.putClientProperty(FlatClientProperties.STYLE, "" + "arc:10;"+"background:#ddd");

        JPanel addStudent = new JPanel(new MigLayout("wrap 2, fillx, insets 20", "[grow 0, trail][fill]"));
        addStudent.setPreferredSize(new Dimension(800, getHeight()));
        addStudent.putClientProperty(FlatClientProperties.STYLE, "" + "arc:10;"+"background:#ddd");

        JLabel title = new JLabel("Student Registration form");
        title.putClientProperty(FlatClientProperties.STYLE, ""+ "font: bold +10");

        panelTitle.add(title, "wrap");

        addStudent.add(new JLabel("Personal Details"), "al lead, wrap");

        addStudent.add(new JLabel("Full Names"));
        addStudent.add(fname, "split 2");
        addStudent.add(lname);
        addStudent.add(new JLabel("Email"));
        addStudent.add(email);
        addStudent.add(new JLabel("Sex"));
        addStudent.add(sex);
        addStudent.add(new JLabel("Nationality"));
        addStudent.add(nationality);
        addStudent.add(new JLabel("Study Mode"));
        addStudent.add(studyMode);
        addStudent.add(new JLabel("Class"));
        addStudent.add(classes);

        addStudent.add(save, "cell 1 9,split 2, gapy 10");
        addStudent.add(reset);





        add(panelTitle);
        add(addStudent);

    }

    public void resetFields() {
        fname.setText("");
        lname.setText("");
        email.setText("");
        nationality.setText("");

        sex.setSelectedIndex(0);
        studyMode.setSelectedIndex(0);
        classes.setSelectedIndex(0);
    }

    public boolean areFieldsEmpty() {
        String ifname = fname.getText();
        String ilname = lname.getText();
        String iemail = email.getText();
        String inationality = nationality.getText();

        String isex = sex.getSelectedItem().toString();
        String istudyMode = studyMode.getSelectedItem().toString();
        String iclass = classes.getSelectedItem().toString();

        return ifname.isEmpty() || ilname.isEmpty() || iemail.isEmpty() || inationality.isEmpty() ||
                isex.isEmpty() || istudyMode.isEmpty() || iclass.isEmpty();
    }
}
