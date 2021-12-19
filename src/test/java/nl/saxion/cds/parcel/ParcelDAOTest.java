package nl.saxion.cds.parcel;

import nl.saxion.cds.client.ClientDAO;
import nl.saxion.cds.parcel.CreateParcelService;
import nl.saxion.cds.parcel.Parcel;
import nl.saxion.cds.parcel.ParcelDAO;
import nl.saxion.cds.parcel.ParcelsCsvLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ParcelDAOTest {
    private static final String PARCELS_FILENAME = "Packages.csv";
    private ParcelDAO underTest;
    private ClientDAO clientDAO;

    @BeforeEach
    void setUp() {
        this.underTest = new ParcelDAO();
        this.clientDAO = new ClientDAO();
        try {
            new ParcelsCsvLoader(PARCELS_FILENAME, new CreateParcelService(this.clientDAO, this.underTest)).loadFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("get() - Existing parcel")
    void getExistingParcel() {
        // given
        var id = 13582L;
        // when
        var parcel = underTest.get(id);
        // then
        assertNotNull(parcel);
        assertEquals(id, parcel.getId());
    }

    @Test
    @DisplayName("get() - Non-existing parcel")
    void getNonExistingParcel() {
        // given
        var id = 1L;
        // when
        var parcel = underTest.get(id);
        // then
        assertNull(parcel);
    }

    @Test
    void getAll() {

    }

    @Test
    @DisplayName("save() - Save a parcel")
    void save() {
        // given
        var parcel = new Parcel(1L, 1, 1, 1, 1, "19-12-2021", clientDAO.get(235001L));
        // when
        this.underTest.save(parcel);
        // then
        assertNotNull(this.underTest.get(parcel.getId()));
    }
}