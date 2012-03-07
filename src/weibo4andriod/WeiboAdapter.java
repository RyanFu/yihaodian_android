// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package weibo4andriod;

import java.util.List;

// Referenced classes of package weibo4andriod:
//            WeiboListener, User, Status, DirectMessage, 
//            IDs, Trends, RateLimitStatus, WeiboException, 
//            QueryResult

public class WeiboAdapter
    implements WeiboListener
{

    public WeiboAdapter()
    {
    }

    @Override
	public void blocked(User user)
    {
    }

    @Override
	public void created(User user)
    {
    }

    @Override
	public void createdBlock(User user)
    {
    }

    @Override
	public void createdFavorite(Status status)
    {
    }

    @Override
	public void createdFriendship(User user)
    {
    }

    @Override
	public void deletedDirectMessage(DirectMessage directmessage)
    {
    }

    @Override
	public void destroyed(User user)
    {
    }

    @Override
	public void destroyedBlock(User user)
    {
    }

    @Override
	public void destroyedDirectMessage(DirectMessage directmessage)
    {
    }

    @Override
	public void destroyedFavorite(Status status)
    {
    }

    @Override
	public void destroyedFriendship(User user)
    {
    }

    @Override
	public void destroyedStatus(Status status)
    {
    }

    @Override
	public void disabledNotification(User user)
    {
    }

    @Override
	public void enabledNotification(User user)
    {
    }

    @Override
	public void followed(User user)
    {
    }

    @Override
	public void gotBlockingUsers(List list)
    {
    }

    @Override
	public void gotBlockingUsersIDs(IDs ids)
    {
    }

    @Override
	public void gotCurrentTrends(Trends trends)
    {
    }

    @Override
	public void gotDailyTrends(List list)
    {
    }

    @Override
	public void gotDirectMessages(List list)
    {
    }

    @Override
	public void gotDowntimeSchedule(String s)
    {
    }

    @Override
	public void gotExists(boolean flag)
    {
    }

    @Override
	public void gotExistsBlock(boolean flag)
    {
    }

    @Override
	public void gotExistsFriendship(boolean flag)
    {
    }

    @Override
	public void gotFavorites(List list)
    {
    }

    @Override
	public void gotFeatured(List list)
    {
    }

    @Override
	public void gotFollowers(List list)
    {
    }

    @Override
	public void gotFollowersIDs(IDs ids)
    {
    }

    @Override
	public void gotFriends(List list)
    {
    }

    @Override
	public void gotFriendsIDs(IDs ids)
    {
    }

    @Override
	public void gotFriendsTimeline(List list)
    {
    }

    @Override
	public void gotHomeTimeline(List list)
    {
    }

    @Override
	public void gotMentions(List list)
    {
    }

    @Override
	public void gotPublicTimeline(List list)
    {
    }

    @Override
	public void gotRateLimitStatus(RateLimitStatus ratelimitstatus)
    {
    }

    @Override
	public void gotReplies(List list)
    {
    }

    @Override
	public void gotRetweetedByMe(List list)
    {
    }

    @Override
	public void gotRetweetedToMe(List list)
    {
    }

    @Override
	public void gotRetweetsOfMe(List list)
    {
    }

    @Override
	public void gotSentDirectMessages(List list)
    {
    }

    @Override
	public void gotShow(Status status)
    {
    }

    @Override
	public void gotShowStatus(Status status)
    {
    }

    @Override
	public void gotTrends(Trends trends)
    {
    }

    @Override
	public void gotUserDetail(User user)
    {
    }

    @Override
	public void gotUserTimeline(List list)
    {
    }

    @Override
	public void gotWeeklyTrends(List list)
    {
    }

    @Override
	public void left(User user)
    {
    }

    @Override
	public void onException(WeiboException weiboexception, int i)
    {
    }

    @Override
	public void retweetedStatus(Status status)
    {
    }

    @Override
	public void searched(QueryResult queryresult)
    {
    }

    @Override
	public void sentDirectMessage(DirectMessage directmessage)
    {
    }

    @Override
	public void tested(boolean flag)
    {
    }

    @Override
	public void unblocked(User user)
    {
    }

    @Override
	public void updated(Status status)
    {
    }

    @Override
	public void updatedDeliverlyDevice(User user)
    {
    }

    @Override
	public void updatedLocation(User user)
    {
    }

    @Override
	public void updatedProfile(User user)
    {
    }

    @Override
	public void updatedProfileColors(User user)
    {
    }

    @Override
	public void updatedStatus(Status status)
    {
    }
}
