// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.client.result;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.google.zxing.oned.UPCEReader;

// Referenced classes of package com.google.zxing.client.result:
//            ResultParser, ProductParsedResult

final class ProductResultParser extends ResultParser
{

    private ProductResultParser()
    {
    }

    public static ProductParsedResult parse(Result result)
    {
        BarcodeFormat barcodeformat = result.getBarcodeFormat();
        if(BarcodeFormat.UPC_A.equals(barcodeformat) || BarcodeFormat.UPC_E.equals(barcodeformat) || BarcodeFormat.EAN_8.equals(barcodeformat) || BarcodeFormat.EAN_13.equals(barcodeformat)) goto _L2; else goto _L1
_L1:
        ProductParsedResult productparsedresult = null;
_L7:
        return productparsedresult;
_L2:
        String s = result.getText();
        if(s != null) goto _L4; else goto _L3
_L3:
        productparsedresult = null;
          goto _L5
_L4:
        int i;
        int j;
        i = s.length();
        j = 0;
_L8:
        if(j >= i)
        {
            char c;
            String s1;
            if(BarcodeFormat.UPC_E.equals(barcodeformat))
                s1 = UPCEReader.convertUPCEtoUPCA(s);
            else
                s1 = s;
            productparsedresult = new ProductParsedResult(s, s1);
        } else
        {
label0:
            {
                c = s.charAt(j);
                if(c >= '0' && c <= '9')
                    break label0;
                productparsedresult = null;
            }
        }
_L5:
        if(true) goto _L7; else goto _L6
_L6:
        j++;
          goto _L8
    }
}
