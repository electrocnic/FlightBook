import javax.swing.*;

/**
 * Created by Andreas on 18.07.2015.
 */
public class FBView extends JFrame {

    public static final int WIDTH = 500;
    public static final int HEIGHT = 300;
    private FlightBook ctr = null;

    public FBView( FlightBook controller ) {
        this.ctr = controller;
        setResizable(true);
        this.add(new FBPanel());
        pack();

        setTitle("FlightBook");

        setDefaultCloseOperation(EXIT_ON_CLOSE);


        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }


}
