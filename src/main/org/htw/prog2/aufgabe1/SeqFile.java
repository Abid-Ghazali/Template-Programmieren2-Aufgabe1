package org.htw.prog2.aufgabe1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class SeqFile {

    private HashSet<String> sequences = new HashSet<>();
    private String firstSequence = "";
    private boolean valid = false;

    /**
     * Reads the specified FASTA file and stores sequences. In case the file does not exist or is not a valid FASTA
     * file, the Constructor does not throw an Exception. Instead, isValid() on the resulting object will return false.
     * @param filename
     */
    public SeqFile(String filename) {
        valid = readFile(filename);
    }

    /**
     * Reads the specified FASTA file.
     * @param filename The path to the FASTA file
     * @return false if the file could not be parsed (wrong format, does not exist), true otherwise.
     */
    private boolean readFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

            String line;
            StringBuilder currentSeq = new StringBuilder();
            boolean hasHeader = false;

            while ((line = br.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty()) continue;

                if (line.startsWith(">")) {
                    if (hasHeader && currentSeq.length() > 0) {
                        addSequence(currentSeq);
                        currentSeq = new StringBuilder();
                    }
                    hasHeader = true;
                } else {
                    if (!hasHeader) {
                        return false;
                    }
                    currentSeq.append(line);
                }
            }

            if (currentSeq.length() > 0) {
                addSequence(currentSeq);
            }

            return true;

        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Adds the sequence in the passed StringBuilder to the internal hash set and also sets the first sequence if it
     * is still empty.
     * @param seq SequenceBuilder to get the sequence from.
     * @return The length of the added sequence.
     */
    private int addSequence(StringBuilder seq) {
        String s = seq.toString();
        sequences.add(s);

        if (firstSequence.isEmpty()) {
            firstSequence = s;
        }

        return s.length();
    }

    /**
     *
     * @return The number of sequences read from the FASTA file, or 0 if isValid() is false.
     */
    public int getNumberOfSequences() {
        if (valid){
            return sequences.size();
        }
        else{
            return 0;
        }
    }

    /**
     *
     * @return The sequences read from the FASTA file, or an empty HashSet if isValid() is false.
     */
    public HashSet<String> getSequences() {
        if (valid){
            return sequences;
        }
        else{
            return new HashSet<>();
        }
    }

    /**
     *
     * @return The first sequence read from the FASTA file, or an empty String if isValid() is false.
     */
    public String getFirstSequence() {
        if (valid){
            return firstSequence;
        }
        else{
            return "";
        }
    }

    /**
     *
     * @return true if the FASTA file was read successfully, false otherwise.
     */
    public boolean isValid() {
        return valid;
    }
}