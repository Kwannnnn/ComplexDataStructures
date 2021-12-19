package nl.saxion.cds.comparator;

import nl.saxion.cds.client.Address;
import nl.saxion.cds.client.Client;
import nl.saxion.cds.parcel.Parcel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AreaDescComparatorTest {
    private static final Client DUMMY_CLIENT = new Client(1L, "John Doe", "J.D.", new Address(1, 1));
    private static final Parcel BIG_PARCEL = new Parcel(1L, 50, 50, 50, 1, "19-12-2021", DUMMY_CLIENT);
    private static final Parcel SMALL_PARCEL = new Parcel(2L, 10, 10, 10, 1, "19-12-2021", DUMMY_CLIENT);

    private AreaDescComparator underTest;

    @BeforeEach
    void setUp() {
        this.underTest = new AreaDescComparator();
    }

    @Test
    @DisplayName("compare() - Parcel1 < Parcel2")
    void compareSmallerThan() {
        var result = this.underTest.compare(BIG_PARCEL, SMALL_PARCEL);
        assertTrue(result < 0);
    }

    @Test
    @DisplayName("compare() - Parcel1 == Parcel2")
    void compareEqual() {
        var result = this.underTest.compare(BIG_PARCEL, BIG_PARCEL);
        assertEquals(0, result);
    }

    @Test
    @DisplayName("compare() - Parcel1 > Parcel2")
    void compareGreaterThan() {
        var result = this.underTest.compare(SMALL_PARCEL, BIG_PARCEL);
        assertTrue(result > 0);
    }
}