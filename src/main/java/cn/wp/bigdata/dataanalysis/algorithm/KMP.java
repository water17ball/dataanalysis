package cn.wp.bigdata.dataanalysis.algorithm;

/**
 * KMP算法
 */
public class KMP {

    /**
     * 返回子串的位置
     *
     * @param src
     * @param target
     * @param jumpArray
     * @return
     */
    public int findSubString(String src, String target, int[] jumpArray) {
        for (int i = 0; i < src.length(); i++) {// i永远不后退
            for (int j = 0; j < target.length(); j++) {//j 按照jumpArray表的指示，在失败时退回到指定位置
                if (j == -1) {//当开头第一个不匹配时，src前进一个
                    j++;
                    i++;
                }

                if (src.charAt(i) == target.charAt(j)) {
                    if (j == (target.length() - 1)) {
                        return i;
                    }

                    i++;// i永远不后退
                    if (i >= src.length()) {
                        break;
                    }

                } else {
                    j = jumpArray[j];
                    j--;//为了和j++抵消
                }


            }
        }

        return -1;
    }

    //获取目标字符串的跳跃表
    public static int[] getTargetStringJumpArray(String targetString) {
        int[] jumpArray = new int[targetString.length()];
        for (int i = 0; i < targetString.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (targetString.charAt(i) == targetString.charAt(j)) {
                    if (0 == j) {
                        jumpArray[i] = 1;
                    } else {
                        jumpArray[i] = jumpArray[i - 1] + 1;
                    }
                    i++;
                    if (i >= targetString.length()) {
                        break;
                    }
                } else {
                    jumpArray[i] = 0;
                    if (0 == j) {
                    }//如果是比较第一个，则正常返回
                    else {//否则，i倒回去自身，再从从j=0开始比较
                        i--;
                    }
                    break;
                }
            }
        }

        int[] actualArray = new int[targetString.length()];
        actualArray[0] = -1;//第一个元素不匹配时的特殊处理
        System.arraycopy(jumpArray, 0, actualArray, 1, actualArray.length - 1);
        System.out.println(jumpArray);
        for (int i = 0; i < actualArray.length; i++) {
            System.out.println(actualArray[i]);
        }

        return actualArray;
    }

    public void findStr(String src, String target) {
        int[] jumpArray = getTargetStringJumpArray(target);
        int findIndex = findSubString(src, target, jumpArray);
        if (findIndex < 0) {
            System.out.println("not found!");
        } else {
            System.out.println("found string:");
            System.out.println(src);
            for (int i = 0; i < (findIndex + 1 - target.length()); i++) {
                System.out.printf(" ");
            }
            System.out.println(target);
        }
    }

    public static void main(String[] args) {
        String targetString = "abaabcac";
        String src = "aababaabaaabaababaabcabaabcaabaabcac";

//        String targetString = "aaa";
//        String src = "aabaacaaabbbacacaaa";
        KMP.getTargetStringJumpArray(targetString);

        KMP kmp = new KMP();
        kmp.findStr(src, targetString);
    }
}
