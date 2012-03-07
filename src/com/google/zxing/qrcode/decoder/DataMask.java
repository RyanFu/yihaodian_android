// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.qrcode.decoder;

import com.google.zxing.common.BitMatrix;

abstract class DataMask
{
    private static class DataMask000 extends DataMask
    {

        @Override
		boolean isMasked(int i, int j)
        {
            boolean flag;
            if((1 & i + j) == 0)
                flag = true;
            else
                flag = false;
            return flag;
        }

        private DataMask000()
        {
            super(null);
        }

        DataMask000(DataMask000 datamask000)
        {
            this();
        }
    }

    private static class DataMask001 extends DataMask
    {

        @Override
		boolean isMasked(int i, int j)
        {
            boolean flag;
            if((i & 1) == 0)
                flag = true;
            else
                flag = false;
            return flag;
        }

        private DataMask001()
        {
            super(null);
        }

        DataMask001(DataMask001 datamask001)
        {
            this();
        }
    }

    private static class DataMask010 extends DataMask
    {

        @Override
		boolean isMasked(int i, int j)
        {
            boolean flag;
            if(j % 3 == 0)
                flag = true;
            else
                flag = false;
            return flag;
        }

        private DataMask010()
        {
            super(null);
        }

        DataMask010(DataMask010 datamask010)
        {
            this();
        }
    }

    private static class DataMask011 extends DataMask
    {

        @Override
		boolean isMasked(int i, int j)
        {
            boolean flag;
            if((i + j) % 3 == 0)
                flag = true;
            else
                flag = false;
            return flag;
        }

        private DataMask011()
        {
            super(null);
        }

        DataMask011(DataMask011 datamask011)
        {
            this();
        }
    }

    private static class DataMask100 extends DataMask
    {

        @Override
		boolean isMasked(int i, int j)
        {
            boolean flag;
            if((1 & (i >>> 1) + j / 3) == 0)
                flag = true;
            else
                flag = false;
            return flag;
        }

        private DataMask100()
        {
            super(null);
        }

        DataMask100(DataMask100 datamask100)
        {
            this();
        }
    }

    private static class DataMask101 extends DataMask
    {

        @Override
		boolean isMasked(int i, int j)
        {
            int k = i * j;
            boolean flag;
            if((k & 1) + k % 3 == 0)
                flag = true;
            else
                flag = false;
            return flag;
        }

        private DataMask101()
        {
            super(null);
        }

        DataMask101(DataMask101 datamask101)
        {
            this();
        }
    }

    private static class DataMask110 extends DataMask
    {

        @Override
		boolean isMasked(int i, int j)
        {
            int k = i * j;
            boolean flag;
            if((1 & (k & 1) + k % 3) == 0)
                flag = true;
            else
                flag = false;
            return flag;
        }

        private DataMask110()
        {
            super(null);
        }

        DataMask110(DataMask110 datamask110)
        {
            this();
        }
    }

    private static class DataMask111 extends DataMask
    {

        @Override
		boolean isMasked(int i, int j)
        {
            boolean flag;
            if((1 & (1 & i + j) + (i * j) % 3) == 0)
                flag = true;
            else
                flag = false;
            return flag;
        }

        private DataMask111()
        {
            super(null);
        }

        DataMask111(DataMask111 datamask111)
        {
            this();
        }
    }


    private DataMask()
    {
    }

    DataMask(DataMask datamask)
    {
        this();
    }

    static DataMask forReference(int i)
    {
        if(i < 0 || i > 7)
            throw new IllegalArgumentException();
        else
            return DATA_MASKS[i];
    }

    abstract boolean isMasked(int i, int j);

    final void unmaskBitMatrix(BitMatrix bitmatrix, int i)
    {
        int j = 0;
_L2:
        if(j >= i)
            return;
        int k = 0;
        do
        {
label0:
            {
                if(k < i)
                    break label0;
                j++;
            }
            if(true)
                continue;
            if(isMasked(j, k))
                bitmatrix.flip(k, j);
            k++;
        } while(true);
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static final DataMask DATA_MASKS[];

    static 
    {
        DataMask adatamask[] = new DataMask[8];
        adatamask[0] = new DataMask000(null);
        adatamask[1] = new DataMask001(null);
        adatamask[2] = new DataMask010(null);
        adatamask[3] = new DataMask011(null);
        adatamask[4] = new DataMask100(null);
        adatamask[5] = new DataMask101(null);
        adatamask[6] = new DataMask110(null);
        adatamask[7] = new DataMask111(null);
        DATA_MASKS = adatamask;
    }
}
