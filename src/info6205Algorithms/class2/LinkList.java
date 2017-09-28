package info6205Algorithms.class2;

class Node {
    public int data;
    public Node next;

    public Node(){}

    public Node(int data){
        this.data = data;
        this.next = null;
    }
}

public class LinkList {
    public Node head;

    public LinkList(){
        head = null;
    }

    public void addToHead(int data){
        Node add = new Node(data);
        add.next = head;
        head = add;
    }

    public void display(){
        if(isCycle()) {
            System.out.println("can't display since it is a cycle!");
            return;
        }
        Node temp = head;
        while (temp!=null){
            System.out.print(temp.data+",");
            temp = temp.next;
        }
        System.out.println("display over");
    }

    public void addToTail(int data){
        Node add = new Node(data);
        if(head==null){
            head = add;
            return;
        }
        Node temp = head;
        while (temp.next != null){
            temp = temp.next;
        }
        temp.next = add;
    }

    public void reverse(){
        if(head == null || head.next == null){
            return;
        }
        Node back = null;
        Node mid = head;
        Node front = head.next;
        while (front!=null){
            mid.next = back;
            back = mid;
            mid = front;
            front = front.next;
        }
        mid.next = back;
        head = mid;
    }

    public Node reverse(Node start){
        if(start == null || start.next == null)
            return start;
        Node cur = start;
        Node prev = null;

        while (cur != null){
            Node temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
        }
        start = prev;
        return start;
    }

    public int length(){
        Node temp = head;
        int count = 0;
        while (temp!=null){
            temp = temp.next;
            count++;
        }
        return count;
    }

    //assume no cycle
    public Node findTail(){
        if(head == null)
            return head;
        Node temp = head;
        while (temp.next != null){
            temp = temp.next;
        }
        return temp;
    }

    public Node findMid(){
        if(head == null || head.next == null){
            return head;
        }
        Node back = head;
        Node front = head;
        while (front != null){
            front = front.next;
            if(front!=null){
                front = front.next;
//                if(front!=null)
                    back = back.next;
            }
        }
        return back;
    }

    public Node findNthFromEnd(int n){
        if(head == null || n < 0){
            return null;
        }

        Node front = head;
        Node back = head;
        for(int i = 0; i<n; i++){
            if(front!=null)
                front = front.next;
            else
                return null;
        }
        while (front!=null){
            front = front.next;
            back = back.next;
        }
        return back;
    }

    public boolean isCycle(){
        if(head == null || head.next == null){
            return false;
        }
        Node back = head.next;
        Node front = head.next.next;
        while (front != back){
            if(front == null || front.next == null)
                return false;
            front = front.next.next;
            back = back.next;
        }
        return true;
    }

    public void createCycle(int n){
        Node tail = findTail();
        Node startOfCycle = findNthFromEnd(n);
        if(startOfCycle != null){
            tail.next = startOfCycle;
        }
    }

    /**front move by 2,back move by 1, the length of the cycle -> count, the length of head to the startOfCycle -> k**/
    /**when back meet front, the length of front to the startOfCycle must be k**/
    /**set back -> head, front and back move by 1, after k step, they will meet again at startOfCycle**/
    public Node findStartOfCycle(){
        if(head == null || head.next == null || head.next.next == null){
            return null;
        }
        Node front = head.next.next;
        Node back = head.next;
        while (front != back){
            front = front.next;
            if(front == null || front.next == null)
                return null;
            front = front.next;
            back = back.next;
        }
        back = head;
        while (back!=front){
            back = back.next;
            front = front.next;
        }
        return back;
    }

    public Node split(){
//        Node mid = findMid();
//        Node temp = mid.next;
//        mid.next = null;
        if(head == null || head.next == null){
            return null;
        }
        Node dummy = new Node();
        dummy.next = head;
        Node front = dummy;
        Node back = dummy;
        while(front != null){
            front = front.next;
            if(front!=null){
                front = front.next;
                back = back.next;
            }
        }
        Node temp = back.next;
        back.next = null;
        return temp;
    }

    public boolean isPalindrome(){
        if(isCycle())
            return false;
        Node second = split();
        second = reverse(second);
        Node temp1 = head;
        Node temp2 = second;
        boolean isPalindrom = true;
        while (temp2 != null){//&&temp1!=null
            if(temp1.data != temp2.data) {
                isPalindrom = false;
                break;
            }
            temp1 = temp1.next;
            temp2 = temp2.next;
        }
        second = reverse(second);

        Node tail = findTail();
        tail.next = second;
        return isPalindrom;
    }

    //node1 & node2 are sorted
    public Node sortMerge (Node node1, Node node2){
        if(node1 == null){
            return node2;
        }
        if(node2 == null){
            return node1;
        }
        Node result;
        if(node1.data < node2.data){
            result = node1;
            result.next = sortMerge(node1.next,node2);
        }else {
            result = node2;
            Node temp = sortMerge(node1,node2.next);
            result.next = temp;
        }
        return result;
    }

    public Node zipMerge(){
        Node second = split();
        second = reverse(second);
        Node first = head;
        return zipMerge(first,second,true);
    }

    private Node zipMerge(Node first, Node second, boolean bSwitch) {
        if(first == null){
            return second;
        }
        if(second == null){
            return first;
        }
        Node result;
        if (bSwitch){
            result = first;
            result.next = zipMerge(first.next,second,false);
        }else{
            result = second;
            result.next = zipMerge(first,second.next,true);
        }
        return result;
    }

    private int length(Node node){
        int count = 0;
        Node temp = node;
        while (temp!=null){
            count++;
            temp = temp.next;
        }
        return count;
    }

    public Node findIntersection(Node node1, Node node2){
        int length1 = length(node1);
        int length2 = length(node2);
        int diff = Math.abs(length1-length2);
        Node temp1 = node1;
        Node temp2 = node2;
        if(length1>length2){
            for(int i =0;i<diff;i++){
                temp1 = temp1.next;
            }
        }else{
            for(int i =0;i<diff;i++){
                temp2 = temp2.next;
            }
        }
        while (temp1!=null&&temp2!=null){
            if(temp1 == temp2)
                return temp1;
            else{
                temp1=temp1.next;
                temp2=temp2.next;
            }
        }
        return null;
    }

}
