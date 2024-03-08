package uk.ac.soton.comp1206;

import java.util.Arrays;
import java.util.Comparator;

public class SortingExample {
    public static class Student implements Comparable<Student>{
        String firstName;
        String surName;
        int studentID;

        public Student(String first, String second, int id){
            firstName =first;
            surName=second;
            studentID=id;
        }
        public String toString(){
            return firstName +" "+ surName+" : "+studentID;
        }

        @Override
        public int compareTo(Student o) {
            return this.surName.compareTo(o.surName);
        }
    }

    static Student[] makeExample(){
        Student[] s = new Student[5];
        s[0] = new Student("Mikel", "Arteta",12);
        s[1] = new Student("Anne","Jones",11);
        s[2] = new Student("Ben","White",4);
        s[3] = new Student("Alice","FooBar",1);
        s[4] = new Student("Bukayo","Saka",7);
        return s;
    }
    public static class CompareByFirstName implements Comparator<Student>{
        @Override
        public int compare(Student o1, Student o2) {
            return o1.firstName.compareTo(o2.firstName);
        }
    }
    public class CompareByIDNumber implements Comparator<Student>{
        @Override
        public int compare(Student o1, Student o2) {
            return o1.studentID-o2.studentID;
        }
    }

    public static void main(String[] args) {
        var students = makeExample();
        for(Student s:students)
            System.out.println(s);
        Arrays.sort(students);
        for(Student s:students)
            System.out.println(s);
        Arrays.sort(students, new CompareByFirstName());
        System.out.println("***First name ******");
        for(Student s:students)
            System.out.println(s);
        Arrays.sort(students,(Student a,Student b)-> a.surName.compareTo(b.surName));
        System.out.println("***Sur name ******");
        for(Student s:students)
            System.out.println(s);
        Arrays.sort(students,SortingExample::myLovelyFunction);
        System.out.println("***Mark ******");
        for(Student s:students)
            System.out.println(s);
    }
    public static int myLovelyFunction(Student o1, Student o2) {
        return o1.studentID-o2.studentID;
    }

}
