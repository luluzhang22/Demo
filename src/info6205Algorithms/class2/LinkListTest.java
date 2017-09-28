package info6205Algorithms.class2;

public class LinkListTest {
    public static void main(String[] args){
        LinkList list1 = new LinkList();
        list1.addToHead(1);
        list1.addToHead(2);
        list1.addToHead(3);

//        list1.addToTail(4);
//        list1.addToTail(5);
//        list1.addToTail(6);
//        list1.addToTail(7);
//        list1.addToTail(8);
//        list1.addToTail(9);

        list1.display();
        list1.reverse();
        list1.display();
        System.out.println("length->"+list1.length());
        System.out.println("mid->"+list1.findMid().data);
        System.out.println("isCycle->"+list1.isCycle());

        list1.createCycle(2);
        System.out.println("isCycle->"+list1.isCycle());
        if(list1.isCycle()) {
            System.out.println("start of cycle->" + list1.findStartOfCycle().data);
        }

        LinkList list2 = new LinkList();
        list2.addToTail(4);
        list2.addToTail(5);
        list2.addToTail(6);
        list2.display();
        System.out.println("isPalindrome->"+list2.isPalindrome());

        list1.findStartOfCycle().next =null;
        list1.head = list2.sortMerge(list1.head,list2.head);
        list1.display();
        list2.display();


        list1.zipMerge();
        list1.display();


        LinkList list3 = new LinkList();
        list3.addToTail(10);
        list3.addToTail(11);
        list1.findTail().next = list3.head;
//        list2.findTail().next = list3.head;
        list1.display();
        list3.display();
        System.out.println("Intersection->"+list1.findIntersection(list1.head,list3.head).data);


    }
}
