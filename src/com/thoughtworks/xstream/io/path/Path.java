// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io.path;

import com.thoughtworks.xstream.core.util.FastStack;
import java.util.ArrayList;
import java.util.List;

public class Path
{

    public Path(String s)
    {
        ArrayList arraylist = new ArrayList();
        int i = 0;
        do
        {
            int j = s.indexOf('/', i);
            if(j != -1)
            {
                arraylist.add(s.substring(i, j));
                i = j + 1;
            } else
            {
                arraylist.add(s.substring(i));
                String as[] = new String[arraylist.size()];
                arraylist.toArray(as);
                chunks = as;
                pathAsString = s;
                return;
            }
        } while(true);
    }

    public Path(String as[])
    {
        chunks = as;
    }

    private int depthOfPathDivergence(String as[], String as1[])
    {
        int i;
        int j;
        i = Math.min(as.length, as1.length);
        j = 0;
_L3:
        if(j >= i)
            break MISSING_BLOCK_LABEL_44;
        if(as[j].equals(as1[j])) goto _L2; else goto _L1
_L1:
        int k = j;
_L4:
        return k;
_L2:
        j++;
          goto _L3
        k = i;
          goto _L4
    }

    public Path apply(Path path)
    {
        FastStack faststack = new FastStack(16);
        for(int i = 0; i < chunks.length; i++)
            faststack.push(chunks[i]);

        int j = 0;
        while(j < path.chunks.length) 
        {
            String s = path.chunks[j];
            if(s.equals(".."))
                faststack.pop();
            else
            if(!s.equals("."))
                faststack.push(s);
            j++;
        }
        String as[] = new String[faststack.size()];
        for(int k = 0; k < as.length; k++)
            as[k] = (String)faststack.get(k);

        return new Path(as);
    }

    @Override
	public boolean equals(Object obj)
    {
        if(this != obj) goto _L2; else goto _L1
_L1:
        boolean flag = true;
_L4:
        return flag;
_L2:
        if(!(obj instanceof Path))
        {
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        Path path = (Path)obj;
        if(chunks.length != path.chunks.length)
        {
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        int i = 0;
        do
        {
            if(i >= chunks.length)
                break;
            if(!chunks[i].equals(path.chunks[i]))
            {
                flag = false;
                continue; /* Loop/switch isn't completed */
            }
            i++;
        } while(true);
        flag = true;
        if(true) goto _L4; else goto _L3
_L3:
    }

    @Override
	public int hashCode()
    {
        int i = 0x20675fcb;
        for(int j = 0; j < chunks.length; j++)
            i = i * 29 + chunks[j].hashCode();

        return i;
    }

    public boolean isAncestor(Path path)
    {
        if(path != null && path.chunks.length >= chunks.length) goto _L2; else goto _L1
_L1:
        boolean flag = false;
_L4:
        return flag;
_L2:
        int i = 0;
        do
        {
            if(i >= chunks.length)
                break;
            if(!chunks[i].equals(path.chunks[i]))
            {
                flag = false;
                continue; /* Loop/switch isn't completed */
            }
            i++;
        } while(true);
        flag = true;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public Path relativeTo(Path path)
    {
        int i = depthOfPathDivergence(chunks, path.chunks);
        String as[] = new String[(chunks.length + path.chunks.length) - i * 2];
        int j = 0;
        for(int k = i; k < chunks.length;)
        {
            int j1 = j + 1;
            as[j] = "..";
            k++;
            j = j1;
        }

        for(int l = i; l < path.chunks.length;)
        {
            int i1 = j + 1;
            as[j] = path.chunks[l];
            l++;
            j = i1;
        }

        Path path1;
        if(j == 0)
            path1 = DOT;
        else
            path1 = new Path(as);
        return path1;
    }

    @Override
	public String toString()
    {
        if(pathAsString == null)
        {
            StringBuffer stringbuffer = new StringBuffer();
            for(int i = 0; i < chunks.length; i++)
            {
                if(i > 0)
                    stringbuffer.append('/');
                stringbuffer.append(chunks[i]);
            }

            pathAsString = stringbuffer.toString();
        }
        return pathAsString;
    }

    private static final Path DOT;
    private final String chunks[];
    private transient String pathAsString;

    static 
    {
        String as[] = new String[1];
        as[0] = ".";
        DOT = new Path(as);
    }
}
