import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andreas on 19.07.2015.
 */
public class Book {

    private String name = null;
    private String book = null;
    private String path = null;
    private List<Flight> flights = null;

    public Book(String name, String book, String path, List<Flight> flights) {
        this.name=name;
        this.book=book;
        this.flights = flights;
        if(flights ==null) flights = new ArrayList<Flight>();
        if( path.equalsIgnoreCase("selbes Verzeichnis") || path.equalsIgnoreCase("same Path")) {
            this.path = "";
        }else this.path = path;
    }

    public Book(String name, String book, String path ) {
        this(name, book, path, null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public void addNewFlight( Flight flight ) {
        if(flights==null) flights = new ArrayList<Flight>();
        flights.add( flight );
    }

    public void addNewFlight() {

    }

    public String toString() {
        return book + System.lineSeparator() + "Name: " + name + System.lineSeparator() + "Path: " + path;
    }
}
