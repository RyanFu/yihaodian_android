// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package weibo4andriod.http;

import java.io.*;

public class ImageItem
{

    public ImageItem(String s, byte abyte0[])
        throws Exception
    {
        String s1 = getImageType(abyte0);
        if(s1 != null && (s1.equalsIgnoreCase("image/gif") || s1.equalsIgnoreCase("image/png") || s1.equalsIgnoreCase("image/jpeg")))
        {
            content = abyte0;
            name = s;
            ImageType = s1;
            return;
        } else
        {
            throw new IllegalStateException("Unsupported image type, Only Suport JPG ,GIF,PNG!");
        }
    }

    public static String getImageType(File file)
    {
        if(file != null && file.exists()) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        FileInputStream fileinputstream = new FileInputStream(file);
        String s1 = getImageType(((InputStream) (fileinputstream)));
        FileInputStream fileinputstream1;
        Exception exception1;
        IOException ioexception4;
        if(fileinputstream != null)
            try
            {
                fileinputstream.close();
            }
            catch(IOException ioexception3) { }
        s = s1;
        continue; /* Loop/switch isn't completed */
        ioexception4;
        fileinputstream = null;
_L7:
        if(fileinputstream != null)
            try
            {
                fileinputstream.close();
            }
            catch(IOException ioexception1) { }
        s = null;
        if(true) goto _L4; else goto _L3
_L3:
        exception1;
        fileinputstream1 = null;
_L6:
        if(fileinputstream1 != null)
            try
            {
                fileinputstream1.close();
            }
            catch(IOException ioexception2) { }
        throw exception1;
        Exception exception;
        exception;
        fileinputstream1 = fileinputstream;
        exception1 = exception;
        if(true) goto _L6; else goto _L5
_L5:
        IOException ioexception;
        ioexception;
          goto _L7
    }

    public static String getImageType(InputStream inputstream)
    {
        if(inputstream != null) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        String s1;
        byte abyte0[] = new byte[8];
        inputstream.read(abyte0);
        s1 = getImageType(abyte0);
        s = s1;
        continue; /* Loop/switch isn't completed */
        IOException ioexception;
        ioexception;
        s = null;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static String getImageType(byte abyte0[])
    {
        String s;
        if(isJPEG(abyte0))
            s = "image/jpeg";
        else
        if(isGIF(abyte0))
            s = "image/gif";
        else
        if(isPNG(abyte0))
            s = "image/png";
        else
        if(isBMP(abyte0))
            s = "application/x-bmp";
        else
            s = null;
        return s;
    }

    private static boolean isBMP(byte abyte0[])
    {
        boolean flag;
        if(abyte0.length < 2)
            flag = false;
        else
        if(abyte0[0] == 66 && abyte0[1] == 77)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private static boolean isGIF(byte abyte0[])
    {
        boolean flag;
        if(abyte0.length < 6)
            flag = false;
        else
        if(abyte0[0] == 71 && abyte0[1] == 73 && abyte0[2] == 70 && abyte0[3] == 56 && (abyte0[4] == 55 || abyte0[4] == 57) && abyte0[5] == 97)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private static boolean isJPEG(byte abyte0[])
    {
        boolean flag;
        if(abyte0.length < 2)
            flag = false;
        else
        if(abyte0[0] == -1 && abyte0[1] == -40)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private static boolean isPNG(byte abyte0[])
    {
        boolean flag;
        if(abyte0.length < 8)
            flag = false;
        else
        if(abyte0[0] == -119 && abyte0[1] == 80 && abyte0[2] == 78 && abyte0[3] == 71 && abyte0[4] == 13 && abyte0[5] == 10 && abyte0[6] == 26 && abyte0[7] == 10)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public byte[] getContent()
    {
        return content;
    }

    public String getImageType()
    {
        return ImageType;
    }

    public String getName()
    {
        return name;
    }

    private String ImageType;
    private byte content[];
    private String name;
}
