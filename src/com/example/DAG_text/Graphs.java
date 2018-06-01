package com.example.DAG_text;

import java.util.*;

/**
 * Created by gg on 2018/6/1.
 */
public class Graphs {

    public static void addGraph(ArrayList<Word> graph, String keyword) {
        if (graph == null) {
            System.out.println("graph is null");
            return;
        }
        if (keyword.length() < 2) {
            System.out.println(keyword + " length < 2");
            return;
        }

        CharSequence charSequence = keyword.subSequence(0, keyword.length());
        List<Word> point = graph;
        for (int i = 0; i < charSequence.length(); i++) {
            //生成初始节点
            Word textWord;
            if (i == charSequence.length() - 1) {
                textWord = new Word(charSequence.charAt(i), true);
            } else {
                textWord = new Word(charSequence.charAt(i));
            }

            //搜索当前指向的子图是否包含节点
            int index = point.indexOf(textWord);
            Word graphWord;
            //当前子图合并节点
            if (index == -1) {
                point.add(textWord);
                graphWord = textWord;
            } else {
                graphWord = point.get(index);
                //设置结束节点
                if (textWord.isEnd()) {
                    graphWord.setEnd(true);
                }
            }

            //图延伸,指向下级子图
            if (i < charSequence.length() - 1) {
                if (graphWord.getNext() == null) {
                    graphWord.setNext(new ArrayList<>());
                }
                point = graphWord.getNext();
            }
        }
    }

    public static List<Keyword> find(ArrayList<Word> graph, String text) {
        if (graph == null || text.length() < 2) {
            return null;
        }

        //排序子图集,方便排序
        Map<Integer, List<Word>> subGraph = new TreeMap<>();
        List<Keyword> keywords = new LinkedList<>();

        CharSequence charSequence = text.subSequence(0, text.length());
        for (int i = 0; i < charSequence.length(); i++) {
            //不区分大小写
            Word textWord = new Word(Character.toLowerCase(charSequence.charAt(i)));

            //当前字符匹配子图
            Iterator<Map.Entry<Integer, List<Word>>> iterator = subGraph.entrySet().iterator();
            while (iterator.hasNext()) {
                //文本开始,子图指针
                Map.Entry<Integer, List<Word>> entry = iterator.next();
                List<Word> point = entry.getValue();
                int index = point.indexOf(textWord);

                //子图不匹配当前路径,必须删除子图
                if (index == -1) {
                    iterator.remove();
                    continue;
                }

                Word graphWord = point.get(index);

                //一条路径结尾
                if (graphWord.isEnd()) {
                    Keyword keyword = new Keyword();
                    keyword.setStart(entry.getKey());
                    keyword.setEnd(i);
                    keyword.setKey(text.substring(keyword.getStart(), keyword.getEnd() + 1));

                    keywords.add(keyword);
                }

                //路径彻底结束
                if (graphWord.getNext() == null) {
                    iterator.remove();
                } else {
                    entry.setValue(graphWord.getNext());
                }
            }

            //当前字符匹配全图,添加子图
            int index = graph.indexOf(textWord);
            if (index > -1) {
                Word graphWord = graph.get(index);
                subGraph.put(i, graphWord.getNext());
            }
        }

        //TODO 贪婪匹配
        return keywords;
    }
}
