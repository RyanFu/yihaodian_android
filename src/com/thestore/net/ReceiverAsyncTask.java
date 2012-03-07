// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.net;

import android.os.*;
import com.yihaodian.mobile.vo.address.GoodReceiverVO;
import java.util.List;

// Referenced classes of package com.thestore.net:
//            Receiver

public class ReceiverAsyncTask extends AsyncTask
{

    public ReceiverAsyncTask(Handler handler, int i)
    {
        result = -1;
        receiver = null;
        goodReceiverVO = null;
        goodReceiverList = null;
        provinceList = null;
        cityList = null;
        countyList = null;
        provinceId = Long.valueOf(0L);
        cityId = Long.valueOf(0L);
        myHandler = handler;
        type = i;
    }

    public ReceiverAsyncTask(GoodReceiverVO goodreceivervo, Handler handler, int i)
    {
        result = -1;
        receiver = null;
        goodReceiverVO = null;
        goodReceiverList = null;
        provinceList = null;
        cityList = null;
        countyList = null;
        provinceId = Long.valueOf(0L);
        cityId = Long.valueOf(0L);
        goodReceiverVO = goodreceivervo;
        myHandler = handler;
        type = i;
    }

    public ReceiverAsyncTask(Long long1, Handler handler, int i)
    {
        result = -1;
        receiver = null;
        goodReceiverVO = null;
        goodReceiverList = null;
        provinceList = null;
        cityList = null;
        countyList = null;
        provinceId = Long.valueOf(0L);
        cityId = Long.valueOf(0L);
        if(i != 0x7f090049) goto _L2; else goto _L1
_L1:
        provinceId = long1;
_L4:
        myHandler = handler;
        type = i;
        return;
_L2:
        if(i == 0x7f09004a)
            cityId = long1;
        if(true) goto _L4; else goto _L3
_L3:
    }

    @Override
	protected transient Object doInBackground(Object aobj[])
    {
        type;
        JVM INSTR tableswitch 2131296324 2131296330: default 301
    //                   2131296324 48
    //                   2131296325 78
    //                   2131296326 115
    //                   2131296327 152
    //                   2131296328 189
    //                   2131296329 219
    //                   2131296330 255;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L2:
        Object obj;
        receiver = new Receiver();
        goodReceiverList = receiver.getGoodReceiverListByToken();
        obj = goodReceiverList;
        break; /* Loop/switch isn't completed */
_L3:
        receiver = new Receiver(goodReceiverVO);
        result = receiver.insertGoodReceiverByToken();
        obj = Integer.valueOf(result);
        break; /* Loop/switch isn't completed */
_L4:
        receiver = new Receiver(goodReceiverVO);
        result = receiver.updateGoodReceiverByToken();
        obj = Integer.valueOf(result);
        break; /* Loop/switch isn't completed */
_L5:
        receiver = new Receiver(goodReceiverVO);
        result = receiver.deleteGoodReceiverByToken();
        obj = Integer.valueOf(result);
        break; /* Loop/switch isn't completed */
_L6:
        receiver = new Receiver();
        provinceList = receiver.getAllProvince();
        obj = provinceList;
        break; /* Loop/switch isn't completed */
_L7:
        receiver = new Receiver(provinceId, 0x7f090049);
        cityList = receiver.getCityByProvinceId();
        obj = cityList;
        break; /* Loop/switch isn't completed */
_L8:
        receiver = new Receiver(cityId, 0x7f09004a);
        countyList = receiver.getCountyByCityId();
        obj = countyList;
        break; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        exception.printStackTrace();
        obj = null;
        break; /* Loop/switch isn't completed */
_L1:
        obj = null;
        return obj;
    }

    @Override
	protected void onPostExecute(Object obj)
    {
        Message message;
        message = myHandler.obtainMessage();
        message.what = type;
        type;
        JVM INSTR tableswitch 2131296324 2131296330: default 64
    //                   2131296324 74
    //                   2131296325 85
    //                   2131296326 99
    //                   2131296327 113
    //                   2131296328 127
    //                   2131296329 138
    //                   2131296330 149;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
        myHandler.sendMessage(message);
        return;
_L2:
        message.obj = goodReceiverList;
        continue; /* Loop/switch isn't completed */
_L3:
        message.obj = Integer.valueOf(result);
        continue; /* Loop/switch isn't completed */
_L4:
        message.obj = Integer.valueOf(result);
        continue; /* Loop/switch isn't completed */
_L5:
        message.obj = Integer.valueOf(result);
        continue; /* Loop/switch isn't completed */
_L6:
        message.obj = provinceList;
        continue; /* Loop/switch isn't completed */
_L7:
        message.obj = cityList;
        continue; /* Loop/switch isn't completed */
_L8:
        message.obj = countyList;
        if(true) goto _L1; else goto _L9
_L9:
    }

    private Long cityId;
    private List cityList;
    private List countyList;
    private List goodReceiverList;
    private GoodReceiverVO goodReceiverVO;
    private Handler myHandler;
    private Long provinceId;
    private List provinceList;
    private Receiver receiver;
    private int result;
    private int type;
}
