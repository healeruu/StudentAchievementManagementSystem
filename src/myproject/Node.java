package myproject;


public class Node {
    private String date;//假定只存这个内容
    private Node next;//存储下一个节点

    public Node(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}