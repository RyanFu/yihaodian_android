// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.persistence;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.ConverterLookup;
import com.thoughtworks.xstream.io.StreamException;
import com.thoughtworks.xstream.mapper.Mapper;
import java.io.*;
import java.util.Iterator;

// Referenced classes of package com.thoughtworks.xstream.persistence:
//            PersistenceStrategy

public abstract class AbstractFilePersistenceStrategy
    implements PersistenceStrategy
{
    protected class XmlMapEntriesIterator
        implements Iterator
    {

        @Override
		public boolean hasNext()
        {
            boolean flag;
            if(1 + position < files.length)
                flag = true;
            else
                flag = false;
            return flag;
        }

        @Override
		public Object next()
        {
            class _cls1
                implements java.util.Map.Entry
            {

                @Override
				public boolean equals(Object obj)
                {
                    boolean flag;
                    if(!(obj instanceof java.util.Map.Entry))
                    {
                        flag = false;
                    } else
                    {
                        Object obj1 = getValue();
                        java.util.Map.Entry entry = (java.util.Map.Entry)obj;
                        Object obj2 = entry.getKey();
                        Object obj3 = entry.getValue();
                        if((key != null ? key.equals(obj2) : obj2 == null) && (obj1 != null ? getValue().equals(entry.getValue()) : obj3 == null))
                            flag = true;
                        else
                            flag = false;
                    }
                    return flag;
                }

                @Override
				public Object getKey()
                {
                    return key;
                }

                @Override
				public Object getValue()
                {
                    return readFile(file);
                }

                @Override
				public Object setValue(Object obj)
                {
                    return put(key, obj);
                }

                private final File file;
                private final Object key;
                private final XmlMapEntriesIterator this$1;

                _cls1()
                {
                    this$1 = XmlMapEntriesIterator.this;
                    file = current = files[int i = 1 + 
// JavaClassFileOutputException: get_constant: invalid tag
            }

            return new _cls1();
        }

        @Override
		public void remove()
        {
            if(current == null)
            {
                throw new IllegalStateException();
            } else
            {
                current.delete();
                return;
            }
        }

        private File current;
        private final File files[];
        private int position;
        private final AbstractFilePersistenceStrategy this$0;


/*
        static File access$202(XmlMapEntriesIterator xmlmapentriesiterator, File file)
        {
            xmlmapentriesiterator.current = file;
            return file;
        }

*/



/*
        static int access$404(XmlMapEntriesIterator xmlmapentriesiterator)
        {
            int i = 1 + xmlmapentriesiterator.position;
            xmlmapentriesiterator.position = i;
            return i;
        }

*/


        protected XmlMapEntriesIterator()
        {
            this$0 = AbstractFilePersistenceStrategy.this;
            files = baseDirectory.listFiles(filter);
            position = -1;
            current = null;
        }
    }

    protected class ValidFilenameFilter
        implements FilenameFilter
    {

        @Override
		public boolean accept(File file, String s)
        {
            boolean flag;
            if((new File(file, s)).isFile() && isValid(file, s))
                flag = true;
            else
                flag = false;
            return flag;
        }

        private final AbstractFilePersistenceStrategy this$0;

        protected ValidFilenameFilter()
        {
            this$0 = AbstractFilePersistenceStrategy.this;
        }
    }


    public AbstractFilePersistenceStrategy(File file, XStream xstream1, String s)
    {
        baseDirectory = file;
        xstream = xstream1;
        encoding = s;
    }

    private File getFile(String s)
    {
        return new File(baseDirectory, s);
    }

    private Object readFile(File file)
    {
        Object obj;
        FileInputStream fileinputstream;
        InputStreamReader inputstreamreader;
        InputStreamReader inputstreamreader1;
        Exception exception;
        Object obj1;
        try
        {
            fileinputstream = new FileInputStream(file);
            if(encoding == null)
                break MISSING_BLOCK_LABEL_57;
            inputstreamreader = new InputStreamReader(fileinputstream, encoding);
        }
        catch(FileNotFoundException filenotfoundexception)
        {
            obj = null;
            break MISSING_BLOCK_LABEL_98;
        }
        catch(IOException ioexception)
        {
            throw new StreamException(ioexception);
        }
        inputstreamreader1 = inputstreamreader;
        obj1 = xstream.fromXML(inputstreamreader1);
        obj = obj1;
        inputstreamreader1.close();
        break MISSING_BLOCK_LABEL_98;
        inputstreamreader1 = new InputStreamReader(fileinputstream);
        break MISSING_BLOCK_LABEL_34;
        exception;
        inputstreamreader1.close();
        throw exception;
        return obj;
    }

    private void writeFile(File file, Object obj)
    {
        FileOutputStream fileoutputstream;
        OutputStreamWriter outputstreamwriter;
        OutputStreamWriter outputstreamwriter1;
        Exception exception;
        try
        {
            fileoutputstream = new FileOutputStream(file);
            if(encoding == null)
                break MISSING_BLOCK_LABEL_50;
            outputstreamwriter = new OutputStreamWriter(fileoutputstream, encoding);
        }
        catch(IOException ioexception)
        {
            throw new StreamException(ioexception);
        }
        outputstreamwriter1 = outputstreamwriter;
        xstream.toXML(obj, outputstreamwriter1);
        outputstreamwriter1.close();
        return;
        outputstreamwriter1 = new OutputStreamWriter(fileoutputstream);
        break MISSING_BLOCK_LABEL_34;
        exception;
        outputstreamwriter1.close();
        throw exception;
    }

    public boolean containsKey(Object obj)
    {
        return getFile(getName(obj)).isFile();
    }

    protected abstract Object extractKey(String s);

    @Override
	public Object get(Object obj)
    {
        return readFile(getFile(getName(obj)));
    }

    protected ConverterLookup getConverterLookup()
    {
        return xstream.getConverterLookup();
    }

    protected Mapper getMapper()
    {
        return xstream.getMapper();
    }

    protected abstract String getName(Object obj);

    protected boolean isValid(File file, String s)
    {
        return s.endsWith(".xml");
    }

    @Override
	public Iterator iterator()
    {
        return new XmlMapEntriesIterator();
    }

    @Override
	public Object put(Object obj, Object obj1)
    {
        Object obj2 = get(obj);
        String s = getName(obj);
        writeFile(new File(baseDirectory, s), obj1);
        return obj2;
    }

    @Override
	public Object remove(Object obj)
    {
        File file = getFile(getName(obj));
        Object obj1 = null;
        if(file.isFile())
        {
            obj1 = readFile(file);
            file.delete();
        }
        return obj1;
    }

    @Override
	public int size()
    {
        return baseDirectory.list(filter).length;
    }

    private final File baseDirectory;
    private final String encoding;
    private final FilenameFilter filter = new ValidFilenameFilter();
    private final transient XStream xstream;



}
