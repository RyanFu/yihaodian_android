// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient;

import java.io.*;

// Referenced classes of package org.apache.commons.httpclient:
//            Wire

class WireLogInputStream extends FilterInputStream
{

    public WireLogInputStream(InputStream inputstream, Wire wire1)
    {
        super(inputstream);
        in = inputstream;
        wire = wire1;
    }

    @Override
	public int read()
        throws IOException
    {
        int i = in.read();
        if(i > 0)
            wire.input(i);
        return i;
    }

    @Override
	public int read(byte abyte0[])
        throws IOException
    {
        int i = in.read(abyte0);
        if(i > 0)
            wire.input(abyte0, 0, i);
        return i;
    }

    @Override
	public int read(byte abyte0[], int i, int j)
        throws IOException
    {
        int k = in.read(abyte0, i, j);
        if(k > 0)
            wire.input(abyte0, i, k);
        return k;
    }

    private InputStream in;
    private Wire wire;
}
