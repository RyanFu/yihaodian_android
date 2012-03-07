// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.mapper;

import com.thoughtworks.xstream.InitializationException;
import com.thoughtworks.xstream.annotations.*;
import com.thoughtworks.xstream.converters.*;
import com.thoughtworks.xstream.converters.reflection.ReflectionProvider;
import com.thoughtworks.xstream.core.JVM;
import com.thoughtworks.xstream.core.util.DependencyInjectionFactory;
import java.lang.reflect.*;
import java.util.*;

// Referenced classes of package com.thoughtworks.xstream.mapper:
//            MapperWrapper, AnnotationConfiguration, ClassAliasingMapper, DefaultImplementationsMapper, 
//            ImplicitCollectionMapper, FieldAliasingMapper, AttributeMapper, LocalConversionMapper, 
//            Mapper

public class AnnotationMapper extends MapperWrapper
    implements AnnotationConfiguration
{
    private static class WeakHashSet
        implements Set
    {

        public boolean add(Object obj)
        {
            boolean flag;
            if(map.put(obj, NULL) == null)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public boolean addAll(Collection collection)
        {
            boolean flag = false;
            for(Iterator iterator1 = collection.iterator(); iterator1.hasNext();)
                flag = false | add(iterator1.next());

            return flag;
        }

        public void clear()
        {
            map.clear();
        }

        public boolean contains(Object obj)
        {
            return map.containsKey(obj);
        }

        public boolean containsAll(Collection collection)
        {
            return map.keySet().containsAll(collection);
        }

        public boolean isEmpty()
        {
            return map.isEmpty();
        }

        public Iterator iterator()
        {
            return map.keySet().iterator();
        }

        public boolean remove(Object obj)
        {
            boolean flag;
            if(map.remove(obj) != null)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public boolean removeAll(Collection collection)
        {
            boolean flag = false;
            for(Iterator iterator1 = collection.iterator(); iterator1.hasNext();)
                flag = false | remove(iterator1.next());

            return flag;
        }

        public boolean retainAll(Collection collection)
        {
            boolean flag = false;
            Iterator iterator1 = iterator();
            do
            {
                if(!iterator1.hasNext())
                    break;
                if(!collection.contains(iterator1.next()))
                {
                    iterator1.remove();
                    flag = true;
                }
            } while(true);
            return flag;
        }

        public int size()
        {
            return map.size();
        }

        public Object[] toArray()
        {
            return map.keySet().toArray();
        }

        public Object[] toArray(Object aobj[])
        {
            return map.keySet().toArray(aobj);
        }

        private static Object NULL = new Object();
        private final WeakHashMap map;


        private WeakHashSet()
        {
            map = new WeakHashMap();
        }

    }

    private final class UnprocessedTypesSet extends LinkedHashSet
    {

        public boolean add(Class class1)
        {
            boolean flag;
            if(class1 == null)
            {
                flag = false;
            } else
            {
                for(; class1.isArray(); class1 = class1.getComponentType());
                String s = class1.getName();
                if(s.startsWith("java.") || s.startsWith("javax."))
                {
                    flag = false;
                } else
                {
                    boolean flag1;
                    if(annotatedTypes.contains(class1))
                        flag1 = false;
                    else
                        flag1 = super.add(class1);
                    if(flag1)
                    {
                        XStreamInclude xstreaminclude = (XStreamInclude)class1.getAnnotation(com/thoughtworks/xstream/annotations/XStreamInclude);
                        if(xstreaminclude != null)
                        {
                            Class aclass[] = xstreaminclude.value();
                            if(aclass != null)
                            {
                                int i = aclass.length;
                                for(int j = 0; j < i; j++)
                                    add(aclass[j]);

                            }
                        }
                    }
                    flag = flag1;
                }
            }
            return flag;
        }

        public volatile boolean add(Object obj)
        {
            return add((Class)obj);
        }

        final AnnotationMapper this$0;

        private UnprocessedTypesSet()
        {
            this$0 = AnnotationMapper.this;
            super();
        }

    }


    public AnnotationMapper(Mapper mapper, ConverterRegistry converterregistry, ClassLoader classloader, ReflectionProvider reflectionprovider, JVM jvm)
    {
        super(mapper);
        converterRegistry = converterregistry;
        annotatedTypes.add(java/lang/Object);
        locked = true;
        Object aobj[] = new Object[4];
        aobj[0] = this;
        aobj[1] = classloader;
        aobj[2] = reflectionprovider;
        aobj[3] = jvm;
        arguments = aobj;
    }

    private void addParametrizedTypes(Type type, final Set types)
    {
        final HashSet processedTypes = new HashSet();
        LinkedHashSet linkedhashset = new LinkedHashSet() {

            public volatile boolean add(Object obj)
            {
                return add((Type)obj);
            }

            public boolean add(Type type1)
            {
                boolean flag;
                if(type1 instanceof Class)
                    flag = types.add((Class)type1);
                else
                if(type1 == null || processedTypes.contains(type1))
                    flag = false;
                else
                    flag = super.add(type1);
                return flag;
            }

            final AnnotationMapper this$0;
            final Set val$processedTypes;
            final Set val$types;

            
            {
                this$0 = AnnotationMapper.this;
                types = set;
                processedTypes = set1;
                super();
            }
        }
;
        while(type != null) 
        {
            processedTypes.add(type);
            if(type instanceof Class)
            {
                Class class1 = (Class)type;
                types.add(class1);
                if(!class1.isPrimitive())
                {
                    TypeVariable atypevariable[] = class1.getTypeParameters();
                    int i1 = atypevariable.length;
                    for(int j1 = 0; j1 < i1; j1++)
                        linkedhashset.add(atypevariable[j1]);

                    linkedhashset.add(class1.getGenericSuperclass());
                    Type atype2[] = class1.getGenericInterfaces();
                    int k1 = atype2.length;
                    for(int l1 = 0; l1 < k1; l1++)
                        linkedhashset.add(atype2[l1]);

                }
            } else
            if(type instanceof TypeVariable)
            {
                Type atype1[] = ((TypeVariable)type).getBounds();
                int k = atype1.length;
                for(int l = 0; l < k; l++)
                    linkedhashset.add(atype1[l]);

            } else
            if(type instanceof ParameterizedType)
            {
                ParameterizedType parameterizedtype = (ParameterizedType)type;
                linkedhashset.add(parameterizedtype.getRawType());
                Type atype[] = parameterizedtype.getActualTypeArguments();
                int i = atype.length;
                for(int j = 0; j < i; j++)
                    linkedhashset.add(atype[j]);

            } else
            if(type instanceof GenericArrayType)
                linkedhashset.add(((GenericArrayType)type).getGenericComponentType());
            if(!linkedhashset.isEmpty())
            {
                Iterator iterator = linkedhashset.iterator();
                type = (Type)iterator.next();
                iterator.remove();
            } else
            {
                type = null;
            }
        }
    }

    private Converter cacheConverter(Class class1)
    {
        Object obj = (Converter)converterCache.get(class1);
        if(obj == null)
            try
            {
                if(com/thoughtworks/xstream/converters/SingleValueConverter.isAssignableFrom(class1))
                    obj = new SingleValueConverterWrapper((SingleValueConverter)DependencyInjectionFactory.newInstance(class1, arguments));
                else
                    obj = (Converter)DependencyInjectionFactory.newInstance(class1, arguments);
                converterCache.put(class1, obj);
            }
            catch(Exception exception)
            {
                throw new InitializationException((new StringBuilder()).append("Cannot instantiate converter ").append(class1.getName()).toString(), exception);
            }
        return ((Converter) (obj));
    }

    private Class getClass(Type type)
    {
        Class class1 = null;
        if(!(type instanceof ParameterizedType)) goto _L2; else goto _L1
_L1:
        class1 = (Class)((ParameterizedType)type).getRawType();
_L4:
        return class1;
_L2:
        if(type instanceof Class)
            class1 = (Class)type;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void processAliasAnnotation(Class class1, Set set)
    {
        XStreamAlias xstreamalias = (XStreamAlias)class1.getAnnotation(com/thoughtworks/xstream/annotations/XStreamAlias);
        if(xstreamalias != null)
        {
            if(classAliasingMapper == null)
                throw new InitializationException((new StringBuilder()).append("No ").append(com/thoughtworks/xstream/mapper/ClassAliasingMapper.getName()).append(" available").toString());
            if(xstreamalias.impl() != java/lang/Void)
            {
                classAliasingMapper.addClassAlias(xstreamalias.value(), class1);
                defaultImplementationsMapper.addDefaultImplementation(xstreamalias.impl(), class1);
                if(class1.isInterface())
                    set.add(xstreamalias.impl());
            } else
            {
                classAliasingMapper.addClassAlias(xstreamalias.value(), class1);
            }
        }
    }

    private void processAnnotations(Class class1)
    {
        if(class1 != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        Set set = annotatedTypes;
        set;
        JVM INSTR monitorenter ;
        UnprocessedTypesSet unprocessedtypesset = new UnprocessedTypesSet();
        unprocessedtypesset.add(class1);
        processTypes(unprocessedtypesset);
        if(true) goto _L1; else goto _L3
_L3:
    }

    private void processAsAttributeAnnotation(Field field)
    {
        if((XStreamAsAttribute)field.getAnnotation(com/thoughtworks/xstream/annotations/XStreamAsAttribute) != null)
        {
            if(attributeMapper == null)
                throw new InitializationException((new StringBuilder()).append("No ").append(com/thoughtworks/xstream/mapper/AttributeMapper.getName()).append(" available").toString());
            attributeMapper.addAttributeFor(field);
        }
    }

    private void processConverterAnnotations(Class class1)
    {
        if(converterRegistry != null)
        {
            XStreamConverters xstreamconverters = (XStreamConverters)class1.getAnnotation(com/thoughtworks/xstream/annotations/XStreamConverters);
            XStreamConverter xstreamconverter = (XStreamConverter)class1.getAnnotation(com/thoughtworks/xstream/annotations/XStreamConverter);
            ArrayList arraylist;
            Iterator iterator;
            if(xstreamconverters != null)
                arraylist = new ArrayList(Arrays.asList(xstreamconverters.value()));
            else
                arraylist = new ArrayList();
            if(xstreamconverter != null)
                arraylist.add(xstreamconverter);
            iterator = arraylist.iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                Class class2 = ((XStreamConverter)iterator.next()).value();
                Converter converter = cacheConverter(class2);
                if(converter != null)
                    if(xstreamconverter != null || converter.canConvert(class1))
                        converterRegistry.registerConverter(converter, 0);
                    else
                        throw new InitializationException((new StringBuilder()).append("Converter ").append(class2.getName()).append(" cannot handle annotated class ").append(class1.getName()).toString());
            } while(true);
        }
    }

    private void processFieldAliasAnnotation(Field field)
    {
        XStreamAlias xstreamalias = (XStreamAlias)field.getAnnotation(com/thoughtworks/xstream/annotations/XStreamAlias);
        if(xstreamalias != null)
        {
            if(fieldAliasingMapper == null)
                throw new InitializationException((new StringBuilder()).append("No ").append(com/thoughtworks/xstream/mapper/FieldAliasingMapper.getName()).append(" available").toString());
            fieldAliasingMapper.addFieldAlias(xstreamalias.value(), field.getDeclaringClass(), field.getName());
        }
    }

    private void processImplicitAnnotation(Field field)
    {
        XStreamImplicit xstreamimplicit = (XStreamImplicit)field.getAnnotation(com/thoughtworks/xstream/annotations/XStreamImplicit);
        if(xstreamimplicit != null)
        {
            if(implicitCollectionMapper == null)
                throw new InitializationException((new StringBuilder()).append("No ").append(com/thoughtworks/xstream/mapper/ImplicitCollectionMapper.getName()).append(" available").toString());
            String s = field.getName();
            String s1 = xstreamimplicit.itemFieldName();
            Class class1 = null;
            Type type = field.getGenericType();
            if(type instanceof ParameterizedType)
                class1 = getClass(((ParameterizedType)type).getActualTypeArguments()[0]);
            if(s1 != null && !"".equals(s1))
                implicitCollectionMapper.add(field.getDeclaringClass(), s, s1, class1);
            else
                implicitCollectionMapper.add(field.getDeclaringClass(), s, class1);
        }
    }

    private void processImplicitCollectionAnnotation(Class class1)
    {
        XStreamImplicitCollection xstreamimplicitcollection = (XStreamImplicitCollection)class1.getAnnotation(com/thoughtworks/xstream/annotations/XStreamImplicitCollection);
        if(xstreamimplicitcollection != null)
        {
            if(implicitCollectionMapper == null)
                throw new InitializationException((new StringBuilder()).append("No ").append(com/thoughtworks/xstream/mapper/ImplicitCollectionMapper.getName()).append(" available").toString());
            String s = xstreamimplicitcollection.value();
            String s1 = xstreamimplicitcollection.item();
            Field field;
            Class class2;
            Type type;
            try
            {
                field = class1.getDeclaredField(s);
            }
            catch(NoSuchFieldException nosuchfieldexception)
            {
                throw new InitializationException((new StringBuilder()).append(class1.getName()).append(" does not have a field named '").append(s).append("' as required by ").append(com/thoughtworks/xstream/annotations/XStreamImplicitCollection.getName()).toString());
            }
            class2 = null;
            type = field.getGenericType();
            if(type instanceof ParameterizedType)
                class2 = getClass(((ParameterizedType)type).getActualTypeArguments()[0]);
            if(class2 == null)
                implicitCollectionMapper.add(class1, s, null, java/lang/Object);
            else
            if(s1.equals(""))
                implicitCollectionMapper.add(class1, s, null, class2);
            else
                implicitCollectionMapper.add(class1, s, s1, class2);
        }
    }

    private void processLocalConverterAnnotation(Field field)
    {
        XStreamConverter xstreamconverter = (XStreamConverter)field.getAnnotation(com/thoughtworks/xstream/annotations/XStreamConverter);
        if(xstreamconverter != null)
        {
            Converter converter = cacheConverter(xstreamconverter.value());
            if(converter != null)
            {
                if(localConversionMapper == null)
                    throw new InitializationException((new StringBuilder()).append("No ").append(com/thoughtworks/xstream/mapper/LocalConversionMapper.getName()).append(" available").toString());
                localConversionMapper.registerLocalConverter(field.getDeclaringClass(), field.getName(), converter);
            }
        }
    }

    private void processOmitFieldAnnotation(Field field)
    {
        if((XStreamOmitField)field.getAnnotation(com/thoughtworks/xstream/annotations/XStreamOmitField) != null)
        {
            if(fieldAliasingMapper == null)
                throw new InitializationException((new StringBuilder()).append("No ").append(com/thoughtworks/xstream/mapper/FieldAliasingMapper.getName()).append(" available").toString());
            fieldAliasingMapper.omitField(field.getDeclaringClass(), field.getName());
        }
    }

    private void processTypes(Set set)
    {
        while(!set.isEmpty()) 
        {
            Iterator iterator = set.iterator();
            Class class1 = (Class)iterator.next();
            iterator.remove();
            if(annotatedTypes.add(class1) && !class1.isPrimitive())
            {
                addParametrizedTypes(class1, set);
                processConverterAnnotations(class1);
                processAliasAnnotation(class1, set);
                if(!class1.isInterface())
                {
                    processImplicitCollectionAnnotation(class1);
                    Field afield[] = class1.getDeclaredFields();
                    int i = 0;
                    while(i < afield.length) 
                    {
                        Field field = afield[i];
                        if(!field.isEnumConstant() && (0x88 & field.getModifiers()) <= 0)
                        {
                            addParametrizedTypes(field.getGenericType(), set);
                            if(!field.isSynthetic())
                            {
                                processFieldAliasAnnotation(field);
                                processAsAttributeAnnotation(field);
                                processImplicitAnnotation(field);
                                processOmitFieldAnnotation(field);
                                processLocalConverterAnnotation(field);
                            }
                        }
                        i++;
                    }
                }
            }
        }
    }

    public void autodetectAnnotations(boolean flag)
    {
        boolean flag1;
        if(!flag)
            flag1 = true;
        else
            flag1 = false;
        locked = flag1;
    }

    public Class defaultImplementationOf(Class class1)
    {
        if(!locked)
            processAnnotations(class1);
        Class class2 = super.defaultImplementationOf(class1);
        if(!locked)
            processAnnotations(class2);
        return class2;
    }

    public Converter getLocalConverter(Class class1, String s)
    {
        if(!locked)
            processAnnotations(class1);
        return super.getLocalConverter(class1, s);
    }

    public void processAnnotations(Class aclass[])
    {
        if(aclass != null && aclass.length != 0) goto _L2; else goto _L1
_L1:
        return;
_L2:
        locked = true;
        Set set = annotatedTypes;
        set;
        JVM INSTR monitorenter ;
        UnprocessedTypesSet unprocessedtypesset = new UnprocessedTypesSet();
        int i = aclass.length;
        for(int j = 0; j < i; j++)
            unprocessedtypesset.add(aclass[j]);

        processTypes(unprocessedtypesset);
        if(true) goto _L1; else goto _L3
_L3:
    }

    public String realMember(Class class1, String s)
    {
        if(!locked)
            processAnnotations(class1);
        return super.realMember(class1, s);
    }

    public String serializedClass(Class class1)
    {
        if(!locked)
            processAnnotations(class1);
        return super.serializedClass(class1);
    }

    private final Set annotatedTypes = new WeakHashSet();
    private final Object arguments[];
    private final AttributeMapper attributeMapper = (AttributeMapper)lookupMapperOfType(com/thoughtworks/xstream/mapper/AttributeMapper);
    private final ClassAliasingMapper classAliasingMapper = (ClassAliasingMapper)lookupMapperOfType(com/thoughtworks/xstream/mapper/ClassAliasingMapper);
    private final Map converterCache = new HashMap();
    private final ConverterRegistry converterRegistry;
    private final DefaultImplementationsMapper defaultImplementationsMapper = (DefaultImplementationsMapper)lookupMapperOfType(com/thoughtworks/xstream/mapper/DefaultImplementationsMapper);
    private final FieldAliasingMapper fieldAliasingMapper = (FieldAliasingMapper)lookupMapperOfType(com/thoughtworks/xstream/mapper/FieldAliasingMapper);
    private final ImplicitCollectionMapper implicitCollectionMapper = (ImplicitCollectionMapper)lookupMapperOfType(com/thoughtworks/xstream/mapper/ImplicitCollectionMapper);
    private final LocalConversionMapper localConversionMapper = (LocalConversionMapper)lookupMapperOfType(com/thoughtworks/xstream/mapper/LocalConversionMapper);
    private boolean locked;

}
