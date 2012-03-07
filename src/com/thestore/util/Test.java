// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.util;

import com.yihaodian.mobile.vo.core.Page;
import com.yihaodian.mobile.vo.product.CategoryVO;
import java.util.ArrayList;

public class Test
{

    public Test()
    {
    }

    public static Page getCategoryVOPages()
    {
        Page page = new Page();
        ArrayList arraylist = new ArrayList();
        CategoryVO categoryvo = new CategoryVO();
        categoryvo.setId(Long.valueOf(361L));
        categoryvo.setCategoryName("\u529E\u516C\u7528\u54C1");
        arraylist.add(categoryvo);
        CategoryVO categoryvo1 = new CategoryVO();
        categoryvo1.setId(Long.valueOf(5009L));
        categoryvo1.setCategoryName("\u7F8E\u5BB9\u62A4\u7406");
        arraylist.add(categoryvo1);
        CategoryVO categoryvo2 = new CategoryVO();
        categoryvo2.setId(Long.valueOf(5117L));
        categoryvo2.setCategoryName("\u6BCD\u5A74");
        arraylist.add(categoryvo2);
        CategoryVO categoryvo3 = new CategoryVO();
        categoryvo3.setId(Long.valueOf(5134L));
        categoryvo3.setCategoryName("\u53A8\u536B\u6E05\u6D01");
        arraylist.add(categoryvo3);
        CategoryVO categoryvo4 = new CategoryVO();
        categoryvo4.setId(Long.valueOf(5135L));
        categoryvo4.setCategoryName("\u98DF\u54C1\u996E\u6599");
        arraylist.add(categoryvo4);
        CategoryVO categoryvo5 = new CategoryVO();
        categoryvo5.setId(Long.valueOf(8704L));
        categoryvo5.setCategoryName("\u8425\u517B\u4FDD\u5065");
        arraylist.add(categoryvo5);
        CategoryVO categoryvo6 = new CategoryVO();
        categoryvo6.setId(Long.valueOf(0xe8020L));
        categoryvo6.setCategoryName("\u5BB6\u5C45");
        arraylist.add(categoryvo6);
        CategoryVO categoryvo7 = new CategoryVO();
        categoryvo7.setId(Long.valueOf(0xe8120L));
        categoryvo7.setCategoryName("\u7535\u5668");
        arraylist.add(categoryvo7);
        CategoryVO categoryvo8 = new CategoryVO();
        categoryvo8.setId(Long.valueOf(17722L));
        categoryvo8.setCategoryName("\u73A9\u5177");
        arraylist.add(categoryvo8);
        CategoryVO categoryvo9 = new CategoryVO();
        categoryvo9.setId(Long.valueOf(0xe85adL));
        categoryvo9.setCategoryName("\u98DF\u54C1\u996E\u65991");
        arraylist.add(categoryvo9);
        page.setObjList(arraylist);
        page.setCurrentPage(Integer.valueOf(1));
        page.setPageSize(Integer.valueOf(50));
        page.setTotalSize(Integer.valueOf(arraylist.size()));
        return page;
    }

    public static String getProductVOPages()
    {
        return "<com.yihaodian.mobile.vo.core.Page><currentPage>1</currentPage><pageSize>10</pageSize><totalSize>62900</totalSize><objList><com.yihaodian.mobile.vo.product.ProductVO><productId>23319</productId><merchantIds><long>1</long></merchantIds><merchantId>1</merchantId><cnName>kolomolo\u7F51\u773Cpolo\u886B\u77ED\u8896</cnName><midleDefaultProductUrl>http://d3.yihaodian.com/t1/2009/05/21/62060_200x200.jpg</midleDefaultProductUrl><miniDefaultProductUrl>http://d3.yihaodian.com/t1/2009/05/21/62060_80x80.jpg</miniDefaultProductUrl><maketPrice>108.0</maketPrice><price>22.0</price><canBuy>true</canBuy><description></description><advertisement></advertisement></com.yihaodian.mobile.vo.product.ProductVO><com.yihaodian.mobile.vo.product.ProductVO><productId>23320</productId><merchantIds><long>1</long></merchantIds><merchantId>1</merchantId><cnName>kolomolo\u7F51\u773Cpolo\u886B\u957F\u8896</cnName><midleDefaultProductUrl>http://d2.yihaodian.com/t1/2009/05/21/62167_200x200.jpg</midleDefaultProductUrl><miniDefaultProductUrl>http://d2.yihaodian.com/t1/2009/05/21/62167_80x80.jpg</miniDefaultProductUrl><maketPrice>108.0</maketPrice><price>22.0</price><canBuy>true</canBuy><description></description><advertisement></advertisement></com.yihaodian.mobile.vo.product.ProductVO><com.yihaodian.mobile.vo.product.ProductVO><productId>23317</productId><merchantIds><long>1</long></merchantIds><merchantId>1</merchantId><cnName>kolomolo\u77ED\u8896\u9488\u7EC7\u886B</cnName><midleDefaultProductUrl>http://d4.yihaodian.com/t1/2009/05/21/61993_200x200.jpg</midleDefaultProductUrl><miniDefaultProductUrl>http://d4.yihaodian.com/t1/2009/05/21/61993_80x80.jpg</miniDefaultProductUrl><maketPrice>98.0</maketPrice><price>20.0</price><canBuy>true</canBuy><description></description><advertisement></advertisement></com.yihaodian.mobile.vo.product.ProductVO><com.yihaodian.mobile.vo.product.ProductVO><productId>23321</productId><merchantIds><long>1</long></merchantIds><merchantId>1</merchantId><cnName>kolomolo\u77ED\u8896\u9488\u7EC7\u886B(\u5976\u725B\uFF09</cnName><midleDefaultProductUrl>http://d4.yihaodian.com/t1/2009/05/21/62277_200x200.jpg</midleDefaultProductUrl><miniDefaultProductUrl>http://d4.yihaodian.com/t1/2009/05/21/62277_80x80.jpg</miniDefaultProductUrl><maketPrice>138.0</maketPrice><price>32.0</price><canBuy>true</canBuy><description></description><advertisement></advertisement></com.yihaodian.mobile.vo.product.ProductVO><com.yihaodian.mobile.vo.product.ProductVO><productId>23322</productId><merchantIds><long>1</long></merchantIds><merchantId>1</merchantId><cnName>kolomolo\u957F\u8896\u9488\u7EC7\u886B\uFF08\u7C73\u62C9\uFF09</cnName><midleDefaultProductUrl>http://d1.yihaodian.com/t1/2009/07/23/82596_200x200.jpg</midleDefaultProductUrl><miniDefaultProductUrl>http://d1.yihaodian.com/t1/2009/07/23/82596_80x80.jpg</miniDefaultProductUrl><maketPrice>158.0</maketPrice><price>32.0</price><canBuy>true</canBuy><description></description><advertisement></advertisement></com.yihaodian.mobile.vo.product.ProductVO><com.yihaodian.mobile.vo.product.ProductVO><productId>38243</productId><merchantIds><long>1</long></merchantIds><merchantId>1</merchantId><cnName>\u5929\u4F7F\u5C9B \u6DD8\u6C14\u5C0F\u5F1F\u5957\u5934\u886B</cnName><midleDefaultProductUrl>http://d4.yihaodian.com/t1/2009/09/25/98668_200x200.jpg</midleDefaultProductUrl><miniDefaultProductUrl>http://d4.yihaodian.com/t1/2009/09/25/98668_80x80.jpg</miniDefaultProductUrl><maketPrice>109.0</maketPrice><price>19.0</price><canBuy>true</canBuy><description></description><advertisement></advertisement></com.yihaodian.mobile.vo.product.ProductVO><com.yihaodian.mobile.vo.product.ProductVO><productId>38206</productId><merchantIds><long>1</long></merchantIds><merchantId>1</merchantId><cnName>\u5929\u4F7F\u5C9B \u9B54\u6CD5\u5A03\u5A03\u5706\u9886T\u6064</cnName><midleDefaultProductUrl>http://d4.yihaodian.com/t1/2009/09/25/98624_200x200.jpg</midleDefaultProductUrl><miniDefaultProductUrl>http://d4.yihaodian.com/t1/2009/09/25/98624_80x80.jpg</miniDefaultProductUrl><maketPrice>59.0</maketPrice><price>9.9</price><canBuy>true</canBuy><description></description><advertisement></advertisement></com.yihaodian.mobile.vo.product.ProductVO><com.yihaodian.mobile.vo.product.ProductVO><productId>38245</productId><merchantIds><long>1</long></merchantIds><merchantId>1</merchantId><cnName>\u5929\u4F7F\u5C9B \u5929\u624D\u5B9D\u8D1D\u5370\u82B1T\u6064</cnName><midleDefaultProductUrl>http://d1.yihaodian.com/t1/2009/09/25/98709_200x200.jpg</midleDefaultProductUrl><miniDefaultProductUrl>http://d1.yihaodian.com/t1/2009/09/25/98709_80x80.jpg</miniDefaultProductUrl><maketPrice>65.0</maketPrice><price>9.9</price><canBuy>true</canBuy><description></description><advertisement></advertisement></com.yihaodian.mobile.vo.product.ProductVO><com.yihaodian.mobile.vo.product.ProductVO><productId>38246</productId><merchantIds><long>1</long></merchantIds><merchantId>1</merchantId><cnName>\u5929\u4F7F\u5C9B \u957F\u8896T\u6064</cnName><midleDefaultProductUrl>http://d1.yihaodian.com/t1/2009/09/25/98556_200x200.jpg</midleDefaultProductUrl><miniDefaultProductUrl>http://d1.yihaodian.com/t1/2009/09/25/98556_80x80.jpg</miniDefaultProductUrl><maketPrice>59.0</maketPrice><price>9.9</price><canBuy>true</canBuy><description></description><advertisement></advertisement></com.yihaodian.mobile.vo.product.ProductVO><com.yihaodian.mobile.vo.product.ProductVO><productId>38247</productId><merchantIds><long>1</long></merchantIds><merchantId>1</merchantId><cnName>\u5929\u4F7F\u5C9B \u6761\u7EB9poloT\u6064</cnName><midleDefaultProductUrl>http://d2.yihaodian.com/t1/2009/09/25/98561_200x200.jpg</midleDefaultProductUrl><miniDefaultProductUrl>http://d2.yihaodian.com/t1/2009/09/25/98561_80x80.jpg</miniDefaultProductUrl><maketPrice>69.0</maketPrice><price>39.0</price><canBuy>true</canBuy><description></description><advertisement></advertisement></com.yihaodian.mobile.vo.product.ProductVO></objList></com.yihaodian.mobile.vo.core.Page>";
    }
}
