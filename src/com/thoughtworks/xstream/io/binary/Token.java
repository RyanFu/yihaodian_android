// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io.binary;

import com.thoughtworks.xstream.io.StreamException;
import java.io.*;

public abstract class Token
{
    public static class Value extends Token
    {

        @Override
		public void readFrom(DataInput datainput, byte byte0)
            throws IOException
        {
            value = readString(datainput);
        }

        @Override
		public void writeTo(DataOutput dataoutput, byte byte0)
            throws IOException
        {
            writeString(dataoutput, value);
        }

        public Value()
        {
            super((byte)6);
        }

        public Value(String s)
        {
            super((byte)6);
            value = s;
        }
    }

    public static class Attribute extends Token
    {

        @Override
		public void readFrom(DataInput datainput, byte byte0)
            throws IOException
        {
            id = readId(datainput, byte0);
            value = readString(datainput);
        }

        @Override
		public void writeTo(DataOutput dataoutput, byte byte0)
            throws IOException
        {
            writeId(dataoutput, id, byte0);
            writeString(dataoutput, value);
        }

        public Attribute()
        {
            super((byte)5);
        }

        public Attribute(long l, String s)
        {
            super((byte)5);
            id = l;
            value = s;
        }
    }

    public static class EndNode extends Token
    {

        @Override
		public void readFrom(DataInput datainput, byte byte0)
        {
        }

        @Override
		public void writeTo(DataOutput dataoutput, byte byte0)
        {
        }

        public EndNode()
        {
            super((byte)4);
        }
    }

    public static class StartNode extends Token
    {

        @Override
		public void readFrom(DataInput datainput, byte byte0)
            throws IOException
        {
            id = readId(datainput, byte0);
        }

        @Override
		public void writeTo(DataOutput dataoutput, byte byte0)
            throws IOException
        {
            writeId(dataoutput, id, byte0);
        }

        public StartNode()
        {
            super((byte)3);
        }

        public StartNode(long l)
        {
            super((byte)3);
            id = l;
        }
    }

    public static class MapIdToValue extends Token
    {

        @Override
		public void readFrom(DataInput datainput, byte byte0)
            throws IOException
        {
            id = readId(datainput, byte0);
            value = readString(datainput);
        }

        @Override
		public void writeTo(DataOutput dataoutput, byte byte0)
            throws IOException
        {
            writeId(dataoutput, id, byte0);
            writeString(dataoutput, value);
        }

        public MapIdToValue()
        {
            super((byte)2);
        }

        public MapIdToValue(long l, String s)
        {
            super((byte)2);
            id = l;
            value = s;
        }
    }

    public static class Formatter
    {

        private Token contructToken(byte byte0)
        {
            byte0;
            JVM INSTR tableswitch 2 6: default 36
        //                       2 56
        //                       3 46
        //                       4 78
        //                       5 67
        //                       6 89;
               goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
            throw new StreamException("Unknown token type");
_L3:
            Object obj = new StartNode();
_L8:
            return ((Token) (obj));
_L2:
            obj = new MapIdToValue();
            continue; /* Loop/switch isn't completed */
_L5:
            obj = new Attribute();
            continue; /* Loop/switch isn't completed */
_L4:
            obj = new EndNode();
            continue; /* Loop/switch isn't completed */
_L6:
            obj = new Value();
            if(true) goto _L8; else goto _L7
_L7:
        }

        public Token read(DataInput datainput)
            throws IOException
        {
            byte byte0 = datainput.readByte();
            int i = byte0 & 7;
            int j = byte0 & 0x38;
            Token token = contructToken(i);
            token.readFrom(datainput, j);
            return token;
        }

        public void write(DataOutput dataoutput, Token token)
            throws IOException
        {
            long l = token.getId();
            byte byte0;
            if(l <= 255L)
                byte0 = 8;
            else
            if(l <= 65535L)
                byte0 = 16;
            else
            if(l <= 0xffffffffL)
                byte0 = 24;
            else
                byte0 = 32;
            dataoutput.write(byte0 + token.getType());
            token.writeTo(dataoutput, byte0);
        }

        public Formatter()
        {
        }
    }


    public Token(byte byte0)
    {
        id = -1L;
        type = byte0;
    }

    @Override
	public boolean equals(Object obj)
    {
        boolean flag;
        if(this == obj)
            flag = true;
        else
        if(obj == null || getClass() != obj.getClass())
        {
            flag = false;
        } else
        {
            Token token = (Token)obj;
            if(id != token.id)
                flag = false;
            else
            if(type != token.type)
                flag = false;
            else
            if(value == null ? token.value != null : !value.equals(token.value))
                flag = false;
            else
                flag = true;
        }
        return flag;
    }

    public long getId()
    {
        return id;
    }

    public byte getType()
    {
        return type;
    }

    public String getValue()
    {
        return value;
    }

    @Override
	public int hashCode()
    {
        int i = 29 * (29 * type + (int)(id ^ id >>> 32));
        int j;
        if(value != null)
            j = value.hashCode();
        else
            j = 0;
        return i + j;
    }

    public abstract void readFrom(DataInput datainput, byte byte0)
        throws IOException;

    protected long readId(DataInput datainput, byte byte0)
        throws IOException
    {
        byte0;
        JVM INSTR lookupswitch 4: default 44
    //                   8: 71
    //                   16: 85
    //                   24: 99
    //                   32: 113;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        throw new Error("Unknown idType " + byte0);
_L2:
        long l = datainput.readByte() + 128;
_L7:
        return l;
_L3:
        l = datainput.readShort() + 32768;
        continue; /* Loop/switch isn't completed */
_L4:
        l = datainput.readInt() + 0x80000000;
        continue; /* Loop/switch isn't completed */
_L5:
        l = datainput.readLong() + 0x8000000000000000L;
        if(true) goto _L7; else goto _L6
_L6:
    }

    protected String readString(DataInput datainput)
        throws IOException
    {
        return datainput.readUTF();
    }

    @Override
	public String toString()
    {
        return getClass().getName() + " [id=" + id + ", value='" + value + "']";
    }

    protected void writeId(DataOutput dataoutput, long l, byte byte0)
        throws IOException
    {
        if(l < 0L)
            throw new IOException("id must not be negative " + l);
        byte0;
        JVM INSTR lookupswitch 4: default 76
    //                   8: 104
    //                   16: 117
    //                   24: 133
    //                   32: 147;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        throw new Error("Unknown idType " + byte0);
_L2:
        dataoutput.writeByte(-128 + (byte)(int)l);
_L7:
        return;
_L3:
        dataoutput.writeShort(-32768 + (short)(int)l);
        continue; /* Loop/switch isn't completed */
_L4:
        dataoutput.writeInt(0x80000000 + (int)l);
        continue; /* Loop/switch isn't completed */
_L5:
        dataoutput.writeLong(0x8000000000000000L + l);
        if(true) goto _L7; else goto _L6
_L6:
    }

    protected void writeString(DataOutput dataoutput, String s)
        throws IOException
    {
        dataoutput.writeUTF(s);
    }

    public abstract void writeTo(DataOutput dataoutput, byte byte0)
        throws IOException;

    private static final byte ID_EIGHT_BYTES = 32;
    private static final byte ID_FOUR_BYTES = 24;
    private static final byte ID_MASK = 56;
    private static final byte ID_ONE_BYTE = 8;
    private static final byte ID_TWO_BYTES = 16;
    public static final byte TYPE_ATTRIBUTE = 5;
    public static final byte TYPE_END_NODE = 4;
    public static final byte TYPE_MAP_ID_TO_VALUE = 2;
    private static final byte TYPE_MASK = 7;
    public static final byte TYPE_START_NODE = 3;
    public static final byte TYPE_VALUE = 6;
    public static final byte TYPE_VERSION = 1;
    protected long id;
    private final byte type;
    protected String value;
}
