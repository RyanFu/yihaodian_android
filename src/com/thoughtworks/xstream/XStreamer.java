// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream;

import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.io.HierarchicalStreamDriver;
import com.thoughtworks.xstream.io.xml.XppDriver;
import java.io.*;

// Referenced classes of package com.thoughtworks.xstream:
//            XStream

public class XStreamer
{

    public XStreamer()
    {
    }

    public Object fromXML(HierarchicalStreamDriver hierarchicalstreamdriver, Reader reader)
        throws IOException, ClassNotFoundException
    {
        com.thoughtworks.xstream.io.HierarchicalStreamReader hierarchicalstreamreader;
        ObjectInputStream objectinputstream;
        XStream xstream = new XStream(hierarchicalstreamdriver);
        hierarchicalstreamreader = hierarchicalstreamdriver.createReader(reader);
        objectinputstream = xstream.createObjectInputStream(hierarchicalstreamreader);
        ObjectInputStream objectinputstream1 = ((XStream)objectinputstream.readObject()).createObjectInputStream(hierarchicalstreamreader);
        Object obj = objectinputstream1.readObject();
        objectinputstream1.close();
        objectinputstream.close();
        return obj;
        Exception exception1;
        exception1;
        objectinputstream1.close();
        throw exception1;
        Exception exception;
        exception;
        objectinputstream.close();
        throw exception;
    }

    public Object fromXML(HierarchicalStreamDriver hierarchicalstreamdriver, String s)
        throws ClassNotFoundException, ObjectStreamException
    {
        Object obj;
        try
        {
            obj = fromXML(hierarchicalstreamdriver, ((new StringReader(s))));
        }
        catch(ObjectStreamException objectstreamexception)
        {
            throw objectstreamexception;
        }
        catch(IOException ioexception)
        {
            throw new ConversionException("Unexpeced IO error from a StringReader", ioexception);
        }
        return obj;
    }

    public Object fromXML(Reader reader)
        throws IOException, ClassNotFoundException
    {
        return fromXML(((new XppDriver())), reader);
    }

    public Object fromXML(String s)
        throws ClassNotFoundException, ObjectStreamException
    {
        Object obj;
        try
        {
            obj = fromXML(((new StringReader(s))));
        }
        catch(ObjectStreamException objectstreamexception)
        {
            throw objectstreamexception;
        }
        catch(IOException ioexception)
        {
            throw new ConversionException("Unexpeced IO error from a StringReader", ioexception);
        }
        return obj;
    }

    public String toXML(XStream xstream, Object obj)
        throws ObjectStreamException
    {
        StringWriter stringwriter = new StringWriter();
        try
        {
            toXML(xstream, obj, ((stringwriter)));
        }
        catch(ObjectStreamException objectstreamexception)
        {
            throw objectstreamexception;
        }
        catch(IOException ioexception)
        {
            throw new ConversionException("Unexpeced IO error from a StringWriter", ioexception);
        }
        return stringwriter.toString();
    }

    public void toXML(XStream xstream, Object obj, Writer writer)
        throws IOException
    {
        ObjectOutputStream objectoutputstream = (new XStream()).createObjectOutputStream(writer);
        objectoutputstream.writeObject(xstream);
        objectoutputstream.flush();
        xstream.toXML(obj, writer);
        objectoutputstream.close();
        return;
        Exception exception;
        exception;
        objectoutputstream.close();
        throw exception;
    }
}
