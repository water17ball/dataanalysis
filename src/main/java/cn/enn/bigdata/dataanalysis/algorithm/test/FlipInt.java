package cn.enn.bigdata.dataanalysis.algorithm.test;

/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 123
 * 输出: 321
 * 示例 2:
 * <p>
 * 输入: -123
 * 输出: -321
 * 示例 3:
 * <p>
 * 输入: 120
 * 输出: 21
 * 注意:
 * <p>
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31,  2^31 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 */
public class FlipInt {


    public int flip(int num) {
        if (Integer.MIN_VALUE == num) {
            return 0;
        }

        String numStr = String.valueOf(Math.abs(num));
        char[] charArray = numStr.toCharArray();
        for (int i = 0; i < numStr.length() / 2; i++) {
            char temp = charArray[i];
            charArray[i] = charArray[numStr.length() - i - 1];
            charArray[numStr.length() - i - 1] = temp;
        }
        numStr = String.valueOf(charArray);
        int numFliped = Integer.valueOf(numStr);
        if (num < 0) {
            return (0 - numFliped);
        } else {
            return numFliped;
        }
    }

    public int flipIntNum(int num) {


        int t = 0;
        while (num != 0) {
            if ((t * 10 / 10) != t) return 0;
            t = t * 10 + (num % 10);
            num = num / 10;
        }
        return t;
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0 || strs == null) return "";
        int minLength = Integer.MAX_VALUE;
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].length() < minLength) minLength = strs[i].length();
        }
        int longestPrefixNum = 0;
        boolean eq = true;
        for (int i = 0; i < minLength; i++) {
            for (int j = 1; j < strs.length; j++) {
                if (strs[0].charAt(i) != strs[j].charAt(i)) {
                    longestPrefixNum = i;
                    return strs[0].substring(0, longestPrefixNum + 1);
                }
            }
        }
        return strs[0].substring(0, minLength);
    }


    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        if (x % 10 == 0) return false;
        int t = 0;
        while (x != 0) {
            t = t * 10 + (x % 10);
            x = x / 10;
        }
        return (t == x);
    }

    public static void main(String[] args) {
        FlipInt flipInt = new FlipInt();
        int num = -120;
        System.out.println("num = " + num);
        System.out.println("flipInt = " + flipInt.flip(num));
        System.out.println("flipIntNum = " + flipInt.flipIntNum(num));
        System.out.println("(-3%10) = " + (-3 % 10));

    }
}
