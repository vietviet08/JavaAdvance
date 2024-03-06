package model;

public class LinkedList<T> {
    private Node<T> head;

    public LinkedList() {
        this.head = null;
    }

    public void traverse() {
        Node<T> node = head;
        while (node != null) {
            System.out.println(node.getData());
            node = node.getNext();
        }
    }

    public void addFirst(T item) {
        Node<T> node = new Node<>();
        node.setData(item);
        node.setNext(head);
        this.head = node;
    }

    public void addLast(T item) {
        if (head == null) addFirst(item);
        else {
            Node<T> node = new Node<>(item, null);
            Node<T> lastNode = head;
            while (lastNode.getNext() != null) {
                lastNode = lastNode.getNext();
            }
            lastNode.setNext(node);
        }
    }

    public void add(T item, int index) {
        if (head == null)
            addFirst(item);
        else {
            Node<T> newNode = new Node<>();
            newNode.setData(item);
            Node<T> node = head;
            int i = 1;

            while (node.getNext() != null) {
                if (i == index - 1) {
                    Node<T> nodeNext = node.getNext();
                    node.setNext(newNode);
                    newNode.setNext(nodeNext);
                    return;
                }
                ;
                node = node.getNext();
                i++;
            }

            if (index > i) addLast(item);
            if (index <= 1) addFirst(item);
        }
    }

    public void delete(int index) {
        if (head == null || index <= 0) return;
        Node<T> node = head;

        if (index == 1) {
            head = node.getNext();
            return;
        }

        int i = 1;
        while (node.getNext() != null) {
            if (i == index - 1) {
                Node<T> nodeNext = node.getNext();
                node.setNext(nodeNext.getNext());
                return;
            }
            node = node.getNext();
            i++;
        }
    }

    public void deleteAll() {
        head = null;
    }

    public void edit(int index, T item) {
        if (head == null || index <= 0) return;
        if (index == 1) {
            head.setData(item);
            return;
        }
        Node<T> node = head;
        int i = 1;
        while (node.getNext() != null) {
            if (i == index - 1) {
                node = node.getNext();
                node.setData(item);
                return;
            }
            node = node.getNext();
            i++;
        }
    }

    public boolean isEmtity(){
        return head == null;
    }

    public int size(){
        if(head == null) return 0;
        int i = 0;
        Node<T> node = head;
        while(node.getNext() != null){
            i++;
            node = node.getNext();
        }
        return i;
    }
}
