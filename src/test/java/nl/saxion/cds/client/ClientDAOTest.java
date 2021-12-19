package nl.saxion.cds.client;

import nl.saxion.cds.client.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ClientDAOTest {
    private static final String CLIENTS_FILENAME = "Clients.csv";
    private ClientDAO underTest;

    @BeforeEach
    void setUp() {
        this.underTest = new ClientDAO();
        try {
            new ClientsCsvLoader(CLIENTS_FILENAME, new CreateClientService(this.underTest)).loadFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("get() - Existing client")
    void getExistingClient() {
        // given
        var id = 235001L;
        // when
        var client = underTest.get(id);
        // then
        assertNotNull(client);
        assertEquals(id, client.getId());
    }

    @Test
    @DisplayName("get() - Non-existing client")
    void getNonExistingClient() {
        // given
        var id = 1L;
        // when
        var client = underTest.get(id);
        // then
        assertNull(client);
    }

    @Test
    void getAll() {

    }

    @Test
    @DisplayName("save() - Save a client")
    void save() {
        // given
        var client = new Client(1L, "John Doe", "J.D.", new Address(0, 0));
        // when
        this.underTest.save(client);
        // then
        assertNotNull(this.underTest.get(client.getId()));
    }
}