package app.staff;

import app.layout.ContentPane;
import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddStaff extends JPanel {

    ContentPane contentPane;
    StaffDAO staffDAO;
    private JTextField fname, lname, phone, country, city, email, address;
    private JButton save, reset;
    public AddStaff(ContentPane contentPane) {
        this.contentPane = contentPane;
        init();
    }

    private void init() {

        staffDAO = new StaffDAO();

        setLayout(new MigLayout("insets 5, wrap, fill", "[]", "[top]"));

        fname = new JTextField();
        lname = new JTextField();
        email = new JTextField();
        phone = new JTextField();
        country = new JTextField();
        city = new JTextField();
        address = new JTextField();

        save = new JButton("Save");
        reset = new JButton("Reset");

        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetForm();
            }
        });

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ifname = fname.getText();
                String ilname = lname.getText();
                String iemail = email.getText();
                String iphone = phone.getText();
                String icountry = country.getText();
                String icity = city.getText();
                String iaddress = address.getText();

                staffDAO.insertStaff(new Staff(ifname, ilname, iemail, iphone, icountry, icity, iaddress));
                resetForm();
            }
        });

        fname.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "First Name");
        lname.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Last Name");
        phone.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Phone number");
        email.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Email Address");
        country.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Country");
        city.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "City");
        address.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Eg: Kigali, Nyarugenge, Gitega");

        JPanel panelTitle = new JPanel(new MigLayout("insets 5 10 5 10"));
        panelTitle.putClientProperty(FlatClientProperties.STYLE, "" + "arc:10;"+"background:#ddd");
        JLabel title = new JLabel("Staff Registration form");
        title.putClientProperty(FlatClientProperties.STYLE, ""+ "font: bold +10");

        JPanel panelBody = new JPanel(new MigLayout("wrap 2, fillx, insets 20", "[grow 0, trail][fill]"));

        panelBody.add(new JLabel("Personal Details"), "al lead, wrap");
        panelBody.setPreferredSize(new Dimension(800, getHeight()));
        panelBody.putClientProperty(FlatClientProperties.STYLE, "" + "arc:10;"+"background:#ddd");

        panelBody.add(new JLabel("Full Names"));
        panelBody.add(fname, "split 2");
        panelBody.add(lname);
        panelBody.add(new JLabel("Email"));
        panelBody.add(email);
        panelBody.add(new JLabel("Phone number"));
        panelBody.add(phone);
        panelBody.add(new JLabel("Country / City"));
        panelBody.add(country, "split 2");
        panelBody.add(city);
        panelBody.add(new JLabel("Address"));
        panelBody.add(address);

        panelBody.add(save, "cell 1 8,split 2, gapy 10");
        panelBody.add(reset);


        panelTitle.add(title, "wrap");

        add(panelTitle); add(panelBody);


    }

    public void resetForm() {
        fname.setText("");
        lname.setText("");
        email.setText("");
        phone.setText("");
        country.setText("");
        city.setText("");
        address.setText("");
    }


}
