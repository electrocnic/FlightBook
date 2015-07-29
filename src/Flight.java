import java.sql.Time;
import java.util.Date;

/**
 * Created by Andreas on 19.07.2015.
 */
public class Flight {

    private int number = 0;
    private Date date = null;
    private String country = null;
    private String schirm = null;
    private Time starttime = null;
    private Time endtime = null;
    private Time duration = null;
    private int min_speed = 0; //km/h
    private int max_speed = 0;
    private float max_steigen = 0; //m/s
    private float max_sinken = 0;
    private float distance = 0; //km
    private int height_start = 0; //m
    private int height_land = 0; //m
    private int height_kumulativ = 0; //m
    private int height_max = 0;
    private int height_min = 0;
    private String startplatz = null;
    private String landeplatz = null;
    private String kurs = null;
    private String custom_comment = null;


    public Flight() {

    }
}
