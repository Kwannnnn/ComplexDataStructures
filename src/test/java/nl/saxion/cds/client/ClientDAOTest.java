package nl.saxion.cds.client;

import nl.saxion.cds.parcel.Parcel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientDAOTest {
    private static final Client CLIENT_1 = new Client(1L, "John Doe", "J.D.", new Address(1, 1));
    private static final Client CLIENT_2 = new Client(2L, "Jane Doe", "J.D.", new Address(300, 300));

    private ClientDAO underTest;

    @BeforeEach
    void setUp() {
        this.underTest = new ClientDAO();
    }

    @Test
    @DisplayName("get() - Existing client")
    void getExistingClient() {
        // First add a client
        this.underTest.save(CLIENT_1);
        assertNotNull(this.underTest.get(CLIENT_1.getId()));

        // given
        var id = 1L;
        // when
        var clientToGet = underTest.get(id);
        // then
        assertNotNull(CLIENT_1);
        assertEquals(CLIENT_1, clientToGet);
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
    @DisplayName("getAll() - Empty data object")
    void getAllWhenDAOIsEmpty() {
        // when
        var parcels = this.underTest.getAll();
        // then
        assertEquals(0, parcels.size());
    }

    @Test
    @DisplayName("getAll() - Non-empty data object")
    void getAll() {
        this.underTest.save(CLIENT_1);
        this.underTest.save(CLIENT_2);
        assertNotNull(this.underTest.get(CLIENT_1.getId()));
        assertNotNull(this.underTest.get(CLIENT_2.getId()));

        // when
        var parcels = this.underTest.getAll();
        // then
        assertEquals(2, parcels.size());
    }
}