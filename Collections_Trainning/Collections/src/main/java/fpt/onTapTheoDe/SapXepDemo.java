package fpt.onTapTheoDe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SapXepDemo {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        list.add(new Student(3, "Nam"));
        list.add(new Student(1, "Kiet"));
        list.add(new Student(2, "An"));

        Collections.sort(list); // nhờ compareTo() trong Student

        System.out.println("Danh sách sau khi sắp xếp theo ID:");
        for (Student s : list) {
            System.out.println(s);
        }
    }
}
