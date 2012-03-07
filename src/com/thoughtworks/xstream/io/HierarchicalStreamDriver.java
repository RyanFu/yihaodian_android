// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io;

import java.io.*;

// Referenced classes of package com.thoughtworks.xstream.io:
//            HierarchicalStreamReader, HierarchicalStreamWriter

public interface HierarchicalStreamDriver
{

    public abstract HierarchicalStreamReader createReader(InputStream inputstream);

    public abstract HierarchicalStreamReader createReader(Reader reader);

    public abstract HierarchicalStreamWriter createWriter(OutputStream outputstream);

    public abstract HierarchicalStreamWriter createWriter(Writer writer);
}
