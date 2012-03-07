// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned;

import com.google.zxing.*;
import com.google.zxing.common.BitArray;
import java.util.Hashtable;

// Referenced classes of package com.google.zxing.oned:
//            OneDReader

public final class Code128Reader extends OneDReader
{

    public Code128Reader()
    {
    }

    private static int decodeCode(BitArray bitarray, int ai[], int i)
        throws NotFoundException
    {
        recordPattern(bitarray, i, ai);
        int j = 64;
        int k = -1;
        int l = 0;
        do
        {
            int i1;
            if(l >= CODE_PATTERNS.length)
                if(k >= 0)
                    return k;
                else
                    throw NotFoundException.getNotFoundInstance();
            i1 = patternMatchVariance(ai, CODE_PATTERNS[l], 179);
            if(i1 < j)
            {
                j = i1;
                k = l;
            }
            l++;
        } while(true);
    }

    private static int[] findStartPattern(BitArray bitarray)
        throws NotFoundException
    {
        int ai[];
        int l;
        boolean flag;
        int j1;
        int k1;
        int l1;
        int i = bitarray.getSize();
        int j = 0;
        int k;
        int i1;
        while(j < i && !bitarray.get(j)) 
            j++;
        ai = new int[6];
        k = j;
        l = ai.length;
        i1 = j;
        flag = false;
        j1 = k;
        k1 = i1;
        l1 = 0;
_L2:
        int i2;
        int k2;
        boolean flag2;
        if(k1 >= i)
            throw NotFoundException.getNotFoundInstance();
        if(!(flag ^ bitarray.get(k1)))
            break; /* Loop/switch isn't completed */
        ai[l1] = 1 + ai[l1];
        i2 = l1;
        flag2 = flag;
        k2 = j1;
_L5:
        k1++;
        j1 = k2;
        flag = flag2;
        l1 = i2;
        if(true) goto _L2; else goto _L1
_L1:
        int l3;
        int i4;
        if(l1 != l - 1)
            break MISSING_BLOCK_LABEL_322;
        int l2 = 64;
        int i3 = -1;
        int j3 = 103;
        do
        {
            if(j3 > 105)
            {
                if(i3 >= 0 && bitarray.isRange(Math.max(0, j1 - (k1 - j1) / 2), j1, false))
                {
                    int ai1[] = new int[3];
                    ai1[0] = j1;
                    ai1[1] = k1;
                    ai1[2] = i3;
                    return ai1;
                }
                break;
            }
            int k3 = patternMatchVariance(ai, CODE_PATTERNS[j3], 179);
            if(k3 < l2)
            {
                l2 = k3;
                i3 = j3;
            }
            j3++;
        } while(true);
        l3 = j1 + (ai[0] + ai[1]);
        i4 = 2;
_L6:
        if(i4 < l) goto _L4; else goto _L3
_L3:
        int j2;
        ai[l - 2] = 0;
        ai[l - 1] = 0;
        i2 = l1 - 1;
        j2 = l3;
_L7:
        ai[i2] = 1;
        boolean flag1;
        if(flag)
            flag1 = false;
        else
            flag1 = true;
        k2 = j2;
        flag2 = flag1;
          goto _L5
_L4:
        ai[i4 - 2] = ai[i4];
        i4++;
          goto _L6
        i2 = l1 + 1;
        j2 = j1;
          goto _L7
    }

    @Override
	public Result decodeRow(int i, BitArray bitarray, Hashtable hashtable)
        throws NotFoundException, FormatException, ChecksumException
    {
        int ai[];
        int j;
        ai = findStartPattern(bitarray);
        j = ai[2];
        j;
        JVM INSTR tableswitch 103 105: default 40
    //                   103 44
    //                   104 159
    //                   105 166;
           goto _L1 _L2 _L3 _L4
_L1:
        throw FormatException.getFormatInstance();
_L2:
        byte byte0 = 101;
_L30:
        boolean flag;
        boolean flag1;
        StringBuffer stringbuffer;
        int k;
        int l;
        int ai1[];
        int i1;
        int j1;
        int k1;
        int l1;
        boolean flag2;
        flag = false;
        flag1 = false;
        stringbuffer = new StringBuffer(20);
        k = ai[0];
        l = ai[1];
        ai1 = new int[6];
        i1 = 0;
        j1 = 0;
        k1 = j;
        l1 = 0;
        flag2 = true;
_L17:
        if(!flag) goto _L6; else goto _L5
_L5:
        int k2 = bitarray.getSize();
_L27:
        if(l < k2 && bitarray.get(l)) goto _L8; else goto _L7
_L3:
        byte0 = 100;
        continue; /* Loop/switch isn't completed */
_L4:
        byte0 = 99;
        continue; /* Loop/switch isn't completed */
_L6:
        flag3 = flag1;
        flag1 = false;
        i1 = j1;
        j1 = decodeCode(bitarray, ai1, l);
        if(j1 != 106)
            flag2 = true;
        if(j1 != 106)
        {
            l1++;
            k1 += l1 * j1;
        }
        k = l;
        i2 = 0;
_L18:
        j2 = ai1.length;
        if(i2 < j2) goto _L10; else goto _L9
_L9:
        j1;
        JVM INSTR tableswitch 103 105: default 272
    //                   103 358
    //                   104 358
    //                   105 358;
           goto _L11 _L12 _L12 _L12
_L11:
        byte0;
        JVM INSTR tableswitch 99 101: default 300
    //                   99 633
    //                   100 509
    //                   101 362;
           goto _L13 _L14 _L15 _L16
_L20:
        if(flag3)
        {
            switch(byte0)
            {
            case 99: // 'c'
                byte0 = 100;
                break;

            case 101: // 'e'
                byte0 = 99;
                break;

            case 100: // 'd'
                byte0 = 101;
                break;
            }
            continue; /* Loop/switch isn't completed */
        }
          goto _L17
_L10:
        l += ai1[i2];
        i2++;
          goto _L18
_L12:
        throw FormatException.getFormatInstance();
_L16:
        if(j1 < 64)
            stringbuffer.append((char)(j1 + 32));
        else
        if(j1 < 96)
        {
            stringbuffer.append((char)(j1 - 64));
        } else
        {
            if(j1 != 106)
                flag2 = false;
            switch(j1)
            {
            case 98: // 'b'
                flag1 = true;
                byte0 = 100;
                break;

            case 100: // 'd'
                byte0 = 100;
                break;

            case 99: // 'c'
                byte0 = 99;
                break;

            case 106: // 'j'
                flag = true;
                break;
            }
        }
_L13:
        if(true) goto _L20; else goto _L19
_L19:
_L15:
        if(j1 >= 96) goto _L22; else goto _L21
_L21:
        stringbuffer.append((char)(j1 + 32));
          goto _L20
_L22:
        if(j1 != 106)
            flag2 = false;
        switch(j1)
        {
        case 98: // 'b'
            flag1 = true;
            byte0 = 99;
            break;

        case 101: // 'e'
            byte0 = 101;
            break;

        case 99: // 'c'
            byte0 = 99;
            break;

        case 106: // 'j'
            flag = true;
            break;
        }
        if(true) goto _L20; else goto _L23
_L23:
_L14:
        if(j1 >= 100) goto _L25; else goto _L24
_L24:
        if(j1 < 10)
            stringbuffer.append('0');
        stringbuffer.append(j1);
          goto _L20
_L25:
        if(j1 != 106)
            flag2 = false;
        switch(j1)
        {
        case 100: // 'd'
            byte0 = 100;
            break;

        case 101: // 'e'
            byte0 = 101;
            break;

        case 106: // 'j'
            flag = true;
            break;
        }
        if(true) goto _L20; else goto _L26
_L26:
_L8:
        l++;
          goto _L27
        if(true) goto _L17; else goto _L28
_L28:
_L7:
        int l2 = Math.min(k2, l + (l - k) / 2);
        boolean flag3;
        int i2;
        int j2;
        if(!bitarray.isRange(l, l2, false))
            throw NotFoundException.getNotFoundInstance();
        if((k1 - l1 * i1) % 103 != i1)
            throw ChecksumException.getChecksumInstance();
        int i3 = stringbuffer.length();
        String s;
        if(i3 > 0 && flag2)
            if(byte0 == 99)
                stringbuffer.delete(i3 - 2, i3);
            else
                stringbuffer.delete(i3 - 1, i3);
        s = stringbuffer.toString();
        if(s.length() == 0)
            throw FormatException.getFormatInstance();
        float f = (float)(ai[1] + ai[0]) / 2.0F;
        float f1 = (float)(l + k) / 2.0F;
        ResultPoint aresultpoint[] = new ResultPoint[2];
        ResultPoint resultpoint = new ResultPoint(f, i);
        aresultpoint[0] = resultpoint;
        ResultPoint resultpoint1 = new ResultPoint(f1, i);
        aresultpoint[1] = resultpoint1;
        Result result = new Result(s, null, aresultpoint, BarcodeFormat.CODE_128);
        return result;
        if(true) goto _L30; else goto _L29
_L29:
    }

    private static final int CODE_CODE_A = 101;
    private static final int CODE_CODE_B = 100;
    private static final int CODE_CODE_C = 99;
    private static final int CODE_FNC_1 = 102;
    private static final int CODE_FNC_2 = 97;
    private static final int CODE_FNC_3 = 96;
    private static final int CODE_FNC_4_A = 101;
    private static final int CODE_FNC_4_B = 100;
    static final int CODE_PATTERNS[][];
    private static final int CODE_SHIFT = 98;
    private static final int CODE_START_A = 103;
    private static final int CODE_START_B = 104;
    private static final int CODE_START_C = 105;
    private static final int CODE_STOP = 106;
    private static final int MAX_AVG_VARIANCE = 64;
    private static final int MAX_INDIVIDUAL_VARIANCE = 179;

    static 
    {
        int ai[][] = new int[107][];
        int ai1[] = new int[6];
        ai1[0] = 2;
        ai1[1] = 1;
        ai1[2] = 2;
        ai1[3] = 2;
        ai1[4] = 2;
        ai1[5] = 2;
        ai[0] = ai1;
        int ai2[] = new int[6];
        ai2[0] = 2;
        ai2[1] = 2;
        ai2[2] = 2;
        ai2[3] = 1;
        ai2[4] = 2;
        ai2[5] = 2;
        ai[1] = ai2;
        int ai3[] = new int[6];
        ai3[0] = 2;
        ai3[1] = 2;
        ai3[2] = 2;
        ai3[3] = 2;
        ai3[4] = 2;
        ai3[5] = 1;
        ai[2] = ai3;
        int ai4[] = new int[6];
        ai4[0] = 1;
        ai4[1] = 2;
        ai4[2] = 1;
        ai4[3] = 2;
        ai4[4] = 2;
        ai4[5] = 3;
        ai[3] = ai4;
        int ai5[] = new int[6];
        ai5[0] = 1;
        ai5[1] = 2;
        ai5[2] = 1;
        ai5[3] = 3;
        ai5[4] = 2;
        ai5[5] = 2;
        ai[4] = ai5;
        int ai6[] = new int[6];
        ai6[0] = 1;
        ai6[1] = 3;
        ai6[2] = 1;
        ai6[3] = 2;
        ai6[4] = 2;
        ai6[5] = 2;
        ai[5] = ai6;
        int ai7[] = new int[6];
        ai7[0] = 1;
        ai7[1] = 2;
        ai7[2] = 2;
        ai7[3] = 2;
        ai7[4] = 1;
        ai7[5] = 3;
        ai[6] = ai7;
        int ai8[] = new int[6];
        ai8[0] = 1;
        ai8[1] = 2;
        ai8[2] = 2;
        ai8[3] = 3;
        ai8[4] = 1;
        ai8[5] = 2;
        ai[7] = ai8;
        int ai9[] = new int[6];
        ai9[0] = 1;
        ai9[1] = 3;
        ai9[2] = 2;
        ai9[3] = 2;
        ai9[4] = 1;
        ai9[5] = 2;
        ai[8] = ai9;
        int ai10[] = new int[6];
        ai10[0] = 2;
        ai10[1] = 2;
        ai10[2] = 1;
        ai10[3] = 2;
        ai10[4] = 1;
        ai10[5] = 3;
        ai[9] = ai10;
        int ai11[] = new int[6];
        ai11[0] = 2;
        ai11[1] = 2;
        ai11[2] = 1;
        ai11[3] = 3;
        ai11[4] = 1;
        ai11[5] = 2;
        ai[10] = ai11;
        int ai12[] = new int[6];
        ai12[0] = 2;
        ai12[1] = 3;
        ai12[2] = 1;
        ai12[3] = 2;
        ai12[4] = 1;
        ai12[5] = 2;
        ai[11] = ai12;
        int ai13[] = new int[6];
        ai13[0] = 1;
        ai13[1] = 1;
        ai13[2] = 2;
        ai13[3] = 2;
        ai13[4] = 3;
        ai13[5] = 2;
        ai[12] = ai13;
        int ai14[] = new int[6];
        ai14[0] = 1;
        ai14[1] = 2;
        ai14[2] = 2;
        ai14[3] = 1;
        ai14[4] = 3;
        ai14[5] = 2;
        ai[13] = ai14;
        int ai15[] = new int[6];
        ai15[0] = 1;
        ai15[1] = 2;
        ai15[2] = 2;
        ai15[3] = 2;
        ai15[4] = 3;
        ai15[5] = 1;
        ai[14] = ai15;
        int ai16[] = new int[6];
        ai16[0] = 1;
        ai16[1] = 1;
        ai16[2] = 3;
        ai16[3] = 2;
        ai16[4] = 2;
        ai16[5] = 2;
        ai[15] = ai16;
        int ai17[] = new int[6];
        ai17[0] = 1;
        ai17[1] = 2;
        ai17[2] = 3;
        ai17[3] = 1;
        ai17[4] = 2;
        ai17[5] = 2;
        ai[16] = ai17;
        int ai18[] = new int[6];
        ai18[0] = 1;
        ai18[1] = 2;
        ai18[2] = 3;
        ai18[3] = 2;
        ai18[4] = 2;
        ai18[5] = 1;
        ai[17] = ai18;
        int ai19[] = new int[6];
        ai19[0] = 2;
        ai19[1] = 2;
        ai19[2] = 3;
        ai19[3] = 2;
        ai19[4] = 1;
        ai19[5] = 1;
        ai[18] = ai19;
        int ai20[] = new int[6];
        ai20[0] = 2;
        ai20[1] = 2;
        ai20[2] = 1;
        ai20[3] = 1;
        ai20[4] = 3;
        ai20[5] = 2;
        ai[19] = ai20;
        int ai21[] = new int[6];
        ai21[0] = 2;
        ai21[1] = 2;
        ai21[2] = 1;
        ai21[3] = 2;
        ai21[4] = 3;
        ai21[5] = 1;
        ai[20] = ai21;
        int ai22[] = new int[6];
        ai22[0] = 2;
        ai22[1] = 1;
        ai22[2] = 3;
        ai22[3] = 2;
        ai22[4] = 1;
        ai22[5] = 2;
        ai[21] = ai22;
        int ai23[] = new int[6];
        ai23[0] = 2;
        ai23[1] = 2;
        ai23[2] = 3;
        ai23[3] = 1;
        ai23[4] = 1;
        ai23[5] = 2;
        ai[22] = ai23;
        int ai24[] = new int[6];
        ai24[0] = 3;
        ai24[1] = 1;
        ai24[2] = 2;
        ai24[3] = 1;
        ai24[4] = 3;
        ai24[5] = 1;
        ai[23] = ai24;
        int ai25[] = new int[6];
        ai25[0] = 3;
        ai25[1] = 1;
        ai25[2] = 1;
        ai25[3] = 2;
        ai25[4] = 2;
        ai25[5] = 2;
        ai[24] = ai25;
        int ai26[] = new int[6];
        ai26[0] = 3;
        ai26[1] = 2;
        ai26[2] = 1;
        ai26[3] = 1;
        ai26[4] = 2;
        ai26[5] = 2;
        ai[25] = ai26;
        int ai27[] = new int[6];
        ai27[0] = 3;
        ai27[1] = 2;
        ai27[2] = 1;
        ai27[3] = 2;
        ai27[4] = 2;
        ai27[5] = 1;
        ai[26] = ai27;
        int ai28[] = new int[6];
        ai28[0] = 3;
        ai28[1] = 1;
        ai28[2] = 2;
        ai28[3] = 2;
        ai28[4] = 1;
        ai28[5] = 2;
        ai[27] = ai28;
        int ai29[] = new int[6];
        ai29[0] = 3;
        ai29[1] = 2;
        ai29[2] = 2;
        ai29[3] = 1;
        ai29[4] = 1;
        ai29[5] = 2;
        ai[28] = ai29;
        int ai30[] = new int[6];
        ai30[0] = 3;
        ai30[1] = 2;
        ai30[2] = 2;
        ai30[3] = 2;
        ai30[4] = 1;
        ai30[5] = 1;
        ai[29] = ai30;
        int ai31[] = new int[6];
        ai31[0] = 2;
        ai31[1] = 1;
        ai31[2] = 2;
        ai31[3] = 1;
        ai31[4] = 2;
        ai31[5] = 3;
        ai[30] = ai31;
        int ai32[] = new int[6];
        ai32[0] = 2;
        ai32[1] = 1;
        ai32[2] = 2;
        ai32[3] = 3;
        ai32[4] = 2;
        ai32[5] = 1;
        ai[31] = ai32;
        int ai33[] = new int[6];
        ai33[0] = 2;
        ai33[1] = 3;
        ai33[2] = 2;
        ai33[3] = 1;
        ai33[4] = 2;
        ai33[5] = 1;
        ai[32] = ai33;
        int ai34[] = new int[6];
        ai34[0] = 1;
        ai34[1] = 1;
        ai34[2] = 1;
        ai34[3] = 3;
        ai34[4] = 2;
        ai34[5] = 3;
        ai[33] = ai34;
        int ai35[] = new int[6];
        ai35[0] = 1;
        ai35[1] = 3;
        ai35[2] = 1;
        ai35[3] = 1;
        ai35[4] = 2;
        ai35[5] = 3;
        ai[34] = ai35;
        int ai36[] = new int[6];
        ai36[0] = 1;
        ai36[1] = 3;
        ai36[2] = 1;
        ai36[3] = 3;
        ai36[4] = 2;
        ai36[5] = 1;
        ai[35] = ai36;
        int ai37[] = new int[6];
        ai37[0] = 1;
        ai37[1] = 1;
        ai37[2] = 2;
        ai37[3] = 3;
        ai37[4] = 1;
        ai37[5] = 3;
        ai[36] = ai37;
        int ai38[] = new int[6];
        ai38[0] = 1;
        ai38[1] = 3;
        ai38[2] = 2;
        ai38[3] = 1;
        ai38[4] = 1;
        ai38[5] = 3;
        ai[37] = ai38;
        int ai39[] = new int[6];
        ai39[0] = 1;
        ai39[1] = 3;
        ai39[2] = 2;
        ai39[3] = 3;
        ai39[4] = 1;
        ai39[5] = 1;
        ai[38] = ai39;
        int ai40[] = new int[6];
        ai40[0] = 2;
        ai40[1] = 1;
        ai40[2] = 1;
        ai40[3] = 3;
        ai40[4] = 1;
        ai40[5] = 3;
        ai[39] = ai40;
        int ai41[] = new int[6];
        ai41[0] = 2;
        ai41[1] = 3;
        ai41[2] = 1;
        ai41[3] = 1;
        ai41[4] = 1;
        ai41[5] = 3;
        ai[40] = ai41;
        int ai42[] = new int[6];
        ai42[0] = 2;
        ai42[1] = 3;
        ai42[2] = 1;
        ai42[3] = 3;
        ai42[4] = 1;
        ai42[5] = 1;
        ai[41] = ai42;
        int ai43[] = new int[6];
        ai43[0] = 1;
        ai43[1] = 1;
        ai43[2] = 2;
        ai43[3] = 1;
        ai43[4] = 3;
        ai43[5] = 3;
        ai[42] = ai43;
        int ai44[] = new int[6];
        ai44[0] = 1;
        ai44[1] = 1;
        ai44[2] = 2;
        ai44[3] = 3;
        ai44[4] = 3;
        ai44[5] = 1;
        ai[43] = ai44;
        int ai45[] = new int[6];
        ai45[0] = 1;
        ai45[1] = 3;
        ai45[2] = 2;
        ai45[3] = 1;
        ai45[4] = 3;
        ai45[5] = 1;
        ai[44] = ai45;
        int ai46[] = new int[6];
        ai46[0] = 1;
        ai46[1] = 1;
        ai46[2] = 3;
        ai46[3] = 1;
        ai46[4] = 2;
        ai46[5] = 3;
        ai[45] = ai46;
        int ai47[] = new int[6];
        ai47[0] = 1;
        ai47[1] = 1;
        ai47[2] = 3;
        ai47[3] = 3;
        ai47[4] = 2;
        ai47[5] = 1;
        ai[46] = ai47;
        int ai48[] = new int[6];
        ai48[0] = 1;
        ai48[1] = 3;
        ai48[2] = 3;
        ai48[3] = 1;
        ai48[4] = 2;
        ai48[5] = 1;
        ai[47] = ai48;
        int ai49[] = new int[6];
        ai49[0] = 3;
        ai49[1] = 1;
        ai49[2] = 3;
        ai49[3] = 1;
        ai49[4] = 2;
        ai49[5] = 1;
        ai[48] = ai49;
        int ai50[] = new int[6];
        ai50[0] = 2;
        ai50[1] = 1;
        ai50[2] = 1;
        ai50[3] = 3;
        ai50[4] = 3;
        ai50[5] = 1;
        ai[49] = ai50;
        int ai51[] = new int[6];
        ai51[0] = 2;
        ai51[1] = 3;
        ai51[2] = 1;
        ai51[3] = 1;
        ai51[4] = 3;
        ai51[5] = 1;
        ai[50] = ai51;
        int ai52[] = new int[6];
        ai52[0] = 2;
        ai52[1] = 1;
        ai52[2] = 3;
        ai52[3] = 1;
        ai52[4] = 1;
        ai52[5] = 3;
        ai[51] = ai52;
        int ai53[] = new int[6];
        ai53[0] = 2;
        ai53[1] = 1;
        ai53[2] = 3;
        ai53[3] = 3;
        ai53[4] = 1;
        ai53[5] = 1;
        ai[52] = ai53;
        int ai54[] = new int[6];
        ai54[0] = 2;
        ai54[1] = 1;
        ai54[2] = 3;
        ai54[3] = 1;
        ai54[4] = 3;
        ai54[5] = 1;
        ai[53] = ai54;
        int ai55[] = new int[6];
        ai55[0] = 3;
        ai55[1] = 1;
        ai55[2] = 1;
        ai55[3] = 1;
        ai55[4] = 2;
        ai55[5] = 3;
        ai[54] = ai55;
        int ai56[] = new int[6];
        ai56[0] = 3;
        ai56[1] = 1;
        ai56[2] = 1;
        ai56[3] = 3;
        ai56[4] = 2;
        ai56[5] = 1;
        ai[55] = ai56;
        int ai57[] = new int[6];
        ai57[0] = 3;
        ai57[1] = 3;
        ai57[2] = 1;
        ai57[3] = 1;
        ai57[4] = 2;
        ai57[5] = 1;
        ai[56] = ai57;
        int ai58[] = new int[6];
        ai58[0] = 3;
        ai58[1] = 1;
        ai58[2] = 2;
        ai58[3] = 1;
        ai58[4] = 1;
        ai58[5] = 3;
        ai[57] = ai58;
        int ai59[] = new int[6];
        ai59[0] = 3;
        ai59[1] = 1;
        ai59[2] = 2;
        ai59[3] = 3;
        ai59[4] = 1;
        ai59[5] = 1;
        ai[58] = ai59;
        int ai60[] = new int[6];
        ai60[0] = 3;
        ai60[1] = 3;
        ai60[2] = 2;
        ai60[3] = 1;
        ai60[4] = 1;
        ai60[5] = 1;
        ai[59] = ai60;
        int ai61[] = new int[6];
        ai61[0] = 3;
        ai61[1] = 1;
        ai61[2] = 4;
        ai61[3] = 1;
        ai61[4] = 1;
        ai61[5] = 1;
        ai[60] = ai61;
        int ai62[] = new int[6];
        ai62[0] = 2;
        ai62[1] = 2;
        ai62[2] = 1;
        ai62[3] = 4;
        ai62[4] = 1;
        ai62[5] = 1;
        ai[61] = ai62;
        int ai63[] = new int[6];
        ai63[0] = 4;
        ai63[1] = 3;
        ai63[2] = 1;
        ai63[3] = 1;
        ai63[4] = 1;
        ai63[5] = 1;
        ai[62] = ai63;
        int ai64[] = new int[6];
        ai64[0] = 1;
        ai64[1] = 1;
        ai64[2] = 1;
        ai64[3] = 2;
        ai64[4] = 2;
        ai64[5] = 4;
        ai[63] = ai64;
        int ai65[] = new int[6];
        ai65[0] = 1;
        ai65[1] = 1;
        ai65[2] = 1;
        ai65[3] = 4;
        ai65[4] = 2;
        ai65[5] = 2;
        ai[64] = ai65;
        int ai66[] = new int[6];
        ai66[0] = 1;
        ai66[1] = 2;
        ai66[2] = 1;
        ai66[3] = 1;
        ai66[4] = 2;
        ai66[5] = 4;
        ai[65] = ai66;
        int ai67[] = new int[6];
        ai67[0] = 1;
        ai67[1] = 2;
        ai67[2] = 1;
        ai67[3] = 4;
        ai67[4] = 2;
        ai67[5] = 1;
        ai[66] = ai67;
        int ai68[] = new int[6];
        ai68[0] = 1;
        ai68[1] = 4;
        ai68[2] = 1;
        ai68[3] = 1;
        ai68[4] = 2;
        ai68[5] = 2;
        ai[67] = ai68;
        int ai69[] = new int[6];
        ai69[0] = 1;
        ai69[1] = 4;
        ai69[2] = 1;
        ai69[3] = 2;
        ai69[4] = 2;
        ai69[5] = 1;
        ai[68] = ai69;
        int ai70[] = new int[6];
        ai70[0] = 1;
        ai70[1] = 1;
        ai70[2] = 2;
        ai70[3] = 2;
        ai70[4] = 1;
        ai70[5] = 4;
        ai[69] = ai70;
        int ai71[] = new int[6];
        ai71[0] = 1;
        ai71[1] = 1;
        ai71[2] = 2;
        ai71[3] = 4;
        ai71[4] = 1;
        ai71[5] = 2;
        ai[70] = ai71;
        int ai72[] = new int[6];
        ai72[0] = 1;
        ai72[1] = 2;
        ai72[2] = 2;
        ai72[3] = 1;
        ai72[4] = 1;
        ai72[5] = 4;
        ai[71] = ai72;
        int ai73[] = new int[6];
        ai73[0] = 1;
        ai73[1] = 2;
        ai73[2] = 2;
        ai73[3] = 4;
        ai73[4] = 1;
        ai73[5] = 1;
        ai[72] = ai73;
        int ai74[] = new int[6];
        ai74[0] = 1;
        ai74[1] = 4;
        ai74[2] = 2;
        ai74[3] = 1;
        ai74[4] = 1;
        ai74[5] = 2;
        ai[73] = ai74;
        int ai75[] = new int[6];
        ai75[0] = 1;
        ai75[1] = 4;
        ai75[2] = 2;
        ai75[3] = 2;
        ai75[4] = 1;
        ai75[5] = 1;
        ai[74] = ai75;
        int ai76[] = new int[6];
        ai76[0] = 2;
        ai76[1] = 4;
        ai76[2] = 1;
        ai76[3] = 2;
        ai76[4] = 1;
        ai76[5] = 1;
        ai[75] = ai76;
        int ai77[] = new int[6];
        ai77[0] = 2;
        ai77[1] = 2;
        ai77[2] = 1;
        ai77[3] = 1;
        ai77[4] = 1;
        ai77[5] = 4;
        ai[76] = ai77;
        int ai78[] = new int[6];
        ai78[0] = 4;
        ai78[1] = 1;
        ai78[2] = 3;
        ai78[3] = 1;
        ai78[4] = 1;
        ai78[5] = 1;
        ai[77] = ai78;
        int ai79[] = new int[6];
        ai79[0] = 2;
        ai79[1] = 4;
        ai79[2] = 1;
        ai79[3] = 1;
        ai79[4] = 1;
        ai79[5] = 2;
        ai[78] = ai79;
        int ai80[] = new int[6];
        ai80[0] = 1;
        ai80[1] = 3;
        ai80[2] = 4;
        ai80[3] = 1;
        ai80[4] = 1;
        ai80[5] = 1;
        ai[79] = ai80;
        int ai81[] = new int[6];
        ai81[0] = 1;
        ai81[1] = 1;
        ai81[2] = 1;
        ai81[3] = 2;
        ai81[4] = 4;
        ai81[5] = 2;
        ai[80] = ai81;
        int ai82[] = new int[6];
        ai82[0] = 1;
        ai82[1] = 2;
        ai82[2] = 1;
        ai82[3] = 1;
        ai82[4] = 4;
        ai82[5] = 2;
        ai[81] = ai82;
        int ai83[] = new int[6];
        ai83[0] = 1;
        ai83[1] = 2;
        ai83[2] = 1;
        ai83[3] = 2;
        ai83[4] = 4;
        ai83[5] = 1;
        ai[82] = ai83;
        int ai84[] = new int[6];
        ai84[0] = 1;
        ai84[1] = 1;
        ai84[2] = 4;
        ai84[3] = 2;
        ai84[4] = 1;
        ai84[5] = 2;
        ai[83] = ai84;
        int ai85[] = new int[6];
        ai85[0] = 1;
        ai85[1] = 2;
        ai85[2] = 4;
        ai85[3] = 1;
        ai85[4] = 1;
        ai85[5] = 2;
        ai[84] = ai85;
        int ai86[] = new int[6];
        ai86[0] = 1;
        ai86[1] = 2;
        ai86[2] = 4;
        ai86[3] = 2;
        ai86[4] = 1;
        ai86[5] = 1;
        ai[85] = ai86;
        int ai87[] = new int[6];
        ai87[0] = 4;
        ai87[1] = 1;
        ai87[2] = 1;
        ai87[3] = 2;
        ai87[4] = 1;
        ai87[5] = 2;
        ai[86] = ai87;
        int ai88[] = new int[6];
        ai88[0] = 4;
        ai88[1] = 2;
        ai88[2] = 1;
        ai88[3] = 1;
        ai88[4] = 1;
        ai88[5] = 2;
        ai[87] = ai88;
        int ai89[] = new int[6];
        ai89[0] = 4;
        ai89[1] = 2;
        ai89[2] = 1;
        ai89[3] = 2;
        ai89[4] = 1;
        ai89[5] = 1;
        ai[88] = ai89;
        int ai90[] = new int[6];
        ai90[0] = 2;
        ai90[1] = 1;
        ai90[2] = 2;
        ai90[3] = 1;
        ai90[4] = 4;
        ai90[5] = 1;
        ai[89] = ai90;
        int ai91[] = new int[6];
        ai91[0] = 2;
        ai91[1] = 1;
        ai91[2] = 4;
        ai91[3] = 1;
        ai91[4] = 2;
        ai91[5] = 1;
        ai[90] = ai91;
        int ai92[] = new int[6];
        ai92[0] = 4;
        ai92[1] = 1;
        ai92[2] = 2;
        ai92[3] = 1;
        ai92[4] = 2;
        ai92[5] = 1;
        ai[91] = ai92;
        int ai93[] = new int[6];
        ai93[0] = 1;
        ai93[1] = 1;
        ai93[2] = 1;
        ai93[3] = 1;
        ai93[4] = 4;
        ai93[5] = 3;
        ai[92] = ai93;
        int ai94[] = new int[6];
        ai94[0] = 1;
        ai94[1] = 1;
        ai94[2] = 1;
        ai94[3] = 3;
        ai94[4] = 4;
        ai94[5] = 1;
        ai[93] = ai94;
        int ai95[] = new int[6];
        ai95[0] = 1;
        ai95[1] = 3;
        ai95[2] = 1;
        ai95[3] = 1;
        ai95[4] = 4;
        ai95[5] = 1;
        ai[94] = ai95;
        int ai96[] = new int[6];
        ai96[0] = 1;
        ai96[1] = 1;
        ai96[2] = 4;
        ai96[3] = 1;
        ai96[4] = 1;
        ai96[5] = 3;
        ai[95] = ai96;
        int ai97[] = new int[6];
        ai97[0] = 1;
        ai97[1] = 1;
        ai97[2] = 4;
        ai97[3] = 3;
        ai97[4] = 1;
        ai97[5] = 1;
        ai[96] = ai97;
        int ai98[] = new int[6];
        ai98[0] = 4;
        ai98[1] = 1;
        ai98[2] = 1;
        ai98[3] = 1;
        ai98[4] = 1;
        ai98[5] = 3;
        ai[97] = ai98;
        int ai99[] = new int[6];
        ai99[0] = 4;
        ai99[1] = 1;
        ai99[2] = 1;
        ai99[3] = 3;
        ai99[4] = 1;
        ai99[5] = 1;
        ai[98] = ai99;
        int ai100[] = new int[6];
        ai100[0] = 1;
        ai100[1] = 1;
        ai100[2] = 3;
        ai100[3] = 1;
        ai100[4] = 4;
        ai100[5] = 1;
        ai[99] = ai100;
        int ai101[] = new int[6];
        ai101[0] = 1;
        ai101[1] = 1;
        ai101[2] = 4;
        ai101[3] = 1;
        ai101[4] = 3;
        ai101[5] = 1;
        ai[100] = ai101;
        int ai102[] = new int[6];
        ai102[0] = 3;
        ai102[1] = 1;
        ai102[2] = 1;
        ai102[3] = 1;
        ai102[4] = 4;
        ai102[5] = 1;
        ai[101] = ai102;
        int ai103[] = new int[6];
        ai103[0] = 4;
        ai103[1] = 1;
        ai103[2] = 1;
        ai103[3] = 1;
        ai103[4] = 3;
        ai103[5] = 1;
        ai[102] = ai103;
        int ai104[] = new int[6];
        ai104[0] = 2;
        ai104[1] = 1;
        ai104[2] = 1;
        ai104[3] = 4;
        ai104[4] = 1;
        ai104[5] = 2;
        ai[103] = ai104;
        int ai105[] = new int[6];
        ai105[0] = 2;
        ai105[1] = 1;
        ai105[2] = 1;
        ai105[3] = 2;
        ai105[4] = 1;
        ai105[5] = 4;
        ai[104] = ai105;
        int ai106[] = new int[6];
        ai106[0] = 2;
        ai106[1] = 1;
        ai106[2] = 1;
        ai106[3] = 2;
        ai106[4] = 3;
        ai106[5] = 2;
        ai[105] = ai106;
        int ai107[] = new int[7];
        ai107[0] = 2;
        ai107[1] = 3;
        ai107[2] = 3;
        ai107[3] = 1;
        ai107[4] = 1;
        ai107[5] = 1;
        ai107[6] = 2;
        ai[106] = ai107;
        CODE_PATTERNS = ai;
    }
}
