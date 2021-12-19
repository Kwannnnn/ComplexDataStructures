package nl.saxion.cds.comparator;

import nl.saxion.cds.client.Address;
import nl.saxion.cds.client.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DistanceComparatorTest {
    private static final Client CLOSE_TO_DC = new Client(1L, "John Doe", "J.D.", new Address(374, 374));
    private static final Client FAR_FROM_DC = new Client(1L, "John Doe", "J.D.", new Address(1, 1));
    private static final Client FAR_FROM_DC_1 = new Client(2L, "John Doe", "J.D.", new Address(1, 1));

    private DistanceComparator underTest;

    @BeforeEach
    void setUp() {
        this.underTest = new DistanceComparator();
    }

    @Test
    @DisplayName("compare() - Client 1 closer to DC")
    void compareSmallerThan() {
        var result = this.underTest.compare(CLOSE_TO_DC, FAR_FROM_DC);
        assertTrue(result < 0);
    }

    @Test
    @DisplayName("compare() - Equally far")
    void compareEqual() {
        var result = this.underTest.compare(FAR_FROM_DC, FAR_FROM_DC_1);
        assertEquals(0, result);
    }

    @Test
    @DisplayName("compare() - Client 2 closer to DC")
    void compareGreaterThan() {
        var result = this.underTest.compare(FAR_FROM_DC, CLOSE_TO_DC);
        assertTrue(result > 0);
    }
}