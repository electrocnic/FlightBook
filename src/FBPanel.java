import javax.swing.*;
import java.awt.*;

/**
 * Created by Andreas on 18.07.2015.
 */
public class FBPanel extends JPanel{

    private FlightBook ctr = null;

    private JButton button_manual_entry = null;
    private JButton button_view_entries = null;
    private JButton button_refresh_data = null;

    private JPanel p1 = null;

    public FBPanel( FlightBook controller ) {
        this.ctr = controller;
        //setPreferredSize(new Dimension(FBView.WIDTH, FBView.HEIGHT));
        p1 = new JPanel();

        button_manual_entry = new JButton( ctr.textHandler().button_manual_entry() );
        button_view_entries = new JButton( ctr.textHandler().button_view_entries() );
        button_refresh_data = new JButton( ctr.textHandler().button_refresh_data() );

        //TODO: add actionlisteners, create Table, read igc files.

        p1.add( button_manual_entry );
        p1.add( button_view_entries );
        p1.add( button_refresh_data );

        this.add(p1);
    }
}
