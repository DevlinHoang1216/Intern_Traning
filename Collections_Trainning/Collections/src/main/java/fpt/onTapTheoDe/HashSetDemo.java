package fpt.onTapTheoDe;

import java.util.HashSet;
import java.util.Set;

public class HashSetDemo {
    public static void main(String[] args) {
        Set<Student> set = new HashSet<>();

        Student s1 = new Student(1, "Kiet");
        Student s2 = new Student(2, "An");
        Student s3 = new Student(2, "An"); // Trùng với s2

        set.add(s1);
        set.add(s2);
        set.add(s3); // Không được thêm vì trùng s2 (theo equals và hashCode)

        System.out.println("Danh sách sinh viên (không trùng)");
        for (Student s : set) {
            System.out.println(s + " | hashCode: " + s.hashCode());
        }

        System.out.println("Tổng số phần tử trong set: " + set.size());
    }
}
