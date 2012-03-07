// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.core.util;

import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.DataHolder;
import java.io.*;
import java.util.Map;

// Referenced classes of package com.thoughtworks.xstream.core.util:
//            FastStack

public class CustomObjectInputStream extends ObjectInputStream
{
    private class CustomGetField extends java.io.ObjectInputStream.GetField
    {

        private Object get(String s)
        {
            return fields.get(s);
        }

        @Override
		public boolean defaulted(String s)
        {
            boolean flag;
            if(!fields.containsKey(s))
                flag = true;
            else
                flag = false;
            return flag;
        }

        @Override
		public byte get(String s, byte byte0)
        {
            byte byte1;
            if(defaulted(s))
                byte1 = byte0;
            else
                byte1 = ((Byte)get(s)).byteValue();
            return byte1;
        }

        @Override
		public char get(String s, char c)
        {
            char c1;
            if(defaulted(s))
                c1 = c;
            else
                c1 = ((Character)get(s)).charValue();
            return c1;
        }

        @Override
		public double get(String s, double d)
        {
            double d1;
            if(defaulted(s))
                d1 = d;
            else
                d1 = ((Double)get(s)).doubleValue();
            return d1;
        }

        @Override
		public float get(String s, float f)
        {
            float f1;
            if(defaulted(s))
                f1 = f;
            else
                f1 = ((Float)get(s)).floatValue();
            return f1;
        }

        @Override
		public int get(String s, int i)
        {
            int j;
            if(defaulted(s))
                j = i;
            else
                j = ((Integer)get(s)).intValue();
            return j;
        }

        @Override
		public long get(String s, long l)
        {
            long l1;
            if(defaulted(s))
                l1 = l;
            else
                l1 = ((Long)get(s)).longValue();
            return l1;
        }

        @Override
		public Object get(String s, Object obj)
        {
            Object obj1;
            if(defaulted(s))
                obj1 = obj;
            else
                obj1 = get(s);
            return obj1;
        }

        @Override
		public short get(String s, short word0)
        {
            short word1;
            if(defaulted(s))
                word1 = word0;
            else
                word1 = ((Short)get(s)).shortValue();
            return word1;
        }

        @Override
		public boolean get(String s, boolean flag)
        {
            boolean flag1;
            if(defaulted(s))
                flag1 = flag;
            else
                flag1 = ((Boolean)get(s)).booleanValue();
            return flag1;
        }

        @Override
		public ObjectStreamClass getObjectStreamClass()
        {
            throw new UnsupportedOperationException();
        }

        private Map fields;
        private final CustomObjectInputStream this$0;

        public CustomGetField(Map map)
        {
            this$0 = CustomObjectInputStream.this;
            fields = map;
        }
    }

    public static interface StreamCallback
    {

        public abstract void close()
            throws IOException;

        public abstract void defaultReadObject()
            throws IOException;

        public abstract Map readFieldsFromStream()
            throws IOException;

        public abstract Object readFromStream()
            throws IOException;

        public abstract void registerValidation(ObjectInputValidation objectinputvalidation, int i)
            throws NotActiveException, InvalidObjectException;
    }


    public CustomObjectInputStream(StreamCallback streamcallback)
        throws IOException, SecurityException
    {
        callbacks = new FastStack(1);
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
	public static CustomObjectInputStream getInstance(DataHolder dataholder, StreamCallback streamcallback)
    {
        com/thoughtworks/xstream/core/util/CustomObjectInputStream;
        JVM INSTR monitorenter ;
        CustomObjectInputStream customobjectinputstream = (CustomObjectInputStream)dataholder.get(DATA_HOLDER_KEY);
        if(customobjectinputstream != null) goto _L2; else goto _L1
_L1:
        customobjectinputstream = new CustomObjectInputStream(streamcallback);
        dataholder.put(DATA_HOLDER_KEY, customobjectinputstream);
_L4:
        com/thoughtworks/xstream/core/util/CustomObjectInputStream;
        JVM INSTR monitorexit ;
        return customobjectinputstream;
_L2:
        customobjectinputstream.pushCallback(streamcallback);
        if(true) goto _L4; else goto _L3
_L3:
        IOException ioexception;
        ioexception;
        throw new ConversionException("Cannot create CustomObjectStream", ioexception);
        Exception exception;
        exception;
        com/thoughtworks/xstream/core/util/CustomObjectInputStream;
        JVM INSTR monitorexit ;
        throw exception;
    }

    @Override
	public int available()
    {
        throw new UnsupportedOperationException();
    }

    @Override
	public void close()
        throws IOException
    {
        peekCallback().close();
    }

    @Override
	public void defaultReadObject()
        throws IOException
    {
        peekCallback().defaultReadObject();
    }

    @Override
	public void mark(int i)
    {
        throw new UnsupportedOperationException();
    }

    @Override
	public boolean markSupported()
    {
        return false;
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
	public int read()
        throws IOException
    {
        return readUnsignedByte();
    }

    @Override
	public int read(byte abyte0[])
        throws IOException
    {
        return read(abyte0, 0, abyte0.length);
    }

    @Override
	public int read(byte abyte0[], int i, int j)
        throws IOException
    {
        byte abyte1[] = (byte[])peekCallback().readFromStream();
        if(abyte1.length != j)
        {
            throw new StreamCorruptedException("Expected " + j + " bytes from stream, got " + abyte1.length);
        } else
        {
            System.arraycopy(abyte1, 0, abyte0, i, j);
            return j;
        }
    }

    @Override
	public boolean readBoolean()
        throws IOException
    {
        return ((Boolean)peekCallback().readFromStream()).booleanValue();
    }

    @Override
	public byte readByte()
        throws IOException
    {
        return ((Byte)peekCallback().readFromStream()).byteValue();
    }

    @Override
	public char readChar()
        throws IOException
    {
        return ((Character)peekCallback().readFromStream()).charValue();
    }

    @Override
	public double readDouble()
        throws IOException
    {
        return ((Double)peekCallback().readFromStream()).doubleValue();
    }

    @Override
	public java.io.ObjectInputStream.GetField readFields()
        throws IOException
    {
        return new CustomGetField(peekCallback().readFieldsFromStream());
    }

    @Override
	public float readFloat()
        throws IOException
    {
        return ((Float)peekCallback().readFromStream()).floatValue();
    }

    @Override
	public void readFully(byte abyte0[])
        throws IOException
    {
        readFully(abyte0, 0, abyte0.length);
    }

    @Override
	public void readFully(byte abyte0[], int i, int j)
        throws IOException
    {
        System.arraycopy((byte[])peekCallback().readFromStream(), 0, abyte0, i, j);
    }

    @Override
	public int readInt()
        throws IOException
    {
        return ((Integer)peekCallback().readFromStream()).intValue();
    }

    @Override
	public String readLine()
    {
        throw new UnsupportedOperationException();
    }

    @Override
	public long readLong()
        throws IOException
    {
        return ((Long)peekCallback().readFromStream()).longValue();
    }

    @Override
	protected Object readObjectOverride()
        throws IOException
    {
        return peekCallback().readFromStream();
    }

    @Override
	public short readShort()
        throws IOException
    {
        return ((Short)peekCallback().readFromStream()).shortValue();
    }

    @Override
	public String readUTF()
        throws IOException
    {
        return (String)peekCallback().readFromStream();
    }

    @Override
	public Object readUnshared()
        throws IOException, ClassNotFoundException
    {
        return readObject();
    }

    @Override
	public int readUnsignedByte()
        throws IOException
    {
        int i = ((Byte)peekCallback().readFromStream()).byteValue();
        if(i < 0)
            i += 127;
        return i;
    }

    @Override
	public int readUnsignedShort()
        throws IOException
    {
        int i = ((Short)peekCallback().readFromStream()).shortValue();
        if(i < 0)
            i += 32767;
        return i;
    }

    @Override
	public void registerValidation(ObjectInputValidation objectinputvalidation, int i)
        throws NotActiveException, InvalidObjectException
    {
        peekCallback().registerValidation(objectinputvalidation, i);
    }

    @Override
	public void reset()
    {
        throw new UnsupportedOperationException();
    }

    @Override
	public long skip(long l)
    {
        throw new UnsupportedOperationException();
    }

    @Override
	public int skipBytes(int i)
    {
        throw new UnsupportedOperationException();
    }

    private static final String DATA_HOLDER_KEY;
    static Class class$com$thoughtworks$xstream$core$util$CustomObjectInputStream;
    private FastStack callbacks;

    static 
    {
        Class class1;
        if(class$com$thoughtworks$xstream$core$util$CustomObjectInputStream == null)
        {
            class1 = _mthclass$("com.thoughtworks.xstream.core.util.CustomObjectInputStream");
            class$com$thoughtworks$xstream$core$util$CustomObjectInputStream = class1;
        } else
        {
            class1 = class$com$thoughtworks$xstream$core$util$CustomObjectInputStream;
        }
        DATA_HOLDER_KEY = class1.getName();
    }
}
