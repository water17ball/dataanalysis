package cn.enn.bigdata.dataanalysis.algorithm.test;

import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class LongNorepeatString {
    public void slideWindow(String a) {
        int longCount = 0;

        int i = 0;
        int j = 0;

        Set windowSet = new HashSet();

        for (i = 0; i < a.length(); i++) {

            while ((j < a.length()) && (!windowSet.contains(a.charAt(j)))) {
                windowSet.add(a.charAt(j));
                if (longCount < windowSet.size()) {
                    longCount = windowSet.size();
                }
                j++;
            }

            windowSet.remove(a.charAt(i));

        }
        System.out.println(longCount);
    }

    public static void main(String[] args) {

        String a = "dfghjmaseffghjklwaerfwrtgtey";
        LongNorepeatString longNorepeatString = new LongNorepeatString();
        longNorepeatString.slideWindow(a);
    }
}
