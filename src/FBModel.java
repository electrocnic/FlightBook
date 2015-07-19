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

                //Dialog Popup
                s = (String) JOptionPane.showInputDialog(
                        ctr.getView(),
                        ctr.textHandler().greeting(),
                        ctr.textHandler().newFlightBook(),
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

                bw.write("FB_1");
                bw.newLine();
                bw.write("Name: " + s);
                bw.newLine();

                String[] list = {ctr.textHandler().samePath()};
                JComboBox jcb = new JComboBox(list);
                jcb.setEditable(true);
                jcb.setPreferredSize(new Dimension(600,25));
                JPanel panel = new JPanel();
                JPanel panel2 = new JPanel();
                panel.setLayout( new GridLayout(2,1));
                JLabel label = new JLabel("<html>" + ctr.textHandler().choosePath().replaceAll(System.lineSeparator(),"<br>") + "</html>");
                panel.add(label);
                panel2.add(jcb);
                panel.add(panel2);


                //Custom button text
                options = new Object[]{"OK",
                        "Abbrechen"};
                int n = JOptionPane.showOptionDialog(ctr.getView(),
                        panel,
                        ctr.textHandler().choosePathHeader(),
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

                bw.write("Path: " + s);
                bw.newLine();

                JOptionPane.showMessageDialog(ctr.getView(),
                        ctr.textHandler().openingHint());

                bw.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String t="";
                if((t=br.readLine()).equalsIgnoreCase(FBText.DEUTSCH)) ctr.setLang(FBText.DEUTSCH);
                else if(t.equalsIgnoreCase(FBText.ENGLISH)) ctr.setLang(FBText.ENGLISH);
                books = new ArrayList<Book>();
                while( (t=br.readLine())!=null ) {
                    String book=t;
                    t=br.readLine();
                    String name = t.split(" ")[0];
                    if (name.equalsIgnoreCase("Name:")) name=t.split(" ")[1];
                    t=br.readLine();
                    String path = t.split(" ")[0];
                    if( path.equalsIgnoreCase("Path:")) path = t.split(" ")[1];
                    books.add( new Book(name, book, path));

                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
