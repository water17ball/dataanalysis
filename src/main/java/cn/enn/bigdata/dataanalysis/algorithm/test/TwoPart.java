package cn.enn.bigdata.dataanalysis.algorithm.test;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *部门准备举办一场王者荣耀表演赛，有 10 名游戏爱好者参与，分 5 为两队，每队 5 人。
 * 每位参与者都有一个评分，代表着他的游戏水平。
 * 为了表演赛尽可能精彩，我们需要把 10 名参赛者分为实力尽量相近的两队。一队的实力可以表示为这一队 5 名队员的评分总和。
 * 现在给你 10 名参与者的游戏水平评分，请你根据上述要求分队最后输出这两组的实力差绝对值。
 * 例: 10 名参赛者的评分分别为 5 1 8 3 4 6 7 10 9 2，分组为 (1 3 5 8 10) (2 4 6 7 9)，两组实力差最小，差值为 1。有多种分法，但实力差的绝对值最小为 1。
 *
 * 解法：归纳为0-1背包问题
 *   每个对象的重量和价值，都为对象的评分值。
 */
public class TwoPart {

    public int getMinDifference(){
        List<Integer> list = getInputList();
        return minDifference(list);
    }

    private int minDifference(List<Integer> list) {
        int sum = list.stream().mapToInt(s -> s).sum();
        int half = sum >>> 1;
        int[][] array = new int[list.size()+1][half+1];
        for (int i = 1; i <= list.size(); i++) {
            for (int j = 1; j <= half; j++) {
                if(j < list.get(i-1)){
                    array[i][j] = Math.max(array[i-1][j], array[i][j-1]);
                }
                else {
                    array[i][j] = Math.max(array[i-1][j-list.get(i-1)] + list.get(i-1), array[i][j-1]);
                }
            }
        }
        int maxI = list.size();
        int maxJ = half;
        int minDifference = half;
        for (int j = 1; j <= maxJ; j++) {
            if(array[maxI][j] <= half){
                if(Math.abs(sum - 2*array[maxI][j]) < minDifference ){
                    minDifference = Math.abs(sum - 2*array[maxI][j]);
                    if(minDifference == 0){
                        return 0;
                    }
                }
            }
            else{
                break;
            }
        }

        return minDifference;
    }

    private List<Integer> getInputList() {
        Scanner scanner = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();
        String numList = scanner.nextLine();
        String[] split = numList.split(" ");
        for (int i = 0; i < split.length; i++) {
            list.add(Integer.valueOf(split[i]));
        }
        return list;
    }

    public static void main(String[] args) {
        TwoPart twoPart = new TwoPart();
        System.out.println(twoPart.getMinDifference());
    }


}
