package com.example.DAG_text;

import java.util.*;

/**
 * Created by gg on 2018/6/1.
 */
public class Graphs {

    public static void addGraph(HashMap<Character, Node> graph, String keyword) {
        if (graph == null) {
            System.out.println("graph is null");
            return;
        }
        if (keyword.length() < 2) {
            System.out.println(keyword + " length < 2");
            return;
        }

        //图指针
        HashMap<Character, Node> point = graph;
        for (int i = 0; i < keyword.length(); i++) {
            //小写
            char c = Character.toLowerCase(keyword.charAt(i));

            //搜索当前指向的子图是否包含节点
            Node node = point.get(c);

            if (node == null) {
                node = new Node();
                node.setKey(c);

                point.put(c, node);
            }

            if (i == keyword.length() - 1) {
                node.setEnd(true);
            } else {
                if (node.getNext() == null) {
                    node.setNext(new HashMap<>());
                }
                point = node.getNext();
            }
        }
    }

    public static List<Keyword> find(HashMap<Character, Node> graph, String text) {
        if (graph == null || text == null || text.length() < 2) {
            return new LinkedList<>();
        }

        //排序子图集,方便排序
        Map<Integer, HashMap<Character, Node>> points = new TreeMap<>();
        List<Keyword> keywords = new LinkedList<>();

        for (int i = 0; i < text.length(); i++) {
            char c = Character.toLowerCase(text.charAt(i));

            //当前字符匹配子图
            Iterator<Map.Entry<Integer, HashMap<Character, Node>>> iterator = points.entrySet().iterator();
            while (iterator.hasNext()) {
                //文本开始,子图指针
                Map.Entry<Integer, HashMap<Character, Node>> entry = iterator.next();
                HashMap<Character, Node> point = entry.getValue();

                Node node = point.get(c);

                //子图不匹配当前路径,必须删除子图
                if (node == null) {
                    iterator.remove();
                    continue;
                } else {
                    //一条路径结尾
                    if (node.isEnd()) {
                        Keyword keyword = new Keyword();
                        keyword.setStart(entry.getKey());
                        keyword.setEnd(i);
                        keyword.setKey(text.substring(keyword.getStart(), keyword.getEnd() + 1));
                        keywords.add(keyword);
                    }

                    //路径彻底结束
                    if (node.getNext() == null) {
                        iterator.remove();
                        continue;
                    } else {
                        entry.setValue(node.getNext());
                    }
                }
            }

            //当前字符匹配全图,添加子图
            Node node = graph.get(c);
            if (node != null) {
                points.put(i, node.getNext());
            }
        }

        //TODO 贪婪匹配
        return keywords;
    }
}
