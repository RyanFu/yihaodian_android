// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.net;

import android.os.*;
import com.yihaodian.mobile.vo.user.UserVO;
import com.yihaodian.mobile.vo.user.VerifyCodeVO;

// Referenced classes of package com.thestore.net:
//            User

public class UserAsyncTask extends AsyncTask
{

    public UserAsyncTask()
    {
        resultValue = "";
    }

    public UserAsyncTask(int i, Handler handler1)
    {
        resultValue = "";
        type = i;
        handler = handler1;
    }

    public UserAsyncTask(String s, Handler handler1, int i)
    {
        this(s, null, handler1, i);
    }

    public UserAsyncTask(String s, String s1, Handler handler1, int i)
    {
        resultValue = "";
        userName = s;
        password = s1;
        handler = handler1;
        type = i;
    }

    public UserAsyncTask(String s, String s1, String s2, Handler handler1, int i)
    {
        resultValue = "";
        userName = s;
        password = s1;
        newPwd = s2;
        handler = handler1;
        type = i;
    }

    @Override
	protected volatile Object doInBackground(Object aobj[])
    {
        return doInBackground(aobj);
    }

    protected transient String doInBackground(Object aobj[])
    {
        User user = new User(userName, password);
        type;
        JVM INSTR lookupswitch 8: default 279
    //                   2131296286: 96
    //                   2131296287: 105
    //                   2131296288: 132
    //                   2131296289: 146
    //                   2131296290: 215
    //                   2131296291: 201
    //                   2131296307: 173
    //                   2131296322: 187;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9
_L2:
        String s;
        s = user.login();
        break; /* Loop/switch isn't completed */
_L3:
        s = (new StringBuilder()).append(user.regist()).append("").toString();
        break; /* Loop/switch isn't completed */
_L4:
        verifyCodeVO = user.getVerify();
        s = null;
        break; /* Loop/switch isn't completed */
_L5:
        s = (new StringBuilder()).append(user.findPassword()).append("").toString();
        break; /* Loop/switch isn't completed */
_L8:
        userVO = user.getMyStore();
        s = null;
        break; /* Loop/switch isn't completed */
_L9:
        timeLong = user.getServerTimeStamp();
        s = null;
        break; /* Loop/switch isn't completed */
_L7:
        logout = user.getLogout();
        s = null;
        break; /* Loop/switch isn't completed */
_L6:
        User user1 = new User(userName, password, newPwd);
        resultValue = (new StringBuilder()).append(user1.modifyPassword()).append("").toString();
        s = resultValue;
        break; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        s = null;
        break; /* Loop/switch isn't completed */
_L1:
        s = null;
        return s;
    }

    @Override
	protected volatile void onPostExecute(Object obj)
    {
        onPostExecute((String)obj);
    }

    protected void onPostExecute(String s)
    {
        Message message;
        message = handler.obtainMessage();
        message.what = type;
        type;
        JVM INSTR lookupswitch 5: default 72
    //                   2131296288: 131
    //                   2131296290: 109
    //                   2131296291: 120
    //                   2131296307: 87
    //                   2131296322: 98;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        message.obj = s;
_L8:
        handler.sendMessage(message);
        return;
_L5:
        message.obj = userVO;
        continue; /* Loop/switch isn't completed */
_L6:
        message.obj = timeLong;
        continue; /* Loop/switch isn't completed */
_L3:
        message.obj = resultValue;
        continue; /* Loop/switch isn't completed */
_L4:
        message.obj = logout;
        continue; /* Loop/switch isn't completed */
_L2:
        message.obj = verifyCodeVO;
        if(true) goto _L8; else goto _L7
_L7:
    }

    private Handler handler;
    private Integer logout;
    private String newPwd;
    private String password;
    private String resultValue;
    private Long timeLong;
    private int type;
    private String userName;
    private UserVO userVO;
    private VerifyCodeVO verifyCodeVO;
}
