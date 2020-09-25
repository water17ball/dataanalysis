package cn.wp.bigdata.dataanalysis.algorithm;

/**
 * 归并排序
 * 时间复杂度O(nlogn)，空间复杂度O(n)
 */
public class MergeSort {

    public void sort(int[] a) {//初始化所需空间
        int[] temp = new int[a.length];
        int left = 0;
        int right = a.length - 1;//这里是能取到的最右的数据,因为在merge过程中，left和right都是要能取到的值
        sort(a, left, right, temp);
    }

    public void sort(int[] a, int left, int right, int[] temp) {//开始二分法，分成两个队列。最后进行二队列的合并（排序也包含在合并过程中）。
        if (left < right) {
            int middle = (left + right) / 2;
            sort(a, left, middle, temp);
            sort(a, middle + 1, right, temp);
            merge(a, left, middle, right, temp);
        }
    }

    private void merge(int[] a, int left, int middle, int right, int[] temp) {
        int i = left;
        int j = middle + 1;
        int tempIndex = 0;
        //先把左右两边(有序)的数据按照规则填充到temp数组
        //直到左右两边的有序序列，有一边处理完毕为止
        while ((i <= middle) && (j <= right)) {
            if (a[i] < a[j]) {
                temp[tempIndex++] = a[i++];
            } else {
                temp[tempIndex++] = a[j++];
            }
        }
        //如果左边的有序序列的当前元素，小于等于右边有序序列的当前元素
        //即将左边的当前元素，填充到temp数组
        while (i <= middle) {
            temp[tempIndex++] = a[i++];
        }
        while (j <= right) {
            temp[tempIndex++] = a[j++];
        }
        //将temp数组的元素拷贝到arr
        //注意，并不是每次都拷贝所有，这句话和递归有关，要理解有一定的难度
        //因为这里并不是全部分完之后再合，而是分一点合一点
        for (int k = 0; k < tempIndex; k++) {
            a[k + left] = temp[k];
        }
    }

    public static void main(String[] args) {
        int[] a = {3, 1, 5, 8, 6, 7, 9, 0, 2, 4};
        MergeSort mergeSort = new MergeSort();
        mergeSort.sort(a);
        for (int i : a) {
            System.out.println(i);
        }
    }
}
