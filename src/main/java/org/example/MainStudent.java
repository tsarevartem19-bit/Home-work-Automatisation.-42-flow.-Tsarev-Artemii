package org.example;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class MainStudent {
    public static void main(String[] args) {
        Set<Student> students = new HashSet<>();
        students.add(new Student("Иван", 5, 1, List.of(4, 5, 3)));
        students.add(new Student("Артем", 5, 1, List.of(2, 3, 4)));
        students.add(new Student("Вова", 5, 1, List.of(3, 4, 3)));
        students.add(new Student("Аркадий", 6, 3, List.of(2, 4, 3)));
        students.add(new Student("Николай", 5, 3, List.of(4, 5, 4)));
        students.add(new Student("Дмитрий", 6, 3, List.of(4, 4, 3)));
        students.add(new Student("Мария", 6, 3, List.of(5, 5, 5)));
        students.add(new Student("Карина", 5, 1, List.of(2, 2, 3)));
        students.add(new Student("Настя", 6, 3, List.of(4, 4, 4)));

        studentExpelOrTransfer(students);
        printStudents(students, 4);

    }

        public static void studentExpelOrTransfer(Set <Student> students) {
            students.removeIf(student -> student.averageMark() < 3);
            for (Student student : students) {
                if (student.averageMark() >= 3) {
                    student.course++;
                    System.out.println("Переведён студент: " + student.name + ", новый курс: " + student.course);
                }
            }
        }

    public static void printStudents(Set<Student> students, int course){
        for(Student student : students){
            if(student.course == course){
                System.out.println( "На  курсе: " + course +  " находятся студенты " + student.name);
            }
        }
    }
    }
