// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned;

import com.google.zxing.*;
import com.google.zxing.common.BitArray;
import java.util.Hashtable;

// Referenced classes of package com.google.zxing.oned:
//            UPCEANReader

final class UPCEANExtensionSupport
{

    UPCEANExtensionSupport()
    {
    }

    private static int determineCheckDigit(int i)
        throws NotFoundException
    {
        int j = 0;
        do
        {
            if(j >= 10)
                throw NotFoundException.getNotFoundInstance();
            if(i == CHECK_DIGIT_ENCODINGS[j])
                return j;
            j++;
        } while(true);
    }

    private static int extensionChecksum(String s)
    {
        int i;
        int j;
        int k;
        i = s.length();
        j = 0;
        k = i - 2;
_L3:
        if(k >= 0) goto _L2; else goto _L1
_L1:
        int l;
        int i1;
        l = j * 3;
        i1 = i - 1;
_L4:
        if(i1 < 0)
            return (l * 3) % 10;
        break MISSING_BLOCK_LABEL_55;
_L2:
        j += s.charAt(k) - 48;
        k -= 2;
          goto _L3
        l += s.charAt(i1) - 48;
        i1 -= 2;
          goto _L4
    }

    private static Integer parseExtension2String(String s)
    {
        return Integer.valueOf(s);
    }

    private static String parseExtension5String(String s)
    {
        String s1 = null;
        s.charAt(0);
        JVM INSTR lookupswitch 3: default 40
    //                   48: 88
    //                   53: 94
    //                   57: 100;
           goto _L1 _L2 _L3 _L4
_L1:
        s1 = "";
_L5:
        String s2;
        int i = Integer.parseInt(s.substring(1));
        s2 = (new StringBuilder(String.valueOf(s1))).append(i / 100).append('.').append(i % 100).toString();
_L6:
        return s2;
_L2:
        s1 = "\u62E2";
          goto _L5
_L3:
        s1 = "$";
          goto _L5
_L4:
        if(!"99991".equals(s))
            continue; /* Loop/switch isn't completed */
        s2 = "0.00";
          goto _L6
        if(!"99990".equals(s)) goto _L5; else goto _L7
_L7:
        s2 = "Used";
          goto _L6
    }

    private static Hashtable parseExtensionString(String s)
    {
        s.length();
        JVM INSTR tableswitch 2 5: default 36
    //                   2 42
    //                   3 36
    //                   4 36
    //                   5 61;
           goto _L1 _L2 _L1 _L1 _L3
_L1:
        Hashtable hashtable1 = null;
_L4:
        return hashtable1;
_L2:
        ResultMetadataType resultmetadatatype;
        Object obj;
        resultmetadatatype = ResultMetadataType.ISSUE_NUMBER;
        obj = parseExtension2String(s);
_L5:
        if(obj == null)
        {
            hashtable1 = null;
        } else
        {
            Hashtable hashtable = new Hashtable(1);
            hashtable.put(resultmetadatatype, obj);
            hashtable1 = hashtable;
        }
        if(true) goto _L4; else goto _L3
_L3:
        resultmetadatatype = ResultMetadataType.SUGGESTED_PRICE;
        obj = parseExtension5String(s);
          goto _L5
    }

    int decodeMiddle(BitArray bitarray, int ai[], StringBuffer stringbuffer)
        throws NotFoundException
    {
        int ai1[] = decodeMiddleCounters;
        ai1[0] = 0;
        ai1[1] = 0;
        ai1[2] = 0;
        ai1[3] = 0;
        int i = bitarray.getSize();
        int j = ai[1];
        int k = 0;
        int l = 0;
label0:
        do
        {
label1:
            {
                if(l >= 5 || j >= i)
                {
                    if(stringbuffer.length() != 5)
                        throw NotFoundException.getNotFoundInstance();
                    break label1;
                }
                int j1 = UPCEANReader.decodeDigit(bitarray, ai1, j, UPCEANReader.L_AND_G_PATTERNS);
                stringbuffer.append((char)(48 + j1 % 10));
                int k1 = 0;
                do
                {
label2:
                    {
                        if(k1 >= ai1.length)
                        {
                            if(j1 >= 10)
                                k |= 1 << 4 - l;
                            if(l != 4)
                            {
                                for(; j < i && !bitarray.get(j); j++)
                                    break label2;

                                for(; j < i && bitarray.get(j); j++)
                                    break MISSING_BLOCK_LABEL_190;

                            }
                            l++;
                            continue label0;
                        }
                        j += ai1[k1];
                        k1++;
                    }
                } while(true);
            }
            int i1 = determineCheckDigit(k);
            if(extensionChecksum(stringbuffer.toString()) != i1)
                throw NotFoundException.getNotFoundInstance();
            return j;
        } while(true);
    }

    Result decodeRow(int i, BitArray bitarray, int j)
        throws NotFoundException
    {
        int ai[] = UPCEANReader.findGuardPattern(bitarray, j, false, EXTENSION_START_PATTERN);
        StringBuffer stringbuffer = decodeRowStringBuffer;
        stringbuffer.setLength(0);
        int k = decodeMiddle(bitarray, ai, stringbuffer);
        String s = stringbuffer.toString();
        Hashtable hashtable = parseExtensionString(s);
        ResultPoint aresultpoint[] = new ResultPoint[2];
        aresultpoint[0] = new ResultPoint((ai[0] + ai[1]) / 2.0F, i);
        aresultpoint[1] = new ResultPoint(k, i);
        Result result = new Result(s, null, aresultpoint, BarcodeFormat.UPC_EAN_EXTENSION);
        if(hashtable != null)
            result.putAllMetadata(hashtable);
        return result;
    }

    private static final int CHECK_DIGIT_ENCODINGS[];
    private static final int EXTENSION_START_PATTERN[];
    private final int decodeMiddleCounters[] = new int[4];
    private final StringBuffer decodeRowStringBuffer = new StringBuffer();

    static 
    {
        int ai[] = new int[3];
        ai[0] = 1;
        ai[1] = 1;
        ai[2] = 2;
        EXTENSION_START_PATTERN = ai;
        int ai1[] = new int[10];
        ai1[0] = 24;
        ai1[1] = 20;
        ai1[2] = 18;
        ai1[3] = 17;
        ai1[4] = 12;
        ai1[5] = 6;
        ai1[6] = 3;
        ai1[7] = 10;
        ai1[8] = 9;
        ai1[9] = 5;
        CHECK_DIGIT_ENCODINGS = ai1;
    }
}
