// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package weibo4andriod.org.json;

import java.io.*;

// Referenced classes of package weibo4andriod.org.json:
//            JSONException, JSONObject, JSONArray

public class JSONTokener
{

    public JSONTokener(Reader reader1)
    {
        Object obj;
        if(reader1.markSupported())
            obj = reader1;
        else
            obj = new BufferedReader(reader1);
        reader = ((Reader) (obj));
        useLastChar = false;
        index = 0;
    }

    public JSONTokener(String s)
    {
        this(((new StringReader(s))));
    }

    public static int dehexchar(char c)
    {
        int i;
        if(c >= '0' && c <= '9')
            i = c - 48;
        else
        if(c >= 'A' && c <= 'F')
            i = c - 55;
        else
        if(c >= 'a' && c <= 'f')
            i = c - 87;
        else
            i = -1;
        return i;
    }

    public void back()
        throws JSONException
    {
        if(useLastChar || index <= 0)
        {
            throw new JSONException("Stepping back two steps is not supported");
        } else
        {
            index = index - 1;
            useLastChar = true;
            return;
        }
    }

    public boolean more()
        throws JSONException
    {
        boolean flag;
        if(next() == 0)
        {
            flag = false;
        } else
        {
            back();
            flag = true;
        }
        return flag;
    }

    public char next()
        throws JSONException
    {
        char c;
        if(useLastChar)
        {
            useLastChar = false;
            if(lastChar != 0)
                index = 1 + index;
            c = lastChar;
        } else
        {
            int i;
            try
            {
                i = reader.read();
            }
            catch(IOException ioexception)
            {
                throw new JSONException(ioexception);
            }
            if(i <= 0)
            {
                lastChar = '\0';
                c = '\0';
            } else
            {
                index = 1 + index;
                lastChar = (char)i;
                c = lastChar;
            }
        }
        return c;
    }

    public char next(char c)
        throws JSONException
    {
        char c1 = next();
        if(c1 != c)
            throw syntaxError((new StringBuilder()).append("Expected '").append(c).append("' and instead saw '").append(c1).append("'").toString());
        else
            return c1;
    }

    public String next(int i)
        throws JSONException
    {
        String s;
        if(i == 0)
        {
            s = "";
        } else
        {
            char ac[] = new char[i];
            int j;
            if(useLastChar)
            {
                useLastChar = false;
                ac[0] = lastChar;
                j = 1;
            } else
            {
                j = 0;
            }
            do
            {
                if(j >= i)
                    break;
                int k;
                try
                {
                    k = reader.read(ac, j, i - j);
                }
                catch(IOException ioexception)
                {
                    throw new JSONException(ioexception);
                }
                if(k == -1)
                    break;
                j += k;
            } while(true);
            index = j + index;
            if(j < i)
                throw syntaxError("Substring bounds error");
            lastChar = ac[i - 1];
            s = new String(ac);
        }
        return s;
    }

    public char nextClean()
        throws JSONException
    {
        char c;
        do
            c = next();
        while(c != 0 && c <= ' ');
        return c;
    }

    public String nextString(char c)
        throws JSONException
    {
        StringBuffer stringbuffer = new StringBuffer();
        do
        {
            char c1 = next();
            switch(c1)
            {
            default:
                if(c1 == c)
                    return stringbuffer.toString();
                break;

            case 0: // '\0'
            case 10: // '\n'
            case 13: // '\r'
                throw syntaxError("Unterminated string");

            case 92: // '\\'
                char c2 = next();
                switch(c2)
                {
                default:
                    stringbuffer.append(c2);
                    break;

                case 98: // 'b'
                    stringbuffer.append('\b');
                    break;

                case 116: // 't'
                    stringbuffer.append('\t');
                    break;

                case 110: // 'n'
                    stringbuffer.append('\n');
                    break;

                case 102: // 'f'
                    stringbuffer.append('\f');
                    break;

                case 114: // 'r'
                    stringbuffer.append('\r');
                    break;

                case 117: // 'u'
                    stringbuffer.append((char)Integer.parseInt(next(4), 16));
                    break;

                case 120: // 'x'
                    stringbuffer.append((char)Integer.parseInt(next(2), 16));
                    break;
                }
                continue;
            }
            stringbuffer.append(c1);
        } while(true);
    }

    public String nextTo(char c)
        throws JSONException
    {
        StringBuffer stringbuffer = new StringBuffer();
        do
        {
            char c1 = next();
            if(c1 == c || c1 == 0 || c1 == '\n' || c1 == '\r')
            {
                if(c1 != 0)
                    back();
                return stringbuffer.toString().trim();
            }
            stringbuffer.append(c1);
        } while(true);
    }

    public String nextTo(String s)
        throws JSONException
    {
        StringBuffer stringbuffer = new StringBuffer();
        do
        {
            char c = next();
            if(s.indexOf(c) >= 0 || c == 0 || c == '\n' || c == '\r')
            {
                if(c != 0)
                    back();
                return stringbuffer.toString().trim();
            }
            stringbuffer.append(c);
        } while(true);
    }

    public Object nextValue()
        throws JSONException
    {
        char c = nextClean();
        c;
        JVM INSTR lookupswitch 5: default 56
    //                   34: 93
    //                   39: 93
    //                   40: 117
    //                   91: 117
    //                   123: 101;
           goto _L1 _L2 _L2 _L3 _L3 _L4
_L1:
        StringBuffer stringbuffer = new StringBuffer();
        Object obj;
        for(; c >= ' ' && ",:]}/\\\"[{;=#".indexOf(c) < 0; c = next())
            stringbuffer.append(c);

        back();
        String s = stringbuffer.toString().trim();
        if(s.equals(""))
            throw syntaxError("Missing value");
        obj = JSONObject.stringToValue(s);
          goto _L5
_L2:
        obj = nextString(c);
_L7:
        return obj;
_L4:
        back();
        obj = new JSONObject(this);
        continue; /* Loop/switch isn't completed */
_L3:
        back();
        obj = new JSONArray(this);
        continue; /* Loop/switch isn't completed */
_L5:
        if(true) goto _L7; else goto _L6
_L6:
    }

    public char skipTo(char c)
        throws JSONException
    {
        int i;
        i = index;
        reader.mark(0x7fffffff);
_L2:
        char c1;
        c1 = next();
        if(c1 != 0)
            continue; /* Loop/switch isn't completed */
        reader.reset();
        index = i;
        char c2 = c1;
_L3:
        return c2;
        if(c1 != c) goto _L2; else goto _L1
_L1:
        back();
        c2 = c1;
          goto _L3
        if(true) goto _L2; else goto _L4
_L4:
        IOException ioexception;
        ioexception;
        throw new JSONException(ioexception);
    }

    public JSONException syntaxError(String s)
    {
        return new JSONException((new StringBuilder()).append(s).append(toString()).toString());
    }

    @Override
	public String toString()
    {
        return (new StringBuilder()).append(" at character ").append(index).toString();
    }

    private int index;
    private char lastChar;
    private Reader reader;
    private boolean useLastChar;
}
