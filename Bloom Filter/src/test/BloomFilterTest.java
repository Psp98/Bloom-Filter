/**
 * Simple Java JUnit class to test the Bloom Filter
 * data structure functions.
 */
package test;

import main.BloomFilter;
import org.junit.jupiter.api.Test;

public class BloomFilterTest {

    private final int expectedItemsCount = 100000;

    @Test
    /**
     * Tests the FNV Hash for given inputs
     */
    void testFNVHash() {
        BloomFilter FNVHashTester = new BloomFilter(expectedItemsCount, "FNV");
        FNVHashTester.add("15bit053@nirmauni.ac.in");
        FNVHashTester.add("98shahpreet@gmail.com");
        FNVHashTester.add("xyz.123@yahoo.in");
        FNVHashTester.add(12345);
        FNVHashTester.add(0.001133);

        assert (FNVHashTester.contains("15bit053@nirmauni.ac.in")); // success
        assert (FNVHashTester.contains("98shahpreet@gmail.com"));   // success
        assert (FNVHashTester.contains("xyz.123@yahoo.in"));    // success
        assert (FNVHashTester.contains(12345)); // success
        assert (FNVHashTester.contains(0.001133));  // success
        assert (FNVHashTester.contains("sdhgjfgjndfjbgdfjgjdfgj")); // fails
        assert (FNVHashTester.contains(21345555555L)); // fails
    }


    @Test
    /**
     * Tests the Murmur Hash for given inputs
     */
    void testMurmurHash() {
        BloomFilter MurmurHashTester = new BloomFilter(expectedItemsCount, "Murmur");
        MurmurHashTester.add("15bit053@nirmauni.ac.in");
        MurmurHashTester.add("98shahpreet@gmail.com");
        MurmurHashTester.add("xyz.123@yahoo.in");
        MurmurHashTester.add(12345);
        MurmurHashTester.add(0.001133);

        assert (MurmurHashTester.contains("15bit053@nirmauni.ac.in")); // success
        assert (MurmurHashTester.contains("98shahpreet@gmail.com"));   // success
        assert (MurmurHashTester.contains("xyz.123@yahoo.in"));    // success
        assert (MurmurHashTester.contains(12345)); // success
        assert (MurmurHashTester.contains(0.001133));  // success

        // This should fail but it doesn't..
        // Bloom Filter erroneously returned true.
        assert (MurmurHashTester.contains("0.001133"));  // success

        assert (MurmurHashTester.contains("sdhgjfgjndfjbgdfjgjdfgj")); // fails
        assert (MurmurHashTester.contains(21345555555L)); // fails
    }
}