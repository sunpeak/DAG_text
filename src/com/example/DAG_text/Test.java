package com.example.DAG_text;

import java.util.HashMap;

/**
 * Created by gg on 2018/6/1.
 */
public class Test {

    public static void main(String[] args) {
        HashMap<Character, Node> keywords = new HashMap<>();

        //初始化关键字图
        Graphs.addGraph(keywords, "微信");
        Graphs.addGraph(keywords, "微博");
        Graphs.addGraph(keywords, "微信公众号");
        Graphs.addGraph(keywords, "支付宝");
        Graphs.addGraph(keywords, "银联支付");

        //大文本查找
        Graphs.find(keywords, "JavaWEB后端支付银联,支付宝,微信对接\n" +
                "标签（空格分隔）： java\n" +
                "\n" +
                "项目概述\n" +
                "最近项目需要后端打通支付,所以对接部分做成了一个小模块. \n" +
                "先说下项目要求: \n" +
                "后端要对接银联无跳转Token支付,支付宝wap支付,微信APP支付,前端用的是H5和ionic.后端对下单请求只拼接参数或者拼接html给前端,前端提交下单,支付逻辑则再后端进行. \n" +
                "其中支付宝提供了SDK,银联也提供了SDK,微信的这个SDK在后端貌似没法用,自己写了个.\n" +
                "\n" +
                "银联支付流程,当然银联接口很多,流程时候设计稿相对应的")
                .forEach(keyword -> System.out.println(keyword));
    }
}
