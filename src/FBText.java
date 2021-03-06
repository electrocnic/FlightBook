/**
 * Created by Andreas on 19.07.2015.
 */
public class FBText {

    public static final String DEUTSCH = "DEUTSCH";
    public static final String ENGLISH = "ENGLISH";

    private FlightBook ctr = null;
    private String lang = null;

    public FBText( FlightBook controller ) {
        ctr = controller;
    }

    public void setLang(String lang) {
        if( DEUTSCH.equalsIgnoreCase( lang ) || ENGLISH.equalsIgnoreCase( lang )) this.lang = lang;
        else this.lang = ENGLISH;
    }

    public String getLang() {
        return lang;
    }

    public String greeting_newFlightBook_text() {
        if( lang.equalsIgnoreCase(DEUTSCH)) {
            return  "Hallo."+System.lineSeparator()+
                    "Sie benutzen FlightBook anscheinend das erste Mal?"+System.lineSeparator()+
                    "Als erstes, tragen Sie bitte ihren Namen ein (Vorname Nachname):";
        }else {
            return "Hello."+System.lineSeparator()+
                    "It seems You are using FlightBook for your first time?"+System.lineSeparator()+
                    "If so, please enter your full name at first:";
        }
    }

    public String greeting_newFlightBook_title() {
        if( lang.equalsIgnoreCase(DEUTSCH)) {
            return "Neues Flugbuch anlegen";
        }else {
            return "Create New FlightBook";
        }
    }

    public String generalPurpose_samePath() {
        if( lang.equalsIgnoreCase(DEUTSCH)) {
            return "selbes Verzeichnis";
        }else {
            return "same Path";
        }
    }

    public String greeting_choosePath_text() {
        if( lang.equalsIgnoreCase(DEUTSCH)) {
            return "Bitte geben Sie das Verzeichnis an, in welchem sich die igc-Dateien vom Vario befinden."
                    +System.lineSeparator()+
                    "Diese sollten in Ordnern nach dem jeweiligen Jahr und Monat sortiert sein."
                    +System.lineSeparator()+
                    "Selbes Verzeichnis bedeutet, dass diese Dateien im selben Verzeichnis wie FlightBook.jar liegen."
                    +System.lineSeparator()+" ";
        }else{
            return "Please choose the Path, where the igc-Files are stored."
                    +System.lineSeparator()+
                    "They should be sorted by years and months using folders and sub-folders."
                    +System.lineSeparator()+
                    "Same Path means, that the igc-files are located in the same directory as the FlightBook.jar"
                    +System.lineSeparator()+" ";
        }
    }

    public String greeting_choosePath_title() {
        if( lang.equalsIgnoreCase(DEUTSCH)) {
            return "Flugdateien Ablageverzeichnis";
        }else{
            return "Files Directory";
        }
    }

    public String greeting_finished_text() {
        if( lang.equalsIgnoreCase(DEUTSCH)) {
            return "Sie haben Ihr erstes Flugbuch nun angelegt!" + System.lineSeparator() +
                    "Sie k\u00F6nnen sp\u00E4ter unter den Einstellungen jederzeit" + System.lineSeparator() +
                    "weitere Flugb\u00FCcher hinzuf\u00FCgen!";
        }else{
            return "Your first Flight book has been created!" + System.lineSeparator() +
                    "You can add more books in the settings if needed at any time.";
        }
    }

    public String generalPurpose_settingsCorrupt() {
        if( lang.equalsIgnoreCase(DEUTSCH)) {
            return "Die Datei settings.cfg ist fehlerhaft und kann nicht gelesen werden!";
        }else{
            return "The file settings.cfg is corrupted and cannot be read!";
        }
    }

    public String label_userselection() {
        if( lang.equalsIgnoreCase(DEUTSCH)) {
            return "Flugbuch Auswahl:";
        }else{
            return "Flightbook Selection:";
        }
    }

    public String label_choosePath() {
        if( lang.equalsIgnoreCase(DEUTSCH)) {
            return "Suchpfad f\u00FCr Flugdateien:";
        }else{
            return "Path for igc-files:";
        }
    }

    public String label_language() {
        if( lang.equalsIgnoreCase(DEUTSCH)) {
            return "Sprache:";
        }else{
            return "Language:";
        }
    }

    public String label_delete() {
        if( lang.equalsIgnoreCase(DEUTSCH)) {
            return "Ausgew\u00E4hltes Flugbuch l\u00F6schen";
        }else{
            return "Delete this FlightBook";
        }
    }

    public String button_apply() {
        if( lang.equalsIgnoreCase(DEUTSCH)) {
            return "\u00DCbernehmen";
        }else{
            return "Apply";
        }
    }

    public String button_cancel() {
        if( lang.equalsIgnoreCase(DEUTSCH)) {
            return "Abbrechen";
        }else{
            return "Cancel";
        }
    }

    public String generalPurpose_warning() {
        if( lang.equalsIgnoreCase(DEUTSCH)) {
            return "Achtung!";
        }else{
            return "Warning!";
        }
    }

    public String label_delete_warning() {
        if( lang.equalsIgnoreCase(DEUTSCH)) {
            return "Alle (manuellen) Eintr\u00E4ge gehen verloren!!!";
        }else{
            return "All (manual) entries are going to be deleted!!!";
        }
    }

    public String generalPurpose_bookNotExists() {
        if( lang.equalsIgnoreCase(DEUTSCH)) {
            return "Diese Buch existiert nicht.";
        }else{
            return "This book does not exist.";
        }
    }

    public String generalPurpose_noPathTold() {
        if( lang.equalsIgnoreCase(DEUTSCH)) {
            return "Es wurde kein Pfad angegeben!" + System.lineSeparator() +"Die Einstellungen wurden noch nicht \u00FCbernommen!";
        }else{
            return "No path is given yet!" + System.lineSeparator() + "The settings have not been saved yet!";
        }
    }

    public String generalPurpose_pathNotExists() {
        if( lang.equalsIgnoreCase(DEUTSCH)) {
            return "Der angegebene Pfad existiert nicht."+System.lineSeparator()
                    +"Bitte legen Sie den Pfad mithilfe des Explorers manuell an!";
        }else{
            return "The given path does not exist yet."+System.lineSeparator()+
                    "Please create the directory manually via the explorer!";
        }
    }

    public String generalPurpose_noPathSaved() {
        if( lang.equalsIgnoreCase(DEUTSCH)) {
            return "Kein Pfad gespeichert";
        }else{
            return "No path saved";
        }
    }

    public String button_help1_text() {
        if( lang.equalsIgnoreCase(DEUTSCH)) {
            return "Wenn Sie ein neues Buch zus\u00E4tzlich anlegen wollen," + System.lineSeparator()
                    + " geben Sie einfach hier einen neuen Namen ein, der aktuelle Eintrag bleibt erhalten.";
        }else{
            return "If you want to add a new Book, just type in a new name, the old one won't be deleted!";
        }
    }

    public String button_help2_text() {
        if( lang.equalsIgnoreCase(DEUTSCH)) {
            return "Die igc-Dateien werden NICHT gel\u00F6scht! Die manuellen Fl\u00FCge gehen aber verloren!!";
        }else{
            return "The igc-Files WON'T be deleted! The manual flight-entries, however, WILL!";
        }
    }

    public String button_manual_entry() {
        if( lang.equalsIgnoreCase(DEUTSCH)) {
            return "Manueller Flugeintrag";
        }else{
            return "Manual Entry";
        }
    }

    public String button_view_entries() {
        if( lang.equalsIgnoreCase(DEUTSCH)) {
            return "Flugbuch ansehen";
        }else{
            return "View FlightBook";
        }
    }

    public String button_refresh_data() {
        if( lang.equalsIgnoreCase(DEUTSCH)) {
            return "Refresh";
        }else{
            return "Refresh";
        }
    }

    public String text() {
        if( lang.equalsIgnoreCase(DEUTSCH)) {
            return "";
        }else{
            return "";
        }
    }

}
