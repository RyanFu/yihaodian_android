// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.reflection;

import com.thoughtworks.xstream.converters.*;
import com.thoughtworks.xstream.core.util.CustomObjectInputStream;
import com.thoughtworks.xstream.core.util.CustomObjectOutputStream;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.mapper.Mapper;
import java.io.Externalizable;
import java.io.IOException;

public class ExternalizableConverter
    implements Converter
{

    public ExternalizableConverter(Mapper mapper1)
    {
        mapper = mapper1;
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

    @Override
	public boolean canConvert(Class class1)
    {
        Class class2;
        if(class$java$io$Externalizable == null)
        {
            class2 = _mthclass$("java.io.Externalizable");
            class$java$io$Externalizable = class2;
        } else
        {
            class2 = class$java$io$Externalizable;
        }
        return class2.isAssignableFrom(class1);
    }

    @Override
	public void marshal(Object obj, final HierarchicalStreamWriter writer, final MarshallingContext context)
    {
        try
        {
            Externalizable externalizable = (Externalizable)obj;
            CustomObjectOutputStream customobjectoutputstream = CustomObjectOutputStream.getInstance(context, new _cls1());
            externalizable.writeExternal(customobjectoutputstream);
            customobjectoutputstream.popCallback();
            return;
        }
        catch(IOException ioexception)
        {
            throw new ConversionException("Cannot serialize " + obj.getClass().getName() + " using Externalization", ioexception);
        }
    }

    @Override
	public Object unmarshal(final HierarchicalStreamReader reader, final UnmarshallingContext context)
    {
        Class class1 = context.getRequiredType();
        final Externalizable externalizable;
        try
        {
            externalizable = (Externalizable)class1.newInstance();
            CustomObjectInputStream customobjectinputstream = CustomObjectInputStream.getInstance(context, new _cls2());
            externalizable.readExternal(customobjectinputstream);
            customobjectinputstream.popCallback();
        }
        catch(InstantiationException instantiationexception)
        {
            throw new ConversionException("Cannot construct " + class1.getClass(), instantiationexception);
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            throw new ConversionException("Cannot construct " + class1.getClass(), illegalaccessexception);
        }
        catch(IOException ioexception)
        {
            throw new ConversionException("Cannot externalize " + class1.getClass(), ioexception);
        }
        catch(ClassNotFoundException classnotfoundexception)
        {
            throw new ConversionException("Cannot externalize " + class1.getClass(), classnotfoundexception);
        }
        return externalizable;
    }

    static Class class$java$io$Externalizable;
    private Mapper mapper;


    private class _cls1
        implements com.thoughtworks.xstream.core.util.CustomObjectOutputStream.StreamCallback
    {

        @Override
		public void close()
        {
            throw new UnsupportedOperationException("Objects are not allowed to call ObjectOutput.close() from writeExternal()");
        }

        @Override
		public void defaultWriteObject()
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void flush()
        {
            writer.flush();
        }

        public void writeFieldsToStream(Map map)
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public void writeToStream(Object obj)
        {
            if(obj == null)
            {
                writer.startNode("null");
                writer.endNode();
            } else
            {
                ExtendedHierarchicalStreamWriterHelper.startNode(writer, mapper.serializedClass(obj.getClass()), obj.getClass());
                context.convertAnother(obj);
                writer.endNode();
            }
        }

        private final ExternalizableConverter this$0;
        private final MarshallingContext val$context;
        private final HierarchicalStreamWriter val$writer;

        _cls1()
        {
            this$0 = ExternalizableConverter.this;
            writer = hierarchicalstreamwriter;
            context = marshallingcontext;
        }
    }


    private class _cls2
        implements com.thoughtworks.xstream.core.util.CustomObjectInputStream.StreamCallback
    {

        @Override
		public void close()
        {
            throw new UnsupportedOperationException("Objects are not allowed to call ObjectInput.close() from readExternal()");
        }

        @Override
		public void defaultReadObject()
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public Map readFieldsFromStream()
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public Object readFromStream()
        {
            reader.moveDown();
            Class class1 = HierarchicalStreams.readClassType(reader, mapper);
            Object obj = context.convertAnother(externalizable, class1);
            reader.moveUp();
            return obj;
        }

        public void registerValidation(ObjectInputValidation objectinputvalidation, int i)
            throws NotActiveException
        {
            throw new NotActiveException("stream inactive");
        }

        private final ExternalizableConverter this$0;
        private final UnmarshallingContext val$context;
        private final Externalizable val$externalizable;
        private final HierarchicalStreamReader val$reader;

        _cls2()
            throws NotActiveException
        {
            this$0 = ExternalizableConverter.this;
            reader = hierarchicalstreamreader;
            context = unmarshallingcontext;
            externalizable = externalizable1;
        }
    }

}
