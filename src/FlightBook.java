import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andreas on 18.07.2015.
 */
public class FlightBook implements ActionListener{

    public static final String SETTINGS = "Settings";
    public static final String EDIT = "Edit";
    public static final String USERSELECTION = "UserSelection";
    public static final String LANGUAGE = "Language";

    public static final int VS_DEFAULT = 1;
    public static final int VS_SETTINGS = 2;
    public static final int VS_VIEW = 3;

    private FBView view = null;
    private FBModel model = null;
    private FBSettings settings = null;
    private FBText text = null;

    public FlightBook() {
        text = new FBText( this );
        model = new FBModel( this );
        settings = new FBSettings( this );
        view = new FBView( this );
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

    /**
     * Returns the books from the model.
     * @return users.
     */
    public List<Book> getBooks() {
        return model.getBooks();
    }

    /**
     * Returns the username of each book in a list.
     * @return
     */
    public List<String> getBookUsers() {
        List<String> users = new ArrayList<String>();
        for( Book b : model.getBooks() ) {
            users.add( b.getName() );
        }
        return users;
    }

    public JFrame getView() {
        return view;
    }

    /**
     * Returns the current chosen language.
     * @return lang
     */
    public String getLang() {
        return text.getLang();
    }

    /**
     * Returns the object of the class which stores the text sections.
     * @return
     */
    public FBText textHandler() {
        return text;
    }


    public void setLang(String lang) {
        text.setLang(lang);
    }

    /**
     * Returns the path for the FlightBook (parameter 1).
     * @param flightBook
     * @return
     */
    public String getPath( String flightBook ) {
        return model.getPath( flightBook );
    }

    public Book getSelectedBook() {
        return model.getSelectedBook();
    }

    public void setSelectedBook( String user ) throws BookNotExistsException {
        model.setSelectedBook( user );
    }

    public void setSelectedBook( Book book ) throws BookNotExistsException {
        model.setSelectedBook( book.getName() );
    }

    public void deleteBook( String user ) {
        model.deleteBook( user );
    }

    public void addNewBook( String user, String book, String path ) {
        model.addNewBook(user, book, path);
    }

    public String getNewBookID() {
        return "FB_" + (model.getBooks().size()+1);
    }

    public void applyChanges( String user, String path ) {
        model.getBook( user ).setPath( path );
    }

    public void refreshSettingsCFG() {
        File file = new File("FlightBook"+File.separator+"settings.cfg");
        try {
            BufferedWriter wr = new BufferedWriter( new FileWriter( file ));
            wr.write("Language: " + text.getLang());
            wr.newLine();
            wr.write( ""+model.getSelectedIndex() );
            wr.newLine();
            for( Book book : model.getBooks() ) {
                wr.write( ""+book );
                wr.newLine();
            }
            wr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getSelectedIndex() {
        return model.getSelectedIndex();
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
        }else if( e.getActionCommand().equalsIgnoreCase("ComboboxChanged") && settings!=null &&view!=null &&text!=null ) {
            JComboBox temp = (JComboBox) e.getSource();
            String selected = (String)temp.getSelectedItem();
            if( temp.getName().equalsIgnoreCase( USERSELECTION )) {
                System.out.println("Userselection action");
                settings.setPath( model.getPath( selected ) );
            }else if( temp.getName().equalsIgnoreCase( LANGUAGE )) {
                System.out.println("Language action");
                text.setLang( selected );
                settings.refreshLanguage();
                view.refreshLanguage();
            }
        }
    }
}
