package fpt.onTapTheoDe;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SoSanhDemo {
    public static void main(String[] args) {
        List<Student> arrayList = new ArrayList<>();
        List<Student> linkedList = new LinkedList<>();

        for (int i = 0; i < 100_000; i++) {
            Student s = new Student(i, "Name" + i);
            arrayList.add(s);
            linkedList.add(s);
        }

        long start1 = System.nanoTime();
        arrayList.add(0, new Student(99999, "Array đầu"));
        long end1 = System.nanoTime();
        System.out.println("ArrayList thêm đầu mất: " + (end1 - start1) + " ns");

        long start2 = System.nanoTime();
        linkedList.add(0, new Student(99999, "Linked đầu"));
        long end2 = System.nanoTime();
        System.out.println("LinkedList thêm đầu mất: " + (end2 - start2) + " ns");
    }
}
