import java.math.BigInteger;

public class FNVHash implements Hash {

    private static final BigInteger INIT64 = new BigInteger("cbf29ce484222325", 16);
    private static final BigInteger PRIME64 = new BigInteger("100000001b3", 16);
    private static final BigInteger MOD64 = new BigInteger("2").pow(64);

    @Override
    /**
     * Generates 64 bit hash from byte array with default seed value.
     *
     * @param o Input object whose hash value needs to be calculated
     * @return 64 bit hash of the given input object
     */
    public long hash(Object o) {
        if (o == null)
            return 0;

        if (o instanceof Long)
            return hash(Long.toString((Long) o).getBytes());

        if (o instanceof Integer)
            return hash(Integer.toString((Integer) o).getBytes());

        if (o instanceof Double)
            return hash(Double.toString((Double) o).getBytes());

        if (o instanceof Float)
            return hash(Float.toString((Float) o).getBytes());

        if (o instanceof String)
            return hash(((String) o).getBytes());

        if (o instanceof byte[]) {
            final byte[] data = (byte[]) o;
            return fnv_64(data);
        }

        return hash(o.toString().getBytes());
    }

    /**
     * Generates 64 bit hash from byte array with default seed value.
     *
     * @param data byte array to hash
     * @return 64 bit hash of the input byte array
     */
    public long fnv_64(byte[] data) {
        BigInteger hash = INIT64;

        for (byte b : data) {
            hash = hash.multiply(PRIME64).mod(MOD64);
            hash = hash.xor(BigInteger.valueOf((int) b & 0xff));
        }

        return hash.longValue();
    }
}