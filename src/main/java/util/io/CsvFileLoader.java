package util.io;

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
    public final void loadFile() throws IOException {
        var reader = new BufferedReader(new FileReader(getResourceFile()));

        String currentLine = reader.readLine(); // Skip header line
        while ((currentLine = reader.readLine()) != null) {
            var args = currentLine.split(";");
            addToManager(args);
        }
    }

    protected abstract void addToManager(String[] args);

    private String getResourceFile() throws FileNotFoundException {
        URL printResource = getClass().getResource("/" + this.filename);
        if (printResource == null) {
            throw new FileNotFoundException("Warning: Could not find " + this.filename + " file!");
        }

        return printResource.getFile();
    }
}
