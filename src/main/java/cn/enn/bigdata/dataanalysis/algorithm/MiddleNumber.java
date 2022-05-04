package cn.enn.bigdata.dataanalysis.algorithm;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Arrays;

/**
 * 两个有序数组，找到中位数。时间复杂度要求logN （需要二分法）
 *
 * LeetCode 4. 寻找两个正序数组的中位数
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 *
 *  思路：
 * 1）满足O(log (m+n))时间复杂度，需要使用2分法进行查找。
 * 2）去除不确定性：让len(m) < len(n)
 *       其他不确定性：
 *        1中位数是一个数字，还是两个数的平均？  计算len(m+n)是奇偶，奇数则是一个，偶数则为两数的平均。
 * 3）分割思路：
 *         left_part          |        right_part
 *   A[0], A[1], ..., A[i-1]  |  A[i], A[i+1], ..., A[m-1]
 *   B[0], B[1], ..., B[j-1]  |  B[j], B[j+1], ..., B[n-1]
 * 普通情况：（m分割成2部分，n分割成2部分，每个数组左侧部分都比中位数小，每个数组右边部分都比中位数大）
 * 公式：
 * 1) len(left_part) == len(right_part)
 * 2) max(left_part) <= min(right_part)
 * 为了达成这两个条件，我们只需要确保：
 * (1) i + j == m - i + n - j (或者: m - i + n - j + 1) 即让左半边元素数量等于与右半边
 *     对于 n >= m 的情况，我们只需要让 : i = 0 ~ m, j = (m + n + 1)/2 - i
 * (2) B[j-1] <= A[i] 并且 A[i-1] <= B[j]  即让左边最大元素小于右边最小元素
 * 所以，我们需要做的就是：
 * 在 [0, m] 中找到一个使下面不等式成立的 i :
 *     B[j-1] <= A[i] and A[i-1] <= B[j], ( where j = (m + n + 1)/2 - i )
 *
 * 找到符合条件的 i 之后，我们想要的中位数就是：
 * max(A[i-1], B[j-1]) ( m + n 是奇数)
 * 或者 (max(A[i-1], B[j-1]) + min(A[i], B[j]))/2 ( m + n 是偶数)
 * 现在让我们考虑边缘值i = 0，i = m。
 * （1） i=0时候， i太大，m数组都比目前的n的左侧要大，那么m都属于中位数的右侧。中位数应该在n中寻找。
 *        因为mid = (m+n +1)/2 ,  position = n[mid]
 *   (2) i=m时候，i太小，m数组都比目前的n的左侧要小，那么m属于中位数的左侧，中位数应该在n的右侧寻找。
 *       position = n[mid-m]
 */
public class MiddleNumber {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int left = 0, right = m;
        // median1：前一部分的最大值
        // median2：后一部分的最小值
        int median1 = 0, median2 = 0;

        while (left <= right) {
            // 前一部分包含 nums1[0 .. i-1] 和 nums2[0 .. j-1]
            // 后一部分包含 nums1[i .. m-1] 和 nums2[j .. n-1]
            int i = (left + right) / 2;
            int j = (m + n + 1) / 2 - i;

            // nums_im1, nums_i, nums_jm1, nums_j 分别表示 nums1[i-1], nums1[i], nums2[j-1], nums2[j]
            int nums_im1 = (i == 0 ? Integer.MIN_VALUE : nums1[i - 1]);
            int nums_i = (i == m ? Integer.MAX_VALUE : nums1[i]);
            int nums_jm1 = (j == 0 ? Integer.MIN_VALUE : nums2[j - 1]);
            int nums_j = (j == n ? Integer.MAX_VALUE : nums2[j]);

            if (nums_im1 <= nums_j) {
                median1 = Math.max(nums_im1, nums_jm1);
                median2 = Math.min(nums_i, nums_j);
                left = i + 1;
            } else {
                right = i - 1;
            }
        }

        return (m + n) % 2 == 0 ? (median1 + median2) / 2.0 : median1;
    }



    public float getMiddle(int[] a, int[] b){
        float middle = 0;

        //1.使得数组a个数m，小于数组b个数n
        if (a.length > b.length){
            int[] temp = null;
            temp = a;
            a = b;
            b = temp;
        }

        //2.
        int m = a.length;
        int n = b.length;

        //3. i是数组a的指针位置， j是数组b的指针位置，left和right是i的左右边界
        //    i = 0 ~ m, j = (m + n + 1)/2 - i
        int left = 0;
        int right = m;
        int i = 0;//init
        int j = 0;//init

        //4.在 [0, m] 中找到一个使下面不等式成立的 i :
        // *     B[j-1] <= A[i] and A[i-1] <= B[j], ( where j = (m + n + 1)/2 - i )
        while (left <= right){
            i = (left + right) / 2;
            j = (m+n+1) / 2 - i;
            if ((i == 0) || (i == m)){
                break;
            }
            int al = a[i-1];
            int bl = b[j-1];
            int ar = a[i];
            int br = b[j];

            int leftMax = al > bl ? al : bl;
            int rightMin = ar > br ? br : ar;
            if (leftMax <= rightMin){
                left  = i + 1;
            }
            else {
                right = i - 1;
            }
        }

        /**
         * 1） i=0时候， i太大，m数组都比目前的n的左侧要大，那么m都属于中位数的右侧。中位数应该在n中寻找。
         *  *        因为mid = (m+n +1)/2 ,  position = n[mid]
         */
        boolean hasTwoMiddle = (((m + n) % 2) == 0);
        int mid = (m+n +1)/2;
        if (i == 0){
            if (hasTwoMiddle){
                int mid1 = b[mid-1];
                int mid2 = b[mid];
                return  (mid1+mid2)/2.0f;
            }else {
                return b[mid];
            }
        }
        /**
         * (2) i=m时候，i太小，m数组都比目前的n的左侧要小，那么m属于中位数的左侧，中位数应该在n的右侧寻找。
         *  *       position = n[mid-m]
         */
        else if (i == m){
            if (hasTwoMiddle){
                int mid1 = b[mid-m-1];
                int mid2 = b[mid-m];
                return  (mid1+mid2)/2.0f;
            }else {
                return b[mid-m];
            }
        }
        /**
         * i在 0~m之间，直接计算
         */
        else {
            return a[i] <= b[j] ? a[i] : b[j];
        }

//        return middle;
    }

    public static void main(String[] args) {
//        int [] n1 = {1,3,5,7};
//        int [] n2 = {2,4,7,9,10};
        int [] n1 = {1,2};
        int [] n2 = {3,4,5,6,7,8,9,10,11,12};

        MiddleNumber middleNumber = new MiddleNumber();
        System.out.println(middleNumber.findMedianSortedArrays(n1, n2));
//        System.out.println(middleNumber.getMiddle(n1, n2));

    }

}
