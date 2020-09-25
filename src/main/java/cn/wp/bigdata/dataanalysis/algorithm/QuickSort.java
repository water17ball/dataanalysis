package cn.wp.bigdata.dataanalysis.algorithm;

/**
 * 快速排序：利用分治法，将一个问题拆分为多个子问题的求解。每个子问题都是独立。分治法是将整体最优分解为子问题最优。
 * <p>
 * 本问题的关键是边界，与交换、移动等等。同时交换之后，要更新数据的当前位置、与之比对的数据的位置。
 *
 * 算法分析：
 * 0.思路：分治法：每次确定一个元素的位置，然后以当前元素分成2个部分，每个部分再重新循环这个过程。
 * 1.数据结构：自身的数组即可
 * 2.空间复杂度：1.  需要一个中间变量，交换时临时存放数据.
 * 3.时间复杂度：
 *   1)在有序情况下，没有交换，比较次数依次为：n-1, n-2, ..., 3,2,1  .加起来就是(n-1 + 1)*(n-1)/2= n*(n-1)/2
 *   2)在完全无需下，每次拿到的元素都是本次数组中的中间数，所以第一次比较次数是n-1,然后分成两部分，每部分比较次数是(n-2)/2,两部分就是2*(n-2)/2
 *     所以，比较次数依次为： n-1，2*(n-1)/2， 4*(n-1)/4， 8*(n-1)/8， log(n, 2)*(n-1)/log(n, 2)
 *     (n-1 )  * log(n, 2), 简化为n*log(n,2)
 *
 */
public class QuickSort {

    public void sort(int[] a, int left, int right) {
        System.out.println("left:" + left + ", right:" + right);

        if (left >= right) {//越界了，就跳出.  递归跳出条件
            return;
        }

        int leftIndex = left;
        int rightIndex = right;
        boolean direction = true;
        int position = rightIndex;//以最右侧为快速排序的第一个数，需要确定它的最终位置

        while (leftIndex < rightIndex) {//将数组划分为两个部分，当前最右边的树找到最终位置即可。
            if (a[leftIndex] > a[rightIndex]) {//保证左侧比右侧小。如果左侧大，则交换。
                int temp = a[leftIndex];
                a[leftIndex] = a[rightIndex];
                a[rightIndex] = temp;

                //如果交换，则更新当前数的位置
                position = leftIndex;

                //同时改变收缩比较的方向
                direction = !direction;

                //改变比较的index位置
                if (direction) {
                    leftIndex++;

                    //如果交换，则更新当前数的位置
                    position = rightIndex;
                } else {
                    rightIndex--;

                    //如果交换，则更新当前数的位置
                    position = leftIndex;
                }
            } else {
                //改变比较的index位置
                if (direction) {
                    leftIndex++;
                } else {
                    rightIndex--;
                }
            }
        }

        //再将数组分为两个部分，继续递归
        sort(a, left, position - 1);
        sort(a, position + 1, right);

    }

    public void sort_smart(int[] a, int left, int right) {
        System.out.println("left:" + left + ", right:" + right);

        if (left >= right) {//越界了，就跳出.  递归跳出条件
            return;
        }

        int leftIndex = left;
        int rightIndex = right;
        int temp = a[rightIndex];

        while (leftIndex < rightIndex) {//将数组划分为两个部分，当前最右边的树找到最终位置即可。
            while ((leftIndex < rightIndex) && (temp > a[leftIndex])) {
                leftIndex++;
            }
            if (leftIndex < rightIndex) {
                a[rightIndex--] = a[leftIndex];
            }
            while ((leftIndex < rightIndex) && (temp < a[rightIndex])) {
                rightIndex--;
            }
            if (leftIndex < rightIndex) {
                a[leftIndex++] = a[rightIndex];
            }
        }
        a[rightIndex] = temp;

        //再将数组分为两个部分，继续递归
        sort(a, left, rightIndex - 1);
        sort(a, rightIndex + 1, right);

    }

    public static void main(String[] args) {
        int[] a = new int[]{0, 3, 2, 8, 6, 5, 1, 9, 7, 4};

//        int[] a = new int[]{4,9,7,8,6,5,1,3,2,0};

        QuickSort quickSort = new QuickSort();
//        quickSort.sort(a, 0, a.length-1);
        quickSort.sort_smart(a, 0, a.length - 1);

        for (int i : a) {
            System.out.println(i);
        }
    }
}
