package cn.wp.bigdata.dataanalysis.algorithm.test;

import static cn.hutool.core.util.ArrayUtil.swap;

/**
 * 快速排序 test
 * 算法分析：
 * 0.思路：分治法：每次确定一个元素的位置，然后以当前元素分成2个部分，每个部分再重新循环这个过程。
 * 1.数据结构：自身的数组即可
 * 2.空间复杂度：1.  需要一个中间变量，交换时临时存放数据.
 * 3.时间复杂度：
 * 1)在有序情况下，没有交换，比较次数依次为：n-1, n-2, ..., 3,2,1  .加起来就是(n-1 + 1)*(n-1)/2= n*(n-1)/2
 * 2)在完全无需下，每次拿到的元素都是本次数组中的中间数，所以第一次比较次数是n-1,然后分成两部分，每部分比较次数是(n-2)/2,两部分就是2*(n-2)/2
 * 所以，比较次数依次为： n-1，2*(n-1)/2， 4*(n-1)/4， 8*(n-1)/8， log(n, 2)*(n-1)/log(n, 2)
 * (n-1 )  * log(n, 2), 简化为n*log(n,2)
 */
public class QuickSortTest {
    public void sort(int[] array) {
        int length = array.length;
        if (length <= 1) {
            return;
        }

        quickSortRoutine(0, length - 1, array);
    }

    private void quickSortRoutine(int start, int end, int[] array) {
        System.out.println("a = " + start + ";b= " + end + "");
        if (start >= end) {//越界就不用比较
            return;
        }
        System.out.println("into sort: a = " + start + ";b= " + end + "");


        //1.找到选定元素的位置，然后分成两部分再进行排序
        //确定位置的思路：选定end元素，index从start开始增长；如果开头元素大于后面元素，则交换，同时index从end开始递减比较。直到Indexstart >= IndexEnd
        int IndexStart = start;
        int IndexEnd = end;
        boolean curEnd = true;//当前从end开始比较
        int curIndex = end;
        while (IndexStart < IndexEnd) {
            if (curEnd) {
                if (array[IndexStart] > array[IndexEnd]) {
                    //交换
                    swap(array, IndexStart, IndexEnd);
                    IndexEnd--;
                    curEnd = false;
                    curIndex = IndexStart;
                } else {
                    IndexStart++;
                }
            } else {
                if (array[IndexStart] > array[IndexEnd]) {
                    //交换
                    swap(array, IndexStart, IndexEnd);
                    IndexStart++;
                    curEnd = true;
                    curIndex = IndexEnd;
                } else {
                    IndexEnd--;
                }
            }
        }

        //2. 分成两部分再进行排序
        int middle = curIndex;
        quickSortRoutine(start, middle - 1, array);
        quickSortRoutine(middle + 1, end, array);
    }

    public static void main(String[] args) {
        int[] array = {2, 1, 3, 6, 9, 5, 4, 0};
        QuickSortTest quickSortTest = new QuickSortTest();
        quickSortTest.sort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}
