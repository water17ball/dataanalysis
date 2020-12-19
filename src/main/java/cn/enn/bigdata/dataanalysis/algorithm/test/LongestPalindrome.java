package cn.enn.bigdata.dataanalysis.algorithm.test;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为1000。
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba"也是一个有效答案。
 * <p>
 * 动态规划（dp）是较好的一个解法。因为此题必须要遍历所有可能的情况，而且可以借助子串提高效率。
 */
public class LongestPalindrome {

    /**
     * 动态规划解法:
     * 先从长度为1的字符串开始填写，长度每加一个，都要看看短长度的是否回文
     * 状态转移方程：  dp[i,j]  ==  ( s[i]==s[j] && dp[i+1, j-1] )
     * <p>
     * 实验证明：simple耗时最短，而且空间复杂度为O(1)
     *
     * @param originStr
     * @return
     */
    public String bpSolution(String originStr) {
        String ans = originStr.substring(0, 1);

        //初始化dp数组  dp：动态规划
        int count = (1 + originStr.length() + 1) * (originStr.length() + 1) / 2;
        boolean[] dp = new boolean[count];
        for (int l = 0; l < originStr.length() - 1; l++) {
            for (int i = 0; (i + l) < originStr.length(); i++) {
                int j = i + l;
                if (0 == l) {
                    setDp(dp, i, j, true);
                } else {
                    boolean curValue = false;
                    if (1 == l) {
                        curValue = originStr.charAt(i) == originStr.charAt(j);
                    } else {
                        curValue = ((originStr.charAt(i) == originStr.charAt(j)) && (getDp(dp, i + 1, j - 1)));
                    }
                    setDp(dp, i, j, curValue);
                    if (curValue) {
                        if (ans.length() < l) {
                            ans = originStr.substring(i, j + 1);
                        }
                    }
                }

            }
        }

//        for (int i = 0; i < dp.length; i++) {
//            System.out.println(dp[i]);
//        }

        return ans;
    }

    public boolean getDp(boolean[] dp, int i, int j) {
        int index = 0;
//        for (int k = 1; k < i+1; k++) {
//            index += k;
//        }
        index += ((1 + i) * i / 2);
        index += j + 1;
//        System.out.println("get " + i + ", " + j);

        return dp[index];
    }

    public void setDp(boolean[] dp, int i, int j, boolean value) {
        int index = 0;
//        for (int k = 1; k < i+1; k++) {
//            index += k;
//        }
        index += ((1 + i) * i / 2);
        index += j + 1;

        dp[index] = value;
//        System.out.println("set " + i + ", " + j +"  :" + String.valueOf(value));
    }


    /**
     * 中心对称的完全遍历的方法
     * 说明：遍历每一个位置，从这个位置开始检查量测的字符串是否对称
     *
     * @param orginStr
     * @return
     */
    public String simple(String orginStr) {
        String ans = new String();

        for (int i = 0; i < orginStr.length() - 1; i++) {
            int length01 = checkPalindrome(orginStr, i, i);
            int length02 = checkPalindrome(orginStr, i, i + 1);
            if (ans.length() < (length01 * 2 + 1)) {
                ans = orginStr.substring(i - length01, i + length01 + 1);
            }
            if (ans.length() < length02 * 2 + 2) {
                ans = orginStr.substring(i - length02, i + 1 + length02 + 1);
            }
        }

        return ans;
    }

    private int checkPalindrome(String orginStr, int i, int j) {
        int firstIndex = i;
        int lastIndex = j;
        int lengthHalf = -1;
        for (; ; ) {
            if (orginStr.charAt(firstIndex) == orginStr.charAt(lastIndex)) {
                firstIndex--;
                lastIndex++;
                lengthHalf++;
                if ((firstIndex < 0) || (lastIndex >= orginStr.length())) {
                    break;
                }
            } else {
                break;
            }
        }
        return lengthHalf;
    }


    public String bpSolutionAll(String originStr) {
        String ans = originStr.substring(0, 1);

        //初始化dp数组  dp：动态规划

        boolean[][] dp = new boolean[originStr.length()][originStr.length()];
        for (int l = 0; l < originStr.length() - 1; l++) {
            for (int i = 0; (i + l) < originStr.length(); i++) {
                int j = i + l;
                if (0 == l) {
                    dp[i][j] = true;
                } else {
                    boolean curValue = false;
                    if (1 == l) {
                        curValue = originStr.charAt(i) == originStr.charAt(j);
                    } else {
                        curValue = ((originStr.charAt(i) == originStr.charAt(j)) && (dp[i + 1][j - 1]));
                    }
                    dp[i][j] = curValue;
                    if (curValue) {
                        if (ans.length() < l) {
                            ans = originStr.substring(i, j + 1);
                        }
                    }
                }

            }
        }


        return ans;
    }

    public static void main(String[] args) {
        int[] sequenceNum = {36, 13, 15, 26, 12, 28, 36, 35, 12, 6, 3, 8, 9, 19, 21, 33};
        String a = createHuiWen(sequenceNum);
//        String a = "babad";
//        String a = "dsfsdhadhfkdsdsfsdhadhdsfsdhadhfkddsfsdhadhfkdsahfksadhdsfsdhadhfkdsahfksadhfksddsfsdhadhfkdsahfksadhfksdhfusdihfksjadfhksadjkdsahfdsjkhfksdhffhiawoeuruihweiyrtiuoncsdbfzmbfkhfioaewncfhskdsfsdhadhfkdsahfksadhfksdhfusdihfksjadfhksadjkdsahfdsjkhfksdhffhiawoeuruihweiyrtiuoncsdbfzmbfkhfioaewncfhskhfusdihfksjadfhksadjkdsahfdsjkhfksdhffhiawoeuruihweiyrtiuoncsdbfzmbfkhfioaewncfhskdsfsdhadhfkdsahfksadhfksdhfusdihfksjadfhksadjkdsahfdsjkhfksdhffhiawoeuruihweiyrtiuoncsdbfzmbfkhfioaewncfhskdsfsdhadhfkdsahfksadhfksdhfusdihfksjadfhksadjkdsahfdsjkhfksdhffhiawoeuruihweiyrtiuoncsdbfzmbfkhfioaewncfhskdsfsdhadhf";
//        String a = "ehdsfsdh";
//        String a = "aghoaerngoinoi;hiuhasoudhfgioaehngio;ahergo;ihqaerioghqaeriogheiqrhgoearhgioqerhfgiehgheirghierhgiehighesijgoweahrgoiwqhogqhorighqoirhfioqwjheowj3ngoqewhgiwo4h5yg89135hgoerhg89p3ghp9erfgv9qhg8093h1f89qwvphfq 3r98per8fg384n8t7ybqeicrq9nqxn8rfgy7qdnb8cgrxb23t78sbnwetfg6rxqefgduywetgf82t3r78qtw78r63764r7rtrt7dyddygq83gt87ggdgggggggkjgxgb basgjcxhsjdhjshdjshdjwdfchwqeifghqioyfoeuieuireuirueiruierueiurieureirueiijdkndkncmdcmdncmdnfmdnfddjdjdjjjjjjjdjjjjjjjjjjjwssssssssse";
        long startTime = System.currentTimeMillis();
        System.out.println(startTime);
        LongestPalindrome longestPalindrome = new LongestPalindrome();
        System.out.println(longestPalindrome.simple(a));
        System.out.println(System.currentTimeMillis());
        System.out.println(longestPalindrome.bpSolutionAll(a));
        System.out.println(System.currentTimeMillis());
        System.out.println(longestPalindrome.bpSolution(a));
        System.out.println(System.currentTimeMillis());

        long lastTime = System.currentTimeMillis() - startTime;
        System.out.println("total time: " + lastTime);
    }

    /**
     * 创建回文字符串，以26个字母和10个数字为基础
     *
     * @param sequenceNum
     * @return
     */
    private static String createHuiWen(int[] sequenceNum) {
        String baseStr = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder resultStr = new StringBuilder();
        StringBuilder tempStr = new StringBuilder();
        int curIndex = 0;
        boolean reserved = true;
        for (int i = 0; i < sequenceNum.length; i++) {
            reserved = !reserved;

            int curNum = sequenceNum[i];
            while (curNum > 0) {
                if ((curIndex + curNum) > (baseStr.length() + 1)) {
                    if (reserved) {
                        tempStr.delete(0, tempStr.length());
                    } else {

                    }
                    resultStr.append(baseStr.substring(curIndex, baseStr.length()));

                    curNum = curNum - (baseStr.length() - curIndex);
                    curIndex = 0;
                } else {
                    resultStr.append(baseStr.substring(curIndex, curIndex + curNum));
                    curIndex = (curIndex + curNum) % baseStr.length();
                    curNum = 0;
                }
            }

        }

        return resultStr.toString();
    }
}
