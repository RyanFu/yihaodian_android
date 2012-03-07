// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.main;

import android.content.*;
import android.graphics.Color;
import android.os.*;
import android.view.*;
import android.widget.*;
import com.thestore.net.*;
import com.thestore.util.Util;
import com.yihaodian.mobile.vo.address.*;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.thestore.main:
//            ActivityMain

public class GoodReceiver extends ActivityMain
{
    private class ReceiverAdapter extends BaseAdapter
    {

        public int getCount()
        {
            return goodReceiverList.size();
        }

        public Object getItem(int i)
        {
            return Integer.valueOf(i);
        }

        public long getItemId(int i)
        {
            return (long)i;
        }

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            inflater = LayoutInflater.from(context);
            View view1 = inflater.inflate(0x7f030030, null);
            View view2 = view1.findViewById(0x7f0901a1);
            if(!((Boolean)isShowList.get(i)).booleanValue());
            view2.setBackgroundResource(0);
            TextView textview = (TextView)view1.findViewById(0x7f0901a2);
            ImageView imageview = (ImageView)view1.findViewById(0x7f0901a3);
            String s = "";
            if(((GoodReceiverVO)goodReceiverList.get(i)).getReceiverMobile() != null && ((GoodReceiverVO)goodReceiverList.get(i)).getReceiverMobile().length() > 0)
                s = (new StringBuilder()).append(s).append("\n").append(((GoodReceiverVO)goodReceiverList.get(i)).getReceiverMobile()).toString();
            if(((GoodReceiverVO)goodReceiverList.get(i)).getReceiverPhone() != null && ((GoodReceiverVO)goodReceiverList.get(i)).getReceiverPhone().length() > 0)
                s = (new StringBuilder()).append(s).append("\n").append(((GoodReceiverVO)goodReceiverList.get(i)).getReceiverPhone()).toString();
            textview.setText((new StringBuilder()).append(((GoodReceiverVO)goodReceiverList.get(i)).getReceiveName()).append("\n").append(((GoodReceiverVO)goodReceiverList.get(i)).getRecordName()).append("\n").append(((GoodReceiverVO)goodReceiverList.get(i)).getProvinceName()).append("   ").append(((GoodReceiverVO)goodReceiverList.get(i)).getCityName()).append("   ").append(((GoodReceiverVO)goodReceiverList.get(i)).getCountyName()).append("\n").append(((GoodReceiverVO)goodReceiverList.get(i)).getPostCode()).append(s).toString());
            if(type == 2)
            {
                if(((GoodReceiverVO)goodReceiverList.get(i)).getId() == updateGoodReceiverVO.getId())
                {
                    textview.setTextColor(Color.rgb(115, 77, 1));
                    imageview.setVisibility(0);
                } else
                {
                    textview.setTextColor(Color.rgb(51, 51, 51));
                    imageview.setVisibility(8);
                }
                type = -1;
            } else
            if(((GoodReceiverVO)goodReceiverList.get(i)).getId().longValue() == goodReceiverId)
            {
                textview.setTextColor(Color.rgb(115, 77, 1));
                imageview.setVisibility(0);
            } else
            {
                textview.setTextColor(Color.rgb(51, 51, 51));
                imageview.setVisibility(8);
            }
            return view1;
        }

        private Context context;
        private LayoutInflater inflater;
        final GoodReceiver this$0;

        public ReceiverAdapter(Context context1)
        {
            this$0 = GoodReceiver.this;
            super();
            context = context1;
        }
    }


    public GoodReceiver()
    {
        result = 0;
        first_01 = true;
        provinceFirst = true;
        cityFirst = true;
        countyFirst = true;
        index = -1;
        type = 0;
        setReveiverType = -1;
        showAddrLayout = null;
        addressListView = null;
        addTextView = null;
        msgTextView = null;
        editAddrLayout = null;
        receviceNameEditText = null;
        provinceSpinner = null;
        citySpinner = null;
        countySpinner = null;
        recordNameEditText = null;
        postCodeTextView = null;
        mobileEditText = null;
        phoneEditText = null;
        editButton = null;
        adapter = null;
        goodReceiverList = null;
        goodReceiverVO = null;
        updateGoodReceiverVO = null;
        editGoodReceiverVO = null;
        delGoodReceiverVO = null;
        provinceList = null;
        tmpProvinceVO = null;
        tmpProvinceList = null;
        provinceNameList = null;
        cityList = null;
        tmpCityVO = null;
        tmpCityList = null;
        cityNameList = null;
        countyList = null;
        tmpCountyVO = null;
        tmpCountyList = null;
        countyNameList = null;
        provinceAdapter = null;
        cityAdapter = null;
        countyAdapter = null;
        provinceId = 0L;
        cityId = 0L;
        countyId = 0L;
        goodReceiverId = -1L;
        isShowList = new ArrayList();
        myHandler = new Handler() {

            public void handleMessage(Message message)
            {
                message.what;
                JVM INSTR tableswitch 2131296316 2131296330: default 80
            //                           2131296316 2905
            //                           2131296317 80
            //                           2131296318 80
            //                           2131296319 80
            //                           2131296320 80
            //                           2131296321 80
            //                           2131296322 80
            //                           2131296323 80
            //                           2131296324 81
            //                           2131296325 284
            //                           2131296326 401
            //                           2131296327 527
            //                           2131296328 644
            //                           2131296329 1209
            //                           2131296330 2164;
                   goto _L1 _L2 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L3 _L4 _L5 _L6 _L7 _L8 _L9
_L1:
                return;
_L3:
                if(message.obj != null)
                {
                    goodReceiverList = (List)message.obj;
                    if(goodReceiverList != null && goodReceiverList.size() > 0)
                    {
                        for(int j3 = 0; j3 < goodReceiverList.size(); j3++)
                            isShowList.add(j3, Boolean.valueOf(false));

                        msgTextView.setVisibility(0);
                        addressListView.setVisibility(0);
                        adapter = new ReceiverAdapter(GoodReceiver.this);
                        addressListView.setAdapter(adapter);
                    } else
                    {
                        msgTextView.setVisibility(8);
                        addressListView.setVisibility(8);
                    }
                } else
                {
                    util.showNetNull();
                }
                cancelProgress();
                continue; /* Loop/switch isn't completed */
_L4:
                if(message.obj != null)
                {
                    result = ((Integer)message.obj).intValue();
                    cancelProgress();
                    if(result == 1)
                    {
                        showToast("\u6DFB\u52A0\u6210\u529F\uFF01");
                        showReceiver();
                    } else
                    if(result == 0)
                        showToast("\u6DFB\u52A0\u5931\u8D25\uFF01");
                    else
                        util.showNetNull();
                } else
                {
                    cancelProgress();
                    util.showNetNull();
                }
                continue; /* Loop/switch isn't completed */
_L5:
                if(message.obj != null)
                {
                    result = ((Integer)message.obj).intValue();
                    cancelProgress();
                    if(result == 1)
                    {
                        showToast("\u66F4\u65B0\u6210\u529F\uFF01");
                        editGoodReceiverVO = null;
                        showReceiver();
                    } else
                    if(result == 0)
                        showToast("\u66F4\u65B0\u5931\u8D25\uFF01");
                    else
                        util.showNetNull();
                } else
                {
                    cancelProgress();
                    util.showNetNull();
                }
                continue; /* Loop/switch isn't completed */
_L6:
                if(message.obj != null)
                {
                    result = ((Integer)message.obj).intValue();
                    cancelProgress();
                    if(result == 1)
                    {
                        showToast("\u5220\u9664\u6210\u529F\uFF01");
                        initAddressView();
                    } else
                    if(result == 0)
                        showToast("\u5220\u9664\u5931\u8D25\uFF01");
                    else
                        util.showNetNull();
                } else
                {
                    cancelProgress();
                    util.showNetNull();
                }
                continue; /* Loop/switch isn't completed */
_L7:
                if(message.obj == null) goto _L11; else goto _L10
_L10:
                provinceList = (List)message.obj;
                if(provinceList.size() <= 0) goto _L13; else goto _L12
_L12:
                if(type != 0) goto _L15; else goto _L14
_L14:
                for(int i3 = 0; i3 < provinceList.size(); i3++)
                    provinceNameList.add(((ProvinceVO)provinceList.get(i3)).getProvinceName());

                ArrayAdapter arrayadapter6 = new ArrayAdapter(GoodReceiver.this, 0x1090008, provinceNameList);
                arrayadapter6.setDropDownViewResource(0x1090009);
                provinceSpinner.setAdapter(arrayadapter6);
_L16:
                cancelProgress();
                continue; /* Loop/switch isn't completed */
_L15:
                if(editGoodReceiverVO != null && type == 1)
                {
                    for(int k2 = 0; k2 < provinceList.size(); k2++)
                        if(!((ProvinceVO)provinceList.get(k2)).getProvinceName().equals(editGoodReceiverVO.getProvinceName()))
                        {
                            tmpProvinceVO = new ProvinceVO();
                            tmpProvinceVO.setId(((ProvinceVO)provinceList.get(k2)).getId());
                            tmpProvinceVO.setProvinceName(((ProvinceVO)provinceList.get(k2)).getProvinceName());
                            tmpProvinceList.add(tmpProvinceVO);
                        }

                    int l2 = 0;
                    while(l2 < tmpProvinceList.size()) 
                    {
                        if(!((ProvinceVO)tmpProvinceList.get(l2)).getProvinceName().equals(editGoodReceiverVO.getProvinceName()))
                            provinceNameList.add(((ProvinceVO)tmpProvinceList.get(l2)).getProvinceName());
                        l2++;
                    }
                    ArrayAdapter arrayadapter5 = new ArrayAdapter(GoodReceiver.this, 0x1090008, provinceNameList);
                    arrayadapter5.setDropDownViewResource(0x1090009);
                    provinceSpinner.setAdapter(arrayadapter5);
                    ReceiverAsyncTask receiverasynctask2 = new ReceiverAsyncTask(editGoodReceiverVO.getProvinceId(), myHandler, 0x7f090049);
                    if(receiverasynctask2.getStatus() == android.os.AsyncTask.Status.RUNNING)
                    {
                        receiverasynctask2.cancel(true);
                        receiverasynctask2.execute(null);
                    } else
                    {
                        receiverasynctask2.execute(null);
                    }
                }
                continue; /* Loop/switch isn't completed */
_L13:
                showToast("\u7701\u4EFD\u52A0\u8F7D\u5931\u8D25");
                continue; /* Loop/switch isn't completed */
_L11:
                util.showNetNull();
                if(true) goto _L16; else goto _L8
_L8:
                if(message.obj == null) goto _L18; else goto _L17
_L17:
                cityList = (List)message.obj;
                if(cityList.size() <= 0) goto _L20; else goto _L19
_L19:
                if(type != 0) goto _L22; else goto _L21
_L21:
                cityNameList = new ArrayList();
                for(int j2 = 0; j2 < cityList.size(); j2++)
                    cityNameList.add(((CityVO)cityList.get(j2)).getCityName());

                ArrayAdapter arrayadapter4 = new ArrayAdapter(GoodReceiver.this, 0x1090008, cityNameList);
                arrayadapter4.setDropDownViewResource(0x1090009);
                citySpinner.setAdapter(arrayadapter4);
                citySpinner.setClickable(true);
_L23:
                cancelProgress();
                continue; /* Loop/switch isn't completed */
_L22:
                if(editGoodReceiverVO != null && type == 1)
                    if(cityFirst)
                    {
                        for(int l1 = 0; l1 < cityList.size(); l1++)
                            if(!((CityVO)cityList.get(l1)).getCityName().equals(editGoodReceiverVO.getCityName()))
                            {
                                tmpCityVO = new CityVO();
                                tmpCityVO.setId(((CityVO)cityList.get(l1)).getId());
                                tmpCityVO.setCityName(((CityVO)cityList.get(l1)).getCityName());
                                tmpCityList.add(tmpCityVO);
                            }

                        int i2 = 0;
                        while(i2 < tmpCityList.size()) 
                        {
                            if(!((CityVO)tmpCityList.get(i2)).getCityName().equals(editGoodReceiverVO.getCityName()))
                                cityNameList.add(((CityVO)tmpCityList.get(i2)).getCityName());
                            i2++;
                        }
                        cityFirst = false;
                        ArrayAdapter arrayadapter3 = new ArrayAdapter(GoodReceiver.this, 0x1090008, cityNameList);
                        arrayadapter3.setDropDownViewResource(0x1090009);
                        citySpinner.setAdapter(arrayadapter3);
                        citySpinner.setClickable(true);
                        ReceiverAsyncTask receiverasynctask1 = new ReceiverAsyncTask(editGoodReceiverVO.getCityId(), myHandler, 0x7f09004a);
                        if(receiverasynctask1.getStatus() == android.os.AsyncTask.Status.RUNNING)
                        {
                            receiverasynctask1.cancel(true);
                            receiverasynctask1.execute(null);
                        } else
                        {
                            receiverasynctask1.execute(null);
                        }
                    } else
                    {
                        tmpCityList.clear();
                        cityNameList.clear();
                        for(int j1 = 0; j1 < cityList.size(); j1++)
                        {
                            tmpCityVO = new CityVO();
                            tmpCityVO.setId(((CityVO)cityList.get(j1)).getId());
                            tmpCityVO.setCityName(((CityVO)cityList.get(j1)).getCityName());
                            tmpCityList.add(tmpCityVO);
                        }

                        for(int k1 = 0; k1 < tmpCityList.size(); k1++)
                            cityNameList.add(((CityVO)tmpCityList.get(k1)).getCityName());

                        ArrayAdapter arrayadapter2 = new ArrayAdapter(GoodReceiver.this, 0x1090008, cityNameList);
                        arrayadapter2.setDropDownViewResource(0x1090009);
                        citySpinner.setAdapter(arrayadapter2);
                        citySpinner.setClickable(true);
                        ReceiverAsyncTask receiverasynctask = new ReceiverAsyncTask(((CityVO)tmpCityList.get(0)).getId(), myHandler, 0x7f09004a);
                        if(receiverasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                        {
                            receiverasynctask.cancel(true);
                            receiverasynctask.execute(null);
                        } else
                        {
                            receiverasynctask.execute(null);
                        }
                    }
                continue; /* Loop/switch isn't completed */
_L20:
                showToast("\u5E02\u533A\u52A0\u8F7D\u5931\u8D25");
                continue; /* Loop/switch isn't completed */
_L18:
                util.showNetNull();
                if(true) goto _L23; else goto _L9
_L9:
                if(message.obj == null) goto _L25; else goto _L24
_L24:
                countyList = (List)message.obj;
                if(countyList.size() <= 0) goto _L27; else goto _L26
_L26:
                if(type != 0) goto _L29; else goto _L28
_L28:
                countyNameList = new ArrayList();
                for(int i1 = 0; i1 < countyList.size(); i1++)
                    countyNameList.add(((CountyVO)countyList.get(i1)).getCountyName());

                ArrayAdapter arrayadapter1 = new ArrayAdapter(GoodReceiver.this, 0x1090008, countyNameList);
                arrayadapter1.setDropDownViewResource(0x1090009);
                countySpinner.setAdapter(arrayadapter1);
_L30:
                cancelProgress();
                continue; /* Loop/switch isn't completed */
_L29:
                if(editGoodReceiverVO != null && type == 1)
                {
                    ArrayAdapter arrayadapter;
                    if(countyFirst)
                    {
                        for(int k = 0; k < countyList.size(); k++)
                            if(!((CountyVO)countyList.get(k)).getCountyName().equals(editGoodReceiverVO.getCountyName()))
                            {
                                tmpCountyVO = new CountyVO();
                                tmpCountyVO.setId(((CountyVO)countyList.get(k)).getId());
                                tmpCountyVO.setCountyName(((CountyVO)countyList.get(k)).getCountyName());
                                tmpCountyList.add(tmpCountyVO);
                            }

                        int l = 0;
                        while(l < tmpCountyList.size()) 
                        {
                            if(!((CountyVO)tmpCountyList.get(l)).getCountyName().equals(editGoodReceiverVO.getCountyName()))
                                countyNameList.add(((CountyVO)tmpCountyList.get(l)).getCountyName());
                            l++;
                        }
                        countyFirst = false;
                    } else
                    {
                        tmpCountyList.clear();
                        countyNameList.clear();
                        for(int i = 0; i < countyList.size(); i++)
                        {
                            tmpCountyVO = new CountyVO();
                            tmpCountyVO.setId(((CountyVO)countyList.get(i)).getId());
                            tmpCountyVO.setCountyName(((CountyVO)countyList.get(i)).getCountyName());
                            tmpCountyList.add(tmpCountyVO);
                        }

                        int j = 0;
                        while(j < tmpCountyList.size()) 
                        {
                            countyNameList.add(((CountyVO)tmpCountyList.get(j)).getCountyName());
                            j++;
                        }
                    }
                    arrayadapter = new ArrayAdapter(GoodReceiver.this, 0x1090008, countyNameList);
                    arrayadapter.setDropDownViewResource(0x1090009);
                    countySpinner.setAdapter(arrayadapter);
                }
                continue; /* Loop/switch isn't completed */
_L27:
                showToast("\u533A\u53BF\u52A0\u8F7D\u5931\u8D25");
                continue; /* Loop/switch isn't completed */
_L25:
                util.showNetNull();
                if(true) goto _L30; else goto _L2
_L2:
                cancelProgress();
                if(message.obj != null)
                {
                    result = ((Integer)message.obj).intValue();
                    if(result == 1)
                    {
                        showToast("\u66F4\u65B0\u6210\u529F\uFF01");
                        finish();
                    } else
                    if(result == -1)
                        showToast("\u5730\u5740\u4E0D\u5C5E\u4E8E\u672C\u4EBA");
                    else
                    if(result == -2)
                        showToast("\u5730\u5740\u548C\u767B\u5F55\u7684\u5730\u533A\u4E0D\u540C");
                    else
                    if(result == -4)
                        showToast("\u4E0D\u652F\u6301\u8D27\u5230\u4ED8\u6B3E");
                    else
                    if(result == -19)
                        showToast("\u8BA2\u5355\u4E0D\u5B58\u5728\uFF01");
                    else
                        showToast("\u66F4\u65B0\u5931\u8D25\uFF01");
                } else
                {
                    util.showNetNull();
                }
                if(true) goto _L1; else goto _L31
_L31:
            }

            final GoodReceiver this$0;

            
            {
                this$0 = GoodReceiver.this;
                super();
            }
        }
;
    }

    private String checkAddReceiver()
    {
        String s = receviceNameEditText.getText().toString().trim();
        String s1 = provinceSpinner.getSelectedItem().toString();
        String s2 = citySpinner.getSelectedItem().toString();
        String s3 = countySpinner.getSelectedItem().toString();
        String s4 = recordNameEditText.getText().toString().trim();
        String s5 = mobileEditText.getText().toString().trim();
        String s6 = phoneEditText.getText().toString().trim();
        String s7;
        if(receviceNameEditText.getText().toString().trim().length() < 1)
            s7 = "\u6536\u8D27\u4EBA\u540D\u79F0\u4E0D\u80FD\u4E3A\u7A7A\uFF01";
        else
        if(s1.equals("\u7701\u4EFD"))
            s7 = "\u8BF7\u9009\u62E9\u9001\u8D27\u5730\u5740\u7701\u4EFD\uFF01";
        else
        if(recordNameEditText.getText().toString().trim().length() < 1)
            s7 = "\u8BE6\u7EC6\u5730\u5740\u4E0D\u80FD\u4E3A\u7A7A\uFF01";
        else
        if(mobileEditText.getText().toString().trim().length() < 1 && phoneEditText.getText().toString().trim().length() < 1)
        {
            s7 = "\u624B\u673A\u548C\u7535\u8BDD\u81F3\u5C111\u4E2A\u4E0D\u80FD\u4E3A\u7A7A\uFF01";
        } else
        {
            goodReceiverVO = new GoodReceiverVO();
            goodReceiverVO.setAddress1((new StringBuilder()).append(s1).append(s2).append(s3).append(s4).toString());
            goodReceiverVO.setReceiveName(s);
            goodReceiverVO.setProvinceName(s1);
            goodReceiverVO.setCityName(s2);
            goodReceiverVO.setCountyName(s3);
            goodReceiverVO.setRecordName(s4);
            goodReceiverVO.setReceiverMobile(s5);
            goodReceiverVO.setReceiverPhone(s6);
            goodReceiverVO.setProvinceId(Long.valueOf(provinceId));
            goodReceiverVO.setCityId(Long.valueOf(cityId));
            goodReceiverVO.setCountyId(Long.valueOf(countyId));
            if(type == 1)
                goodReceiverVO.setId(editGoodReceiverVO.getId());
            s7 = null;
        }
        return s7;
    }

    private void initAddressView()
    {
        if(first_01)
        {
            showAddrLayout = (LinearLayout)findViewById(0x7f0900a2);
            addressListView = (ListView)findViewById(0x7f0900a5);
            addTextView = (TextView)findViewById(0x7f0900a3);
            msgTextView = (TextView)findViewById(0x7f0900a4);
            first_01 = false;
        }
        util.setDefaultTitle(0x7f0a0077);
        showAddrLayout.setVisibility(0);
        addTextView.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                editAddrLayout = (LinearLayout)findViewById(0x7f0900a6);
                showAddrLayout.setVisibility(8);
                editAddrLayout.setVisibility(0);
                initEditAddressView();
                if(User.token != null && DBHelper.testNet())
                {
                    showProgress();
                    ReceiverAsyncTask receiverasynctask1 = new ReceiverAsyncTask(myHandler, 0x7f090048);
                    if(receiverasynctask1.getStatus() == android.os.AsyncTask.Status.RUNNING)
                    {
                        receiverasynctask1.cancel(true);
                        receiverasynctask1.execute(null);
                    } else
                    {
                        receiverasynctask1.execute(null);
                    }
                } else
                {
                    util.changeMain("com.thestore.main.userland");
                }
            }

            final GoodReceiver this$0;

            
            {
                this$0 = GoodReceiver.this;
                super();
            }
        }
);
        if(User.token != null && DBHelper.testNet())
        {
            showProgress();
            ReceiverAsyncTask receiverasynctask = new ReceiverAsyncTask(myHandler, 0x7f090044);
            if(receiverasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
            {
                receiverasynctask.cancel(true);
                receiverasynctask.execute(null);
            } else
            {
                receiverasynctask.execute(null);
            }
        } else
        {
            util.changeMain("com.thestore.main.userland");
        }
        addressListView.setOnCreateContextMenuListener(new android.view.View.OnCreateContextMenuListener() {

            public void onCreateContextMenu(ContextMenu contextmenu, View view, android.view.ContextMenu.ContextMenuInfo contextmenuinfo)
            {
                if(setReveiverType != 0x7f090043)
                    contextmenu.add(0, 0x7f09004b, 0, "\u8BBE\u4E3A\u9001\u8D27\u5730\u5740");
                contextmenu.add(0, 0x7f09004c, 0, "\u4FEE\u6539\u5730\u5740");
                contextmenu.add(0, 0x7f09004d, 0, "\u5220\u9664\u5730\u5740");
            }

            final GoodReceiver this$0;

            
            {
                this$0 = GoodReceiver.this;
                super();
            }
        }
);
        addressListView.setOnItemLongClickListener(new android.widget.AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView adapterview, View view, int i, long l)
            {
                index = i;
                isShowList.set(i, Boolean.valueOf(true));
                return false;
            }

            final GoodReceiver this$0;

            
            {
                this$0 = GoodReceiver.this;
                super();
            }
        }
);
    }

    private void initEditAddressView()
    {
        util.setDefaultTitle(0x7f0a0078);
        editAddrLayout = (LinearLayout)findViewById(0x7f0900a6);
        receviceNameEditText = (EditText)findViewById(0x7f0900a7);
        provinceSpinner = (Spinner)findViewById(0x7f0900a8);
        citySpinner = (Spinner)findViewById(0x7f0900a9);
        countySpinner = (Spinner)findViewById(0x7f0900aa);
        recordNameEditText = (EditText)findViewById(0x7f0900ab);
        postCodeTextView = (TextView)findViewById(0x7f0900ac);
        mobileEditText = (EditText)findViewById(0x7f0900ad);
        phoneEditText = (EditText)findViewById(0x7f0900ae);
        editButton = (Button)findViewById(0x7f0900af);
        editAddrLayout.setVisibility(0);
        editButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                String s = checkAddReceiver();
                if(s != null)
                    showToast(s);
                else
                if(User.token != null && DBHelper.testNet())
                {
                    showProgress();
                    if(type == 0)
                    {
                        ReceiverAsyncTask receiverasynctask = new ReceiverAsyncTask(goodReceiverVO, myHandler, 0x7f090045);
                        if(receiverasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                        {
                            receiverasynctask.cancel(true);
                            receiverasynctask.execute(null);
                        } else
                        {
                            receiverasynctask.execute(null);
                        }
                    } else
                    {
                        ReceiverAsyncTask receiverasynctask1 = new ReceiverAsyncTask(goodReceiverVO, myHandler, 0x7f090046);
                        if(receiverasynctask1.getStatus() == android.os.AsyncTask.Status.RUNNING)
                        {
                            receiverasynctask1.cancel(true);
                            receiverasynctask1.execute(null);
                        } else
                        {
                            receiverasynctask1.execute(null);
                        }
                    }
                } else
                {
                    util.changeMain("com.thestore.main.userland");
                }
            }

            final GoodReceiver this$0;

            
            {
                this$0 = GoodReceiver.this;
                super();
            }
        }
);
        if(type != 0) goto _L2; else goto _L1
_L1:
        initSpinner();
        provinceSpinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView adapterview, View view, int i, long l)
            {
                if(!provinceSpinner.getSelectedItem().toString().equals("\u7701\u4EFD"))
                {
                    if(User.token != null && DBHelper.testNet())
                    {
                        showProgress();
                        provinceId = ((ProvinceVO)provinceList.get(i - 1)).getId().longValue();
                        ReceiverAsyncTask receiverasynctask = new ReceiverAsyncTask(((ProvinceVO)provinceList.get(i - 1)).getId(), myHandler, 0x7f090049);
                        if(receiverasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                        {
                            receiverasynctask.cancel(true);
                            receiverasynctask.execute(null);
                        } else
                        {
                            receiverasynctask.execute(null);
                        }
                    } else
                    {
                        util.changeMain("com.thestore.main.userland");
                    }
                } else
                {
                    citySpinner.setAdapter(cityAdapter);
                    countySpinner.setAdapter(countyAdapter);
                }
            }

            public void onNothingSelected(AdapterView adapterview)
            {
            }

            final GoodReceiver this$0;

            
            {
                this$0 = GoodReceiver.this;
                super();
            }
        }
);
        citySpinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView adapterview, View view, int i, long l)
            {
                if(!citySpinner.getSelectedItem().toString().equals("\u5E02/\u533A"))
                {
                    if(User.token != null && DBHelper.testNet())
                    {
                        showProgress();
                        cityId = ((CityVO)cityList.get(i)).getId().longValue();
                        ReceiverAsyncTask receiverasynctask = new ReceiverAsyncTask(((CityVO)cityList.get(i)).getId(), myHandler, 0x7f09004a);
                        if(receiverasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                        {
                            receiverasynctask.cancel(true);
                            receiverasynctask.execute(null);
                        } else
                        {
                            receiverasynctask.execute(null);
                        }
                    } else
                    {
                        util.changeMain("com.thestore.main.userland");
                    }
                } else
                {
                    countySpinner.setAdapter(countyAdapter);
                }
            }

            public void onNothingSelected(AdapterView adapterview)
            {
            }

            final GoodReceiver this$0;

            
            {
                this$0 = GoodReceiver.this;
                super();
            }
        }
);
        countySpinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView adapterview, View view, int i, long l)
            {
                if(!countySpinner.getSelectedItem().toString().equals("\u533A/\u53BF"))
                {
                    if(((CountyVO)countyList.get(i)).getPostcode() != null)
                        postCodeTextView.setText(((CountyVO)countyList.get(i)).getPostcode());
                    else
                        postCodeTextView.setText("200333");
                    countyId = ((CountyVO)countyList.get(i)).getId().longValue();
                }
            }

            public void onNothingSelected(AdapterView adapterview)
            {
            }

            final GoodReceiver this$0;

            
            {
                this$0 = GoodReceiver.this;
                super();
            }
        }
);
        receviceNameEditText.setText("");
        recordNameEditText.setText("");
        postCodeTextView.setText("");
        mobileEditText.setText("");
        phoneEditText.setText("");
        editButton.setText(0x7f0a0080);
_L4:
        return;
_L2:
        if(editGoodReceiverVO != null && type == 1)
        {
            initUpdateSpinner();
            provinceSpinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {

                public void onItemSelected(AdapterView adapterview, View view, int i, long l)
                {
                    if(provinceSpinner.getSelectedItem().toString().equals(editGoodReceiverVO.getProvinceName()) && provinceFirst)
                    {
                        citySpinner.setAdapter(cityAdapter);
                        countySpinner.setAdapter(countyAdapter);
                    } else
                    {
                        provinceFirst = false;
                        if(User.token != null && DBHelper.testNet())
                        {
                            showProgress();
                            provinceId = ((ProvinceVO)tmpProvinceList.get(i)).getId().longValue();
                            ReceiverAsyncTask receiverasynctask = new ReceiverAsyncTask(((ProvinceVO)tmpProvinceList.get(i)).getId(), myHandler, 0x7f090049);
                            if(receiverasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                            {
                                receiverasynctask.cancel(true);
                                receiverasynctask.execute(null);
                            } else
                            {
                                receiverasynctask.execute(null);
                            }
                        } else
                        {
                            util.changeMain("com.thestore.main.userland");
                        }
                    }
                }

                public void onNothingSelected(AdapterView adapterview)
                {
                }

                final GoodReceiver this$0;

            
            {
                this$0 = GoodReceiver.this;
                super();
            }
            }
);
            citySpinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {

                public void onItemSelected(AdapterView adapterview, View view, int i, long l)
                {
                    if(!citySpinner.getSelectedItem().toString().equals(editGoodReceiverVO.getCityName()))
                    {
                        if(User.token != null && DBHelper.testNet())
                        {
                            showProgress();
                            cityId = ((CityVO)cityList.get(i)).getId().longValue();
                            ReceiverAsyncTask receiverasynctask = new ReceiverAsyncTask(((CityVO)cityList.get(i)).getId(), myHandler, 0x7f09004a);
                            if(receiverasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                            {
                                receiverasynctask.cancel(true);
                                receiverasynctask.execute(null);
                            } else
                            {
                                receiverasynctask.execute(null);
                            }
                        } else
                        {
                            util.changeMain("com.thestore.main.userland");
                        }
                    } else
                    {
                        countySpinner.setAdapter(countyAdapter);
                    }
                }

                public void onNothingSelected(AdapterView adapterview)
                {
                }

                final GoodReceiver this$0;

            
            {
                this$0 = GoodReceiver.this;
                super();
            }
            }
);
            countySpinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {

                public void onItemSelected(AdapterView adapterview, View view, int i, long l)
                {
                    if(!countySpinner.getSelectedItem().toString().equals(editGoodReceiverVO.getCountyName()))
                    {
                        if(((CountyVO)countyList.get(i)).getPostcode() != null)
                            postCodeTextView.setText(((CountyVO)countyList.get(i)).getPostcode());
                        else
                            postCodeTextView.setText("200333");
                        countyId = ((CountyVO)countyList.get(i)).getId().longValue();
                    }
                }

                public void onNothingSelected(AdapterView adapterview)
                {
                }

                final GoodReceiver this$0;

            
            {
                this$0 = GoodReceiver.this;
                super();
            }
            }
);
            receviceNameEditText.setText(editGoodReceiverVO.getReceiveName());
            recordNameEditText.setText(editGoodReceiverVO.getRecordName());
            postCodeTextView.setText(editGoodReceiverVO.getPostCode());
            mobileEditText.setText(editGoodReceiverVO.getReceiverMobile());
            phoneEditText.setText(editGoodReceiverVO.getReceiverPhone());
            editButton.setText(0x7f0a0081);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void initSpinner()
    {
        provinceNameList = new ArrayList();
        ArrayList arraylist = new ArrayList();
        ArrayList arraylist1 = new ArrayList();
        provinceNameList.add("\u7701\u4EFD");
        arraylist.add("\u5E02/\u533A");
        arraylist1.add("\u533A/\u53BF");
        provinceAdapter = new ArrayAdapter(this, 0x1090008, provinceNameList);
        provinceAdapter.setDropDownViewResource(0x1090009);
        cityAdapter = new ArrayAdapter(this, 0x1090008, arraylist);
        cityAdapter.setDropDownViewResource(0x1090009);
        countyAdapter = new ArrayAdapter(this, 0x1090008, arraylist1);
        countyAdapter.setDropDownViewResource(0x1090009);
        provinceSpinner.setAdapter(provinceAdapter);
        citySpinner.setAdapter(cityAdapter);
        countySpinner.setAdapter(countyAdapter);
    }

    private void initUpdateSpinner()
    {
        tmpProvinceVO = new ProvinceVO();
        tmpProvinceList = new ArrayList();
        tmpProvinceVO.setId(editGoodReceiverVO.getProvinceId());
        provinceId = editGoodReceiverVO.getProvinceId().longValue();
        tmpProvinceVO.setProvinceName(editGoodReceiverVO.getProvinceName());
        tmpProvinceList.add(tmpProvinceVO);
        provinceNameList = new ArrayList();
        provinceNameList.add(editGoodReceiverVO.getProvinceName());
        provinceAdapter = new ArrayAdapter(this, 0x1090008, provinceNameList);
        provinceAdapter.setDropDownViewResource(0x1090009);
        provinceSpinner.setAdapter(provinceAdapter);
        tmpCityVO = new CityVO();
        tmpCityList = new ArrayList();
        tmpCityVO.setId(editGoodReceiverVO.getCityId());
        cityId = editGoodReceiverVO.getCityId().longValue();
        tmpCityVO.setCityName(editGoodReceiverVO.getCityName());
        tmpCityList.add(tmpCityVO);
        cityNameList = new ArrayList();
        cityNameList.add(editGoodReceiverVO.getCityName());
        cityAdapter = new ArrayAdapter(this, 0x1090008, cityNameList);
        cityAdapter.setDropDownViewResource(0x1090009);
        citySpinner.setAdapter(cityAdapter);
        tmpCountyVO = new CountyVO();
        tmpCountyList = new ArrayList();
        tmpCountyVO.setId(editGoodReceiverVO.getCountyId());
        countyId = editGoodReceiverVO.getCountyId().longValue();
        tmpCountyVO.setCountyName(editGoodReceiverVO.getCountyName());
        tmpCountyList.add(tmpCountyVO);
        countyNameList = new ArrayList();
        countyNameList.add(editGoodReceiverVO.getCountyName());
        countyAdapter = new ArrayAdapter(this, 0x1090008, countyNameList);
        countyAdapter.setDropDownViewResource(0x1090009);
        countySpinner.setAdapter(countyAdapter);
    }

    private void setMenuIsShown(boolean flag)
    {
        menu.findItem(0x7f090218).setVisible(flag);
        menu.findItem(0x7f090219).setVisible(flag);
        menu.findItem(0x7f090202).setVisible(true);
    }

    private void showReceiver()
    {
        editAddrLayout.setVisibility(8);
        type = 0;
        initAddressView();
    }

    public boolean onContextItemSelected(MenuItem menuitem)
    {
        menuitem.getItemId();
        JVM INSTR tableswitch 2131296331 2131296333: default 32
    //                   2131296331 38
    //                   2131296332 188
    //                   2131296333 326;
           goto _L1 _L2 _L3 _L4
_L1:
        return super.onContextItemSelected(menuitem);
_L2:
        if(goodReceiverList.size() < 1)
        {
            showToast("\u65E0\u53EF\u9009\u9879\u7684\u9001\u8D27\u5730\u5740\uFF01");
        } else
        {
            updateGoodReceiverVO = (GoodReceiverVO)goodReceiverList.get(index);
            if(updateGoodReceiverVO == null)
                showToast("\u9001\u8D27\u5730\u5740\u4E0D\u6B63\u786E\uFF0C\u8BF7\u91CD\u65B0\u9009\u62E9\uFF01");
            else
            if(User.token != null && DBHelper.testNet())
            {
                showProgress();
                OrderAsyncTask orderasynctask = new OrderAsyncTask(updateGoodReceiverVO.getId(), myHandler, 0x7f09003c);
                if(orderasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                {
                    orderasynctask.cancel(true);
                    orderasynctask.execute(null);
                } else
                {
                    orderasynctask.execute(null);
                }
            } else
            {
                util.changeMain("com.thestore.main.userland");
            }
        }
        continue; /* Loop/switch isn't completed */
_L3:
        type = 1;
        editGoodReceiverVO = (GoodReceiverVO)goodReceiverList.get(index);
        editAddrLayout = (LinearLayout)findViewById(0x7f0900a6);
        showAddrLayout.setVisibility(8);
        editAddrLayout.setVisibility(0);
        initEditAddressView();
        if(User.token != null && DBHelper.testNet())
        {
            showProgress();
            ReceiverAsyncTask receiverasynctask = new ReceiverAsyncTask(myHandler, 0x7f090048);
            if(receiverasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
            {
                receiverasynctask.cancel(true);
                receiverasynctask.execute(null);
            } else
            {
                receiverasynctask.execute(null);
            }
        } else
        {
            util.changeMain("com.thestore.main.userland");
        }
        continue; /* Loop/switch isn't completed */
_L4:
        (new android.app.AlertDialog.Builder(this)).setTitle(0x7f0a00c0).setMessage(0x7f0a0082).setIcon(0x7f020068).setPositiveButton("\u786E\u5B9A", new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                delGoodReceiverVO = (GoodReceiverVO)goodReceiverList.get(index);
                if(User.token != null && DBHelper.testNet())
                {
                    showProgress();
                    ReceiverAsyncTask receiverasynctask1 = new ReceiverAsyncTask(delGoodReceiverVO, myHandler, 0x7f090047);
                    if(receiverasynctask1.getStatus() == android.os.AsyncTask.Status.RUNNING)
                    {
                        receiverasynctask1.cancel(true);
                        receiverasynctask1.execute(null);
                    } else
                    {
                        receiverasynctask1.execute(null);
                    }
                } else
                {
                    util.changeMain("com.thestore.main.userland");
                }
            }

            final GoodReceiver this$0;

            
            {
                this$0 = GoodReceiver.this;
                super();
            }
        }
).setNegativeButton("\u53D6\u6D88", new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
            }

            final GoodReceiver this$0;

            
            {
                this$0 = GoodReceiver.this;
                super();
            }
        }
).show();
        if(true) goto _L1; else goto _L5
_L5:
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        util = new Util(this);
        setContentView(0x7f03000e);
        bottomOnClick(this);
        Intent intent = getIntent();
        goodReceiverId = intent.getLongExtra("GOODRECEIVER_ID", 0L);
        setReveiverType = intent.getIntExtra("SET_GOODRECEIVER", 0);
        initAddressView();
    }

    public boolean onCreateOptionsMenu(Menu menu1)
    {
        menu = menu1;
        (new MenuInflater(this)).inflate(0x7f0c000b, menu1);
        return true;
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        boolean flag;
        if(i == 4 && !showAddrLayout.isShown())
        {
            showReceiver();
            flag = false;
        } else
        {
            flag = super.onKeyDown(i, keyevent);
        }
        return flag;
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        menuitem.getItemId();
        JVM INSTR lookupswitch 3: default 40
    //                   2131296770: 42
    //                   2131296792: 49
    //                   2131296793: 167;
           goto _L1 _L2 _L3 _L4
_L1:
        return true;
_L2:
        finish();
        continue; /* Loop/switch isn't completed */
_L3:
        editAddrLayout = (LinearLayout)findViewById(0x7f0900a6);
        showAddrLayout.setVisibility(8);
        editAddrLayout.setVisibility(0);
        initEditAddressView();
        if(User.token != null && DBHelper.testNet())
        {
            showProgress();
            ReceiverAsyncTask receiverasynctask1 = new ReceiverAsyncTask(myHandler, 0x7f090048);
            if(receiverasynctask1.getStatus() == android.os.AsyncTask.Status.RUNNING)
            {
                receiverasynctask1.cancel(true);
                receiverasynctask1.execute(null);
            } else
            {
                receiverasynctask1.execute(null);
            }
        } else
        {
            util.changeMain("com.thestore.main.userland");
        }
        continue; /* Loop/switch isn't completed */
_L4:
        provinceFirst = true;
        cityFirst = true;
        countyFirst = true;
        initEditAddressView();
        if(User.token != null && DBHelper.testNet())
        {
            showProgress();
            ReceiverAsyncTask receiverasynctask = new ReceiverAsyncTask(myHandler, 0x7f090048);
            if(receiverasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
            {
                receiverasynctask.cancel(true);
                receiverasynctask.execute(null);
            } else
            {
                receiverasynctask.execute(null);
            }
        } else
        {
            util.changeMain("com.thestore.main.userland");
        }
        if(true) goto _L1; else goto _L5
_L5:
    }

    public boolean onPrepareOptionsMenu(Menu menu1)
    {
        setMenuIsShown(false);
        if(!showAddrLayout.isShown()) goto _L2; else goto _L1
_L1:
        menu1.findItem(0x7f090218).setVisible(true);
_L4:
        return super.onPrepareOptionsMenu(menu1);
_L2:
        if(editAddrLayout.isShown())
            menu1.findItem(0x7f090219).setVisible(true);
        if(true) goto _L4; else goto _L3
_L3:
    }

    protected void onRestart()
    {
        super.onRestart();
        bottomOnClick(this);
    }

    private ReceiverAdapter adapter;
    private TextView addTextView;
    private ListView addressListView;
    private ArrayAdapter cityAdapter;
    private boolean cityFirst;
    private long cityId;
    private List cityList;
    private List cityNameList;
    private Spinner citySpinner;
    private ArrayAdapter countyAdapter;
    private boolean countyFirst;
    private long countyId;
    private List countyList;
    private List countyNameList;
    private Spinner countySpinner;
    private GoodReceiverVO delGoodReceiverVO;
    private LinearLayout editAddrLayout;
    private Button editButton;
    private GoodReceiverVO editGoodReceiverVO;
    private boolean first_01;
    private long goodReceiverId;
    private List goodReceiverList;
    private GoodReceiverVO goodReceiverVO;
    private int index;
    private List isShowList;
    private Menu menu;
    private EditText mobileEditText;
    private TextView msgTextView;
    private Handler myHandler;
    private EditText phoneEditText;
    private TextView postCodeTextView;
    private ArrayAdapter provinceAdapter;
    private boolean provinceFirst;
    private long provinceId;
    private List provinceList;
    private List provinceNameList;
    private Spinner provinceSpinner;
    private EditText receviceNameEditText;
    private EditText recordNameEditText;
    private int result;
    private int setReveiverType;
    private LinearLayout showAddrLayout;
    private List tmpCityList;
    private CityVO tmpCityVO;
    private List tmpCountyList;
    private CountyVO tmpCountyVO;
    private List tmpProvinceList;
    private ProvinceVO tmpProvinceVO;
    private int type;
    private GoodReceiverVO updateGoodReceiverVO;
    private Util util;



/*
    static LinearLayout access$002(GoodReceiver goodreceiver, LinearLayout linearlayout)
    {
        goodreceiver.editAddrLayout = linearlayout;
        return linearlayout;
    }

*/





/*
    static int access$1102(GoodReceiver goodreceiver, int i)
    {
        goodreceiver.type = i;
        return i;
    }

*/




/*
    static long access$1402(GoodReceiver goodreceiver, long l)
    {
        goodreceiver.provinceId = l;
        return l;
    }

*/



/*
    static List access$1502(GoodReceiver goodreceiver, List list)
    {
        goodreceiver.provinceList = list;
        return list;
    }

*/







/*
    static long access$2002(GoodReceiver goodreceiver, long l)
    {
        goodreceiver.cityId = l;
        return l;
    }

*/



/*
    static List access$2102(GoodReceiver goodreceiver, List list)
    {
        goodreceiver.cityList = list;
        return list;
    }

*/



/*
    static List access$2202(GoodReceiver goodreceiver, List list)
    {
        goodreceiver.countyList = list;
        return list;
    }

*/



/*
    static long access$2402(GoodReceiver goodreceiver, long l)
    {
        goodreceiver.countyId = l;
        return l;
    }

*/



/*
    static GoodReceiverVO access$2502(GoodReceiver goodreceiver, GoodReceiverVO goodreceivervo)
    {
        goodreceiver.editGoodReceiverVO = goodreceivervo;
        return goodreceivervo;
    }

*/



/*
    static boolean access$2602(GoodReceiver goodreceiver, boolean flag)
    {
        goodreceiver.provinceFirst = flag;
        return flag;
    }

*/







/*
    static ReceiverAdapter access$3002(GoodReceiver goodreceiver, ReceiverAdapter receiveradapter)
    {
        goodreceiver.adapter = receiveradapter;
        return receiveradapter;
    }

*/



/*
    static int access$3102(GoodReceiver goodreceiver, int i)
    {
        goodreceiver.result = i;
        return i;
    }

*/






/*
    static ProvinceVO access$3502(GoodReceiver goodreceiver, ProvinceVO provincevo)
    {
        goodreceiver.tmpProvinceVO = provincevo;
        return provincevo;
    }

*/



/*
    static List access$3602(GoodReceiver goodreceiver, List list)
    {
        goodreceiver.cityNameList = list;
        return list;
    }

*/



/*
    static boolean access$3702(GoodReceiver goodreceiver, boolean flag)
    {
        goodreceiver.cityFirst = flag;
        return flag;
    }

*/



/*
    static CityVO access$3802(GoodReceiver goodreceiver, CityVO cityvo)
    {
        goodreceiver.tmpCityVO = cityvo;
        return cityvo;
    }

*/





/*
    static List access$4002(GoodReceiver goodreceiver, List list)
    {
        goodreceiver.countyNameList = list;
        return list;
    }

*/



/*
    static boolean access$4102(GoodReceiver goodreceiver, boolean flag)
    {
        goodreceiver.countyFirst = flag;
        return flag;
    }

*/



/*
    static CountyVO access$4202(GoodReceiver goodreceiver, CountyVO countyvo)
    {
        goodreceiver.tmpCountyVO = countyvo;
        return countyvo;
    }

*/







/*
    static int access$602(GoodReceiver goodreceiver, int i)
    {
        goodreceiver.index = i;
        return i;
    }

*/




/*
    static GoodReceiverVO access$802(GoodReceiver goodreceiver, GoodReceiverVO goodreceivervo)
    {
        goodreceiver.delGoodReceiverVO = goodreceivervo;
        return goodreceivervo;
    }

*/



/*
    static List access$902(GoodReceiver goodreceiver, List list)
    {
        goodreceiver.goodReceiverList = list;
        return list;
    }

*/
}
