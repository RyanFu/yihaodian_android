// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream;

import com.thoughtworks.xstream.alias.ClassMapper;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.ConverterLookup;
import com.thoughtworks.xstream.converters.ConverterRegistry;
import com.thoughtworks.xstream.converters.DataHolder;
import com.thoughtworks.xstream.converters.SingleValueConverter;
import com.thoughtworks.xstream.converters.SingleValueConverterWrapper;
import com.thoughtworks.xstream.converters.basic.BigDecimalConverter;
import com.thoughtworks.xstream.converters.basic.BigIntegerConverter;
import com.thoughtworks.xstream.converters.basic.BooleanConverter;
import com.thoughtworks.xstream.converters.basic.ByteConverter;
import com.thoughtworks.xstream.converters.basic.CharConverter;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.thoughtworks.xstream.converters.basic.DoubleConverter;
import com.thoughtworks.xstream.converters.basic.FloatConverter;
import com.thoughtworks.xstream.converters.basic.IntConverter;
import com.thoughtworks.xstream.converters.basic.LongConverter;
import com.thoughtworks.xstream.converters.basic.NullConverter;
import com.thoughtworks.xstream.converters.basic.ShortConverter;
import com.thoughtworks.xstream.converters.basic.StringBufferConverter;
import com.thoughtworks.xstream.converters.basic.StringConverter;
import com.thoughtworks.xstream.converters.basic.URLConverter;
import com.thoughtworks.xstream.converters.collections.ArrayConverter;
import com.thoughtworks.xstream.converters.collections.BitSetConverter;
import com.thoughtworks.xstream.converters.collections.CharArrayConverter;
import com.thoughtworks.xstream.converters.collections.CollectionConverter;
import com.thoughtworks.xstream.converters.collections.MapConverter;
import com.thoughtworks.xstream.converters.collections.PropertiesConverter;
import com.thoughtworks.xstream.converters.collections.TreeMapConverter;
import com.thoughtworks.xstream.converters.collections.TreeSetConverter;
import com.thoughtworks.xstream.converters.extended.ColorConverter;
import com.thoughtworks.xstream.converters.extended.DynamicProxyConverter;
import com.thoughtworks.xstream.converters.extended.EncodedByteArrayConverter;
import com.thoughtworks.xstream.converters.extended.FileConverter;
import com.thoughtworks.xstream.converters.extended.FontConverter;
import com.thoughtworks.xstream.converters.extended.GregorianCalendarConverter;
import com.thoughtworks.xstream.converters.extended.JavaClassConverter;
import com.thoughtworks.xstream.converters.extended.JavaMethodConverter;
import com.thoughtworks.xstream.converters.extended.LocaleConverter;
import com.thoughtworks.xstream.converters.extended.LookAndFeelConverter;
import com.thoughtworks.xstream.converters.extended.SqlDateConverter;
import com.thoughtworks.xstream.converters.extended.SqlTimeConverter;
import com.thoughtworks.xstream.converters.extended.SqlTimestampConverter;
import com.thoughtworks.xstream.converters.extended.TextAttributeConverter;
import com.thoughtworks.xstream.converters.reflection.ExternalizableConverter;
import com.thoughtworks.xstream.converters.reflection.ReflectionConverter;
import com.thoughtworks.xstream.converters.reflection.ReflectionProvider;
import com.thoughtworks.xstream.converters.reflection.SelfStreamingInstanceChecker;
import com.thoughtworks.xstream.converters.reflection.SerializableConverter;
import com.thoughtworks.xstream.core.DefaultConverterLookup;
import com.thoughtworks.xstream.core.JVM;
import com.thoughtworks.xstream.core.MapBackedDataHolder;
import com.thoughtworks.xstream.core.ReferenceByIdMarshallingStrategy;
import com.thoughtworks.xstream.core.ReferenceByXPathMarshallingStrategy;
import com.thoughtworks.xstream.core.TreeMarshallingStrategy;
import com.thoughtworks.xstream.core.util.ClassLoaderReference;
import com.thoughtworks.xstream.core.util.CompositeClassLoader;
import com.thoughtworks.xstream.core.util.CustomObjectInputStream;
import com.thoughtworks.xstream.core.util.CustomObjectOutputStream;
import com.thoughtworks.xstream.io.HierarchicalStreamDriver;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.StatefulWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.thoughtworks.xstream.mapper.AnnotationConfiguration;
import com.thoughtworks.xstream.mapper.ArrayMapper;
import com.thoughtworks.xstream.mapper.AttributeAliasingMapper;
import com.thoughtworks.xstream.mapper.AttributeMapper;
import com.thoughtworks.xstream.mapper.CachingMapper;
import com.thoughtworks.xstream.mapper.ClassAliasingMapper;
import com.thoughtworks.xstream.mapper.DefaultImplementationsMapper;
import com.thoughtworks.xstream.mapper.DefaultMapper;
import com.thoughtworks.xstream.mapper.DynamicProxyMapper;
import com.thoughtworks.xstream.mapper.FieldAliasingMapper;
import com.thoughtworks.xstream.mapper.ImmutableTypesMapper;
import com.thoughtworks.xstream.mapper.ImplicitCollectionMapper;
import com.thoughtworks.xstream.mapper.LocalConversionMapper;
import com.thoughtworks.xstream.mapper.Mapper;
import com.thoughtworks.xstream.mapper.MapperWrapper;
import com.thoughtworks.xstream.mapper.OuterClassMapper;
import com.thoughtworks.xstream.mapper.PackageAliasingMapper;
import com.thoughtworks.xstream.mapper.SystemAttributeAliasingMapper;
import com.thoughtworks.xstream.mapper.XStream11XmlFriendlyMapper;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Proxy;

// Referenced classes of package com.thoughtworks.xstream:
//            MarshallingStrategy, XStreamException

public class XStream
{
    public static class InitializationException extends XStreamException
    {

        public InitializationException(String s)
        {
            super(s);
        }

        public InitializationException(String s, Throwable throwable)
        {
            super(s, throwable);
        }
    }


    public XStream()
    {
        this(null, (Mapper)null, ((HierarchicalStreamDriver) (new XppDriver())));
    }

    public XStream(ReflectionProvider reflectionprovider)
    {
        this(reflectionprovider, (Mapper)null, ((HierarchicalStreamDriver) (new XppDriver())));
    }

    public XStream(ReflectionProvider reflectionprovider, ClassMapper classmapper, HierarchicalStreamDriver hierarchicalstreamdriver)
    {
        this(reflectionprovider, ((Mapper) (classmapper)), hierarchicalstreamdriver);
    }

    public XStream(ReflectionProvider reflectionprovider, ClassMapper classmapper, HierarchicalStreamDriver hierarchicalstreamdriver, String s)
    {
        this(reflectionprovider, ((Mapper) (classmapper)), hierarchicalstreamdriver);
        aliasAttribute(s, "class");
    }

    public XStream(ReflectionProvider reflectionprovider, HierarchicalStreamDriver hierarchicalstreamdriver)
    {
        this(reflectionprovider, (Mapper)null, hierarchicalstreamdriver);
    }

    public XStream(ReflectionProvider reflectionprovider, HierarchicalStreamDriver hierarchicalstreamdriver, ClassLoader classloader)
    {
        this(reflectionprovider, hierarchicalstreamdriver, classloader, ((Mapper) (null)));
    }

    public XStream(ReflectionProvider reflectionprovider, HierarchicalStreamDriver hierarchicalstreamdriver, ClassLoader classloader, Mapper mapper1)
    {
        this(reflectionprovider, hierarchicalstreamdriver, classloader, mapper1, ((ConverterLookup) (new DefaultConverterLookup())), null);
    }

    public XStream(ReflectionProvider reflectionprovider, HierarchicalStreamDriver hierarchicalstreamdriver, ClassLoader classloader, Mapper mapper1, ConverterLookup converterlookup, ConverterRegistry converterregistry)
    {
        jvm = new JVM();
        jvm = new JVM();
        if(reflectionprovider == null)
            reflectionprovider = jvm.bestReflectionProvider();
        reflectionProvider = reflectionprovider;
        hierarchicalStreamDriver = hierarchicalstreamdriver;
        ClassLoaderReference classloaderreference;
        ConverterRegistry converterregistry1;
        Mapper mapper2;
        if(classloader instanceof ClassLoaderReference)
            classloaderreference = (ClassLoaderReference)classloader;
        else
            classloaderreference = new ClassLoaderReference(classloader);
        classLoaderReference = classloaderreference;
        converterLookup = converterlookup;
        if(converterregistry != null)
            converterregistry1 = converterregistry;
        else
        if(converterlookup instanceof ConverterRegistry)
            converterregistry1 = (ConverterRegistry)converterlookup;
        else
            converterregistry1 = null;
        converterRegistry = converterregistry1;
        if(mapper1 == null)
            mapper2 = buildMapper();
        else
            mapper2 = mapper1;
        mapper = mapper2;
        setupMappers();
        setupAliases();
        setupDefaultImplementations();
        setupConverters();
        setupImmutableTypes();
        setMode(1003);
    }

    public XStream(ReflectionProvider reflectionprovider, Mapper mapper1, HierarchicalStreamDriver hierarchicalstreamdriver)
    {
        this(reflectionprovider, hierarchicalstreamdriver, ((ClassLoader) (new ClassLoaderReference(new CompositeClassLoader()))), mapper1, ((ConverterLookup) (new DefaultConverterLookup())), null);
    }

    public XStream(HierarchicalStreamDriver hierarchicalstreamdriver)
    {
        this(null, (Mapper)null, hierarchicalstreamdriver);
    }

    private Mapper buildMapper()
    {
        Object obj = new DefaultMapper(classLoaderReference);
        if(useXStream11XmlFriendlyMapper())
            obj = new XStream11XmlFriendlyMapper(((Mapper) (obj)));
        AttributeMapper attributemapper = new AttributeMapper(new DefaultImplementationsMapper(new ArrayMapper(new OuterClassMapper(new ImplicitCollectionMapper(new SystemAttributeAliasingMapper(new AttributeAliasingMapper(new FieldAliasingMapper(new ClassAliasingMapper(new PackageAliasingMapper(new DynamicProxyMapper(((Mapper) (obj)))))))))))), converterLookup);
        Object obj1;
        if(JVM.is15())
        {
            Class aclass1[] = new Class[1];
            Object obj2;
            Class aclass[];
            Object aobj[];
            Class class6;
            Object aobj1[];
            if(class$com$thoughtworks$xstream$mapper$Mapper == null)
            {
                class6 = _mthclass$("com.thoughtworks.xstream.mapper.Mapper");
                class$com$thoughtworks$xstream$mapper$Mapper = class6;
            } else
            {
                class6 = class$com$thoughtworks$xstream$mapper$Mapper;
            }
            aclass1[0] = class6;
            aobj1 = new Object[1];
            aobj1[0] = attributemapper;
            obj1 = buildMapperDynamically("com.thoughtworks.xstream.mapper.EnumMapper", aclass1, aobj1);
        } else
        {
            obj1 = attributemapper;
        }
        obj2 = new ImmutableTypesMapper(new LocalConversionMapper(((Mapper) (obj1))));
        if(JVM.is15())
        {
            aclass = new Class[5];
            Class class1;
            Class class2;
            Class class3;
            Class class4;
            Class class5;
            if(class$com$thoughtworks$xstream$mapper$Mapper == null)
            {
                class1 = _mthclass$("com.thoughtworks.xstream.mapper.Mapper");
                class$com$thoughtworks$xstream$mapper$Mapper = class1;
            } else
            {
                class1 = class$com$thoughtworks$xstream$mapper$Mapper;
            }
            aclass[0] = class1;
            if(class$com$thoughtworks$xstream$converters$ConverterRegistry == null)
            {
                class2 = _mthclass$("com.thoughtworks.xstream.converters.ConverterRegistry");
                class$com$thoughtworks$xstream$converters$ConverterRegistry = class2;
            } else
            {
                class2 = class$com$thoughtworks$xstream$converters$ConverterRegistry;
            }
            aclass[1] = class2;
            if(class$java$lang$ClassLoader == null)
            {
                class3 = _mthclass$("java.lang.ClassLoader");
                class$java$lang$ClassLoader = class3;
            } else
            {
                class3 = class$java$lang$ClassLoader;
            }
            aclass[2] = class3;
            if(class$com$thoughtworks$xstream$converters$reflection$ReflectionProvider == null)
            {
                class4 = _mthclass$("com.thoughtworks.xstream.converters.reflection.ReflectionProvider");
                class$com$thoughtworks$xstream$converters$reflection$ReflectionProvider = class4;
            } else
            {
                class4 = class$com$thoughtworks$xstream$converters$reflection$ReflectionProvider;
            }
            aclass[3] = class4;
            if(class$com$thoughtworks$xstream$core$JVM == null)
            {
                class5 = _mthclass$("com.thoughtworks.xstream.core.JVM");
                class$com$thoughtworks$xstream$core$JVM = class5;
            } else
            {
                class5 = class$com$thoughtworks$xstream$core$JVM;
            }
            aclass[4] = class5;
            aobj = new Object[5];
            aobj[0] = obj2;
            aobj[1] = converterLookup;
            aobj[2] = classLoaderReference;
            aobj[3] = reflectionProvider;
            aobj[4] = jvm;
            obj2 = buildMapperDynamically("com.thoughtworks.xstream.mapper.AnnotationMapper", aclass, aobj);
        }
        return new CachingMapper(wrapMapper((MapperWrapper)obj2));
    }

    private Mapper buildMapperDynamically(String s, Class aclass[], Object aobj[])
    {
        Mapper mapper1;
        try
        {
            mapper1 = (Mapper)Class.forName(s, false, classLoaderReference.getReference()).getConstructor(aclass).newInstance(aobj);
        }
        catch(Exception exception)
        {
            throw new InitializationException("Could not instantiate mapper : " + s, exception);
        }
        return mapper1;
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

    private void dynamicallyRegisterConverter(String s, int i, Class aclass[], Object aobj[])
    {
        try
        {
            Object obj = Class.forName(s, false, classLoaderReference.getReference()).getConstructor(aclass).newInstance(aobj);
            if(obj instanceof Converter)
                registerConverter((Converter)obj, i);
            else
            if(obj instanceof SingleValueConverter)
                registerConverter((SingleValueConverter)obj, i);
        }
        catch(Exception exception)
        {
            throw new InitializationException("Could not instantiate converter : " + s, exception);
        }
    }

    private Object readResolve()
    {
        jvm = new JVM();
        return this;
    }

    private void setupMappers()
    {
        Mapper mapper1 = mapper;
        Class class1;
        Mapper mapper2;
        Class class2;
        Mapper mapper3;
        Class class3;
        Mapper mapper4;
        Class class4;
        Mapper mapper5;
        Class class5;
        Mapper mapper6;
        Class class6;
        Mapper mapper7;
        Class class7;
        Mapper mapper8;
        Class class8;
        Mapper mapper9;
        Class class9;
        Mapper mapper10;
        Class class10;
        Mapper mapper11;
        Class class11;
        if(class$com$thoughtworks$xstream$mapper$PackageAliasingMapper == null)
        {
            class1 = _mthclass$("com.thoughtworks.xstream.mapper.PackageAliasingMapper");
            class$com$thoughtworks$xstream$mapper$PackageAliasingMapper = class1;
        } else
        {
            class1 = class$com$thoughtworks$xstream$mapper$PackageAliasingMapper;
        }
        packageAliasingMapper = (PackageAliasingMapper)mapper1.lookupMapperOfType(class1);
        mapper2 = mapper;
        if(class$com$thoughtworks$xstream$mapper$ClassAliasingMapper == null)
        {
            class2 = _mthclass$("com.thoughtworks.xstream.mapper.ClassAliasingMapper");
            class$com$thoughtworks$xstream$mapper$ClassAliasingMapper = class2;
        } else
        {
            class2 = class$com$thoughtworks$xstream$mapper$ClassAliasingMapper;
        }
        classAliasingMapper = (ClassAliasingMapper)mapper2.lookupMapperOfType(class2);
        mapper3 = mapper;
        if(class$com$thoughtworks$xstream$mapper$FieldAliasingMapper == null)
        {
            class3 = _mthclass$("com.thoughtworks.xstream.mapper.FieldAliasingMapper");
            class$com$thoughtworks$xstream$mapper$FieldAliasingMapper = class3;
        } else
        {
            class3 = class$com$thoughtworks$xstream$mapper$FieldAliasingMapper;
        }
        fieldAliasingMapper = (FieldAliasingMapper)mapper3.lookupMapperOfType(class3);
        mapper4 = mapper;
        if(class$com$thoughtworks$xstream$mapper$AttributeMapper == null)
        {
            class4 = _mthclass$("com.thoughtworks.xstream.mapper.AttributeMapper");
            class$com$thoughtworks$xstream$mapper$AttributeMapper = class4;
        } else
        {
            class4 = class$com$thoughtworks$xstream$mapper$AttributeMapper;
        }
        attributeMapper = (AttributeMapper)mapper4.lookupMapperOfType(class4);
        mapper5 = mapper;
        if(class$com$thoughtworks$xstream$mapper$AttributeAliasingMapper == null)
        {
            class5 = _mthclass$("com.thoughtworks.xstream.mapper.AttributeAliasingMapper");
            class$com$thoughtworks$xstream$mapper$AttributeAliasingMapper = class5;
        } else
        {
            class5 = class$com$thoughtworks$xstream$mapper$AttributeAliasingMapper;
        }
        attributeAliasingMapper = (AttributeAliasingMapper)mapper5.lookupMapperOfType(class5);
        mapper6 = mapper;
        if(class$com$thoughtworks$xstream$mapper$SystemAttributeAliasingMapper == null)
        {
            class6 = _mthclass$("com.thoughtworks.xstream.mapper.SystemAttributeAliasingMapper");
            class$com$thoughtworks$xstream$mapper$SystemAttributeAliasingMapper = class6;
        } else
        {
            class6 = class$com$thoughtworks$xstream$mapper$SystemAttributeAliasingMapper;
        }
        systemAttributeAliasingMapper = (SystemAttributeAliasingMapper)mapper6.lookupMapperOfType(class6);
        mapper7 = mapper;
        if(class$com$thoughtworks$xstream$mapper$ImplicitCollectionMapper == null)
        {
            class7 = _mthclass$("com.thoughtworks.xstream.mapper.ImplicitCollectionMapper");
            class$com$thoughtworks$xstream$mapper$ImplicitCollectionMapper = class7;
        } else
        {
            class7 = class$com$thoughtworks$xstream$mapper$ImplicitCollectionMapper;
        }
        implicitCollectionMapper = (ImplicitCollectionMapper)mapper7.lookupMapperOfType(class7);
        mapper8 = mapper;
        if(class$com$thoughtworks$xstream$mapper$DefaultImplementationsMapper == null)
        {
            class8 = _mthclass$("com.thoughtworks.xstream.mapper.DefaultImplementationsMapper");
            class$com$thoughtworks$xstream$mapper$DefaultImplementationsMapper = class8;
        } else
        {
            class8 = class$com$thoughtworks$xstream$mapper$DefaultImplementationsMapper;
        }
        defaultImplementationsMapper = (DefaultImplementationsMapper)mapper8.lookupMapperOfType(class8);
        mapper9 = mapper;
        if(class$com$thoughtworks$xstream$mapper$ImmutableTypesMapper == null)
        {
            class9 = _mthclass$("com.thoughtworks.xstream.mapper.ImmutableTypesMapper");
            class$com$thoughtworks$xstream$mapper$ImmutableTypesMapper = class9;
        } else
        {
            class9 = class$com$thoughtworks$xstream$mapper$ImmutableTypesMapper;
        }
        immutableTypesMapper = (ImmutableTypesMapper)mapper9.lookupMapperOfType(class9);
        mapper10 = mapper;
        if(class$com$thoughtworks$xstream$mapper$LocalConversionMapper == null)
        {
            class10 = _mthclass$("com.thoughtworks.xstream.mapper.LocalConversionMapper");
            class$com$thoughtworks$xstream$mapper$LocalConversionMapper = class10;
        } else
        {
            class10 = class$com$thoughtworks$xstream$mapper$LocalConversionMapper;
        }
        localConversionMapper = (LocalConversionMapper)mapper10.lookupMapperOfType(class10);
        mapper11 = mapper;
        if(class$com$thoughtworks$xstream$mapper$AnnotationConfiguration == null)
        {
            class11 = _mthclass$("com.thoughtworks.xstream.mapper.AnnotationConfiguration");
            class$com$thoughtworks$xstream$mapper$AnnotationConfiguration = class11;
        } else
        {
            class11 = class$com$thoughtworks$xstream$mapper$AnnotationConfiguration;
        }
        annotationConfiguration = (AnnotationConfiguration)mapper11.lookupMapperOfType(class11);
    }

    public void addDefaultImplementation(Class class1, Class class2)
    {
        if(defaultImplementationsMapper == null)
        {
            StringBuffer stringbuffer = (new StringBuffer()).append("No ");
            Class class3;
            if(class$com$thoughtworks$xstream$mapper$DefaultImplementationsMapper == null)
            {
                class3 = _mthclass$("com.thoughtworks.xstream.mapper.DefaultImplementationsMapper");
                class$com$thoughtworks$xstream$mapper$DefaultImplementationsMapper = class3;
            } else
            {
                class3 = class$com$thoughtworks$xstream$mapper$DefaultImplementationsMapper;
            }
            throw new InitializationException(stringbuffer.append(class3.getName()).append(" available").toString());
        } else
        {
            defaultImplementationsMapper.addDefaultImplementation(class1, class2);
            return;
        }
    }

    public void addImmutableType(Class class1)
    {
        if(immutableTypesMapper == null)
        {
            StringBuffer stringbuffer = (new StringBuffer()).append("No ");
            Class class2;
            if(class$com$thoughtworks$xstream$mapper$ImmutableTypesMapper == null)
            {
                class2 = _mthclass$("com.thoughtworks.xstream.mapper.ImmutableTypesMapper");
                class$com$thoughtworks$xstream$mapper$ImmutableTypesMapper = class2;
            } else
            {
                class2 = class$com$thoughtworks$xstream$mapper$ImmutableTypesMapper;
            }
            throw new InitializationException(stringbuffer.append(class2.getName()).append(" available").toString());
        } else
        {
            immutableTypesMapper.addImmutableType(class1);
            return;
        }
    }

    public void addImplicitCollection(Class class1, String s)
    {
        if(implicitCollectionMapper == null)
        {
            StringBuffer stringbuffer = (new StringBuffer()).append("No ");
            Class class2;
            if(class$com$thoughtworks$xstream$mapper$ImplicitCollectionMapper == null)
            {
                class2 = _mthclass$("com.thoughtworks.xstream.mapper.ImplicitCollectionMapper");
                class$com$thoughtworks$xstream$mapper$ImplicitCollectionMapper = class2;
            } else
            {
                class2 = class$com$thoughtworks$xstream$mapper$ImplicitCollectionMapper;
            }
            throw new InitializationException(stringbuffer.append(class2.getName()).append(" available").toString());
        } else
        {
            implicitCollectionMapper.add(class1, s, null, null);
            return;
        }
    }

    public void addImplicitCollection(Class class1, String s, Class class2)
    {
        if(implicitCollectionMapper == null)
        {
            StringBuffer stringbuffer = (new StringBuffer()).append("No ");
            Class class3;
            if(class$com$thoughtworks$xstream$mapper$ImplicitCollectionMapper == null)
            {
                class3 = _mthclass$("com.thoughtworks.xstream.mapper.ImplicitCollectionMapper");
                class$com$thoughtworks$xstream$mapper$ImplicitCollectionMapper = class3;
            } else
            {
                class3 = class$com$thoughtworks$xstream$mapper$ImplicitCollectionMapper;
            }
            throw new InitializationException(stringbuffer.append(class3.getName()).append(" available").toString());
        } else
        {
            implicitCollectionMapper.add(class1, s, null, class2);
            return;
        }
    }

    public void addImplicitCollection(Class class1, String s, String s1, Class class2)
    {
        if(implicitCollectionMapper == null)
        {
            StringBuffer stringbuffer = (new StringBuffer()).append("No ");
            Class class3;
            if(class$com$thoughtworks$xstream$mapper$ImplicitCollectionMapper == null)
            {
                class3 = _mthclass$("com.thoughtworks.xstream.mapper.ImplicitCollectionMapper");
                class$com$thoughtworks$xstream$mapper$ImplicitCollectionMapper = class3;
            } else
            {
                class3 = class$com$thoughtworks$xstream$mapper$ImplicitCollectionMapper;
            }
            throw new InitializationException(stringbuffer.append(class3.getName()).append(" available").toString());
        } else
        {
            implicitCollectionMapper.add(class1, s, s1, class2);
            return;
        }
    }

    public void alias(String s, Class class1)
    {
        if(classAliasingMapper == null)
        {
            StringBuffer stringbuffer = (new StringBuffer()).append("No ");
            Class class2;
            if(class$com$thoughtworks$xstream$mapper$ClassAliasingMapper == null)
            {
                class2 = _mthclass$("com.thoughtworks.xstream.mapper.ClassAliasingMapper");
                class$com$thoughtworks$xstream$mapper$ClassAliasingMapper = class2;
            } else
            {
                class2 = class$com$thoughtworks$xstream$mapper$ClassAliasingMapper;
            }
            throw new InitializationException(stringbuffer.append(class2.getName()).append(" available").toString());
        } else
        {
            classAliasingMapper.addClassAlias(s, class1);
            return;
        }
    }

    public void alias(String s, Class class1, Class class2)
    {
        alias(s, class1);
        addDefaultImplementation(class2, class1);
    }

    public void aliasAttribute(Class class1, String s, String s1)
    {
        aliasField(s1, class1, s);
        useAttributeFor(class1, s);
    }

    public void aliasAttribute(String s, String s1)
    {
        if(attributeAliasingMapper == null)
        {
            StringBuffer stringbuffer = (new StringBuffer()).append("No ");
            Class class1;
            if(class$com$thoughtworks$xstream$mapper$AttributeAliasingMapper == null)
            {
                class1 = _mthclass$("com.thoughtworks.xstream.mapper.AttributeAliasingMapper");
                class$com$thoughtworks$xstream$mapper$AttributeAliasingMapper = class1;
            } else
            {
                class1 = class$com$thoughtworks$xstream$mapper$AttributeAliasingMapper;
            }
            throw new InitializationException(stringbuffer.append(class1.getName()).append(" available").toString());
        } else
        {
            attributeAliasingMapper.addAliasFor(s1, s);
            return;
        }
    }

    public void aliasField(String s, Class class1, String s1)
    {
        if(fieldAliasingMapper == null)
        {
            StringBuffer stringbuffer = (new StringBuffer()).append("No ");
            Class class2;
            if(class$com$thoughtworks$xstream$mapper$FieldAliasingMapper == null)
            {
                class2 = _mthclass$("com.thoughtworks.xstream.mapper.FieldAliasingMapper");
                class$com$thoughtworks$xstream$mapper$FieldAliasingMapper = class2;
            } else
            {
                class2 = class$com$thoughtworks$xstream$mapper$FieldAliasingMapper;
            }
            throw new InitializationException(stringbuffer.append(class2.getName()).append(" available").toString());
        } else
        {
            fieldAliasingMapper.addFieldAlias(s, class1, s1);
            return;
        }
    }

    public void aliasPackage(String s, String s1)
    {
        if(packageAliasingMapper == null)
        {
            StringBuffer stringbuffer = (new StringBuffer()).append("No ");
            Class class1;
            if(class$com$thoughtworks$xstream$mapper$PackageAliasingMapper == null)
            {
                class1 = _mthclass$("com.thoughtworks.xstream.mapper.PackageAliasingMapper");
                class$com$thoughtworks$xstream$mapper$PackageAliasingMapper = class1;
            } else
            {
                class1 = class$com$thoughtworks$xstream$mapper$PackageAliasingMapper;
            }
            throw new InitializationException(stringbuffer.append(class1.getName()).append(" available").toString());
        } else
        {
            packageAliasingMapper.addPackageAlias(s, s1);
            return;
        }
    }

    public void aliasSystemAttribute(String s, String s1)
    {
        if(systemAttributeAliasingMapper == null)
        {
            StringBuffer stringbuffer = (new StringBuffer()).append("No ");
            Class class1;
            if(class$com$thoughtworks$xstream$mapper$SystemAttributeAliasingMapper == null)
            {
                class1 = _mthclass$("com.thoughtworks.xstream.mapper.SystemAttributeAliasingMapper");
                class$com$thoughtworks$xstream$mapper$SystemAttributeAliasingMapper = class1;
            } else
            {
                class1 = class$com$thoughtworks$xstream$mapper$SystemAttributeAliasingMapper;
            }
            throw new InitializationException(stringbuffer.append(class1.getName()).append(" available").toString());
        } else
        {
            systemAttributeAliasingMapper.addAliasFor(s1, s);
            return;
        }
    }

    public void aliasType(String s, Class class1)
    {
        if(classAliasingMapper == null)
        {
            StringBuffer stringbuffer = (new StringBuffer()).append("No ");
            Class class2;
            if(class$com$thoughtworks$xstream$mapper$ClassAliasingMapper == null)
            {
                class2 = _mthclass$("com.thoughtworks.xstream.mapper.ClassAliasingMapper");
                class$com$thoughtworks$xstream$mapper$ClassAliasingMapper = class2;
            } else
            {
                class2 = class$com$thoughtworks$xstream$mapper$ClassAliasingMapper;
            }
            throw new InitializationException(stringbuffer.append(class2.getName()).append(" available").toString());
        } else
        {
            classAliasingMapper.addTypeAlias(s, class1);
            return;
        }
    }

    public void autodetectAnnotations(boolean flag)
    {
        if(annotationConfiguration != null)
            annotationConfiguration.autodetectAnnotations(flag);
    }

    public ObjectInputStream createObjectInputStream(final HierarchicalStreamReader reader)
        throws IOException
    {
        return new CustomObjectInputStream(new _cls3());
    }

    public ObjectInputStream createObjectInputStream(InputStream inputstream)
        throws IOException
    {
        return createObjectInputStream(hierarchicalStreamDriver.createReader(inputstream));
    }

    public ObjectInputStream createObjectInputStream(Reader reader)
        throws IOException
    {
        return createObjectInputStream(hierarchicalStreamDriver.createReader(reader));
    }

    public ObjectOutputStream createObjectOutputStream(HierarchicalStreamWriter hierarchicalstreamwriter)
        throws IOException
    {
        return createObjectOutputStream(hierarchicalstreamwriter, "object-stream");
    }

    public ObjectOutputStream createObjectOutputStream(HierarchicalStreamWriter hierarchicalstreamwriter, String s)
        throws IOException
    {
        final StatefulWriter statefulWriter = new StatefulWriter(hierarchicalstreamwriter);
        statefulWriter.startNode(s, null);
        return new CustomObjectOutputStream(new _cls2());
    }

    public ObjectOutputStream createObjectOutputStream(OutputStream outputstream)
        throws IOException
    {
        return createObjectOutputStream(hierarchicalStreamDriver.createWriter(outputstream), "object-stream");
    }

    public ObjectOutputStream createObjectOutputStream(OutputStream outputstream, String s)
        throws IOException
    {
        return createObjectOutputStream(hierarchicalStreamDriver.createWriter(outputstream), s);
    }

    public ObjectOutputStream createObjectOutputStream(Writer writer)
        throws IOException
    {
        return createObjectOutputStream(hierarchicalStreamDriver.createWriter(writer), "object-stream");
    }

    public ObjectOutputStream createObjectOutputStream(Writer writer, String s)
        throws IOException
    {
        return createObjectOutputStream(hierarchicalStreamDriver.createWriter(writer), s);
    }

    public Object fromXML(InputStream inputstream)
    {
        return unmarshal(hierarchicalStreamDriver.createReader(inputstream), null);
    }

    public Object fromXML(InputStream inputstream, Object obj)
    {
        return unmarshal(hierarchicalStreamDriver.createReader(inputstream), obj);
    }

    public Object fromXML(Reader reader)
    {
        return unmarshal(hierarchicalStreamDriver.createReader(reader), null);
    }

    public Object fromXML(Reader reader, Object obj)
    {
        return unmarshal(hierarchicalStreamDriver.createReader(reader), obj);
    }

    public Object fromXML(String s)
    {
        return fromXML(((Reader) (new StringReader(s))));
    }

    public Object fromXML(String s, Object obj)
    {
        return fromXML(((Reader) (new StringReader(s))), obj);
    }

    public ClassLoader getClassLoader()
    {
        return classLoaderReference.getReference();
    }

    public ClassMapper getClassMapper()
    {
        ClassMapper classmapper;
        if(mapper instanceof ClassMapper)
        {
            classmapper = (ClassMapper)mapper;
        } else
        {
            ClassLoader classloader = getClassLoader();
            Class aclass[] = new Class[1];
            Class class1;
            if(class$com$thoughtworks$xstream$alias$ClassMapper == null)
            {
                class1 = _mthclass$("com.thoughtworks.xstream.alias.ClassMapper");
                class$com$thoughtworks$xstream$alias$ClassMapper = class1;
            } else
            {
                class1 = class$com$thoughtworks$xstream$alias$ClassMapper;
            }
            aclass[0] = class1;
            classmapper = (ClassMapper)Proxy.newProxyInstance(classloader, aclass, new _cls1());
        }
        return classmapper;
    }

    public ConverterLookup getConverterLookup()
    {
        return converterLookup;
    }

    public Mapper getMapper()
    {
        return mapper;
    }

    public ReflectionProvider getReflectionProvider()
    {
        return reflectionProvider;
    }

    public void marshal(Object obj, HierarchicalStreamWriter hierarchicalstreamwriter)
    {
        marshal(obj, hierarchicalstreamwriter, null);
    }

    public void marshal(Object obj, HierarchicalStreamWriter hierarchicalstreamwriter, DataHolder dataholder)
    {
        marshallingStrategy.marshal(hierarchicalstreamwriter, obj, converterLookup, mapper, dataholder);
    }

    public DataHolder newDataHolder()
    {
        return new MapBackedDataHolder();
    }

    public void omitField(Class class1, String s)
    {
        if(fieldAliasingMapper == null)
        {
            StringBuffer stringbuffer = (new StringBuffer()).append("No ");
            Class class2;
            if(class$com$thoughtworks$xstream$mapper$FieldAliasingMapper == null)
            {
                class2 = _mthclass$("com.thoughtworks.xstream.mapper.FieldAliasingMapper");
                class$com$thoughtworks$xstream$mapper$FieldAliasingMapper = class2;
            } else
            {
                class2 = class$com$thoughtworks$xstream$mapper$FieldAliasingMapper;
            }
            throw new InitializationException(stringbuffer.append(class2.getName()).append(" available").toString());
        } else
        {
            fieldAliasingMapper.omitField(class1, s);
            return;
        }
    }

    public void processAnnotations(Class class1)
    {
        Class aclass[] = new Class[1];
        aclass[0] = class1;
        processAnnotations(aclass);
    }

    public void processAnnotations(Class aclass[])
    {
        if(annotationConfiguration == null)
        {
            throw new InitializationException("No com.thoughtworks.xstream.mapper.AnnotationMapper available");
        } else
        {
            annotationConfiguration.processAnnotations(aclass);
            return;
        }
    }

    public void registerConverter(Converter converter)
    {
        registerConverter(converter, 0);
    }

    public void registerConverter(Converter converter, int i)
    {
        if(converterRegistry != null)
            converterRegistry.registerConverter(converter, i);
    }

    public void registerConverter(SingleValueConverter singlevalueconverter)
    {
        registerConverter(singlevalueconverter, 0);
    }

    public void registerConverter(SingleValueConverter singlevalueconverter, int i)
    {
        if(converterRegistry != null)
            converterRegistry.registerConverter(new SingleValueConverterWrapper(singlevalueconverter), i);
    }

    public void registerLocalConverter(Class class1, String s, Converter converter)
    {
        if(localConversionMapper == null)
        {
            StringBuffer stringbuffer = (new StringBuffer()).append("No ");
            Class class2;
            if(class$com$thoughtworks$xstream$mapper$LocalConversionMapper == null)
            {
                class2 = _mthclass$("com.thoughtworks.xstream.mapper.LocalConversionMapper");
                class$com$thoughtworks$xstream$mapper$LocalConversionMapper = class2;
            } else
            {
                class2 = class$com$thoughtworks$xstream$mapper$LocalConversionMapper;
            }
            throw new InitializationException(stringbuffer.append(class2.getName()).append(" available").toString());
        } else
        {
            localConversionMapper.registerLocalConverter(class1, s, converter);
            return;
        }
    }

    public void registerLocalConverter(Class class1, String s, SingleValueConverter singlevalueconverter)
    {
        registerLocalConverter(class1, s, ((Converter) (new SingleValueConverterWrapper(singlevalueconverter))));
    }

    public void setClassLoader(ClassLoader classloader)
    {
        classLoaderReference.setReference(classloader);
    }

    public void setMarshallingStrategy(MarshallingStrategy marshallingstrategy)
    {
        marshallingStrategy = marshallingstrategy;
    }

    public void setMode(int i)
    {
        i;
        JVM INSTR tableswitch 1001 1004: default 32
    //                   1001 60
    //                   1002 72
    //                   1003 86
    //                   1004 103;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        throw new IllegalArgumentException("Unknown mode : " + i);
_L2:
        setMarshallingStrategy(new TreeMarshallingStrategy());
_L7:
        return;
_L3:
        setMarshallingStrategy(new ReferenceByIdMarshallingStrategy());
        continue; /* Loop/switch isn't completed */
_L4:
        setMarshallingStrategy(new ReferenceByXPathMarshallingStrategy(ReferenceByXPathMarshallingStrategy.RELATIVE));
        continue; /* Loop/switch isn't completed */
_L5:
        setMarshallingStrategy(new ReferenceByXPathMarshallingStrategy(ReferenceByXPathMarshallingStrategy.ABSOLUTE));
        if(true) goto _L7; else goto _L6
_L6:
    }

    protected void setupAliases()
    {
        if(classAliasingMapper != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        Class class1;
        Class class2;
        Class class3;
        Class class4;
        Class class5;
        Class class6;
        Class class7;
        Class class8;
        Class class9;
        Class class10;
        Class class11;
        Class class12;
        Class class13;
        Class class14;
        Class class15;
        Class class16;
        Class class17;
        Class class18;
        Class class19;
        Class class20;
        Class class21;
        Class class22;
        Class class23;
        Class class24;
        Class class25;
        Class class26;
        Class class27;
        Class class28;
        Class class29;
        Class class30;
        Class class31;
        Class class32;
        Class class33;
        Class class34;
        if(class$com$thoughtworks$xstream$mapper$Mapper$Null == null)
        {
            class1 = _mthclass$("com.thoughtworks.xstream.mapper.Mapper$Null");
            class$com$thoughtworks$xstream$mapper$Mapper$Null = class1;
        } else
        {
            class1 = class$com$thoughtworks$xstream$mapper$Mapper$Null;
        }
        alias("null", class1);
        if(class$java$lang$Integer == null)
        {
            class2 = _mthclass$("java.lang.Integer");
            class$java$lang$Integer = class2;
        } else
        {
            class2 = class$java$lang$Integer;
        }
        alias("int", class2);
        if(class$java$lang$Float == null)
        {
            class3 = _mthclass$("java.lang.Float");
            class$java$lang$Float = class3;
        } else
        {
            class3 = class$java$lang$Float;
        }
        alias("float", class3);
        if(class$java$lang$Double == null)
        {
            class4 = _mthclass$("java.lang.Double");
            class$java$lang$Double = class4;
        } else
        {
            class4 = class$java$lang$Double;
        }
        alias("double", class4);
        if(class$java$lang$Long == null)
        {
            class5 = _mthclass$("java.lang.Long");
            class$java$lang$Long = class5;
        } else
        {
            class5 = class$java$lang$Long;
        }
        alias("long", class5);
        if(class$java$lang$Short == null)
        {
            class6 = _mthclass$("java.lang.Short");
            class$java$lang$Short = class6;
        } else
        {
            class6 = class$java$lang$Short;
        }
        alias("short", class6);
        if(class$java$lang$Character == null)
        {
            class7 = _mthclass$("java.lang.Character");
            class$java$lang$Character = class7;
        } else
        {
            class7 = class$java$lang$Character;
        }
        alias("char", class7);
        if(class$java$lang$Byte == null)
        {
            class8 = _mthclass$("java.lang.Byte");
            class$java$lang$Byte = class8;
        } else
        {
            class8 = class$java$lang$Byte;
        }
        alias("byte", class8);
        if(class$java$lang$Boolean == null)
        {
            class9 = _mthclass$("java.lang.Boolean");
            class$java$lang$Boolean = class9;
        } else
        {
            class9 = class$java$lang$Boolean;
        }
        alias("boolean", class9);
        if(class$java$lang$Number == null)
        {
            class10 = _mthclass$("java.lang.Number");
            class$java$lang$Number = class10;
        } else
        {
            class10 = class$java$lang$Number;
        }
        alias("number", class10);
        if(class$java$lang$Object == null)
        {
            class11 = _mthclass$("java.lang.Object");
            class$java$lang$Object = class11;
        } else
        {
            class11 = class$java$lang$Object;
        }
        alias("object", class11);
        if(class$java$math$BigInteger == null)
        {
            class12 = _mthclass$("java.math.BigInteger");
            class$java$math$BigInteger = class12;
        } else
        {
            class12 = class$java$math$BigInteger;
        }
        alias("big-int", class12);
        if(class$java$math$BigDecimal == null)
        {
            class13 = _mthclass$("java.math.BigDecimal");
            class$java$math$BigDecimal = class13;
        } else
        {
            class13 = class$java$math$BigDecimal;
        }
        alias("big-decimal", class13);
        if(class$java$lang$StringBuffer == null)
        {
            class14 = _mthclass$("java.lang.StringBuffer");
            class$java$lang$StringBuffer = class14;
        } else
        {
            class14 = class$java$lang$StringBuffer;
        }
        alias("string-buffer", class14);
        if(class$java$lang$String == null)
        {
            class15 = _mthclass$("java.lang.String");
            class$java$lang$String = class15;
        } else
        {
            class15 = class$java$lang$String;
        }
        alias("string", class15);
        if(class$java$lang$Class == null)
        {
            class16 = _mthclass$("java.lang.Class");
            class$java$lang$Class = class16;
        } else
        {
            class16 = class$java$lang$Class;
        }
        alias("java-class", class16);
        if(class$java$lang$reflect$Method == null)
        {
            class17 = _mthclass$("java.lang.reflect.Method");
            class$java$lang$reflect$Method = class17;
        } else
        {
            class17 = class$java$lang$reflect$Method;
        }
        alias("method", class17);
        if(class$java$lang$reflect$Constructor == null)
        {
            class18 = _mthclass$("java.lang.reflect.Constructor");
            class$java$lang$reflect$Constructor = class18;
        } else
        {
            class18 = class$java$lang$reflect$Constructor;
        }
        alias("constructor", class18);
        if(class$java$util$Date == null)
        {
            class19 = _mthclass$("java.util.Date");
            class$java$util$Date = class19;
        } else
        {
            class19 = class$java$util$Date;
        }
        alias("date", class19);
        if(class$java$net$URL == null)
        {
            class20 = _mthclass$("java.net.URL");
            class$java$net$URL = class20;
        } else
        {
            class20 = class$java$net$URL;
        }
        alias("url", class20);
        if(class$java$util$BitSet == null)
        {
            class21 = _mthclass$("java.util.BitSet");
            class$java$util$BitSet = class21;
        } else
        {
            class21 = class$java$util$BitSet;
        }
        alias("bit-set", class21);
        if(class$java$util$Map == null)
        {
            class22 = _mthclass$("java.util.Map");
            class$java$util$Map = class22;
        } else
        {
            class22 = class$java$util$Map;
        }
        alias("map", class22);
        if(class$java$util$Map$Entry == null)
        {
            class23 = _mthclass$("java.util.Map$Entry");
            class$java$util$Map$Entry = class23;
        } else
        {
            class23 = class$java$util$Map$Entry;
        }
        alias("entry", class23);
        if(class$java$util$Properties == null)
        {
            class24 = _mthclass$("java.util.Properties");
            class$java$util$Properties = class24;
        } else
        {
            class24 = class$java$util$Properties;
        }
        alias("properties", class24);
        if(class$java$util$List == null)
        {
            class25 = _mthclass$("java.util.List");
            class$java$util$List = class25;
        } else
        {
            class25 = class$java$util$List;
        }
        alias("list", class25);
        if(class$java$util$Set == null)
        {
            class26 = _mthclass$("java.util.Set");
            class$java$util$Set = class26;
        } else
        {
            class26 = class$java$util$Set;
        }
        alias("set", class26);
        if(class$java$util$LinkedList == null)
        {
            class27 = _mthclass$("java.util.LinkedList");
            class$java$util$LinkedList = class27;
        } else
        {
            class27 = class$java$util$LinkedList;
        }
        alias("linked-list", class27);
        if(class$java$util$Vector == null)
        {
            class28 = _mthclass$("java.util.Vector");
            class$java$util$Vector = class28;
        } else
        {
            class28 = class$java$util$Vector;
        }
        alias("vector", class28);
        if(class$java$util$TreeMap == null)
        {
            class29 = _mthclass$("java.util.TreeMap");
            class$java$util$TreeMap = class29;
        } else
        {
            class29 = class$java$util$TreeMap;
        }
        alias("tree-map", class29);
        if(class$java$util$TreeSet == null)
        {
            class30 = _mthclass$("java.util.TreeSet");
            class$java$util$TreeSet = class30;
        } else
        {
            class30 = class$java$util$TreeSet;
        }
        alias("tree-set", class30);
        if(class$java$util$Hashtable == null)
        {
            class31 = _mthclass$("java.util.Hashtable");
            class$java$util$Hashtable = class31;
        } else
        {
            class31 = class$java$util$Hashtable;
        }
        alias("hashtable", class31);
        if(jvm.supportsAWT())
        {
            alias("awt-color", jvm.loadClass("java.awt.Color"));
            alias("awt-font", jvm.loadClass("java.awt.Font"));
            alias("awt-text-attribute", jvm.loadClass("java.awt.font.TextAttribute"));
        }
        if(jvm.supportsSQL())
        {
            alias("sql-timestamp", jvm.loadClass("java.sql.Timestamp"));
            alias("sql-time", jvm.loadClass("java.sql.Time"));
            alias("sql-date", jvm.loadClass("java.sql.Date"));
        }
        if(class$java$io$File == null)
        {
            class32 = _mthclass$("java.io.File");
            class$java$io$File = class32;
        } else
        {
            class32 = class$java$io$File;
        }
        alias("file", class32);
        if(class$java$util$Locale == null)
        {
            class33 = _mthclass$("java.util.Locale");
            class$java$util$Locale = class33;
        } else
        {
            class33 = class$java$util$Locale;
        }
        alias("locale", class33);
        if(class$java$util$Calendar == null)
        {
            class34 = _mthclass$("java.util.Calendar");
            class$java$util$Calendar = class34;
        } else
        {
            class34 = class$java$util$Calendar;
        }
        alias("gregorian-calendar", class34);
        if(JVM.is14())
        {
            alias("auth-subject", jvm.loadClass("javax.security.auth.Subject"));
            alias("linked-hash-map", jvm.loadClass("java.util.LinkedHashMap"));
            alias("linked-hash-set", jvm.loadClass("java.util.LinkedHashSet"));
            alias("trace", jvm.loadClass("java.lang.StackTraceElement"));
            alias("currency", jvm.loadClass("java.util.Currency"));
            aliasType("charset", jvm.loadClass("java.nio.charset.Charset"));
        }
        if(JVM.is15())
        {
            alias("duration", jvm.loadClass("javax.xml.datatype.Duration"));
            alias("enum-set", jvm.loadClass("java.util.EnumSet"));
            alias("enum-map", jvm.loadClass("java.util.EnumMap"));
            alias("string-builder", jvm.loadClass("java.lang.StringBuilder"));
            alias("uuid", jvm.loadClass("java.util.UUID"));
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    protected void setupConverters()
    {
        ReflectionConverter reflectionconverter = new ReflectionConverter(mapper, reflectionProvider);
        registerConverter(reflectionconverter, -20);
        registerConverter(new SerializableConverter(mapper, reflectionProvider), -10);
        registerConverter(new ExternalizableConverter(mapper), -10);
        registerConverter(new NullConverter(), 10000);
        registerConverter(new IntConverter(), 0);
        registerConverter(new FloatConverter(), 0);
        registerConverter(new DoubleConverter(), 0);
        registerConverter(new LongConverter(), 0);
        registerConverter(new ShortConverter(), 0);
        registerConverter(new CharConverter(), 0);
        registerConverter(new BooleanConverter(), 0);
        registerConverter(new ByteConverter(), 0);
        registerConverter(new StringConverter(), 0);
        registerConverter(new StringBufferConverter(), 0);
        registerConverter(new DateConverter(), 0);
        registerConverter(new BitSetConverter(), 0);
        registerConverter(new URLConverter(), 0);
        registerConverter(new BigIntegerConverter(), 0);
        registerConverter(new BigDecimalConverter(), 0);
        registerConverter(new ArrayConverter(mapper), 0);
        registerConverter(new CharArrayConverter(), 0);
        registerConverter(new CollectionConverter(mapper), 0);
        registerConverter(new MapConverter(mapper), 0);
        registerConverter(new TreeMapConverter(mapper), 0);
        registerConverter(new TreeSetConverter(mapper), 0);
        registerConverter(new PropertiesConverter(), 0);
        registerConverter(new EncodedByteArrayConverter(), 0);
        registerConverter(new FileConverter(), 0);
        if(jvm.supportsSQL())
        {
            registerConverter(new SqlTimestampConverter(), 0);
            registerConverter(new SqlTimeConverter(), 0);
            registerConverter(new SqlDateConverter(), 0);
        }
        registerConverter(new DynamicProxyConverter(mapper, classLoaderReference), 0);
        registerConverter(new JavaClassConverter(classLoaderReference), 0);
        registerConverter(new JavaMethodConverter(classLoaderReference), 0);
        if(jvm.supportsAWT())
        {
            registerConverter(new FontConverter(), 0);
            registerConverter(new ColorConverter(), 0);
            registerConverter(new TextAttributeConverter(), 0);
        }
        if(jvm.supportsSwing())
            registerConverter(new LookAndFeelConverter(mapper, reflectionProvider), 0);
        registerConverter(new LocaleConverter(), 0);
        registerConverter(new GregorianCalendarConverter(), 0);
        if(JVM.is14())
        {
            Class aclass2[] = new Class[1];
            Class aclass[];
            Object aobj[];
            Class aclass1[];
            Object aobj1[];
            Class class3;
            Object aobj2[];
            Class aclass3[];
            Class class4;
            Object aobj3[];
            Class aclass4[];
            Class class5;
            Object aobj4[];
            if(class$com$thoughtworks$xstream$mapper$Mapper == null)
            {
                class3 = _mthclass$("com.thoughtworks.xstream.mapper.Mapper");
                class$com$thoughtworks$xstream$mapper$Mapper = class3;
            } else
            {
                class3 = class$com$thoughtworks$xstream$mapper$Mapper;
            }
            aclass2[0] = class3;
            aobj2 = new Object[1];
            aobj2[0] = mapper;
            dynamicallyRegisterConverter("com.thoughtworks.xstream.converters.extended.SubjectConverter", 0, aclass2, aobj2);
            aclass3 = new Class[1];
            if(class$com$thoughtworks$xstream$converters$Converter == null)
            {
                class4 = _mthclass$("com.thoughtworks.xstream.converters.Converter");
                class$com$thoughtworks$xstream$converters$Converter = class4;
            } else
            {
                class4 = class$com$thoughtworks$xstream$converters$Converter;
            }
            aclass3[0] = class4;
            aobj3 = new Object[1];
            aobj3[0] = reflectionconverter;
            dynamicallyRegisterConverter("com.thoughtworks.xstream.converters.extended.ThrowableConverter", 0, aclass3, aobj3);
            dynamicallyRegisterConverter("com.thoughtworks.xstream.converters.extended.StackTraceElementConverter", 0, null, null);
            dynamicallyRegisterConverter("com.thoughtworks.xstream.converters.extended.CurrencyConverter", 0, null, null);
            aclass4 = new Class[1];
            if(class$com$thoughtworks$xstream$converters$Converter == null)
            {
                class5 = _mthclass$("com.thoughtworks.xstream.converters.Converter");
                class$com$thoughtworks$xstream$converters$Converter = class5;
            } else
            {
                class5 = class$com$thoughtworks$xstream$converters$Converter;
            }
            aclass4[0] = class5;
            aobj4 = new Object[1];
            aobj4[0] = reflectionconverter;
            dynamicallyRegisterConverter("com.thoughtworks.xstream.converters.extended.RegexPatternConverter", 0, aclass4, aobj4);
            dynamicallyRegisterConverter("com.thoughtworks.xstream.converters.extended.CharsetConverter", 0, null, null);
        }
        if(JVM.is15())
        {
            dynamicallyRegisterConverter("com.thoughtworks.xstream.converters.extended.DurationConverter", 0, null, null);
            dynamicallyRegisterConverter("com.thoughtworks.xstream.converters.enums.EnumConverter", 0, null, null);
            aclass = new Class[1];
            Class class1;
            Class class2;
            if(class$com$thoughtworks$xstream$mapper$Mapper == null)
            {
                class1 = _mthclass$("com.thoughtworks.xstream.mapper.Mapper");
                class$com$thoughtworks$xstream$mapper$Mapper = class1;
            } else
            {
                class1 = class$com$thoughtworks$xstream$mapper$Mapper;
            }
            aclass[0] = class1;
            aobj = new Object[1];
            aobj[0] = mapper;
            dynamicallyRegisterConverter("com.thoughtworks.xstream.converters.enums.EnumSetConverter", 0, aclass, aobj);
            aclass1 = new Class[1];
            if(class$com$thoughtworks$xstream$mapper$Mapper == null)
            {
                class2 = _mthclass$("com.thoughtworks.xstream.mapper.Mapper");
                class$com$thoughtworks$xstream$mapper$Mapper = class2;
            } else
            {
                class2 = class$com$thoughtworks$xstream$mapper$Mapper;
            }
            aclass1[0] = class2;
            aobj1 = new Object[1];
            aobj1[0] = mapper;
            dynamicallyRegisterConverter("com.thoughtworks.xstream.converters.enums.EnumMapConverter", 0, aclass1, aobj1);
            dynamicallyRegisterConverter("com.thoughtworks.xstream.converters.basic.StringBuilderConverter", 0, null, null);
            dynamicallyRegisterConverter("com.thoughtworks.xstream.converters.basic.UUIDConverter", 0, null, null);
        }
        registerConverter(new SelfStreamingInstanceChecker(reflectionconverter, this), 0);
    }

    protected void setupDefaultImplementations()
    {
        if(defaultImplementationsMapper != null)
        {
            Class class1;
            Class class2;
            Class class3;
            Class class4;
            Class class5;
            Class class6;
            Class class7;
            Class class8;
            if(class$java$util$HashMap == null)
            {
                class1 = _mthclass$("java.util.HashMap");
                class$java$util$HashMap = class1;
            } else
            {
                class1 = class$java$util$HashMap;
            }
            if(class$java$util$Map == null)
            {
                class2 = _mthclass$("java.util.Map");
                class$java$util$Map = class2;
            } else
            {
                class2 = class$java$util$Map;
            }
            addDefaultImplementation(class1, class2);
            if(class$java$util$ArrayList == null)
            {
                class3 = _mthclass$("java.util.ArrayList");
                class$java$util$ArrayList = class3;
            } else
            {
                class3 = class$java$util$ArrayList;
            }
            if(class$java$util$List == null)
            {
                class4 = _mthclass$("java.util.List");
                class$java$util$List = class4;
            } else
            {
                class4 = class$java$util$List;
            }
            addDefaultImplementation(class3, class4);
            if(class$java$util$HashSet == null)
            {
                class5 = _mthclass$("java.util.HashSet");
                class$java$util$HashSet = class5;
            } else
            {
                class5 = class$java$util$HashSet;
            }
            if(class$java$util$Set == null)
            {
                class6 = _mthclass$("java.util.Set");
                class$java$util$Set = class6;
            } else
            {
                class6 = class$java$util$Set;
            }
            addDefaultImplementation(class5, class6);
            if(class$java$util$GregorianCalendar == null)
            {
                class7 = _mthclass$("java.util.GregorianCalendar");
                class$java$util$GregorianCalendar = class7;
            } else
            {
                class7 = class$java$util$GregorianCalendar;
            }
            if(class$java$util$Calendar == null)
            {
                class8 = _mthclass$("java.util.Calendar");
                class$java$util$Calendar = class8;
            } else
            {
                class8 = class$java$util$Calendar;
            }
            addDefaultImplementation(class7, class8);
        }
    }

    protected void setupImmutableTypes()
    {
        if(immutableTypesMapper != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        addImmutableType(Boolean.TYPE);
        Class class1;
        Class class2;
        Class class3;
        Class class4;
        Class class5;
        Class class6;
        Class class7;
        Class class8;
        Class class9;
        Class class10;
        Class class11;
        Class class12;
        Class class13;
        Class class14;
        Class class15;
        if(class$java$lang$Boolean == null)
        {
            class1 = _mthclass$("java.lang.Boolean");
            class$java$lang$Boolean = class1;
        } else
        {
            class1 = class$java$lang$Boolean;
        }
        addImmutableType(class1);
        addImmutableType(Byte.TYPE);
        if(class$java$lang$Byte == null)
        {
            class2 = _mthclass$("java.lang.Byte");
            class$java$lang$Byte = class2;
        } else
        {
            class2 = class$java$lang$Byte;
        }
        addImmutableType(class2);
        addImmutableType(Character.TYPE);
        if(class$java$lang$Character == null)
        {
            class3 = _mthclass$("java.lang.Character");
            class$java$lang$Character = class3;
        } else
        {
            class3 = class$java$lang$Character;
        }
        addImmutableType(class3);
        addImmutableType(Double.TYPE);
        if(class$java$lang$Double == null)
        {
            class4 = _mthclass$("java.lang.Double");
            class$java$lang$Double = class4;
        } else
        {
            class4 = class$java$lang$Double;
        }
        addImmutableType(class4);
        addImmutableType(Float.TYPE);
        if(class$java$lang$Float == null)
        {
            class5 = _mthclass$("java.lang.Float");
            class$java$lang$Float = class5;
        } else
        {
            class5 = class$java$lang$Float;
        }
        addImmutableType(class5);
        addImmutableType(Integer.TYPE);
        if(class$java$lang$Integer == null)
        {
            class6 = _mthclass$("java.lang.Integer");
            class$java$lang$Integer = class6;
        } else
        {
            class6 = class$java$lang$Integer;
        }
        addImmutableType(class6);
        addImmutableType(Long.TYPE);
        if(class$java$lang$Long == null)
        {
            class7 = _mthclass$("java.lang.Long");
            class$java$lang$Long = class7;
        } else
        {
            class7 = class$java$lang$Long;
        }
        addImmutableType(class7);
        addImmutableType(Short.TYPE);
        if(class$java$lang$Short == null)
        {
            class8 = _mthclass$("java.lang.Short");
            class$java$lang$Short = class8;
        } else
        {
            class8 = class$java$lang$Short;
        }
        addImmutableType(class8);
        if(class$com$thoughtworks$xstream$mapper$Mapper$Null == null)
        {
            class9 = _mthclass$("com.thoughtworks.xstream.mapper.Mapper$Null");
            class$com$thoughtworks$xstream$mapper$Mapper$Null = class9;
        } else
        {
            class9 = class$com$thoughtworks$xstream$mapper$Mapper$Null;
        }
        addImmutableType(class9);
        if(class$java$math$BigDecimal == null)
        {
            class10 = _mthclass$("java.math.BigDecimal");
            class$java$math$BigDecimal = class10;
        } else
        {
            class10 = class$java$math$BigDecimal;
        }
        addImmutableType(class10);
        if(class$java$math$BigInteger == null)
        {
            class11 = _mthclass$("java.math.BigInteger");
            class$java$math$BigInteger = class11;
        } else
        {
            class11 = class$java$math$BigInteger;
        }
        addImmutableType(class11);
        if(class$java$lang$String == null)
        {
            class12 = _mthclass$("java.lang.String");
            class$java$lang$String = class12;
        } else
        {
            class12 = class$java$lang$String;
        }
        addImmutableType(class12);
        if(class$java$net$URL == null)
        {
            class13 = _mthclass$("java.net.URL");
            class$java$net$URL = class13;
        } else
        {
            class13 = class$java$net$URL;
        }
        addImmutableType(class13);
        if(class$java$io$File == null)
        {
            class14 = _mthclass$("java.io.File");
            class$java$io$File = class14;
        } else
        {
            class14 = class$java$io$File;
        }
        addImmutableType(class14);
        if(class$java$lang$Class == null)
        {
            class15 = _mthclass$("java.lang.Class");
            class$java$lang$Class = class15;
        } else
        {
            class15 = class$java$lang$Class;
        }
        addImmutableType(class15);
        if(jvm.supportsAWT())
            addImmutableType(jvm.loadClass("java.awt.font.TextAttribute"));
        if(JVM.is14())
            addImmutableType(jvm.loadClass("com.thoughtworks.xstream.converters.extended.CharsetConverter"));
        if(true) goto _L1; else goto _L3
_L3:
    }

    public String toXML(Object obj)
    {
        StringWriter stringwriter = new StringWriter();
        toXML(obj, ((Writer) (stringwriter)));
        return stringwriter.toString();
    }

    public void toXML(Object obj, OutputStream outputstream)
    {
        HierarchicalStreamWriter hierarchicalstreamwriter = hierarchicalStreamDriver.createWriter(outputstream);
        marshal(obj, hierarchicalstreamwriter);
        hierarchicalstreamwriter.flush();
        return;
        Exception exception;
        exception;
        hierarchicalstreamwriter.flush();
        throw exception;
    }

    public void toXML(Object obj, Writer writer)
    {
        HierarchicalStreamWriter hierarchicalstreamwriter = hierarchicalStreamDriver.createWriter(writer);
        marshal(obj, hierarchicalstreamwriter);
        hierarchicalstreamwriter.flush();
        return;
        Exception exception;
        exception;
        hierarchicalstreamwriter.flush();
        throw exception;
    }

    public Object unmarshal(HierarchicalStreamReader hierarchicalstreamreader)
    {
        return unmarshal(hierarchicalstreamreader, null, null);
    }

    public Object unmarshal(HierarchicalStreamReader hierarchicalstreamreader, Object obj)
    {
        return unmarshal(hierarchicalstreamreader, obj, null);
    }

    public Object unmarshal(HierarchicalStreamReader hierarchicalstreamreader, Object obj, DataHolder dataholder)
    {
        return marshallingStrategy.unmarshal(obj, hierarchicalstreamreader, dataholder, converterLookup, mapper);
    }

    public void useAttributeFor(Class class1)
    {
        if(attributeMapper == null)
        {
            StringBuffer stringbuffer = (new StringBuffer()).append("No ");
            Class class2;
            if(class$com$thoughtworks$xstream$mapper$AttributeMapper == null)
            {
                class2 = _mthclass$("com.thoughtworks.xstream.mapper.AttributeMapper");
                class$com$thoughtworks$xstream$mapper$AttributeMapper = class2;
            } else
            {
                class2 = class$com$thoughtworks$xstream$mapper$AttributeMapper;
            }
            throw new InitializationException(stringbuffer.append(class2.getName()).append(" available").toString());
        } else
        {
            attributeMapper.addAttributeFor(class1);
            return;
        }
    }

    public void useAttributeFor(Class class1, String s)
    {
        if(attributeMapper == null)
        {
            StringBuffer stringbuffer = (new StringBuffer()).append("No ");
            Class class2;
            if(class$com$thoughtworks$xstream$mapper$AttributeMapper == null)
            {
                class2 = _mthclass$("com.thoughtworks.xstream.mapper.AttributeMapper");
                class$com$thoughtworks$xstream$mapper$AttributeMapper = class2;
            } else
            {
                class2 = class$com$thoughtworks$xstream$mapper$AttributeMapper;
            }
            throw new InitializationException(stringbuffer.append(class2.getName()).append(" available").toString());
        } else
        {
            attributeMapper.addAttributeFor(class1, s);
            return;
        }
    }

    public void useAttributeFor(String s, Class class1)
    {
        if(attributeMapper == null)
        {
            StringBuffer stringbuffer = (new StringBuffer()).append("No ");
            Class class2;
            if(class$com$thoughtworks$xstream$mapper$AttributeMapper == null)
            {
                class2 = _mthclass$("com.thoughtworks.xstream.mapper.AttributeMapper");
                class$com$thoughtworks$xstream$mapper$AttributeMapper = class2;
            } else
            {
                class2 = class$com$thoughtworks$xstream$mapper$AttributeMapper;
            }
            throw new InitializationException(stringbuffer.append(class2.getName()).append(" available").toString());
        } else
        {
            attributeMapper.addAttributeFor(s, class1);
            return;
        }
    }

    protected boolean useXStream11XmlFriendlyMapper()
    {
        return false;
    }

    protected MapperWrapper wrapMapper(MapperWrapper mapperwrapper)
    {
        return mapperwrapper;
    }

    private static final String ANNOTATION_MAPPER_TYPE = "com.thoughtworks.xstream.mapper.AnnotationMapper";
    public static final int ID_REFERENCES = 1002;
    public static final int NO_REFERENCES = 1001;
    public static final int PRIORITY_LOW = -10;
    public static final int PRIORITY_NORMAL = 0;
    public static final int PRIORITY_VERY_HIGH = 10000;
    public static final int PRIORITY_VERY_LOW = -20;
    public static final int XPATH_ABSOLUTE_REFERENCES = 1004;
    public static final int XPATH_REFERENCES = 1003;
    public static final int XPATH_RELATIVE_REFERENCES = 1003;
    static Class class$com$thoughtworks$xstream$alias$ClassMapper;
    static Class class$com$thoughtworks$xstream$converters$Converter;
    static Class class$com$thoughtworks$xstream$converters$ConverterRegistry;
    static Class class$com$thoughtworks$xstream$converters$reflection$ReflectionProvider;
    static Class class$com$thoughtworks$xstream$core$JVM;
    static Class class$com$thoughtworks$xstream$mapper$AnnotationConfiguration;
    static Class class$com$thoughtworks$xstream$mapper$AttributeAliasingMapper;
    static Class class$com$thoughtworks$xstream$mapper$AttributeMapper;
    static Class class$com$thoughtworks$xstream$mapper$ClassAliasingMapper;
    static Class class$com$thoughtworks$xstream$mapper$DefaultImplementationsMapper;
    static Class class$com$thoughtworks$xstream$mapper$FieldAliasingMapper;
    static Class class$com$thoughtworks$xstream$mapper$ImmutableTypesMapper;
    static Class class$com$thoughtworks$xstream$mapper$ImplicitCollectionMapper;
    static Class class$com$thoughtworks$xstream$mapper$LocalConversionMapper;
    static Class class$com$thoughtworks$xstream$mapper$Mapper;
    static Class class$com$thoughtworks$xstream$mapper$Mapper$Null;
    static Class class$com$thoughtworks$xstream$mapper$PackageAliasingMapper;
    static Class class$com$thoughtworks$xstream$mapper$SystemAttributeAliasingMapper;
    static Class class$java$io$File;
    static Class class$java$lang$Boolean;
    static Class class$java$lang$Byte;
    static Class class$java$lang$Character;
    static Class class$java$lang$Class;
    static Class class$java$lang$ClassLoader;
    static Class class$java$lang$Double;
    static Class class$java$lang$Float;
    static Class class$java$lang$Integer;
    static Class class$java$lang$Long;
    static Class class$java$lang$Number;
    static Class class$java$lang$Object;
    static Class class$java$lang$Short;
    static Class class$java$lang$String;
    static Class class$java$lang$StringBuffer;
    static Class class$java$lang$reflect$Constructor;
    static Class class$java$lang$reflect$Method;
    static Class class$java$math$BigDecimal;
    static Class class$java$math$BigInteger;
    static Class class$java$net$URL;
    static Class class$java$util$ArrayList;
    static Class class$java$util$BitSet;
    static Class class$java$util$Calendar;
    static Class class$java$util$Date;
    static Class class$java$util$GregorianCalendar;
    static Class class$java$util$HashMap;
    static Class class$java$util$HashSet;
    static Class class$java$util$Hashtable;
    static Class class$java$util$LinkedList;
    static Class class$java$util$List;
    static Class class$java$util$Locale;
    static Class class$java$util$Map;
    static Class class$java$util$Map$Entry;
    static Class class$java$util$Properties;
    static Class class$java$util$Set;
    static Class class$java$util$TreeMap;
    static Class class$java$util$TreeSet;
    static Class class$java$util$Vector;
    private AnnotationConfiguration annotationConfiguration;
    private AttributeAliasingMapper attributeAliasingMapper;
    private AttributeMapper attributeMapper;
    private ClassAliasingMapper classAliasingMapper;
    private ClassLoaderReference classLoaderReference;
    private ConverterLookup converterLookup;
    private ConverterRegistry converterRegistry;
    private DefaultImplementationsMapper defaultImplementationsMapper;
    private FieldAliasingMapper fieldAliasingMapper;
    private HierarchicalStreamDriver hierarchicalStreamDriver;
    private ImmutableTypesMapper immutableTypesMapper;
    private ImplicitCollectionMapper implicitCollectionMapper;
    private transient JVM jvm;
    private LocalConversionMapper localConversionMapper;
    private Mapper mapper;
    private MarshallingStrategy marshallingStrategy;
    private PackageAliasingMapper packageAliasingMapper;
    private ReflectionProvider reflectionProvider;
    private SystemAttributeAliasingMapper systemAttributeAliasingMapper;


    private class _cls3
        implements com.thoughtworks.xstream.core.util.CustomObjectInputStream.StreamCallback
    {

        public void close()
        {
            reader.close();
        }

        public void defaultReadObject()
            throws NotActiveException
        {
            throw new NotActiveException("not in call to readObject");
        }

        public Map readFieldsFromStream()
            throws IOException
        {
            throw new NotActiveException("not in call to readObject");
        }

        public Object readFromStream()
            throws EOFException
        {
            if(!reader.hasMoreChildren())
            {
                throw new EOFException();
            } else
            {
                reader.moveDown();
                Object obj = unmarshal(reader);
                reader.moveUp();
                return obj;
            }
        }

        public void registerValidation(ObjectInputValidation objectinputvalidation, int i)
            throws NotActiveException
        {
            throw new NotActiveException("stream inactive");
        }

        private final XStream this$0;
        private final HierarchicalStreamReader val$reader;

        _cls3()
            throws NotActiveException, EOFException
        {
            this$0 = XStream.this;
            reader = hierarchicalstreamreader;
        }
    }


    private class _cls2
        implements com.thoughtworks.xstream.core.util.CustomObjectOutputStream.StreamCallback
    {

        public void close()
        {
            if(statefulWriter.state() != StatefulWriter.STATE_CLOSED)
            {
                statefulWriter.endNode();
                statefulWriter.close();
            }
        }

        public void defaultWriteObject()
            throws NotActiveException
        {
            throw new NotActiveException("not in call to writeObject");
        }

        public void flush()
        {
            statefulWriter.flush();
        }

        public void writeFieldsToStream(Map map)
            throws NotActiveException
        {
            throw new NotActiveException("not in call to writeObject");
        }

        public void writeToStream(Object obj)
        {
            marshal(obj, statefulWriter);
        }

        private final XStream this$0;
        private final StatefulWriter val$statefulWriter;

        _cls2()
            throws NotActiveException
        {
            this$0 = XStream.this;
            statefulWriter = statefulwriter;
        }
    }


    private class _cls1
        implements InvocationHandler
    {

        public Object invoke(Object obj, Method method, Object aobj[])
            throws Throwable
        {
            return method.invoke(mapper, aobj);
        }

        private final XStream this$0;

        _cls1()
            throws InvocationTargetException, IllegalAccessException
        {
            this$0 = XStream.this;
        }
    }

}
