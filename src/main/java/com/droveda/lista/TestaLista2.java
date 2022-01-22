package com.droveda.lista;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class TestaLista2 {

    public static void main(String[] args) {

//        List<String> list = Collections.synchronizedList(new ArrayList<>());
        List<String> list = new Vector<>();

        for (int i = 0; i < 100; i++) {
            int finalI = i;
            new Thread(() -> {

                for (int j = 0; j < 10; j++) {
                    list.add("Thread: " + finalI + " - " + j);
                }

            }).start();

        }

        list.forEach(System.out::println);

    }

}
