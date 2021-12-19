package nl.saxion.cds.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateClientServiceTest {
    private ClientDAO clientDAO;
    private CreateClientService underTest;

    @BeforeEach
    void setUp() {
        this.clientDAO = new ClientDAO();
        this.underTest = new CreateClientService(this.clientDAO);
    }

    @Test
    @DisplayName("createClient()")
    void createClient() {
        // given
        var id = 1L;
        var name = "John Doe";
        var initials = "J.D.";
        var addressX = 1;
        var addressY = 1;
        // when
        this.underTest.createClient(id, name, initials, addressX, addressY);
        // then
        assertNotNull(this.clientDAO.get(id));
    }
}