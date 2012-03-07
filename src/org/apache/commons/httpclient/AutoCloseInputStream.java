// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient;

import java.io.*;

// Referenced classes of package org.apache.commons.httpclient:
//            ResponseConsumedWatcher

class AutoCloseInputStream extends FilterInputStream
{

    public AutoCloseInputStream(InputStream inputstream, ResponseConsumedWatcher responseconsumedwatcher)
    {
        super(inputstream);
        streamOpen = true;
        selfClosed = false;
        watcher = null;
        watcher = responseconsumedwatcher;
    }

    private void checkClose(int i)
        throws IOException
    {
        if(i == -1)
            notifyWatcher();
    }

    private boolean isReadAllowed()
        throws IOException
    {
        if(!streamOpen && selfClosed)
            throw new IOException("Attempted read on closed stream.");
        else
            return streamOpen;
    }

    private void notifyWatcher()
        throws IOException
    {
        if(streamOpen)
        {
            super.close();
            streamOpen = false;
            if(watcher != null)
                watcher.responseConsumed();
        }
    }

    @Override
	public void close()
        throws IOException
    {
        if(!selfClosed)
        {
            selfClosed = true;
            notifyWatcher();
        }
    }

    @Override
	public int read()
        throws IOException
    {
        int i = -1;
        if(isReadAllowed())
        {
            i = super.read();
            checkClose(i);
        }
        return i;
    }

    @Override
	public int read(byte abyte0[])
        throws IOException
    {
        int i = -1;
        if(isReadAllowed())
        {
            i = super.read(abyte0);
            checkClose(i);
        }
        return i;
    }

    @Override
	public int read(byte abyte0[], int i, int j)
        throws IOException
    {
        int k = -1;
        if(isReadAllowed())
        {
            k = super.read(abyte0, i, j);
            checkClose(k);
        }
        return k;
    }

    private boolean selfClosed;
    private boolean streamOpen;
    private ResponseConsumedWatcher watcher;
}
