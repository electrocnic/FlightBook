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
        this.lang = lang;
    }

    public String getLang() {
        return lang;
    }

    public String greeting() {
        if( lang.equalsIgnoreCase(DEUTSCH)) {
            return  "Hallo."+System.lineSeparator()+
                    "Sie benutzen FlightBook anscheinend das erste Mal?"+System.lineSeparator()+
                    "Als erstes, tragen Sie bitte ihren Namen ein (Vorname Nachname):";
        }else if( lang.equalsIgnoreCase(ENGLISH)) {
            return "Hello."+System.lineSeparator()+
                    "It seems You are using FlightBook for your first time?"+System.lineSeparator()+
                    "If so, please enter your full name at first:";
        }
        return null;
    }

    public String newFlightBook() {
        if( lang.equalsIgnoreCase(DEUTSCH)) {
            return "Neues Flugbuch anlegen";
        }else if( lang.equalsIgnoreCase(ENGLISH)) {
            return "Create New FlightBook";
        }
        return null;
    }

    public String samePath() {
        if( lang.equalsIgnoreCase(DEUTSCH)) {
            return "selbes Verzeichnis";
        }else if( lang.equalsIgnoreCase(ENGLISH)) {
            return "same Path";
        }
        return null;
    }

    public String choosePath() {
        if( lang.equalsIgnoreCase(DEUTSCH)) {
            return "Bitte geben Sie das Verzeichnis an, in welchem sich die igc-Dateien vom Vario befinden."
                    +System.lineSeparator()+
                    "Diese sollten in Ordnern nach dem jeweiligen Jahr und Monat sortiert sein."
                    +System.lineSeparator()+
                    "Selbes Verzeichnis bedeutet, dass diese Dateien im selben Verzeichnis wie FlightBook.jar liegen."
                    +System.lineSeparator()+" ";
        }else if( lang.equalsIgnoreCase(ENGLISH)) {
            return "Please choose the Path, where the igc-Files are stored."
                    +System.lineSeparator()+
                    "They should be sorted by years and months using folders and sub-folders."
                    +System.lineSeparator()+
                    "Same Path means, that the igc-files are located in the same directory as the FlightBook.jar"
                    +System.lineSeparator()+" ";
        }
        return null;
    }

    public String choosePathHeader() {
        if( lang.equalsIgnoreCase(DEUTSCH)) {
            return "Flugdateien Ablageverzeichnis";
        }else if( lang.equalsIgnoreCase(ENGLISH)) {
            return "Files Directory";
        }
        return null;
    }

    public String openingHint() {
        if( lang.equalsIgnoreCase(DEUTSCH)) {
            return "Sie haben Ihr erstes Flugbuch nun angelegt!" + System.lineSeparator() +
                    "Sie k\u00F6nnen sp\u00E4ter unter den Einstellungen jederzeit" + System.lineSeparator() +
                    "weitere Flugb\u00FCcher hinzuf\u00FCgen!";
        }else if( lang.equalsIgnoreCase(ENGLISH)) {
            return "Your first Flight book has been created!" + System.lineSeparator() +
                    "You can add more books in the settings if needed at any time.";
        }
        return null;
    }

    public String text() {
        if( lang.equalsIgnoreCase(DEUTSCH)) {
            return "";
        }else if( lang.equalsIgnoreCase(ENGLISH)) {
            return "";
        }
        return null;
    }

}
