package fpt.onTapTheoDe;

import java.util.HashMap;
import java.util.Map;

public class HashMapDemo {
    public static void main(String[] args) {
        Map<Integer, Student> map = new HashMap<>();

        map.put(1, new Student(1, "Kiet"));
        map.put(2, new Student(2, "An"));

        Student student = map.get(1);
        System.out.println("Sinh viên có ID = 1: " + student);
    }
}
