package nl.saxion.cds.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ClientServiceTest {
    private static final String CLIENTS_FILENAME = "Clients.csv";
    private ClientDAO clientDAO;
    private ClientService underTest;

    @BeforeEach
    void setUp() {
        this.clientDAO = new ClientDAO();
        try {
            new ClientsCsvLoader(CLIENTS_FILENAME, new CreateClientService(this.clientDAO)).loadFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.underTest = new ClientService(this.clientDAO);
    }

    @Test
    @DisplayName("getClient() - Existing client")
    void getExistingClient() {
        // given
        var id = 235006L;
        // when
        var client = this.underTest.getClient(id);
        // then
        assertNotNull(client);
        assertEquals(client.getId(), id);
    }

    @Test
    @DisplayName("getClient() - Non-existing client")
    void getNonExistingClient() {
        // given
        var id = 1L;
        // when
        var client = this.underTest.getClient(id);
        // then
        assertNull(client);
    }
}