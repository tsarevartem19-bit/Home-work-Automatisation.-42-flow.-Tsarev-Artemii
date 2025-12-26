package org.example;
import java.util.List;
public class Student {
    String name;
    int group;
    int course;
    List <Integer> marks;

    public Student(String name,int group,int course,List <Integer> marks){
        this.name = name;
        this.group = group;
        this.course = course;
        this.marks = marks;
    }

    public double averageMark(){
        int sum = 0;
        for(int mark : marks){
            sum+=mark;
        }
        return (double) sum/ marks.size();
    }
}

