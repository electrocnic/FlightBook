import java.awt.*;

/**
 * Created by Andreas on 18.07.2015.
 */
public class FlightBook {

    private FBView view = null;
    private FBModel model = null;

    public FlightBook() {
        view = new FBView( this );
        model = new FBModel( this );
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                FlightBook window = new FlightBook();
            }
        });
    }
}
