// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.qrcode.decoder;


// Referenced classes of package com.google.zxing.qrcode.decoder:
//            Version

public final class Mode
{

    private Mode(int ai[], int i, String s)
    {
        characterCountBitsForVersions = ai;
        bits = i;
        name = s;
    }

    public static Mode forBits(int i)
    {
        i;
        JVM INSTR tableswitch 0 9: default 56
    //                   0 64
    //                   1 70
    //                   2 77
    //                   3 84
    //                   4 91
    //                   5 98
    //                   6 56
    //                   7 105
    //                   8 112
    //                   9 119;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L1 _L8 _L9 _L10
_L1:
        throw new IllegalArgumentException();
_L2:
        Mode mode = TERMINATOR;
_L12:
        return mode;
_L3:
        mode = NUMERIC;
        continue; /* Loop/switch isn't completed */
_L4:
        mode = ALPHANUMERIC;
        continue; /* Loop/switch isn't completed */
_L5:
        mode = STRUCTURED_APPEND;
        continue; /* Loop/switch isn't completed */
_L6:
        mode = BYTE;
        continue; /* Loop/switch isn't completed */
_L7:
        mode = FNC1_FIRST_POSITION;
        continue; /* Loop/switch isn't completed */
_L8:
        mode = ECI;
        continue; /* Loop/switch isn't completed */
_L9:
        mode = KANJI;
        continue; /* Loop/switch isn't completed */
_L10:
        mode = FNC1_SECOND_POSITION;
        if(true) goto _L12; else goto _L11
_L11:
    }

    public int getBits()
    {
        return bits;
    }

    public int getCharacterCountBits(Version version)
    {
        if(characterCountBitsForVersions == null)
            throw new IllegalArgumentException("Character count doesn't apply to this mode");
        int i = version.getVersionNumber();
        int j;
        if(i <= 9)
            j = 0;
        else
        if(i <= 26)
            j = 1;
        else
            j = 2;
        return characterCountBitsForVersions[j];
    }

    public String getName()
    {
        return name;
    }

    @Override
	public String toString()
    {
        return name;
    }

    public static final Mode ALPHANUMERIC;
    public static final Mode BYTE;
    public static final Mode ECI = new Mode(null, 7, "ECI");
    public static final Mode FNC1_FIRST_POSITION = new Mode(null, 5, "FNC1_FIRST_POSITION");
    public static final Mode FNC1_SECOND_POSITION = new Mode(null, 9, "FNC1_SECOND_POSITION");
    public static final Mode KANJI;
    public static final Mode NUMERIC;
    public static final Mode STRUCTURED_APPEND = new Mode(new int[3], 3, "STRUCTURED_APPEND");
    public static final Mode TERMINATOR = new Mode(new int[3], 0, "TERMINATOR");
    private final int bits;
    private final int characterCountBitsForVersions[];
    private final String name;

    static 
    {
        int ai[] = new int[3];
        ai[0] = 10;
        ai[1] = 12;
        ai[2] = 14;
        NUMERIC = new Mode(ai, 1, "NUMERIC");
        int ai1[] = new int[3];
        ai1[0] = 9;
        ai1[1] = 11;
        ai1[2] = 13;
        ALPHANUMERIC = new Mode(ai1, 2, "ALPHANUMERIC");
        int ai2[] = new int[3];
        ai2[0] = 8;
        ai2[1] = 16;
        ai2[2] = 16;
        BYTE = new Mode(ai2, 4, "BYTE");
        int ai3[] = new int[3];
        ai3[0] = 8;
        ai3[1] = 10;
        ai3[2] = 12;
        KANJI = new Mode(ai3, 8, "KANJI");
    }
}
