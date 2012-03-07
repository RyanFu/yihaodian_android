// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.annotations;

import com.thoughtworks.xstream.converters.*;
import com.thoughtworks.xstream.converters.reflection.*;
import com.thoughtworks.xstream.mapper.Mapper;
import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.thoughtworks.xstream.annotations:
//            XStreamConverter, AnnotationProvider

public class AnnotationReflectionConverter extends ReflectionConverter
{

    public AnnotationReflectionConverter(Mapper mapper, ReflectionProvider reflectionprovider, AnnotationProvider annotationprovider)
    {
        super(mapper, reflectionprovider);
        annotationProvider = annotationprovider;
    }

    private void ensureCache(Class class1)
    {
        if(!cachedConverters.containsKey(class1))
            cachedConverters.put(class1, newInstance(class1));
    }

    private Converter newInstance(Class class1)
    {
        Object obj;
        try
        {
            if(com/thoughtworks/xstream/converters/SingleValueConverter.isAssignableFrom(class1))
                obj = new SingleValueConverterWrapper((SingleValueConverter)class1.getConstructor(new Class[0]).newInstance(new Object[0]));
            else
                obj = class1.getConstructor(new Class[0]).newInstance(new Object[0]);
        }
        catch(InvocationTargetException invocationtargetexception)
        {
            throw new ObjectAccessException((new StringBuilder()).append("Cannot construct ").append(class1.getName()).toString(), invocationtargetexception.getCause());
        }
        catch(InstantiationException instantiationexception)
        {
            throw new ObjectAccessException((new StringBuilder()).append("Cannot construct ").append(class1.getName()).toString(), instantiationexception);
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            throw new ObjectAccessException((new StringBuilder()).append("Cannot construct ").append(class1.getName()).toString(), illegalaccessexception);
        }
        catch(NoSuchMethodException nosuchmethodexception)
        {
            throw new ObjectAccessException((new StringBuilder()).append("Cannot construct ").append(class1.getName()).toString(), nosuchmethodexception);
        }
        return ((Converter) (obj));
    }

    @Override
	protected void marshallField(MarshallingContext marshallingcontext, Object obj, Field field)
    {
        XStreamConverter xstreamconverter = (XStreamConverter)annotationProvider.getAnnotation(field, com/thoughtworks/xstream/annotations/XStreamConverter);
        if(xstreamconverter != null)
        {
            Class class1 = xstreamconverter.value();
            ensureCache(class1);
            marshallingcontext.convertAnother(obj, (Converter)cachedConverters.get(class1));
        } else
        {
            marshallingcontext.convertAnother(obj);
        }
    }

    @Override
	protected Object unmarshallField(UnmarshallingContext unmarshallingcontext, Object obj, Class class1, Field field)
    {
        XStreamConverter xstreamconverter = (XStreamConverter)annotationProvider.getAnnotation(field, com/thoughtworks/xstream/annotations/XStreamConverter);
        Object obj1;
        if(xstreamconverter != null)
        {
            Class class2 = xstreamconverter.value();
            ensureCache(class2);
            obj1 = unmarshallingcontext.convertAnother(obj, class1, (Converter)cachedConverters.get(class2));
        } else
        {
            obj1 = unmarshallingcontext.convertAnother(obj, class1);
        }
        return obj1;
    }

    private final AnnotationProvider annotationProvider;
    private final Map cachedConverters = new HashMap();
}
