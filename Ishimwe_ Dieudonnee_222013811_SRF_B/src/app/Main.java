package app;

import app.layout.ContentPane;
import app.layout.Sidebar;
import app.student.AddStudentPanel;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    public Main() {
        init();
    }


    private void init() {


        ContentPane contentPane = new ContentPane();
        Sidebar sidebar = new Sidebar(contentPane);

        getContentPane().add(sidebar, BorderLayout.WEST);
        getContentPane().add(contentPane, BorderLayout.CENTER);

        setTitle("App Demo");
        setSize(800, 700);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    public static void main(String[] args) {

        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("app.resources.themes");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatLightLaf.setup();



        SwingUtilities.invokeLater(() -> {
            Main main = new Main();
            main.setVisible(true);
        });

    }

}
