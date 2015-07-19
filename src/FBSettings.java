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
        this.add(panel);
        pack();
        setTitle( FlightBook.SETTINGS );
        this.setLocationRelativeTo(null);
        this.setVisible(false);
    }

}
