// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.qrcode.detector;

import com.google.zxing.*;
import com.google.zxing.common.*;
import java.util.Hashtable;
import java.util.Vector;

// Referenced classes of package com.google.zxing.qrcode.detector:
//            FinderPattern, FinderPatternInfo

public class FinderPatternFinder
{
    private static class CenterComparator
        implements Comparator
    {

        public int compare(Object obj, Object obj1)
        {
            int i;
            if(((FinderPattern)obj1).getCount() != ((FinderPattern)obj).getCount())
            {
                i = ((FinderPattern)obj1).getCount() - ((FinderPattern)obj).getCount();
            } else
            {
                float f = Math.abs(((FinderPattern)obj1).getEstimatedModuleSize() - average);
                float f1 = Math.abs(((FinderPattern)obj).getEstimatedModuleSize() - average);
                if(f < f1)
                    i = 1;
                else
                if(f == f1)
                    i = 0;
                else
                    i = -1;
            }
            return i;
        }

        private final float average;

        public CenterComparator(float f)
        {
            average = f;
        }
    }

    private static class FurthestFromAverageComparator
        implements Comparator
    {

        public int compare(Object obj, Object obj1)
        {
            float f = Math.abs(((FinderPattern)obj1).getEstimatedModuleSize() - average);
            float f1 = Math.abs(((FinderPattern)obj).getEstimatedModuleSize() - average);
            byte byte0;
            if(f < f1)
                byte0 = -1;
            else
            if(f == f1)
                byte0 = 0;
            else
                byte0 = 1;
            return byte0;
        }

        private final float average;

        public FurthestFromAverageComparator(float f)
        {
            average = f;
        }
    }


    public FinderPatternFinder(BitMatrix bitmatrix)
    {
        this(bitmatrix, null);
    }

    public FinderPatternFinder(BitMatrix bitmatrix, ResultPointCallback resultpointcallback)
    {
        image = bitmatrix;
        possibleCenters = new Vector();
        crossCheckStateCount = new int[5];
        resultPointCallback = resultpointcallback;
    }

    private static float centerFromEnd(int ai[], int i)
    {
        return (float)(i - ai[4] - ai[3]) - (float)ai[2] / 2.0F;
    }

    private float crossCheckHorizontal(int i, int j, int k, int l)
    {
        BitMatrix bitmatrix;
        int i1;
        int ai[];
        int j1;
        bitmatrix = image;
        i1 = bitmatrix.getWidth();
        ai = getCrossCheckStateCount();
        j1 = i;
_L3:
        if(j1 >= 0 && bitmatrix.get(j1, j)) goto _L2; else goto _L1
_L1:
        float f;
        if(j1 < 0)
        {
            f = (0.0F / 0.0F);
        } else
        {
            for(; j1 >= 0 && !bitmatrix.get(j1, j) && ai[1] <= k; j1--)
                ai[1] = 1 + ai[1];

            if(j1 < 0 || ai[1] > k)
            {
                f = (0.0F / 0.0F);
            } else
            {
label0:
                {
                    for(; j1 >= 0 && bitmatrix.get(j1, j) && ai[0] <= k; j1--)
                        ai[0] = 1 + ai[0];

                    if(ai[0] <= k)
                        break label0;
                    f = (0.0F / 0.0F);
                }
            }
        }
_L4:
        return f;
_L2:
        ai[2] = 1 + ai[2];
        j1--;
          goto _L3
        int k1 = i + 1;
_L5:
label1:
        {
            if(k1 < i1 && bitmatrix.get(k1, j))
                break label1;
            if(k1 == i1)
            {
                f = (0.0F / 0.0F);
            } else
            {
                for(; k1 < i1 && !bitmatrix.get(k1, j) && ai[3] < k; k1++)
                    ai[3] = 1 + ai[3];

                if(k1 == i1 || ai[3] >= k)
                {
                    f = (0.0F / 0.0F);
                } else
                {
                    for(; k1 < i1 && bitmatrix.get(k1, j) && ai[4] < k; k1++)
                        ai[4] = 1 + ai[4];

                    if(ai[4] >= k)
                        f = (0.0F / 0.0F);
                    else
                    if(5 * Math.abs((ai[0] + ai[1] + ai[2] + ai[3] + ai[4]) - l) >= l)
                        f = (0.0F / 0.0F);
                    else
                    if(foundPatternCross(ai))
                        f = centerFromEnd(ai, k1);
                    else
                        f = (0.0F / 0.0F);
                }
            }
        }
          goto _L4
        ai[2] = 1 + ai[2];
        k1++;
          goto _L5
    }

    private float crossCheckVertical(int i, int j, int k, int l)
    {
        BitMatrix bitmatrix;
        int i1;
        int ai[];
        int j1;
        bitmatrix = image;
        i1 = bitmatrix.getHeight();
        ai = getCrossCheckStateCount();
        j1 = i;
_L3:
        if(j1 >= 0 && bitmatrix.get(j, j1)) goto _L2; else goto _L1
_L1:
        float f;
        if(j1 < 0)
        {
            f = (0.0F / 0.0F);
        } else
        {
            for(; j1 >= 0 && !bitmatrix.get(j, j1) && ai[1] <= k; j1--)
                ai[1] = 1 + ai[1];

            if(j1 < 0 || ai[1] > k)
            {
                f = (0.0F / 0.0F);
            } else
            {
label0:
                {
                    for(; j1 >= 0 && bitmatrix.get(j, j1) && ai[0] <= k; j1--)
                        ai[0] = 1 + ai[0];

                    if(ai[0] <= k)
                        break label0;
                    f = (0.0F / 0.0F);
                }
            }
        }
_L4:
        return f;
_L2:
        ai[2] = 1 + ai[2];
        j1--;
          goto _L3
        int k1 = i + 1;
_L5:
label1:
        {
            if(k1 < i1 && bitmatrix.get(j, k1))
                break label1;
            if(k1 == i1)
            {
                f = (0.0F / 0.0F);
            } else
            {
                for(; k1 < i1 && !bitmatrix.get(j, k1) && ai[3] < k; k1++)
                    ai[3] = 1 + ai[3];

                if(k1 == i1 || ai[3] >= k)
                {
                    f = (0.0F / 0.0F);
                } else
                {
                    for(; k1 < i1 && bitmatrix.get(j, k1) && ai[4] < k; k1++)
                        ai[4] = 1 + ai[4];

                    if(ai[4] >= k)
                        f = (0.0F / 0.0F);
                    else
                    if(5 * Math.abs((ai[0] + ai[1] + ai[2] + ai[3] + ai[4]) - l) >= l * 2)
                        f = (0.0F / 0.0F);
                    else
                    if(foundPatternCross(ai))
                        f = centerFromEnd(ai, k1);
                    else
                        f = (0.0F / 0.0F);
                }
            }
        }
          goto _L4
        ai[2] = 1 + ai[2];
        k1++;
          goto _L5
    }

    private int findRowSkip()
    {
        int i = possibleCenters.size();
        if(i > 1) goto _L2; else goto _L1
_L1:
        int k = 0;
_L4:
        return k;
_L2:
        FinderPattern finderpattern = null;
        int j = 0;
        FinderPattern finderpattern1;
        do
        {
            if(j >= i)
            {
                k = 0;
                continue; /* Loop/switch isn't completed */
            }
            finderpattern1 = (FinderPattern)possibleCenters.elementAt(j);
            if(finderpattern1.getCount() >= 2)
            {
                if(finderpattern != null)
                    break;
                finderpattern = finderpattern1;
            }
            j++;
        } while(true);
        hasSkipped = true;
        k = (int)(Math.abs(finderpattern.getX() - finderpattern1.getX()) - Math.abs(finderpattern.getY() - finderpattern1.getY())) / 2;
        if(true) goto _L4; else goto _L3
_L3:
    }

    protected static boolean foundPatternCross(int ai[])
    {
        int i;
        int j;
        i = 0;
        j = 0;
_L4:
label0:
        {
            {
                if(j < 5)
                    break label0;
                int k;
                boolean flag;
                if(i < 7)
                {
                    flag = false;
                } else
                {
                    int l = (i << 8) / 7;
                    int i1 = l / 2;
                    if(Math.abs(l - (ai[0] << 8)) < i1 && Math.abs(l - (ai[1] << 8)) < i1 && Math.abs(l * 3 - (ai[2] << 8)) < i1 * 3 && Math.abs(l - (ai[3] << 8)) < i1 && Math.abs(l - (ai[4] << 8)) < i1)
                        flag = true;
                    else
                        flag = false;
                }
            }
            return flag;
        }
        k = ai[j];
        if(k != 0)
            break; /* Loop/switch isn't completed */
        flag = false;
        if(true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_18;
_L1:
        i += k;
        j++;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private int[] getCrossCheckStateCount()
    {
        crossCheckStateCount[0] = 0;
        crossCheckStateCount[1] = 0;
        crossCheckStateCount[2] = 0;
        crossCheckStateCount[3] = 0;
        crossCheckStateCount[4] = 0;
        return crossCheckStateCount;
    }

    private boolean haveMultiplyConfirmedCenters()
    {
        int i;
        float f;
        int j;
        int k;
        i = 0;
        f = 0.0F;
        j = possibleCenters.size();
        k = 0;
_L5:
        if(k < j) goto _L2; else goto _L1
_L1:
        if(i >= 3) goto _L4; else goto _L3
_L3:
        boolean flag = false;
_L6:
        return flag;
_L2:
        FinderPattern finderpattern = (FinderPattern)possibleCenters.elementAt(k);
        if(finderpattern.getCount() >= 2)
        {
            i++;
            f += finderpattern.getEstimatedModuleSize();
        }
        k++;
          goto _L5
_L4:
        float f1;
        float f2;
        int l;
        f1 = f / (float)j;
        f2 = 0.0F;
        l = 0;
_L7:
label0:
        {
            if(l < j)
                break label0;
            if(f2 <= 0.05F * f)
                flag = true;
            else
                flag = false;
        }
          goto _L6
        f2 += Math.abs(((FinderPattern)possibleCenters.elementAt(l)).getEstimatedModuleSize() - f1);
        l++;
          goto _L7
    }

    private FinderPattern[] selectBestPatterns()
        throws NotFoundException
    {
        int i;
        i = possibleCenters.size();
        if(i < 3)
            throw NotFoundException.getNotFoundInstance();
        if(i <= 3) goto _L2; else goto _L1
_L1:
        float f2;
        float f3;
        int k;
        f2 = 0.0F;
        f3 = 0.0F;
        k = 0;
_L8:
        if(k < i) goto _L4; else goto _L3
_L3:
        float f5;
        float f7;
        int l;
        f5 = f2 / (float)i;
        float f6 = (float)Math.sqrt(f3 / (float)i - f5 * f5);
        Collections.insertionSort(possibleCenters, new FurthestFromAverageComparator(f5));
        f7 = Math.max(0.2F * f5, f6);
        l = 0;
_L9:
        if(l < possibleCenters.size() && possibleCenters.size() > 3) goto _L5; else goto _L2
_L2:
        if(possibleCenters.size() <= 3) goto _L7; else goto _L6
_L6:
        float f;
        int j;
        f = 0.0F;
        j = 0;
_L10:
        if(j < possibleCenters.size())
            break MISSING_BLOCK_LABEL_314;
        float f1 = f / (float)possibleCenters.size();
        Collections.insertionSort(possibleCenters, new CenterComparator(f1));
        possibleCenters.setSize(3);
_L7:
        FinderPattern afinderpattern[] = new FinderPattern[3];
        afinderpattern[0] = (FinderPattern)possibleCenters.elementAt(0);
        afinderpattern[1] = (FinderPattern)possibleCenters.elementAt(1);
        afinderpattern[2] = (FinderPattern)possibleCenters.elementAt(2);
        return afinderpattern;
_L4:
        float f4 = ((FinderPattern)possibleCenters.elementAt(k)).getEstimatedModuleSize();
        f2 += f4;
        f3 += f4 * f4;
        k++;
          goto _L8
_L5:
        if(Math.abs(((FinderPattern)possibleCenters.elementAt(l)).getEstimatedModuleSize() - f5) > f7)
        {
            possibleCenters.removeElementAt(l);
            l--;
        }
        l++;
          goto _L9
        f += ((FinderPattern)possibleCenters.elementAt(j)).getEstimatedModuleSize();
        j++;
          goto _L10
    }

    FinderPatternInfo find(Hashtable hashtable)
        throws NotFoundException
    {
        int j;
        int k;
        boolean flag1;
        int ai[];
        int l;
        boolean flag;
        int i;
        FinderPattern afinderpattern[];
        if(hashtable != null && hashtable.containsKey(DecodeHintType.TRY_HARDER))
            flag = true;
        else
            flag = false;
        i = image.getHeight();
        j = image.getWidth();
        k = (i * 3) / 228;
        if(k < 3 || flag)
            k = 3;
        flag1 = false;
        ai = new int[5];
        l = k - 1;
_L2:
        if(l >= i || flag1)
        {
            afinderpattern = selectBestPatterns();
            ResultPoint.orderBestPatterns(afinderpattern);
            return new FinderPatternInfo(afinderpattern);
        }
        ai[0] = 0;
        ai[1] = 0;
        ai[2] = 0;
        ai[3] = 0;
        ai[4] = 0;
        int i1 = 0;
        int j1 = 0;
        do
        {
label0:
            {
                if(j1 < j)
                    break label0;
                if(foundPatternCross(ai) && handlePossibleCenter(ai, l, j))
                {
                    k = ai[0];
                    if(hasSkipped)
                        flag1 = haveMultiplyConfirmedCenters();
                }
                l += k;
            }
            if(true)
                continue;
            if(image.get(j1, l))
            {
                if((i1 & 1) == 1)
                    i1++;
                ai[i1] = 1 + ai[i1];
            } else
            if((i1 & 1) == 0)
            {
                if(i1 == 4)
                {
                    if(foundPatternCross(ai))
                    {
                        if(handlePossibleCenter(ai, l, j1))
                        {
                            k = 2;
                            if(hasSkipped)
                            {
                                flag1 = haveMultiplyConfirmedCenters();
                            } else
                            {
                                int k1 = findRowSkip();
                                if(k1 > ai[2])
                                {
                                    l += k1 - ai[2] - k;
                                    j1 = j - 1;
                                }
                            }
                            i1 = 0;
                            ai[0] = 0;
                            ai[1] = 0;
                            ai[2] = 0;
                            ai[3] = 0;
                            ai[4] = 0;
                        } else
                        {
                            ai[0] = ai[2];
                            ai[1] = ai[3];
                            ai[2] = ai[4];
                            ai[3] = 1;
                            ai[4] = 0;
                            i1 = 3;
                        }
                    } else
                    {
                        ai[0] = ai[2];
                        ai[1] = ai[3];
                        ai[2] = ai[4];
                        ai[3] = 1;
                        ai[4] = 0;
                        i1 = 3;
                    }
                } else
                {
                    i1++;
                    ai[i1] = 1 + ai[i1];
                }
            } else
            {
                ai[i1] = 1 + ai[i1];
            }
            j1++;
        } while(true);
        if(true) goto _L2; else goto _L1
_L1:
    }

    protected BitMatrix getImage()
    {
        return image;
    }

    protected Vector getPossibleCenters()
    {
        return possibleCenters;
    }

    protected boolean handlePossibleCenter(int ai[], int i, int j)
    {
        float f1;
        float f2;
        float f3;
        boolean flag1;
        int l;
        int i1;
        int k = ai[0] + ai[1] + ai[2] + ai[3] + ai[4];
        float f = centerFromEnd(ai, j);
        f1 = crossCheckVertical(i, (int)f, ai[2], k);
        if(Float.isNaN(f1))
            break MISSING_BLOCK_LABEL_204;
        f2 = crossCheckHorizontal((int)f, (int)f1, ai[2], k);
        if(Float.isNaN(f2))
            break MISSING_BLOCK_LABEL_204;
        f3 = (float)k / 7F;
        flag1 = false;
        l = possibleCenters.size();
        i1 = 0;
_L1:
        boolean flag;
        if(i1 < l)
        {
label0:
            {
                FinderPattern finderpattern = (FinderPattern)possibleCenters.elementAt(i1);
                if(!finderpattern.aboutEquals(f3, f1, f2))
                    break label0;
                finderpattern.incrementCount();
                flag1 = true;
            }
        }
        if(!flag1)
        {
            FinderPattern finderpattern1 = new FinderPattern(f2, f1, f3);
            possibleCenters.addElement(finderpattern1);
            if(resultPointCallback != null)
                resultPointCallback.foundPossibleResultPoint(finderpattern1);
        }
        flag = true;
_L2:
        return flag;
        i1++;
          goto _L1
        flag = false;
          goto _L2
    }

    private static final int CENTER_QUORUM = 2;
    private static final int INTEGER_MATH_SHIFT = 8;
    protected static final int MAX_MODULES = 57;
    protected static final int MIN_SKIP = 3;
    private final int crossCheckStateCount[];
    private boolean hasSkipped;
    private final BitMatrix image;
    private final Vector possibleCenters;
    private final ResultPointCallback resultPointCallback;
}
