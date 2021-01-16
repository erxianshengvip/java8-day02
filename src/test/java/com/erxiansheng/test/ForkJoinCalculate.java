package com.erxiansheng.test;

import java.math.BigInteger;
import java.util.concurrent.RecursiveTask;

public class ForkJoinCalculate extends RecursiveTask<BigInteger> {

    private BigInteger start;
    private BigInteger end;

    private static final BigInteger threshold= BigInteger.valueOf(10000L);

    public ForkJoinCalculate(BigInteger start, BigInteger end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected BigInteger compute() {
        BigInteger length=end.subtract(start);

        return null;
    }
}
