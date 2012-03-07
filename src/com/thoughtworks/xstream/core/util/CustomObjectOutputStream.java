// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.core.util;

import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.DataHolder;
import java.io.*;
import java.util.Map;

// Referenced classes of package com.thoughtworks.xstream.core.util:
//            FastStack, OrderRetainingMap

public class CustomObjectOutputStream extends ObjectOutputStream
{
    private class CustomPutField extends java.io.ObjectOutputStream.PutField
    {

        public Map asMap()
        {
            return fields;
        }

        @Override
		public void put(String s, byte byte0)
        {
            put(s, new Byte(byte0));
        }

        @Override
		public void put(String s, char c)
        {
            put(s, new Character(c));
        }

        @Override
		public void put(String s, double d)
        {
            put(s, new Double(d));
        }

        @Override
		public void put(String s, float f)
        {
            put(s, new Float(f));
        }

        @Override
		public void put(String s, int i)
        {
            put(s, new Integer(i));
        }

        @Override
		public void put(String s, long l)
        {
            put(s, new Long(l));
        }

        @Override
		public void put(String s, Object obj)
        {
            fields.put(s, obj);
        }

        @Override
		public void put(String s, short word0)
        {
            put(s, new Short(word0));
        }

        @Override
		public void put(String s, boolean flag)
        {
            Boolean boolean1;
            if(flag)
                boolean1 = Boolean.TRUE;
            else
                boolean1 = Boolean.FALSE;
            put(s, boolean1);
        }

        @Override
		public void write(ObjectOutput objectoutput)
            throws IOException
        {
            peekCallback().writeToStream(asMap());
        }

        private final Map fields;
        private final CustomObjectOutputStream this$0;

        private CustomPutField()
        {
            this$0 = CustomObjectOutputStream.this;
            fields = new OrderRetainingMap();
        }

    }

    public static interface StreamCallback
    {

        public abstract void close()
            throws IOException;

        public abstract void defaultWriteObject()
            throws IOException;

        public abstract void flush()
            throws IOException;

        public abstract void writeFieldsToStream(Map map)
            throws IOException;

        public abstract void writeToStream(Object obj)
            throws IOException;
    }


    public CustomObjectOutputStream(StreamCallback streamcallback)
        throws IOException, SecurityException
    {
        callbacks = new FastStack(1);
        customFields = new FastStack(1);
        callbacks.push(streamcallback);
    }

    static Class _mthclass$(String s)
    {
        Class class1;
        try
        {
            class1 = Class.forName(s);
        }
        catch(ClassNotFoundException classnotfoundexception)
        {
            throw new NoClassDefFoundError(classnotfoundexception.getMessage());
        }
        return class1;
    }

    /**
     * @deprecated Method getInstance is deprecated
     */

    @Deprecated
	public static CustomObjectOutputStream getInstance(DataHolder dataholder, StreamCallback streamcallback)
    {
        com/thoughtworks/xstream/core/util/CustomObjectOutputStream;
        JVM INSTR monitorenter ;
        CustomObjectOutputStream customobjectoutputstream = (CustomObjectOutputStream)dataholder.get(DATA_HOLDER_KEY);
        if(customobjectoutputstream != null) goto _L2; else goto _L1
_L1:
        customobjectoutputstream = new CustomObjectOutputStream(streamcallback);
        dataholder.put(DATA_HOLDER_KEY, customobjectoutputstream);
_L4:
        com/thoughtworks/xstream/core/util/CustomObjectOutputStream;
        JVM INSTR monitorexit ;
        return customobjectoutputstream;
_L2:
        customobjectoutputstream.pushCallback(streamcallback);
        if(true) goto _L4; else goto _L3
_L3:
        IOException ioexception;
        ioexception;
        throw new ConversionException("Cannot create CustomObjectStream", ioexception);
        Exception exception;
        exception;
        com/thoughtworks/xstream/core/util/CustomObjectOutputStream;
        JVM INSTR monitorexit ;
        throw exception;
    }

    @Override
	public void close()
        throws IOException
    {
        peekCallback().close();
    }

    @Override
	public void defaultWriteObject()
        throws IOException
    {
        peekCallback().defaultWriteObject();
    }

    @Override
	public void flush()
        throws IOException
    {
        peekCallback().flush();
    }

    public StreamCallback peekCallback()
    {
        return (StreamCallback)callbacks.peek();
    }

    public StreamCallback popCallback()
    {
        return (StreamCallback)callbacks.pop();
    }

    public void pushCallback(StreamCallback streamcallback)
    {
        callbacks.push(streamcallback);
    }

    @Override
	public java.io.ObjectOutputStream.PutField putFields()
    {
        CustomPutField customputfield = new CustomPutField();
        customFields.push(customputfield);
        return customputfield;
    }

    @Override
	public void reset()
    {
        throw new UnsupportedOperationException();
    }

    @Override
	public void useProtocolVersion(int i)
    {
        throw new UnsupportedOperationException();
    }

    @Override
	public void write(int i)
        throws IOException
    {
        peekCallback().writeToStream(new Byte((byte)i));
    }

    @Override
	public void write(byte abyte0[])
        throws IOException
    {
        peekCallback().writeToStream(abyte0);
    }

    @Override
	public void write(byte abyte0[], int i, int j)
        throws IOException
    {
        byte abyte1[] = new byte[j];
        System.arraycopy(abyte0, i, abyte1, 0, j);
        peekCallback().writeToStream(abyte1);
    }

    @Override
	public void writeBoolean(boolean flag)
        throws IOException
    {
        StreamCallback streamcallback = peekCallback();
        Boolean boolean1;
        if(flag)
            boolean1 = Boolean.TRUE;
        else
            boolean1 = Boolean.FALSE;
        streamcallback.writeToStream(boolean1);
    }

    @Override
	public void writeByte(int i)
        throws IOException
    {
        peekCallback().writeToStream(new Byte((byte)i));
    }

    @Override
	public void writeBytes(String s)
    {
        throw new UnsupportedOperationException();
    }

    @Override
	public void writeChar(int i)
        throws IOException
    {
        peekCallback().writeToStream(new Character((char)i));
    }

    @Override
	public void writeChars(String s)
        throws IOException
    {
        peekCallback().writeToStream(s.toCharArray());
    }

    @Override
	public void writeDouble(double d)
        throws IOException
    {
        peekCallback().writeToStream(new Double(d));
    }

    @Override
	public void writeFields()
        throws IOException
    {
        CustomPutField customputfield = (CustomPutField)customFields.pop();
        peekCallback().writeFieldsToStream(customputfield.asMap());
    }

    @Override
	public void writeFloat(float f)
        throws IOException
    {
        peekCallback().writeToStream(new Float(f));
    }

    @Override
	public void writeInt(int i)
        throws IOException
    {
        peekCallback().writeToStream(new Integer(i));
    }

    @Override
	public void writeLong(long l)
        throws IOException
    {
        peekCallback().writeToStream(new Long(l));
    }

    @Override
	protected void writeObjectOverride(Object obj)
        throws IOException
    {
        peekCallback().writeToStream(obj);
    }

    @Override
	public void writeShort(int i)
        throws IOException
    {
        peekCallback().writeToStream(new Short((short)i));
    }

    @Override
	public void writeUTF(String s)
        throws IOException
    {
        peekCallback().writeToStream(s);
    }

    @Override
	public void writeUnshared(Object obj)
    {
        throw new UnsupportedOperationException();
    }

    private static final String DATA_HOLDER_KEY;
    static Class class$com$thoughtworks$xstream$core$util$CustomObjectOutputStream;
    private FastStack callbacks;
    private FastStack customFields;

    static 
    {
        Class class1;
        if(class$com$thoughtworks$xstream$core$util$CustomObjectOutputStream == null)
        {
            class1 = _mthclass$("com.thoughtworks.xstream.core.util.CustomObjectOutputStream");
            class$com$thoughtworks$xstream$core$util$CustomObjectOutputStream = class1;
        } else
        {
            class1 = class$com$thoughtworks$xstream$core$util$CustomObjectOutputStream;
        }
        DATA_HOLDER_KEY = class1.getName();
    }
}
