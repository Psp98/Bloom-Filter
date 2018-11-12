/**
 * A Bloom filter offers an approximate containment test
 * with one-sided error: if it claims that an element is contained in it, this might be in error,
 * but if it claims that an element is not contained in it, then this is definitely true.
 *
 * If you are unfamiliar with Bloom filter, this nice
 * <a href="http://llimllib.github.com/bloomfilter-tutorial/"> tutorial </a>
 * may help you understand how they work.
 *
 * References:
 * <a href="https://github.com/google/guava/blob/master/guava/src
 * /com/google/common/hash/BloomFilter.java"> Reference 1</a>
 *
 * <a href="https://www.geeksforgeeks.org/bloom-filters-introduction-
 * and-python-implementation/"> Reference 2</a>
 *
 * @author Preet Shah
 */
public class BloomFilter {

    private static final double FALSE_POSITIVE_PROBABILITY = 0.05;
    private boolean bitArray[];
    private int size, hashCount;
    private Hash hash;

    public BloomFilter(int itemsCount, String hashType) {
        initialize(itemsCount);

        if (hashType.equals("FNV")) {
            hash = new FNVHash();
        }
        else {
            hash = new MurmurHash();
        }
    }

    /**
     * Initialized the size (based on expected input items count), hashCount and bitArray
     *
     * @param itemsCount Expected count of the items to be given as an input
     * @return void
     */
    private void initialize(int itemsCount) {
        size = getSize(itemsCount);
        hashCount = getHashFunctionsCount(itemsCount, size);
        bitArray = new boolean[size];
    }

    /**
     * Calculates the size of the bitArray based on expected count of input items
     *
     * @param itemsCount Expected count of the items to be given as an input
     * @return size of the bitArray
     */
    private int getSize(int itemsCount) {
        double size = -(itemsCount * Math.log(FALSE_POSITIVE_PROBABILITY))
                / (Math.log(2) * Math.log(2));

        return (int) size;
    }

    /**
     * Generates count of hashes needed for each element
     *
     * @param itemsCount Expected count of the items to be given as an input
     * @param size size of the bitArray
     * @return number of hashes per element
     */
    private int getHashFunctionsCount(int itemsCount, int size) {
        double hashCount = (size / itemsCount) * Math.log(2);

        return (int) hashCount;
    }

    /**
     * Add the input item to the Bloom Filter data structure
     *
     * @param item Input item that needs to be added to Bloom Filter
     * @return void
     */
    public void add(Object item) {
        for (int i = 0; i < hashCount; i++) {
            long hashValue = hash.hash(item);
            hashValue &= Long.MAX_VALUE;
            int position = (int) (hashValue % (long) size);
            bitArray[position] = true;
        }
    }

    /**
     * Check if the item is contained in Bloom Filter
     *
     * @param item Input item that needs to be added to Bloom Filter
     * @return true, if Bloom Filter contains the item else false
     */
    public boolean contains(Object item) {
        for (int i = 0; i < hashCount; i++) {
            long hashValue = hash.hash(item);
            hashValue &= Long.MAX_VALUE;
            int position = (int) (hashValue % (long) size);

            if (!bitArray[position]) {
                return false;
            }
        }

        return true;
    }
}