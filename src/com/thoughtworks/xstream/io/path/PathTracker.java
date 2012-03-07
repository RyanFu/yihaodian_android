// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io.path;

import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.thoughtworks.xstream.io.path:
//            Path

public class PathTracker
{

    public PathTracker()
    {
        this(16);
    }

    public PathTracker(int i)
    {
        capacity = Math.max(1, i);
        pathStack = new String[capacity];
        indexMapStack = new Map[capacity];
    }

    private void resizeStacks(int i)
    {
        String as[] = new String[i];
        Map amap[] = new Map[i];
        int j = Math.min(capacity, i);
        System.arraycopy(pathStack, 0, as, 0, j);
        System.arraycopy(indexMapStack, 0, amap, 0, j);
        pathStack = as;
        indexMapStack = amap;
        capacity = i;
    }

    public String getCurrentPath()
    {
        return getPath().toString();
    }

    public Path getPath()
    {
        if(currentPath == null)
        {
            String as[] = new String[1 + pointer];
            as[0] = "";
            int i = 0;
            while(i < pointer) 
            {
                int j = ((Integer)indexMapStack[i].get(pathStack[i])).intValue();
                if(j > 1)
                {
                    StringBuffer stringbuffer = new StringBuffer(6 + pathStack[i].length());
                    stringbuffer.append(pathStack[i]).append('[').append(j).append(']');
                    as[i + 1] = stringbuffer.toString();
                } else
                {
                    as[i + 1] = pathStack[i];
                }
                i++;
            }
            currentPath = new Path(as);
        }
        return currentPath;
    }

    public void popElement()
    {
        indexMapStack[pointer] = null;
        currentPath = null;
        pointer = pointer - 1;
    }

    public void pushElement(String s)
    {
        if(1 + pointer >= capacity)
            resizeStacks(2 * capacity);
        pathStack[pointer] = s;
        Object obj = indexMapStack[pointer];
        if(obj == null)
        {
            obj = new HashMap();
            indexMapStack[pointer] = ((Map) (obj));
        }
        if(((Map) (obj)).containsKey(s))
            ((Map) (obj)).put(s, new Integer(1 + ((Integer)((Map) (obj)).get(s)).intValue()));
        else
            ((Map) (obj)).put(s, new Integer(1));
        pointer = 1 + pointer;
        currentPath = null;
    }

    private int capacity;
    private Path currentPath;
    private Map indexMapStack[];
    private String pathStack[];
    private int pointer;
}
