// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.javabean;

import com.thoughtworks.xstream.alias.ClassMapper;
import com.thoughtworks.xstream.converters.*;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.mapper.Mapper;

// Referenced classes of package com.thoughtworks.xstream.converters.javabean:
//            BeanProvider

public class JavaBeanConverter
    implements Converter
{
    public static class DuplicateFieldException extends ConversionException
    {

        public DuplicateFieldException(String s)
        {
            super(s);
        }
    }


    public JavaBeanConverter(ClassMapper classmapper, String s)
    {
        this(((Mapper) (classmapper)), s);
    }

    public JavaBeanConverter(Mapper mapper1)
    {
        this(mapper1, new BeanProvider());
    }

    public JavaBeanConverter(Mapper mapper1, BeanProvider beanprovider)
    {
        mapper = mapper1;
        beanProvider = beanprovider;
    }

    public JavaBeanConverter(Mapper mapper1, String s)
    {
        this(mapper1, new BeanProvider());
        classAttributeIdentifier = s;
    }

    private Class determineType(HierarchicalStreamReader hierarchicalstreamreader, Object obj, String s)
    {
        String s1;
        String s2;
        Class class1;
        if(classAttributeIdentifier != null)
            s1 = classAttributeIdentifier;
        else
            s1 = mapper.aliasForSystemAttribute("class");
        if(s1 == null)
            s2 = null;
        else
            s2 = hierarchicalstreamreader.getAttribute(s1);
        if(s2 != null)
            class1 = mapper.realClass(s2);
        else
            class1 = mapper.defaultImplementationOf(beanProvider.getPropertyType(obj, s));
        return class1;
    }

    private Object instantiateNewInstance(UnmarshallingContext unmarshallingcontext)
    {
        Object obj = unmarshallingcontext.currentObject();
        if(obj == null)
            obj = beanProvider.newInstance(unmarshallingcontext.getRequiredType());
        return obj;
    }

    @Override
	public boolean canConvert(Class class1)
    {
        return beanProvider.canInstantiate(class1);
    }

    @Override
	public void marshal(final Object source, final HierarchicalStreamWriter writer, final MarshallingContext context)
    {
        final String classAttributeName;
        if(classAttributeIdentifier != null)
            classAttributeName = classAttributeIdentifier;
        else
            classAttributeName = mapper.aliasForSystemAttribute("class");
        beanProvider.visitSerializableProperties(source, new _cls1());
    }

    @Override
	public Object unmarshal(HierarchicalStreamReader hierarchicalstreamreader, UnmarshallingContext unmarshallingcontext)
    {
        Object obj = instantiateNewInstance(unmarshallingcontext);
_L2:
        String s;
        if(!hierarchicalstreamreader.hasMoreChildren())
            break MISSING_BLOCK_LABEL_157;
        hierarchicalstreamreader.moveDown();
        s = mapper.realMember(obj.getClass(), hierarchicalstreamreader.getNodeName());
        if(!beanProvider.propertyDefinedInClass(s, obj.getClass()))
            break; /* Loop/switch isn't completed */
        Object obj1 = unmarshallingcontext.convertAnother(obj, determineType(hierarchicalstreamreader, obj, s));
        beanProvider.writeProperty(obj, s, obj1);
_L4:
        hierarchicalstreamreader.moveUp();
        if(true) goto _L2; else goto _L1
_L1:
        if(!mapper.shouldSerializeMember(obj.getClass(), s)) goto _L4; else goto _L3
_L3:
        throw new ConversionException("Property '" + s + "' not defined in class " + obj.getClass().getName());
        return obj;
    }

    private BeanProvider beanProvider;
    private String classAttributeIdentifier;
    private Mapper mapper;


    private class _cls1
        implements BeanProvider.Visitor
    {

        private void writeField(String s, Class class1, Object obj, Class class2)
        {
            String s1 = mapper.serializedMember(source.getClass(), s);
            ExtendedHierarchicalStreamWriterHelper.startNode(writer, s1, class1);
            Class class3 = obj.getClass();
            if(!class3.equals(mapper.defaultImplementationOf(class1)) && classAttributeName != null)
                writer.addAttribute(classAttributeName, mapper.serializedClass(class3));
            context.convertAnother(obj);
            writer.endNode();
        }

        @Override
		public boolean shouldVisit(String s, Class class1)
        {
            return mapper.shouldSerializeMember(class1, s);
        }

        @Override
		public void visit(String s, Class class1, Class class2, Object obj)
        {
            if(obj != null)
                writeField(s, class1, obj, class2);
        }

        private final JavaBeanConverter this$0;
        private final String val$classAttributeName;
        private final MarshallingContext val$context;
        private final Object val$source;
        private final HierarchicalStreamWriter val$writer;

        _cls1()
        {
            this$0 = JavaBeanConverter.this;
            source = obj;
            writer = hierarchicalstreamwriter;
            classAttributeName = s;
            context = marshallingcontext;
        }
    }

}
