package fpt.onTapTheoDe;

import java.util.Objects;

public class Student implements Comparable<Student> {
    private Integer id;
    private String name;

    public Student(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Student)) return false;
        Student student = (Student) obj;
        return Objects.equals(id, student.id) &&
                Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,name);
    }

    @Override
    public String toString() {
        return id + " - " + name;
    }

    @Override
    public int compareTo(Student other) {
        return this.id.compareTo(other.id); // sắp xếp theo id tăng dần
    }
}