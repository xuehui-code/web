package lesson2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SingleThread {
    public static void main(String[] args){
        //耗时多的任务
        calculate(new ArrayList<>());
        calculate(new ArrayList<>());
        //阻塞任务
        Scanner sc = new Scanner(System.in);
        print(sc);
        print(sc);
    }

    private static void calculate(ArrayList<Object> objects) {
    }


    private static int calculate(List<Integer> list) {
        return 0;
    }

    public static void print(Scanner sc){
        System.out.println(sc.nextLine());
    }
}
