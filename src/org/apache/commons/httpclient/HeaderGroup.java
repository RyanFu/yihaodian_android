// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient;

import java.util.*;

// Referenced classes of package org.apache.commons.httpclient:
//            Header, NameValuePair

public class HeaderGroup
{

    public HeaderGroup()
    {
        headers = new ArrayList();
    }

    public void addHeader(Header header)
    {
        headers.add(header);
    }

    public void clear()
    {
        headers.clear();
    }

    public boolean containsHeader(String s)
    {
        Iterator iterator = headers.iterator();
_L2:
        boolean flag;
        if(iterator.hasNext())
            continue; /* Loop/switch isn't completed */
        flag = false;
_L3:
        return flag;
        if(!((Header)iterator.next()).getName().equalsIgnoreCase(s)) goto _L2; else goto _L1
_L1:
        flag = true;
          goto _L3
        if(true) goto _L2; else goto _L4
_L4:
    }

    public Header[] getAllHeaders()
    {
        return (Header[])headers.toArray(new Header[headers.size()]);
    }

    public Header getCondensedHeader(String s)
    {
        Header aheader[] = getHeaders(s);
        if(aheader.length != 0) goto _L2; else goto _L1
_L1:
        Header header = null;
_L4:
        return header;
_L2:
        StringBuffer stringbuffer;
        int i;
        if(aheader.length == 1)
        {
            header = new Header(aheader[0].getName(), aheader[0].getValue());
            continue; /* Loop/switch isn't completed */
        }
        stringbuffer = new StringBuffer(aheader[0].getValue());
        i = 1;
_L5:
label0:
        {
            if(i < aheader.length)
                break label0;
            header = new Header(s.toLowerCase(), stringbuffer.toString());
        }
        if(true) goto _L4; else goto _L3
_L3:
        stringbuffer.append(", ");
        stringbuffer.append(aheader[i].getValue());
        i++;
          goto _L5
        if(true) goto _L4; else goto _L6
_L6:
    }

    public Header getFirstHeader(String s)
    {
        Iterator iterator = headers.iterator();
_L2:
        Header header1;
        if(!iterator.hasNext())
        {
            header1 = null;
        } else
        {
            Header header = (Header)iterator.next();
            if(!header.getName().equalsIgnoreCase(s))
                continue; /* Loop/switch isn't completed */
            header1 = header;
        }
        return header1;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public Header[] getHeaders(String s)
    {
        ArrayList arraylist = new ArrayList();
        Iterator iterator = headers.iterator();
        do
        {
            Header header;
            do
            {
                if(!iterator.hasNext())
                    return (Header[])arraylist.toArray(new Header[arraylist.size()]);
                header = (Header)iterator.next();
            } while(!header.getName().equalsIgnoreCase(s));
            arraylist.add(header);
        } while(true);
    }

    public Iterator getIterator()
    {
        return headers.iterator();
    }

    public Header getLastHeader(String s)
    {
        int i = headers.size() - 1;
_L6:
        if(i >= 0) goto _L2; else goto _L1
_L1:
        Header header1 = null;
_L4:
        return header1;
_L2:
        Header header = (Header)headers.get(i);
        if(!header.getName().equalsIgnoreCase(s))
            break; /* Loop/switch isn't completed */
        header1 = header;
        if(true) goto _L4; else goto _L3
_L3:
        i--;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public void removeHeader(Header header)
    {
        headers.remove(header);
    }

    public void setHeaders(Header aheader[])
    {
        clear();
        int i = 0;
        do
        {
            if(i >= aheader.length)
                return;
            addHeader(aheader[i]);
            i++;
        } while(true);
    }

    private List headers;
}
