package resource;

import model.Package;

import java.io.*;
import java.util.ArrayList;

public class PackageParser {
    private final String filePath;
    private final String delimiter;

    public PackageParser(String filePath, String delimiter) {
        this.filePath = filePath;
        this.delimiter = delimiter;
    }

    public ArrayList<Package> getAllPackages() {
        var result = new ArrayList<Package>();
        try {
            var bufferedReader = new BufferedReader(new FileReader(this.filePath));

            String currentLine = bufferedReader.readLine(); // Skip header line
            String[] lineValues;
            while ((currentLine = bufferedReader.readLine()) != null) {
                lineValues = currentLine.split(this.delimiter);

                var aPackage = new Package(
                        Long.parseLong(lineValues[0]),
                        Integer.parseInt(lineValues[1]),
                        Integer.parseInt(lineValues[2]),
                        Integer.parseInt(lineValues[3]),
                        Double.parseDouble(lineValues[4]),
                        lineValues[5],
                        lineValues[6]
                );

                result.add(aPackage);
            }
        } catch (FileNotFoundException e) {
            throw new Error("No such file: " + this.filePath);
        } catch (IOException e) {
            throw new Error("I/O error: " + e);
        }

        return result;
    }
}
