// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package weibo4andriod;

import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import weibo4andriod.http.HttpClient;
import weibo4andriod.http.PostParameter;

// Referenced classes of package weibo4andriod:
//            WeiboSupport, Configuration, WeiboException, StatusStream, 
//            StatusListener, Status

public class WeiboStream extends WeiboSupport
{
    abstract class StreamHandlingThread extends Thread
    {

        private void setStatus(String s)
        {
            String s1 = (new StringBuilder()).append("Weibo Stream Handling Thread").append(s).toString();
            setName(s1);
            log(s1);
        }

        /**
         * @deprecated Method close is deprecated
         */

        public void close()
            throws IOException
        {
            this;
            JVM INSTR monitorenter ;
            setStatus("[disposing thread]");
            if(stream != null)
            {
                stream.close();
                closed = true;
            }
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        abstract StatusStream getStream()
            throws WeiboException;

        public void run()
        {
_L2:
            do
            {
                if(closed)
                    break MISSING_BLOCK_LABEL_330;
                try
                {
                    if(retryHistory.size() > 0 && System.currentTimeMillis() - ((Long)retryHistory.get(0)).longValue() > 60000L)
                        retryHistory.remove(0);
                    if(retryHistory.size() < retryPerMinutes)
                    {
                        setStatus("[establishing connection]");
                        do
                        {
                            if(closed || stream != null)
                                continue; /* Loop/switch isn't completed */
                            if(retryHistory.size() < retryPerMinutes)
                            {
                                retryHistory.add(Long.valueOf(System.currentTimeMillis()));
                                stream = getStream();
                            }
                        } while(true);
                    }
                    break;
                }
                catch(WeiboException weiboexception)
                {
                    stream = null;
                    weiboexception.printStackTrace();
                    log(weiboexception.getMessage());
                    statusListener.onException(weiboexception);
                }
            } while(true);
            long l;
            l = 60000L - (System.currentTimeMillis() - ((Long)retryHistory.get(retryHistory.size() - 1)).longValue());
            setStatus((new StringBuilder()).append("[retry limit reached. sleeping for ").append(l / 1000L).append(" secs]").toString());
            Status status;
            try
            {
                Thread.sleep(l);
            }
            catch(InterruptedException interruptedexception) { }
            if(stream == null) goto _L2; else goto _L1
_L1:
            setStatus("[receiving stream]");
_L5:
            if(closed) goto _L2; else goto _L3
_L3:
            status = stream.next();
            if(status == null) goto _L2; else goto _L4
_L4:
            log("received:", status.toString());
            if(statusListener != null)
                statusListener.onStatus(status);
              goto _L5
        }

        private static final String NAME = "Weibo Stream Handling Thread";
        Object args[];
        private boolean closed;
        private List retryHistory;
        StatusStream stream;
        final WeiboStream this$0;

        StreamHandlingThread(Object aobj[])
        {
            this$0 = WeiboStream.this;
            super("Weibo Stream Handling Thread[initializing]");
            stream = null;
            closed = false;
            args = aobj;
            retryHistory = new ArrayList(retryPerMinutes);
        }
    }


    public WeiboStream()
    {
        handler = null;
        retryPerMinutes = 1;
    }

    public WeiboStream(String s, String s1)
    {
        super(s, s1);
        handler = null;
        retryPerMinutes = 1;
    }

    public WeiboStream(String s, String s1, StatusListener statuslistener)
    {
        super(s, s1);
        handler = null;
        retryPerMinutes = 1;
        statusListener = statuslistener;
    }

    private String getStreamBaseURL()
    {
        String s;
        if(USE_SSL)
            s = "https://stream.t.sina.com.cn/";
        else
            s = "http://stream.t.sina.com.cn/";
        return s;
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

    /**
     * @deprecated Method startHandler is deprecated
     */

    private void startHandler(StreamHandlingThread streamhandlingthread)
        throws WeiboException
    {
        this;
        JVM INSTR monitorenter ;
        cleanup();
        if(statusListener == null)
            throw new IllegalStateException("StatusListener is not set.");
        break MISSING_BLOCK_LABEL_28;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        handler = streamhandlingthread;
        handler.start();
        this;
        JVM INSTR monitorexit ;
    }

    private String toFollowString(int ai[])
    {
        StringBuffer stringbuffer = new StringBuffer(11 * ai.length);
        int i = ai.length;
        for(int j = 0; j < i; j++)
        {
            int k = ai[j];
            if(stringbuffer.length() != 0)
                stringbuffer.append(",");
            stringbuffer.append(k);
        }

        return stringbuffer.toString();
    }

    private String toTrackString(String as[])
    {
        StringBuffer stringbuffer = new StringBuffer(4 * (20 * as.length));
        int i = as.length;
        for(int j = 0; j < i; j++)
        {
            String s = as[j];
            if(stringbuffer.length() != 0)
                stringbuffer.append(",");
            stringbuffer.append(s);
        }

        return stringbuffer.toString();
    }

    public void birddog(int i, int ai[])
        throws WeiboException
    {
        Object aobj[] = new Object[2];
        aobj[0] = Integer.valueOf(i);
        aobj[1] = ai;
        startHandler(new StreamHandlingThread(aobj) {

            public StatusStream getStream()
                throws WeiboException
            {
                return getBirddogStream(((Integer)args[0]).intValue(), (int[])(int[])args[1]);
            }

            final WeiboStream this$0;

            
            {
                this$0 = WeiboStream.this;
                super(aobj);
            }
        }
);
    }

    /**
     * @deprecated Method cleanup is deprecated
     */

    public void cleanup()
    {
        this;
        JVM INSTR monitorenter ;
        StreamHandlingThread streamhandlingthread = handler;
        if(streamhandlingthread == null)
            break MISSING_BLOCK_LABEL_18;
        Exception exception;
        try
        {
            handler.close();
        }
        catch(IOException ioexception) { }
        this;
        JVM INSTR monitorexit ;
        return;
        exception;
        throw exception;
    }

    public void filter(int i, int ai[], String as[])
        throws WeiboException
    {
        Object aobj[] = new Object[3];
        aobj[0] = Integer.valueOf(i);
        aobj[1] = ai;
        aobj[2] = as;
        startHandler(new StreamHandlingThread(aobj) {

            public StatusStream getStream()
                throws WeiboException
            {
                return getFilterStream(((Integer)args[0]).intValue(), (int[])(int[])args[1], (String[])(String[])args[2]);
            }

            final WeiboStream this$0;

            
            {
                this$0 = WeiboStream.this;
                super(aobj);
            }
        }
);
    }

    public void firehose(int i)
        throws WeiboException
    {
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(i);
        startHandler(new StreamHandlingThread(aobj) {

            public StatusStream getStream()
                throws WeiboException
            {
                return getFirehoseStream(((Integer)args[0]).intValue());
            }

            final WeiboStream this$0;

            
            {
                this$0 = WeiboStream.this;
                super(aobj);
            }
        }
);
    }

    public void follow(int ai[])
        throws WeiboException
    {
        Object aobj[] = new Object[1];
        aobj[0] = ai;
        startHandler(new StreamHandlingThread(aobj) {

            public StatusStream getStream()
                throws WeiboException
            {
                return getFollowStream((int[])(int[])args[0]);
            }

            final WeiboStream this$0;

            
            {
                this$0 = WeiboStream.this;
                super(aobj);
            }
        }
);
    }

    public volatile void forceUsePost(boolean flag)
    {
        super.forceUsePost(flag);
    }

    public void gardenhose()
        throws WeiboException
    {
        startHandler(new StreamHandlingThread(null) {

            public StatusStream getStream()
                throws WeiboException
            {
                return getGardenhoseStream();
            }

            final WeiboStream this$0;

            
            {
                this$0 = WeiboStream.this;
                super(aobj);
            }
        }
);
    }

    public StatusStream getBirddogStream(int i, int ai[])
        throws WeiboException
    {
        return getFilterStream(i, ai, null);
    }

    public volatile String getClientURL()
    {
        return super.getClientURL();
    }

    public volatile String getClientVersion()
    {
        return super.getClientVersion();
    }

    public StatusStream getFilterStream(int i, int ai[], String as[])
        throws WeiboException
    {
        ArrayList arraylist = new ArrayList();
        arraylist.add(new PostParameter("count", i));
        if(ai != null && ai.length > 0)
            arraylist.add(new PostParameter("follow", toFollowString(ai)));
        if(as != null && as.length > 0)
            arraylist.add(new PostParameter("track", toTrackString(as)));
        StatusStream statusstream;
        try
        {
            statusstream = new StatusStream(http.post((new StringBuilder()).append(getStreamBaseURL()).append("1/statuses/filter.json").toString(), (PostParameter[])arraylist.toArray(new PostParameter[0]), true));
        }
        catch(IOException ioexception)
        {
            throw new WeiboException(ioexception);
        }
        return statusstream;
    }

    public StatusStream getFirehoseStream(int i)
        throws WeiboException
    {
        StatusStream statusstream;
        try
        {
            HttpClient httpclient = http;
            String s = (new StringBuilder()).append(getStreamBaseURL()).append("1/statuses/firehose.json").toString();
            PostParameter apostparameter[] = new PostParameter[1];
            apostparameter[0] = new PostParameter("count", String.valueOf(i));
            statusstream = new StatusStream(httpclient.post(s, apostparameter, true));
        }
        catch(IOException ioexception)
        {
            throw new WeiboException(ioexception);
        }
        return statusstream;
    }

    public StatusStream getFollowStream(int ai[])
        throws WeiboException
    {
        return getFilterStream(0, ai, null);
    }

    public StatusStream getGardenhoseStream()
        throws WeiboException
    {
        return getSampleStream();
    }

    public volatile String getPassword()
    {
        return super.getPassword();
    }

    public StatusStream getRetweetStream()
        throws WeiboException
    {
        StatusStream statusstream;
        try
        {
            statusstream = new StatusStream(http.post((new StringBuilder()).append(getStreamBaseURL()).append("1/statuses/retweet.json").toString(), new PostParameter[0], true));
        }
        catch(IOException ioexception)
        {
            throw new WeiboException(ioexception);
        }
        return statusstream;
    }

    public StatusStream getSampleStream()
        throws WeiboException
    {
        StatusStream statusstream;
        try
        {
            statusstream = new StatusStream(http.get((new StringBuilder()).append(getStreamBaseURL()).append("1/statuses/sample.json").toString(), true));
        }
        catch(IOException ioexception)
        {
            throw new WeiboException(ioexception);
        }
        return statusstream;
    }

    public StatusStream getShadowStream(int i, int ai[])
        throws WeiboException
    {
        return getFilterStream(i, ai, null);
    }

    public volatile String getSource()
    {
        return super.getSource();
    }

    public StatusStream getSpritzerStream()
        throws WeiboException
    {
        return getSampleStream();
    }

    public StatusListener getStatusListener()
    {
        return statusListener;
    }

    public StatusStream getTrackStream(String as[])
        throws WeiboException
    {
        return getFilterStream(0, null, as);
    }

    public volatile String getUserAgent()
    {
        return super.getUserAgent();
    }

    public volatile String getUserId()
    {
        return super.getUserId();
    }

    public volatile boolean isUsePostForced()
    {
        return super.isUsePostForced();
    }

    public void retweet()
        throws WeiboException
    {
        startHandler(new StreamHandlingThread(new Object[0]) {

            public StatusStream getStream()
                throws WeiboException
            {
                return getRetweetStream();
            }

            final WeiboStream this$0;

            
            {
                this$0 = WeiboStream.this;
                super(aobj);
            }
        }
);
    }

    public void sample()
        throws WeiboException
    {
        startHandler(new StreamHandlingThread(null) {

            public StatusStream getStream()
                throws WeiboException
            {
                return getSampleStream();
            }

            final WeiboStream this$0;

            
            {
                this$0 = WeiboStream.this;
                super(aobj);
            }
        }
);
    }

    public volatile void setClientURL(String s)
    {
        super.setClientURL(s);
    }

    public volatile void setClientVersion(String s)
    {
        super.setClientVersion(s);
    }

    public volatile void setHttpConnectionTimeout(int i)
    {
        super.setHttpConnectionTimeout(i);
    }

    public volatile void setHttpProxy(String s, int i)
    {
        super.setHttpProxy(s, i);
    }

    public volatile void setHttpProxyAuth(String s, String s1)
    {
        super.setHttpProxyAuth(s, s1);
    }

    public volatile void setHttpReadTimeout(int i)
    {
        super.setHttpReadTimeout(i);
    }

    public volatile void setPassword(String s)
    {
        super.setPassword(s);
    }

    public volatile void setRequestHeader(String s, String s1)
    {
        super.setRequestHeader(s, s1);
    }

    public volatile void setRetryCount(int i)
    {
        super.setRetryCount(i);
    }

    public volatile void setRetryIntervalSecs(int i)
    {
        super.setRetryIntervalSecs(i);
    }

    public volatile void setSource(String s)
    {
        super.setSource(s);
    }

    public void setStatusListener(StatusListener statuslistener)
    {
        statusListener = statuslistener;
    }

    public volatile void setUserAgent(String s)
    {
        super.setUserAgent(s);
    }

    public volatile void setUserId(String s)
    {
        super.setUserId(s);
    }

    public void shadow(int i, int ai[])
        throws WeiboException
    {
        Object aobj[] = new Object[2];
        aobj[0] = Integer.valueOf(i);
        aobj[1] = ai;
        startHandler(new StreamHandlingThread(aobj) {

            public StatusStream getStream()
                throws WeiboException
            {
                return getShadowStream(((Integer)args[0]).intValue(), (int[])(int[])args[1]);
            }

            final WeiboStream this$0;

            
            {
                this$0 = WeiboStream.this;
                super(aobj);
            }
        }
);
    }

    public void spritzer()
        throws WeiboException
    {
        startHandler(new StreamHandlingThread(null) {

            public StatusStream getStream()
                throws WeiboException
            {
                return getSpritzerStream();
            }

            final WeiboStream this$0;

            
            {
                this$0 = WeiboStream.this;
                super(aobj);
            }
        }
);
    }

    public void track(String as[])
        throws WeiboException
    {
        startHandler(new StreamHandlingThread(as) {

            public StatusStream getStream()
                throws WeiboException
            {
                return getTrackStream(keywords);
            }

            final WeiboStream this$0;
            final String val$keywords[];

            
            {
                this$0 = WeiboStream.this;
                keywords = as;
                super(final_aobj);
            }
        }
);
    }

    private static final boolean DEBUG = Configuration.getDebug();
    private StreamHandlingThread handler;
    private int retryPerMinutes;
    private StatusListener statusListener;





}
