package nl.saxion.cds.parcel;

import nl.saxion.cds.client.ClientDAO;
import nl.saxion.cds.client.ClientsCsvLoader;
import nl.saxion.cds.client.CreateClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ParcelServiceTest {
    private static final String PARCELS_FILENAME = "Packages.csv";
    private static final String CLIENTS_FILENAME = "Clients.csv";

    private static final String STATUS_DC = "DC";

    private ParcelDAO parcelDAO;
    private ClientDAO clientDAO;
    private ParcelService underTest;

    @BeforeEach
    void setUp() {
        this.clientDAO = new ClientDAO();
        this.parcelDAO = new ParcelDAO();
        try {
            new ClientsCsvLoader(CLIENTS_FILENAME, new CreateClientService(this.clientDAO)).loadFile();
            new ParcelsCsvLoader(PARCELS_FILENAME, new CreateParcelService(this.clientDAO, this.parcelDAO)).loadFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.underTest = new ParcelService(this.parcelDAO);
    }

    @Test
    @DisplayName("getParcelStatus() - Existing parcel")
    void getExistingParcelStatus() {
        // given
        var id = "13582";
        // when
        var parcelStatus = this.underTest.getParcelStatus(id);
        // then
        assertEquals(parcelStatus, STATUS_DC);
    }

    @Test
    @DisplayName("getParcelStatus() - Non-existing parcel")
    void getNonExistingParcelStatus() {
        // given
        var id = "1";
        // when
        var parcelStatus = this.underTest.getParcelStatus(id);
        // then
        assertEquals(parcelStatus, "Parcel with " + id + " not found!");
    }

    @Test
    void getAllParcelIDs() {
    }
}