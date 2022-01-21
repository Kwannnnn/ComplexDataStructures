package nl.saxion.cds.io;

import nl.saxion.cds.exception.RecordNotLoadedException;

import java.io.IOException;

public interface FileLoader {
    void loadFile() throws IOException, RecordNotLoadedException;
}
