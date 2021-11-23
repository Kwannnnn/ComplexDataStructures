package resource;

import model.Address;
import model.Client;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public record ClientParser(String filePath, String delimiter) {

    public HashMap<Long, Client> getAllClients() {
        var result = new HashMap<Long, Client>();
        try {
            var bufferedReader = new BufferedReader(new FileReader(this.filePath));

            String currentLine = bufferedReader.readLine(); // Skip header line
            String[] lineValues;
            while ((currentLine = bufferedReader.readLine()) != null) {
                lineValues = currentLine.split(this.delimiter);

                var clientAddress = new Address(
                        Integer.parseInt(lineValues[3]),
                        Integer.parseInt(lineValues[4])
                );

                var client = new Client(
                        Long.parseLong(lineValues[0]),
                        lineValues[1],
                        lineValues[2],
                        clientAddress
                );

                result.put(Long.parseLong(lineValues[0]), client);
            }
        } catch (FileNotFoundException e) {
            throw new Error("No such file: " + this.filePath);
        } catch (IOException e) {
            throw new Error("I/O error: " + e);
        }

        return result;
    }
}
