// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.datamatrix.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.*;
import com.google.zxing.common.detector.WhiteRectangleDetector;
import java.util.*;

public final class Detector
{
    private static class ResultPointsAndTransitions
    {

        public ResultPoint getFrom()
        {
            return from;
        }

        public ResultPoint getTo()
        {
            return to;
        }

        public int getTransitions()
        {
            return transitions;
        }

        @Override
		public String toString()
        {
            return (new StringBuilder()).append(from).append("/").append(to).append('/').append(transitions).toString();
        }

        private final ResultPoint from;
        private final ResultPoint to;
        private final int transitions;

        private ResultPointsAndTransitions(ResultPoint resultpoint, ResultPoint resultpoint1, int i)
        {
            from = resultpoint;
            to = resultpoint1;
            transitions = i;
        }

        ResultPointsAndTransitions(ResultPoint resultpoint, ResultPoint resultpoint1, int i, ResultPointsAndTransitions resultpointsandtransitions)
        {
            this(resultpoint, resultpoint1, i);
        }
    }

    private static class ResultPointsAndTransitionsComparator
        implements Comparator
    {

        public int compare(Object obj, Object obj1)
        {
            return ((ResultPointsAndTransitions)obj).getTransitions() - ((ResultPointsAndTransitions)obj1).getTransitions();
        }

        private ResultPointsAndTransitionsComparator()
        {
        }

        ResultPointsAndTransitionsComparator(ResultPointsAndTransitionsComparator resultpointsandtransitionscomparator)
        {
            this();
        }
    }


    public Detector(BitMatrix bitmatrix)
    {
        image = bitmatrix;
        rectangleDetector = new WhiteRectangleDetector(bitmatrix);
    }

    private ResultPoint correctTopRight(ResultPoint resultpoint, ResultPoint resultpoint1, ResultPoint resultpoint2, ResultPoint resultpoint3, int i)
    {
        float f = (float)distance(resultpoint, resultpoint1) / (float)i;
        int j = distance(resultpoint2, resultpoint3);
        float f1 = (resultpoint3.getX() - resultpoint2.getX()) / j;
        float f2 = (resultpoint3.getY() - resultpoint2.getY()) / j;
        ResultPoint resultpoint4 = new ResultPoint(resultpoint3.getX() + f * f1, resultpoint3.getY() + f * f2);
        float f3 = (float)distance(resultpoint, resultpoint1) / (float)i;
        int k = distance(resultpoint1, resultpoint3);
        float f4 = (resultpoint3.getX() - resultpoint1.getX()) / k;
        float f5 = (resultpoint3.getY() - resultpoint1.getY()) / k;
        ResultPoint resultpoint5 = new ResultPoint(resultpoint3.getX() + f3 * f4, resultpoint3.getY() + f3 * f5);
        ResultPoint resultpoint6;
        if(!isValid(resultpoint4))
        {
            if(isValid(resultpoint5))
                resultpoint6 = resultpoint5;
            else
                resultpoint6 = null;
        } else
        if(!isValid(resultpoint5))
            resultpoint6 = resultpoint4;
        else
        if(Math.abs(transitionsBetween(resultpoint2, resultpoint4).getTransitions() - transitionsBetween(resultpoint1, resultpoint4).getTransitions()) <= Math.abs(transitionsBetween(resultpoint2, resultpoint5).getTransitions() - transitionsBetween(resultpoint1, resultpoint5).getTransitions()))
            resultpoint6 = resultpoint4;
        else
            resultpoint6 = resultpoint5;
        return resultpoint6;
    }

    private static int distance(ResultPoint resultpoint, ResultPoint resultpoint1)
    {
        return round((float)Math.sqrt((resultpoint.getX() - resultpoint1.getX()) * (resultpoint.getX() - resultpoint1.getX()) + (resultpoint.getY() - resultpoint1.getY()) * (resultpoint.getY() - resultpoint1.getY())));
    }

    private static void increment(Hashtable hashtable, ResultPoint resultpoint)
    {
        Integer integer = (Integer)hashtable.get(resultpoint);
        Integer integer1;
        if(integer == null)
            integer1 = INTEGERS[1];
        else
            integer1 = INTEGERS[1 + integer.intValue()];
        hashtable.put(resultpoint, integer1);
    }

    private boolean isValid(ResultPoint resultpoint)
    {
        boolean flag;
        if(resultpoint.getX() >= 0.0F && resultpoint.getX() < image.width && resultpoint.getY() > 0.0F && resultpoint.getY() < image.height)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private static int round(float f)
    {
        return (int)(0.5F + f);
    }

    private static BitMatrix sampleGrid(BitMatrix bitmatrix, ResultPoint resultpoint, ResultPoint resultpoint1, ResultPoint resultpoint2, ResultPoint resultpoint3, int i)
        throws NotFoundException
    {
        return GridSampler.getInstance().sampleGrid(bitmatrix, i, 0.5F, 0.5F, i - 0.5F, 0.5F, i - 0.5F, i - 0.5F, 0.5F, i - 0.5F, resultpoint.getX(), resultpoint.getY(), resultpoint3.getX(), resultpoint3.getY(), resultpoint2.getX(), resultpoint2.getY(), resultpoint1.getX(), resultpoint1.getY());
    }

    private ResultPointsAndTransitions transitionsBetween(ResultPoint resultpoint, ResultPoint resultpoint1)
    {
        int l;
        boolean flag;
        int i1;
        int j1;
        int k1;
        byte byte0;
        byte byte1;
        int l1;
        boolean flag1;
        int k2;
        int l2;
        int i = (int)resultpoint.getX();
        int j = (int)resultpoint.getY();
        int k = (int)resultpoint1.getX();
        l = (int)resultpoint1.getY();
        BitMatrix bitmatrix;
        int i2;
        int j2;
        ResultPointsAndTransitions resultpointsandtransitions;
        if(Math.abs(l - j) > Math.abs(k - i))
            flag = true;
        else
            flag = false;
        if(flag)
        {
            int k3 = i;
            i = j;
            j = k3;
            int l3 = k;
            k = l;
            l = l3;
        }
        i1 = Math.abs(k - i);
        j1 = Math.abs(l - j);
        k1 = -i1 >> 1;
        if(j < l)
            byte0 = 1;
        else
            byte0 = -1;
        if(i < k)
            byte1 = 1;
        else
            byte1 = -1;
        l1 = 0;
        bitmatrix = image;
        if(flag)
            i2 = j;
        else
            i2 = i;
        if(flag)
            j2 = i;
        else
            j2 = j;
        flag1 = bitmatrix.get(i2, j2);
        k2 = i;
        l2 = j;
_L5:
        if(k2 != k) goto _L2; else goto _L1
_L1:
        resultpointsandtransitions = new ResultPointsAndTransitions(resultpoint, resultpoint1, l1, null);
        return resultpointsandtransitions;
_L2:
        BitMatrix bitmatrix1 = image;
        int i3;
        int j3;
        boolean flag2;
        if(flag)
            i3 = l2;
        else
            i3 = k2;
        if(flag)
            j3 = k2;
        else
            j3 = l2;
        flag2 = bitmatrix1.get(i3, j3);
        if(flag2 != flag1)
        {
            l1++;
            flag1 = flag2;
        }
        k1 += j1;
        if(k1 <= 0)
            break; /* Loop/switch isn't completed */
        if(l2 == l)
            continue; /* Loop/switch isn't completed */
        l2 += byte0;
        k1 -= i1;
        break; /* Loop/switch isn't completed */
        if(true) goto _L1; else goto _L3
_L3:
        k2 += byte1;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public DetectorResult detect()
        throws NotFoundException
    {
        ResultPoint aresultpoint[] = rectangleDetector.detect();
        ResultPoint resultpoint = aresultpoint[0];
        ResultPoint resultpoint1 = aresultpoint[1];
        ResultPoint resultpoint2 = aresultpoint[2];
        ResultPoint resultpoint3 = aresultpoint[3];
        Vector vector = new Vector(4);
        vector.addElement(transitionsBetween(resultpoint, resultpoint1));
        vector.addElement(transitionsBetween(resultpoint, resultpoint2));
        vector.addElement(transitionsBetween(resultpoint1, resultpoint3));
        vector.addElement(transitionsBetween(resultpoint2, resultpoint3));
        Collections.insertionSort(vector, new ResultPointsAndTransitionsComparator(null));
        ResultPointsAndTransitions resultpointsandtransitions = (ResultPointsAndTransitions)vector.elementAt(0);
        ResultPointsAndTransitions resultpointsandtransitions1 = (ResultPointsAndTransitions)vector.elementAt(1);
        Hashtable hashtable = new Hashtable();
        increment(hashtable, resultpointsandtransitions.getFrom());
        increment(hashtable, resultpointsandtransitions.getTo());
        increment(hashtable, resultpointsandtransitions1.getFrom());
        increment(hashtable, resultpointsandtransitions1.getTo());
        ResultPoint resultpoint4 = null;
        ResultPoint resultpoint5 = null;
        ResultPoint resultpoint6 = null;
        Enumeration enumeration = hashtable.keys();
        do
        {
            if(!enumeration.hasMoreElements())
            {
                if(resultpoint4 == null || resultpoint5 == null || resultpoint6 == null)
                    throw NotFoundException.getNotFoundInstance();
                break;
            }
            ResultPoint resultpoint7 = (ResultPoint)enumeration.nextElement();
            if(((Integer)hashtable.get(resultpoint7)).intValue() == 2)
                resultpoint5 = resultpoint7;
            else
            if(resultpoint4 == null)
                resultpoint4 = resultpoint7;
            else
                resultpoint6 = resultpoint7;
        } while(true);
        ResultPoint aresultpoint1[] = new ResultPoint[3];
        aresultpoint1[0] = resultpoint4;
        aresultpoint1[1] = resultpoint5;
        aresultpoint1[2] = resultpoint6;
        ResultPoint.orderBestPatterns(aresultpoint1);
        ResultPoint resultpoint8 = aresultpoint1[0];
        ResultPoint resultpoint9 = aresultpoint1[1];
        ResultPoint resultpoint10 = aresultpoint1[2];
        ResultPoint resultpoint11;
        int i;
        ResultPoint resultpoint12;
        int j;
        BitMatrix bitmatrix;
        ResultPoint aresultpoint2[];
        if(!hashtable.containsKey(resultpoint))
            resultpoint11 = resultpoint;
        else
        if(!hashtable.containsKey(resultpoint1))
            resultpoint11 = resultpoint1;
        else
        if(!hashtable.containsKey(resultpoint2))
            resultpoint11 = resultpoint2;
        else
            resultpoint11 = resultpoint3;
        i = Math.min(transitionsBetween(resultpoint10, resultpoint11).getTransitions(), transitionsBetween(resultpoint8, resultpoint11).getTransitions());
        if((i & 1) == 1)
            i++;
        resultpoint12 = correctTopRight(resultpoint9, resultpoint8, resultpoint10, resultpoint11, i + 2);
        if(resultpoint12 == null)
            resultpoint12 = resultpoint11;
        j = 1 + Math.max(transitionsBetween(resultpoint10, resultpoint12).getTransitions(), transitionsBetween(resultpoint8, resultpoint12).getTransitions());
        if((j & 1) == 1)
            j++;
        bitmatrix = sampleGrid(image, resultpoint10, resultpoint9, resultpoint8, resultpoint12, j);
        aresultpoint2 = new ResultPoint[4];
        aresultpoint2[0] = resultpoint10;
        aresultpoint2[1] = resultpoint9;
        aresultpoint2[2] = resultpoint8;
        aresultpoint2[3] = resultpoint12;
        return new DetectorResult(bitmatrix, aresultpoint2);
    }

    private static final Integer INTEGERS[];
    private final BitMatrix image;
    private final WhiteRectangleDetector rectangleDetector;

    static 
    {
        Integer ainteger[] = new Integer[5];
        ainteger[0] = new Integer(0);
        ainteger[1] = new Integer(1);
        ainteger[2] = new Integer(2);
        ainteger[3] = new Integer(3);
        ainteger[4] = new Integer(4);
        INTEGERS = ainteger;
    }
}
