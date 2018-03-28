package com.zettayun.util;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class ApiUtil {
    private static int thread_num = 1000;
    private static int client_num = 1000;

    static long max_time = 0;
    static long min_time = 100000;

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        // 50个线程可以同时访问
        final Semaphore semp = new Semaphore(thread_num);
        // 模拟2000个客户端访问
        for (int index = 0; index < client_num; index++) {
            final int NO = index;
            Runnable run = new Runnable() {
                public void run() {
                    try {
                        // 获取许可
                        semp.acquire();
//                      System.out.println("Thread:" + NO);
                        String host = "http://www.zettayun.com:8888/zettayunee/system/query/queryValueSet";
//                      String host = "http://123.57.151.87:8081/yuebo/income/getVideoIncomeData.action?";


                        String para = "{\"token\":\"b4433fdb6f244b9fb964222e9ea127fe\",\"setType\":1,\"startRow\":0,\"pageSize\":9,\"sortSet\":{\"value\":1}}";
                        URL url = new URL(host);// 此处填写供测试的url
                        HttpURLConnection connection = (HttpURLConnection) url
                                .openConnection();
                        connection.setRequestProperty("Content-Type", "application/json");
                        connection.setRequestProperty("Accept", "application/json");
                        connection.setRequestMethod("POST");
                        connection.setDoOutput(true);
                        connection.setDoInput(true);
//                        connection.addRequestProperty("oid", "8704d9df2b8e4a0bb96a3e60f4d5d907");
//                        connection.addRequestProperty("t", "4D2BE71B9D4A855BF56AD92696F72DD5");
//                        connection.addRequestProperty("uid", "1255");
                        //请求访问时间
                        Date time = new Date();
                        PrintWriter out = new PrintWriter(
                                connection.getOutputStream());
                        out.print(para);
                        out.flush();
                        out.close();
                        BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                        connection.getInputStream()));
                        String line = "";
                        String result = "";
                        while ((line = in.readLine()) != null) {
                            result += line;
                        }
                            //System.out.println(result);
                        long i =  new Date().getTime() - time.getTime() ;
                        TimeStatistics(i);
                        // 释放
                        //System.out.println("第：" + NO + " 个, 用时："+i);
                        semp.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if(NO==client_num-1){
                        System.out.println(thread_num+"线程"+client_num+"遍"+"最大时长："+max_time+", 最小时长："+min_time);
                    }
                }
            };
            exec.execute(run);
        }
        // 退出线程池
        exec.shutdown();
    }

    private static void TimeStatistics(long time){
        if(time>max_time){
            max_time=time;
        }
        if(time<min_time){
            min_time=time;
        }
    }


    public static boolean importData(String host, String data){
        boolean flag = false;
        try {
            URL url = new URL(host);// 此处填写供测试的url
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
//                        connection.addRequestProperty("oid", "8704d9df2b8e4a0bb96a3e60f4d5d907");
//                        connection.addRequestProperty("t", "4D2BE71B9D4A855BF56AD92696F72DD5");
//                        connection.addRequestProperty("uid", "1255");
            //请求访问时间
            Date time = new Date();
            PrintWriter out = new PrintWriter(
                    connection.getOutputStream());
            out.print(data);
            out.flush();
            out.close();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            connection.getInputStream()));
            String line = "";
            StringBuilder result = new StringBuilder();
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            System.out.println(result);
            long i =  new Date().getTime() - time.getTime() ;
            System.out.println("访问时间：" + i);
            flag = true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return flag;
    }

    public static String importDataSet(String host, String data){
        String token = null;
        try {
            URL url = new URL(host);// 此处填写供测试的url
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
//                        connection.addRequestProperty("oid", "8704d9df2b8e4a0bb96a3e60f4d5d907");
//                        connection.addRequestProperty("t", "4D2BE71B9D4A855BF56AD92696F72DD5");
//                        connection.addRequestProperty("uid", "1255");
            //请求访问时间
            Date time = new Date();
            PrintWriter out = new PrintWriter(
                    connection.getOutputStream());
            out.print(data);
            out.flush();
            out.close();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            connection.getInputStream()));
            String line = "";
            StringBuilder result = new StringBuilder();
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            System.out.println(result);
            JSONObject res = JSONObject.parseObject(result.toString());
            JSONObject tokenJson = res.getJSONObject("result");
            token = tokenJson.getString("token");
            long i =  new Date().getTime() - time.getTime() ;
            System.out.println("访问时间：" + i);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return token;
    }
}