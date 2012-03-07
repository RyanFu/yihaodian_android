// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.extended;

import com.thoughtworks.xstream.converters.*;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.thoughtworks.xstream.converters.extended:
//            JavaClassConverter

public class JavaMethodConverter
    implements Converter
{

    public JavaMethodConverter()
    {
        Class class1;
        if(class$com$thoughtworks$xstream$converters$extended$JavaMethodConverter == null)
        {
            class1 = _mthclass$("com.thoughtworks.xstream.converters.extended.JavaMethodConverter");
            class$com$thoughtworks$xstream$converters$extended$JavaMethodConverter = class1;
        } else
        {
            class1 = class$com$thoughtworks$xstream$converters$extended$JavaMethodConverter;
        }
        this(class1.getClassLoader());
    }

    public JavaMethodConverter(ClassLoader classloader)
    {
        javaClassConverter = new JavaClassConverter(classloader);
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

    private void marshalMethod(HierarchicalStreamWriter hierarchicalstreamwriter, String s, String s1, Class aclass[])
    {
        hierarchicalstreamwriter.startNode("class");
        hierarchicalstreamwriter.setValue(s);
        hierarchicalstreamwriter.endNode();
        if(s1 != null)
        {
            hierarchicalstreamwriter.startNode("name");
            hierarchicalstreamwriter.setValue(s1);
            hierarchicalstreamwriter.endNode();
        }
        hierarchicalstreamwriter.startNode("parameter-types");
        for(int i = 0; i < aclass.length; i++)
        {
            hierarchicalstreamwriter.startNode("class");
            hierarchicalstreamwriter.setValue(javaClassConverter.toString(aclass[i]));
            hierarchicalstreamwriter.endNode();
        }

        hierarchicalstreamwriter.endNode();
    }

    @Override
	public boolean canConvert(Class class1)
    {
        Class class2;
        boolean flag;
        Class class3;
        if(class$java$lang$reflect$Method == null)
        {
            class2 = _mthclass$("java.lang.reflect.Method");
            class$java$lang$reflect$Method = class2;
        } else
        {
            class2 = class$java$lang$reflect$Method;
        }
        if(class1.equals(class2)) goto _L2; else goto _L1
_L1:
        if(class$java$lang$reflect$Constructor == null)
        {
            class3 = _mthclass$("java.lang.reflect.Constructor");
            class$java$lang$reflect$Constructor = class3;
        } else
        {
            class3 = class$java$lang$reflect$Constructor;
        }
        if(!class1.equals(class3)) goto _L3; else goto _L2
_L2:
        flag = true;
_L5:
        return flag;
_L3:
        flag = false;
        if(true) goto _L5; else goto _L4
_L4:
    }

    @Override
	public void marshal(Object obj, HierarchicalStreamWriter hierarchicalstreamwriter, MarshallingContext marshallingcontext)
    {
        if(obj instanceof Method)
        {
            Method method = (Method)obj;
            marshalMethod(hierarchicalstreamwriter, javaClassConverter.toString(method.getDeclaringClass()), method.getName(), method.getParameterTypes());
        } else
        {
            Constructor constructor = (Constructor)obj;
            marshalMethod(hierarchicalstreamwriter, javaClassConverter.toString(constructor.getDeclaringClass()), null, constructor.getParameterTypes());
        }
    }

    @Override
	public Object unmarshal(HierarchicalStreamReader hierarchicalstreamreader, UnmarshallingContext unmarshallingcontext)
    {
        Class class1;
        Class class2;
        class1 = unmarshallingcontext.getRequiredType();
        if(class$java$lang$reflect$Method != null)
            break MISSING_BLOCK_LABEL_175;
        class2 = _mthclass$("java.lang.reflect.Method");
        class$java$lang$reflect$Method = class2;
_L1:
        boolean flag;
        Class class3;
        String s1;
        ArrayList arraylist;
        flag = class1.equals(class2);
        hierarchicalstreamreader.moveDown();
        String s = hierarchicalstreamreader.getValue();
        class3 = (Class)javaClassConverter.fromString(s);
        hierarchicalstreamreader.moveUp();
        s1 = null;
        if(flag)
        {
            hierarchicalstreamreader.moveDown();
            s1 = hierarchicalstreamreader.getValue();
            hierarchicalstreamreader.moveUp();
        }
        hierarchicalstreamreader.moveDown();
        arraylist = new ArrayList();
        for(; hierarchicalstreamreader.hasMoreChildren(); hierarchicalstreamreader.moveUp())
        {
            hierarchicalstreamreader.moveDown();
            String s2 = hierarchicalstreamreader.getValue();
            arraylist.add(javaClassConverter.fromString(s2));
        }

        break MISSING_BLOCK_LABEL_183;
        NoSuchMethodException nosuchmethodexception;
        nosuchmethodexception;
        throw new ConversionException(nosuchmethodexception);
        class2 = class$java$lang$reflect$Method;
          goto _L1
        Constructor constructor;
        Object obj;
        Class aclass[] = (Class[])arraylist.toArray(new Class[arraylist.size()]);
        hierarchicalstreamreader.moveUp();
        if(flag)
        {
            obj = class3.getDeclaredMethod(s1, aclass);
            break MISSING_BLOCK_LABEL_246;
        }
        constructor = class3.getDeclaredConstructor(aclass);
        obj = constructor;
        return obj;
    }

    static Class class$com$thoughtworks$xstream$converters$extended$JavaMethodConverter;
    static Class class$java$lang$reflect$Constructor;
    static Class class$java$lang$reflect$Method;
    private final SingleValueConverter javaClassConverter;
}
