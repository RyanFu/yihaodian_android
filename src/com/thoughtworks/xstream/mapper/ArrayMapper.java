// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.mapper;

import com.thoughtworks.xstream.alias.ClassMapper;
import java.util.Arrays;
import java.util.Collection;

// Referenced classes of package com.thoughtworks.xstream.mapper:
//            MapperWrapper, Mapper

public class ArrayMapper extends MapperWrapper
{

    public ArrayMapper(ClassMapper classmapper)
    {
        this(((Mapper) (classmapper)));
    }

    public ArrayMapper(Mapper mapper)
    {
        super(mapper);
    }

    private String arrayType(int i, Class class1)
    {
        StringBuffer stringbuffer = new StringBuffer();
        for(int j = 0; j < i; j++)
            stringbuffer.append('[');

        String s;
        if(class1.isPrimitive())
        {
            stringbuffer.append(charThatJavaUsesToRepresentPrimitiveArrayType(class1));
            s = stringbuffer.toString();
        } else
        {
            stringbuffer.append('L').append(class1.getName()).append(';');
            s = stringbuffer.toString();
        }
        return s;
    }

    private String boxedTypeName(Class class1)
    {
        String s;
        if(BOXED_TYPES.contains(class1))
            s = class1.getName();
        else
            s = null;
        return s;
    }

    private char charThatJavaUsesToRepresentPrimitiveArrayType(Class class1)
    {
        char c;
        if(class1 == Boolean.TYPE)
            c = 'Z';
        else
        if(class1 == Byte.TYPE)
            c = 'B';
        else
        if(class1 == Character.TYPE)
            c = 'C';
        else
        if(class1 == Short.TYPE)
            c = 'S';
        else
        if(class1 == Integer.TYPE)
            c = 'I';
        else
        if(class1 == Long.TYPE)
            c = 'J';
        else
        if(class1 == Float.TYPE)
            c = 'F';
        else
        if(class1 == Double.TYPE)
            c = 'D';
        else
            c = '\0';
        return c;
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

    private Class primitiveClassNamed(String s)
    {
        Class class1;
        if(s.equals("void"))
            class1 = Void.TYPE;
        else
        if(s.equals("boolean"))
            class1 = Boolean.TYPE;
        else
        if(s.equals("byte"))
            class1 = Byte.TYPE;
        else
        if(s.equals("char"))
            class1 = Character.TYPE;
        else
        if(s.equals("short"))
            class1 = Short.TYPE;
        else
        if(s.equals("int"))
            class1 = Integer.TYPE;
        else
        if(s.equals("long"))
            class1 = Long.TYPE;
        else
        if(s.equals("float"))
            class1 = Float.TYPE;
        else
        if(s.equals("double"))
            class1 = Double.TYPE;
        else
            class1 = null;
        return class1;
    }

    @Override
	public Class realClass(String s)
    {
        int i;
        for(i = 0; s.endsWith("-array"); i++)
            s = s.substring(0, s.length() - 6);

        Class class1;
        if(i > 0)
        {
            Class class2 = primitiveClassNamed(s);
            if(class2 == null)
                class2 = super.realClass(s);
            while(class2.isArray()) 
            {
                class2 = class2.getComponentType();
                i++;
            }
            class1 = super.realClass(arrayType(i, class2));
        } else
        {
            class1 = super.realClass(s);
        }
        return class1;
    }

    @Override
	public String serializedClass(Class class1)
    {
        StringBuffer stringbuffer = new StringBuffer();
        String s = null;
        do
        {
            if(!class1.isArray())
                break;
            s = super.serializedClass(class1);
            if(!class1.getName().equals(s))
                break;
            class1 = class1.getComponentType();
            stringbuffer.append("-array");
            s = null;
        } while(true);
        if(s == null)
            s = boxedTypeName(class1);
        if(s == null)
            s = super.serializedClass(class1);
        String s1;
        if(stringbuffer.length() > 0)
            s1 = s + stringbuffer;
        else
            s1 = s;
        return s1;
    }

    private static final Collection BOXED_TYPES;
    static Class class$java$lang$Boolean;
    static Class class$java$lang$Byte;
    static Class class$java$lang$Character;
    static Class class$java$lang$Double;
    static Class class$java$lang$Float;
    static Class class$java$lang$Integer;
    static Class class$java$lang$Long;
    static Class class$java$lang$Short;

    static 
    {
        Class aclass[] = new Class[8];
        Class class1;
        Class class2;
        Class class3;
        Class class4;
        Class class5;
        Class class6;
        Class class7;
        Class class8;
        if(class$java$lang$Boolean == null)
        {
            class1 = _mthclass$("java.lang.Boolean");
            class$java$lang$Boolean = class1;
        } else
        {
            class1 = class$java$lang$Boolean;
        }
        aclass[0] = class1;
        if(class$java$lang$Byte == null)
        {
            class2 = _mthclass$("java.lang.Byte");
            class$java$lang$Byte = class2;
        } else
        {
            class2 = class$java$lang$Byte;
        }
        aclass[1] = class2;
        if(class$java$lang$Character == null)
        {
            class3 = _mthclass$("java.lang.Character");
            class$java$lang$Character = class3;
        } else
        {
            class3 = class$java$lang$Character;
        }
        aclass[2] = class3;
        if(class$java$lang$Short == null)
        {
            class4 = _mthclass$("java.lang.Short");
            class$java$lang$Short = class4;
        } else
        {
            class4 = class$java$lang$Short;
        }
        aclass[3] = class4;
        if(class$java$lang$Integer == null)
        {
            class5 = _mthclass$("java.lang.Integer");
            class$java$lang$Integer = class5;
        } else
        {
            class5 = class$java$lang$Integer;
        }
        aclass[4] = class5;
        if(class$java$lang$Long == null)
        {
            class6 = _mthclass$("java.lang.Long");
            class$java$lang$Long = class6;
        } else
        {
            class6 = class$java$lang$Long;
        }
        aclass[5] = class6;
        if(class$java$lang$Float == null)
        {
            class7 = _mthclass$("java.lang.Float");
            class$java$lang$Float = class7;
        } else
        {
            class7 = class$java$lang$Float;
        }
        aclass[6] = class7;
        if(class$java$lang$Double == null)
        {
            class8 = _mthclass$("java.lang.Double");
            class$java$lang$Double = class8;
        } else
        {
            class8 = class$java$lang$Double;
        }
        aclass[7] = class8;
        BOXED_TYPES = Arrays.asList(aclass);
    }
}
