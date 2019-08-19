package com.smile.math;

/**
 * ${DESCRIPTION}
 *
 * @author: ayuan
 * @create: 2019-01-30 14:48
 */
public class BitOperation {
    // int占4个字符，4*8=32位
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY = (1 << COUNT_BITS) - 1;

    private static final int RUNNING = -1 << COUNT_BITS;
    private static final int SHUTDOWN = 0 << COUNT_BITS;
    private static final int STOP = 1 << COUNT_BITS;
    private static final int TIDYING = 2 << COUNT_BITS;
    private static final int TERMINATED = 3<< COUNT_BITS;

    private static int runStateOf(int c){ return c & ~CAPACITY; }

    public static void main(String[] args) {
        System.out.println(CAPACITY);
        System.out.println(RUNNING);

        System.out.println(Float.MIN_VALUE);
        System.out.println(Float.MIN_NORMAL);

        double a = 1.0-0.9;
        System.out.println(a);
        float b = 1.0f-0.9f;
        System.out.println(b);
        System.out.println(Double.toHexString(1.0));
        System.out.println(Double.toHexString(0.9));
    }
}
