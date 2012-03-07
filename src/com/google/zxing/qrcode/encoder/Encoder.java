// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.qrcode.encoder;

import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.*;
import com.google.zxing.common.reedsolomon.GF256;
import com.google.zxing.common.reedsolomon.ReedSolomonEncoder;
import com.google.zxing.qrcode.decoder.*;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.Vector;

// Referenced classes of package com.google.zxing.qrcode.encoder:
//            MaskUtil, MatrixUtil, QRCode, ByteMatrix, 
//            BlockPair

public final class Encoder
{

    private Encoder()
    {
    }

    static void append8BitBytes(String s, BitArray bitarray, String s1)
        throws WriterException
    {
        byte abyte0[];
        int i;
        try
        {
            abyte0 = s.getBytes(s1);
        }
        catch(UnsupportedEncodingException unsupportedencodingexception)
        {
            throw new WriterException(unsupportedencodingexception.toString());
        }
        i = 0;
        do
        {
            if(i >= abyte0.length)
                return;
            bitarray.appendBits(abyte0[i], 8);
            i++;
        } while(true);
    }

    static void appendAlphanumericBytes(String s, BitArray bitarray)
        throws WriterException
    {
        int i = s.length();
        int j = 0;
        do
        {
            if(j >= i)
                return;
            int k = getAlphanumericCode(s.charAt(j));
            if(k == -1)
                throw new WriterException();
            if(j + 1 < i)
            {
                int l = getAlphanumericCode(s.charAt(j + 1));
                if(l == -1)
                    throw new WriterException();
                bitarray.appendBits(l + k * 45, 11);
                j += 2;
            } else
            {
                bitarray.appendBits(k, 6);
                j++;
            }
        } while(true);
    }

    static void appendBytes(String s, Mode mode, BitArray bitarray, String s1)
        throws WriterException
    {
        if(mode.equals(Mode.NUMERIC))
            appendNumericBytes(s, bitarray);
        else
        if(mode.equals(Mode.ALPHANUMERIC))
            appendAlphanumericBytes(s, bitarray);
        else
        if(mode.equals(Mode.BYTE))
            append8BitBytes(s, bitarray, s1);
        else
        if(mode.equals(Mode.KANJI))
            appendKanjiBytes(s, bitarray);
        else
            throw new WriterException((new StringBuilder("Invalid mode: ")).append(mode).toString());
    }

    private static void appendECI(ECI eci, BitArray bitarray)
    {
        bitarray.appendBits(Mode.ECI.getBits(), 4);
        bitarray.appendBits(eci.getValue(), 8);
    }

    static void appendKanjiBytes(String s, BitArray bitarray)
        throws WriterException
    {
        byte abyte0[];
        int j;
        int i;
        try
        {
            abyte0 = s.getBytes("Shift_JIS");
        }
        catch(UnsupportedEncodingException unsupportedencodingexception)
        {
            throw new WriterException(unsupportedencodingexception.toString());
        }
        i = abyte0.length;
        j = 0;
_L6:
        int l;
        int i1;
        if(j >= i)
            return;
        int k = 0xff & abyte0[j];
        l = 0xff & abyte0[j + 1] | k << 8;
        i1 = -1;
        if(l < 33088 || l > 40956) goto _L2; else goto _L1
_L1:
        i1 = l - 33088;
_L4:
        if(i1 == -1)
            throw new WriterException("Invalid byte sequence");
        break; /* Loop/switch isn't completed */
_L2:
        if(l >= 57408 && l <= 60351)
            i1 = l - 49472;
        if(true) goto _L4; else goto _L3
_L3:
        bitarray.appendBits(192 * (i1 >> 8) + (i1 & 0xff), 13);
        j += 2;
        if(true) goto _L6; else goto _L5
_L5:
    }

    static void appendLengthInfo(int i, int j, Mode mode, BitArray bitarray)
        throws WriterException
    {
        int k = mode.getCharacterCountBits(Version.getVersionForNumber(j));
        if(i > (1 << k) - 1)
        {
            throw new WriterException((new StringBuilder(String.valueOf(i))).append("is bigger than").append((1 << k) - 1).toString());
        } else
        {
            bitarray.appendBits(i, k);
            return;
        }
    }

    static void appendModeInfo(Mode mode, BitArray bitarray)
    {
        bitarray.appendBits(mode.getBits(), 4);
    }

    static void appendNumericBytes(String s, BitArray bitarray)
    {
        int i = s.length();
        int j = 0;
        do
        {
            if(j >= i)
                return;
            int k = s.charAt(j) - 48;
            if(j + 2 < i)
            {
                int l = s.charAt(j + 1) - 48;
                bitarray.appendBits((s.charAt(j + 2) - 48) + (k * 100 + l * 10), 10);
                j += 3;
            } else
            if(j + 1 < i)
            {
                bitarray.appendBits((s.charAt(j + 1) - 48) + k * 10, 7);
                j += 2;
            } else
            {
                bitarray.appendBits(k, 4);
                j++;
            }
        } while(true);
    }

    private static int calculateMaskPenalty(ByteMatrix bytematrix)
    {
        return 0 + MaskUtil.applyMaskPenaltyRule1(bytematrix) + MaskUtil.applyMaskPenaltyRule2(bytematrix) + MaskUtil.applyMaskPenaltyRule3(bytematrix) + MaskUtil.applyMaskPenaltyRule4(bytematrix);
    }

    private static int chooseMaskPattern(BitArray bitarray, ErrorCorrectionLevel errorcorrectionlevel, int i, ByteMatrix bytematrix)
        throws WriterException
    {
        int j = 0x7fffffff;
        int k = -1;
        int l = 0;
        do
        {
            if(l >= 8)
                return k;
            MatrixUtil.buildMatrix(bitarray, errorcorrectionlevel, i, l, bytematrix);
            int i1 = calculateMaskPenalty(bytematrix);
            if(i1 < j)
            {
                j = i1;
                k = l;
            }
            l++;
        } while(true);
    }

    public static Mode chooseMode(String s)
    {
        return chooseMode(s, null);
    }

    public static Mode chooseMode(String s, String s1)
    {
        if(!"Shift_JIS".equals(s1)) goto _L2; else goto _L1
_L1:
        Mode mode;
        if(isOnlyDoubleByteKanji(s))
            mode = Mode.KANJI;
        else
            mode = Mode.BYTE;
_L7:
        return mode;
_L2:
        boolean flag;
        boolean flag1;
        int i;
        flag = false;
        flag1 = false;
        i = 0;
_L4:
        if(i >= s.length())
        {
            char c;
            if(flag1)
                mode = Mode.ALPHANUMERIC;
            else
            if(flag)
                mode = Mode.NUMERIC;
            else
                mode = Mode.BYTE;
            continue; /* Loop/switch isn't completed */
        }
        c = s.charAt(i);
        if(c < '0' || c > '9')
            break; /* Loop/switch isn't completed */
        flag = true;
_L5:
        i++;
        if(true) goto _L4; else goto _L3
_L3:
label0:
        {
            if(getAlphanumericCode(c) == -1)
                break label0;
            flag1 = true;
        }
          goto _L5
          goto _L4
        mode = Mode.BYTE;
        if(true) goto _L7; else goto _L6
_L6:
    }

    public static void encode(String s, ErrorCorrectionLevel errorcorrectionlevel, QRCode qrcode)
        throws WriterException
    {
        encode(s, errorcorrectionlevel, null, qrcode);
    }

    public static void encode(String s, ErrorCorrectionLevel errorcorrectionlevel, Hashtable hashtable, QRCode qrcode)
        throws WriterException
    {
        String s1;
        Mode mode;
        BitArray bitarray;
        BitArray bitarray1;
        int i;
        BitArray bitarray2;
        ByteMatrix bytematrix;
        if(hashtable == null)
            s1 = null;
        else
            s1 = (String)hashtable.get(EncodeHintType.CHARACTER_SET);
        if(s1 == null)
            s1 = "ISO-8859-1";
        mode = chooseMode(s, s1);
        bitarray = new BitArray();
        appendBytes(s, mode, bitarray, s1);
        initQRCode(bitarray.getSizeInBytes(), errorcorrectionlevel, mode, qrcode);
        bitarray1 = new BitArray();
        if(mode == Mode.BYTE && !"ISO-8859-1".equals(s1))
        {
            CharacterSetECI characterseteci = CharacterSetECI.getCharacterSetECIByName(s1);
            if(characterseteci != null)
                appendECI(characterseteci, bitarray1);
        }
        appendModeInfo(mode, bitarray1);
        if(mode.equals(Mode.BYTE))
            i = bitarray.getSizeInBytes();
        else
            i = s.length();
        appendLengthInfo(i, qrcode.getVersion(), mode, bitarray1);
        bitarray1.appendBitArray(bitarray);
        terminateBits(qrcode.getNumDataBytes(), bitarray1);
        bitarray2 = new BitArray();
        interleaveWithECBytes(bitarray1, qrcode.getNumTotalBytes(), qrcode.getNumDataBytes(), qrcode.getNumRSBlocks(), bitarray2);
        bytematrix = new ByteMatrix(qrcode.getMatrixWidth(), qrcode.getMatrixWidth());
        qrcode.setMaskPattern(chooseMaskPattern(bitarray2, qrcode.getECLevel(), qrcode.getVersion(), bytematrix));
        MatrixUtil.buildMatrix(bitarray2, qrcode.getECLevel(), qrcode.getVersion(), qrcode.getMaskPattern(), bytematrix);
        qrcode.setMatrix(bytematrix);
        if(!qrcode.isValid())
            throw new WriterException((new StringBuilder("Invalid QR code: ")).append(qrcode.toString()).toString());
        else
            return;
    }

    static byte[] generateECBytes(byte abyte0[], int i)
    {
        int j;
        int ai[];
        int k;
        j = abyte0.length;
        ai = new int[j + i];
        k = 0;
_L3:
        if(k < j) goto _L2; else goto _L1
_L1:
        byte abyte1[];
        int l;
        (new ReedSolomonEncoder(GF256.QR_CODE_FIELD)).encode(ai, i);
        abyte1 = new byte[i];
        l = 0;
_L4:
        if(l >= i)
            return abyte1;
        break MISSING_BLOCK_LABEL_68;
_L2:
        ai[k] = 0xff & abyte0[k];
        k++;
          goto _L3
        abyte1[l] = (byte)ai[j + l];
        l++;
          goto _L4
    }

    static int getAlphanumericCode(int i)
    {
        int j;
        if(i < ALPHANUMERIC_TABLE.length)
            j = ALPHANUMERIC_TABLE[i];
        else
            j = -1;
        return j;
    }

    static void getNumDataBytesAndNumECBytesForBlockID(int i, int j, int k, int l, int ai[], int ai1[])
        throws WriterException
    {
        if(l >= k)
            throw new WriterException("Block ID too large");
        int i1 = i % k;
        int j1 = k - i1;
        int k1 = i / k;
        int l1 = k1 + 1;
        int i2 = j / k;
        int j2 = i2 + 1;
        int k2 = k1 - i2;
        int l2 = l1 - j2;
        if(k2 != l2)
            throw new WriterException("EC bytes mismatch");
        if(k != j1 + i1)
            throw new WriterException("RS blocks mismatch");
        if(i != j1 * (i2 + k2) + i1 * (j2 + l2))
            throw new WriterException("Total bytes mismatch");
        if(l < j1)
        {
            ai[0] = i2;
            ai1[0] = k2;
        } else
        {
            ai[0] = j2;
            ai1[0] = l2;
        }
    }

    private static void initQRCode(int i, ErrorCorrectionLevel errorcorrectionlevel, Mode mode, QRCode qrcode)
        throws WriterException
    {
        qrcode.setECLevel(errorcorrectionlevel);
        qrcode.setMode(mode);
        int j = 1;
        do
        {
            if(j > 40)
                throw new WriterException("Cannot find proper rs block info (input data too big?)");
            Version version = Version.getVersionForNumber(j);
            int k = version.getTotalCodewords();
            com.google.zxing.qrcode.decoder.Version.ECBlocks ecblocks = version.getECBlocksForLevel(errorcorrectionlevel);
            int l = ecblocks.getTotalECCodewords();
            int i1 = ecblocks.getNumBlocks();
            int j1 = k - l;
            if(j1 >= i + 3)
            {
                qrcode.setVersion(j);
                qrcode.setNumTotalBytes(k);
                qrcode.setNumDataBytes(j1);
                qrcode.setNumRSBlocks(i1);
                qrcode.setNumECBytes(l);
                qrcode.setMatrixWidth(version.getDimensionForVersion());
                return;
            }
            j++;
        } while(true);
    }

    static void interleaveWithECBytes(BitArray bitarray, int i, int j, int k, BitArray bitarray1)
        throws WriterException
    {
        Vector vector;
        int i1;
        int j1;
        int l2;
        if(bitarray.getSizeInBytes() != j)
            throw new WriterException("Number of bits and data bytes does not match");
        vector = new Vector(k);
        int l = 0;
        i1 = 0;
        j1 = 0;
        int k1 = 0;
        do
        {
            if(l >= k)
            {
                if(j != k1)
                    throw new WriterException("Data bytes does not match offset");
                break;
            }
            int ai[] = new int[1];
            int ai1[] = new int[1];
            getNumDataBytesAndNumECBytesForBlockID(i, j, k, l, ai, ai1);
            int l1 = ai[0];
            byte abyte0[] = new byte[l1];
            bitarray.toBytes(k1 * 8, abyte0, 0, l1);
            byte abyte1[] = generateECBytes(abyte0, ai1[0]);
            vector.addElement(new BlockPair(abyte0, abyte1));
            int i2 = Math.max(j1, l1);
            int j2 = Math.max(i1, abyte1.length);
            int k2 = k1 + ai[0];
            l++;
            i1 = j2;
            j1 = i2;
            k1 = k2;
        } while(true);
        l2 = 0;
_L1:
        int j3;
        if(l2 >= j1)
        {
            j3 = 0;
            break MISSING_BLOCK_LABEL_198;
        }
        i3 = 0;
_L2:
label0:
        {
            if(i3 < vector.size())
                break label0;
            l2++;
        }
          goto _L1
        byte abyte2[] = ((BlockPair)vector.elementAt(i3)).getDataBytes();
        if(l2 < abyte2.length)
            bitarray1.appendBits(abyte2[l2], 8);
        i3++;
          goto _L2
_L3:
        int i3;
        int k3;
        byte abyte3[];
        if(j3 >= i1)
            if(i != bitarray1.getSizeInBytes())
                throw new WriterException((new StringBuilder("Interleaving error: ")).append(i).append(" and ").append(bitarray1.getSizeInBytes()).append(" differ.").toString());
            else
                return;
        k3 = 0;
_L4:
label1:
        {
            if(k3 < vector.size())
                break label1;
            j3++;
        }
          goto _L3
        abyte3 = ((BlockPair)vector.elementAt(k3)).getErrorCorrectionBytes();
        if(j3 < abyte3.length)
            bitarray1.appendBits(abyte3[j3], 8);
        k3++;
          goto _L4
    }

    private static boolean isOnlyDoubleByteKanji(String s)
    {
        boolean flag;
        byte abyte0[];
        int i;
        try
        {
            abyte0 = s.getBytes("Shift_JIS");
        }
        catch(UnsupportedEncodingException unsupportedencodingexception)
        {
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        i = abyte0.length;
        if(i % 2 == 0) goto _L2; else goto _L1
_L1:
        flag = false;
_L4:
        return flag;
_L2:
        int j = 0;
_L5:
        if(j >= i)
        {
            flag = true;
        } else
        {
label0:
            {
                int k = 0xff & abyte0[j];
                if(k >= 129 && k <= 159 || k >= 224 && k <= 235)
                    break label0;
                flag = false;
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
        j += 2;
          goto _L5
        if(true) goto _L4; else goto _L6
_L6:
    }

    static void terminateBits(int i, BitArray bitarray)
        throws WriterException
    {
        int j;
        int k;
        j = i << 3;
        if(bitarray.getSize() > j)
            throw new WriterException((new StringBuilder("data bits cannot fit in the QR Code")).append(bitarray.getSize()).append(" > ").append(j).toString());
        k = 0;
_L6:
        if(k < 4 && bitarray.getSize() < j) goto _L2; else goto _L1
_L1:
        int l = 7 & bitarray.getSize();
        if(l <= 0) goto _L4; else goto _L3
_L3:
        int k1 = l;
_L7:
        if(k1 < 8) goto _L5; else goto _L4
_L4:
        int i1;
        int j1;
        i1 = i - bitarray.getSizeInBytes();
        j1 = 0;
_L8:
        char c;
        if(j1 >= i1)
            if(bitarray.getSize() != j)
                throw new WriterException("Bits size does not equal capacity");
            else
                return;
        break MISSING_BLOCK_LABEL_149;
_L2:
        bitarray.appendBit(false);
        k++;
          goto _L6
_L5:
        bitarray.appendBit(false);
        k1++;
          goto _L7
        if((j1 & 1) == 0)
            c = '\354';
        else
            c = '\021';
        bitarray.appendBits(c, 8);
        j1++;
          goto _L8
    }

    private static final int ALPHANUMERIC_TABLE[];
    static final String DEFAULT_BYTE_MODE_ENCODING = "ISO-8859-1";

    static 
    {
        int ai[] = new int[96];
        ai[0] = -1;
        ai[1] = -1;
        ai[2] = -1;
        ai[3] = -1;
        ai[4] = -1;
        ai[5] = -1;
        ai[6] = -1;
        ai[7] = -1;
        ai[8] = -1;
        ai[9] = -1;
        ai[10] = -1;
        ai[11] = -1;
        ai[12] = -1;
        ai[13] = -1;
        ai[14] = -1;
        ai[15] = -1;
        ai[16] = -1;
        ai[17] = -1;
        ai[18] = -1;
        ai[19] = -1;
        ai[20] = -1;
        ai[21] = -1;
        ai[22] = -1;
        ai[23] = -1;
        ai[24] = -1;
        ai[25] = -1;
        ai[26] = -1;
        ai[27] = -1;
        ai[28] = -1;
        ai[29] = -1;
        ai[30] = -1;
        ai[31] = -1;
        ai[32] = 36;
        ai[33] = -1;
        ai[34] = -1;
        ai[35] = -1;
        ai[36] = 37;
        ai[37] = 38;
        ai[38] = -1;
        ai[39] = -1;
        ai[40] = -1;
        ai[41] = -1;
        ai[42] = 39;
        ai[43] = 40;
        ai[44] = -1;
        ai[45] = 41;
        ai[46] = 42;
        ai[47] = 43;
        ai[49] = 1;
        ai[50] = 2;
        ai[51] = 3;
        ai[52] = 4;
        ai[53] = 5;
        ai[54] = 6;
        ai[55] = 7;
        ai[56] = 8;
        ai[57] = 9;
        ai[58] = 44;
        ai[59] = -1;
        ai[60] = -1;
        ai[61] = -1;
        ai[62] = -1;
        ai[63] = -1;
        ai[64] = -1;
        ai[65] = 10;
        ai[66] = 11;
        ai[67] = 12;
        ai[68] = 13;
        ai[69] = 14;
        ai[70] = 15;
        ai[71] = 16;
        ai[72] = 17;
        ai[73] = 18;
        ai[74] = 19;
        ai[75] = 20;
        ai[76] = 21;
        ai[77] = 22;
        ai[78] = 23;
        ai[79] = 24;
        ai[80] = 25;
        ai[81] = 26;
        ai[82] = 27;
        ai[83] = 28;
        ai[84] = 29;
        ai[85] = 30;
        ai[86] = 31;
        ai[87] = 32;
        ai[88] = 33;
        ai[89] = 34;
        ai[90] = 35;
        ai[91] = -1;
        ai[92] = -1;
        ai[93] = -1;
        ai[94] = -1;
        ai[95] = -1;
        ALPHANUMERIC_TABLE = ai;
    }
}
