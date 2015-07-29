import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andreas on 18.07.2015.
 */
public class FBModel {

    private FlightBook ctr = null;
    private List<Book> books = null;
    private int n_selected = 0;

    public FBModel( FlightBook controller ) {
        this.ctr = controller;

        File fb = new File( "FlightBook" );
        if( !fb.exists() ) fb.mkdir();

        /** Search for settings.cfg **/
        File file = new File("FlightBook"+File.separator+"settings.cfg");
        System.out.println(file.getAbsoluteFile());
        if( !file.exists() ) {  //Write
            try {
                BufferedWriter bw = new BufferedWriter( new FileWriter(file) );

                Object[] options = {"Deutsch", "English"};
                //Dialog Popup Language
                String s = (String) JOptionPane.showInputDialog(
                        ctr.getView(),
                        "Language / Sprache:",
                        "Language / Sprache",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        options,
                        options[0]);

                if( s==null ) {
                    bw.close();
                    file.delete();
                    fb.delete();
                    System.exit(0);
                }

                if( s.equalsIgnoreCase(FBText.ENGLISH) ) ctr.setLang( FBText.ENGLISH );
                else if( s.equalsIgnoreCase(FBText.DEUTSCH)) ctr.setLang( FBText.DEUTSCH );
                bw.write( "Language: " + s );
                bw.newLine();
                bw.write( "0" );
                bw.newLine();
                //Dialog Popup
                s = (String) JOptionPane.showInputDialog(
                        ctr.getView(),
                        ctr.textHandler().greeting_newFlightBook_text(),
                        ctr.textHandler().greeting_newFlightBook_title(),
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        "Max Mustermann");

                if( s==null ) {
                    bw.close();
                    file.delete();
                    fb.delete();
                    System.exit(0);
                }

                books = new ArrayList<Book>();
                books.add( new Book(s, "FB_1", "same path") );
                bw.write("FB_1");
                bw.newLine();
                bw.write("Name: " + s);
                bw.newLine();

                String[] list = {ctr.textHandler().generalPurpose_samePath()};
                JComboBox jcb = new JComboBox(list);
                jcb.setEditable(true);
                jcb.setPreferredSize(new Dimension(600,25));
                JPanel panel = new JPanel();
                JPanel panel2 = new JPanel();
                panel.setLayout( new GridLayout(2,1));
                JLabel label = new JLabel("<html>" + ctr.textHandler().greeting_choosePath_text().replaceAll(System.lineSeparator(),"<br>") + "</html>");
                panel.add(label);
                panel2.add(jcb);
                panel.add(panel2);


                //Custom button text
                options = new Object[]{"OK",
                        ctr.textHandler().button_cancel()};
                int n = JOptionPane.showOptionDialog(ctr.getView(),
                        panel,
                        ctr.textHandler().greeting_choosePath_title(),
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);

                if( n==1 ) { //equals s==null
                    bw.close();
                    file.delete();
                    fb.delete();
                    System.exit(0);
                }

                s = (String) jcb.getSelectedItem();

                books.get(0).setPath(s);
                bw.write("Path: " + s);
                bw.newLine();

                JOptionPane.showMessageDialog(ctr.getView(),
                        ctr.textHandler().greeting_finished_text());

                bw.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(file));
                String t="";
                if((t=br.readLine().split(" ")[1]).equalsIgnoreCase(FBText.DEUTSCH)) ctr.setLang(FBText.DEUTSCH);
                else if(t.equalsIgnoreCase(FBText.ENGLISH)) ctr.setLang(FBText.ENGLISH);
                n_selected = Integer.parseInt(br.readLine());
                books = new ArrayList<Book>();
                while( (t=br.readLine())!=null ) {
                    String book=t;
                    t=br.readLine();
                    String name = t.split(" ")[0];
                    if (name.equalsIgnoreCase("Name:")){
                        name="";
                        for(int i=1; i<t.split(" ").length; i++) name+=t.split(" ")[i]+((i+1<t.split(" ").length)?" ":"");
                    }
                    t=br.readLine();
                    String path = t.split(" ")[0];
                    if( path.equalsIgnoreCase("Path:")){
                        path="";
                        for(int i=1; i<t.split(" ").length; i++) path+=t.split(" ")[i]+((i+1<t.split(" ").length)?" ":"");
                    }
                    if( path.endsWith("\\") ) path = path.substring(0, path.length()-1 );
                    books.add( new Book(name, book, path));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(ctr.getView(),
                        ctr.textHandler().generalPurpose_settingsCorrupt());
                try {
                    br.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                file.delete();
                fb.delete();
                System.exit(0);
            }

        }

    }

    public List<Book> getBooks() {
        return books;
    }

    public String getPath( String flightBook ) {
        for( Book b : books ) {
            if( b.getName().equalsIgnoreCase( flightBook ) ) {
                String s = b.getPath();
                if( s.isEmpty() ) s=ctr.textHandler().generalPurpose_samePath();
                return s;
            }
        }
        return ctr.textHandler().generalPurpose_noPathSaved();
    }

    public Book getSelectedBook() {
        if( n_selected >= books.size() ) n_selected = 0;
        return books.get( n_selected );
    }

    public void setSelectedBook( String user ) throws BookNotExistsException {
        int n = getIndex( user );
        if( n != -1 ) {
            n_selected = n;
        } else {
            throw new BookNotExistsException();
        }
    }

    public int getSelectedIndex() {
        return n_selected;
    }

    /**
     * Deletes the Book with equal user names and chooses another book as selected book.
     * @param user
     */
    public void deleteBook( String user ) {
        int n = getIndex( user );
        if( n != -1 ) {
            books.remove( n );
            if( books.isEmpty() ) {
                n_selected = -1;
            } else n_selected = 0;
        }
    }

    private int getIndex( String user ) {
        int n=0;
        for( ; n<books.size(); n++ ) {
            if( books.get(n).getName().equalsIgnoreCase( user )) {
                return n;
            }
        }
        return -1;
    }

    public void addNewBook( String user, String book, String path ) {
        books.add( new Book(user, book, path));
    }

    public Book getBook( String user ) {
        return books.get( getIndex(user) );
    }


}
