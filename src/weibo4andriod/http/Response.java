// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package weibo4andriod.http;

import java.io.*;
import java.net.HttpURLConnection;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import javax.xml.parsers.*;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import weibo4andriod.Configuration;
import weibo4andriod.WeiboException;
import weibo4andriod.org.json.*;

public class Response
{

    public Response()
    {
        responseAsDocument = null;
        responseAsString = null;
        streamConsumed = false;
    }

    Response(String s)
    {
        responseAsDocument = null;
        responseAsString = null;
        streamConsumed = false;
        responseAsString = s;
    }

    public Response(HttpURLConnection httpurlconnection)
        throws IOException
    {
        responseAsDocument = null;
        responseAsString = null;
        streamConsumed = false;
        con = httpurlconnection;
        statusCode = httpurlconnection.getResponseCode();
        InputStream inputstream = httpurlconnection.getErrorStream();
        is = inputstream;
        if(inputstream == null)
            is = httpurlconnection.getInputStream();
        if(is != null && "gzip".equals(httpurlconnection.getContentEncoding()))
            is = new GZIPInputStream(is);
    }

    private void log(String s)
    {
        if(DEBUG)
            System.out.println((new StringBuilder()).append("[").append(new Date()).append("]").append(s).toString());
    }

    private void log(String s, String s1)
    {
        if(DEBUG)
            log((new StringBuilder()).append(s).append(s1).toString());
    }

    public static String unescape(String s)
    {
        Matcher matcher = escaped.matcher(s);
        StringBuffer stringbuffer = new StringBuffer();
        for(; matcher.find(); matcher.appendReplacement(stringbuffer, Character.toString((char)Integer.parseInt(matcher.group(1), 10))));
        matcher.appendTail(stringbuffer);
        return stringbuffer.toString();
    }

    public Document asDocument()
        throws WeiboException
    {
        if(responseAsDocument == null)
            try
            {
                responseAsDocument = ((DocumentBuilder)builders.get()).parse(new ByteArrayInputStream(asString().getBytes("UTF-8")));
            }
            catch(SAXException saxexception)
            {
                throw new WeiboException((new StringBuilder()).append("The response body was not well-formed:\n").append(responseAsString).toString(), saxexception);
            }
            catch(IOException ioexception)
            {
                throw new WeiboException("There's something with the connection.", ioexception);
            }
        return responseAsDocument;
    }

    public JSONArray asJSONArray()
        throws WeiboException
    {
        JSONArray jsonarray;
        try
        {
            jsonarray = new JSONArray(asString());
        }
        catch(Exception exception)
        {
            throw new WeiboException((new StringBuilder()).append(exception.getMessage()).append(":").append(responseAsString).toString(), exception);
        }
        return jsonarray;
    }

    public JSONObject asJSONObject()
        throws WeiboException
    {
        JSONObject jsonobject;
        try
        {
            jsonobject = new JSONObject(asString());
        }
        catch(JSONException jsonexception)
        {
            throw new WeiboException((new StringBuilder()).append(jsonexception.getMessage()).append(":").append(responseAsString).toString(), jsonexception);
        }
        return jsonobject;
    }

    public InputStreamReader asReader()
    {
        InputStreamReader inputstreamreader;
        try
        {
            inputstreamreader = new InputStreamReader(is, "UTF-8");
        }
        catch(UnsupportedEncodingException unsupportedencodingexception)
        {
            inputstreamreader = new InputStreamReader(is);
        }
        return inputstreamreader;
    }

    public InputStream asStream()
    {
        if(streamConsumed)
            throw new IllegalStateException("Stream has already been consumed.");
        else
            return is;
    }

    public String asString()
        throws WeiboException
    {
        if(responseAsString != null)
            break MISSING_BLOCK_LABEL_146;
        String s;
        InputStream inputstream;
        inputstream = asStream();
        if(inputstream == null)
        {
            s = null;
            break MISSING_BLOCK_LABEL_168;
        }
        BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(inputstream, "UTF-8"));
        StringBuffer stringbuffer = new StringBuffer();
        NullPointerException nullpointerexception;
        do
        {
            String s1 = bufferedreader.readLine();
            if(s1 == null)
                break;
            stringbuffer.append(s1).append("\n");
        } while(true);
        try
        {
            responseAsString = stringbuffer.toString();
            if(Configuration.isDalvik())
                responseAsString = unescape(responseAsString);
            log(responseAsString);
            inputstream.close();
            con.disconnect();
            streamConsumed = true;
        }
        // Misplaced declaration of an exception variable
        catch(NullPointerException nullpointerexception)
        {
            throw new WeiboException(nullpointerexception.getMessage(), nullpointerexception);
        }
        catch(IOException ioexception)
        {
            throw new WeiboException(ioexception.getMessage(), ioexception);
        }
        s = responseAsString;
        return s;
    }

    public void disconnect()
    {
        con.disconnect();
    }

    public String getResponseAsString()
    {
        return responseAsString;
    }

    public String getResponseHeader(String s)
    {
        String s1;
        if(con != null)
            s1 = con.getHeaderField(s);
        else
            s1 = null;
        return s1;
    }

    public int getStatusCode()
    {
        return statusCode;
    }

    public void setResponseAsString(String s)
    {
        responseAsString = s;
    }

    public void setStatusCode(int i)
    {
        statusCode = i;
    }

    @Override
	public String toString()
    {
        String s;
        if(responseAsString != null)
            s = responseAsString;
        else
            s = (new StringBuilder()).append("Response{statusCode=").append(statusCode).append(", response=").append(responseAsDocument).append(", responseString='").append(responseAsString).append('\'').append(", is=").append(is).append(", con=").append(con).append('}').toString();
        return s;
    }

    private static final boolean DEBUG = Configuration.getDebug();
    private static ThreadLocal builders = new ThreadLocal() {

        @Override
		protected volatile Object initialValue()
        {
            return initialValue();
        }

        protected DocumentBuilder initialValue()
        {
            DocumentBuilder documentbuilder;
            try
            {
                documentbuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            }
            catch(ParserConfigurationException parserconfigurationexception)
            {
                throw new ExceptionInInitializerError(parserconfigurationexception);
            }
            return documentbuilder;
        }

    }
;
    private static Pattern escaped = Pattern.compile("&#([0-9]{3,5});");
    private HttpURLConnection con;
    private InputStream is;
    private Document responseAsDocument;
    private String responseAsString;
    private int statusCode;
    private boolean streamConsumed;

}
