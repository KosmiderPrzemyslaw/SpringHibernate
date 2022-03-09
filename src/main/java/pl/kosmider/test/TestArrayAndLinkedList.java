package pl.kosmider.test;

import java.util.ArrayList;
import java.util.List;

public class TestArrayAndLinkedList {
    public static void main(String[] args) {
        List<String> stringList  = new ArrayList<>();



        for (int i = 0; i < 1000_000_000; i++) {
            stringList.add("a");
        }


        System.out.println(stringList);

    }
}
