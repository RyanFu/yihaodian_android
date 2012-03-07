// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.auth;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.httpclient.util.EncodingUtil;

// Referenced classes of package org.apache.commons.httpclient.auth:
//            AuthenticationException

final class NTLM
{

    NTLM()
    {
        currentPosition = 0;
        credentialCharset = "ASCII";
    }

    private void addByte(byte byte0)
    {
        currentResponse[currentPosition] = byte0;
        currentPosition = 1 + currentPosition;
    }

    private void addBytes(byte abyte0[])
    {
        int i = 0;
        do
        {
            if(i >= abyte0.length)
                return;
            currentResponse[currentPosition] = abyte0[i];
            currentPosition = 1 + currentPosition;
            i++;
        } while(true);
    }

    private void calcResp(byte abyte0[], byte abyte1[], byte abyte2[])
        throws AuthenticationException
    {
        byte abyte3[];
        byte abyte4[];
        byte abyte5[];
        int i;
        abyte3 = new byte[7];
        abyte4 = new byte[7];
        abyte5 = new byte[7];
        i = 0;
_L11:
        if(i < 7) goto _L2; else goto _L1
_L1:
        int j = 0;
_L12:
        if(j < 7) goto _L4; else goto _L3
_L3:
        int k = 0;
_L13:
        if(k < 7) goto _L6; else goto _L5
_L5:
        byte abyte6[];
        byte abyte7[];
        byte abyte8[];
        int l;
        abyte6 = encrypt(abyte3, abyte1);
        abyte7 = encrypt(abyte4, abyte1);
        abyte8 = encrypt(abyte5, abyte1);
        l = 0;
_L14:
        if(l < 8) goto _L8; else goto _L7
_L7:
        int i1 = 0;
_L15:
        if(i1 < 8) goto _L10; else goto _L9
_L9:
        int j1 = 0;
_L16:
        if(j1 >= 8)
            return;
        break MISSING_BLOCK_LABEL_190;
_L2:
        abyte3[i] = abyte0[i];
        i++;
          goto _L11
_L4:
        abyte4[j] = abyte0[j + 7];
        j++;
          goto _L12
_L6:
        abyte5[k] = abyte0[k + 14];
        k++;
          goto _L13
_L8:
        abyte2[l] = abyte6[l];
        l++;
          goto _L14
_L10:
        abyte2[i1 + 8] = abyte7[i1];
        i1++;
          goto _L15
        abyte2[j1 + 16] = abyte8[j1];
        j1++;
          goto _L16
    }

    private byte[] convertShort(int i)
    {
        byte abyte0[] = new byte[2];
        String s = Integer.toString(i, 16);
        do
        {
            if(s.length() >= 4)
            {
                String s1 = s.substring(2, 4);
                String s2 = s.substring(0, 2);
                abyte0[0] = (byte)Integer.parseInt(s1, 16);
                abyte0[1] = (byte)Integer.parseInt(s2, 16);
                return abyte0;
            }
            s = "0" + s;
        } while(true);
    }

    private byte[] encrypt(byte abyte0[], byte abyte1[])
        throws AuthenticationException
    {
        Cipher cipher = getCipher(abyte0);
        byte abyte2[];
        try
        {
            abyte2 = cipher.doFinal(abyte1);
        }
        catch(IllegalBlockSizeException illegalblocksizeexception)
        {
            throw new AuthenticationException("Invalid block size for DES encryption.", illegalblocksizeexception);
        }
        catch(BadPaddingException badpaddingexception)
        {
            throw new AuthenticationException("Data not padded correctly for DES encryption.", badpaddingexception);
        }
        return abyte2;
    }

    private Cipher getCipher(byte abyte0[])
        throws AuthenticationException
    {
        Cipher cipher;
        try
        {
            cipher = Cipher.getInstance("DES/ECB/NoPadding");
            cipher.init(1, new SecretKeySpec(setupKey(abyte0), "DES"));
        }
        catch(NoSuchAlgorithmException nosuchalgorithmexception)
        {
            throw new AuthenticationException("DES encryption is not available.", nosuchalgorithmexception);
        }
        catch(InvalidKeyException invalidkeyexception)
        {
            throw new AuthenticationException("Invalid key for DES encryption.", invalidkeyexception);
        }
        catch(NoSuchPaddingException nosuchpaddingexception)
        {
            throw new AuthenticationException("NoPadding option for DES is not available.", nosuchpaddingexception);
        }
        return cipher;
    }

    private String getResponse()
    {
        byte abyte1[];
        int i;
        if(currentResponse.length <= currentPosition)
            break MISSING_BLOCK_LABEL_54;
        abyte1 = new byte[currentPosition];
        i = 0;
_L3:
        if(i < currentPosition) goto _L2; else goto _L1
_L1:
        byte abyte0[] = abyte1;
_L4:
        return EncodingUtil.getAsciiString(Base64.encodeBase64(abyte0));
_L2:
        abyte1[i] = currentResponse[i];
        i++;
          goto _L3
        abyte0 = currentResponse;
          goto _L4
    }

    private byte[] hashPassword(String s, byte abyte0[])
        throws AuthenticationException
    {
        byte abyte1[];
        byte abyte2[];
        byte abyte3[];
        int i;
        int j;
        abyte1 = EncodingUtil.getBytes(s.toUpperCase(), credentialCharset);
        abyte2 = new byte[7];
        abyte3 = new byte[7];
        i = abyte1.length;
        if(i > 7)
            i = 7;
        j = 0;
_L13:
        if(j < i) goto _L2; else goto _L1
_L1:
        if(j < 7) goto _L4; else goto _L3
_L3:
        int k;
        int l;
        k = abyte1.length;
        if(k > 14)
            k = 14;
        l = 7;
_L14:
        if(l < k) goto _L6; else goto _L5
_L5:
        if(l < 14) goto _L8; else goto _L7
_L7:
        byte abyte5[];
        byte abyte6[];
        byte abyte7[];
        int i1;
        byte abyte4[] = new byte[8];
        abyte4[0] = 75;
        abyte4[1] = 71;
        abyte4[2] = 83;
        abyte4[3] = 33;
        abyte4[4] = 64;
        abyte4[5] = 35;
        abyte4[6] = 36;
        abyte4[7] = 37;
        abyte5 = encrypt(abyte2, abyte4);
        abyte6 = encrypt(abyte3, abyte4);
        abyte7 = new byte[21];
        i1 = 0;
_L15:
        if(i1 < abyte5.length) goto _L10; else goto _L9
_L9:
        int j1 = 0;
_L16:
        if(j1 < abyte6.length) goto _L12; else goto _L11
_L11:
        int k1 = 0;
_L17:
        if(k1 >= 5)
        {
            byte abyte8[] = new byte[24];
            calcResp(abyte7, abyte0, abyte8);
            return abyte8;
        }
        break MISSING_BLOCK_LABEL_315;
_L2:
        abyte2[j] = abyte1[j];
        j++;
          goto _L13
_L4:
        abyte2[j] = 0;
        j++;
          goto _L1
_L6:
        abyte3[l - 7] = abyte1[l];
        l++;
          goto _L14
_L8:
        abyte3[l - 7] = 0;
        l++;
          goto _L5
_L10:
        abyte7[i1] = abyte5[i1];
        i1++;
          goto _L15
_L12:
        abyte7[j1 + 8] = abyte6[j1];
        j1++;
          goto _L16
        abyte7[k1 + 16] = 0;
        k1++;
          goto _L17
    }

    private void prepareResponse(int i)
    {
        currentResponse = new byte[i];
        currentPosition = 0;
    }

    private byte[] setupKey(byte abyte0[])
    {
        byte abyte1[] = new byte[8];
        abyte1[0] = (byte)(0xff & abyte0[0] >> 1);
        abyte1[1] = (byte)(0xff & ((1 & abyte0[0]) << 6 | 0xff & (0xff & abyte0[1]) >> 2));
        abyte1[2] = (byte)(0xff & ((3 & abyte0[1]) << 5 | 0xff & (0xff & abyte0[2]) >> 3));
        abyte1[3] = (byte)(0xff & ((7 & abyte0[2]) << 4 | 0xff & (0xff & abyte0[3]) >> 4));
        abyte1[4] = (byte)(0xff & ((0xf & abyte0[3]) << 3 | 0xff & (0xff & abyte0[4]) >> 5));
        abyte1[5] = (byte)(0xff & ((0x1f & abyte0[4]) << 2 | 0xff & (0xff & abyte0[5]) >> 6));
        abyte1[6] = (byte)(0xff & ((0x3f & abyte0[5]) << 1 | 0xff & (0xff & abyte0[6]) >> 7));
        abyte1[7] = (byte)(0x7f & abyte0[6]);
        int i = 0;
        do
        {
            if(i >= abyte1.length)
                return abyte1;
            abyte1[i] = (byte)(abyte1[i] << 1);
            i++;
        } while(true);
    }

    public String getCredentialCharset()
    {
        return credentialCharset;
    }

    public final String getResponseFor(String s, String s1, String s2, String s3, String s4)
        throws AuthenticationException
    {
        String s5;
        if(s == null || s.trim().equals(""))
            s5 = getType1Message(s3, s4);
        else
            s5 = getType3Message(s1, s2, s3, s4, parseType2Message(s));
        return s5;
    }

    public String getType1Message(String s, String s1)
    {
        String s2 = s.toUpperCase();
        String s3 = s1.toUpperCase();
        byte abyte0[] = EncodingUtil.getBytes(s2, "ASCII");
        byte abyte1[] = EncodingUtil.getBytes(s3, "ASCII");
        prepareResponse(32 + abyte0.length + abyte1.length);
        addBytes(EncodingUtil.getBytes("NTLMSSP", "ASCII"));
        addByte((byte)0);
        addByte((byte)1);
        addByte((byte)0);
        addByte((byte)0);
        addByte((byte)0);
        addByte((byte)6);
        addByte((byte)82);
        addByte((byte)0);
        addByte((byte)0);
        byte abyte2[] = convertShort(abyte1.length);
        addByte(abyte2[0]);
        addByte(abyte2[1]);
        addByte(abyte2[0]);
        addByte(abyte2[1]);
        byte abyte3[] = convertShort(32 + abyte0.length);
        addByte(abyte3[0]);
        addByte(abyte3[1]);
        addByte((byte)0);
        addByte((byte)0);
        byte abyte4[] = convertShort(abyte0.length);
        addByte(abyte4[0]);
        addByte(abyte4[1]);
        addByte(abyte4[0]);
        addByte(abyte4[1]);
        byte abyte5[] = convertShort(32);
        addByte(abyte5[0]);
        addByte(abyte5[1]);
        addByte((byte)0);
        addByte((byte)0);
        addBytes(abyte0);
        addBytes(abyte1);
        return getResponse();
    }

    public String getType3Message(String s, String s1, String s2, String s3, byte abyte0[])
        throws AuthenticationException
    {
        String s4 = s3.toUpperCase();
        String s5 = s2.toUpperCase();
        String s6 = s.toUpperCase();
        byte abyte1[] = EncodingUtil.getBytes(s4, "ASCII");
        byte abyte2[] = EncodingUtil.getBytes(s5, "ASCII");
        byte abyte3[] = EncodingUtil.getBytes(s6, credentialCharset);
        int i = abyte1.length;
        int j = abyte2.length;
        int k = abyte3.length;
        int _tmp = 0 + 64;
        int _tmp1 = 24 + 64;
        int l = j + (k + (i + 88));
        prepareResponse(l);
        addBytes(EncodingUtil.getBytes("NTLMSSP", "ASCII"));
        addByte((byte)0);
        addByte((byte)3);
        addByte((byte)0);
        addByte((byte)0);
        addByte((byte)0);
        addBytes(convertShort(24));
        addBytes(convertShort(24));
        addBytes(convertShort(l - 24));
        addByte((byte)0);
        addByte((byte)0);
        addBytes(convertShort(0));
        addBytes(convertShort(0));
        addBytes(convertShort(l));
        addByte((byte)0);
        addByte((byte)0);
        addBytes(convertShort(i));
        addBytes(convertShort(i));
        addBytes(convertShort(64));
        addByte((byte)0);
        addByte((byte)0);
        addBytes(convertShort(k));
        addBytes(convertShort(k));
        addBytes(convertShort(i + 64));
        addByte((byte)0);
        addByte((byte)0);
        addBytes(convertShort(j));
        addBytes(convertShort(j));
        addBytes(convertShort(k + (i + 64)));
        int i1 = 0;
        do
        {
            if(i1 >= 6)
            {
                addBytes(convertShort(l));
                addByte((byte)0);
                addByte((byte)0);
                addByte((byte)6);
                addByte((byte)82);
                addByte((byte)0);
                addByte((byte)0);
                addBytes(abyte1);
                addBytes(abyte3);
                addBytes(abyte2);
                addBytes(hashPassword(s1, abyte0));
                return getResponse();
            }
            addByte((byte)0);
            i1++;
        } while(true);
    }

    public byte[] parseType2Message(String s)
    {
        byte abyte0[] = Base64.decodeBase64(EncodingUtil.getBytes(s, "ASCII"));
        byte abyte1[] = new byte[8];
        int i = 0;
        do
        {
            if(i >= 8)
                return abyte1;
            abyte1[i] = abyte0[i + 24];
            i++;
        } while(true);
    }

    public void setCredentialCharset(String s)
    {
        credentialCharset = s;
    }

    public static final String DEFAULT_CHARSET = "ASCII";
    private String credentialCharset;
    private int currentPosition;
    private byte currentResponse[];
}
