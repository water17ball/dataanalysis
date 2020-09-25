package cn.enn.bigdata.dataanalysis.algorithm;

/**
 * 快速排序：利用分治法，将一个问题拆分为多个子问题的求解。每个子问题都是独立。分治法是将整体最优分解为子问题最优。
 * <p>
 * 本问题的关键是边界，与交换、移动等等。同时交换之后，要更新数据的当前位置、与之比对的数据的位置。
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
