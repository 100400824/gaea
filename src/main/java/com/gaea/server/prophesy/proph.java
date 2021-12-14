package com.gaea.server.prophesy;

import com.gaea.utls.Mysql.ConnectMySql;

import java.util.*;


public class proph {

    public static void main(String[] args) {
        calcNum();
    }

    public static void calcNum() {

        String sql1 = "select * from ball";

        List<Map<String, Object>> list = ConnectMySql.getCases(sql1);

        Map<String, Integer> fenbuMap = new HashMap<>();

        String targetFenbu = "0411";

        boolean isFenbu = false;

        //获取目标分布的下一次分布
        for (Map<String, Object> map : list) {

            String fenbu = String.valueOf(map.get("fenbu"));

            if (isFenbu) {
                int value = fenbuMap.getOrDefault(fenbu, 0);
                fenbuMap.put(fenbu, value + 1);
                isFenbu = false;
            }

            if (targetFenbu.equals(fenbu)) {
                isFenbu = true;
            }
        }

        //获取下一次分布组合出现最低和最高概率出现的分布
        List<Integer> fenbuList = new ArrayList<>();
        for (String key : fenbuMap.keySet()) {
            fenbuList.add(fenbuMap.get(key));
        }
        Collections.sort(fenbuList);

        List<String> fenbuMinList = new ArrayList<>();
        List<String> fenbuMaxList = new ArrayList<>();
        for (String key : fenbuMap.keySet()) {
            if (Objects.equals(fenbuMap.get(key), fenbuList.get(0))) {
                fenbuMinList.add(key);
            }
            if (Objects.equals(fenbuMap.get(key), fenbuList.get(fenbuList.size() - 1))) {
                fenbuMaxList.add(key);
            }
        }

        //计算符合要求的组合在全部历史中出现的最高和最低概率
        Map<String, Integer> fenbuMinMap = new HashMap<>();
        Map<String, Integer> fenbuMaxMap = new HashMap<>();

        for (String listStr : fenbuMinList) {
            String sql2 = "SELECT COUNT(fenbu) AS counts from ball GROUP BY fenbu HAVING fenbu = '" + listStr + "'";
            List<Map<String, Object>> fenbuMapList = ConnectMySql.getCases(sql2);
            fenbuMinMap.put(listStr, Integer.parseInt(fenbuMapList.get(0).get("counts").toString()));
        }

        for (String listStr : fenbuMaxList) {
            String sql2 = "SELECT COUNT(fenbu) AS counts from ball GROUP BY fenbu HAVING fenbu = '" + listStr + "'";
            List<Map<String, Object>> fenbuMapList = ConnectMySql.getCases(sql2);
            fenbuMaxMap.put(listStr, Integer.parseInt(fenbuMapList.get(0).get("counts").toString()));
        }

        fenbuMinList.clear();
        fenbuMaxList.clear();
        fenbuMinList = getMMList(fenbuMinMap, "min");
        fenbuMaxList = getMMList(fenbuMaxMap, "max");

       /* System.out.println(fenbuMinList);
        System.out.println(fenbuMaxList);*/

        //获取该组合中各数字出现过的概率
        getNumCount(fenbuMinList, "min");
        getNumCount(fenbuMaxList, "max");

    }

    //获取list中各种分布情况下的数字概率
    public static void getNumCount(List<String> fenbuList, String type) {

        Map<String, Integer> map = new HashMap<>();

        for (String fenbuStr : fenbuList) {

            for (int num = 1; num <= 6; num++) {

                String sKey = "red" + num;

                String sql = "SELECT ball." + sKey + ",COUNT(*) as count FROM ball WHERE ball.fenbu = 'fenbuStr' GROUP BY ball." + sKey;

                sql = sql.replace("fenbuStr", fenbuStr);

                List<Map<String, Object>> fenbuMapList = ConnectMySql.getCases(sql);

                for (Map<String, Object> map1 : fenbuMapList) {
                    String mapKey = map1.get(sKey).toString();
                    int map1Num = Integer.parseInt(map1.get("count").toString());
                    int numCount = map.getOrDefault(mapKey, 0);
                    map.put(mapKey, map1Num + numCount);
                }
            }
            sortMap(map, type, fenbuStr);
        }


    }

    //map按照value排序
    public static void sortMap(Map<String, Integer> map, String type, String fenbu) {

        List<Integer> list = new ArrayList<>();

        for (String key : map.keySet()) {

            list.add(map.get(key));
        }

        Collections.sort(list);

        List<String> listSort1 = new ArrayList<>();
        List<String> listSort2 = new ArrayList<>();
        List<String> listSort3 = new ArrayList<>();
        List<String> listSort4 = new ArrayList<>();

        for (Integer i : list) {

            for (String key : map.keySet()) {

                if (Objects.equals(i, map.get(key))) {
                    map.remove(key);
                    if (1 <= Integer.parseInt(key) && Integer.parseInt(key) <= 10) {
                        listSort1.add(key + ":" + i);
                    } else if (11 <= Integer.parseInt(key) && Integer.parseInt(key) <= 20) {
                        listSort2.add(key + ":" + i);
                    } else if (21 <= Integer.parseInt(key) && Integer.parseInt(key) <= 30) {
                        listSort3.add(key + ":" + i);
                    } else {
                        listSort4.add(key + ":" + i);
                    }
                    break;
                }
            }
        }

        System.out.println(fenbu + ":" + type + ":");
        System.out.println(listSort1);
        System.out.println(listSort2);
        System.out.println(listSort3);
        System.out.println(listSort4);

        lucky(fenbu, type, listSort1, listSort2, listSort3, listSort4);
    }

    //根据分布和概率筛选出最终的结果
    public static void lucky(String fenbu, String type,
                             List<String> listSort1,
                             List<String> listSort2,
                             List<String> listSort3,
                             List<String> listSort4) {

        for (int index = 0; index < 4; index++) {

            int fenbuChar = Integer.parseInt(fenbu.substring(index, 1 + index));

            if (index == 0) {
                if ("min".equals(type)) {
                    for (int i = 0; i < fenbuChar; i++) {
                        System.out.print(listSort1.get(i) + " ");
                    }
                } else {
                    for (int i = listSort1.size(); i > listSort1.size() - fenbuChar; i--) {
                        System.out.print(listSort1.get(i - 1) + " ");
                    }
                }
            } else if (index == 1) {
                if ("min".equals(type)) {
                    for (int i = 0; i < fenbuChar; i++) {
                        System.out.print(listSort2.get(i) + " ");
                    }
                } else {
                    for (int i = listSort2.size(); i > listSort2.size() - fenbuChar; i--) {
                        System.out.print(listSort2.get(i - 1) + " ");
                    }
                }

            } else if (index == 2) {
                if ("min".equals(type)) {
                    for (int i = 0; i < fenbuChar; i++) {
                        System.out.print(listSort3.get(i) + " ");
                    }
                } else {
                    for (int i = listSort3.size(); i > listSort3.size() - fenbuChar; i--) {
                        System.out.print(listSort3.get(i - 1) + " ");
                    }
                }

            } else {
                if ("min".equals(type)) {
                    for (int i = 0; i < fenbuChar; i++) {
                        System.out.print(listSort4.get(i) + " ");
                    }
                } else {
                    for (int i = listSort4.size(); i > listSort4.size() - fenbuChar; i--) {
                        System.out.print(listSort4.get(i - 1) + " ");
                    }
                }
            }
        }

        System.out.println();
    }

    //获取map中value最高或最低的key(list)
    public static List<String> getMMList(Map<String, Integer> map, String type) {

        List<String> resultList = new ArrayList<>();

        if (map.size() <= 1) {
            resultList.addAll(map.keySet());
            return resultList;
        }

        List<Integer> list = new ArrayList<>();

        for (String key : map.keySet()) {
            list.add(map.get(key));
        }
        Collections.sort(list);

        for (String key : map.keySet()) {

            if (Objects.equals(map.get(key), list.get(0)) && "min".equals(type)) {

                resultList.add(key);
            }

            if (Objects.equals(map.get(key), list.get(list.size() - 1)) && "max".equals(type)) {

                resultList.add(key);
            }
        }
        return resultList;
    }


}
