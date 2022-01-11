package nl.saxion.cds.client;

import nl.saxion.cds.parcel.ParcelDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientServiceTest {
    private static final Client CLIENT_1 = new Client(1L, "John Doe", "J.D.", new Address(1, 1));
    private static final Client CLIENT_2 = new Client(2L, "Jane Doe", "J.D.", new Address(300, 300));

    private ClientService underTest;

    @BeforeEach
    void setUp() {
        var clientDAO = new ClientDAO();
        clientDAO.save(CLIENT_1);
        clientDAO.save(CLIENT_2);

        this.underTest = new ClientService(clientDAO);
    }

    @Test
    @DisplayName("getClient() - Existing client")
    void getExistingClient() {
        // given
        var id = 1L;
        // when
        var client = this.underTest.getClient(id);
        // then
        assertNotNull(client);
        assertEquals(CLIENT_1, client);
    }

    @Test
    @DisplayName("getClient() - Non-existing client")
    void getNonExistingClient() {
        // given
        var id = 1337L;
        // when
        var client = this.underTest.getClient(id);
        // then
        assertNull(client);
    }
}