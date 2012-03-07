// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.net;

import android.os.*;
import com.yihaodian.mobile.vo.product.ProductVO;
import java.util.List;

// Referenced classes of package com.thestore.net:
//            Product

public class ProductAsyncTask extends AsyncTask
{

    public ProductAsyncTask(Long long1, Handler handler1, int i)
    {
        product = null;
        productVO = null;
        productId = long1;
        handler = handler1;
        type = i;
    }

    public ProductAsyncTask(String s, Handler handler1, int i)
    {
        product = null;
        productVO = null;
        barcode = s;
        type = i;
        handler = handler1;
    }

    public ProductAsyncTask(List list1, Handler handler1, int i)
    {
        product = null;
        productVO = null;
        handler = handler1;
        type = i;
        list = list1;
    }

    @Override
	protected transient Object doInBackground(Object aobj[])
    {
        type;
        JVM INSTR lookupswitch 4: default 48
    //                   2131296292: 74
    //                   2131296293: 108
    //                   2131296294: 142
    //                   2131296339: 176;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        Object obj;
        product = new Product(barcode);
        obj = product.getBarcode();
        break; /* Loop/switch isn't completed */
_L2:
        product = new Product(productId);
        productVO = product.getProductDetail();
        obj = productVO;
        break; /* Loop/switch isn't completed */
_L3:
        product = new Product(productId);
        productVO = product.getProductDetailDescription();
        obj = productVO;
        break; /* Loop/switch isn't completed */
_L4:
        product = new Product(productId);
        productVO = product.getProductDetailComment();
        obj = productVO;
        break; /* Loop/switch isn't completed */
_L5:
        List list1;
        product = new Product(list);
        list1 = product.getNote();
        obj = list1;
        break; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        exception.printStackTrace();
        obj = null;
        return obj;
    }

    @Override
	protected void onPostExecute(Object obj)
    {
        Message message;
        message = handler.obtainMessage();
        message.what = type;
        type;
        JVM INSTR tableswitch 2131296292 2131296294: default 48
    //                   2131296292 63
    //                   2131296293 74
    //                   2131296294 85;
           goto _L1 _L2 _L3 _L4
_L1:
        message.obj = obj;
_L6:
        handler.sendMessage(message);
        return;
_L2:
        message.obj = productVO;
        continue; /* Loop/switch isn't completed */
_L3:
        message.obj = productVO;
        continue; /* Loop/switch isn't completed */
_L4:
        message.obj = productVO;
        if(true) goto _L6; else goto _L5
_L5:
    }

    private String barcode;
    private Handler handler;
    private List list;
    private Product product;
    private Long productId;
    private ProductVO productVO;
    private int type;
}
