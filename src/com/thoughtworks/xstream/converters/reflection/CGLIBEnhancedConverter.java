// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.reflection;

import com.thoughtworks.xstream.converters.*;
import com.thoughtworks.xstream.io.*;
import com.thoughtworks.xstream.mapper.Mapper;
import java.lang.reflect.*;
import java.util.*;

// Referenced classes of package com.thoughtworks.xstream.converters.reflection:
//            SerializableConverter, ObjectAccessException, SerializationMethodInvoker, ReflectionProvider, 
//            ReflectionProviderWrapper

public class CGLIBEnhancedConverter extends SerializableConverter
{
    private static class ReverseEngineeredCallbackFilter
        implements CallbackFilter
    {

        public int accept(Method method)
        {
            if(!callbackIndexMap.containsKey(method))
            {
                ConversionException conversionexception = new ConversionException("CGLIB callback not detected in reverse engineering");
                conversionexception.add("CGLIB callback", method.toString());
                throw conversionexception;
            } else
            {
                return ((Integer)callbackIndexMap.get(method)).intValue();
            }
        }

        private final Map callbackIndexMap;

        public ReverseEngineeredCallbackFilter(Map map)
        {
            callbackIndexMap = map;
        }
    }

    private static final class ReverseEngineeringInvocationHandler
        implements InvocationHandler
    {

        public Object invoke(Object obj, Method method, Object aobj[])
            throws Throwable
        {
            indexMap.put(indexMap.get(null), index);
            return null;
        }

        private final Integer index;
        private final Map indexMap;

        public ReverseEngineeringInvocationHandler(int i, Map map)
        {
            indexMap = map;
            index = new Integer(i);
        }
    }

    private static class CGLIBFilteringReflectionProvider extends ReflectionProviderWrapper
    {

        public void visitSerializableFields(Object obj, final ReflectionProvider.Visitor visitor)
        {
            class _cls1
                implements ReflectionProvider.Visitor
            {

                public void visit(String s, Class class1, Class class2, Object obj1)
                {
                    if(!s.startsWith("CGLIB$"))
                        visitor.visit(s, class1, class2, obj1);
                }

                private final CGLIBFilteringReflectionProvider this$0;
                private final ReflectionProvider.Visitor val$visitor;

                _cls1()
                {
                    this$0 = CGLIBFilteringReflectionProvider.this;
                    visitor = visitor1;
                }
            }

            wrapped.visitSerializableFields(obj, new _cls1());
        }

        public CGLIBFilteringReflectionProvider(ReflectionProvider reflectionprovider)
        {
            super(reflectionprovider);
        }
    }


    public CGLIBEnhancedConverter(Mapper mapper, ReflectionProvider reflectionprovider)
    {
        super(mapper, new CGLIBFilteringReflectionProvider(reflectionprovider));
        fieldCache = new HashMap();
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

    private Object create(Enhancer enhancer, List list, boolean flag)
    {
        Object obj = enhancer.create();
        if(flag)
            ((Factory)obj).setCallbacks((Callback[])(Callback[])list.toArray(new Callback[list.size()]));
        return obj;
    }

    private Map createCallbackIndexMap(Factory factory)
    {
        Callback acallback[];
        Callback acallback1[];
        HashMap hashmap;
        int i;
        acallback = factory.getCallbacks();
        acallback1 = new Callback[acallback.length];
        hashmap = new HashMap();
        i = -1;
        int j = 0;
        do
        {
            int k = acallback.length;
            if(j >= k)
                break;
            Callback callback = acallback[j];
            if(callback == null)
            {
                acallback1[j] = null;
            } else
            {
                Class class3;
                if(class$net$sf$cglib$proxy$NoOp == null)
                {
                    class3 = _mthclass$("net.sf.cglib.proxy.NoOp");
                    class$net$sf$cglib$proxy$NoOp = class3;
                } else
                {
                    class3 = class$net$sf$cglib$proxy$NoOp;
                }
                if(class3.isAssignableFrom(callback.getClass()))
                {
                    acallback1[j] = NoOp.INSTANCE;
                    i = j;
                } else
                {
                    acallback1[j] = createReverseEngineeredCallbackOfProperType(callback, j, hashmap);
                }
            }
            j++;
        } while(true);
        HashSet hashset1;
        Class class1;
        factory.setCallbacks(acallback1);
        HashSet hashset = new HashSet();
        hashset1 = new HashSet();
        class1 = factory.getClass();
        do
        {
            hashset1.addAll(Arrays.asList(class1.getDeclaredMethods()));
            hashset1.addAll(Arrays.asList(class1.getMethods()));
            hashset.addAll(Arrays.asList(class1.getInterfaces()));
            class1 = class1.getSuperclass();
        } while(class1 != null);
        for(Iterator iterator = hashset.iterator(); iterator.hasNext(); hashset1.addAll(Arrays.asList(class1.getDeclaredMethods())))
            class1 = (Class)iterator.next();

        break MISSING_BLOCK_LABEL_285;
        Exception exception;
        exception;
        factory.setCallbacks(acallback);
        throw exception;
        Iterator iterator1 = hashset1.iterator();
_L5:
        if(!iterator1.hasNext()) goto _L2; else goto _L1
_L1:
        Method method;
        method = (Method)iterator1.next();
        method.setAccessible(true);
        Class class2;
        if(class$net$sf$cglib$proxy$Factory == null)
        {
            class2 = _mthclass$("net.sf.cglib.proxy.Factory");
            class$net$sf$cglib$proxy$Factory = class2;
        } else
        {
            class2 = class$net$sf$cglib$proxy$Factory;
        }
        if(!class2.isAssignableFrom(method.getDeclaringClass()) && (0x18 & method.getModifiers()) <= 0) goto _L4; else goto _L3
_L3:
        iterator1.remove();
          goto _L5
_L4:
        Class aclass[] = method.getParameterTypes();
        Method method1 = method;
        if((0x400 & method.getModifiers()) > 0)
            method1 = factory.getClass().getMethod(method.getName(), method.getParameterTypes());
        hashmap.put(null, method);
        if(aclass != null) goto _L7; else goto _L6
_L6:
        Object aobj1[] = (Object[])null;
_L8:
        method1.invoke(factory, aobj1);
_L9:
        if(hashmap.containsKey(method))
            iterator1.remove();
          goto _L5
_L7:
        Object aobj[] = createNullArguments(aclass);
        aobj1 = aobj;
          goto _L8
        IllegalAccessException illegalaccessexception;
        illegalaccessexception;
        throw new ObjectAccessException("Access to " + method1 + " not allowed");
        NoSuchMethodException nosuchmethodexception;
        nosuchmethodexception;
        ConversionException conversionexception = new ConversionException("CGLIB enhanced proxies wit abstract nethod that has not been implemented");
        conversionexception.add("proxy superclass", class1.getSuperclass().getName());
        conversionexception.add("method", method.toString());
        throw conversionexception;
_L2:
        if(i >= 0)
        {
            Integer integer = new Integer(i);
            for(Iterator iterator2 = hashset1.iterator(); iterator2.hasNext(); hashmap.put(iterator2.next(), integer));
        }
        factory.setCallbacks(acallback);
        hashmap.remove(null);
        return hashmap;
        InvocationTargetException invocationtargetexception;
        invocationtargetexception;
          goto _L9
    }

    private Object[] createNullArguments(Class aclass[])
    {
        Object aobj[] = new Object[aclass.length];
        int i = 0;
        while(i < aobj.length) 
        {
            Class class1 = aclass[i];
            if(class1.isPrimitive())
                if(class1 == Byte.TYPE)
                    aobj[i] = new Byte((byte)0);
                else
                if(class1 == Short.TYPE)
                    aobj[i] = new Short((short)0);
                else
                if(class1 == Integer.TYPE)
                    aobj[i] = new Integer(0);
                else
                if(class1 == Long.TYPE)
                    aobj[i] = new Long(0L);
                else
                if(class1 == Float.TYPE)
                    aobj[i] = new Float(0.0F);
                else
                if(class1 == Double.TYPE)
                    aobj[i] = new Double(0.0D);
                else
                if(class1 == Character.TYPE)
                    aobj[i] = new Character('\0');
                else
                    aobj[i] = Boolean.FALSE;
            i++;
        }
        return aobj;
    }

    private Callback createReverseEngineeredCallbackOfProperType(Callback callback, int i, Map map)
    {
        Class class1 = null;
        Class aclass[] = callback.getClass().getInterfaces();
        int j = 0;
        do
        {
label0:
            {
label1:
                {
                    if(j < aclass.length)
                    {
                        Class class2;
                        Class class3;
                        if(class$net$sf$cglib$proxy$Callback == null)
                        {
                            class2 = _mthclass$("net.sf.cglib.proxy.Callback");
                            class$net$sf$cglib$proxy$Callback = class2;
                        } else
                        {
                            class2 = class$net$sf$cglib$proxy$Callback;
                        }
                        if(!class2.isAssignableFrom(aclass[j]))
                            break label0;
                        class1 = aclass[j];
                        if(class$net$sf$cglib$proxy$Callback == null)
                        {
                            class3 = _mthclass$("net.sf.cglib.proxy.Callback");
                            class$net$sf$cglib$proxy$Callback = class3;
                        } else
                        {
                            class3 = class$net$sf$cglib$proxy$Callback;
                        }
                        if(class1 == class3)
                        {
                            ConversionException conversionexception = new ConversionException("Cannot handle CGLIB callback");
                            conversionexception.add("CGLIB callback type", callback.getClass().getName());
                            throw conversionexception;
                        }
                        aclass = class1.getInterfaces();
                        List list = Arrays.asList(aclass);
                        ClassLoader classloader;
                        Class aclass1[];
                        Class class4;
                        if(class$net$sf$cglib$proxy$Callback == null)
                        {
                            class4 = _mthclass$("net.sf.cglib.proxy.Callback");
                            class$net$sf$cglib$proxy$Callback = class4;
                        } else
                        {
                            class4 = class$net$sf$cglib$proxy$Callback;
                        }
                        if(!list.contains(class4))
                            break label1;
                    }
                    classloader = class1.getClassLoader();
                    aclass1 = new Class[1];
                    aclass1[0] = class1;
                    return (Callback)Proxy.newProxyInstance(classloader, aclass1, new ReverseEngineeringInvocationHandler(i, map));
                }
                j = -1;
            }
            j++;
        } while(true);
    }

    private Callback[] getCallbacks(Object obj)
    {
        Class class1;
        Object obj1;
        int i;
        class1 = obj.getClass();
        obj1 = (List)fieldCache.get(class1.getName());
        if(obj1 != null)
            break MISSING_BLOCK_LABEL_102;
        obj1 = new ArrayList();
        fieldCache.put(class1.getName(), obj1);
        i = 0;
_L2:
        Field field = class1.getDeclaredField(CALLBACK_MARKER + i);
        field.setAccessible(true);
        ((List) (obj1)).add(field);
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        NoSuchFieldException nosuchfieldexception;
        nosuchfieldexception;
        ArrayList arraylist = new ArrayList();
        int j = 0;
        while(j < ((List) (obj1)).size()) 
        {
            try
            {
                arraylist.add(((Field)((List) (obj1)).get(j)).get(obj));
            }
            catch(IllegalAccessException illegalaccessexception)
            {
                throw new ObjectAccessException("Access to " + class1.getName() + "." + CALLBACK_MARKER + j + " not allowed");
            }
            j++;
        }
        return (Callback[])(Callback[])arraylist.toArray(new Callback[arraylist.size()]);
    }

    private void readCallback(HierarchicalStreamReader hierarchicalstreamreader, UnmarshallingContext unmarshallingcontext, List list, List list1)
    {
        Callback callback = (Callback)unmarshallingcontext.convertAnother(null, mapper.realClass(hierarchicalstreamreader.getNodeName()));
        list1.add(callback);
        if(callback == null)
            list.add(NoOp.INSTANCE);
        else
            list.add(callback);
    }

    private Object readResolve()
    {
        fieldCache = new HashMap();
        return this;
    }

    public boolean canConvert(Class class1)
    {
        if(Enhancer.isEnhanced(class1) && class1.getName().indexOf(DEFAULT_NAMING_MARKER) > 0) goto _L2; else goto _L1
_L1:
        Class class2;
        boolean flag;
        if(class$com$thoughtworks$xstream$mapper$CGLIBMapper$Marker == null)
        {
            class2 = _mthclass$("com.thoughtworks.xstream.mapper.CGLIBMapper$Marker");
            class$com$thoughtworks$xstream$mapper$CGLIBMapper$Marker = class2;
        } else
        {
            class2 = class$com$thoughtworks$xstream$mapper$CGLIBMapper$Marker;
        }
        if(class1 != class2) goto _L3; else goto _L2
_L2:
        flag = true;
_L5:
        return flag;
_L3:
        flag = false;
        if(true) goto _L5; else goto _L4
_L4:
    }

    protected List hierarchyFor(Class class1)
    {
        List list = super.hierarchyFor(class1);
        list.remove(list.size() - 1);
        return list;
    }

    public void marshal(Object obj, HierarchicalStreamWriter hierarchicalstreamwriter, MarshallingContext marshallingcontext)
    {
        Class class1;
        Callback acallback[];
        boolean flag1;
        Callback callback;
        class1 = obj.getClass();
        Class class2;
        boolean flag;
        Class aclass[];
        int i;
        if(class$net$sf$cglib$proxy$Factory == null)
        {
            class2 = _mthclass$("net.sf.cglib.proxy.Factory");
            class$net$sf$cglib$proxy$Factory = class2;
        } else
        {
            class2 = class$net$sf$cglib$proxy$Factory;
        }
        flag = class2.isAssignableFrom(class1);
        ExtendedHierarchicalStreamWriterHelper.startNode(hierarchicalstreamwriter, "type", class1);
        marshallingcontext.convertAnother(class1.getSuperclass());
        hierarchicalstreamwriter.endNode();
        hierarchicalstreamwriter.startNode("interfaces");
        aclass = class1.getInterfaces();
        i = 0;
        do
        {
            int j = aclass.length;
            if(i >= j)
                break;
            Class class5 = aclass[i];
            Class class6;
            if(class$net$sf$cglib$proxy$Factory == null)
            {
                class6 = _mthclass$("net.sf.cglib.proxy.Factory");
                class$net$sf$cglib$proxy$Factory = class6;
            } else
            {
                class6 = class$net$sf$cglib$proxy$Factory;
            }
            if(class5 != class6)
            {
                ExtendedHierarchicalStreamWriterHelper.startNode(hierarchicalstreamwriter, mapper.serializedClass(aclass[i].getClass()), aclass[i].getClass());
                marshallingcontext.convertAnother(aclass[i]);
                hierarchicalstreamwriter.endNode();
            }
            i++;
        } while(true);
        hierarchicalstreamwriter.endNode();
        hierarchicalstreamwriter.startNode("hasFactory");
        hierarchicalstreamwriter.setValue(String.valueOf(flag));
        hierarchicalstreamwriter.endNode();
        int k;
        int l;
        Map map;
        if(flag)
            acallback = ((Factory)obj).getCallbacks();
        else
            acallback = getCallbacks(obj);
        if(acallback.length <= 1) goto _L2; else goto _L1
_L1:
        if(!flag) goto _L4; else goto _L3
_L3:
        map = createCallbackIndexMap((Factory)obj);
        hierarchicalstreamwriter.startNode("callbacks");
        hierarchicalstreamwriter.startNode("mapping");
        marshallingcontext.convertAnother(map);
        hierarchicalstreamwriter.endNode();
_L2:
        flag1 = false;
        k = 0;
_L6:
        l = acallback.length;
        if(k >= l)
            break MISSING_BLOCK_LABEL_501;
        callback = acallback[k];
        if(callback != null)
            break; /* Loop/switch isn't completed */
        hierarchicalstreamwriter.startNode(mapper.serializedClass(null));
        hierarchicalstreamwriter.endNode();
_L7:
        k++;
        if(true) goto _L6; else goto _L5
_L4:
        ConversionException conversionexception = new ConversionException("Cannot handle CGLIB enhanced proxies without factory that have multiple callbacks");
        conversionexception.add("proxy superclass", class1.getSuperclass().getName());
        conversionexception.add("number of callbacks", String.valueOf(acallback.length));
        throw conversionexception;
_L5:
        if(!flag1)
        {
            Class class4;
            if(class$net$sf$cglib$proxy$MethodInterceptor == null)
            {
                class4 = _mthclass$("net.sf.cglib.proxy.MethodInterceptor");
                class$net$sf$cglib$proxy$MethodInterceptor = class4;
            } else
            {
                class4 = class$net$sf$cglib$proxy$MethodInterceptor;
            }
            if(!class4.isAssignableFrom(callback.getClass()))
                break MISSING_BLOCK_LABEL_495;
        }
        flag1 = true;
_L8:
        ExtendedHierarchicalStreamWriterHelper.startNode(hierarchicalstreamwriter, mapper.serializedClass(callback.getClass()), callback.getClass());
        marshallingcontext.convertAnother(callback);
        hierarchicalstreamwriter.endNode();
          goto _L7
        flag1 = false;
          goto _L8
        if(acallback.length > 1)
            hierarchicalstreamwriter.endNode();
        long l1;
        Field field = class1.getDeclaredField("serialVersionUID");
        field.setAccessible(true);
        l1 = field.getLong(null);
        if(class$java$lang$String != null) goto _L10; else goto _L9
_L9:
        Class class3;
        class3 = _mthclass$("java.lang.String");
        class$java$lang$String = class3;
_L11:
        ExtendedHierarchicalStreamWriterHelper.startNode(hierarchicalstreamwriter, "serialVersionUID", class3);
        hierarchicalstreamwriter.setValue(String.valueOf(l1));
        hierarchicalstreamwriter.endNode();
_L12:
        if(flag1)
        {
            hierarchicalstreamwriter.startNode("instance");
            super.doMarshalConditionally(obj, hierarchicalstreamwriter, marshallingcontext);
            hierarchicalstreamwriter.endNode();
        }
        return;
_L10:
        class3 = class$java$lang$String;
          goto _L11
        IllegalAccessException illegalaccessexception;
        illegalaccessexception;
        throw new ObjectAccessException("Access to serialverionUID of " + class1.getName() + " not allowed");
        NoSuchFieldException nosuchfieldexception;
        nosuchfieldexception;
          goto _L12
    }

    public Object unmarshal(HierarchicalStreamReader hierarchicalstreamreader, UnmarshallingContext unmarshallingcontext)
    {
        Enhancer enhancer = new Enhancer();
        hierarchicalstreamreader.moveDown();
        Class class1;
        ArrayList arraylist;
        if(class$java$lang$Class == null)
        {
            class1 = _mthclass$("java.lang.Class");
            class$java$lang$Class = class1;
        } else
        {
            class1 = class$java$lang$Class;
        }
        enhancer.setSuperclass((Class)unmarshallingcontext.convertAnother(null, class1));
        hierarchicalstreamreader.moveUp();
        hierarchicalstreamreader.moveDown();
        arraylist = new ArrayList();
        for(; hierarchicalstreamreader.hasMoreChildren(); hierarchicalstreamreader.moveUp())
        {
            hierarchicalstreamreader.moveDown();
            arraylist.add(unmarshallingcontext.convertAnother(null, mapper.realClass(hierarchicalstreamreader.getNodeName())));
        }

        enhancer.setInterfaces((Class[])(Class[])arraylist.toArray(new Class[arraylist.size()]));
        hierarchicalstreamreader.moveUp();
        hierarchicalstreamreader.moveDown();
        boolean flag = Boolean.valueOf(hierarchicalstreamreader.getValue()).booleanValue();
        enhancer.setUseFactory(flag);
        hierarchicalstreamreader.moveUp();
        ArrayList arraylist1 = new ArrayList();
        ArrayList arraylist2 = new ArrayList();
        Map map = null;
        hierarchicalstreamreader.moveDown();
        if("callbacks".equals(hierarchicalstreamreader.getNodeName()))
        {
            hierarchicalstreamreader.moveDown();
            Class class2;
            if(class$java$util$HashMap == null)
            {
                class2 = _mthclass$("java.util.HashMap");
                class$java$util$HashMap = class2;
            } else
            {
                class2 = class$java$util$HashMap;
            }
            map = (Map)unmarshallingcontext.convertAnother(null, class2);
            hierarchicalstreamreader.moveUp();
            for(; hierarchicalstreamreader.hasMoreChildren(); hierarchicalstreamreader.moveUp())
            {
                hierarchicalstreamreader.moveDown();
                readCallback(hierarchicalstreamreader, unmarshallingcontext, arraylist1, arraylist2);
            }

        } else
        {
            readCallback(hierarchicalstreamreader, unmarshallingcontext, arraylist1, arraylist2);
        }
        enhancer.setCallbacks((Callback[])(Callback[])arraylist1.toArray(new Callback[arraylist1.size()]));
        if(map != null)
            enhancer.setCallbackFilter(new ReverseEngineeredCallbackFilter(map));
        hierarchicalstreamreader.moveUp();
        Object obj = null;
        while(hierarchicalstreamreader.hasMoreChildren()) 
        {
            hierarchicalstreamreader.moveDown();
            if(hierarchicalstreamreader.getNodeName().equals("serialVersionUID"))
                enhancer.setSerialVersionUID(Long.valueOf(hierarchicalstreamreader.getValue()));
            else
            if(hierarchicalstreamreader.getNodeName().equals("instance"))
            {
                Object obj1 = create(enhancer, arraylist2, flag);
                super.doUnmarshalConditionally(obj1, hierarchicalstreamreader, unmarshallingcontext);
                obj = obj1;
            }
            hierarchicalstreamreader.moveUp();
        }
        if(obj == null)
            obj = create(enhancer, arraylist2, flag);
        return serializationMethodInvoker.callReadResolve(obj);
    }

    private static String CALLBACK_MARKER = "CGLIB$CALLBACK_";
    private static String DEFAULT_NAMING_MARKER = "$$EnhancerByCGLIB$$";
    static Class class$com$thoughtworks$xstream$mapper$CGLIBMapper$Marker;
    static Class class$java$lang$Class;
    static Class class$java$lang$String;
    static Class class$java$util$HashMap;
    static Class class$net$sf$cglib$proxy$Callback;
    static Class class$net$sf$cglib$proxy$Factory;
    static Class class$net$sf$cglib$proxy$MethodInterceptor;
    static Class class$net$sf$cglib$proxy$NoOp;
    private transient Map fieldCache;

}
