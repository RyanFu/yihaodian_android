// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned;

import com.google.zxing.*;
import com.google.zxing.common.BitArray;
import java.util.Enumeration;
import java.util.Hashtable;

public abstract class OneDReader
    implements Reader
{

    public OneDReader()
    {
    }

    private Result doDecode(BinaryBitmap binarybitmap, Hashtable hashtable)
        throws NotFoundException
    {
        int i;
        int j;
        BitArray bitarray;
        int k;
        i = binarybitmap.getWidth();
        j = binarybitmap.getHeight();
        bitarray = new BitArray(i);
        k = j >> 1;
        if(hashtable == null) goto _L2; else goto _L1
_L1:
        DecodeHintType decodehinttype1 = DecodeHintType.TRY_HARDER;
        if(!hashtable.containsKey(decodehinttype1)) goto _L2; else goto _L3
_L3:
        boolean flag = true;
_L6:
        int l;
        int j1;
        byte byte0;
        int i1;
        if(flag)
            byte0 = 8;
        else
            byte0 = 5;
        l = Math.max(1, j >> byte0);
        if(flag)
            i1 = j;
        else
            i1 = 15;
        j1 = 0;
_L8:
        if(j1 < i1) goto _L5; else goto _L4
_L4:
        throw NotFoundException.getNotFoundInstance();
_L2:
        flag = false;
          goto _L6
_L5:
        int i2;
        int j2;
        int k1 = j1 + 1 >> 1;
        boolean flag1;
        int l1;
        BitArray bitarray1;
        if((j1 & 1) == 0)
            flag1 = true;
        else
            flag1 = false;
        if(flag1)
            l1 = k1;
        else
            l1 = -k1;
        i2 = k + l1 * l;
        if(i2 < 0 || i2 >= j) goto _L4; else goto _L7
_L7:
        bitarray1 = binarybitmap.getBlackRow(i2, bitarray);
        bitarray = bitarray1;
        j2 = 0;
_L18:
        if(j2 < 2)
            break MISSING_BLOCK_LABEL_207;
_L9:
        j1++;
          goto _L8
        NotFoundException notfoundexception;
        notfoundexception;
          goto _L9
        if(j2 != 1) goto _L11; else goto _L10
_L10:
        bitarray.reverse();
        if(hashtable == null) goto _L11; else goto _L12
_L12:
        DecodeHintType decodehinttype = DecodeHintType.NEED_RESULT_POINT_CALLBACK;
        if(!hashtable.containsKey(decodehinttype)) goto _L11; else goto _L13
_L13:
        Hashtable hashtable1;
        Enumeration enumeration;
        hashtable1 = new Hashtable();
        enumeration = hashtable.keys();
_L17:
        if(enumeration.hasMoreElements()) goto _L15; else goto _L14
_L14:
        hashtable = hashtable1;
_L11:
        Result result;
        result = decodeRow(i2, bitarray, hashtable);
        if(j2 == 1)
        {
            result.putMetadata(ResultMetadataType.ORIENTATION, new Integer(180));
            ResultPoint aresultpoint[] = result.getResultPoints();
            aresultpoint[0] = new ResultPoint((float)i - aresultpoint[0].getX() - 1.0F, aresultpoint[0].getY());
            aresultpoint[1] = new ResultPoint((float)i - aresultpoint[1].getX() - 1.0F, aresultpoint[1].getY());
        }
        return result;
_L15:
        Object obj = enumeration.nextElement();
        if(!obj.equals(DecodeHintType.NEED_RESULT_POINT_CALLBACK))
            hashtable1.put(obj, hashtable.get(obj));
        if(true) goto _L17; else goto _L16
_L16:
        ReaderException readerexception;
        readerexception;
        j2++;
          goto _L18
    }

    protected static int patternMatchVariance(int ai[], int ai1[], int i)
    {
        int j;
        int k;
        int l;
        int i1;
        j = ai.length;
        k = 0;
        l = 0;
        i1 = 0;
_L5:
        if(i1 < j) goto _L2; else goto _L1
_L1:
        if(k >= l) goto _L4; else goto _L3
_L3:
        int i3 = 0x7fffffff;
_L6:
        return i3;
_L2:
        k += ai[i1];
        l += ai1[i1];
        i1++;
          goto _L5
_L4:
        int j1;
        int k1;
        int l1;
        int i2;
        j1 = (k << 8) / l;
        k1 = i * j1 >> 8;
        l1 = 0;
        i2 = 0;
_L7:
        int l2;
        if(i2 >= j)
        {
            i3 = l1 / k;
        } else
        {
label0:
            {
                int j2 = ai[i2] << 8;
                int k2 = j1 * ai1[i2];
                if(j2 > k2)
                    l2 = j2 - k2;
                else
                    l2 = k2 - j2;
                if(l2 <= k1)
                    break label0;
                i3 = 0x7fffffff;
            }
        }
          goto _L6
        l1 += l2;
        i2++;
          goto _L7
    }

    protected static void recordPattern(BitArray bitarray, int i, int ai[])
        throws NotFoundException
    {
        int j;
        boolean flag;
        int i1;
        int j1;
        j = ai.length;
        int k = 0;
        int l;
        do
        {
            if(k >= j)
            {
                l = bitarray.getSize();
                if(i >= l)
                    throw NotFoundException.getNotFoundInstance();
                break;
            }
            ai[k] = 0;
            k++;
        } while(true);
        if(bitarray.get(i))
            flag = false;
        else
            flag = true;
        i1 = 0;
        j1 = i;
_L3:
        if(j1 < l) goto _L2; else goto _L1
_L1:
        if(i1 != j && (i1 != j - 1 || j1 != l))
            throw NotFoundException.getNotFoundInstance();
        else
            return;
_L2:
        if(!(flag ^ bitarray.get(j1)))
            continue; /* Loop/switch isn't completed */
        ai[i1] = 1 + ai[i1];
_L5:
        j1++;
          goto _L3
        if(++i1 == j) goto _L1; else goto _L4
_L4:
        ai[i1] = 1;
        if(flag)
            flag = false;
        else
            flag = true;
          goto _L5
    }

    protected static void recordPatternInReverse(BitArray bitarray, int i, int ai[])
        throws NotFoundException
    {
        int j = ai.length;
        boolean flag = bitarray.get(i);
        do
        {
            do
            {
                if(i <= 0 || j < 0)
                    if(j >= 0)
                    {
                        throw NotFoundException.getNotFoundInstance();
                    } else
                    {
                        recordPattern(bitarray, i + 1, ai);
                        return;
                    }
                i--;
            } while(bitarray.get(i) == flag);
            j--;
            if(flag)
                flag = false;
            else
                flag = true;
        } while(true);
    }

    public Result decode(BinaryBitmap binarybitmap)
        throws NotFoundException, FormatException
    {
        return decode(binarybitmap, null);
    }

    public Result decode(BinaryBitmap binarybitmap, Hashtable hashtable)
        throws NotFoundException, FormatException
    {
        Result result2 = doDecode(binarybitmap, hashtable);
        Result result1 = result2;
_L2:
        return result1;
        NotFoundException notfoundexception;
        notfoundexception;
        boolean flag;
        if(hashtable != null && hashtable.containsKey(DecodeHintType.TRY_HARDER))
            flag = true;
        else
            flag = false;
        if(flag && binarybitmap.isRotateSupported())
        {
            BinaryBitmap binarybitmap1 = binarybitmap.rotateCounterClockwise();
            Result result = doDecode(binarybitmap1, hashtable);
            Hashtable hashtable1 = result.getResultMetadata();
            int i = 270;
            if(hashtable1 != null && hashtable1.containsKey(ResultMetadataType.ORIENTATION))
                i = (i + ((Integer)hashtable1.get(ResultMetadataType.ORIENTATION)).intValue()) % 360;
            result.putMetadata(ResultMetadataType.ORIENTATION, new Integer(i));
            ResultPoint aresultpoint[] = result.getResultPoints();
            int j = binarybitmap1.getHeight();
            int k = 0;
            do
            {
                if(k >= aresultpoint.length)
                {
                    result1 = result;
                    continue; /* Loop/switch isn't completed */
                }
                aresultpoint[k] = new ResultPoint((float)j - aresultpoint[k].getY() - 1.0F, aresultpoint[k].getX());
                k++;
            } while(true);
        }
        throw notfoundexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public abstract Result decodeRow(int i, BitArray bitarray, Hashtable hashtable)
        throws NotFoundException, ChecksumException, FormatException;

    public void reset()
    {
    }

    protected static final int INTEGER_MATH_SHIFT = 8;
    protected static final int PATTERN_MATCH_RESULT_SCALE_FACTOR = 256;
}
