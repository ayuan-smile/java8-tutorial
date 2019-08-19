package com.smile.math;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 测试题
 * @author: ayuan
 * @create: 2019-04-09 10:05
 */
public class CaculateTest {
    public static void main(String[] args) {
        Integer[] arrays = {1,2,4,3,5};
        // 获取数组内第二大值
        int secondLargest = getSecondLargest(arrays);
        System.out.println(secondLargest);
        int[] secondLargest2 = getSecondLargest2(arrays);
        System.out.println(secondLargest2[0] + "," + secondLargest2[1]);
        // 获取数字相加得到target值的组合
        List<Integer[]> list = getTargetResult(arrays,5);
        list.forEach((s)->{
            System.out.println(s[0] + "," + s[1]);
        });
    }

    /**
     * 获取数组中第二大的值-排序获取
     * @param arrays
     * @return
     */
    public static int getSecondLargest(Integer[] arrays){
        if(arrays.length < 2){
            throw new RuntimeException("arrays length less than 2");
        }
        List<Integer> list = Arrays.asList(arrays)
            .stream()
            .sorted(Comparator.reverseOrder())
            .collect(Collectors.toList());
        return list.size()>1?list.get(1):list.get(0);
    }

    public static int[] getSecondLargest2(Integer[] arrays){
        if(arrays.length < 2){
            throw new RuntimeException("arrays length less than 2");
        }
        int first = arrays[0],second = first;
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i] > first) {
                second = first;
                first = arrays[i];
            }
        }
        return new int[]{first,second};
    }

    /**
     * 获取数组中两个数相加为target的组合
     * @param arrays
     * @param target
     * @return
     */
    public static List<Integer[]> getTargetResult(Integer[] arrays,int target){
        List<Integer[]> list = new ArrayList<>();
        for (int i = 0; i < arrays.length; i++) {
            for (int j = i+1; j < arrays.length; j++) {
                if ((arrays[i] + arrays[j]) == target){
                    list.add(new Integer[]{arrays[i],arrays[j]});
                }
            }
        }
        return list;
    }
}
