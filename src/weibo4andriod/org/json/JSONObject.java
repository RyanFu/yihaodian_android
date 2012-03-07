// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package weibo4andriod.org.json;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

// Referenced classes of package weibo4andriod.org.json:
//            JSONException, JSONTokener, JSONArray, JSONString

public class JSONObject
{
    private static final class Null
    {

        protected final Object clone()
        {
            return this;
        }

        public boolean equals(Object obj)
        {
            boolean flag;
            if(obj == null || obj == this)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public String toString()
        {
            return "null";
        }

        private Null()
        {
        }

    }


    public JSONObject()
    {
        map = new HashMap();
    }

    public JSONObject(Object obj)
    {
        this();
        populateInternalMap(obj, false);
    }

    public JSONObject(Object obj, boolean flag)
    {
        this();
        populateInternalMap(obj, flag);
    }

    public JSONObject(Object obj, String as[])
    {
        this();
        Class class1 = obj.getClass();
        int i = 0;
        while(i < as.length) 
        {
            String s = as[i];
            try
            {
                putOpt(s, class1.getField(s).get(obj));
            }
            catch(Exception exception) { }
            i++;
        }
    }

    public JSONObject(String s)
        throws JSONException
    {
        this(new JSONTokener(s));
    }

    public JSONObject(Map map1)
    {
        Object obj;
        if(map1 == null)
            obj = new HashMap();
        else
            obj = map1;
        map = ((Map) (obj));
    }

    public JSONObject(Map map1, boolean flag)
    {
        map = new HashMap();
        if(map1 != null)
        {
            java.util.Map.Entry entry;
            for(Iterator iterator = map1.entrySet().iterator(); iterator.hasNext(); map.put(entry.getKey(), new JSONObject(entry.getValue(), flag)))
                entry = (java.util.Map.Entry)iterator.next();

        }
    }

    public JSONObject(JSONObject jsonobject, String as[])
        throws JSONException
    {
        this();
        for(int i = 0; i < as.length; i++)
            putOnce(as[i], jsonobject.opt(as[i]));

    }

    public JSONObject(JSONTokener jsontokener)
        throws JSONException
    {
        this();
        if(jsontokener.nextClean() != '{')
            throw jsontokener.syntaxError("A JSONObject text must begin with '{'");
          goto _L1
_L11:
        jsontokener.back();
_L1:
        jsontokener.nextClean();
        JVM INSTR lookupswitch 2: default 56
    //                   0: 147
    //                   125: 176;
           goto _L2 _L3 _L4
_L2:
        String s;
        char c;
        jsontokener.back();
        s = jsontokener.nextValue().toString();
        c = jsontokener.nextClean();
        if(c != '=') goto _L6; else goto _L5
_L5:
        if(jsontokener.next() != '>')
            jsontokener.back();
_L10:
        putOnce(s, jsontokener.nextValue());
        jsontokener.nextClean();
        JVM INSTR lookupswitch 3: default 140
    //                   44: 167
    //                   59: 167
    //                   125: 176;
           goto _L7 _L8 _L8 _L4
_L7:
        throw jsontokener.syntaxError("Expected a ',' or '}'");
_L3:
        throw jsontokener.syntaxError("A JSONObject text must end with '}'");
_L6:
        if(c == ':') goto _L10; else goto _L9
_L9:
        throw jsontokener.syntaxError("Expected a ':' after a key");
_L8:
        if(jsontokener.nextClean() != '}') goto _L11; else goto _L4
_L4:
    }

    public static String doubleToString(double d)
    {
        if(!Double.isInfinite(d) && !Double.isNaN(d)) goto _L2; else goto _L1
_L1:
        String s = "null";
_L4:
        return s;
_L2:
        s = Double.toString(d);
        if(s.indexOf('.') > 0 && s.indexOf('e') < 0 && s.indexOf('E') < 0)
        {
            for(; s.endsWith("0"); s = s.substring(0, s.length() - 1));
            if(s.endsWith("."))
                s = s.substring(0, s.length() - 1);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static String[] getNames(Object obj)
    {
        String as1[];
        if(obj == null)
        {
            as1 = null;
        } else
        {
            Field afield[] = obj.getClass().getFields();
            int i = afield.length;
            if(i == 0)
            {
                as1 = null;
            } else
            {
                String as[] = new String[i];
                for(int j = 0; j < i; j++)
                    as[j] = afield[j].getName();

                as1 = as;
            }
        }
        return as1;
    }

    public static String[] getNames(JSONObject jsonobject)
    {
        int i = jsonobject.length();
        String as[];
        if(i == 0)
        {
            as = null;
        } else
        {
            Iterator iterator = jsonobject.keys();
            as = new String[i];
            int j = 0;
            while(iterator.hasNext()) 
            {
                as[j] = (String)iterator.next();
                j++;
            }
        }
        return as;
    }

    private boolean isStandardProperty(Class class1)
    {
        boolean flag;
        if(class1.isPrimitive() || class1.isAssignableFrom(java/lang/Byte) || class1.isAssignableFrom(java/lang/Short) || class1.isAssignableFrom(java/lang/Integer) || class1.isAssignableFrom(java/lang/Long) || class1.isAssignableFrom(java/lang/Float) || class1.isAssignableFrom(java/lang/Double) || class1.isAssignableFrom(java/lang/Character) || class1.isAssignableFrom(java/lang/String) || class1.isAssignableFrom(java/lang/Boolean))
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static String numberToString(Number number)
        throws JSONException
    {
        if(number == null)
            throw new JSONException("Null pointer");
        testValidity(number);
        String s = number.toString();
        if(s.indexOf('.') > 0 && s.indexOf('e') < 0 && s.indexOf('E') < 0)
        {
            for(; s.endsWith("0"); s = s.substring(0, s.length() - 1));
            if(s.endsWith("."))
                s = s.substring(0, s.length() - 1);
        }
        return s;
    }

    private void populateInternalMap(Object obj, boolean flag)
    {
        int i = 0;
        Class class1 = obj.getClass();
        boolean flag1;
        Method amethod[];
        Exception exception;
        Method method;
        String s;
        String s1;
        String s2;
        Object obj1;
        if(class1.getClassLoader() == null)
            flag1 = false;
        else
            flag1 = flag;
        if(flag1)
            amethod = class1.getMethods();
        else
            amethod = class1.getDeclaredMethods();
        if(i >= amethod.length) goto _L2; else goto _L1
_L1:
        method = amethod[i];
        s = method.getName();
        if(!s.startsWith("get")) goto _L4; else goto _L3
_L3:
        s1 = s.substring(3);
_L11:
        if(s1.length() <= 0 || !Character.isUpperCase(s1.charAt(0)) || method.getParameterTypes().length != 0) goto _L6; else goto _L5
_L5:
        if(s1.length() != 1) goto _L8; else goto _L7
_L7:
        s2 = s1.toLowerCase();
_L14:
        obj1 = method.invoke(obj, (Object[])null);
        if(obj1 != null) goto _L10; else goto _L9
_L9:
        map.put(s2, NULL);
_L6:
        i++;
        break MISSING_BLOCK_LABEL_31;
_L4:
        if(!s.startsWith("is"))
            break MISSING_BLOCK_LABEL_470;
        s1 = s.substring(2);
          goto _L11
_L8:
        if(Character.isUpperCase(s1.charAt(1))) goto _L13; else goto _L12
_L12:
        s2 = (new StringBuilder()).append(s1.substring(0, 1).toLowerCase()).append(s1.substring(1)).toString();
          goto _L14
_L10:
        if(!obj1.getClass().isArray()) goto _L16; else goto _L15
_L15:
        map.put(s2, new JSONArray(obj1, flag1));
          goto _L6
_L16:
        try
        {
            if(obj1 instanceof Collection)
                map.put(s2, new JSONArray((Collection)obj1, flag1));
            else
            if(obj1 instanceof Map)
                map.put(s2, new JSONObject((Map)obj1, flag1));
            else
            if(isStandardProperty(obj1.getClass()))
                map.put(s2, obj1);
            else
            if(obj1.getClass().getPackage().getName().startsWith("java") || obj1.getClass().getClassLoader() == null)
                map.put(s2, obj1.toString());
            else
                map.put(s2, new JSONObject(obj1, flag1));
        }
        // Misplaced declaration of an exception variable
        catch(Exception exception)
        {
            throw new RuntimeException(exception);
        }
          goto _L6
_L2:
        return;
_L13:
        s2 = s1;
          goto _L14
        s1 = "";
          goto _L11
    }

    public static String quote(String s)
    {
        char c = '\0';
        if(s != null && s.length() != 0) goto _L2; else goto _L1
_L1:
        String s1 = "\"\"";
_L4:
        return s1;
_L2:
        StringBuffer stringbuffer;
label0:
        {
label1:
            {
label2:
                {
label3:
                    {
label4:
                        {
label5:
                            {
label6:
                                {
                                    int i = s.length();
                                    stringbuffer = new StringBuffer(i + 4);
                                    stringbuffer.append('"');
                                    int j = 0;
label7:
                                    do
                                    {
                                        {
                                            if(j >= i)
                                                break label0;
                                            char c1 = s.charAt(j);
                                            switch(c1)
                                            {
                                            default:
                                                if(c1 < ' ' || c1 >= '\200' && c1 < '\240' || c1 >= '\u2000' && c1 < '\u2100')
                                                {
                                                    String s2 = (new StringBuilder()).append("000").append(Integer.toHexString(c1)).toString();
                                                    stringbuffer.append((new StringBuilder()).append("\\u").append(s2.substring(s2.length() - 4)).toString());
                                                } else
                                                {
                                                    stringbuffer.append(c1);
                                                }
                                                break;

                                            case 8: // '\b'
                                                break label5;

                                            case 9: // '\t'
                                                break label4;

                                            case 10: // '\n'
                                                break label3;

                                            case 12: // '\f'
                                                break label2;

                                            case 13: // '\r'
                                                break label1;

                                            case 34: // '"'
                                            case 92: // '\\'
                                                break label7;

                                            case 47: // '/'
                                                break label6;
                                            }
                                        }
                                        j++;
                                        c = c1;
                                    } while(true);
                                    stringbuffer.append('\\');
                                    stringbuffer.append(c1);
                                    break MISSING_BLOCK_LABEL_238;
                                }
                                if(c == '<')
                                    stringbuffer.append('\\');
                                stringbuffer.append(c1);
                                break MISSING_BLOCK_LABEL_238;
                            }
                            stringbuffer.append("\\b");
                            break MISSING_BLOCK_LABEL_238;
                        }
                        stringbuffer.append("\\t");
                        break MISSING_BLOCK_LABEL_238;
                    }
                    stringbuffer.append("\\n");
                    break MISSING_BLOCK_LABEL_238;
                }
                stringbuffer.append("\\f");
                break MISSING_BLOCK_LABEL_238;
            }
            stringbuffer.append("\\r");
            break MISSING_BLOCK_LABEL_238;
        }
        stringbuffer.append('"');
        s1 = stringbuffer.toString();
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static Object stringToValue(String s)
    {
        Object obj;
        if(s.equals(""))
            obj = s;
        else
        if(s.equalsIgnoreCase("true"))
            obj = Boolean.TRUE;
        else
        if(s.equalsIgnoreCase("false"))
        {
            obj = Boolean.FALSE;
        } else
        {
label0:
            {
                if(!s.equalsIgnoreCase("null"))
                    break label0;
                obj = NULL;
            }
        }
_L7:
        return obj;
        char c = s.charAt(0);
        if((c < '0' || c > '9') && c != '.' && c != '-' && c != '+') goto _L2; else goto _L1
_L1:
        if(c != '0') goto _L4; else goto _L3
_L3:
        if(s.length() <= 2 || s.charAt(1) != 'x' && s.charAt(1) != 'X') goto _L6; else goto _L5
_L5:
        obj = new Integer(Integer.parseInt(s.substring(2), 16));
          goto _L7
        Exception exception4;
        exception4;
_L4:
        obj = new Integer(s);
          goto _L7
        Exception exception;
        exception;
        obj = new Long(s);
          goto _L7
        Exception exception1;
        exception1;
        obj = new Double(s);
          goto _L7
        Exception exception2;
        exception2;
_L2:
        obj = s;
          goto _L7
_L6:
        obj = new Integer(Integer.parseInt(s, 8));
          goto _L7
        Exception exception3;
        exception3;
          goto _L4
    }

    static void testValidity(Object obj)
        throws JSONException
    {
        if(obj != null)
            if(obj instanceof Double)
            {
                if(((Double)obj).isInfinite() || ((Double)obj).isNaN())
                    throw new JSONException("JSON does not allow non-finite numbers.");
            } else
            if((obj instanceof Float) && (((Float)obj).isInfinite() || ((Float)obj).isNaN()))
                throw new JSONException("JSON does not allow non-finite numbers.");
    }

    static String valueToString(Object obj)
        throws JSONException
    {
        String s;
        if(obj == null || obj.equals(null))
            s = "null";
        else
        if(obj instanceof JSONString)
        {
            String s1;
            try
            {
                s1 = ((JSONString)obj).toJSONString();
            }
            catch(Exception exception)
            {
                throw new JSONException(exception);
            }
            if(s1 instanceof String)
                s = (String)s1;
            else
                throw new JSONException((new StringBuilder()).append("Bad value from toJSONString: ").append(s1).toString());
        } else
        if(obj instanceof Number)
            s = numberToString((Number)obj);
        else
        if((obj instanceof Boolean) || (obj instanceof JSONObject) || (obj instanceof JSONArray))
            s = obj.toString();
        else
        if(obj instanceof Map)
            s = (new JSONObject((Map)obj)).toString();
        else
        if(obj instanceof Collection)
            s = (new JSONArray((Collection)obj)).toString();
        else
        if(obj.getClass().isArray())
            s = (new JSONArray(obj)).toString();
        else
            s = quote(obj.toString());
        return s;
    }

    static String valueToString(Object obj, int i, int j)
        throws JSONException
    {
        if(obj != null && !obj.equals(null)) goto _L2; else goto _L1
_L1:
        String s = "null";
_L4:
        return s;
_L2:
        String s2;
        if(!(obj instanceof JSONString))
            break MISSING_BLOCK_LABEL_58;
        String s1 = ((JSONString)obj).toJSONString();
        if(!(s1 instanceof String))
            break MISSING_BLOCK_LABEL_58;
        s2 = (String)s1;
        s = s2;
        continue; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        if(obj instanceof Number)
            s = numberToString((Number)obj);
        else
        if(obj instanceof Boolean)
            s = obj.toString();
        else
        if(obj instanceof JSONObject)
            s = ((JSONObject)obj).toString(i, j);
        else
        if(obj instanceof JSONArray)
            s = ((JSONArray)obj).toString(i, j);
        else
        if(obj instanceof Map)
            s = (new JSONObject((Map)obj)).toString(i, j);
        else
        if(obj instanceof Collection)
            s = (new JSONArray((Collection)obj)).toString(i, j);
        else
        if(obj.getClass().isArray())
            s = (new JSONArray(obj)).toString(i, j);
        else
            s = quote(obj.toString());
        if(true) goto _L4; else goto _L3
_L3:
    }

    public JSONObject accumulate(String s, Object obj)
        throws JSONException
    {
        testValidity(obj);
        Object obj1 = opt(s);
        if(obj1 == null)
        {
            Object obj2;
            if(obj instanceof JSONArray)
                obj2 = (new JSONArray()).put(obj);
            else
                obj2 = obj;
            put(s, obj2);
        } else
        if(obj1 instanceof JSONArray)
            ((JSONArray)obj1).put(obj);
        else
            put(s, (new JSONArray()).put(obj1).put(obj));
        return this;
    }

    public JSONObject append(String s, Object obj)
        throws JSONException
    {
        testValidity(obj);
        Object obj1 = opt(s);
        if(obj1 == null)
            put(s, (new JSONArray()).put(obj));
        else
        if(obj1 instanceof JSONArray)
            put(s, ((JSONArray)obj1).put(obj));
        else
            throw new JSONException((new StringBuilder()).append("JSONObject[").append(s).append("] is not a JSONArray.").toString());
        return this;
    }

    public Object get(String s)
        throws JSONException
    {
        return opt(s);
    }

    public boolean getBoolean(String s)
        throws JSONException
    {
        Object obj = get(s);
        boolean flag;
        if(obj == null)
            flag = false;
        else
        if(obj.equals(Boolean.FALSE) || (obj instanceof String) && ((String)obj).equalsIgnoreCase("false"))
            flag = false;
        else
        if(obj.equals(Boolean.TRUE) || (obj instanceof String) && ((String)obj).equalsIgnoreCase("true"))
            flag = true;
        else
            throw new JSONException((new StringBuilder()).append("JSONObject[").append(quote(s)).append("] is not a Boolean.").toString());
        return flag;
    }

    public double getDouble(String s)
        throws JSONException
    {
        Object obj = get(s);
        if(obj != null) goto _L2; else goto _L1
_L1:
        double d = 0.0D;
_L4:
        return d;
_L2:
label0:
        {
            double d1;
            try
            {
                if(obj instanceof Number)
                {
                    d = ((Number)obj).doubleValue();
                    continue; /* Loop/switch isn't completed */
                }
                if(obj.toString().length() <= 0)
                    break label0;
                d1 = Double.valueOf(obj.toString()).doubleValue();
            }
            catch(Exception exception)
            {
                throw new JSONException((new StringBuilder()).append("JSONObject[").append(quote(s)).append("] is not a number.").toString());
            }
            d = d1;
            continue; /* Loop/switch isn't completed */
        }
        d = 0.0D;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public int getInt(String s)
        throws JSONException
    {
        Object obj = get(s);
        int i;
        if(obj == null)
            i = 0;
        else
        if(obj instanceof Number)
            i = ((Number)obj).intValue();
        else
            i = (int)getDouble(s);
        return i;
    }

    public JSONArray getJSONArray(String s)
        throws JSONException
    {
        Object obj = get(s);
        JSONArray jsonarray;
        if(obj == null)
            jsonarray = null;
        else
        if(obj instanceof JSONArray)
            jsonarray = (JSONArray)obj;
        else
            throw new JSONException((new StringBuilder()).append("JSONObject[").append(quote(s)).append("] is not a JSONArray.").toString());
        return jsonarray;
    }

    public JSONObject getJSONObject(String s)
        throws JSONException
    {
        Object obj = get(s);
        JSONObject jsonobject;
        if(obj == null)
            jsonobject = null;
        else
        if(obj instanceof JSONObject)
            jsonobject = (JSONObject)obj;
        else
            throw new JSONException((new StringBuilder()).append("JSONObject[").append(quote(s)).append("] is not a JSONObject.").toString());
        return jsonobject;
    }

    public long getLong(String s)
        throws JSONException
    {
        Object obj = get(s);
        long l;
        if(obj == null)
            l = 0L;
        else
        if(obj instanceof String)
        {
            if(obj.toString().length() > 0)
                l = Long.valueOf(obj.toString()).longValue();
            else
                l = 0L;
        } else
        if(obj instanceof Number)
            l = ((Number)obj).longValue();
        else
            l = (long)getDouble(s);
        return l;
    }

    public String getString(String s)
        throws JSONException
    {
        Object obj = get(s);
        String s1;
        if(obj == null)
            s1 = "";
        else
            s1 = obj.toString();
        return s1;
    }

    public boolean has(String s)
    {
        return map.containsKey(s);
    }

    public boolean isNull(String s)
    {
        return NULL.equals(opt(s));
    }

    public Iterator keys()
    {
        return map.keySet().iterator();
    }

    public int length()
    {
        return map.size();
    }

    public JSONArray names()
    {
        JSONArray jsonarray = new JSONArray();
        for(Iterator iterator = keys(); iterator.hasNext(); jsonarray.put(iterator.next()));
        if(jsonarray.length() == 0)
            jsonarray = null;
        return jsonarray;
    }

    public Object opt(String s)
    {
        Object obj;
        if(s == null)
            obj = null;
        else
            obj = map.get(s);
        return obj;
    }

    public boolean optBoolean(String s)
    {
        return optBoolean(s, false);
    }

    public boolean optBoolean(String s, boolean flag)
    {
        boolean flag2 = getBoolean(s);
        boolean flag1 = flag2;
_L2:
        return flag1;
        Exception exception;
        exception;
        flag1 = flag;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public double optDouble(String s)
    {
        return optDouble(s, (0.0D / 0.0D));
    }

    public double optDouble(String s, double d)
    {
        double d1;
        double d2;
        Object obj = opt(s);
        if(obj instanceof Number)
        {
            d1 = ((Number)obj).doubleValue();
            break MISSING_BLOCK_LABEL_57;
        }
        d2 = (new Double((String)obj)).doubleValue();
        d1 = d2;
        break MISSING_BLOCK_LABEL_57;
        Exception exception;
        exception;
        d1 = d;
        return d1;
    }

    public int optInt(String s)
    {
        return optInt(s, 0);
    }

    public int optInt(String s, int i)
    {
        int k = getInt(s);
        int j = k;
_L2:
        return j;
        Exception exception;
        exception;
        j = i;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public JSONArray optJSONArray(String s)
    {
        Object obj = opt(s);
        JSONArray jsonarray;
        if(obj instanceof JSONArray)
            jsonarray = (JSONArray)obj;
        else
            jsonarray = null;
        return jsonarray;
    }

    public JSONObject optJSONObject(String s)
    {
        Object obj = opt(s);
        JSONObject jsonobject;
        if(obj instanceof JSONObject)
            jsonobject = (JSONObject)obj;
        else
            jsonobject = null;
        return jsonobject;
    }

    public long optLong(String s)
    {
        return optLong(s, 0L);
    }

    public long optLong(String s, long l)
    {
        long l2 = getLong(s);
        long l1 = l2;
_L2:
        return l1;
        Exception exception;
        exception;
        l1 = l;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public String optString(String s)
    {
        return optString(s, "");
    }

    public String optString(String s, String s1)
    {
        Object obj = opt(s);
        String s2;
        if(obj != null)
            s2 = obj.toString();
        else
            s2 = s1;
        return s2;
    }

    public JSONObject put(String s, double d)
        throws JSONException
    {
        put(s, new Double(d));
        return this;
    }

    public JSONObject put(String s, int i)
        throws JSONException
    {
        put(s, new Integer(i));
        return this;
    }

    public JSONObject put(String s, long l)
        throws JSONException
    {
        put(s, new Long(l));
        return this;
    }

    public JSONObject put(String s, Object obj)
        throws JSONException
    {
        if(s == null)
            throw new JSONException("Null key.");
        if(obj != null)
        {
            testValidity(obj);
            map.put(s, obj);
        } else
        {
            remove(s);
        }
        return this;
    }

    public JSONObject put(String s, Collection collection)
        throws JSONException
    {
        put(s, new JSONArray(collection));
        return this;
    }

    public JSONObject put(String s, Map map1)
        throws JSONException
    {
        put(s, new JSONObject(map1));
        return this;
    }

    public JSONObject put(String s, boolean flag)
        throws JSONException
    {
        Boolean boolean1;
        if(flag)
            boolean1 = Boolean.TRUE;
        else
            boolean1 = Boolean.FALSE;
        put(s, boolean1);
        return this;
    }

    public JSONObject putOnce(String s, Object obj)
        throws JSONException
    {
        if(s != null && obj != null)
        {
            if(opt(s) != null)
                throw new JSONException((new StringBuilder()).append("Duplicate key \"").append(s).append("\"").toString());
            put(s, obj);
        }
        return this;
    }

    public JSONObject putOpt(String s, Object obj)
        throws JSONException
    {
        if(s != null && obj != null)
            put(s, obj);
        return this;
    }

    public Object remove(String s)
    {
        return map.remove(s);
    }

    public Iterator sortedKeys()
    {
        return (new TreeSet(map.keySet())).iterator();
    }

    public JSONArray toJSONArray(JSONArray jsonarray)
        throws JSONException
    {
        JSONArray jsonarray1;
        if(jsonarray == null || jsonarray.length() == 0)
        {
            jsonarray1 = null;
        } else
        {
            jsonarray1 = new JSONArray();
            int i = 0;
            while(i < jsonarray.length()) 
            {
                jsonarray1.put(opt(jsonarray.getString(i)));
                i++;
            }
        }
        return jsonarray1;
    }

    public String toString()
    {
        String s1;
        Iterator iterator = keys();
        StringBuffer stringbuffer = new StringBuffer("{");
        Object obj;
        for(; iterator.hasNext(); stringbuffer.append(valueToString(map.get(obj))))
        {
            if(stringbuffer.length() > 1)
                stringbuffer.append(',');
            obj = iterator.next();
            stringbuffer.append(quote(obj.toString()));
            stringbuffer.append(':');
        }

        stringbuffer.append('}');
        s1 = stringbuffer.toString();
        String s;
        s = s1;
        break MISSING_BLOCK_LABEL_120;
        Exception exception;
        exception;
        s = null;
        return s;
    }

    public String toString(int i)
        throws JSONException
    {
        return toString(i, 0);
    }

    String toString(int i, int j)
        throws JSONException
    {
        int k = length();
        if(k != 0) goto _L2; else goto _L1
_L1:
        String s = "{}";
_L4:
        return s;
_L2:
        Iterator iterator;
        StringBuffer stringbuffer;
        int l;
        iterator = sortedKeys();
        stringbuffer = new StringBuffer("{");
        l = j + i;
        if(k != 1)
            break; /* Loop/switch isn't completed */
        Object obj1 = iterator.next();
        stringbuffer.append(quote(obj1.toString()));
        stringbuffer.append(": ");
        stringbuffer.append(valueToString(map.get(obj1), i, j));
_L8:
        stringbuffer.append('}');
        s = stringbuffer.toString();
        if(true) goto _L4; else goto _L3
_L6:
        Object obj;
        stringbuffer.append(quote(obj.toString()));
        stringbuffer.append(": ");
        stringbuffer.append(valueToString(map.get(obj), i, l));
_L3:
        if(!iterator.hasNext())
            break; /* Loop/switch isn't completed */
        obj = iterator.next();
        int j1;
        if(stringbuffer.length() > 1)
            stringbuffer.append(",\n");
        else
            stringbuffer.append('\n');
        j1 = 0;
        while(j1 < l) 
        {
            stringbuffer.append(' ');
            j1++;
        }
        if(true) goto _L6; else goto _L5
_L5:
        if(stringbuffer.length() <= 1) goto _L8; else goto _L7
_L7:
        stringbuffer.append('\n');
        int i1 = 0;
        while(i1 < j) 
        {
            stringbuffer.append(' ');
            i1++;
        }
          goto _L8
    }

    public Writer write(Writer writer)
        throws JSONException
    {
        boolean flag = false;
        Iterator iterator;
        iterator = keys();
        writer.write(123);
_L2:
        Object obj1;
        if(iterator.hasNext())
        {
            if(flag)
                writer.write(44);
            Object obj = iterator.next();
            writer.write(quote(obj.toString()));
            writer.write(58);
            obj1 = map.get(obj);
            if(obj1 instanceof JSONObject)
            {
                ((JSONObject)obj1).write(writer);
                break MISSING_BLOCK_LABEL_146;
            }
            if(obj1 instanceof JSONArray)
            {
                ((JSONArray)obj1).write(writer);
                break MISSING_BLOCK_LABEL_146;
            }
            break MISSING_BLOCK_LABEL_126;
        }
        break MISSING_BLOCK_LABEL_138;
        IOException ioexception;
        ioexception;
        throw new JSONException(ioexception);
        writer.write(valueToString(obj1));
        break MISSING_BLOCK_LABEL_146;
        writer.write(125);
        return writer;
        flag = true;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static final Object NULL = new Null();
    private Map map;

}
