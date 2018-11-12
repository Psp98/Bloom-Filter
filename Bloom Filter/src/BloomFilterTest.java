/**
 * Simple Java JUnit class to test the Bloom Filter
 * data structure functions.
 */

import org.junit.jupiter.api.Test;

public class BloomFilterTest {

    private int expectedItemsCount = 100000;

    @Test
    /**
     * Tests the FNV Hash for given inputs
     */
    public void testFNVHash() {
        BloomFilter FNVHashTester = new BloomFilter(expectedItemsCount, "FNV");
        FNVHashTester.add("15bit053@nirmauni.ac.in");
        FNVHashTester.add("98shahpreet@gmail.com");
        FNVHashTester.add("xyz.123@yahoo.in");
        FNVHashTester.add(12345);
        FNVHashTester.add(0.001133);

        assert (FNVHashTester.contains("15bit053@nirmauni.ac.in"));
        assert (FNVHashTester.contains("98shahpreet@gmail.com"));
        assert (FNVHashTester.contains("xyz.123@yahoo.in"));
        assert (FNVHashTester.contains("12345"));
        assert (FNVHashTester.contains("0.001133"));
    }


    @Test
    /**
     * Tests the Murmur Hash for given inputs
     */
    public void testMurmurHash() {
        BloomFilter MurmurHashTester = new BloomFilter(expectedItemsCount, "Murmur");
        MurmurHashTester.add("15bit053@nirmauni.ac.in");
        MurmurHashTester.add("98shahpreet@gmail.com");
        MurmurHashTester.add("xyz.123@yahoo.in");
        MurmurHashTester.add(12345);
        MurmurHashTester.add(0.001133);

        assert (MurmurHashTester.contains("15bit053@nirmauni.ac.in"));
        assert (MurmurHashTester.contains("98shahpreet@gmail.com"));
        assert (MurmurHashTester.contains("xyz.123@yahoo.in"));
        assert (MurmurHashTester.contains("12345"));
        assert (MurmurHashTester.contains("0.001133"));
    }
}