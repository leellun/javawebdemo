package com.newland.structure.map;

import java.util.HashMap;
import java.util.Map;

/**
 * HashMap特点：
 * 1 键不可重复，值可重复
 * 2 底层哈希表
 * 3 线程不安全
 * 4 允许key值为null，value也可以为null
 */
public class HashMapTest {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            map.put(String.valueOf(i) + "-key", i);
        }
    }
}
