package org.htw.prog2.aufgabe1;
import org.apache.commons.cli.*;

public class HIVDiagnostics {

    /**
     * Parst die Kommandozeilenargumente. Gibt null zurück, falls:
     * <ul>
     *     <li>Ein Fehler beim Parsen aufgetreten ist (z.B. eins der erforderlichen Argumente nicht angegeben wurde)</li>
     *     <li>Bei -m, -d und -r nicht die gleiche Anzahl an Argumenten angegeben wurde</li>
     * </ul>
     * @param args Array mit Kommandozeilen-Argumenten
     * @return CommandLine-Objekt mit geparsten Optionen
     */

    public static CommandLine parseOptions(String[] args) {
        Options options = new Options();

        options.addOption("m", true, "");
        options.addOption("d", true, "");
        options.addOption("r", true, "");
        options.addOption("p", true, "");

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();

        try {
            CommandLine cmd = parser.parse(options, args);

            String m = cmd.getOptionValue("m");
            String d = cmd.getOptionValue("d");
            String r = cmd.getOptionValue("r");
            String p = cmd.getOptionValue("p");

            if (m == null || d == null || r == null || p == null) {
                formatter.printHelp("HIVDiagnostics", options);
                return null;
            }

            return cmd;

        } catch (ParseException e) {
            formatter.printHelp("HIVDiagnostics", options);
            return null;
        }
    }

    public static void main(String[] args) {
    }
}
