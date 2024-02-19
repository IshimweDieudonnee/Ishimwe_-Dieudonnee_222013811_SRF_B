package app.classes;

import app.layout.ContentPane;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class VAClasses extends JPanel {

    ContentPane contentPane;

    public VAClasses(ContentPane contentPane) {
        this.contentPane = contentPane;
        init();
    }

    private void init() {
        setLayout(new MigLayout("insets 5, fill,wrap" ,"[]", "[top]"));

        JPanel addClass = new JPanel(new MigLayout());
        JPanel viewClasses = new JPanel(new MigLayout());


        add(addClass);
        add(viewClasses);

    }

}
