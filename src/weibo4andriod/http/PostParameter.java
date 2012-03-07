// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package weibo4andriod.http;

import java.io.*;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;

public class PostParameter
    implements Serializable, Comparable
{

    public PostParameter(String s, double d)
    {
        file = null;
        name = s;
        value = String.valueOf(d);
    }

    public PostParameter(String s, int i)
    {
        file = null;
        name = s;
        value = String.valueOf(i);
    }

    public PostParameter(String s, File file1)
    {
        file = null;
        name = s;
        file = file1;
    }

    public PostParameter(String s, String s1)
    {
        file = null;
        name = s;
        value = s1;
    }

    static boolean containsFile(List list)
    {
        boolean flag = false;
        Iterator iterator = list.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            if(!((PostParameter)iterator.next()).isFile())
                continue;
            flag = true;
            break;
        } while(true);
        return flag;
    }

    public static boolean containsFile(PostParameter apostparameter[])
    {
        if(apostparameter != null) goto _L2; else goto _L1
_L1:
        boolean flag = false;
_L4:
        return flag;
_L2:
        int i = apostparameter.length;
        int j = 0;
        do
        {
            if(j >= i)
                break;
            if(apostparameter[j].isFile())
            {
                flag = true;
                continue; /* Loop/switch isn't completed */
            }
            j++;
        } while(true);
        flag = false;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static String encodeParameters(PostParameter apostparameter[])
    {
        String s;
        if(apostparameter == null)
        {
            s = "";
        } else
        {
            StringBuffer stringbuffer = new StringBuffer();
            int i = 0;
            while(i < apostparameter.length) 
            {
                if(apostparameter[i].isFile())
                    throw new IllegalArgumentException((new StringBuilder()).append("parameter [").append(apostparameter[i].name).append("]should be text").toString());
                if(i != 0)
                    stringbuffer.append("&");
                try
                {
                    stringbuffer.append(URLEncoder.encode(apostparameter[i].name, "UTF-8")).append("=").append(URLEncoder.encode(apostparameter[i].value, "UTF-8"));
                }
                catch(UnsupportedEncodingException unsupportedencodingexception) { }
                i++;
            }
            s = stringbuffer.toString();
        }
        return s;
    }

    public static PostParameter[] getParameterArray(String s, int i)
    {
        return getParameterArray(s, String.valueOf(i));
    }

    public static PostParameter[] getParameterArray(String s, int i, String s1, int j)
    {
        return getParameterArray(s, String.valueOf(i), s1, String.valueOf(j));
    }

    public static PostParameter[] getParameterArray(String s, String s1)
    {
        PostParameter apostparameter[] = new PostParameter[1];
        apostparameter[0] = new PostParameter(s, s1);
        return apostparameter;
    }

    public static PostParameter[] getParameterArray(String s, String s1, String s2, String s3)
    {
        PostParameter apostparameter[] = new PostParameter[2];
        apostparameter[0] = new PostParameter(s, s1);
        apostparameter[1] = new PostParameter(s2, s3);
        return apostparameter;
    }

    @Override
	public int compareTo(Object obj)
    {
        PostParameter postparameter = (PostParameter)obj;
        int i = name.compareTo(postparameter.name);
        if(i == 0)
            i = value.compareTo(postparameter.value);
        return i;
    }

    @Override
	public boolean equals(Object obj)
    {
        boolean flag;
        if(obj == null)
            flag = false;
        else
        if(this == obj)
            flag = true;
        else
        if(obj instanceof PostParameter)
        {
            PostParameter postparameter = (PostParameter)obj;
            if(file == null ? postparameter.file != null : !file.equals(postparameter.file))
                flag = false;
            else
            if(name.equals(postparameter.name) && value.equals(postparameter.value))
                flag = true;
            else
                flag = false;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public String getContentType()
    {
        if(!isFile())
            throw new IllegalStateException("not a file");
        String s = file.getName();
        String s2;
        if(-1 == s.lastIndexOf("."))
        {
            s2 = "application/octet-stream";
        } else
        {
            String s1 = s.substring(1 + s.lastIndexOf(".")).toLowerCase();
            if(s1.length() == 3)
            {
                if("gif".equals(s1))
                    s2 = "image/gif";
                else
                if("png".equals(s1))
                    s2 = "image/png";
                else
                if("jpg".equals(s1))
                    s2 = "image/jpeg";
                else
                    s2 = "application/octet-stream";
            } else
            if(s1.length() == 4)
            {
                if("jpeg".equals(s1))
                    s2 = "image/jpeg";
                else
                    s2 = "application/octet-stream";
            } else
            {
                s2 = "application/octet-stream";
            }
        }
        return s2;
    }

    public File getFile()
    {
        return file;
    }

    public String getName()
    {
        return name;
    }

    public String getValue()
    {
        return value;
    }

    @Override
	public int hashCode()
    {
        int i = 31 * (31 * name.hashCode() + value.hashCode());
        int j;
        if(file != null)
            j = file.hashCode();
        else
            j = 0;
        return i + j;
    }

    public boolean isFile()
    {
        boolean flag;
        if(file != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    @Override
	public String toString()
    {
        return (new StringBuilder()).append("PostParameter{name='").append(name).append('\'').append(", value='").append(value).append('\'').append(", file=").append(file).append('}').toString();
    }

    private static final String GIF = "image/gif";
    private static final String JPEG = "image/jpeg";
    private static final String OCTET = "application/octet-stream";
    private static final String PNG = "image/png";
    private static final long serialVersionUID = 0x8726953b6ebdb774L;
    private File file;
    String name;
    String value;
}
