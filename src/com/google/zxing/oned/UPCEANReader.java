// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned;

import com.google.zxing.*;
import com.google.zxing.common.BitArray;
import java.util.Hashtable;

// Referenced classes of package com.google.zxing.oned:
//            OneDReader, UPCEANExtensionSupport, EANManufacturerOrgSupport

public abstract class UPCEANReader extends OneDReader
{

    protected UPCEANReader()
    {
    }

    private static boolean checkStandardUPCEANChecksum(String s)
        throws FormatException
    {
        int i = s.length();
        if(i != 0) goto _L2; else goto _L1
_L1:
        boolean flag = false;
_L5:
        return flag;
_L2:
        int j;
        int k;
        j = 0;
        k = i - 2;
_L6:
        if(k >= 0) goto _L4; else goto _L3
_L3:
        int i1;
        int j1;
        i1 = j * 3;
        j1 = i - 1;
_L7:
        if(j1 >= 0)
            break MISSING_BLOCK_LABEL_93;
        int l;
        int k1;
        if(i1 % 10 == 0)
            flag = true;
        else
            flag = false;
          goto _L5
_L4:
        l = s.charAt(k) - 48;
        if(l < 0 || l > 9)
            throw FormatException.getFormatInstance();
        j += l;
        k += -2;
          goto _L6
        k1 = s.charAt(j1) - 48;
        if(k1 < 0 || k1 > 9)
            throw FormatException.getFormatInstance();
        i1 += k1;
        j1 += -2;
          goto _L7
    }

    static int decodeDigit(BitArray bitarray, int ai[], int i, int ai1[][])
        throws NotFoundException
    {
        recordPattern(bitarray, i, ai);
        int j = 107;
        int k = -1;
        int l = ai1.length;
        int i1 = 0;
        do
        {
            int j1;
            if(i1 >= l)
                if(k >= 0)
                    return k;
                else
                    throw NotFoundException.getNotFoundInstance();
            j1 = patternMatchVariance(ai, ai1[i1], 179);
            if(j1 < j)
            {
                j = j1;
                k = i1;
            }
            i1++;
        } while(true);
    }

    static int[] findGuardPattern(BitArray bitarray, int i, boolean flag, int ai[])
        throws NotFoundException
    {
        int j;
        int ai1[];
        int k;
        boolean flag1;
        j = ai.length;
        ai1 = new int[j];
        k = bitarray.getSize();
        flag1 = false;
_L5:
        if(i < k) goto _L2; else goto _L1
_L1:
        int i1;
        int j1;
        boolean flag2;
        int k1;
        int l = i;
        i1 = i;
        j1 = 0;
        flag2 = flag1;
        k1 = l;
_L8:
        if(i1 >= k)
            throw NotFoundException.getNotFoundInstance();
          goto _L3
_L2:
        if(bitarray.get(i))
            flag1 = false;
        else
            flag1 = true;
        if(flag == flag1) goto _L1; else goto _L4
_L4:
        i++;
          goto _L5
_L3:
        if(!(flag2 ^ bitarray.get(i1))) goto _L7; else goto _L6
_L6:
        ai1[j1] = 1 + ai1[j1];
_L11:
        i1++;
          goto _L8
_L7:
        int l1;
        if(j1 != j - 1)
            break MISSING_BLOCK_LABEL_233;
        if(patternMatchVariance(ai1, ai, 179) < 107)
        {
            int ai2[] = new int[2];
            ai2[0] = k1;
            ai2[1] = i1;
            return ai2;
        }
        k1 += ai1[0] + ai1[1];
        l1 = 2;
_L12:
        if(l1 < j) goto _L10; else goto _L9
_L9:
        ai1[j - 2] = 0;
        ai1[j - 1] = 0;
        j1--;
_L13:
        ai1[j1] = 1;
        if(flag2)
            flag2 = false;
        else
            flag2 = true;
          goto _L11
_L10:
        ai1[l1 - 2] = ai1[l1];
        l1++;
          goto _L12
        j1++;
          goto _L13
    }

    static int[] findStartGuardPattern(BitArray bitarray)
        throws NotFoundException
    {
        boolean flag = false;
        int ai[] = null;
        int i = 0;
        do
        {
            int j;
            int k;
            do
            {
                if(flag)
                    return ai;
                ai = findGuardPattern(bitarray, i, false, START_END_PATTERN);
                j = ai[0];
                i = ai[1];
                k = j - (i - j);
            } while(k < 0);
            flag = bitarray.isRange(k, j, false);
        } while(true);
    }

    boolean checkChecksum(String s)
        throws ChecksumException, FormatException
    {
        return checkStandardUPCEANChecksum(s);
    }

    int[] decodeEnd(BitArray bitarray, int i)
        throws NotFoundException
    {
        return findGuardPattern(bitarray, i, false, START_END_PATTERN);
    }

    protected abstract int decodeMiddle(BitArray bitarray, int ai[], StringBuffer stringbuffer)
        throws NotFoundException;

    @Override
	public Result decodeRow(int i, BitArray bitarray, Hashtable hashtable)
        throws NotFoundException, ChecksumException, FormatException
    {
        return decodeRow(i, bitarray, findStartGuardPattern(bitarray), hashtable);
    }

    public Result decodeRow(int i, BitArray bitarray, int ai[], Hashtable hashtable)
        throws NotFoundException, ChecksumException, FormatException
    {
        ResultPointCallback resultpointcallback;
        StringBuffer stringbuffer;
        int j;
        int ai1[];
        int k;
        int l;
        if(hashtable == null)
            resultpointcallback = null;
        else
            resultpointcallback = (ResultPointCallback)hashtable.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
        if(resultpointcallback != null)
        {
            ResultPoint resultpoint = new ResultPoint((ai[0] + ai[1]) / 2.0F, i);
            resultpointcallback.foundPossibleResultPoint(resultpoint);
        }
        stringbuffer = decodeRowStringBuffer;
        stringbuffer.setLength(0);
        j = decodeMiddle(bitarray, ai, stringbuffer);
        if(resultpointcallback != null)
        {
            ResultPoint resultpoint1 = new ResultPoint(j, i);
            resultpointcallback.foundPossibleResultPoint(resultpoint1);
        }
        ai1 = decodeEnd(bitarray, j);
        if(resultpointcallback != null)
        {
            ResultPoint resultpoint2 = new ResultPoint((ai1[0] + ai1[1]) / 2.0F, i);
            resultpointcallback.foundPossibleResultPoint(resultpoint2);
        }
        k = ai1[1];
        l = k + (k - ai1[0]);
        if(l >= bitarray.getSize() || !bitarray.isRange(k, l, false))
            throw NotFoundException.getNotFoundInstance();
        String s = stringbuffer.toString();
        if(!checkChecksum(s))
            throw ChecksumException.getChecksumInstance();
        float f = (ai[1] + ai[0]) / 2.0F;
        float f1 = (ai1[1] + ai1[0]) / 2.0F;
        BarcodeFormat barcodeformat = getBarcodeFormat();
        ResultPoint aresultpoint[] = new ResultPoint[2];
        ResultPoint resultpoint3 = new ResultPoint(f, i);
        aresultpoint[0] = resultpoint3;
        ResultPoint resultpoint4 = new ResultPoint(f1, i);
        aresultpoint[1] = resultpoint4;
        Result result = new Result(s, null, aresultpoint, barcodeformat);
        try
        {
            Result result1 = extensionReader.decodeRow(i, bitarray, ai1[1]);
            result.putAllMetadata(result1.getResultMetadata());
            result.addResultPoints(result1.getResultPoints());
        }
        catch(ReaderException readerexception) { }
        if(BarcodeFormat.EAN_13.equals(barcodeformat) || BarcodeFormat.UPC_A.equals(barcodeformat))
        {
            String s1 = eanManSupport.lookupCountryIdentifier(s);
            if(s1 != null)
                result.putMetadata(ResultMetadataType.POSSIBLE_COUNTRY, s1);
        }
        return result;
    }

    abstract BarcodeFormat getBarcodeFormat();

    static final int L_AND_G_PATTERNS[][];
    static final int L_PATTERNS[][];
    private static final int MAX_AVG_VARIANCE = 107;
    private static final int MAX_INDIVIDUAL_VARIANCE = 179;
    static final int MIDDLE_PATTERN[];
    static final int START_END_PATTERN[];
    private final StringBuffer decodeRowStringBuffer = new StringBuffer(20);
    private final EANManufacturerOrgSupport eanManSupport = new EANManufacturerOrgSupport();
    private final UPCEANExtensionSupport extensionReader = new UPCEANExtensionSupport();

    static 
    {
        int j;
        int ai[] = new int[3];
        ai[0] = 1;
        ai[1] = 1;
        ai[2] = 1;
        START_END_PATTERN = ai;
        int ai1[] = new int[5];
        ai1[0] = 1;
        ai1[1] = 1;
        ai1[2] = 1;
        ai1[3] = 1;
        ai1[4] = 1;
        MIDDLE_PATTERN = ai1;
        int ai2[][] = new int[10][];
        int ai3[] = new int[4];
        ai3[0] = 3;
        ai3[1] = 2;
        ai3[2] = 1;
        ai3[3] = 1;
        ai2[0] = ai3;
        int ai4[] = new int[4];
        ai4[0] = 2;
        ai4[1] = 2;
        ai4[2] = 2;
        ai4[3] = 1;
        ai2[1] = ai4;
        int ai5[] = new int[4];
        ai5[0] = 2;
        ai5[1] = 1;
        ai5[2] = 2;
        ai5[3] = 2;
        ai2[2] = ai5;
        int ai6[] = new int[4];
        ai6[0] = 1;
        ai6[1] = 4;
        ai6[2] = 1;
        ai6[3] = 1;
        ai2[3] = ai6;
        int ai7[] = new int[4];
        ai7[0] = 1;
        ai7[1] = 1;
        ai7[2] = 3;
        ai7[3] = 2;
        ai2[4] = ai7;
        int ai8[] = new int[4];
        ai8[0] = 1;
        ai8[1] = 2;
        ai8[2] = 3;
        ai8[3] = 1;
        ai2[5] = ai8;
        int ai9[] = new int[4];
        ai9[0] = 1;
        ai9[1] = 1;
        ai9[2] = 1;
        ai9[3] = 4;
        ai2[6] = ai9;
        int ai10[] = new int[4];
        ai10[0] = 1;
        ai10[1] = 3;
        ai10[2] = 1;
        ai10[3] = 2;
        ai2[7] = ai10;
        int ai11[] = new int[4];
        ai11[0] = 1;
        ai11[1] = 2;
        ai11[2] = 1;
        ai11[3] = 3;
        ai2[8] = ai11;
        int ai12[] = new int[4];
        ai12[0] = 3;
        ai12[1] = 1;
        ai12[2] = 1;
        ai12[3] = 2;
        ai2[9] = ai12;
        L_PATTERNS = ai2;
        L_AND_G_PATTERNS = new int[20][];
        int i = 0;
        do
        {
            if(i >= 10)
            {
                j = 10;
                break MISSING_BLOCK_LABEL_378;
            }
            L_AND_G_PATTERNS[i] = L_PATTERNS[i];
            i++;
        } while(true);
_L2:
        int ai13[];
        int ai14[];
        int k;
        if(j >= 20)
            return;
        ai13 = L_PATTERNS[j - 10];
        ai14 = new int[ai13.length];
        k = 0;
_L3:
label0:
        {
            if(k < ai13.length)
                break label0;
            L_AND_G_PATTERNS[j] = ai14;
            j++;
        }
        if(true) goto _L2; else goto _L1
_L1:
        ai14[k] = ai13[ai13.length - k - 1];
        k++;
          goto _L3
    }
}
