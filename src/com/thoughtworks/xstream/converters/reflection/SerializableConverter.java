// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.reflection;

import com.thoughtworks.xstream.converters.*;
import com.thoughtworks.xstream.core.util.*;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.mapper.Mapper;
import java.io.IOException;
import java.io.ObjectStreamField;
import java.lang.reflect.Field;
import java.util.*;

// Referenced classes of package com.thoughtworks.xstream.converters.reflection:
//            AbstractReflectionConverter, SerializationMethodInvoker, ObjectAccessException, ReflectionProvider, 
//            ReflectionProviderWrapper

public class SerializableConverter extends AbstractReflectionConverter
{
    private static class UnserializableParentsReflectionProvider extends ReflectionProviderWrapper
    {

        public void visitSerializableFields(Object obj, final ReflectionProvider.Visitor visitor)
        {
            class _cls1
                implements ReflectionProvider.Visitor
            {

                public void visit(String s, Class class1, Class class2, Object obj1)
                {
                    Class class3;
                    if(SerializableConverter.class$java$io$Serializable == null)
                    {
                        class3 = SerializableConverter._mthclass$("java.io.Serializable");
                        SerializableConverter.class$java$io$Serializable = class3;
                    } else
                    {
                        class3 = SerializableConverter.class$java$io$Serializable;
                    }
                    if(!class3.isAssignableFrom(class2))
                        visitor.visit(s, class1, class2, obj1);
                }

                private final UnserializableParentsReflectionProvider this$0;
                private final ReflectionProvider.Visitor val$visitor;

                _cls1()
                {
                    this$0 = UnserializableParentsReflectionProvider.this;
                    visitor = visitor1;
                }
            }

            wrapped.visitSerializableFields(obj, new _cls1());
        }

        public UnserializableParentsReflectionProvider(ReflectionProvider reflectionprovider)
        {
            super(reflectionprovider);
        }
    }


    public SerializableConverter(Mapper mapper, ReflectionProvider reflectionprovider)
    {
        super(mapper, new UnserializableParentsReflectionProvider(reflectionprovider));
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

    private boolean isSerializable(Class class1)
    {
        Class class2;
        boolean flag;
        if(class$java$io$Serializable == null)
        {
            class2 = _mthclass$("java.io.Serializable");
            class$java$io$Serializable = class2;
        } else
        {
            class2 = class$java$io$Serializable;
        }
        if(class2.isAssignableFrom(class1) && (serializationMethodInvoker.supportsReadObject(class1, true) || serializationMethodInvoker.supportsWriteObject(class1, true)))
            flag = true;
        else
            flag = false;
        return flag;
    }

    private void marshalUnserializableParent(HierarchicalStreamWriter hierarchicalstreamwriter, MarshallingContext marshallingcontext, Object obj)
    {
        hierarchicalstreamwriter.startNode("unserializable-parents");
        super.doMarshal(obj, hierarchicalstreamwriter, marshallingcontext);
        hierarchicalstreamwriter.endNode();
    }

    private Object readField(ObjectStreamField objectstreamfield, Class class1, Object obj)
    {
        Object obj1;
        try
        {
            Field field = class1.getDeclaredField(objectstreamfield.getName());
            field.setAccessible(true);
            obj1 = field.get(obj);
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            throw new ObjectAccessException("Could not get field " + objectstreamfield.getClass() + "." + objectstreamfield.getName(), illegalargumentexception);
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            throw new ObjectAccessException("Could not get field " + objectstreamfield.getClass() + "." + objectstreamfield.getName(), illegalaccessexception);
        }
        catch(NoSuchFieldException nosuchfieldexception)
        {
            throw new ObjectAccessException("Could not get field " + objectstreamfield.getClass() + "." + objectstreamfield.getName(), nosuchfieldexception);
        }
        catch(SecurityException securityexception)
        {
            throw new ObjectAccessException("Could not get field " + objectstreamfield.getClass() + "." + objectstreamfield.getName(), securityexception);
        }
        return obj1;
    }

    public boolean canConvert(Class class1)
    {
        return isSerializable(class1);
    }

    public void doMarshal(final Object source, final HierarchicalStreamWriter writer, final MarshallingContext context)
    {
        final Class currentType[];
        final boolean writtenClassWrapper[];
        _cls1 _lcls1;
        boolean flag;
        String s = mapper.aliasForSystemAttribute("serialization");
        if(s != null)
            writer.addAttribute(s, "custom");
        currentType = new Class[1];
        writtenClassWrapper = new boolean[1];
        writtenClassWrapper[0] = false;
        _lcls1 = new _cls1();
        flag = false;
        Iterator iterator = hierarchyFor(source.getClass()).iterator();
_L2:
        IOException ioexception;
        do
        {
            if(!iterator.hasNext())
                break MISSING_BLOCK_LABEL_335;
            currentType[0] = (Class)iterator.next();
            Class class1;
            if(class$java$io$Serializable == null)
            {
                class1 = _mthclass$("java.io.Serializable");
                class$java$io$Serializable = class1;
            } else
            {
                class1 = class$java$io$Serializable;
            }
            if(!class1.isAssignableFrom(currentType[0]))
            {
                flag = true;
            } else
            {
                if(flag)
                {
                    marshalUnserializableParent(writer, context, source);
                    flag = false;
                }
                if(!serializationMethodInvoker.supportsWriteObject(currentType[0], false))
                    break MISSING_BLOCK_LABEL_252;
                writtenClassWrapper[0] = true;
                writer.startNode(mapper.serializedClass(currentType[0]));
                CustomObjectOutputStream customobjectoutputstream = CustomObjectOutputStream.getInstance(context, _lcls1);
                serializationMethodInvoker.callWriteObject(currentType[0], source, customobjectoutputstream);
                customobjectoutputstream.popCallback();
                writer.endNode();
            }
        } while(true);
        try
        {
            if(serializationMethodInvoker.supportsReadObject(currentType[0], false))
            {
                writtenClassWrapper[0] = true;
                writer.startNode(mapper.serializedClass(currentType[0]));
                _lcls1.defaultWriteObject();
                writer.endNode();
            } else
            {
                writtenClassWrapper[0] = false;
                _lcls1.defaultWriteObject();
                if(writtenClassWrapper[0])
                    writer.endNode();
            }
        }
        // Misplaced declaration of an exception variable
        catch(IOException ioexception)
        {
            throw new ObjectAccessException("Could not call defaultWriteObject()", ioexception);
        }
        if(true) goto _L2; else goto _L1
_L1:
    }

    protected void doMarshalConditionally(Object obj, HierarchicalStreamWriter hierarchicalstreamwriter, MarshallingContext marshallingcontext)
    {
        if(isSerializable(obj.getClass()))
            doMarshal(obj, hierarchicalstreamwriter, marshallingcontext);
        else
            super.doMarshal(obj, hierarchicalstreamwriter, marshallingcontext);
    }

    public Object doUnmarshal(final Object result, final HierarchicalStreamReader reader, final UnmarshallingContext context)
    {
        final Class currentType[] = new Class[1];
        String s = mapper.aliasForSystemAttribute("serialization");
        if(s != null && !"custom".equals(reader.getAttribute(s)))
            throw new ConversionException("Cannot deserialize object with new readObject()/writeObject() methods");
        _cls2 _lcls2 = new _cls2();
        while(reader.hasMoreChildren()) 
        {
            reader.moveDown();
            String s1 = reader.getNodeName();
            if(s1.equals("unserializable-parents"))
            {
                super.doUnmarshal(result, reader, context);
            } else
            {
                String s2 = HierarchicalStreams.readClassAttribute(reader, mapper);
                if(s2 == null)
                    currentType[0] = mapper.defaultImplementationOf(mapper.realClass(s1));
                else
                    currentType[0] = mapper.realClass(s2);
                if(serializationMethodInvoker.supportsReadObject(currentType[0], false))
                {
                    CustomObjectInputStream customobjectinputstream = CustomObjectInputStream.getInstance(context, _lcls2);
                    serializationMethodInvoker.callReadObject(currentType[0], result, customobjectinputstream);
                    customobjectinputstream.popCallback();
                } else
                {
                    try
                    {
                        _lcls2.defaultReadObject();
                    }
                    catch(IOException ioexception)
                    {
                        throw new ObjectAccessException("Could not call defaultWriteObject()", ioexception);
                    }
                }
            }
            reader.moveUp();
        }
        return result;
    }

    protected Object doUnmarshalConditionally(Object obj, HierarchicalStreamReader hierarchicalstreamreader, UnmarshallingContext unmarshallingcontext)
    {
        Object obj1;
        if(isSerializable(obj.getClass()))
            obj1 = doUnmarshal(obj, hierarchicalstreamreader, unmarshallingcontext);
        else
            obj1 = super.doUnmarshal(obj, hierarchicalstreamreader, unmarshallingcontext);
        return obj1;
    }

    protected List hierarchyFor(Class class1)
    {
        ArrayList arraylist = new ArrayList();
        do
        {
            Class class2;
            if(class$java$lang$Object == null)
            {
                class2 = _mthclass$("java.lang.Object");
                class$java$lang$Object = class2;
            } else
            {
                class2 = class$java$lang$Object;
            }
            if(class1 != class2)
            {
                arraylist.add(class1);
                class1 = class1.getSuperclass();
            } else
            {
                Collections.reverse(arraylist);
                return arraylist;
            }
        } while(true);
    }

    private static final String ATTRIBUTE_CLASS = "class";
    private static final String ATTRIBUTE_NAME = "name";
    private static final String ATTRIBUTE_SERIALIZATION = "serialization";
    private static final String ATTRIBUTE_VALUE_CUSTOM = "custom";
    private static final String ELEMENT_DEFAULT = "default";
    private static final String ELEMENT_FIELD = "field";
    private static final String ELEMENT_FIELDS = "fields";
    private static final String ELEMENT_NULL = "null";
    private static final String ELEMENT_UNSERIALIZABLE_PARENTS = "unserializable-parents";
    static Class class$java$io$Serializable;
    static Class class$java$lang$Object;


    private class _cls1
        implements com.thoughtworks.xstream.core.util.CustomObjectOutputStream.StreamCallback
    {

        public void close()
        {
            throw new UnsupportedOperationException("Objects are not allowed to call ObjectOutputStream.close() from writeObject()");
        }

        public void defaultWriteObject()
        {
            boolean flag;
            ObjectStreamClass objectstreamclass;
            flag = false;
            objectstreamclass = ObjectStreamClass.lookup(currentType[0]);
            if(objectstreamclass != null) goto _L2; else goto _L1
_L1:
            return;
_L2:
            ObjectStreamField aobjectstreamfield[] = objectstreamclass.getFields();
            int i = 0;
            while(i < aobjectstreamfield.length) 
            {
                ObjectStreamField objectstreamfield = aobjectstreamfield[i];
                Object obj = readField(objectstreamfield, currentType[0], source);
                if(obj != null)
                {
                    if(!writtenClassWrapper[0])
                    {
                        writer.startNode(mapper.serializedClass(currentType[0]));
                        writtenClassWrapper[0] = true;
                    }
                    if(!flag)
                    {
                        writer.startNode("default");
                        flag = true;
                    }
                    if(mapper.shouldSerializeMember(currentType[0], objectstreamfield.getName()))
                    {
                        ExtendedHierarchicalStreamWriterHelper.startNode(writer, mapper.serializedMember(source.getClass(), objectstreamfield.getName()), objectstreamfield.getType());
                        Class class1 = obj.getClass();
                        if(!class1.equals(mapper.defaultImplementationOf(objectstreamfield.getType())))
                        {
                            String s = mapper.aliasForSystemAttribute("class");
                            if(s != null)
                                writer.addAttribute(s, mapper.serializedClass(class1));
                        }
                        context.convertAnother(obj);
                        writer.endNode();
                    }
                }
                i++;
            }
            if(writtenClassWrapper[0] && !flag)
            {
                writer.startNode("default");
                writer.endNode();
            } else
            if(flag)
                writer.endNode();
            if(true) goto _L1; else goto _L3
_L3:
        }

        public void flush()
        {
            writer.flush();
        }

        public void writeFieldsToStream(Map map)
        {
            ObjectStreamClass objectstreamclass = ObjectStreamClass.lookup(currentType[0]);
            writer.startNode("default");
            Iterator iterator = map.keySet().iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                String s = (String)iterator.next();
                if(mapper.shouldSerializeMember(currentType[0], s))
                {
                    ObjectStreamField objectstreamfield = objectstreamclass.getField(s);
                    Object obj = map.get(s);
                    if(objectstreamfield == null)
                        throw new ObjectAccessException("Class " + obj.getClass().getName() + " may not write a field named '" + s + "'");
                    if(obj != null)
                    {
                        ExtendedHierarchicalStreamWriterHelper.startNode(writer, mapper.serializedMember(source.getClass(), s), objectstreamfield.getType());
                        if(objectstreamfield.getType() != obj.getClass() && !objectstreamfield.getType().isPrimitive())
                        {
                            String s1 = mapper.aliasForSystemAttribute("class");
                            if(s1 != null)
                                writer.addAttribute(s1, mapper.serializedClass(obj.getClass()));
                        }
                        context.convertAnother(obj);
                        writer.endNode();
                    }
                }
            } while(true);
            writer.endNode();
        }

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

        private final SerializableConverter this$0;
        private final MarshallingContext val$context;
        private final Class val$currentType[];
        private final Object val$source;
        private final HierarchicalStreamWriter val$writer;
        private final boolean val$writtenClassWrapper[];

        _cls1()
        {
            this$0 = SerializableConverter.this;
            writer = hierarchicalstreamwriter;
            context = marshallingcontext;
            currentType = aclass;
            source = obj;
            writtenClassWrapper = aflag;
        }
    }


    private class _cls2
        implements com.thoughtworks.xstream.core.util.CustomObjectInputStream.StreamCallback
    {

        public void close()
        {
            throw new UnsupportedOperationException("Objects are not allowed to call ObjectInputStream.close() from readObject()");
        }

        public void defaultReadObject()
        {
            if(reader.hasMoreChildren())
            {
                reader.moveDown();
                if(!reader.getNodeName().equals("default"))
                    throw new ConversionException("Expected <default/> element in readObject() stream");
                while(reader.hasMoreChildren()) 
                {
                    reader.moveDown();
                    String s = mapper.realMember(currentType[0], reader.getNodeName());
                    if(mapper.shouldSerializeMember(currentType[0], s))
                    {
                        String s1 = HierarchicalStreams.readClassAttribute(reader, mapper);
                        Class class1;
                        Object obj;
                        if(s1 != null)
                            class1 = mapper.realClass(s1);
                        else
                            class1 = mapper.defaultImplementationOf(reflectionProvider.getFieldType(result, s, currentType[0]));
                        obj = context.convertAnother(result, class1);
                        reflectionProvider.writeField(result, s, obj, currentType[0]);
                    }
                    reader.moveUp();
                }
                reader.moveUp();
            }
        }

        public Map readFieldsFromStream()
        {
            HashMap hashmap;
            hashmap = new HashMap();
            reader.moveDown();
            if(!reader.getNodeName().equals("fields")) goto _L2; else goto _L1
_L1:
            for(; reader.hasMoreChildren(); reader.moveUp())
            {
                reader.moveDown();
                if(!reader.getNodeName().equals("field"))
                    throw new ConversionException("Expected <field/> element inside <field/>");
                String s2 = reader.getAttribute("name");
                Class class2 = mapper.realClass(reader.getAttribute("class"));
                hashmap.put(s2, context.convertAnother(result, class2));
            }

              goto _L3
_L2:
            if(!reader.getNodeName().equals("default")) goto _L5; else goto _L4
_L4:
            ObjectStreamClass objectstreamclass = ObjectStreamClass.lookup(currentType[0]);
_L7:
            while(reader.hasMoreChildren()) 
            {
                reader.moveDown();
                String s = mapper.realMember(currentType[0], reader.getNodeName());
                if(mapper.shouldSerializeMember(currentType[0], s))
                {
                    String s1 = HierarchicalStreams.readClassAttribute(reader, mapper);
                    Class class1;
                    if(s1 != null)
                    {
                        class1 = mapper.realClass(s1);
                    } else
                    {
                        ObjectStreamField objectstreamfield = objectstreamclass.getField(s);
                        if(objectstreamfield == null)
                            throw new ObjectAccessException("Class " + currentType[0] + " does not contain a field named '" + s + "'");
                        class1 = objectstreamfield.getType();
                    }
                    hashmap.put(s, context.convertAnother(result, class1));
                }
                reader.moveUp();
            }
              goto _L3
_L5:
            throw new ConversionException("Expected <fields/> or <default/> element when calling ObjectInputStream.readFields()");
_L3:
            reader.moveUp();
            return hashmap;
            if(true) goto _L7; else goto _L6
_L6:
        }

        public Object readFromStream()
        {
            reader.moveDown();
            Class class1 = HierarchicalStreams.readClassType(reader, mapper);
            Object obj = context.convertAnother(result, class1);
            reader.moveUp();
            return obj;
        }

        public void registerValidation(final ObjectInputValidation validation, int i)
        {
            class _cls1
                implements Runnable
            {

                public void run()
                {
                    try
                    {
                        validation.validateObject();
                        return;
                    }
                    catch(InvalidObjectException invalidobjectexception)
                    {
                        throw new ObjectAccessException("Cannot validate object : " + invalidobjectexception.getMessage(), invalidobjectexception);
                    }
                }

                private final _cls2 this$1;
                private final ObjectInputValidation val$validation;

                _cls1()
                {
                    this$1 = _cls2.this;
                    validation = objectinputvalidation;
                }
            }

            context.addCompletionCallback(new _cls1(), i);
        }

        private final SerializableConverter this$0;
        private final UnmarshallingContext val$context;
        private final Class val$currentType[];
        private final HierarchicalStreamReader val$reader;
        private final Object val$result;

        _cls2()
        {
            this$0 = SerializableConverter.this;
            reader = hierarchicalstreamreader;
            context = unmarshallingcontext;
            result = obj;
            currentType = aclass;
        }
    }

}
