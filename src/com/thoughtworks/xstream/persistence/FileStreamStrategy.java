// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.persistence;

import com.thoughtworks.xstream.XStream;
import java.io.File;

// Referenced classes of package com.thoughtworks.xstream.persistence:
//            AbstractFilePersistenceStrategy, StreamStrategy

public class FileStreamStrategy extends AbstractFilePersistenceStrategy
    implements StreamStrategy
{

    public FileStreamStrategy(File file)
    {
        this(file, new XStream());
    }

    public FileStreamStrategy(File file, XStream xstream)
    {
        super(file, xstream, null);
    }

    protected String escape(String s)
    {
        StringBuffer stringbuffer = new StringBuffer();
        char ac[] = s.toCharArray();
        int i = 0;
        while(i < ac.length) 
        {
            char c = ac[i];
            if(Character.isDigit(c) || c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z')
                stringbuffer.append(c);
            else
            if(c == '_')
                stringbuffer.append("__");
            else
                stringbuffer.append("_" + Integer.toHexString(c) + "_");
            i++;
        }
        return stringbuffer.toString();
    }

    @Override
	protected Object extractKey(String s)
    {
        String s1 = unescape(s.substring(0, s.length() - 4));
        String s2;
        if(s1.equals("\uFFFD\uFFFD"))
            s2 = null;
        else
            s2 = s1;
        return s2;
    }

    @Override
	protected String getName(Object obj)
    {
        StringBuffer stringbuffer = new StringBuffer();
        String s;
        if(obj == null)
            s = "\uFFFD\uFFFD";
        else
            s = obj.toString();
        return stringbuffer.append(escape(s)).append(".xml").toString();
    }

    protected String unescape(String s)
    {
        StringBuffer stringbuffer = new StringBuffer();
        int i = 65535;
        int j = -1;
        char ac[] = s.toCharArray();
        int k = 0;
        while(k < ac.length) 
        {
            char c = ac[k];
            if(c == '_' && j != -1)
            {
                if(i == 95)
                    stringbuffer.append('_');
                else
                    stringbuffer.append((char)j);
                j = -1;
            } else
            if(c == '_')
                j = 0;
            else
            if(j != -1)
                j = j * 16 + Integer.parseInt(String.valueOf(c), 16);
            else
                stringbuffer.append(c);
            i = c;
            k++;
        }
        return stringbuffer.toString();
    }
}
