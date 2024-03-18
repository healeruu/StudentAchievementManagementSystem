package myproject;

public class LinkedList {
    private Node head;//表示单项链表的第一个节点（头指针）
    private int size;//表示单项链表的长度

    public boolean add(String date){//添加数据
        Node node = new Node(date);//1、新建一个添加了数据date的链表（只有一个节点，他本身）/*这个也是临时链表*/
        if(head == null)//2、如果以head为第一个节点的链表为null
            head = node;//另它成为添加了数据date的链表
        else{
            Node t = head;//2、建立只有一个节点的 临时链表t
            while (t.getNext() != null){//遍历链表，找到head链表中的最后一个节点
                t=t.getNext();//3、让临时链表（节点）t 中的next值依次等于head链表下一个中的next，直到等于null，退出循环
            }
            t.setNext(node);//4、把第1步中 node的内容添加到head链表的最后一个节点
        }
        size++;//5、链表长度+1，因为是链表，长度不能任意设定
        return true;//6、返回true /*其实不管如何都会返回true*/
    }

    public LinkedList() {
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public int getSize() {
        return size;
    }


}
