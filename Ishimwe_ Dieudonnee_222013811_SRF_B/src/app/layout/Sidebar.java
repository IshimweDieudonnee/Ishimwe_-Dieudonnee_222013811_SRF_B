package app.layout;

import app.Main;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Sidebar extends JPanel {

    private JButton menuBtns[];
    private String[][] submenuItems;

    private final String [][] menuItems = {
//            {"Dashboard"},
            {"Students", "Add Students", "View Students"},
            {"Staff", "View Staff", "Add Staff"},
//            {"Class", "All Classes"}
    };

    ContentPane contentPane;

    public Sidebar(ContentPane contentPane) {
        this.contentPane = contentPane;
        init();
    }

    private void init() {


        int borderThickness = 1;
        Color borderColor = Color.GRAY;
        Border rightBorder = BorderFactory.createMatteBorder(0, 0, 0, borderThickness, borderColor);

        setPreferredSize(new Dimension(150, 700));
        setLayout(new MigLayout("insets 20, wrap"));
//        setSize(200, getHeight());

        setBorder(BorderFactory.createCompoundBorder(getBorder(), rightBorder));

       menuBtns = new JButton[menuItems.length];
       submenuItems = new String[menuItems.length][];

        for (int i = 0; i < menuItems.length; i++) {
            String btntxt = menuItems[i][0];
            menuBtns[i] = new JButton(btntxt);
            add(menuBtns[i]);

            final int index = i;
            menuBtns[i].addActionListener(e -> {
                if (submenuItems[index].length == 0) {
                    System.out.println(btntxt);

                }else {
                    showSubMenu(menuBtns[index], submenuItems[index]);
                }
            });
            submenuItems[i] = new String[menuItems[i].length - 1];
            System.arraycopy(menuItems[i], 1, submenuItems[i], 0, menuItems[i].length-1);
        }

    }

    private void showSubMenu(Component parentComp, String[] subMenu) {
        JPopupMenu popupMenu = new JPopupMenu();

        for(String menuItem : subMenu) {
            JMenuItem menuItemComp = new JMenuItem(menuItem);
            menuItemComp.addActionListener(e -> {
                System.out.println(menuItemComp.getText());
                if(menuItemComp.getText().equals("View Students")) {
                    contentPane.switchPanel("allStd");
                }else if(menuItemComp.getText().equals("Add Students")) {
                    contentPane.switchPanel("addStd");
                }else if(menuItemComp.getText().equals("All Classes")) {
                    contentPane.switchPanel("vaClass");
                } else if (menuItemComp.getText().equals("Add Staff")) {
                    contentPane.switchPanel("addStf");
                } else if (menuItemComp.getText().equals("View Staff")) {
                    contentPane.switchPanel("allStf");
                }
            });
            popupMenu.add(menuItemComp);
        }
        popupMenu.show(parentComp, 0, parentComp.getHeight());
    }

}
