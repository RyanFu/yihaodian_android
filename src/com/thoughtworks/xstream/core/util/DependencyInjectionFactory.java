// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.core.util;

import com.thoughtworks.xstream.converters.reflection.ObjectAccessException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

// Referenced classes of package com.thoughtworks.xstream.core.util:
//            Primitives, TypedNull

public class DependencyInjectionFactory
{
    private static class TypedValue
    {

        final Class type;
        final Object value;

        public TypedValue(Class class1, Object obj)
        {
            type = class1;
            value = obj;
        }
    }


    public DependencyInjectionFactory()
    {
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

    public static Object newInstance(Class class1, Object aobj[])
    {
        Constructor aconstructor[];
        TypedValue atypedvalue[];
        Constructor constructor;
        int j;
        ArrayList arraylist;
        int k;
        Object obj;
        aconstructor = class1.getConstructors();
        if(aconstructor.length > 1)
            Arrays.sort(aconstructor, new _cls1());
        atypedvalue = new TypedValue[aobj.length];
        int i = 0;
        while(i < aobj.length) 
        {
            Object obj2 = aobj[i];
            Class class2 = obj2.getClass();
            if(class2.isPrimitive())
            {
                class2 = Primitives.box(class2);
            } else
            {
                Class class3;
                if(class$com$thoughtworks$xstream$core$util$TypedNull == null)
                {
                    class3 = _mthclass$("com.thoughtworks.xstream.core.util.TypedNull");
                    class$com$thoughtworks$xstream$core$util$TypedNull = class3;
                } else
                {
                    class3 = class$com$thoughtworks$xstream$core$util$TypedNull;
                }
                if(class2 == class3)
                {
                    class2 = ((TypedNull)obj2).getType();
                    obj2 = null;
                }
            }
            atypedvalue[i] = new TypedValue(class2, obj2);
            i++;
        }
        constructor = null;
        j = 0x7fffffff;
        arraylist = new ArrayList();
        k = 0;
        obj = null;
_L2:
        Constructor constructor2;
        Class aclass[];
        Constructor constructor4;
        if(constructor != null || k >= aconstructor.length)
            break MISSING_BLOCK_LABEL_788;
        constructor2 = aconstructor[k];
        aclass = constructor2.getParameterTypes();
        if(aclass.length <= aobj.length)
            break; /* Loop/switch isn't completed */
        constructor4 = obj;
_L3:
        k++;
        obj = constructor4;
        if(true) goto _L2; else goto _L1
_L1:
        Constructor constructor1;
        ObjectAccessException objectaccessexception3;
        if(aclass.length == 0)
        {
            constructor1 = constructor2;
            break MISSING_BLOCK_LABEL_215;
        }
label0:
        {
            if(j <= aclass.length)
                break MISSING_BLOCK_LABEL_293;
            if(obj == null)
                break label0;
            constructor = obj;
            constructor4 = obj;
        }
          goto _L3
        j = aclass.length;
        int i1;
        for(int l = 0; l < aclass.length; l++)
            if(aclass[l].isPrimitive())
                aclass[l] = Primitives.box(aclass[l]);

        arraylist.clear();
        i1 = 0;
        j1 = 0;
_L16:
        if(i1 >= aclass.length || (j1 + aclass.length) - i1 > atypedvalue.length)
            break MISSING_BLOCK_LABEL_778;
        if(!aclass[i1].isAssignableFrom(atypedvalue[j1].type)) goto _L5; else goto _L4
_L4:
        arraylist.add(atypedvalue[j1].value);
        if(++i1 != aclass.length) goto _L5; else goto _L6
_L6:
        Constructor constructor3;
        i1;
        constructor3 = constructor2;
_L22:
        if(constructor3 != null || obj != null) goto _L8; else goto _L7
_L7:
        atypedvalue1 = new TypedValue[atypedvalue.length];
        System.arraycopy(atypedvalue, 0, atypedvalue1, 0, atypedvalue1.length);
        arraylist.clear();
        k1 = 0;
_L21:
        if(k1 >= aclass.length) goto _L10; else goto _L9
_L9:
        l1 = -1;
        i2 = 0;
_L15:
        if(i2 >= atypedvalue1.length) goto _L12; else goto _L11
_L11:
        if(atypedvalue1[i2] != null) goto _L14; else goto _L13
_L13:
        i2++;
          goto _L15
_L5:
        j1++;
          goto _L16
_L14:
        if(atypedvalue1[i2].type != aclass[k1]) goto _L18; else goto _L17
_L17:
        l1 = i2;
_L12:
        if(l1 < 0) goto _L20; else goto _L19
_L19:
        arraylist.add(atypedvalue1[l1].value);
        atypedvalue1[l1] = null;
        k1++;
          goto _L21
_L18:
        if(aclass[k1].isAssignableFrom(atypedvalue1[i2].type) && (l1 < 0 || atypedvalue1[l1].type.isAssignableFrom(atypedvalue1[i2].type)))
            l1 = i2;
          goto _L13
_L20:
        constructor4 = null;
        constructor = constructor3;
          goto _L3
_L23:
        if(constructor1 == null)
        {
            int j1;
            TypedValue atypedvalue1[];
            int k1;
            int l1;
            int i2;
            if(obj == null)
            {
                objectaccessexception3 = new ObjectAccessException("Cannot construct " + class1.getName() + ", none of the dependencies match any constructor's parameters");
                throw objectaccessexception3;
            }
            constructor1 = obj;
        }
        Object obj1;
        try
        {
            Object aobj1[] = arraylist.toArray();
            obj1 = constructor1.newInstance(aobj1);
        }
        catch(InstantiationException instantiationexception)
        {
            ObjectAccessException objectaccessexception2 = new ObjectAccessException("Cannot construct " + class1.getName(), instantiationexception);
            throw objectaccessexception2;
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            ObjectAccessException objectaccessexception1 = new ObjectAccessException("Cannot construct " + class1.getName(), illegalaccessexception);
            throw objectaccessexception1;
        }
        catch(InvocationTargetException invocationtargetexception)
        {
            ObjectAccessException objectaccessexception = new ObjectAccessException("Cannot construct " + class1.getName(), invocationtargetexception);
            throw objectaccessexception;
        }
        return obj1;
_L10:
        constructor4 = constructor2;
        constructor = constructor3;
          goto _L3
_L8:
        constructor4 = obj;
        constructor = constructor3;
          goto _L3
        i1;
        constructor3 = constructor;
          goto _L22
        constructor1 = constructor;
          goto _L23
    }

    static Class class$com$thoughtworks$xstream$core$util$TypedNull;

    private class _cls1
        implements Comparator
    {

        public int compare(Object obj, Object obj1)
        {
            return ((Constructor)obj1).getParameterTypes().length - ((Constructor)obj).getParameterTypes().length;
        }

        _cls1()
        {
        }
    }

}
