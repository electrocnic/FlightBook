import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Andreas on 18.07.2015.
 */
public class FlightBook implements ActionListener{

    public static final String SETTINGS = "Settings";
    public static final String EDIT = "Edit";

    public static final int VS_DEFAULT = 1;
    public static final int VS_SETTINGS = 2;
    public static final int VS_VIEW = 3;

    private FBView view = null;
    private FBModel model = null;
    private FBSettings settings = null;

    public FlightBook() {
        settings = new FBSettings( this );
        view = new FBView( this );
        model = new FBModel( this );
    }

    /**
     * Removes all windows but shows the original default window.
     * TODO: Update if necessary
     */
    public void setVisibility( int visibility ) {
        switch( visibility ) {
            case VS_DEFAULT:
                settings.setVisible(false);
                view.setVisible(true);
                break;
            case VS_SETTINGS:
                settings.setVisible(true);
                view.setVisible(false);
                break;
            case VS_VIEW:
                settings.setVisible(false);
                view.setVisible(false);
                break;
        }
    }



    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                FlightBook window = new FlightBook();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if( SETTINGS.equalsIgnoreCase(e.getActionCommand()) ) {
            System.out.println("Settings pressed");
            setVisibility( VS_SETTINGS );
        }
    }
}
