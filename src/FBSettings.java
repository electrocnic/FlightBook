import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Andreas on 19.07.2015.
 */
public class FBSettings extends JFrame {

    public static final int WIDTH = 300;
    public static final int HEIGHT = 200;

    private FlightBook ctr = null;
    private JPanel panel = null;
    private JComboBox userselection = null;

    public FBSettings( FlightBook controller ) {
        ctr = controller;

        addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        // This closes the settings and sets the original frame visible.
                        System.out.println("Exit Settings");
                        ctr.setVisibility( FlightBook.VS_DEFAULT );
                    }
                }
        );

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(FBSettings.WIDTH, FBSettings.HEIGHT));
        //panel.setLayout( new GridLayout(3,2) );

        //TODO: Settings User Interface to finish  Springlayout? See zettel...
        //TODO: Reload files and language after apply in settings...

        JLabel userselection_label = new JLabel( ctr.textHandler().userselection_label());
        panel.add( userselection_label );

        userselection = new JComboBox();
        userselection.setEditable(true);
        for( String s : ctr.getBookUsers() ) {
            userselection.addItem( s );
        }
        userselection.addActionListener(ctr);
        userselection.setName( FlightBook.USERSELECTION );
        panel.add( userselection );

        this.add(panel);
        pack();
        setTitle(FlightBook.SETTINGS);
        this.setLocationRelativeTo(null);
        this.setVisible(false);
    }

}
