// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.protocol;

import java.io.IOException;
import java.lang.reflect.*;
import java.net.*;
import org.apache.commons.httpclient.ConnectTimeoutException;

public final class ReflectionSocketFactory
{

    private ReflectionSocketFactory()
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

    public static Socket createSocket(String s, String s1, int i, InetAddress inetaddress, int j, int k)
        throws IOException, UnknownHostException, ConnectTimeoutException
    {
        if(!REFLECTION_FAILED) goto _L2; else goto _L1
_L1:
        Socket socket = null;
_L4:
        return socket;
_L2:
        try
        {
            Class class1 = Class.forName(s);
            Object obj = class1.getMethod("getDefault", new Class[0]).invoke(null, new Object[0]);
            Socket socket1 = (Socket)class1.getMethod("createSocket", new Class[0]).invoke(obj, new Object[0]);
            if(INETSOCKETADDRESS_CONSTRUCTOR == null)
            {
                Class class4 = Class.forName("java.net.InetSocketAddress");
                Class aclass2[] = new Class[2];
                Constructor constructor;
                Object aobj[];
                Object obj1;
                Constructor constructor1;
                Object aobj1[];
                Object obj2;
                Method method;
                Object aobj2[];
                Method method1;
                Object aobj3[];
                Class aclass[];
                Class aclass1[];
                Class class5;
                if(class$java$net$InetAddress == null)
                {
                    class5 = _mthclass$("java.net.InetAddress");
                    class$java$net$InetAddress = class5;
                } else
                {
                    class5 = class$java$net$InetAddress;
                }
                aclass2[0] = class5;
                aclass2[1] = Integer.TYPE;
                INETSOCKETADDRESS_CONSTRUCTOR = class4.getConstructor(aclass2);
            }
            constructor = INETSOCKETADDRESS_CONSTRUCTOR;
            aobj = new Object[2];
            aobj[0] = InetAddress.getByName(s1);
            aobj[1] = new Integer(i);
            obj1 = constructor.newInstance(aobj);
            constructor1 = INETSOCKETADDRESS_CONSTRUCTOR;
            aobj1 = new Object[2];
            aobj1[0] = inetaddress;
            aobj1[1] = new Integer(j);
            obj2 = constructor1.newInstance(aobj1);
            if(SOCKETCONNECT_METHOD == null)
            {
                Class class3;
                if(class$java$net$Socket == null)
                {
                    class3 = _mthclass$("java.net.Socket");
                    class$java$net$Socket = class3;
                } else
                {
                    class3 = class$java$net$Socket;
                }
                aclass1 = new Class[2];
                aclass1[0] = Class.forName("java.net.SocketAddress");
                aclass1[1] = Integer.TYPE;
                SOCKETCONNECT_METHOD = class3.getMethod("connect", aclass1);
            }
            if(SOCKETBIND_METHOD == null)
            {
                Class class2;
                if(class$java$net$Socket == null)
                {
                    class2 = _mthclass$("java.net.Socket");
                    class$java$net$Socket = class2;
                } else
                {
                    class2 = class$java$net$Socket;
                }
                aclass = new Class[1];
                aclass[0] = Class.forName("java.net.SocketAddress");
                SOCKETBIND_METHOD = class2.getMethod("bind", aclass);
            }
            method = SOCKETBIND_METHOD;
            aobj2 = new Object[1];
            aobj2[0] = obj2;
            method.invoke(socket1, aobj2);
            method1 = SOCKETCONNECT_METHOD;
            aobj3 = new Object[2];
            aobj3[0] = obj1;
            aobj3[1] = new Integer(k);
            method1.invoke(socket1, aobj3);
            socket = socket1;
            continue; /* Loop/switch isn't completed */
        }
        catch(InvocationTargetException invocationtargetexception)
        {
            Throwable throwable = invocationtargetexception.getTargetException();
            if(SOCKETTIMEOUTEXCEPTION_CLASS == null)
                try
                {
                    SOCKETTIMEOUTEXCEPTION_CLASS = Class.forName("java.net.SocketTimeoutException");
                }
                catch(ClassNotFoundException classnotfoundexception)
                {
                    REFLECTION_FAILED = true;
                    socket = null;
                    continue; /* Loop/switch isn't completed */
                }
            if(SOCKETTIMEOUTEXCEPTION_CLASS.isInstance(throwable))
                throw new ConnectTimeoutException("The host did not accept the connection within timeout of " + k + " ms", throwable);
            if(throwable instanceof IOException)
                throw (IOException)throwable;
            socket = null;
        }
        catch(Exception exception)
        {
            REFLECTION_FAILED = true;
            socket = null;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static Constructor INETSOCKETADDRESS_CONSTRUCTOR = null;
    private static boolean REFLECTION_FAILED = false;
    private static Method SOCKETBIND_METHOD = null;
    private static Method SOCKETCONNECT_METHOD = null;
    private static Class SOCKETTIMEOUTEXCEPTION_CLASS = null;
    static Class class$java$net$InetAddress;
    static Class class$java$net$Socket;

}
