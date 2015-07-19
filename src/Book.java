import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andreas on 19.07.2015.
 */
public class Book {

    private String name = null;
    private String book = null;
    private String path = null;
    private List<Flight> entries = null;

    public Book(String name, String book, String path, List<Flight> entries) {
        this.name=name;
        this.book=book;
        this.entries=entries;
        if(entries==null) entries = new ArrayList<Flight>();
        if( path.equalsIgnoreCase("selbes Verzeichnis") || path.equalsIgnoreCase("same Path")) {
            this.path = "";
        }else this.path = path;
    }

    public Book(String name, String book, String path ) {
        this(name, book, path, null);
    }


}
