// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.core;

import com.thoughtworks.xstream.converters.reflection.PureJavaReflectionProvider;
import com.thoughtworks.xstream.converters.reflection.ReflectionProvider;
import java.io.PrintStream;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.security.AccessControlException;
import java.util.HashMap;
import java.util.Map;

public class JVM
{

    public JVM()
    {
        loaderCache = new HashMap();
        boolean flag;
        boolean flag1;
        boolean flag2;
        if(loadClass("java.awt.Color") != null)
            flag = true;
        else
            flag = false;
        supportsAWT = flag;
        if(loadClass("javax.swing.LookAndFeel") != null)
            flag1 = true;
        else
            flag1 = false;
        supportsSwing = flag1;
        if(loadClass("java.sql.Date") != null)
            flag2 = true;
        else
            flag2 = false;
        supportsSQL = flag2;
    }

    private boolean canUseHarmonyReflectionProvider()
    {
        return isHarmony();
    }

    private boolean canUseSun14ReflectionProvider()
    {
        boolean flag;
        if((isSun() || isApple() || isHPUX() || isIBM() || isBlackdown() || isBEAWithUnsafeSupport() || isHitachi() || isSAP() || isDiablo()) && is14() && loadClass("sun.misc.Unsafe") != null)
            flag = true;
        else
            flag = false;
        return flag;
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

    private static final float getMajorJavaVersion()
    {
        float f1 = Float.parseFloat(System.getProperty("java.specification.version"));
        float f = f1;
_L2:
        return f;
        NumberFormatException numberformatexception;
        numberformatexception;
        f = 1.3F;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static boolean is14()
    {
        boolean flag;
        if(majorJavaVersion >= 1.4F)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static boolean is15()
    {
        boolean flag;
        if(majorJavaVersion >= 1.5F)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static boolean is16()
    {
        boolean flag;
        if(majorJavaVersion >= 1.6F)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private static boolean isApple()
    {
        boolean flag;
        if(vendor.indexOf("Apple") != -1)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private static boolean isBEAWithUnsafeSupport()
    {
        if(vendor.indexOf("BEA") == -1) goto _L2; else goto _L1
_L1:
        if(!System.getProperty("java.vm.version").startsWith("R")) goto _L4; else goto _L3
_L3:
        boolean flag = true;
_L6:
        return flag;
_L4:
        String s = System.getProperty("java.vm.info");
        if(s != null)
        {
            if(s.startsWith("R25.1") || s.startsWith("R25.2"))
                flag = true;
            else
                flag = false;
            continue; /* Loop/switch isn't completed */
        }
_L2:
        flag = false;
        if(true) goto _L6; else goto _L5
_L5:
    }

    private static boolean isBlackdown()
    {
        boolean flag;
        if(vendor.indexOf("Blackdown") != -1)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private static boolean isDiablo()
    {
        boolean flag;
        if(vendor.indexOf("FreeBSD Foundation") != -1)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private static boolean isHPUX()
    {
        boolean flag;
        if(vendor.indexOf("Hewlett-Packard Company") != -1)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private static boolean isHarmony()
    {
        boolean flag;
        if(vendor.indexOf("Apache Software Foundation") != -1)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private static boolean isHitachi()
    {
        boolean flag;
        if(vendor.indexOf("Hitachi") != -1)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private static boolean isIBM()
    {
        boolean flag;
        if(vendor.indexOf("IBM") != -1)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private static boolean isSAP()
    {
        boolean flag;
        if(vendor.indexOf("SAP AG") != -1)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private static boolean isSun()
    {
        boolean flag;
        if(vendor.indexOf("Sun") != -1)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static void main(String args[])
    {
        boolean flag;
        int i;
        boolean flag1;
        int j;
        flag = false;
        Class class1;
        Field afield[];
        JVM jvm;
        PrintStream printstream;
        StringBuffer stringbuffer;
        Field afield1[];
        if(class$java$text$AttributedString == null)
        {
            class1 = _mthclass$("java.text.AttributedString");
            class$java$text$AttributedString = class1;
        } else
        {
            class1 = class$java$text$AttributedString;
        }
        afield = class1.getDeclaredFields();
        i = 0;
_L7:
        if(i >= afield.length) goto _L2; else goto _L1
_L1:
        if(!afield[i].getName().equals("text")) goto _L4; else goto _L3
_L3:
        if(i > 3)
            flag = true;
        else
            flag = false;
_L2:
        if(!flag) goto _L6; else goto _L5
_L5:
        Class class2;
        if(class$com$thoughtworks$xstream$core$JVM == null)
        {
            class2 = _mthclass$("com.thoughtworks.xstream.core.JVM");
            class$com$thoughtworks$xstream$core$JVM = class2;
        } else
        {
            class2 = class$com$thoughtworks$xstream$core$JVM;
        }
        afield1 = class2.getDeclaredFields();
        j = 0;
_L8:
        if(j < afield1.length)
        {
            if(!afield1[j].getName().equals("reflectionProvider"))
                break MISSING_BLOCK_LABEL_419;
            if(j > 2)
                flag = true;
            else
                flag = false;
        }
_L6:
        jvm = new JVM();
        System.out.println("XStream JVM diagnostics");
        System.out.println("java.specification.version: " + System.getProperty("java.specification.version"));
        System.out.println("java.vm.vendor: " + vendor);
        System.out.println("Version: " + majorJavaVersion);
        printstream = System.out;
        stringbuffer = (new StringBuffer()).append("XStream support for enhanced Mode: ");
        if(jvm.canUseSun14ReflectionProvider() || jvm.canUseHarmonyReflectionProvider())
            flag1 = true;
        else
            flag1 = false;
        printstream.println(stringbuffer.append(flag1).toString());
        System.out.println("Supports AWT: " + jvm.supportsAWT());
        System.out.println("Supports Swing: " + jvm.supportsSwing());
        System.out.println("Supports SQL: " + jvm.supportsSQL());
        System.out.println("Reverse field order detected (may have failed): " + flag);
        return;
_L4:
        i++;
          goto _L7
        j++;
          goto _L8
    }

    private Object readResolve()
    {
        loaderCache = new HashMap();
        return this;
    }

    public static boolean reverseFieldDefinition()
    {
        return reverseFieldOrder;
    }

    /**
     * @deprecated Method bestReflectionProvider is deprecated
     */

    public ReflectionProvider bestReflectionProvider()
    {
        this;
        JVM INSTR monitorenter ;
        ReflectionProvider reflectionprovider = reflectionProvider;
        if(reflectionprovider != null) goto _L2; else goto _L1
_L1:
        if(!canUseSun14ReflectionProvider()) goto _L4; else goto _L3
_L3:
        reflectionProvider = (ReflectionProvider)loadClass("com.thoughtworks.xstream.converters.reflection.Sun14ReflectionProvider").newInstance();
_L6:
        if(reflectionProvider == null)
            reflectionProvider = new PureJavaReflectionProvider();
_L2:
        ReflectionProvider reflectionprovider1 = reflectionProvider;
        this;
        JVM INSTR monitorexit ;
        return reflectionprovider1;
_L4:
        if(!canUseHarmonyReflectionProvider()) goto _L6; else goto _L5
_L5:
        reflectionProvider = (ReflectionProvider)loadClass("com.thoughtworks.xstream.converters.reflection.HarmonyReflectionProvider").newInstance();
          goto _L6
        InstantiationException instantiationexception;
        instantiationexception;
        reflectionProvider = new PureJavaReflectionProvider();
          goto _L2
        Exception exception;
        exception;
        throw exception;
        IllegalAccessException illegalaccessexception;
        illegalaccessexception;
        reflectionProvider = new PureJavaReflectionProvider();
          goto _L2
        AccessControlException accesscontrolexception;
        accesscontrolexception;
        reflectionProvider = new PureJavaReflectionProvider();
          goto _L2
    }

    public Class loadClass(String s)
    {
        Class class1;
        Class class2;
        WeakReference weakreference = (WeakReference)loaderCache.get(s);
        if(weakreference != null)
        {
            Class class3 = (Class)weakreference.get();
            if(class3 != null)
            {
                class1 = class3;
                break MISSING_BLOCK_LABEL_84;
            }
        }
        class2 = Class.forName(s, false, getClass().getClassLoader());
        loaderCache.put(s, new WeakReference(class2));
        class1 = class2;
        break MISSING_BLOCK_LABEL_84;
        ClassNotFoundException classnotfoundexception;
        classnotfoundexception;
        class1 = null;
        return class1;
    }

    public boolean supportsAWT()
    {
        return supportsAWT;
    }

    public boolean supportsSQL()
    {
        return supportsSQL;
    }

    public boolean supportsSwing()
    {
        return supportsSwing;
    }

    static final float DEFAULT_JAVA_VERSION = 1.3F;
    static Class class$com$thoughtworks$xstream$core$JVM;
    static Class class$java$text$AttributedString;
    private static final float majorJavaVersion = getMajorJavaVersion();
    private static final boolean reverseFieldOrder;
    private static final String vendor = System.getProperty("java.vm.vendor");
    private transient Map loaderCache;
    private ReflectionProvider reflectionProvider;
    private final boolean supportsAWT;
    private final boolean supportsSQL;
    private final boolean supportsSwing;

    static 
    {
        boolean flag;
        if(isHarmony() || isIBM() && !is15())
            flag = true;
        else
            flag = false;
        reverseFieldOrder = flag;
    }
}
