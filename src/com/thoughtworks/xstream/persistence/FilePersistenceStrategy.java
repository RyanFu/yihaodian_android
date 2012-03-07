// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.persistence;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.SingleValueConverter;
import com.thoughtworks.xstream.io.StreamException;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.File;

// Referenced classes of package com.thoughtworks.xstream.persistence:
//            AbstractFilePersistenceStrategy

public class FilePersistenceStrategy extends AbstractFilePersistenceStrategy
{

    public FilePersistenceStrategy(File file)
    {
        this(file, new XStream(new DomDriver()));
    }

    public FilePersistenceStrategy(File file, XStream xstream)
    {
        this(file, xstream, "utf-8", "<>?:/\\\"|*%");
    }

    public FilePersistenceStrategy(File file, XStream xstream, String s, String s1)
    {
        super(file, xstream, s);
        illegalChars = s1;
    }

    protected String escape(String s)
    {
        StringBuffer stringbuffer = new StringBuffer();
        char ac[] = s.toCharArray();
        int i = 0;
        while(i < ac.length) 
        {
            char c = ac[i];
            if(c >= ' ' && illegalChars.indexOf(c) < 0)
                stringbuffer.append(c);
            else
                stringbuffer.append("%" + Integer.toHexString(c).toUpperCase());
            i++;
        }
        return stringbuffer.toString();
    }

    @Override
	protected Object extractKey(String s)
    {
        String s1 = unescape(s.substring(0, s.length() - 4));
        Object obj;
        if("null@null".equals(s1))
        {
            obj = null;
        } else
        {
            int i = s1.indexOf('@');
            if(i < 0)
                throw new StreamException("Not a valid key: " + s1);
            Class class1 = getMapper().realClass(s1.substring(0, i));
            com.thoughtworks.xstream.converters.Converter converter = getConverterLookup().lookupConverterForType(class1);
            if(converter instanceof SingleValueConverter)
                obj = ((SingleValueConverter)converter).fromString(s1.substring(i + 1));
            else
                throw new StreamException("No SingleValueConverter for type " + class1.getName() + " available");
        }
        return obj;
    }

    @Override
	protected String getName(Object obj)
    {
        String s;
        if(obj == null)
        {
            s = "null@null.xml";
        } else
        {
            Class class1 = obj.getClass();
            com.thoughtworks.xstream.converters.Converter converter = getConverterLookup().lookupConverterForType(class1);
            if(converter instanceof SingleValueConverter)
            {
                SingleValueConverter singlevalueconverter = (SingleValueConverter)converter;
                s = getMapper().serializedClass(class1) + '@' + escape(singlevalueconverter.toString(obj)) + ".xml";
            } else
            {
                throw new StreamException("No SingleValueConverter for type " + class1.getName() + " available");
            }
        }
        return s;
    }

    @Override
	protected boolean isValid(File file, String s)
    {
        boolean flag;
        if(super.isValid(file, s) && s.indexOf('@') > 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    protected String unescape(String s)
    {
        StringBuffer stringbuffer = new StringBuffer();
        for(int i = s.indexOf('%'); i >= 0; i = s.indexOf('%'))
        {
            stringbuffer.append(s.substring(0, i));
            stringbuffer.append((char)Integer.parseInt(s.substring(i + 1, i + 3), 16));
            s = s.substring(i + 3);
        }

        stringbuffer.append(s);
        return stringbuffer.toString();
    }

    private final String illegalChars;
}
