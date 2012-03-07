// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package weibo4andriod;

import java.util.List;

// Referenced classes of package weibo4andriod:
//            User, Status, DirectMessage, IDs, 
//            Trends, RateLimitStatus, WeiboException, QueryResult

public interface WeiboListener
{

    public abstract void blocked(User user);

    public abstract void created(User user);

    public abstract void createdBlock(User user);

    public abstract void createdFavorite(Status status);

    public abstract void createdFriendship(User user);

    public abstract void deletedDirectMessage(DirectMessage directmessage);

    public abstract void destroyed(User user);

    public abstract void destroyedBlock(User user);

    public abstract void destroyedDirectMessage(DirectMessage directmessage);

    public abstract void destroyedFavorite(Status status);

    public abstract void destroyedFriendship(User user);

    public abstract void destroyedStatus(Status status);

    public abstract void disabledNotification(User user);

    public abstract void enabledNotification(User user);

    public abstract void followed(User user);

    public abstract void gotBlockingUsers(List list);

    public abstract void gotBlockingUsersIDs(IDs ids);

    public abstract void gotCurrentTrends(Trends trends);

    public abstract void gotDailyTrends(List list);

    public abstract void gotDirectMessages(List list);

    public abstract void gotDowntimeSchedule(String s);

    public abstract void gotExists(boolean flag);

    public abstract void gotExistsBlock(boolean flag);

    public abstract void gotExistsFriendship(boolean flag);

    public abstract void gotFavorites(List list);

    public abstract void gotFeatured(List list);

    public abstract void gotFollowers(List list);

    public abstract void gotFollowersIDs(IDs ids);

    public abstract void gotFriends(List list);

    public abstract void gotFriendsIDs(IDs ids);

    public abstract void gotFriendsTimeline(List list);

    public abstract void gotHomeTimeline(List list);

    public abstract void gotMentions(List list);

    public abstract void gotPublicTimeline(List list);

    public abstract void gotRateLimitStatus(RateLimitStatus ratelimitstatus);

    public abstract void gotReplies(List list);

    public abstract void gotRetweetedByMe(List list);

    public abstract void gotRetweetedToMe(List list);

    public abstract void gotRetweetsOfMe(List list);

    public abstract void gotSentDirectMessages(List list);

    public abstract void gotShow(Status status);

    public abstract void gotShowStatus(Status status);

    public abstract void gotTrends(Trends trends);

    public abstract void gotUserDetail(User user);

    public abstract void gotUserTimeline(List list);

    public abstract void gotWeeklyTrends(List list);

    public abstract void left(User user);

    public abstract void onException(WeiboException weiboexception, int i);

    public abstract void retweetedStatus(Status status);

    public abstract void searched(QueryResult queryresult);

    public abstract void sentDirectMessage(DirectMessage directmessage);

    public abstract void tested(boolean flag);

    public abstract void unblocked(User user);

    public abstract void updated(Status status);

    public abstract void updatedDeliverlyDevice(User user);

    public abstract void updatedLocation(User user);

    public abstract void updatedProfile(User user);

    public abstract void updatedProfileColors(User user);

    public abstract void updatedStatus(Status status);
}
