package test;

import model.LinkedList;
import model.Node;
import model.Student;

public class Test {
    public static void main(String[] args) {

        Student s1 = new Student("viet", 18, "IT");
        Student s2 = new Student("kang", 28, "MK");
        Student s3 = new Student("david", 20, "AI");
        Student s4 = new Student("Jonh", 23, "PS");
        Student s5 = new Student("Lyly", 19, "DEV");

        //Bai - 12
        /*
        Node<Student> node1 = new Node<>();
        node1.setData(s1);


        Node<Student> node2 = new Node<>();
        node2.setData(s2);

        Node<Student> node3 = new Node<>();
        node3.setData(s3);

        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(null);

        Node<Student> node = node1;
        while(true){
            System.out.println(node.getData());
            node = node.getNext();
            if(node == null) break;
        }*/


        //Bai - 13

        LinkedList<Student> ll = new LinkedList<>();

        ll.addFirst(s1);

        ll.addFirst(s2);

        ll.addFirst(s3);

        ll.addLast(s4);

        ll.add(s5, 4);

//        ll.delete(2);

        ll.edit(2, s5);

        ll.traverse();


    }
}
