// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient;

import java.io.*;

// Referenced classes of package org.apache.commons.httpclient:
//            Wire

class WireLogOutputStream extends FilterOutputStream
{

    public WireLogOutputStream(OutputStream outputstream, Wire wire1)
    {
        super(outputstream);
        out = outputstream;
        wire = wire1;
    }

    @Override
	public void write(int i)
        throws IOException
    {
        out.write(i);
        wire.output(i);
    }

    @Override
	public void write(byte abyte0[])
        throws IOException
    {
        out.write(abyte0);
        wire.output(abyte0);
    }

    @Override
	public void write(byte abyte0[], int i, int j)
        throws IOException
    {
        out.write(abyte0, i, j);
        wire.output(abyte0, i, j);
    }

    private OutputStream out;
    private Wire wire;
}
