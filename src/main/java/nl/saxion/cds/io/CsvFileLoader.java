package nl.saxion.cds.io;

import nl.saxion.cds.exception.RecordNotLoadedException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public abstract class CsvFileLoader implements FileLoader {
    private final String filename;

    public CsvFileLoader(String filename) {
        this.filename = filename;
    }

    @Override
    public final void loadFile() throws IOException, RecordNotLoadedException {
        var reader = new BufferedReader(new FileReader(getResourceFile()));

        String currentLine = reader.readLine(); // Skip header line
        while ((currentLine = reader.readLine()) != null) {
            var args = currentLine.split(";");
            save(args);
        }
    }

    protected abstract void save(String[] args) throws RecordNotLoadedException;

    private String getResourceFile() throws FileNotFoundException {
        URL printResource = getClass().getResource("/" + this.filename);
        if (printResource == null) {
            throw new FileNotFoundException("Warning: Could not find " + this.filename + " file!");
        }

        return printResource.getFile();
    }
}
