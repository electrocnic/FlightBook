/**
 * Created by Andreas on 24.07.2015.
 */
public class BookNotExistsException extends Exception {

    public BookNotExistsException() {
        super("This book does not exist.");
    }

    public BookNotExistsException( String s ) {
        super(s);
    }
}
