package nl.saxion.cds.parcel;

import nl.saxion.cds.client.Address;
import nl.saxion.cds.client.Client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParcelDAOTest {
    private static final Client CLIENT_1 = new Client(1L, "John Doe", "J.D.", new Address(1, 1));
    private static final Client CLIENT_2 = new Client(2L, "Jane Doe", "J.D.", new Address(300, 300));

    private ParcelDAO underTest;

    @BeforeEach
    void setUp() {
        this.underTest = new ParcelDAO();
    }

    @Test
    @DisplayName("save() - Save a parcel")
    void saveAValidParcel() {
        // given
        var parcel = new Parcel(1L, 1, 1, 1, 1, "19-12-2021", CLIENT_1);
        // when
        this.underTest.save(parcel);
        // then
        assertNotNull(this.underTest.get(parcel.getId()));
    }

    @Test
    @DisplayName("get() - Existing parcel")
    void getExistingParcel() {
        // Create an existing parcel
        var parcel = new Parcel(1337L, 1, 1, 1, 1, "19-12-2021", CLIENT_1);
        this.underTest.save(parcel);
        assertNotNull(this.underTest.get(parcel.getId()));

        // given
        var id = 1337L;
        // when
        var parcelToGet = this.underTest.get(id);
        // then
        assertNotNull(parcelToGet);
        assertEquals(parcel, parcelToGet);
    }

    @Test
    @DisplayName("get() - Non-existing parcel")
    void getNonExistingParcel() {
        // given
        var id = 1337L;
        // when
        var parcel = underTest.get(id);
        // then
        assertNull(parcel);
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
        // Create a couple existing parcel
        var parcel1 = new Parcel(1337L, 1, 1, 1, 1, "19-12-2021", CLIENT_1);
        var parcel2 = new Parcel(1338L, 2, 2, 2, 2, "20-12-2021", CLIENT_2);
        this.underTest.save(parcel1);
        this.underTest.save(parcel2);
        assertNotNull(this.underTest.get(parcel1.getId()));
        assertNotNull(this.underTest.get(parcel2.getId()));

        // when
        var parcels = this.underTest.getAll();
        // then
        assertEquals(2, parcels.size());
    }
}