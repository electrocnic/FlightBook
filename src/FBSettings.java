import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Andreas on 19.07.2015.
 */
public class FBSettings extends JFrame implements ActionListener {

    public static final int WIDTH = 300;
    public static final int HEIGHT = 200;

    private FlightBook ctr = null;

    private JPanel panel = null;
    private JPanel p1 = null;
    private JPanel p2 = null;
    private JPanel p3 = null;
    private JPanel p4 = null;
    private JPanel p5 = null;
    private JPanel p6 = null;
    private JPanel p7 = null;

    private JLabel label_selectedFB = null;
    private JLabel label_path = null;
    private JLabel label_language = null;
    private JLabel label_delete = null;
    private JLabel label_delete_warning = null;

    private JComboBox combobox_userselection = null;
    private JComboBox combobox_language = null;

    private JCheckBox checkbox_delete = null;

    private JTextField textfield_path = null;

    private JButton button_help1 = null;
    private JButton button_help2 = null;
    private JButton button_apply = null;
    private JButton button_cancel = null;

    private JSeparator separator = null;



    public FBSettings( FlightBook controller ) {
        ctr = controller;

        addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        // This closes the settings and sets the original frame visible.
                        System.out.println("Exit Settings");
                        ctr.setVisibility( FlightBook.VS_DEFAULT );
                    }
                }
        );


        /** -----+-----++--- Main Panel Initialization ---++-----+----- **/
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(FBSettings.WIDTH, FBSettings.HEIGHT));
        panel.setLayout( new GridLayout(6,1) );


        /** -----+-----++--- Flight Book Selection Panel ---++-----+----- **/
        p1 = new JPanel();
        label_selectedFB = new JLabel( ctr.textHandler().userselection_label());

        combobox_userselection = new JComboBox();
        combobox_userselection.setEditable(true);
        for( String s : ctr.getBookUsers() ) {
            combobox_userselection.addItem(s);
        }
        combobox_userselection.addActionListener(ctr);
        combobox_userselection.setName(FlightBook.USERSELECTION);

        BufferedImage help1ICON = null;
        try {
            help1ICON = ImageIO.read( System.class.getResourceAsStream("resources/question_mark_icon.gif"));
        } catch (IOException e) {
            System.out.println("No Image found.");
            e.printStackTrace();
        }
        if( help1ICON==null ) button_help1 = new JButton("?");
        else {
            button_help1 = new JButton( new ImageIcon(help1ICON) );
            button_help1.setBorder(BorderFactory.createEmptyBorder());
            button_help1.setContentAreaFilled(false);
        }

        p1.add( label_selectedFB );
        p1.add( combobox_userselection );
        p1.add( button_help1 );


        /** -----+-----++--- Search Path LABEL ---++-----+----- **/
        label_path = new JLabel( ctr.textHandler().settingsPathLabel() );
        label_path.setPreferredSize(new Dimension(WIDTH - 10, 30));
        p2 = new JPanel();
        p2.setLayout(new BorderLayout());
        p2.add(Box.createRigidArea(new Dimension(5,0)), BorderLayout.WEST);
        p2.add(label_path, BorderLayout.CENTER);


        /** -----+-----++--- Search Path Textfield ---++-----+----- **/
        textfield_path = new JTextField( getPath() );
        textfield_path.setPreferredSize(new Dimension(WIDTH - 10, 35));
        p3 = new JPanel();
        p3.setLayout(new BorderLayout());
        p3.add(Box.createRigidArea(new Dimension(5, 0)), BorderLayout.WEST);
        p3.add(Box.createRigidArea(new Dimension(5, 0)), BorderLayout.EAST);
        p3.add(textfield_path, BorderLayout.CENTER);


        /** -----+-----++--- Separator ---++-----+----- **/
        //p4 = new JPanel();
        //p4.add(Box.createRigidArea(new Dimension(5, 0)), BorderLayout.WEST);
        //p4.add(Box.createRigidArea(new Dimension(5, 0)), BorderLayout.EAST);
        //separator = new JSeparator();
        //p4.add( separator );


        /** -----+-----++--- Language Panel ---++-----+----- **/
        label_language = new JLabel( ctr.textHandler().label_language() );
        combobox_language = new JComboBox();
        combobox_language.setEditable(false);
        combobox_language.addItem("Deutsch");
        combobox_language.addItem("English");
        combobox_language.setName( FlightBook.LANGUAGE );
        p5 = new JPanel();
        p5.add( label_language );
        p5.add( combobox_language );


        /** -----+-----++--- Delete Panel ---++-----+----- **/
        label_delete = new JLabel( ctr.textHandler().label_delete() );
        checkbox_delete = new JCheckBox();

        if( help1ICON==null ) button_help2 = new JButton("?");
        else {
            button_help2 = new JButton( new ImageIcon(help1ICON) );
            button_help2.setBorder(BorderFactory.createEmptyBorder());
            button_help2.setContentAreaFilled(false);
        }

        p6 = new JPanel();
        p6.add( label_delete );
        p6.add( checkbox_delete );
        p6.add( button_help2 );


        /** -----+-----++--- Apply and Cancel Panel ---++-----+----- **/
        button_apply = new JButton( ctr.textHandler().button_apply() );
        button_apply.addActionListener( this );
        button_cancel = new JButton( ctr.textHandler().button_cancel() );
        button_cancel.addActionListener( this );
        p7 = new JPanel();
        p7.add(button_apply);
        p7.add(button_cancel);


        /** -----+-----++--- All the Panels ---++-----+----- **/
        panel.add( p1 );
        panel.add( p2 );
        panel.add( p3 );
        //panel.add( p4 );
        panel.add( p5 );
        panel.add( p6 );
        panel.add( p7 );

        this.add(panel);
        pack();
        setTitle(FlightBook.SETTINGS);
        this.setLocationRelativeTo(null);
        this.setVisible(false);
    }

    /**
     * Returns the path to the selected FlightBook...
     * @return
     */
    private String getPath() {
        return ctr.getPath((String) combobox_userselection.getSelectedItem());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println( e.getActionCommand() );
        if( e.getActionCommand().equalsIgnoreCase(ctr.textHandler().button_cancel())) {
            System.out.println("Exit Settings");
            ctr.setVisibility( FlightBook.VS_DEFAULT );
        }else if( e.getActionCommand().equalsIgnoreCase(ctr.textHandler().button_apply())) {
            System.out.println("Remember changes");
            int n = 0;
            if( checkbox_delete.isSelected() ) {
                Object[] options = new Object[]{"OK",
                        ctr.textHandler().button_cancel()};
                label_delete_warning = new JLabel( ctr.textHandler().label_delete_warning() );
                n = JOptionPane.showOptionDialog(this,
                        label_delete_warning,
                        ctr.textHandler().warning(),
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.WARNING_MESSAGE,
                        null,
                        options,
                        options[0]);
            }
            if( n==0 ) { //OK
                //TODO: Read settings.cfg and change what changed and save into file.
                //1.: Neuer Benutzer: Abfragen ob es ihn schon gibt, sonst neu anlegen, dialog bei leerem Verzeichnisfeld.
                //2.: Benutzer wechseln: Model laut settings.cfg anpassen
                //3.: Irgendwas ändern: anpassen.
                //4.: Löschen: Die Einträge aus dem settings.cfg löschen.
                //5.: Help Buttons
                String fb = (String) combobox_userselection.getSelectedItem();
                if (checkbox_delete.isSelected()) { //delete this book
                    ctr.deleteBook( fb );
                } else {
                    String newPath="";
                    String newUser=(String)combobox_userselection.getSelectedItem();
                    boolean exists = false;
                    for (String s : ctr.getBookUsers()) { //existiert ausgewählter user bereits, oder wurde er neu eingegeben?
                        if (s.equalsIgnoreCase(fb)) { //existiert bereits
                            exists = true;
                            break;
                        }
                    }
                    if (!exists) { //make new
                        //TODO: make new, reload settings, cfg anpassen...
                        File path = new File( textfield_path.getText() );
                        if( textfield_path.getText().equalsIgnoreCase("same path") ||
                                textfield_path.getText().equalsIgnoreCase("same") ||
                                textfield_path.getText().equalsIgnoreCase("selbes verzeichnis") ||
                                textfield_path.getText().equalsIgnoreCase("gleich") ||
                                textfield_path.getText().equalsIgnoreCase("selber Pfad") ||
                                textfield_path.getText().equalsIgnoreCase("gleicher Pfad") ||
                                textfield_path.getText().equalsIgnoreCase("gleiches verzeichnis") ) {
                            newPath = "";
                        }else if( textfield_path.getText().isEmpty() ) { //Kein pfad angegeben.
                            JOptionPane.showMessageDialog(this,
                                    ctr.textHandler().noPathTold());
                            return;
                        }else{ //Custom Path angegeben.
                            if( path.exists() ) {
                                newPath = textfield_path.getText();
                            }else {
                                JOptionPane.showMessageDialog(this,
                                        ctr.textHandler().pathNotExists());
                                return;
                            }
                        }
                    } else { //TODO: existiert schon: Änderungen übernehmen. Das was hier steht, muss im actionhandler von der combobox passieren, nicht hier.
                        try {
                            ctr.setSelectedBook(fb);
                            //textfield_path.setText(ctr.getPath(fb));
                            newPath = textfield_path.getText();
                        } catch (BookNotExistsException e1) {
                            e1.printStackTrace();
                            JOptionPane.showMessageDialog(this,
                                    ctr.textHandler().bookNotExists());
                        }
                    }


                }
                System.out.println("Settings wurden \u00FCbernommen und ins File gespeichert.");
                System.out.println("Exit Settings");
                ctr.setVisibility(FlightBook.VS_DEFAULT);
            }
        }
    }
}