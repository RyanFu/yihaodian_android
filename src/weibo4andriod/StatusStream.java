// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package weibo4andriod;

import java.io.*;
import weibo4andriod.http.Response;
import weibo4andriod.org.json.JSONException;

// Referenced classes of package weibo4andriod:
//            WeiboException, Status

public class StatusStream
{

    StatusStream(InputStream inputstream)
        throws IOException
    {
        streamAlive = true;
        is = inputstream;
        br = new BufferedReader(new InputStreamReader(inputstream, "UTF-8"));
    }

    StatusStream(Response response1)
        throws IOException
    {
        this(response1.asStream());
        response = response1;
    }

    public void close()
        throws IOException
    {
        is.close();
        br.close();
        if(response != null)
            response.disconnect();
    }

    public Status next()
        throws WeiboException
    {
        if(!streamAlive)
            throw new IllegalStateException("Stream already closed.");
        break MISSING_BLOCK_LABEL_19;
        JSONException jsonexception;
        jsonexception;
        String s;
        int i;
        do
        {
            do
            {
                if(!streamAlive)
                    break MISSING_BLOCK_LABEL_62;
                s = br.readLine();
            } while(s == null);
            i = s.length();
        } while(i <= 0);
        Status status = new Status(s);
        return status;
        try
        {
            throw new WeiboException("Stream closed.");
        }
        catch(IOException ioexception)
        {
            try
            {
                is.close();
            }
            catch(IOException ioexception1) { }
        }
        streamAlive = false;
        throw new WeiboException("Stream closed.", ioexception);
    }

    private BufferedReader br;
    private InputStream is;
    private Response response;
    private boolean streamAlive;
}
