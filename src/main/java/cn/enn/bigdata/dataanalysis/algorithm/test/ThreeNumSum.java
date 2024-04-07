package cn.enn.bigdata.dataanalysis.algorithm.test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeNumSum {
    List<List<Integer>> getAllThreeNum(Integer[] nums, Integer target){
        List<List<Integer>> result = new ArrayList<>();

        //1.先排序
        Arrays.sort(nums);

        //2.再找结果
        for (int first = 0; first < nums.length-3; first++) {
            if((first> 0) && (nums[first] == nums[first -1])){
                continue;
            }

            for (int second = first +1; second < nums.length-2; second++) {
                if(nums[second] == nums[second -1]){
                    continue;
                }
                for (int third = nums.length-1; third> second;third--) {
                    if(nums[first]+nums[second+nums[third]] == target){
                        List<Integer> list = new ArrayList<>(4);
                        list.add(nums[first]);
                        list.add(nums[second]);
                        list.add(nums[third]);
                        result.add(list);
                    }

                    //三数之和小于目标，则换下一个second继续
                    if(nums[first]+nums[second+nums[third]] < target){
                        break;
                    }

                }
            }
        }


        return result;
    }


    public static void main(String[] args) {

    }
}
