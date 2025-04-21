package proton.traning;

import java.util.*;
import java.util.stream.Collectors;

public class AgeClassification {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    List<Person> personList = new ArrayList<>(List.of(
            new Person("Kiet", 19, "Male"),
            new Person("Lan", 17, "Female"),
            new Person("Minh", 65, "Male"),
            new Person("An", 33, "Male"),
            new Person("Hoa", 12, "Female"),
            new Person("Long", 45, "Male"),
            new Person("Mai", 70, "Female"),
            new Person("Phuc", 29, "Male"),
            new Person("Linh", 60, "Other"),
            new Person("Tuan", 15, "Male"),
            new Person("Vanh", 150, "Female"),
            new Person("Nuoc", -20, "Other")
    ));

    //1.1 Phân Loại Theo Nhóm Tuổi
        System.out.println("Phân loại theo nhóm tuổi:\n");
        System.out.println("+------------+-------+----------------------+");
        System.out.printf("| %-10s | %-5s | %-20s |\n", "Tên", "Tuổi", "Nhóm tuổi");
        System.out.println("+------------+-------+----------------------+");

        personList.forEach(person -> {
            String group = switch (getAgeGroup(person.getAge())) {
                case 1 -> "Trẻ em";
                case 2 -> "Người lớn";
                case 3 -> "Người cao tuổi";
                default -> "Không hợp lệ";
            };
            System.out.printf("| %-10s | %-5d | %-20s |\n", person.getName(), person.getAge(), group);
        });
        System.out.println("+------------+-------+----------------------+");


        // 1.2 Tính tuổi trung bình
        double avgAge = personList.stream()
                .filter(p -> p.getAge() > 0 && p.getAge() <=100)
                .mapToInt(Person::getAge)
                .average()
                .orElse(0);
        System.out.println("\nTuổi trung bình: " + avgAge);
        System.out.println("+------------+-------+----------------------+");


        //1.3 Người lớn tuổi nhất và người nhỏ tuổi nhất
        List<Person> validPersons = personList.stream()
                .filter(p -> p.getAge() > 0 && p.getAge() <=100)
                .toList();

        if (!validPersons.isEmpty()) {
            int maxAge = validPersons.stream().mapToInt(Person ::getAge).max().orElse(0);
            int minAge = validPersons.stream().mapToInt(Person ::getAge).min().orElse(0);
            System.out.println("\nNgười lớn tuổi nhất: "+maxAge);
            System.out.println("\nNgười nhỏ tuổi nhất: "+minAge);
        } else{
            System.out.println("Không có người nào có tuổi hợp lệ");
        }
        System.out.println("+------------+-------+----------------------+");


        // 1.4 Đếm theo giới tính (chỉ tính người có tuổi hợp lệ)
        Map<String, Long> genderCount = personList.stream()
                .filter(p -> p.getAge() > 0 && p.getAge() <= 120)
                .collect(Collectors.groupingBy(Person::getGender, Collectors.counting()));
        System.out.println("\n+------------ SỐ NGƯỜI THEO GIỚI TÍNH ------------+");
        genderCount.forEach((gender, count) -> System.out.printf("| %-8s: %d\n", gender, count));
        System.out.println("+------------------------------------------------+");


        // 1.5 Validate tuổi và thêm mới
        System.out.println("\n+----------- THÊM NGƯỜI MỚI (VALIDATE) -----------+");

        String name;
        int age;
        String gender;

        while (true) {
            System.out.print("Nhập tên: ");
            name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Tên không được để trống!");
                continue;
            }
            String finalName = name;
            boolean exists = personList.stream().anyMatch(p -> p.getName().equalsIgnoreCase(finalName));
            if (exists) {
                System.out.println("Tên đã tồn tại, vui lòng nhập lại!");
                continue;
            }

            while (true) {
                System.out.print("Nhập tuổi: ");
                String inputAge = scanner.nextLine().trim();
                try {
                    age = Integer.parseInt(inputAge);
                    if (age <= 0 || age > 120) {
                        System.out.println("Tuổi phải từ 1 đến 120. Vui lòng nhập lại!");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Tuổi không hợp lệ. Vui lòng nhập lại!");
                }
            }

            while (true) {
                System.out.print("Nhập giới tính (Male/Female/Other): ");
                gender = scanner.nextLine().trim();
                if (!gender.equalsIgnoreCase("Male") &&
                        !gender.equalsIgnoreCase("Female") &&
                        !gender.equalsIgnoreCase("Other")) {
                    System.out.println("Giới tính không hợp lệ. Vui lòng nhập lại!");
                } else {
                    break;
                }
            }

            addPerson(personList, new Person(name, age, gender));
            break;
        }
        System.out.println("+-----------------------------------------------------+");


        // 1.6 Sắp xếp theo tuổi
        System.out.println();
        System.out.println("+--------- DANH SÁCH SẮP XẾP THEO TUỔI ---------+");
        personList.stream()
                .filter(p -> p.getAge() > 0 && p.getAge() <= 120)
                .sorted(Comparator.comparingInt(Person::getAge))
                .forEach(p -> System.out.printf("| %-10s | %-3d | %-6s |\n", p.getName(), p.getAge(), p.getGender()));
        System.out.println("+-----------------------------------------------------+");

       // 1.7 Tìm người theo tên
        System.out.print("\nNhập tên để tìm: ");
        String nameToSearch = scanner.nextLine();
        if (nameToSearch.isEmpty()) {
            System.out.println("Bạn chưa nhập tên!");
        } else {
            System.out.println("Kết quả tìm thấy:");
            personList.stream()
                    .filter(p -> p.getName().equalsIgnoreCase(nameToSearch))
                    .forEach(System.out::println);
        }
        System.out.println("+-----------------------------------------------------+");

        // 1.8 In ra lời chào theo giới tính
        System.out.println("\n Lời chào:");
        personList.stream()
                .filter(p -> p.getAge() > 0 && p.getAge() <= 100)
                .forEach( p ->{
            String greeting = switch (p.getGender()){
              case "Male" -> "Hey boy!";
              case "Female" -> "Hey girl!";
                default -> "Hey man!";
            };
            System.out.println(p.getName() + ": " + greeting);
        });
        scanner.close();
    }

    public static int getAgeGroup(int age) {
        if (age < 0 || age > 100) return -1;
        if (age < 18) return 1;
        if (age <= 60) return 2;
        return 3;
    }

    // Hàm thêm Person có validate
   public static void addPerson(List<Person> list, Person p) {
        if (p.getAge() > 0 && p.getAge() <= 120) {
            list.add(p);
            System.out.printf("%-25s: %s %d %s\n", "Đã thêm", p.getName(), p.getAge(), p.getGender());
        } else {
            System.out.printf("%-25s: %s\n", "Tuổi ko hợp lệ, không thêm", p.getName());
        }
    }

    public static String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
    
}
