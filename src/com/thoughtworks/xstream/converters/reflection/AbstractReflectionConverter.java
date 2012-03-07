// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.reflection;

import com.thoughtworks.xstream.converters.*;
import com.thoughtworks.xstream.core.util.HierarchicalStreams;
import com.thoughtworks.xstream.core.util.Primitives;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.mapper.Mapper;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

// Referenced classes of package com.thoughtworks.xstream.converters.reflection:
//            SerializationMethodInvoker, ReflectionProvider, ObjectAccessException, PureJavaReflectionProvider

public abstract class AbstractReflectionConverter
    implements Converter
{
    public static class DuplicateFieldException extends ConversionException
    {

        public DuplicateFieldException(String s)
        {
            super(s);
            add("duplicate-field", s);
        }
    }

    private static class SeenFields
    {

        public void add(Class class1, String s)
        {
            String s1 = s;
            if(class1 != null)
                s1 = s1 + " [" + class1.getName() + "]";
            if(seen.contains(s1))
            {
                throw new DuplicateFieldException(s1);
            } else
            {
                seen.add(s1);
                return;
            }
        }

        private Set seen;

        private SeenFields()
        {
            seen = new HashSet();
        }

        SeenFields(_cls1 _pcls1)
        {
            this();
        }
    }


    public AbstractReflectionConverter(Mapper mapper1, ReflectionProvider reflectionprovider)
    {
        mapper = mapper1;
        reflectionProvider = reflectionprovider;
        serializationMethodInvoker = new SerializationMethodInvoker();
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

    private Class determineType(HierarchicalStreamReader hierarchicalstreamreader, boolean flag, Object obj, String s, Class class1)
    {
        String s1 = HierarchicalStreams.readClassAttribute(hierarchicalstreamreader, mapper);
        if(s1 == null) goto _L2; else goto _L1
_L1:
        Class class2 = mapper.realClass(s1);
_L4:
        return class2;
_L2:
        if(!flag)
        {
            Class class3 = mapper.getItemTypeForItemFieldName(obj.getClass(), s);
            if(class3 != null)
            {
                class2 = class3;
                continue; /* Loop/switch isn't completed */
            }
            String s2 = hierarchicalstreamreader.getNodeName();
            if(class1 == null)
            {
                Class class4 = obj.getClass();
                do
                {
                    if(class4 == null)
                        break;
                    if(!mapper.shouldSerializeMember(class4, s2))
                    {
                        class2 = null;
                        continue; /* Loop/switch isn't completed */
                    }
                    class4 = class4.getSuperclass();
                } while(true);
            }
            class2 = mapper.realClass(s2);
        } else
        {
            class2 = mapper.defaultImplementationOf(reflectionProvider.getFieldType(obj, s, class1));
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private Class determineWhichClassDefinesField(HierarchicalStreamReader hierarchicalstreamreader)
    {
        String s = mapper.aliasForSystemAttribute("defined-in");
        String s1;
        Class class1;
        if(s == null)
            s1 = null;
        else
            s1 = hierarchicalstreamreader.getAttribute(s);
        if(s1 == null)
            class1 = null;
        else
            class1 = mapper.realClass(s1);
        return class1;
    }

    private Object readResolve()
    {
        serializationMethodInvoker = new SerializationMethodInvoker();
        return this;
    }

    private Map writeValueToImplicitCollection(UnmarshallingContext unmarshallingcontext, Object obj, Map map, Object obj1, String s)
    {
        Mapper mapper1 = mapper;
        Class class1 = unmarshallingcontext.getRequiredType();
        Class class2;
        String s1;
        if(obj != null)
            class2 = obj.getClass();
        else
        if(class$com$thoughtworks$xstream$mapper$Mapper$Null == null)
        {
            class2 = _mthclass$("com.thoughtworks.xstream.mapper.Mapper$Null");
            class$com$thoughtworks$xstream$mapper$Mapper$Null = class2;
        } else
        {
            class2 = class$com$thoughtworks$xstream$mapper$Mapper$Null;
        }
        s1 = mapper1.getFieldNameForItemTypeAndName(class1, class2, s);
        if(s1 != null)
        {
            if(map == null)
                map = new HashMap();
            Collection collection = (Collection)map.get(s1);
            if(collection == null)
            {
                Class class3 = mapper.defaultImplementationOf(reflectionProvider.getFieldType(obj1, s1, null));
                Class class4;
                if(class$java$util$Collection == null)
                {
                    class4 = _mthclass$("java.util.Collection");
                    class$java$util$Collection = class4;
                } else
                {
                    class4 = class$java$util$Collection;
                }
                if(!class4.isAssignableFrom(class3))
                    throw new ObjectAccessException("Field " + s1 + " of " + obj1.getClass().getName() + " is configured for an implicit Collection, but field is of type " + class3.getName());
                if(pureJavaReflectionProvider == null)
                    pureJavaReflectionProvider = new PureJavaReflectionProvider();
                collection = (Collection)pureJavaReflectionProvider.newInstance(class3);
                reflectionProvider.writeField(obj1, s1, collection, null);
                map.put(s1, collection);
            }
            collection.add(obj);
            return map;
        } else
        {
            throw new ConversionException("Element " + s + " of type " + obj.getClass().getName() + " is not defined as field in type " + obj1.getClass().getName());
        }
    }

    protected void doMarshal(final Object source, final HierarchicalStreamWriter writer, final MarshallingContext context)
    {
        final HashSet seenFields = new HashSet();
        final HashMap defaultFieldDefinition = new HashMap();
        reflectionProvider.visitSerializableFields(source, new _cls1());
        reflectionProvider.visitSerializableFields(source, new _cls2());
    }

    public Object doUnmarshal(Object obj, HierarchicalStreamReader hierarchicalstreamreader, UnmarshallingContext unmarshallingcontext)
    {
        SeenFields seenfields;
        Map map;
        seenfields = new SeenFields(null);
        Iterator iterator = hierarchicalstreamreader.getAttributeNames();
        do
        {
            if(!iterator.hasNext())
                break;
            String s2 = (String)iterator.next();
            String s3 = mapper.realMember(obj.getClass(), mapper.attributeForAlias(s2));
            Class class6 = determineWhichClassDefinesField(hierarchicalstreamreader);
            if(reflectionProvider.fieldDefinedInClass(s3, obj.getClass()))
            {
                Field field1 = reflectionProvider.getField(obj.getClass(), s3);
                if(!Modifier.isTransient(field1.getModifiers()) || shouldUnmarshalTransientFields())
                {
                    SingleValueConverter singlevalueconverter = mapper.getConverterFromAttribute(field1.getDeclaringClass(), s3, field1.getType());
                    Class class7 = field1.getType();
                    if(singlevalueconverter != null)
                    {
                        Object obj2 = singlevalueconverter.fromString(hierarchicalstreamreader.getAttribute(s2));
                        if(class7.isPrimitive())
                            class7 = Primitives.box(class7);
                        if(obj2 != null)
                        {
                            Class class8 = obj2.getClass();
                            if(!class7.isAssignableFrom(class8))
                                throw new ConversionException("Cannot convert type " + obj2.getClass().getName() + " to type " + class7.getName());
                        }
                        reflectionProvider.writeField(obj, s3, obj2, class6);
                        seenfields.add(class6, s3);
                    }
                }
            }
        } while(true);
        map = null;
_L6:
        String s;
        String s1;
        Class class1;
        boolean flag;
        Class class2;
        if(!hierarchicalstreamreader.hasMoreChildren())
            break; /* Loop/switch isn't completed */
        hierarchicalstreamreader.moveDown();
        s = hierarchicalstreamreader.getNodeName();
        s1 = mapper.realMember(obj.getClass(), s);
        com.thoughtworks.xstream.mapper.Mapper.ImplicitCollectionMapping implicitcollectionmapping = mapper.getImplicitCollectionDefForFieldName(obj.getClass(), s1);
        class1 = determineWhichClassDefinesField(hierarchicalstreamreader);
        ReflectionProvider reflectionprovider;
        if(implicitcollectionmapping == null && reflectionProvider.fieldDefinedInClass(s1, obj.getClass()))
            flag = true;
        else
            flag = false;
        if(implicitcollectionmapping == null)
            class2 = determineType(hierarchicalstreamreader, flag, obj, s1, class1);
        else
            class2 = implicitcollectionmapping.getItemType();
        if(!flag) goto _L2; else goto _L1
_L1:
        Object obj1;
        reflectionprovider = reflectionProvider;
        Class class4;
        Field field;
        if(class1 != null)
            class4 = class1;
        else
            class4 = obj.getClass();
        field = reflectionprovider.getField(class4, s1);
        if(Modifier.isTransient(field.getModifiers()) && !shouldUnmarshalTransientFields())
        {
            hierarchicalstreamreader.moveUp();
            continue; /* Loop/switch isn't completed */
        }
        obj1 = unmarshallField(unmarshallingcontext, obj, class2, field);
        Class class5 = reflectionProvider.getFieldType(obj, s1, class1);
        if(!class5.isPrimitive())
            class2 = class5;
_L4:
        if(obj1 != null)
        {
            Class class3 = obj1.getClass();
            if(!class2.isAssignableFrom(class3))
                throw new ConversionException("Cannot convert type " + obj1.getClass().getName() + " to type " + class2.getName());
        }
        break; /* Loop/switch isn't completed */
_L2:
        if(class2 != null)
            obj1 = unmarshallingcontext.convertAnother(obj, class2);
        else
            obj1 = null;
        if(true) goto _L4; else goto _L3
_L3:
        if(flag)
        {
            reflectionProvider.writeField(obj, s1, obj1, class1);
            seenfields.add(class1, s1);
        } else
        if(class2 != null)
            map = writeValueToImplicitCollection(unmarshallingcontext, obj1, map, obj, s);
        hierarchicalstreamreader.moveUp();
        if(true) goto _L6; else goto _L5
_L5:
        return obj;
    }

    protected Object instantiateNewInstance(HierarchicalStreamReader hierarchicalstreamreader, UnmarshallingContext unmarshallingcontext)
    {
        String s = mapper.aliasForSystemAttribute("resolves-to");
        String s1;
        Object obj;
        Object obj1;
        if(s == null)
            s1 = null;
        else
            s1 = hierarchicalstreamreader.getAttribute(s);
        obj = unmarshallingcontext.currentObject();
        if(obj != null)
            obj1 = obj;
        else
        if(s1 != null)
            obj1 = reflectionProvider.newInstance(mapper.realClass(s1));
        else
            obj1 = reflectionProvider.newInstance(unmarshallingcontext.getRequiredType());
        return obj1;
    }

    public void marshal(Object obj, HierarchicalStreamWriter hierarchicalstreamwriter, MarshallingContext marshallingcontext)
    {
        Object obj1 = serializationMethodInvoker.callWriteReplace(obj);
        if(obj1.getClass() != obj.getClass())
        {
            String s = mapper.aliasForSystemAttribute("resolves-to");
            if(s != null)
                hierarchicalstreamwriter.addAttribute(s, mapper.serializedClass(obj1.getClass()));
            marshallingcontext.convertAnother(obj1);
        } else
        {
            doMarshal(obj1, hierarchicalstreamwriter, marshallingcontext);
        }
    }

    protected void marshallField(MarshallingContext marshallingcontext, Object obj, Field field)
    {
        marshallingcontext.convertAnother(obj, mapper.getLocalConverter(field.getDeclaringClass(), field.getName()));
    }

    protected boolean shouldUnmarshalTransientFields()
    {
        return false;
    }

    public Object unmarshal(HierarchicalStreamReader hierarchicalstreamreader, UnmarshallingContext unmarshallingcontext)
    {
        Object obj = doUnmarshal(instantiateNewInstance(hierarchicalstreamreader, unmarshallingcontext), hierarchicalstreamreader, unmarshallingcontext);
        return serializationMethodInvoker.callReadResolve(obj);
    }

    protected Object unmarshallField(UnmarshallingContext unmarshallingcontext, Object obj, Class class1, Field field)
    {
        return unmarshallingcontext.convertAnother(obj, class1, mapper.getLocalConverter(field.getDeclaringClass(), field.getName()));
    }

    static Class class$com$thoughtworks$xstream$mapper$Mapper$Null;
    static Class class$java$util$Collection;
    protected final Mapper mapper;
    private transient ReflectionProvider pureJavaReflectionProvider;
    protected final ReflectionProvider reflectionProvider;
    protected transient SerializationMethodInvoker serializationMethodInvoker;

    private class _cls1
        implements ReflectionProvider.Visitor
    {

        public void visit(String s, Class class1, Class class2, Object obj)
        {
            if(mapper.shouldSerializeMember(class2, s)) goto _L2; else goto _L1
_L1:
            return;
_L2:
            if(!defaultFieldDefinition.containsKey(s))
            {
                Class class3 = source.getClass();
                defaultFieldDefinition.put(s, reflectionProvider.getField(class3, s));
            }
            SingleValueConverter singlevalueconverter = mapper.getConverterFromItemType(s, class1, class2);
            if(singlevalueconverter != null)
            {
                if(obj != null)
                {
                    if(seenFields.contains(s))
                        throw new ConversionException("Cannot write field with name '" + s + "' twice as attribute for object of type " + source.getClass().getName());
                    String s1 = singlevalueconverter.toString(obj);
                    if(s1 != null)
                        writer.addAttribute(mapper.aliasForAttribute(mapper.serializedMember(class2, s)), s1);
                }
                seenFields.add(s);
            }
            if(true) goto _L1; else goto _L3
_L3:
        }

        private final AbstractReflectionConverter this$0;
        private final Map val$defaultFieldDefinition;
        private final Set val$seenFields;
        private final Object val$source;
        private final HierarchicalStreamWriter val$writer;

        _cls1()
        {
            this$0 = AbstractReflectionConverter.this;
            defaultFieldDefinition = map;
            source = obj;
            seenFields = set;
            writer = hierarchicalstreamwriter;
        }
    }


    private class _cls2
        implements ReflectionProvider.Visitor
    {

        private void writeField(String s, String s1, Class class1, Class class2, Object obj)
        {
            HierarchicalStreamWriter hierarchicalstreamwriter = writer;
            String s2;
            if(s1 != null)
                s2 = s1;
            else
                s2 = mapper.serializedMember(source.getClass(), s);
            ExtendedHierarchicalStreamWriterHelper.startNode(hierarchicalstreamwriter, s2, class1);
            if(obj != null)
            {
                Class class3 = obj.getClass();
                Class class4 = mapper.defaultImplementationOf(class1);
                if(!class3.equals(class4))
                {
                    String s4 = mapper.serializedClass(class3);
                    if(!s4.equals(mapper.serializedClass(class4)))
                    {
                        String s5 = mapper.aliasForSystemAttribute("class");
                        if(s5 != null)
                            writer.addAttribute(s5, s4);
                    }
                }
                if(((Field)defaultFieldDefinition.get(s)).getDeclaringClass() != class2)
                {
                    String s3 = mapper.aliasForSystemAttribute("defined-in");
                    if(s3 != null)
                        writer.addAttribute(s3, mapper.serializedClass(class2));
                }
                Field field = reflectionProvider.getField(class2, s);
                marshallField(context, obj, field);
            }
            writer.endNode();
        }

        public void visit(String s, Class class1, Class class2, Object obj)
        {
            if(mapper.shouldSerializeMember(class2, s) && !seenFields.contains(s) && obj != null)
            {
                com.thoughtworks.xstream.mapper.Mapper.ImplicitCollectionMapping implicitcollectionmapping = mapper.getImplicitCollectionDefForFieldName(source.getClass(), s);
                if(implicitcollectionmapping != null)
                {
                    if(implicitcollectionmapping.getItemFieldName() != null)
                    {
                        Iterator iterator = ((Collection)obj).iterator();
                        while(iterator.hasNext()) 
                        {
                            Object obj1 = iterator.next();
                            String s1;
                            if(obj1 == null)
                                s1 = mapper.serializedClass(null);
                            else
                                s1 = implicitcollectionmapping.getItemFieldName();
                            writeField(s, s1, implicitcollectionmapping.getItemType(), class2, obj1);
                        }
                    } else
                    {
                        context.convertAnother(obj);
                    }
                } else
                {
                    writeField(s, null, class1, class2, obj);
                }
            }
        }

        private final AbstractReflectionConverter this$0;
        private final MarshallingContext val$context;
        private final Map val$defaultFieldDefinition;
        private final Set val$seenFields;
        private final Object val$source;
        private final HierarchicalStreamWriter val$writer;

        _cls2()
        {
            this$0 = AbstractReflectionConverter.this;
            seenFields = set;
            source = obj;
            context = marshallingcontext;
            writer = hierarchicalstreamwriter;
            defaultFieldDefinition = map;
        }
    }

}
