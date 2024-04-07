package cn.enn.bigdata.dataanalysis.algorithm.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneNumber {
    public List<String> getAllPossibleStr(String digits){
        List<String> result = new ArrayList<>();
        StringBuilder stringBuilder  = new StringBuilder();
        Map<String, String> phoneNumMap = new HashMap<>();
        phoneNumMap.put("2", "abc");
        phoneNumMap.put("3", "def");
        phoneNumMap.put("4", "ghi");
        phoneNumMap.put("5", "jkl");
        phoneNumMap.put("6", "mno");
        phoneNumMap.put("7", "pqrs");
        phoneNumMap.put("8", "tuv");
        phoneNumMap.put("9", "wxyz");

        getBackTrace(digits, 0, stringBuilder,phoneNumMap,  result);
        return result;
    }

    private void getBackTrace(String digits, int index, StringBuilder lastStringBuilder, Map<String, String> phoneNumMap, List<String> result) {

        char c = digits.charAt(index);
        String possibleChars = phoneNumMap.get(String.valueOf(c));
        for (int i = 0; i < possibleChars.length(); i++) {
            lastStringBuilder.append(possibleChars.charAt(i));
            if(index+1 == digits.length()){
                result.add(lastStringBuilder.toString());
            }
            else{
                getBackTrace(digits, index+1, lastStringBuilder, phoneNumMap, result);
            }
            lastStringBuilder.deleteCharAt(lastStringBuilder.length()-1);
        }
    }

    private void getBackTraceSin(String digits, int index, StringBuilder lastStringBuilder, Map<String, String> phoneNumMap, List<String> result) {
        if(index == digits.length()){
            result.add(lastStringBuilder.toString());
            return;
        }

        char c = digits.charAt(index);
        String possibleChars = phoneNumMap.get(String.valueOf(c));
        for (int i = 0; i < possibleChars.length(); i++) {
            lastStringBuilder.append(possibleChars.charAt(i));
            {
                getBackTraceSin(digits, index+1, lastStringBuilder, phoneNumMap, result);
            }
            lastStringBuilder.deleteCharAt(lastStringBuilder.length()-1);
        }
    }


    public static void main(String[] args) {

        PhoneNumber phoneNumber = new PhoneNumber();
        List<String> allPossibleStr = phoneNumber.getAllPossibleStr("23456");
        System.out.println(allPossibleStr);
    }

}
