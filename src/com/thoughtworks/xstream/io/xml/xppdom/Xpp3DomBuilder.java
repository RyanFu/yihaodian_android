// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io.xml.xppdom;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

// Referenced classes of package com.thoughtworks.xstream.io.xml.xppdom:
//            Xpp3Dom

public class Xpp3DomBuilder
{

    public Xpp3DomBuilder()
    {
    }

    public static Xpp3Dom build(Reader reader)
        throws Exception
    {
        ArrayList arraylist;
        ArrayList arraylist1;
        MXParser mxparser;
        int i;
        Xpp3Dom xpp3dom;
        arraylist = new ArrayList();
        arraylist1 = new ArrayList();
        mxparser = new MXParser();
        mxparser.setInput(reader);
        i = mxparser.getEventType();
        xpp3dom = null;
_L2:
        Xpp3Dom xpp3dom2;
        if(i == 1)
            break; /* Loop/switch isn't completed */
        if(i == 2)
        {
            Xpp3Dom xpp3dom1 = new Xpp3Dom(mxparser.getName());
            int j = arraylist.size();
            if(j > 0)
                ((Xpp3Dom)arraylist.get(j - 1)).addChild(xpp3dom1);
            arraylist.add(xpp3dom1);
            arraylist1.add(new StringBuffer());
            int k = mxparser.getAttributeCount();
            for(int l = 0; l < k; l++)
                xpp3dom1.setAttribute(mxparser.getAttributeName(l), mxparser.getAttributeValue(l));

            xpp3dom2 = xpp3dom;
        } else
        if(i == 4)
        {
            ((StringBuffer)arraylist1.get(arraylist1.size() - 1)).append(mxparser.getText());
            xpp3dom2 = xpp3dom;
        } else
        {
            if(i != 3)
                break MISSING_BLOCK_LABEL_313;
            int i1 = arraylist.size() - 1;
            Xpp3Dom xpp3dom3 = (Xpp3Dom)arraylist.remove(i1);
            String s = arraylist1.remove(i1).toString();
            String s1;
            if(s.length() == 0)
                s1 = null;
            else
                s1 = s;
            xpp3dom3.setValue(s1);
            if(i1 != 0)
                break MISSING_BLOCK_LABEL_313;
            xpp3dom2 = xpp3dom3;
        }
_L3:
        i = mxparser.next();
        xpp3dom = xpp3dom2;
        if(true) goto _L2; else goto _L1
_L1:
        reader.close();
        return xpp3dom;
        xpp3dom2 = xpp3dom;
          goto _L3
    }
}
