// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package weibo4andriod;

import java.util.Date;

// Referenced classes of package weibo4andriod:
//            Weibo, Dispatcher, Configuration, WeiboAdapter, 
//            WeiboException, Paging, WeiboListener, Query

public class AsyncWeibo extends Weibo
{
    abstract class AsyncTask
        implements Runnable
    {

        abstract void invoke(WeiboListener weibolistener, Object aobj[])
            throws WeiboException;

        public void run()
        {
            invoke(listener, args);
_L1:
            return;
            WeiboException weiboexception;
            weiboexception;
            if(listener != null)
                listener.onException(weiboexception, method);
              goto _L1
        }

        Object args[];
        WeiboListener listener;
        int method;
        final AsyncWeibo this$0;

        AsyncTask(int i, WeiboListener weibolistener, Object aobj[])
        {
            this$0 = AsyncWeibo.this;
            super();
            method = i;
            listener = weibolistener;
            args = aobj;
        }
    }


    public AsyncWeibo(String s, String s1)
    {
        super(s, s1);
        shutdown = false;
    }

    public AsyncWeibo(String s, String s1, String s2)
    {
        super(s, s1, s2);
        shutdown = false;
    }

    private Dispatcher getDispatcher()
    {
        if(shutdown)
            throw new IllegalStateException("Already shut down");
        if(dispatcher == null)
            dispatcher = new Dispatcher("Weibo4J Async Dispatcher", Configuration.getNumberOfAsyncThreads());
        return dispatcher;
    }

    public void blockAsync(String s)
    {
        Dispatcher dispatcher1 = getDispatcher();
        WeiboAdapter weiboadapter = new WeiboAdapter();
        String as[] = new String[1];
        as[0] = s;
        dispatcher1.invokeLater(new AsyncTask(22, weiboadapter, as) {

            public void invoke(WeiboListener weibolistener, Object aobj[])
                throws WeiboException
            {
                weibolistener.blocked(block((String)aobj[0]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void createAsync(String s)
    {
        Dispatcher dispatcher1 = getDispatcher();
        WeiboAdapter weiboadapter = new WeiboAdapter();
        String as[] = new String[1];
        as[0] = s;
        dispatcher1.invokeLater(new AsyncTask(12, weiboadapter, as) {

            public void invoke(WeiboListener weibolistener, Object aobj[])
                throws WeiboException
            {
                weibolistener.created(create((String)aobj[0]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void createAsync(String s, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        String as[] = new String[1];
        as[0] = s;
        dispatcher1.invokeLater(new AsyncTask(12, weibolistener, as) {

            public void invoke(WeiboListener weibolistener1, Object aobj[])
                throws WeiboException
            {
                weibolistener1.created(create((String)aobj[0]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void createBlockAsync(String s)
    {
        createBlockAsync(s, ((WeiboListener) (new WeiboAdapter())));
    }

    public void createBlockAsync(String s, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        String as[] = new String[1];
        as[0] = s;
        dispatcher1.invokeLater(new AsyncTask(43, weibolistener, as) {

            public void invoke(WeiboListener weibolistener1, Object aobj[])
                throws WeiboException
            {
                weibolistener1.createdBlock(createBlock((String)aobj[0]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void createFavoriteAsync(int i)
    {
        createFavoriteAsync(i);
    }

    public void createFavoriteAsync(int i, WeiboListener weibolistener)
    {
        createFavoriteAsync(i, weibolistener);
    }

    public void createFavoriteAsync(long l)
    {
        Dispatcher dispatcher1 = getDispatcher();
        WeiboAdapter weiboadapter = new WeiboAdapter();
        Object aobj[] = new Object[1];
        aobj[0] = Long.valueOf(l);
        dispatcher1.invokeLater(new AsyncTask(17, weiboadapter, aobj) {

            public void invoke(WeiboListener weibolistener, Object aobj1[])
                throws WeiboException
            {
                weibolistener.createdFavorite(createFavorite(((Long)aobj1[0]).longValue()));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void createFavoriteAsync(long l, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[1];
        aobj[0] = Long.valueOf(l);
        dispatcher1.invokeLater(new AsyncTask(17, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.createdFavorite(createFavorite(((Long)aobj1[0]).longValue()));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void createFriendshipAsync(String s)
    {
        createFriendshipAsync(s, ((WeiboListener) (new WeiboAdapter())));
    }

    public void createFriendshipAsync(String s, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        String as[] = new String[1];
        as[0] = s;
        dispatcher1.invokeLater(new AsyncTask(32, weibolistener, as) {

            public void invoke(WeiboListener weibolistener1, Object aobj[])
                throws WeiboException
            {
                weibolistener1.createdFriendship(createFriendship((String)aobj[0]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void createFriendshipAsync(String s, boolean flag, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[2];
        aobj[0] = s;
        aobj[1] = Boolean.valueOf(flag);
        dispatcher1.invokeLater(new AsyncTask(32, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.createdFriendship(createFriendship((String)aobj1[0], ((Boolean)aobj1[1]).booleanValue()));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void deleteDirectMessageAsync(int i, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(i);
        dispatcher1.invokeLater(new AsyncTask(40, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.deletedDirectMessage(deleteDirectMessage(((Integer)aobj1[0]).intValue()));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void destoryStatusAsync(int i)
    {
        destroyStatusAsync(i);
    }

    public void destoryStatusAsync(int i, WeiboListener weibolistener)
    {
        destroyStatusAsync(i, weibolistener);
    }

    public void destroyAsync(String s)
    {
        Dispatcher dispatcher1 = getDispatcher();
        WeiboAdapter weiboadapter = new WeiboAdapter();
        String as[] = new String[1];
        as[0] = s;
        dispatcher1.invokeLater(new AsyncTask(13, weiboadapter, as) {

            public void invoke(WeiboListener weibolistener, Object aobj[])
                throws WeiboException
            {
                weibolistener.destroyed(destroy((String)aobj[0]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void destroyAsync(String s, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        String as[] = new String[1];
        as[0] = s;
        dispatcher1.invokeLater(new AsyncTask(13, weibolistener, as) {

            public void invoke(WeiboListener weibolistener1, Object aobj[])
                throws WeiboException
            {
                weibolistener1.destroyed(destroy((String)aobj[0]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void destroyBlockAsync(String s)
    {
        destroyBlockAsync(s, ((WeiboListener) (new WeiboAdapter())));
    }

    public void destroyBlockAsync(String s, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        String as[] = new String[1];
        as[0] = s;
        dispatcher1.invokeLater(new AsyncTask(42, weibolistener, as) {

            public void invoke(WeiboListener weibolistener1, Object aobj[])
                throws WeiboException
            {
                weibolistener1.destroyedBlock(destroyBlock((String)aobj[0]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void destroyDirectMessageAsync(int i)
    {
        Dispatcher dispatcher1 = getDispatcher();
        WeiboAdapter weiboadapter = new WeiboAdapter();
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(i);
        dispatcher1.invokeLater(new AsyncTask(40, weiboadapter, aobj) {

            public void invoke(WeiboListener weibolistener, Object aobj1[])
                throws WeiboException
            {
                weibolistener.destroyedDirectMessage(destroyDirectMessage(((Integer)aobj1[0]).intValue()));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void destroyDirectMessageAsync(int i, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(i);
        dispatcher1.invokeLater(new AsyncTask(40, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.destroyedDirectMessage(destroyDirectMessage(((Integer)aobj1[0]).intValue()));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void destroyFavoriteAsync(int i)
    {
        destroyFavoriteAsync(i);
    }

    public void destroyFavoriteAsync(int i, WeiboListener weibolistener)
    {
        destroyFavoriteAsync(i, weibolistener);
    }

    public void destroyFavoriteAsync(long l)
    {
        Dispatcher dispatcher1 = getDispatcher();
        WeiboAdapter weiboadapter = new WeiboAdapter();
        Object aobj[] = new Object[1];
        aobj[0] = Long.valueOf(l);
        dispatcher1.invokeLater(new AsyncTask(17, weiboadapter, aobj) {

            public void invoke(WeiboListener weibolistener, Object aobj1[])
                throws WeiboException
            {
                weibolistener.destroyedFavorite(destroyFavorite(((Long)aobj1[0]).longValue()));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void destroyFavoriteAsync(long l, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[1];
        aobj[0] = Long.valueOf(l);
        dispatcher1.invokeLater(new AsyncTask(17, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.destroyedFavorite(destroyFavorite(((Long)aobj1[0]).longValue()));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void destroyFriendshipAsync(String s)
    {
        destroyFriendshipAsync(s, ((WeiboListener) (new WeiboAdapter())));
    }

    public void destroyFriendshipAsync(String s, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        String as[] = new String[1];
        as[0] = s;
        dispatcher1.invokeLater(new AsyncTask(33, weibolistener, as) {

            public void invoke(WeiboListener weibolistener1, Object aobj[])
                throws WeiboException
            {
                weibolistener1.destroyedFriendship(destroyFriendship((String)aobj[0]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void destroyStatusAsync(int i)
    {
        destroyStatusAsync(i);
    }

    public void destroyStatusAsync(int i, WeiboListener weibolistener)
    {
        destroyStatusAsync(i, weibolistener);
    }

    public void destroyStatusAsync(long l)
    {
        Dispatcher dispatcher1 = getDispatcher();
        WeiboAdapter weiboadapter = new WeiboAdapter();
        Long along[] = new Long[1];
        along[0] = Long.valueOf(l);
        dispatcher1.invokeLater(new AsyncTask(26, weiboadapter, along) {

            public void invoke(WeiboListener weibolistener, Object aobj[])
                throws WeiboException
            {
                weibolistener.destroyedStatus(destroyStatus(((Long)aobj[0]).longValue()));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void destroyStatusAsync(long l, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Long along[] = new Long[1];
        along[0] = Long.valueOf(l);
        dispatcher1.invokeLater(new AsyncTask(26, weibolistener, along) {

            public void invoke(WeiboListener weibolistener1, Object aobj[])
                throws WeiboException
            {
                weibolistener1.destroyedStatus(destroyStatus(((Long)aobj[0]).longValue()));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void disableNotificationAsync(String s)
    {
        disableNotificationAsync(s, ((WeiboListener) (new WeiboAdapter())));
    }

    public void disableNotificationAsync(String s, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        String as[] = new String[1];
        as[0] = s;
        dispatcher1.invokeLater(new AsyncTask(36, weibolistener, as) {

            public void invoke(WeiboListener weibolistener1, Object aobj[])
                throws WeiboException
            {
                weibolistener1.disabledNotification(disableNotification((String)aobj[0]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void enableNotificationAsync(String s)
    {
        enableNotificationAsync(s, ((WeiboListener) (new WeiboAdapter())));
    }

    public void enableNotificationAsync(String s, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        String as[] = new String[1];
        as[0] = s;
        dispatcher1.invokeLater(new AsyncTask(35, weibolistener, as) {

            public void invoke(WeiboListener weibolistener1, Object aobj[])
                throws WeiboException
            {
                weibolistener1.enabledNotification(enableNotification((String)aobj[0]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void existsAsync(String s, String s1, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        String as[] = new String[2];
        as[0] = s;
        as[1] = s1;
        dispatcher1.invokeLater(new AsyncTask(28, weibolistener, as) {

            public void invoke(WeiboListener weibolistener1, Object aobj[])
                throws WeiboException
            {
                weibolistener1.gotExists(exists((String)aobj[0], (String)aobj[1]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void existsBlockAsync(String s, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        String as[] = new String[1];
        as[0] = s;
        dispatcher1.invokeLater(new AsyncTask(48, weibolistener, as) {

            public void invoke(WeiboListener weibolistener1, Object aobj[])
                throws WeiboException
            {
                weibolistener1.gotExistsBlock(existsBlock((String)aobj[0]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void existsFriendshipAsync(String s, String s1, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        String as[] = new String[2];
        as[0] = s;
        as[1] = s1;
        dispatcher1.invokeLater(new AsyncTask(34, weibolistener, as) {

            public void invoke(WeiboListener weibolistener1, Object aobj[])
                throws WeiboException
            {
                weibolistener1.gotExistsFriendship(existsFriendship((String)aobj[0], (String)aobj[1]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void favoritesAsync(int i, WeiboListener weibolistener)
    {
        getFavoritesAsync(i, weibolistener);
    }

    public void favoritesAsync(String s, int i, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[2];
        aobj[0] = s;
        aobj[1] = Integer.valueOf(i);
        dispatcher1.invokeLater(new AsyncTask(17, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.gotFavorites(favorites((String)aobj1[0], ((Integer)aobj1[1]).intValue()));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void favoritesAsync(String s, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[1];
        aobj[0] = s;
        dispatcher1.invokeLater(new AsyncTask(17, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.gotFavorites(favorites((String)aobj1[0]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void favoritesAsync(WeiboListener weibolistener)
    {
        getFavoritesAsync(weibolistener);
    }

    public void followAsync(String s)
    {
        Dispatcher dispatcher1 = getDispatcher();
        WeiboAdapter weiboadapter = new WeiboAdapter();
        String as[] = new String[1];
        as[0] = s;
        dispatcher1.invokeLater(new AsyncTask(14, weiboadapter, as) {

            public void invoke(WeiboListener weibolistener, Object aobj[])
                throws WeiboException
            {
                weibolistener.followed(follow((String)aobj[0]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void followAsync(String s, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        String as[] = new String[1];
        as[0] = s;
        dispatcher1.invokeLater(new AsyncTask(14, weibolistener, as) {

            public void invoke(WeiboListener weibolistener1, Object aobj[])
                throws WeiboException
            {
                weibolistener1.followed(follow((String)aobj[0]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getAuthenticatedUserAsync(WeiboListener weibolistener)
    {
        if(getUserId() == null)
        {
            throw new IllegalStateException("User Id not specified.");
        } else
        {
            getUserDetailAsync(getUserId(), weibolistener);
            return;
        }
    }

    public void getBlockingUsersAsync(int i, WeiboListener weibolistener)
        throws WeiboException
    {
        Dispatcher dispatcher1 = getDispatcher();
        Integer ainteger[] = new Integer[1];
        ainteger[0] = Integer.valueOf(i);
        dispatcher1.invokeLater(new AsyncTask(49, weibolistener, ainteger) {

            public void invoke(WeiboListener weibolistener1, Object aobj[])
                throws WeiboException
            {
                weibolistener1.gotBlockingUsers(getBlockingUsers(((Integer)aobj[0]).intValue()));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getBlockingUsersAsync(WeiboListener weibolistener)
        throws WeiboException
    {
        getDispatcher().invokeLater(new AsyncTask(49, weibolistener, null) {

            public void invoke(WeiboListener weibolistener1, Object aobj[])
                throws WeiboException
            {
                weibolistener1.gotBlockingUsers(getBlockingUsers());
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getBlockingUsersIDsAsync(WeiboListener weibolistener)
        throws WeiboException
    {
        getDispatcher().invokeLater(new AsyncTask(50, weibolistener, null) {

            public void invoke(WeiboListener weibolistener1, Object aobj[])
                throws WeiboException
            {
                weibolistener1.gotBlockingUsersIDs(getBlockingUsersIDs());
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getCurrentTrendsAsync(WeiboListener weibolistener)
    {
        getDispatcher().invokeLater(new AsyncTask(45, weibolistener, null) {

            public void invoke(WeiboListener weibolistener1, Object aobj[])
                throws WeiboException
            {
                weibolistener1.gotCurrentTrends(getCurrentTrends());
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getCurrentTrendsAsync(boolean flag, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[1];
        aobj[0] = Boolean.valueOf(flag);
        dispatcher1.invokeLater(new AsyncTask(45, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.gotCurrentTrends(getCurrentTrends(((Boolean)aobj1[0]).booleanValue()));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getDailyTrendsAsync(Date date, boolean flag, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[2];
        aobj[0] = date;
        aobj[1] = Boolean.valueOf(flag);
        dispatcher1.invokeLater(new AsyncTask(46, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.gotDailyTrends(getDailyTrends((Date)aobj1[0], ((Boolean)aobj1[1]).booleanValue()));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getDailyTrendsAsync(WeiboListener weibolistener)
    {
        getDispatcher().invokeLater(new AsyncTask(46, weibolistener, null) {

            public void invoke(WeiboListener weibolistener1, Object aobj[])
                throws WeiboException
            {
                weibolistener1.gotDailyTrends(getDailyTrends());
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getDirectMessagesAsync(int i, WeiboListener weibolistener)
    {
        getDirectMessagesAsync(new Paging(i), weibolistener);
    }

    public void getDirectMessagesAsync(Date date, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[1];
        aobj[0] = date;
        dispatcher1.invokeLater(new AsyncTask(10, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.gotDirectMessages(getDirectMessages((Date)aobj1[0]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getDirectMessagesAsync(Paging paging, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[1];
        aobj[0] = paging;
        dispatcher1.invokeLater(new AsyncTask(10, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.gotDirectMessages(getDirectMessages((Paging)aobj1[0]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getDirectMessagesAsync(WeiboListener weibolistener)
    {
        getDispatcher().invokeLater(new AsyncTask(10, weibolistener, null) {

            public void invoke(WeiboListener weibolistener1, Object aobj[])
                throws WeiboException
            {
                weibolistener1.gotDirectMessages(getDirectMessages());
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getDirectMessagesByPageAsync(int i, int j, WeiboListener weibolistener)
    {
        getDirectMessagesAsync(new Paging(i, j), weibolistener);
    }

    public void getDirectMessagesByPageAsync(int i, WeiboListener weibolistener)
    {
        getDirectMessagesAsync(new Paging(i), weibolistener);
    }

    public void getDowntimeScheduleAsync()
    {
        throw new RuntimeException("this method is not supported by the Weibo API anymore", new NoSuchMethodException("this method is not supported by the Weibo API anymore"));
    }

    public void getFavoritesAsync(int i, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(i);
        dispatcher1.invokeLater(new AsyncTask(17, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.gotFavorites(getFavorites(((Integer)aobj1[0]).intValue()));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getFavoritesAsync(String s, int i, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[2];
        aobj[0] = s;
        aobj[1] = Integer.valueOf(i);
        dispatcher1.invokeLater(new AsyncTask(17, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.gotFavorites(getFavorites((String)aobj1[0], ((Integer)aobj1[1]).intValue()));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getFavoritesAsync(String s, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[1];
        aobj[0] = s;
        dispatcher1.invokeLater(new AsyncTask(17, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.gotFavorites(getFavorites((String)aobj1[0]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getFavoritesAsync(WeiboListener weibolistener)
    {
        getDispatcher().invokeLater(new AsyncTask(17, weibolistener, null) {

            public void invoke(WeiboListener weibolistener1, Object aobj[])
                throws WeiboException
            {
                weibolistener1.gotFavorites(getFavorites());
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getFeaturedAsync(WeiboListener weibolistener)
    {
        getDispatcher().invokeLater(new AsyncTask(8, weibolistener, null) {

            public void invoke(WeiboListener weibolistener1, Object aobj[])
                throws WeiboException
            {
                weibolistener1.gotFeatured(getFeatured());
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getFollowersAsync(int i, WeiboListener weibolistener)
    {
        getFollowersStatusesAsync(new Paging(i), weibolistener);
    }

    public void getFollowersAsync(String s, int i, WeiboListener weibolistener)
    {
        getFollowersStatusesAsync(s, new Paging(i), weibolistener);
    }

    public void getFollowersAsync(String s, Paging paging, WeiboListener weibolistener)
    {
        getFollowersStatusesAsync(s, paging, weibolistener);
    }

    public void getFollowersAsync(String s, WeiboListener weibolistener)
    {
        getFollowersStatusesAsync(s, weibolistener);
    }

    public void getFollowersAsync(Paging paging, WeiboListener weibolistener)
    {
        getFollowersStatusesAsync(paging, weibolistener);
    }

    public void getFollowersAsync(WeiboListener weibolistener)
    {
        getFollowersStatusesAsync(weibolistener);
    }

    public void getFollowersIDsAsync(int i, long l, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[2];
        aobj[0] = Integer.valueOf(i);
        aobj[1] = Long.valueOf(l);
        dispatcher1.invokeLater(new AsyncTask(30, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.gotFollowersIDs(getFollowersIDs(((Integer)aobj1[0]).intValue(), ((Long)aobj1[1]).longValue()));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getFollowersIDsAsync(int i, Paging paging, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[2];
        aobj[0] = Integer.valueOf(i);
        aobj[1] = paging;
        dispatcher1.invokeLater(new AsyncTask(30, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.gotFollowersIDs(getFollowersIDs(((Integer)aobj1[0]).intValue(), (Paging)aobj1[1]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getFollowersIDsAsync(int i, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Integer ainteger[] = new Integer[1];
        ainteger[0] = Integer.valueOf(i);
        dispatcher1.invokeLater(new AsyncTask(30, weibolistener, ainteger) {

            public void invoke(WeiboListener weibolistener1, Object aobj[])
                throws WeiboException
            {
                weibolistener1.gotFollowersIDs(getFollowersIDs(((Integer)aobj[0]).intValue()));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getFollowersIDsAsync(long l, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[1];
        aobj[0] = Long.valueOf(l);
        dispatcher1.invokeLater(new AsyncTask(30, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.gotFollowersIDs(getFollowersIDs(((Long)(Long)aobj1[0]).longValue()));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getFollowersIDsAsync(String s, long l, WeiboListener weibolistener)
        throws WeiboException
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[2];
        aobj[0] = s;
        aobj[1] = Long.valueOf(l);
        dispatcher1.invokeLater(new AsyncTask(30, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.gotFollowersIDs(getFollowersIDs((String)aobj1[0], ((Long)aobj1[1]).longValue()));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getFollowersIDsAsync(String s, Paging paging, WeiboListener weibolistener)
        throws WeiboException
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[2];
        aobj[0] = s;
        aobj[1] = paging;
        dispatcher1.invokeLater(new AsyncTask(30, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.gotFollowersIDs(getFollowersIDs((String)aobj1[0], (Paging)aobj1[1]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getFollowersIDsAsync(String s, WeiboListener weibolistener)
        throws WeiboException
    {
        Dispatcher dispatcher1 = getDispatcher();
        String as[] = new String[1];
        as[0] = s;
        dispatcher1.invokeLater(new AsyncTask(30, weibolistener, as) {

            public void invoke(WeiboListener weibolistener1, Object aobj[])
                throws WeiboException
            {
                weibolistener1.gotFollowersIDs(getFollowersIDs((String)aobj[0]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getFollowersIDsAsync(Paging paging, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[1];
        aobj[0] = paging;
        dispatcher1.invokeLater(new AsyncTask(30, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.gotFollowersIDs(getFollowersIDs((Paging)(Paging)aobj1[0]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getFollowersIDsAsync(WeiboListener weibolistener)
    {
        getDispatcher().invokeLater(new AsyncTask(30, weibolistener, null) {

            public void invoke(WeiboListener weibolistener1, Object aobj[])
                throws WeiboException
            {
                weibolistener1.gotFollowersIDs(getFollowersIDs());
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getFollowersStatusesAsync(String s, Paging paging, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[2];
        aobj[0] = s;
        aobj[1] = paging;
        dispatcher1.invokeLater(new AsyncTask(7, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.gotFollowers(getFollowersStatuses((String)aobj1[0], (Paging)aobj1[1]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getFollowersStatusesAsync(String s, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[1];
        aobj[0] = s;
        dispatcher1.invokeLater(new AsyncTask(7, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.gotFollowers(getFollowersStatuses((String)aobj1[0]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getFollowersStatusesAsync(Paging paging, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[1];
        aobj[0] = paging;
        dispatcher1.invokeLater(new AsyncTask(7, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.gotFollowers(getFollowersStatuses((Paging)aobj1[0]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getFollowersStatusesAsync(WeiboListener weibolistener)
    {
        getDispatcher().invokeLater(new AsyncTask(7, weibolistener, null) {

            public void invoke(WeiboListener weibolistener1, Object aobj[])
                throws WeiboException
            {
                weibolistener1.gotFollowers(getFollowers());
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getFriendsAsync(int i, WeiboListener weibolistener)
    {
        getFriendsStatusesAsync(new Paging(i), weibolistener);
    }

    public void getFriendsAsync(String s, int i, WeiboListener weibolistener)
    {
        getFriendsStatusesAsync(s, new Paging(i), weibolistener);
    }

    public void getFriendsAsync(String s, Paging paging, WeiboListener weibolistener)
    {
        getFriendsStatusesAsync(s, paging, weibolistener);
    }

    public void getFriendsAsync(String s, WeiboListener weibolistener)
    {
        getFriendsStatusesAsync(s, weibolistener);
    }

    public void getFriendsAsync(Paging paging, WeiboListener weibolistener)
    {
        getFriendsStatusesAsync(paging, weibolistener);
    }

    public void getFriendsAsync(WeiboListener weibolistener)
    {
        getFriendsStatusesAsync(weibolistener);
    }

    public void getFriendsIDsAsync(int i, long l, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[2];
        aobj[0] = Integer.valueOf(i);
        aobj[1] = Long.valueOf(l);
        dispatcher1.invokeLater(new AsyncTask(29, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.gotFriendsIDs(getFriendsIDs(((Integer)aobj1[0]).intValue(), ((Long)aobj1[1]).longValue()));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getFriendsIDsAsync(int i, Paging paging, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[2];
        aobj[0] = Integer.valueOf(i);
        aobj[1] = paging;
        dispatcher1.invokeLater(new AsyncTask(29, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.gotFriendsIDs(getFriendsIDs(((Integer)aobj1[0]).intValue(), (Paging)aobj1[1]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getFriendsIDsAsync(int i, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Integer ainteger[] = new Integer[1];
        ainteger[0] = Integer.valueOf(i);
        dispatcher1.invokeLater(new AsyncTask(29, weibolistener, ainteger) {

            public void invoke(WeiboListener weibolistener1, Object aobj[])
                throws WeiboException
            {
                weibolistener1.gotFriendsIDs(getFriendsIDs(((Integer)aobj[0]).intValue()));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getFriendsIDsAsync(long l, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[1];
        aobj[0] = Long.valueOf(l);
        dispatcher1.invokeLater(new AsyncTask(29, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.gotFriendsIDs(getFriendsIDs(((Long)aobj1[0]).longValue()));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getFriendsIDsAsync(String s, long l, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[2];
        aobj[0] = s;
        aobj[1] = Long.valueOf(l);
        dispatcher1.invokeLater(new AsyncTask(29, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.gotFriendsIDs(getFriendsIDs((String)aobj1[0], ((Long)aobj1[1]).longValue()));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getFriendsIDsAsync(String s, Paging paging, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[2];
        aobj[0] = s;
        aobj[1] = paging;
        dispatcher1.invokeLater(new AsyncTask(29, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.gotFriendsIDs(getFriendsIDs((String)aobj1[0], (Paging)aobj1[1]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getFriendsIDsAsync(String s, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        String as[] = new String[1];
        as[0] = s;
        dispatcher1.invokeLater(new AsyncTask(29, weibolistener, as) {

            public void invoke(WeiboListener weibolistener1, Object aobj[])
                throws WeiboException
            {
                weibolistener1.gotFriendsIDs(getFriendsIDs((String)aobj[0]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getFriendsIDsAsync(Paging paging, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[1];
        aobj[0] = paging;
        dispatcher1.invokeLater(new AsyncTask(29, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.gotFriendsIDs(getFriendsIDs((Paging)aobj1[0]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getFriendsIDsAsync(WeiboListener weibolistener)
    {
        getDispatcher().invokeLater(new AsyncTask(29, weibolistener, null) {

            public void invoke(WeiboListener weibolistener1, Object aobj[])
                throws WeiboException
            {
                weibolistener1.gotFriendsIDs(getFriendsIDs());
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getFriendsStatusesAsync(String s, Paging paging, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[2];
        aobj[0] = s;
        aobj[1] = paging;
        dispatcher1.invokeLater(new AsyncTask(6, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.gotFriends(getFriendsStatuses((String)aobj1[0], (Paging)aobj1[1]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getFriendsStatusesAsync(String s, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[1];
        aobj[0] = s;
        dispatcher1.invokeLater(new AsyncTask(6, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.gotFriends(getFriendsStatuses((String)aobj1[0]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getFriendsStatusesAsync(Paging paging, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[1];
        aobj[0] = paging;
        dispatcher1.invokeLater(new AsyncTask(6, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.gotFriends(getFriendsStatuses((Paging)aobj1[0]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getFriendsStatusesAsync(WeiboListener weibolistener)
    {
        getDispatcher().invokeLater(new AsyncTask(6, weibolistener, null) {

            public void invoke(WeiboListener weibolistener1, Object aobj[])
                throws WeiboException
            {
                weibolistener1.gotFriends(getFriendsStatuses());
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getFriendsTimelineAsync(int i, WeiboListener weibolistener)
    {
        getFriendsTimelineAsync(new Paging(i), weibolistener);
    }

    public void getFriendsTimelineAsync(long l, int i, WeiboListener weibolistener)
    {
        getFriendsTimelineAsync(new Paging(i, l), weibolistener);
    }

    public void getFriendsTimelineAsync(long l, String s, int i, WeiboListener weibolistener)
    {
        throw new IllegalStateException("The Weibo API is not supporting this method anymore");
    }

    public void getFriendsTimelineAsync(long l, WeiboListener weibolistener)
    {
        getFriendsTimelineAsync(new Paging(l), weibolistener);
    }

    public void getFriendsTimelineAsync(String s, int i, WeiboListener weibolistener)
    {
        throw new IllegalStateException("The Weibo API is not supporting this method anymore");
    }

    public void getFriendsTimelineAsync(String s, long l, WeiboListener weibolistener)
    {
        throw new IllegalStateException("The Weibo API is not supporting this method anymore");
    }

    public void getFriendsTimelineAsync(String s, Date date, WeiboListener weibolistener)
    {
        throw new IllegalStateException("The Weibo API is not supporting this method anymore");
    }

    public void getFriendsTimelineAsync(String s, Paging paging, WeiboListener weibolistener)
    {
        throw new IllegalStateException("The Weibo API is not supporting this method anymore");
    }

    public void getFriendsTimelineAsync(String s, WeiboListener weibolistener)
    {
        throw new IllegalStateException("The Weibo API is not supporting this method anymore");
    }

    public void getFriendsTimelineAsync(Date date, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[1];
        aobj[0] = date;
        dispatcher1.invokeLater(new AsyncTask(1, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.gotFriendsTimeline(getFriendsTimeline((Date)aobj1[0]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getFriendsTimelineAsync(Paging paging, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[1];
        aobj[0] = paging;
        dispatcher1.invokeLater(new AsyncTask(1, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.gotFriendsTimeline(getFriendsTimeline((Paging)aobj1[0]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getFriendsTimelineAsync(WeiboListener weibolistener)
    {
        getDispatcher().invokeLater(new AsyncTask(1, weibolistener, null) {

            public void invoke(WeiboListener weibolistener1, Object aobj[])
                throws WeiboException
            {
                weibolistener1.gotFriendsTimeline(getFriendsTimeline());
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getFriendsTimelineByPageAsync(int i, WeiboListener weibolistener)
    {
        getFriendsTimelineAsync(new Paging(i), weibolistener);
    }

    public void getFriendsTimelineByPageAsync(String s, int i, WeiboListener weibolistener)
    {
        throw new IllegalStateException("The Weibo API is not supporting this method anymore");
    }

    public void getHomeTimelineAsync(Paging paging, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[1];
        aobj[0] = paging;
        dispatcher1.invokeLater(new AsyncTask(51, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.gotHomeTimeline(getHomeTimeline((Paging)aobj1[0]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getHomeTimelineAsync(WeiboListener weibolistener)
    {
        getDispatcher().invokeLater(new AsyncTask(51, weibolistener, null) {

            public void invoke(WeiboListener weibolistener1, Object aobj[])
                throws WeiboException
            {
                weibolistener1.gotHomeTimeline(getHomeTimeline());
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getMentionsAsync(Paging paging, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[1];
        aobj[0] = paging;
        dispatcher1.invokeLater(new AsyncTask(37, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.gotMentions(getMentions((Paging)aobj1[0]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getMentionsAsync(WeiboListener weibolistener)
    {
        getDispatcher().invokeLater(new AsyncTask(37, weibolistener, null) {

            public void invoke(WeiboListener weibolistener1, Object aobj[])
                throws WeiboException
            {
                weibolistener1.gotMentions(getMentions());
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getPublicTimelineAsync(int i, WeiboListener weibolistener)
    {
        getPublicTimelineAsync(i, weibolistener);
    }

    public void getPublicTimelineAsync(long l, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Long along[] = new Long[1];
        along[0] = Long.valueOf(l);
        dispatcher1.invokeLater(new AsyncTask(0, weibolistener, along) {

            public void invoke(WeiboListener weibolistener1, Object aobj[])
                throws WeiboException
            {
                weibolistener1.gotPublicTimeline(getPublicTimeline(((Long)aobj[0]).longValue()));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getPublicTimelineAsync(WeiboListener weibolistener)
    {
        getDispatcher().invokeLater(new AsyncTask(0, weibolistener, null) {

            public void invoke(WeiboListener weibolistener1, Object aobj[])
                throws WeiboException
            {
                weibolistener1.gotPublicTimeline(getPublicTimeline());
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getRepliesAsync(int i, WeiboListener weibolistener)
    {
        getMentionsAsync(new Paging(i), weibolistener);
    }

    public void getRepliesAsync(long l, int i, WeiboListener weibolistener)
    {
        getMentionsAsync(new Paging(i, l), weibolistener);
    }

    public void getRepliesAsync(long l, WeiboListener weibolistener)
    {
        getMentionsAsync(new Paging(l), weibolistener);
    }

    public void getRepliesAsync(WeiboListener weibolistener)
    {
        getDispatcher().invokeLater(new AsyncTask(5, weibolistener, null) {

            public void invoke(WeiboListener weibolistener1, Object aobj[])
                throws WeiboException
            {
                weibolistener1.gotReplies(getReplies());
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getRepliesByPageAsync(int i, WeiboListener weibolistener)
    {
        getMentionsAsync(new Paging(i), weibolistener);
    }

    public void getRetweetedByMeAsync(Paging paging, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[1];
        aobj[0] = paging;
        dispatcher1.invokeLater(new AsyncTask(53, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.gotRetweetedByMe(getRetweetedByMe((Paging)aobj1[0]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getRetweetedByMeAsync(WeiboListener weibolistener)
    {
        getDispatcher().invokeLater(new AsyncTask(53, weibolistener, null) {

            public void invoke(WeiboListener weibolistener1, Object aobj[])
                throws WeiboException
            {
                weibolistener1.gotRetweetedByMe(getRetweetedByMe());
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getRetweetedToMeAsync(Paging paging, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[1];
        aobj[0] = paging;
        dispatcher1.invokeLater(new AsyncTask(54, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.gotRetweetedToMe(getRetweetedToMe((Paging)aobj1[0]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getRetweetedToMeAsync(WeiboListener weibolistener)
    {
        getDispatcher().invokeLater(new AsyncTask(54, weibolistener, null) {

            public void invoke(WeiboListener weibolistener1, Object aobj[])
                throws WeiboException
            {
                weibolistener1.gotRetweetedToMe(getRetweetedToMe());
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getRetweetsOfMeAsync(Paging paging, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[1];
        aobj[0] = paging;
        dispatcher1.invokeLater(new AsyncTask(55, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.gotRetweetsOfMe(getRetweetsOfMe((Paging)aobj1[0]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getRetweetsOfMeAsync(WeiboListener weibolistener)
    {
        getDispatcher().invokeLater(new AsyncTask(55, weibolistener, null) {

            public void invoke(WeiboListener weibolistener1, Object aobj[])
                throws WeiboException
            {
                weibolistener1.gotRetweetsOfMe(getRetweetsOfMe());
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getSentDirectMessagesAsync(int i, int j, WeiboListener weibolistener)
    {
        getSentDirectMessagesAsync(new Paging(i, j), weibolistener);
    }

    public void getSentDirectMessagesAsync(int i, WeiboListener weibolistener)
    {
        getSentDirectMessagesAsync(new Paging(i), weibolistener);
    }

    public void getSentDirectMessagesAsync(Date date, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[1];
        aobj[0] = date;
        dispatcher1.invokeLater(new AsyncTask(10, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.gotSentDirectMessages(getSentDirectMessages((Date)aobj1[0]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getSentDirectMessagesAsync(Paging paging, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[1];
        aobj[0] = paging;
        dispatcher1.invokeLater(new AsyncTask(10, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.gotSentDirectMessages(getSentDirectMessages((Paging)aobj1[0]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getSentDirectMessagesAsync(WeiboListener weibolistener)
    {
        getDispatcher().invokeLater(new AsyncTask(10, weibolistener, null) {

            public void invoke(WeiboListener weibolistener1, Object aobj[])
                throws WeiboException
            {
                weibolistener1.gotSentDirectMessages(getSentDirectMessages());
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getTrendsAsync(WeiboListener weibolistener)
    {
        getDispatcher().invokeLater(new AsyncTask(44, weibolistener, null) {

            public void invoke(WeiboListener weibolistener1, Object aobj[])
                throws WeiboException
            {
                weibolistener1.gotTrends(getTrends());
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getUserDetailAsync(String s, WeiboListener weibolistener)
    {
        showUserAsync(s, weibolistener);
    }

    public void getUserTimelineAsync(int i, long l, WeiboListener weibolistener)
    {
        getUserTimelineAsync((new Paging(l)).count(i), weibolistener);
    }

    public void getUserTimelineAsync(int i, Date date, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[2];
        aobj[0] = Integer.valueOf(i);
        aobj[1] = date;
        dispatcher1.invokeLater(new AsyncTask(2, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.gotUserTimeline(getUserTimeline(((Integer)aobj1[0]).intValue(), (Date)aobj1[1]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getUserTimelineAsync(long l, WeiboListener weibolistener)
    {
        getUserTimelineAsync(new Paging(l), weibolistener);
    }

    public void getUserTimelineAsync(String s, int i, long l, WeiboListener weibolistener)
    {
        getUserTimelineAsync(s, new Paging(i, l), weibolistener);
    }

    public void getUserTimelineAsync(String s, int i, Date date, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[3];
        aobj[0] = s;
        aobj[1] = Integer.valueOf(i);
        aobj[2] = date;
        dispatcher1.invokeLater(new AsyncTask(2, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.gotUserTimeline(getUserTimeline((String)aobj1[0], ((Integer)aobj1[1]).intValue(), (Date)aobj1[2]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getUserTimelineAsync(String s, int i, WeiboListener weibolistener)
    {
        getUserTimelineAsync(s, (new Paging()).count(i), weibolistener);
    }

    public void getUserTimelineAsync(String s, long l, WeiboListener weibolistener)
    {
        getUserTimelineAsync(s, new Paging(l), weibolistener);
    }

    public void getUserTimelineAsync(String s, Date date, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[2];
        aobj[0] = s;
        aobj[1] = date;
        dispatcher1.invokeLater(new AsyncTask(2, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.gotUserTimeline(getUserTimeline((String)aobj1[0], (Date)aobj1[1]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getUserTimelineAsync(String s, Paging paging, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[2];
        aobj[0] = s;
        aobj[1] = paging;
        dispatcher1.invokeLater(new AsyncTask(2, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.gotUserTimeline(getUserTimeline((String)aobj1[0], (Paging)aobj1[1]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getUserTimelineAsync(String s, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[1];
        aobj[0] = s;
        dispatcher1.invokeLater(new AsyncTask(2, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.gotUserTimeline(getUserTimeline((String)aobj1[0]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getUserTimelineAsync(Paging paging, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[1];
        aobj[0] = paging;
        dispatcher1.invokeLater(new AsyncTask(2, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.gotUserTimeline(getUserTimeline((Paging)aobj1[0]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getUserTimelineAsync(WeiboListener weibolistener)
    {
        getDispatcher().invokeLater(new AsyncTask(2, weibolistener, null) {

            public void invoke(WeiboListener weibolistener1, Object aobj[])
                throws WeiboException
            {
                weibolistener1.gotUserTimeline(getUserTimeline());
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getWeeklyTrendsAsync(Date date, boolean flag, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[2];
        aobj[0] = date;
        aobj[1] = Boolean.valueOf(flag);
        dispatcher1.invokeLater(new AsyncTask(47, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.gotWeeklyTrends(getWeeklyTrends((Date)aobj1[0], ((Boolean)aobj1[1]).booleanValue()));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void getWeeklyTrendsAsync(WeiboListener weibolistener)
    {
        getDispatcher().invokeLater(new AsyncTask(47, weibolistener, null) {

            public void invoke(WeiboListener weibolistener1, Object aobj[])
                throws WeiboException
            {
                weibolistener1.gotWeeklyTrends(getWeeklyTrends());
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void leaveAsync(String s)
    {
        Dispatcher dispatcher1 = getDispatcher();
        WeiboAdapter weiboadapter = new WeiboAdapter();
        String as[] = new String[1];
        as[0] = s;
        dispatcher1.invokeLater(new AsyncTask(15, weiboadapter, as) {

            public void invoke(WeiboListener weibolistener, Object aobj[])
                throws WeiboException
            {
                weibolistener.left(leave((String)aobj[0]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void leaveAsync(String s, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        String as[] = new String[1];
        as[0] = s;
        dispatcher1.invokeLater(new AsyncTask(15, weibolistener, as) {

            public void invoke(WeiboListener weibolistener1, Object aobj[])
                throws WeiboException
            {
                weibolistener1.left(leave((String)aobj[0]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void rateLimitStatusAsync(WeiboListener weibolistener)
    {
        getDispatcher().invokeLater(new AsyncTask(28, weibolistener, new Object[0]) {

            public void invoke(WeiboListener weibolistener1, Object aobj[])
                throws WeiboException
            {
                weibolistener1.gotRateLimitStatus(rateLimitStatus());
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void retweetStatusAsync(long l)
    {
        retweetStatusAsync(l, ((WeiboListener) (new WeiboAdapter())));
    }

    public void retweetStatusAsync(long l, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Long along[] = new Long[1];
        along[0] = Long.valueOf(l);
        dispatcher1.invokeLater(new AsyncTask(52, weibolistener, along) {

            public void invoke(WeiboListener weibolistener1, Object aobj[])
                throws WeiboException
            {
                weibolistener1.retweetedStatus(retweetStatus(((Long)aobj[0]).longValue()));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void searchAcync(Query query, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[1];
        aobj[0] = query;
        dispatcher1.invokeLater(new AsyncTask(27, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.searched(search((Query)aobj1[0]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void sendDirectMessageAsync(String s, String s1)
    {
        Dispatcher dispatcher1 = getDispatcher();
        WeiboAdapter weiboadapter = new WeiboAdapter();
        String as[] = new String[2];
        as[0] = s;
        as[1] = s1;
        dispatcher1.invokeLater(new AsyncTask(11, weiboadapter, as) {

            public void invoke(WeiboListener weibolistener, Object aobj[])
                throws WeiboException
            {
                weibolistener.sentDirectMessage(sendDirectMessage((String)aobj[0], (String)aobj[1]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void sendDirectMessageAsync(String s, String s1, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        String as[] = new String[2];
        as[0] = s;
        as[1] = s1;
        dispatcher1.invokeLater(new AsyncTask(11, weibolistener, as) {

            public void invoke(WeiboListener weibolistener1, Object aobj[])
                throws WeiboException
            {
                weibolistener1.sentDirectMessage(sendDirectMessage((String)aobj[0], (String)aobj[1]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void showAsync(int i, WeiboListener weibolistener)
    {
        showAsync(i, weibolistener);
    }

    public void showAsync(long l, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[1];
        aobj[0] = Long.valueOf(l);
        dispatcher1.invokeLater(new AsyncTask(3, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.gotShow(show(((Long)aobj1[0]).longValue()));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void showStatusAsync(long l, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[1];
        aobj[0] = Long.valueOf(l);
        dispatcher1.invokeLater(new AsyncTask(38, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.gotShowStatus(showStatus(((Long)aobj1[0]).longValue()));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void showUserAsync(String s, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[1];
        aobj[0] = s;
        dispatcher1.invokeLater(new AsyncTask(9, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.gotUserDetail(showUser((String)aobj1[0]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void shutdown()
    {
        weibo4andriod/AsyncWeibo;
        JVM INSTR monitorenter ;
        shutdown = true;
        if(true)
            throw new IllegalStateException("Already shut down");
        break MISSING_BLOCK_LABEL_29;
        Exception exception;
        exception;
        throw exception;
        getDispatcher().shutdown();
        dispatcher = null;
        shutdown = true;
        weibo4andriod/AsyncWeibo;
        JVM INSTR monitorexit ;
    }

    public void testAsync()
    {
        getDispatcher().invokeLater(new AsyncTask(24, new WeiboAdapter(), new Object[0]) {

            public void invoke(WeiboListener weibolistener, Object aobj[])
                throws WeiboException
            {
                weibolistener.tested(test());
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void unblockAsync(String s)
    {
        Dispatcher dispatcher1 = getDispatcher();
        WeiboAdapter weiboadapter = new WeiboAdapter();
        String as[] = new String[1];
        as[0] = s;
        dispatcher1.invokeLater(new AsyncTask(23, weiboadapter, as) {

            public void invoke(WeiboListener weibolistener, Object aobj[])
                throws WeiboException
            {
                weibolistener.unblocked(unblock((String)aobj[0]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void updateAsync(String s)
    {
        Dispatcher dispatcher1 = getDispatcher();
        WeiboAdapter weiboadapter = new WeiboAdapter();
        String as[] = new String[1];
        as[0] = s;
        dispatcher1.invokeLater(new AsyncTask(4, weiboadapter, as) {

            public void invoke(WeiboListener weibolistener, Object aobj[])
                throws WeiboException
            {
                weibolistener.updated(update((String)aobj[0]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void updateAsync(String s, long l)
    {
        Dispatcher dispatcher1 = getDispatcher();
        WeiboAdapter weiboadapter = new WeiboAdapter();
        Object aobj[] = new Object[2];
        aobj[0] = s;
        aobj[1] = Long.valueOf(l);
        dispatcher1.invokeLater(new AsyncTask(4, weiboadapter, aobj) {

            public void invoke(WeiboListener weibolistener, Object aobj1[])
                throws WeiboException
            {
                weibolistener.updated(update((String)aobj1[0], ((Long)aobj1[1]).longValue()));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void updateAsync(String s, long l, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[2];
        aobj[0] = s;
        aobj[1] = Long.valueOf(l);
        dispatcher1.invokeLater(new AsyncTask(4, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.updated(update((String)aobj1[0], ((Long)aobj1[1]).longValue()));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void updateAsync(String s, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        String as[] = new String[1];
        as[0] = s;
        dispatcher1.invokeLater(new AsyncTask(4, weibolistener, as) {

            public void invoke(WeiboListener weibolistener1, Object aobj[])
                throws WeiboException
            {
                weibolistener1.updated(update((String)aobj[0]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void updateDeliverlyDeviceAsync(Weibo.Device device, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[1];
        aobj[0] = device;
        dispatcher1.invokeLater(new AsyncTask(20, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.updatedDeliverlyDevice(updateDeliverlyDevice((Weibo.Device)aobj1[0]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void updateLocationAsync(String s, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[1];
        aobj[0] = s;
        dispatcher1.invokeLater(new AsyncTask(20, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.updatedLocation(updateLocation((String)aobj1[0]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void updateProfileAsync(String s, String s1, String s2, String s3, String s4)
    {
        updateProfileAsync(s, s1, s2, s3, s4, ((WeiboListener) (new WeiboAdapter())));
    }

    public void updateProfileAsync(String s, String s1, String s2, String s3, String s4, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        String as[] = new String[5];
        as[0] = s;
        as[1] = s1;
        as[2] = s2;
        as[3] = s3;
        as[4] = s4;
        dispatcher1.invokeLater(new AsyncTask(41, weibolistener, as) {

            public void invoke(WeiboListener weibolistener1, Object aobj[])
                throws WeiboException
            {
                weibolistener1.updatedProfile(updateProfile((String)aobj[0], (String)aobj[1], (String)aobj[2], (String)aobj[3], (String)aobj[4]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void updateProfileColorsAsync(String s, String s1, String s2, String s3, String s4)
    {
        updateProfileColorsAsync(s, s1, s2, s3, s4, ((WeiboListener) (new WeiboAdapter())));
    }

    public void updateProfileColorsAsync(String s, String s1, String s2, String s3, String s4, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[5];
        aobj[0] = s;
        aobj[1] = s1;
        aobj[2] = s2;
        aobj[3] = s3;
        aobj[4] = s4;
        dispatcher1.invokeLater(new AsyncTask(31, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.updatedProfileColors(updateProfileColors((String)aobj1[0], (String)aobj1[1], (String)aobj1[2], (String)aobj1[3], (String)aobj1[4]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void updateStatusAsync(String s)
    {
        Dispatcher dispatcher1 = getDispatcher();
        WeiboAdapter weiboadapter = new WeiboAdapter();
        String as[] = new String[1];
        as[0] = s;
        dispatcher1.invokeLater(new AsyncTask(39, weiboadapter, as) {

            public void invoke(WeiboListener weibolistener, Object aobj[])
                throws WeiboException
            {
                weibolistener.updatedStatus(updateStatus((String)aobj[0]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void updateStatusAsync(String s, long l)
    {
        Dispatcher dispatcher1 = getDispatcher();
        WeiboAdapter weiboadapter = new WeiboAdapter();
        Object aobj[] = new Object[2];
        aobj[0] = s;
        aobj[1] = Long.valueOf(l);
        dispatcher1.invokeLater(new AsyncTask(39, weiboadapter, aobj) {

            public void invoke(WeiboListener weibolistener, Object aobj1[])
                throws WeiboException
            {
                weibolistener.updatedStatus(updateStatus((String)aobj1[0], ((Long)aobj1[1]).longValue()));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void updateStatusAsync(String s, long l, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        Object aobj[] = new Object[2];
        aobj[0] = s;
        aobj[1] = Long.valueOf(l);
        dispatcher1.invokeLater(new AsyncTask(39, weibolistener, aobj) {

            public void invoke(WeiboListener weibolistener1, Object aobj1[])
                throws WeiboException
            {
                weibolistener1.updatedStatus(updateStatus((String)aobj1[0], ((Long)aobj1[1]).longValue()));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public void updateStatusAsync(String s, WeiboListener weibolistener)
    {
        Dispatcher dispatcher1 = getDispatcher();
        String as[] = new String[1];
        as[0] = s;
        dispatcher1.invokeLater(new AsyncTask(39, weibolistener, as) {

            public void invoke(WeiboListener weibolistener1, Object aobj[])
                throws WeiboException
            {
                weibolistener1.updatedStatus(updateStatus((String)aobj[0]));
            }

            final AsyncWeibo this$0;

            
            {
                this$0 = AsyncWeibo.this;
                super(i, weibolistener, aobj);
            }
        }
);
    }

    public static final int BLOCK = 22;
    public static final int CREATE = 12;
    public static final int CREATED_BLOCK = 43;
    public static final int CREATE_FAVORITE = 18;
    public static final int CREATE_FRIENDSHIP = 32;
    public static final int CURRENT_TRENDS = 45;
    public static final int DAILY_TRENDS = 46;
    public static final int DESTORY = 13;
    public static final int DESTROY = 13;
    public static final int DESTROYED_BLOCK = 42;
    public static final int DESTROY_DIRECT_MESSAGES = 40;
    public static final int DESTROY_FAVORITE = 19;
    public static final int DESTROY_FRIENDSHIP = 33;
    public static final int DESTROY_STATUS = 26;
    public static final int DIRECT_MESSAGES = 10;
    public static final int DISABLE_NOTIFICATION = 36;
    public static final int ENABLE_NOTIFICATION = 35;
    public static final int EXISTS = 28;
    private static final int EXISTS_BLOCK = 48;
    public static final int EXISTS_FRIENDSHIP = 34;
    public static final int FAVORITES = 17;
    public static final int FEATURED = 8;
    public static final int FOLLOW = 14;
    public static final int FOLLOWERS = 7;
    public static final int FOLLOWERS_IDS = 30;
    public static final int FRIENDS = 6;
    public static final int FRIENDS_IDS = 29;
    public static final int FRIENDS_TIMELINE = 1;
    private static final int GET_BLOCKING_USERS = 49;
    private static final int GET_BLOCKING_USERS_IDS = 50;
    public static final int GET_DOWNTIME_SCHEDULE = 25;
    public static final int HOME_TIMELINE = 51;
    public static final int LEAVE = 15;
    public static final int MENTIONS = 37;
    public static final int PUBLIC_TIMELINE = 0;
    public static final int RATE_LIMIT_STATUS = 28;
    public static final int REPLIES = 5;
    public static final int RETWEETED_BY_ME = 53;
    public static final int RETWEETED_TO_ME = 54;
    public static final int RETWEETS_OF_ME = 55;
    public static final int RETWEET_STATUS = 52;
    public static final int SEARCH = 27;
    public static final int SEND_DIRECT_MESSAGE = 11;
    public static final int SHOW = 3;
    public static final int SHOW_STATUS = 38;
    public static final int TEST = 24;
    public static final int TRENDS = 44;
    public static final int UNBLOCK = 23;
    public static final int UPDATE = 4;
    public static final int UPDATE_DELIVERLY_DEVICE = 21;
    public static final int UPDATE_LOCATION = 20;
    public static final int UPDATE_PROFILE = 41;
    public static final int UPDATE_PROFILE_COLORS = 31;
    public static final int UPDATE_STATUS = 39;
    public static final int USER_DETAIL = 9;
    public static final int USER_TIMELINE = 2;
    public static final int WEEKLY_TRENDS = 47;
    private static transient Dispatcher dispatcher;
    private static final long serialVersionUID = 0xe41fc728386718fdL;
    private boolean shutdown;
}
