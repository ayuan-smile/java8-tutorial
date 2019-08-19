package com.smile.collection;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author: ayuan
 * @create: 2019-03-13 10:46
 */
public class EfficiencyOfList {
    private static final int LIST_SIZE = 5000000;


    static enum ListType{
        ARRAY,LINKED
    }

    public static void insertList(List list){
        for (int i = 0; i < LIST_SIZE; i++) {
            list.add(i);
        }
    }

    public static void insertTest(List list, ListType array){
        long startTime = System.currentTimeMillis();
        insertList(list);
        long endTime = System.currentTimeMillis();
        System.out.println(array + " list insert time " + (endTime - startTime));
    }

    public static void delTest(List list, ListType array){
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < LIST_SIZE; i++) {
            list.remove(LIST_SIZE - 1 - i);
        }
        long endTime = System.currentTimeMillis();
        System.out.println(array + " list delete time " + (endTime - startTime));
    }

    public static void iteratorTest(List list, ListType array){
        long startTime = System.currentTimeMillis();
        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            //System.out.print(iterator.next());
            iterator.next();
        }
        long endTime = System.currentTimeMillis();
        System.out.println(array + " list iterator time " + (endTime - startTime));
    }

    public static void main(String[] args) {
        List<Integer> arrayList = Lists.newArrayList();
        LinkedList<Integer> linkedList = Lists.newLinkedList();
        // 测试写入速度
        insertTest(arrayList,ListType.ARRAY);
        insertTest(linkedList, ListType.LINKED);

        // 测试读取速度
        iteratorTest(arrayList,ListType.ARRAY);
        iteratorTest(linkedList,ListType.LINKED);

        // 测试删除速度
        delTest(arrayList,ListType.ARRAY);
        delTest(linkedList,ListType.LINKED);

        /**
         * 测试结果
         * ARRAY list insert time 2352
         LINKED list insert time 7568
         ARRAY list iterator time 7
         LINKED list iterator time 78
         ARRAY list delete time 20
         LINKED list delete time 105
         */
        /**
         * arrayList的存储是数组，而LinkedList的存储是链表形式。
         * 追加末尾要比追加到头部效率要高的多，追加到头部需要移动数据。
         * 正常追加到末尾，arrayList与LinkedList的效率与数据量相关，在数据量小于50万时，arrayList效率低，数据量超过50万，则arrayList效率低高
         * 遍历、删除的效率也都是arrayList效率高。
         * 数组的遍历本来就比链表要快，末尾删除不涉及数据移动 因此也比较快
         */

    }
}

