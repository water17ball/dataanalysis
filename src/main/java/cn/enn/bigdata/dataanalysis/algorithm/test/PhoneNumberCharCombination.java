package cn.enn.bigdata.dataanalysis.algorithm.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 电话号码的字符串组合
 */
public class PhoneNumberCharCombination {

    //通过回溯+for循环的方法解决。 回溯通过backtrack的递归来实现
    public void backtrack(List<String> combinations, Map<String, String> phoneMap, String input, int index, StringBuilder str) {
        if (input.length() == index) {
            combinations.add(str.toString());
            return;
        }

        char c = input.charAt(index);
        String curChars = phoneMap.get(String.valueOf(c));
        for (int i = 0; i < curChars.length(); i++) {
            str.append(curChars.charAt(i));
            backtrack(combinations, phoneMap, input, index + 1, str);
            str.deleteCharAt(str.length() - 1);
        }
    }

    public static void main(String[] args) {
        Map<String, String> phoneMap = new HashMap<>();
        phoneMap.put("2", "abc");
        phoneMap.put("3", "def");
        phoneMap.put("4", "ghi");
        phoneMap.put("5", "jkl");
        phoneMap.put("6", "mno");
        phoneMap.put("7", "pqrs");
        phoneMap.put("8", "tuv");
        phoneMap.put("9", "wxyz");

        String input = "258654";
        List<String> results = new ArrayList<>();
        PhoneNumberCharCombination phoneNumberCharCombination = new PhoneNumberCharCombination();
        phoneNumberCharCombination.backtrack(results, phoneMap, input, 0, new StringBuilder());
        System.out.println(results);
        System.out.println(results.size());
    }
}
