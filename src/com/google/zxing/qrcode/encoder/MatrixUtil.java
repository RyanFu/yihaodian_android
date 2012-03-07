// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.qrcode.encoder;

import com.google.zxing.WriterException;
import com.google.zxing.common.BitArray;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

// Referenced classes of package com.google.zxing.qrcode.encoder:
//            ByteMatrix, MaskUtil, QRCode

public final class MatrixUtil
{

    private MatrixUtil()
    {
    }

    public static void buildMatrix(BitArray bitarray, ErrorCorrectionLevel errorcorrectionlevel, int i, int j, ByteMatrix bytematrix)
        throws WriterException
    {
        clearMatrix(bytematrix);
        embedBasicPatterns(i, bytematrix);
        embedTypeInfo(errorcorrectionlevel, j, bytematrix);
        maybeEmbedVersionInfo(i, bytematrix);
        embedDataBits(bitarray, j, bytematrix);
    }

    public static int calculateBCHCode(int i, int j)
    {
        int k = findMSBSet(j);
        int l = i << k - 1;
        do
        {
            if(findMSBSet(l) < k)
                return l;
            l ^= j << findMSBSet(l) - k;
        } while(true);
    }

    public static void clearMatrix(ByteMatrix bytematrix)
    {
        bytematrix.clear((byte)-1);
    }

    public static void embedBasicPatterns(int i, ByteMatrix bytematrix)
        throws WriterException
    {
        embedPositionDetectionPatternsAndSeparators(bytematrix);
        embedDarkDotAtLeftBottomCorner(bytematrix);
        maybeEmbedPositionAdjustmentPatterns(i, bytematrix);
        embedTimingPatterns(bytematrix);
    }

    private static void embedDarkDotAtLeftBottomCorner(ByteMatrix bytematrix)
        throws WriterException
    {
        if(bytematrix.get(8, bytematrix.getHeight() - 8) == 0)
        {
            throw new WriterException();
        } else
        {
            bytematrix.set(8, bytematrix.getHeight() - 8, 1);
            return;
        }
    }

    public static void embedDataBits(BitArray bitarray, int i, ByteMatrix bytematrix)
        throws WriterException
    {
        int j;
        int k;
        int l;
        int i1;
        j = 0;
        k = -1;
        l = bytematrix.getWidth() - 1;
        i1 = bytematrix.getHeight() - 1;
_L1:
        int j1;
        int k1;
        boolean flag;
        if(l <= 0)
            if(j != bitarray.getSize())
                throw new WriterException((new StringBuilder("Not all bits consumed: ")).append(j).append('/').append(bitarray.getSize()).toString());
            else
                return;
        if(l == 6)
            l--;
label0:
        {
            if(i1 >= 0 && i1 < bytematrix.getHeight())
                break label0;
            k = -k;
            i1 += k;
            l += -2;
        }
          goto _L1
        j1 = 0;
_L3:
label1:
        {
            if(j1 < 2)
                break label1;
            i1 += k;
        }
        break MISSING_BLOCK_LABEL_85;
        k1 = l - j1;
        if(isEmpty(bytematrix.get(k1, i1)))
        {
            if(j < bitarray.getSize())
            {
                flag = bitarray.get(j);
                j++;
            } else
            {
                flag = false;
            }
            if(i != -1 && MaskUtil.getDataMaskBit(i, k1, i1))
                if(flag)
                    flag = false;
                else
                    flag = true;
            bytematrix.set(k1, i1, flag);
        }
        j1++;
        if(true) goto _L3; else goto _L2
_L2:
    }

    private static void embedHorizontalSeparationPattern(int i, int j, ByteMatrix bytematrix)
        throws WriterException
    {
        if(HORIZONTAL_SEPARATION_PATTERN[0].length != 8 || HORIZONTAL_SEPARATION_PATTERN.length != 1)
            throw new WriterException("Bad horizontal separation pattern");
        int k = 0;
        do
        {
            if(k >= 8)
                return;
            if(!isEmpty(bytematrix.get(i + k, j)))
                throw new WriterException();
            bytematrix.set(i + k, j, HORIZONTAL_SEPARATION_PATTERN[0][k]);
            k++;
        } while(true);
    }

    private static void embedPositionAdjustmentPattern(int i, int j, ByteMatrix bytematrix)
        throws WriterException
    {
        int k;
        if(POSITION_ADJUSTMENT_PATTERN[0].length != 5 || POSITION_ADJUSTMENT_PATTERN.length != 5)
            throw new WriterException("Bad position adjustment");
        k = 0;
_L2:
        if(k >= 5)
            return;
        int l = 0;
        do
        {
label0:
            {
                if(l < 5)
                    break label0;
                k++;
            }
            if(true)
                continue;
            if(!isEmpty(bytematrix.get(i + l, j + k)))
                throw new WriterException();
            bytematrix.set(i + l, j + k, POSITION_ADJUSTMENT_PATTERN[k][l]);
            l++;
        } while(true);
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static void embedPositionDetectionPattern(int i, int j, ByteMatrix bytematrix)
        throws WriterException
    {
        int k;
        if(POSITION_DETECTION_PATTERN[0].length != 7 || POSITION_DETECTION_PATTERN.length != 7)
            throw new WriterException("Bad position detection pattern");
        k = 0;
_L2:
        if(k >= 7)
            return;
        int l = 0;
        do
        {
label0:
            {
                if(l < 7)
                    break label0;
                k++;
            }
            if(true)
                continue;
            if(!isEmpty(bytematrix.get(i + l, j + k)))
                throw new WriterException();
            bytematrix.set(i + l, j + k, POSITION_DETECTION_PATTERN[k][l]);
            l++;
        } while(true);
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static void embedPositionDetectionPatternsAndSeparators(ByteMatrix bytematrix)
        throws WriterException
    {
        int i = POSITION_DETECTION_PATTERN[0].length;
        embedPositionDetectionPattern(0, 0, bytematrix);
        embedPositionDetectionPattern(bytematrix.getWidth() - i, 0, bytematrix);
        embedPositionDetectionPattern(0, bytematrix.getWidth() - i, bytematrix);
        int j = HORIZONTAL_SEPARATION_PATTERN[0].length;
        embedHorizontalSeparationPattern(0, j - 1, bytematrix);
        embedHorizontalSeparationPattern(bytematrix.getWidth() - j, j - 1, bytematrix);
        embedHorizontalSeparationPattern(0, bytematrix.getWidth() - j, bytematrix);
        int k = VERTICAL_SEPARATION_PATTERN.length;
        embedVerticalSeparationPattern(k, 0, bytematrix);
        embedVerticalSeparationPattern(bytematrix.getHeight() - k - 1, 0, bytematrix);
        embedVerticalSeparationPattern(k, bytematrix.getHeight() - k, bytematrix);
    }

    private static void embedTimingPatterns(ByteMatrix bytematrix)
        throws WriterException
    {
        int i = 8;
        do
        {
            if(i >= bytematrix.getWidth() - 8)
                return;
            int j = (i + 1) % 2;
            if(!isValidValue(bytematrix.get(i, 6)))
                throw new WriterException();
            if(isEmpty(bytematrix.get(i, 6)))
                bytematrix.set(i, 6, j);
            if(!isValidValue(bytematrix.get(6, i)))
                throw new WriterException();
            if(isEmpty(bytematrix.get(6, i)))
                bytematrix.set(6, i, j);
            i++;
        } while(true);
    }

    public static void embedTypeInfo(ErrorCorrectionLevel errorcorrectionlevel, int i, ByteMatrix bytematrix)
        throws WriterException
    {
        BitArray bitarray = new BitArray();
        makeTypeInfoBits(errorcorrectionlevel, i, bitarray);
        int j = 0;
        do
        {
            if(j >= bitarray.getSize())
                return;
            boolean flag = bitarray.get(bitarray.getSize() - 1 - j);
            bytematrix.set(TYPE_INFO_COORDINATES[j][0], TYPE_INFO_COORDINATES[j][1], flag);
            if(j < 8)
                bytematrix.set(bytematrix.getWidth() - j - 1, 8, flag);
            else
                bytematrix.set(8, (bytematrix.getHeight() - 7) + (j - 8), flag);
            j++;
        } while(true);
    }

    private static void embedVerticalSeparationPattern(int i, int j, ByteMatrix bytematrix)
        throws WriterException
    {
        if(VERTICAL_SEPARATION_PATTERN[0].length != 1 || VERTICAL_SEPARATION_PATTERN.length != 7)
            throw new WriterException("Bad vertical separation pattern");
        int k = 0;
        do
        {
            if(k >= 7)
                return;
            if(!isEmpty(bytematrix.get(i, j + k)))
                throw new WriterException();
            bytematrix.set(i, j + k, VERTICAL_SEPARATION_PATTERN[k][0]);
            k++;
        } while(true);
    }

    public static int findMSBSet(int i)
    {
        int j = 0;
        do
        {
            if(i == 0)
                return j;
            i >>>= 1;
            j++;
        } while(true);
    }

    private static boolean isEmpty(int i)
    {
        boolean flag;
        if(i == -1)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private static boolean isValidValue(int i)
    {
        boolean flag;
        if(i != -1 && i != 0 && i != 1)
            flag = false;
        else
            flag = true;
        return flag;
    }

    public static void makeTypeInfoBits(ErrorCorrectionLevel errorcorrectionlevel, int i, BitArray bitarray)
        throws WriterException
    {
        if(!QRCode.isValidMaskPattern(i))
            throw new WriterException("Invalid mask pattern");
        int j = i | errorcorrectionlevel.getBits() << 3;
        bitarray.appendBits(j, 5);
        bitarray.appendBits(calculateBCHCode(j, 1335), 10);
        BitArray bitarray1 = new BitArray();
        bitarray1.appendBits(21522, 15);
        bitarray.xor(bitarray1);
        if(bitarray.getSize() != 15)
            throw new WriterException((new StringBuilder("should not happen but we got: ")).append(bitarray.getSize()).toString());
        else
            return;
    }

    public static void makeVersionInfoBits(int i, BitArray bitarray)
        throws WriterException
    {
        bitarray.appendBits(i, 6);
        bitarray.appendBits(calculateBCHCode(i, 7973), 12);
        if(bitarray.getSize() != 18)
            throw new WriterException((new StringBuilder("should not happen but we got: ")).append(bitarray.getSize()).toString());
        else
            return;
    }

    private static void maybeEmbedPositionAdjustmentPatterns(int i, ByteMatrix bytematrix)
        throws WriterException
    {
        if(i >= 2) goto _L2; else goto _L1
_L1:
        return;
_L2:
        int ai[];
        int k;
        int l;
        int j = i - 1;
        ai = POSITION_ADJUSTMENT_PATTERN_COORDINATE_TABLE[j];
        k = POSITION_ADJUSTMENT_PATTERN_COORDINATE_TABLE[j].length;
        l = 0;
_L4:
        if(l >= k) goto _L1; else goto _L3
_L3:
        int i1 = 0;
_L5:
label0:
        {
            if(i1 < k)
                break label0;
            l++;
        }
          goto _L4
        int j1 = ai[l];
        int k1 = ai[i1];
        if(k1 != -1 && j1 != -1 && isEmpty(bytematrix.get(k1, j1)))
            embedPositionAdjustmentPattern(k1 - 2, j1 - 2, bytematrix);
        i1++;
          goto _L5
    }

    public static void maybeEmbedVersionInfo(int i, ByteMatrix bytematrix)
        throws WriterException
    {
        if(i >= 7) goto _L2; else goto _L1
_L1:
        return;
_L2:
        BitArray bitarray;
        int j;
        int k;
        bitarray = new BitArray();
        makeVersionInfoBits(i, bitarray);
        j = 17;
        k = 0;
_L4:
        if(k >= 6) goto _L1; else goto _L3
_L3:
        int l = 0;
_L5:
label0:
        {
            if(l < 3)
                break label0;
            k++;
        }
          goto _L4
          goto _L1
        boolean flag = bitarray.get(j);
        j--;
        bytematrix.set(k, l + (bytematrix.getHeight() - 11), flag);
        bytematrix.set(l + (bytematrix.getHeight() - 11), k, flag);
        l++;
          goto _L5
    }

    private static final int HORIZONTAL_SEPARATION_PATTERN[][];
    private static final int POSITION_ADJUSTMENT_PATTERN[][];
    private static final int POSITION_ADJUSTMENT_PATTERN_COORDINATE_TABLE[][];
    private static final int POSITION_DETECTION_PATTERN[][];
    private static final int TYPE_INFO_COORDINATES[][];
    private static final int TYPE_INFO_MASK_PATTERN = 21522;
    private static final int TYPE_INFO_POLY = 1335;
    private static final int VERSION_INFO_POLY = 7973;
    private static final int VERTICAL_SEPARATION_PATTERN[][];

    static 
    {
        int ai[][] = new int[7][];
        int ai1[] = new int[7];
        ai1[0] = 1;
        ai1[1] = 1;
        ai1[2] = 1;
        ai1[3] = 1;
        ai1[4] = 1;
        ai1[5] = 1;
        ai1[6] = 1;
        ai[0] = ai1;
        int ai2[] = new int[7];
        ai2[0] = 1;
        ai2[6] = 1;
        ai[1] = ai2;
        int ai3[] = new int[7];
        ai3[0] = 1;
        ai3[2] = 1;
        ai3[3] = 1;
        ai3[4] = 1;
        ai3[6] = 1;
        ai[2] = ai3;
        int ai4[] = new int[7];
        ai4[0] = 1;
        ai4[2] = 1;
        ai4[3] = 1;
        ai4[4] = 1;
        ai4[6] = 1;
        ai[3] = ai4;
        int ai5[] = new int[7];
        ai5[0] = 1;
        ai5[2] = 1;
        ai5[3] = 1;
        ai5[4] = 1;
        ai5[6] = 1;
        ai[4] = ai5;
        int ai6[] = new int[7];
        ai6[0] = 1;
        ai6[6] = 1;
        ai[5] = ai6;
        int ai7[] = new int[7];
        ai7[0] = 1;
        ai7[1] = 1;
        ai7[2] = 1;
        ai7[3] = 1;
        ai7[4] = 1;
        ai7[5] = 1;
        ai7[6] = 1;
        ai[6] = ai7;
        POSITION_DETECTION_PATTERN = ai;
        int ai8[][] = new int[1][];
        ai8[0] = new int[8];
        HORIZONTAL_SEPARATION_PATTERN = ai8;
        int ai9[][] = new int[7][];
        ai9[0] = new int[1];
        ai9[1] = new int[1];
        ai9[2] = new int[1];
        ai9[3] = new int[1];
        ai9[4] = new int[1];
        ai9[5] = new int[1];
        ai9[6] = new int[1];
        VERTICAL_SEPARATION_PATTERN = ai9;
        int ai10[][] = new int[5][];
        int ai11[] = new int[5];
        ai11[0] = 1;
        ai11[1] = 1;
        ai11[2] = 1;
        ai11[3] = 1;
        ai11[4] = 1;
        ai10[0] = ai11;
        int ai12[] = new int[5];
        ai12[0] = 1;
        ai12[4] = 1;
        ai10[1] = ai12;
        int ai13[] = new int[5];
        ai13[0] = 1;
        ai13[2] = 1;
        ai13[4] = 1;
        ai10[2] = ai13;
        int ai14[] = new int[5];
        ai14[0] = 1;
        ai14[4] = 1;
        ai10[3] = ai14;
        int ai15[] = new int[5];
        ai15[0] = 1;
        ai15[1] = 1;
        ai15[2] = 1;
        ai15[3] = 1;
        ai15[4] = 1;
        ai10[4] = ai15;
        POSITION_ADJUSTMENT_PATTERN = ai10;
        int ai16[][] = new int[40][];
        int ai17[] = new int[7];
        ai17[0] = -1;
        ai17[1] = -1;
        ai17[2] = -1;
        ai17[3] = -1;
        ai17[4] = -1;
        ai17[5] = -1;
        ai17[6] = -1;
        ai16[0] = ai17;
        int ai18[] = new int[7];
        ai18[0] = 6;
        ai18[1] = 18;
        ai18[2] = -1;
        ai18[3] = -1;
        ai18[4] = -1;
        ai18[5] = -1;
        ai18[6] = -1;
        ai16[1] = ai18;
        int ai19[] = new int[7];
        ai19[0] = 6;
        ai19[1] = 22;
        ai19[2] = -1;
        ai19[3] = -1;
        ai19[4] = -1;
        ai19[5] = -1;
        ai19[6] = -1;
        ai16[2] = ai19;
        int ai20[] = new int[7];
        ai20[0] = 6;
        ai20[1] = 26;
        ai20[2] = -1;
        ai20[3] = -1;
        ai20[4] = -1;
        ai20[5] = -1;
        ai20[6] = -1;
        ai16[3] = ai20;
        int ai21[] = new int[7];
        ai21[0] = 6;
        ai21[1] = 30;
        ai21[2] = -1;
        ai21[3] = -1;
        ai21[4] = -1;
        ai21[5] = -1;
        ai21[6] = -1;
        ai16[4] = ai21;
        int ai22[] = new int[7];
        ai22[0] = 6;
        ai22[1] = 34;
        ai22[2] = -1;
        ai22[3] = -1;
        ai22[4] = -1;
        ai22[5] = -1;
        ai22[6] = -1;
        ai16[5] = ai22;
        int ai23[] = new int[7];
        ai23[0] = 6;
        ai23[1] = 22;
        ai23[2] = 38;
        ai23[3] = -1;
        ai23[4] = -1;
        ai23[5] = -1;
        ai23[6] = -1;
        ai16[6] = ai23;
        int ai24[] = new int[7];
        ai24[0] = 6;
        ai24[1] = 24;
        ai24[2] = 42;
        ai24[3] = -1;
        ai24[4] = -1;
        ai24[5] = -1;
        ai24[6] = -1;
        ai16[7] = ai24;
        int ai25[] = new int[7];
        ai25[0] = 6;
        ai25[1] = 26;
        ai25[2] = 46;
        ai25[3] = -1;
        ai25[4] = -1;
        ai25[5] = -1;
        ai25[6] = -1;
        ai16[8] = ai25;
        int ai26[] = new int[7];
        ai26[0] = 6;
        ai26[1] = 28;
        ai26[2] = 50;
        ai26[3] = -1;
        ai26[4] = -1;
        ai26[5] = -1;
        ai26[6] = -1;
        ai16[9] = ai26;
        int ai27[] = new int[7];
        ai27[0] = 6;
        ai27[1] = 30;
        ai27[2] = 54;
        ai27[3] = -1;
        ai27[4] = -1;
        ai27[5] = -1;
        ai27[6] = -1;
        ai16[10] = ai27;
        int ai28[] = new int[7];
        ai28[0] = 6;
        ai28[1] = 32;
        ai28[2] = 58;
        ai28[3] = -1;
        ai28[4] = -1;
        ai28[5] = -1;
        ai28[6] = -1;
        ai16[11] = ai28;
        int ai29[] = new int[7];
        ai29[0] = 6;
        ai29[1] = 34;
        ai29[2] = 62;
        ai29[3] = -1;
        ai29[4] = -1;
        ai29[5] = -1;
        ai29[6] = -1;
        ai16[12] = ai29;
        int ai30[] = new int[7];
        ai30[0] = 6;
        ai30[1] = 26;
        ai30[2] = 46;
        ai30[3] = 66;
        ai30[4] = -1;
        ai30[5] = -1;
        ai30[6] = -1;
        ai16[13] = ai30;
        int ai31[] = new int[7];
        ai31[0] = 6;
        ai31[1] = 26;
        ai31[2] = 48;
        ai31[3] = 70;
        ai31[4] = -1;
        ai31[5] = -1;
        ai31[6] = -1;
        ai16[14] = ai31;
        int ai32[] = new int[7];
        ai32[0] = 6;
        ai32[1] = 26;
        ai32[2] = 50;
        ai32[3] = 74;
        ai32[4] = -1;
        ai32[5] = -1;
        ai32[6] = -1;
        ai16[15] = ai32;
        int ai33[] = new int[7];
        ai33[0] = 6;
        ai33[1] = 30;
        ai33[2] = 54;
        ai33[3] = 78;
        ai33[4] = -1;
        ai33[5] = -1;
        ai33[6] = -1;
        ai16[16] = ai33;
        int ai34[] = new int[7];
        ai34[0] = 6;
        ai34[1] = 30;
        ai34[2] = 56;
        ai34[3] = 82;
        ai34[4] = -1;
        ai34[5] = -1;
        ai34[6] = -1;
        ai16[17] = ai34;
        int ai35[] = new int[7];
        ai35[0] = 6;
        ai35[1] = 30;
        ai35[2] = 58;
        ai35[3] = 86;
        ai35[4] = -1;
        ai35[5] = -1;
        ai35[6] = -1;
        ai16[18] = ai35;
        int ai36[] = new int[7];
        ai36[0] = 6;
        ai36[1] = 34;
        ai36[2] = 62;
        ai36[3] = 90;
        ai36[4] = -1;
        ai36[5] = -1;
        ai36[6] = -1;
        ai16[19] = ai36;
        int ai37[] = new int[7];
        ai37[0] = 6;
        ai37[1] = 28;
        ai37[2] = 50;
        ai37[3] = 72;
        ai37[4] = 94;
        ai37[5] = -1;
        ai37[6] = -1;
        ai16[20] = ai37;
        int ai38[] = new int[7];
        ai38[0] = 6;
        ai38[1] = 26;
        ai38[2] = 50;
        ai38[3] = 74;
        ai38[4] = 98;
        ai38[5] = -1;
        ai38[6] = -1;
        ai16[21] = ai38;
        int ai39[] = new int[7];
        ai39[0] = 6;
        ai39[1] = 30;
        ai39[2] = 54;
        ai39[3] = 78;
        ai39[4] = 102;
        ai39[5] = -1;
        ai39[6] = -1;
        ai16[22] = ai39;
        int ai40[] = new int[7];
        ai40[0] = 6;
        ai40[1] = 28;
        ai40[2] = 54;
        ai40[3] = 80;
        ai40[4] = 106;
        ai40[5] = -1;
        ai40[6] = -1;
        ai16[23] = ai40;
        int ai41[] = new int[7];
        ai41[0] = 6;
        ai41[1] = 32;
        ai41[2] = 58;
        ai41[3] = 84;
        ai41[4] = 110;
        ai41[5] = -1;
        ai41[6] = -1;
        ai16[24] = ai41;
        int ai42[] = new int[7];
        ai42[0] = 6;
        ai42[1] = 30;
        ai42[2] = 58;
        ai42[3] = 86;
        ai42[4] = 114;
        ai42[5] = -1;
        ai42[6] = -1;
        ai16[25] = ai42;
        int ai43[] = new int[7];
        ai43[0] = 6;
        ai43[1] = 34;
        ai43[2] = 62;
        ai43[3] = 90;
        ai43[4] = 118;
        ai43[5] = -1;
        ai43[6] = -1;
        ai16[26] = ai43;
        int ai44[] = new int[7];
        ai44[0] = 6;
        ai44[1] = 26;
        ai44[2] = 50;
        ai44[3] = 74;
        ai44[4] = 98;
        ai44[5] = 122;
        ai44[6] = -1;
        ai16[27] = ai44;
        int ai45[] = new int[7];
        ai45[0] = 6;
        ai45[1] = 30;
        ai45[2] = 54;
        ai45[3] = 78;
        ai45[4] = 102;
        ai45[5] = 126;
        ai45[6] = -1;
        ai16[28] = ai45;
        int ai46[] = new int[7];
        ai46[0] = 6;
        ai46[1] = 26;
        ai46[2] = 52;
        ai46[3] = 78;
        ai46[4] = 104;
        ai46[5] = 130;
        ai46[6] = -1;
        ai16[29] = ai46;
        int ai47[] = new int[7];
        ai47[0] = 6;
        ai47[1] = 30;
        ai47[2] = 56;
        ai47[3] = 82;
        ai47[4] = 108;
        ai47[5] = 134;
        ai47[6] = -1;
        ai16[30] = ai47;
        int ai48[] = new int[7];
        ai48[0] = 6;
        ai48[1] = 34;
        ai48[2] = 60;
        ai48[3] = 86;
        ai48[4] = 112;
        ai48[5] = 138;
        ai48[6] = -1;
        ai16[31] = ai48;
        int ai49[] = new int[7];
        ai49[0] = 6;
        ai49[1] = 30;
        ai49[2] = 58;
        ai49[3] = 86;
        ai49[4] = 114;
        ai49[5] = 142;
        ai49[6] = -1;
        ai16[32] = ai49;
        int ai50[] = new int[7];
        ai50[0] = 6;
        ai50[1] = 34;
        ai50[2] = 62;
        ai50[3] = 90;
        ai50[4] = 118;
        ai50[5] = 146;
        ai50[6] = -1;
        ai16[33] = ai50;
        int ai51[] = new int[7];
        ai51[0] = 6;
        ai51[1] = 30;
        ai51[2] = 54;
        ai51[3] = 78;
        ai51[4] = 102;
        ai51[5] = 126;
        ai51[6] = 150;
        ai16[34] = ai51;
        int ai52[] = new int[7];
        ai52[0] = 6;
        ai52[1] = 24;
        ai52[2] = 50;
        ai52[3] = 76;
        ai52[4] = 102;
        ai52[5] = 128;
        ai52[6] = 154;
        ai16[35] = ai52;
        int ai53[] = new int[7];
        ai53[0] = 6;
        ai53[1] = 28;
        ai53[2] = 54;
        ai53[3] = 80;
        ai53[4] = 106;
        ai53[5] = 132;
        ai53[6] = 158;
        ai16[36] = ai53;
        int ai54[] = new int[7];
        ai54[0] = 6;
        ai54[1] = 32;
        ai54[2] = 58;
        ai54[3] = 84;
        ai54[4] = 110;
        ai54[5] = 136;
        ai54[6] = 162;
        ai16[37] = ai54;
        int ai55[] = new int[7];
        ai55[0] = 6;
        ai55[1] = 26;
        ai55[2] = 54;
        ai55[3] = 82;
        ai55[4] = 110;
        ai55[5] = 138;
        ai55[6] = 166;
        ai16[38] = ai55;
        int ai56[] = new int[7];
        ai56[0] = 6;
        ai56[1] = 30;
        ai56[2] = 58;
        ai56[3] = 86;
        ai56[4] = 114;
        ai56[5] = 142;
        ai56[6] = 170;
        ai16[39] = ai56;
        POSITION_ADJUSTMENT_PATTERN_COORDINATE_TABLE = ai16;
        int ai57[][] = new int[15][];
        int ai58[] = new int[2];
        ai58[0] = 8;
        ai57[0] = ai58;
        int ai59[] = new int[2];
        ai59[0] = 8;
        ai59[1] = 1;
        ai57[1] = ai59;
        int ai60[] = new int[2];
        ai60[0] = 8;
        ai60[1] = 2;
        ai57[2] = ai60;
        int ai61[] = new int[2];
        ai61[0] = 8;
        ai61[1] = 3;
        ai57[3] = ai61;
        int ai62[] = new int[2];
        ai62[0] = 8;
        ai62[1] = 4;
        ai57[4] = ai62;
        int ai63[] = new int[2];
        ai63[0] = 8;
        ai63[1] = 5;
        ai57[5] = ai63;
        int ai64[] = new int[2];
        ai64[0] = 8;
        ai64[1] = 7;
        ai57[6] = ai64;
        int ai65[] = new int[2];
        ai65[0] = 8;
        ai65[1] = 8;
        ai57[7] = ai65;
        int ai66[] = new int[2];
        ai66[0] = 7;
        ai66[1] = 8;
        ai57[8] = ai66;
        int ai67[] = new int[2];
        ai67[0] = 5;
        ai67[1] = 8;
        ai57[9] = ai67;
        int ai68[] = new int[2];
        ai68[0] = 4;
        ai68[1] = 8;
        ai57[10] = ai68;
        int ai69[] = new int[2];
        ai69[0] = 3;
        ai69[1] = 8;
        ai57[11] = ai69;
        int ai70[] = new int[2];
        ai70[0] = 2;
        ai70[1] = 8;
        ai57[12] = ai70;
        int ai71[] = new int[2];
        ai71[0] = 1;
        ai71[1] = 8;
        ai57[13] = ai71;
        int ai72[] = new int[2];
        ai72[1] = 8;
        ai57[14] = ai72;
        TYPE_INFO_COORDINATES = ai57;
    }
}
