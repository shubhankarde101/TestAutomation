package com.apple.phoenix.MyCode;

import java.math.BigInteger;

abstract class shape {

    private int surface;

    public shape() {
        this.surface = computeSurface();
    }
    protected abstract int computeSurface();

    public int getSurface() {
        return this.surface;
    }
}

class square extends shape {
    private int size;

    public square(int size) {
        this.size = size;
    }

    @Override
    protected int computeSurface() {
        return size * size;
    }
}

class BigIntegerTest {

    public static void main(String[] args) {
        java.math.BigInteger bi = new java.math.BigInteger("123445554454545445444434434434533233479988765566888888800980000");
        bi = bi.add(bi);
        System.out.println(bi);

        shape sq = new square(10);
        System.out.println(sq.getSurface());
    }
}

