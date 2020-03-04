package com.example.demo;

import com.alibaba.fastjson.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
        List<Integer> num = new ArrayList<>();
        for (int i = 0; i < 55; i++) {
            num.add(i);
        }
        int size = num.size();
        int i = size / 200;
        int j = size % 200;
        int z;
        for (z = 0; z < i; z++) {
            System.out.println(num.subList(z * 10, (z + 1) * 10));
            System.out.println();
        }
        if (j > 0) {
            System.out.println(num.subList(z * 10, size));
        }
    }

    @Test
    void jsonToObject(){
        String str = "[{\"rule_id\":\"1003616999916420096\",\"field_val\":\"price\",\"operate_name\":\"管理员\",\"rule_type\":2,\"param_val\":\"0\",\"warning_title\":\"严重警告\",\"warning_color\":\"ff0000\",\"operate_id\":\"1\",\"warning_info\":\"采购单价大于之前的单价，请联系供应商！\",\"field_name\":\"单价\",\"param_type\":2,\"operate_time\":1582517896000},{\"rule_id\":\"1003616999916420022\",\"field_val\":\"stock_num\",\"operate_name\":\"管理员\",\"rule_type\":1,\"param_val\":\"50\",\"warning_title\":\"一般警告\",\"warning_color\":\"e4ca41\",\"operate_id\":\"1\",\"warning_info\":\"库存数量充足，无需采购\",\"field_name\":\"库存数量\",\"param_type\":1,\"operate_time\":1582516014000}]";
        List<Map> parse = (List<Map>) JSONArray.parse(str);
        parse.stream().forEach(map -> System.out.println(map));
    }

}
