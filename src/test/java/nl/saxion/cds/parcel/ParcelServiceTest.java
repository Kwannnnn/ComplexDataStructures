package nl.saxion.cds.parcel;

import nl.saxion.cds.client.*;
import nl.saxion.cds.region.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParcelServiceTest {
    private static final Client CLIENT_1 = new Client(1L, "John Doe", "J.D.", new Coordinate(1, 1));
    private static final Client CLIENT_2 = new Client(2L, "Jane Doe", "J.D.", new Coordinate(300, 300));
    private static final Parcel PARCEL_1 = new Parcel(1337L, 1, 1, 1, 1, "19-12-2021", CLIENT_1);
    private static final Parcel PARCEL_2 = new Parcel(1338L, 2, 2, 2, 2, "20-12-2021", CLIENT_2);

    private static final String STATUS_DC = "DC";

    private ParcelService underTest;

    @BeforeEach
    void setUp() {
        var parcelDAO = new ParcelDAO();
        parcelDAO.save(PARCEL_1);
        parcelDAO.save(PARCEL_2);

        this.underTest = new ParcelService(parcelDAO);
    }

    @Test
    @DisplayName("getParcelStatus() - Existing parcel")
    void getExistingParcelStatus() {
        // given
        var id = "1337";
        // when
//        var parcelStatus = this.underTest.getParcelStatus(id);
        // then
//        assertEquals(parcelStatus, STATUS_DC);
    }

    @Test
    @DisplayName("getParcelStatus() - Non-existing parcel")
    void getNonExistingParcelStatus() {
        // TODO: check for thrown exception
        // given
        var id = "1";
        // when
//        var parcelStatus = this.underTest.getParcelStatus(id);
        // then
//        assertEquals(parcelStatus, "Parcel with " + id + " not found!");
    }

    @Test
    @DisplayName("getAllParcelIDs()")
    void getAllParcelIDs() {
        // when
//        var parcelIDs = this.underTest.getAllParcelIDs();
//        var parcel1ID = parcelIDs.get(0);
//        var parcel2ID = parcelIDs.get(1);
        // then
//        assertEquals(PARCEL_1.getId().toString(), parcel1ID);
//        assertEquals(PARCEL_2.getId().toString(), parcel2ID);
    }
}