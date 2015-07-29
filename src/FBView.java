import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Andreas on 18.07.2015.
 */
public class FBView extends JFrame {

    public static final int WIDTH = 500;
    public static final int HEIGHT = 300;
    private FlightBook ctr = null;

    private JMenuBar menubar = null;
    private JMenu edit = null;
    private JMenuItem settings = null;

    public FBView( FlightBook controller ) {
        this.ctr = controller;
        setResizable(true);
        try {
            this.setIconImage( ImageIO.read( this.getClass().getClassLoader().getResourceAsStream("resources/paragliding-icon.png") ) );
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }

        /** -------+-------++--- Menu Bar ---++-------+------- **/
        menubar = new JMenuBar();
        edit = new JMenu( FlightBook.EDIT );
        edit.setMnemonic(KeyEvent.VK_E);

        settings = new JMenuItem( FlightBook.SETTINGS );
        settings.setMnemonic(KeyEvent.VK_S);
        settings.addActionListener(ctr);

        edit.add(settings);
        menubar.add(edit);
        this.setJMenuBar(menubar);

        this.add(new FBPanel( ctr ));



        pack();
        setTitle("FlightBook");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }


    public void refreshLanguage() {
        //TODO
    }

}
