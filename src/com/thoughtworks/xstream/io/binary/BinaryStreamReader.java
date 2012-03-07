// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io.binary;

import com.thoughtworks.xstream.converters.ErrorWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.StreamException;
import java.io.*;
import java.util.*;

// Referenced classes of package com.thoughtworks.xstream.io.binary:
//            ReaderDepthState, Token

public class BinaryStreamReader
    implements HierarchicalStreamReader
{
    private static class IdRegistry
    {

        public String get(long l)
        {
            String s = (String)map.get(new Long(l));
            if(s == null)
                throw new StreamException("Unknown ID : " + l);
            else
                return s;
        }

        public void put(long l, String s)
        {
            map.put(new Long(l), s);
        }

        private Map map;

        private IdRegistry()
        {
            map = new HashMap();
        }

    }


    public BinaryStreamReader(InputStream inputstream)
    {
        in = new DataInputStream(inputstream);
        moveDown();
    }

    private Token readToken()
    {
        if(pushback != null) goto _L2; else goto _L1
_L1:
        Token token2 = tokenFormatter.read(in);
        token2.getType();
        JVM INSTR tableswitch 2 2: default 98
    //                   2 44;
           goto _L3 _L4
_L4:
        Token token3;
        idRegistry.put(token2.getId(), token2.getValue());
        token3 = readToken();
        Token token1;
        token1 = token3;
        break MISSING_BLOCK_LABEL_101;
        IOException ioexception;
        ioexception;
        throw new StreamException(ioexception);
_L2:
        Token token = pushback;
        pushback = null;
        token1 = token;
        break MISSING_BLOCK_LABEL_101;
_L3:
        token1 = token2;
        return token1;
    }

    @Override
	public void appendErrors(ErrorWriter errorwriter)
    {
    }

    @Override
	public void close()
    {
        try
        {
            in.close();
            return;
        }
        catch(IOException ioexception)
        {
            throw new StreamException(ioexception);
        }
    }

    @Override
	public String getAttribute(int i)
    {
        return depthState.getAttribute(i);
    }

    @Override
	public String getAttribute(String s)
    {
        return depthState.getAttribute(s);
    }

    @Override
	public int getAttributeCount()
    {
        return depthState.getAttributeCount();
    }

    @Override
	public String getAttributeName(int i)
    {
        return depthState.getAttributeName(i);
    }

    @Override
	public Iterator getAttributeNames()
    {
        return depthState.getAttributeNames();
    }

    @Override
	public String getNodeName()
    {
        return depthState.getName();
    }

    @Override
	public String getValue()
    {
        return depthState.getValue();
    }

    @Override
	public boolean hasMoreChildren()
    {
        return depthState.hasMoreChildren();
    }

    @Override
	public void moveDown()
    {
        depthState.push();
        Token token = readToken();
        switch(token.getType())
        {
        default:
            throw new StreamException("Expected StartNode");

        case 3: // '\003'
            depthState.setName(idRegistry.get(token.getId()));
            break;
        }
_L6:
        Token token1 = readToken();
        token1.getType();
        JVM INSTR tableswitch 3 6: default 104
    //                   3 184
    //                   4 170
    //                   5 131
    //                   6 156;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        throw new StreamException("Unexpected token " + token1);
_L4:
        depthState.addAttribute(idRegistry.get(token1.getId()), token1.getValue());
          goto _L6
_L5:
        depthState.setValue(token1.getValue());
          goto _L6
_L3:
        depthState.setHasMoreChildren(false);
        pushBack(token1);
_L8:
        return;
_L2:
        depthState.setHasMoreChildren(true);
        pushBack(token1);
        if(true) goto _L8; else goto _L7
_L7:
    }

    @Override
	public void moveUp()
    {
        int i;
        depthState.pop();
        i = 0;
_L1:
        readToken().getType();
        JVM INSTR tableswitch 3 4: default 40
    //                   3 43
    //                   4 49;
           goto _L1 _L2 _L3
_L2:
        i++;
          goto _L1
_L3:
        if(i != 0) goto _L5; else goto _L4
_L4:
        Token token = readToken();
        token.getType();
        JVM INSTR tableswitch 3 4: default 84
    //                   3 131
    //                   4 117;
           goto _L6 _L7 _L8
_L6:
        throw new StreamException("Unexpected token " + token);
_L5:
        i--;
          goto _L1
_L8:
        depthState.setHasMoreChildren(false);
_L10:
        pushBack(token);
        return;
_L7:
        depthState.setHasMoreChildren(true);
        if(true) goto _L10; else goto _L9
_L9:
    }

    public void pushBack(Token token)
    {
        if(pushback == null)
        {
            pushback = token;
            return;
        } else
        {
            throw new Error("Cannot push more than one token back");
        }
    }

    @Override
	public HierarchicalStreamReader underlyingReader()
    {
        return this;
    }

    private final ReaderDepthState depthState = new ReaderDepthState();
    private final IdRegistry idRegistry = new IdRegistry();
    private final DataInputStream in;
    private Token pushback;
    private final Token.Formatter tokenFormatter = new Token.Formatter();
}
