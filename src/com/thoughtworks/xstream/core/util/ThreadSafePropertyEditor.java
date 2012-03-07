// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.core.util;

// Referenced classes of package com.thoughtworks.xstream.core.util:
//            Pool

public class ThreadSafePropertyEditor
{

    public ThreadSafePropertyEditor(Class class1, int i, int j)
    {
        Class class2;
        if(class$java$beans$PropertyEditor == null)
        {
            class2 = _mthclass$("java.beans.PropertyEditor");
            class$java$beans$PropertyEditor = class2;
        } else
        {
            class2 = class$java$beans$PropertyEditor;
        }
        if(!class2.isAssignableFrom(class1))
        {
            StringBuffer stringbuffer = (new StringBuffer()).append(class1.getName()).append(" is not a ");
            Class class3;
            if(class$java$beans$PropertyEditor == null)
            {
                class3 = _mthclass$("java.beans.PropertyEditor");
                class$java$beans$PropertyEditor = class3;
            } else
            {
                class3 = class$java$beans$PropertyEditor;
            }
            throw new IllegalArgumentException(stringbuffer.append(class3.getName()).toString());
        } else
        {
            editorType = class1;
            pool = new Pool(i, j, new _cls1());
            return;
        }
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

    private PropertyEditor fetchFromPool()
    {
        return (PropertyEditor)pool.fetchFromPool();
    }

    public String getAsText(Object obj)
    {
        PropertyEditor propertyeditor = fetchFromPool();
        String s;
        propertyeditor.setValue(obj);
        s = propertyeditor.getAsText();
        pool.putInPool(propertyeditor);
        return s;
        Exception exception;
        exception;
        pool.putInPool(propertyeditor);
        throw exception;
    }

    public Object setAsText(String s)
    {
        PropertyEditor propertyeditor = fetchFromPool();
        Object obj;
        propertyeditor.setAsText(s);
        obj = propertyeditor.getValue();
        pool.putInPool(propertyeditor);
        return obj;
        Exception exception;
        exception;
        pool.putInPool(propertyeditor);
        throw exception;
    }

    static Class class$java$beans$PropertyEditor;
    private final Class editorType;
    private final Pool pool;


    private class _cls1
        implements Pool.Factory
    {

        @Override
		public Object newInstance()
        {
            Object obj;
            try
            {
                obj = editorType.newInstance();
            }
            catch(InstantiationException instantiationexception)
            {
                throw new ObjectAccessException("Could not call default constructor of " + editorType.getName(), instantiationexception);
            }
            catch(IllegalAccessException illegalaccessexception)
            {
                throw new ObjectAccessException("Could not call default constructor of " + editorType.getName(), illegalaccessexception);
            }
            return obj;
        }

        private final ThreadSafePropertyEditor this$0;

        _cls1()
        {
            this$0 = ThreadSafePropertyEditor.this;
        }
    }

}
