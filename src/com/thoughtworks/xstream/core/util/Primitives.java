// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.core.util;

import java.util.HashMap;
import java.util.Map;

public final class Primitives
{

    public Primitives()
    {
    }

    public static Class box(Class class1)
    {
        return (Class)BOX.get(class1);
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

    public static Class unbox(Class class1)
    {
        return (Class)UNBOX.get(class1);
    }

    private static final Map BOX;
    private static final Map UNBOX;
    static Class class$java$lang$Boolean;
    static Class class$java$lang$Byte;
    static Class class$java$lang$Character;
    static Class class$java$lang$Double;
    static Class class$java$lang$Float;
    static Class class$java$lang$Integer;
    static Class class$java$lang$Long;
    static Class class$java$lang$Short;
    static Class class$java$lang$Void;

    static 
    {
        BOX = new HashMap();
        UNBOX = new HashMap();
        Class aclass[][] = new Class[9][];
        Class aclass1[] = new Class[2];
        aclass1[0] = Byte.TYPE;
        Class class1;
        Class aclass2[];
        Class class2;
        Class aclass3[];
        Class class3;
        Class aclass4[];
        Class class4;
        Class aclass5[];
        Class class5;
        Class aclass6[];
        Class class6;
        Class aclass7[];
        Class class7;
        Class aclass8[];
        Class class8;
        Class aclass9[];
        Class class9;
        if(class$java$lang$Byte == null)
        {
            class1 = _mthclass$("java.lang.Byte");
            class$java$lang$Byte = class1;
        } else
        {
            class1 = class$java$lang$Byte;
        }
        aclass1[1] = class1;
        aclass[0] = aclass1;
        aclass2 = new Class[2];
        aclass2[0] = Character.TYPE;
        if(class$java$lang$Character == null)
        {
            class2 = _mthclass$("java.lang.Character");
            class$java$lang$Character = class2;
        } else
        {
            class2 = class$java$lang$Character;
        }
        aclass2[1] = class2;
        aclass[1] = aclass2;
        aclass3 = new Class[2];
        aclass3[0] = Short.TYPE;
        if(class$java$lang$Short == null)
        {
            class3 = _mthclass$("java.lang.Short");
            class$java$lang$Short = class3;
        } else
        {
            class3 = class$java$lang$Short;
        }
        aclass3[1] = class3;
        aclass[2] = aclass3;
        aclass4 = new Class[2];
        aclass4[0] = Integer.TYPE;
        if(class$java$lang$Integer == null)
        {
            class4 = _mthclass$("java.lang.Integer");
            class$java$lang$Integer = class4;
        } else
        {
            class4 = class$java$lang$Integer;
        }
        aclass4[1] = class4;
        aclass[3] = aclass4;
        aclass5 = new Class[2];
        aclass5[0] = Long.TYPE;
        if(class$java$lang$Long == null)
        {
            class5 = _mthclass$("java.lang.Long");
            class$java$lang$Long = class5;
        } else
        {
            class5 = class$java$lang$Long;
        }
        aclass5[1] = class5;
        aclass[4] = aclass5;
        aclass6 = new Class[2];
        aclass6[0] = Float.TYPE;
        if(class$java$lang$Float == null)
        {
            class6 = _mthclass$("java.lang.Float");
            class$java$lang$Float = class6;
        } else
        {
            class6 = class$java$lang$Float;
        }
        aclass6[1] = class6;
        aclass[5] = aclass6;
        aclass7 = new Class[2];
        aclass7[0] = Double.TYPE;
        if(class$java$lang$Double == null)
        {
            class7 = _mthclass$("java.lang.Double");
            class$java$lang$Double = class7;
        } else
        {
            class7 = class$java$lang$Double;
        }
        aclass7[1] = class7;
        aclass[6] = aclass7;
        aclass8 = new Class[2];
        aclass8[0] = Boolean.TYPE;
        if(class$java$lang$Boolean == null)
        {
            class8 = _mthclass$("java.lang.Boolean");
            class$java$lang$Boolean = class8;
        } else
        {
            class8 = class$java$lang$Boolean;
        }
        aclass8[1] = class8;
        aclass[7] = aclass8;
        aclass9 = new Class[2];
        aclass9[0] = Void.TYPE;
        if(class$java$lang$Void == null)
        {
            class9 = _mthclass$("java.lang.Void");
            class$java$lang$Void = class9;
        } else
        {
            class9 = class$java$lang$Void;
        }
        aclass9[1] = class9;
        aclass[8] = aclass9;
        for(int i = 0; i < aclass.length; i++)
        {
            BOX.put(aclass[i][0], aclass[i][1]);
            UNBOX.put(aclass[i][1], aclass[i][0]);
        }

    }
}
