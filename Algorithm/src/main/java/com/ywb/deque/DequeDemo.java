package com.ywb.deque;

import java.util.*;

/**
 * @Author yaowenbin
 * @Date 2022/10/11
 */
public class DequeDemo {
    public static void main(String[] args) {
        Deque<Integer> queue = new LinkedList<>();
        queue.add(2); // addFirst
        queue.remove(); // removeLast

        queue.push(1); // addFirst
        queue.pop(); // removeFirst
        queue.peek(); // return FirstValue

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]) {
                    return o1[0] - o2[0];
                } else {
                    return o1[1] - o2[1];
                }
            }
        });

        // 输出数组
        int[] nums = new int[2];
        System.out.println(Arrays.toString(nums));

        List<Integer> list = new ArrayList<>();
        System.out.println(list.toString());

    }
}
