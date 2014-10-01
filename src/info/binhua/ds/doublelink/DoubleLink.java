package info.binhua.ds.doublelink;
/*
 * 单链表中只有一个指针指向后继节点，
 * 从某个节点只能查找其后的节点，假如要查找某节点之前的节点又得从头结点开始！
 * 如果节点除了持有指向直接后继节点的指针外还有指向其直接前驱的指针，那么链表就可以双向流动，
 * 这就是双链表的思想。双链表与单链表的操作差不多，操作时记得节点有两个指针就OK了。
 */

// the node class
class Link<T>{
	public T data;
	public Link<T> pre;
	public Link<T> next;
	
	public Link(T data){
		this.data = data;
		pre = null;
		next = null;
	}
	
	public void displayLink(){
		System.out.print(data.toString()+" ");
	}
}

// the double link list
class LinkList<T>{
	public Link head;
	public Link rear;
	
	public LinkList(){
		head = null;
		rear = null;
	}
	
	//insert the newlink at start of the list
	public <T> void insertHead(T obj){
		Link<T> newLink = new Link<T>(obj);
		if(isEmpty()) rear = newLink; 
		else head.pre = newLink;
		newLink.next = head;
		head = newLink;
	}
	
	// insert the newlink at end of the list
	public <T> void insertRear(T obj){
		Link<T> newLink = new Link<T>(obj);
		if(isEmpty()) head = newLink;
		else{
			rear.next = newLink;
			newLink.pre = rear;
		}
		rear = newLink;
	}
	
    //insert the newObj at the front of the given obj
	public boolean insertBefore(T obj, T newObj){
		Link<T> newLink = new Link<T>(newObj);
		Link<T> curLink = head;
		// if the list is empty then insert the new obj by insertHead
		if(isEmpty()) insertHead(newObj); 
		else{
			while(!isEquals(curLink.data, obj)){
				curLink = curLink.next;
				if(curLink == null) return false;// can not find obj in the list
			}
			//the linklist has just equals the first node
			if(curLink == head){
				newLink.next = head;
				head.pre = newLink;
				head = newLink;
				return true;
			}
			newLink.pre = curLink.pre;
			curLink.pre.next = newLink;
			newLink.next = curLink;
			curLink.pre = newLink;
			return true;
		}
		return true;
	}
	
	 //insert the obj at the back of the given obj
    public <T> boolean insertAfter(T obj, T newObj){
    	Link<T> newLink = new Link<T>(newObj);
    	Link<T> curLink = head;
    	// if the list is empty then insert the new obj by insertHead
    	if(isEmpty()) insertHead(newObj);
    	else{
    		while(!isEquals(curLink.data, obj)){
    			curLink = curLink.next;
    			if(curLink == null) return false;// can not find obj in the list
    		}
    		//the linklist has just equals the first node
    		if(curLink == head){
    			curLink.next = newLink;
    			newLink.pre = curLink;
    			rear = newLink;
    			return true;
    		}
    		newLink.next = curLink.next;
    		curLink.next.pre = newLink;
    		newLink.pre = curLink;
    		curLink.next = newLink;
    		return true;
    	}
    	return false;
    }
    
    //delete the given node
    //true is success, false is failed
    public <T> boolean delNode(T obj){
    	Link<T> curLink = head;
    	while(!isEquals(curLink.data, obj)){
    		curLink = curLink.next;
    		if(curLink == null) return false; //not find the node
    	}
    	//the list just one node and equals the given node
    	if(curLink == head && curLink == rear){
    		head = rear = null;    	
    		return true;
    	}
    	// the node equals head
    	if(curLink == head){
    		head = curLink.next;
    		curLink.pre = null;
    		return true;
    	}
    	//the node equals rear
    	if(curLink == rear){
    		rear = curLink.pre;
    		curLink.pre.next = null;
    		return true;
    	}
    	curLink.pre.next = curLink.next;
    	return true;
    }
    
	//is empty?
	public boolean isEmpty(){
		return head == null;
	}
	
	//simple judgement if objs equals
    public <T> boolean isEquals(T obj1, T obj2){
        return obj1.equals(obj2) || obj1 == obj2;
    }
	
    //the length of the linklist
    public int listLength(){
        int n = 0;
        Link temp = head;
        while(temp!=null){
            n++;
            temp = temp.next;
        }
        return n;
    }
    
    //return the given node's index in the linklist
    //if not find return -1
    public <T> int find(T obj){
    	Link<T> temp = head;
    	int p = 0;
    	while(temp != null){
    		++p;
    		if(isEquals(temp.data, obj)) return p;
    		else temp = temp.next;
    	}
    	return -1;
    }
    
    // display the list from rear
    public void displayListInvOrder() {
		Link rearLink = rear;
        while( rearLink != null){
        	rearLink.displayLink();
        	rearLink = rearLink.pre;
        }
	}
    
    // display the list from head
    public void displayListOrder() {
		Link tempLink = head;
        while( tempLink != null){
            tempLink.displayLink();
            tempLink = tempLink.next;
        }
	}
}

//test
public class DoubleLink {
	public static void main(String[] args) {
		LinkList linkList = new LinkList();
        linkList.insertRear(2);
        linkList.insertHead(1);
        linkList.insertRear(4);
        linkList.insertRear(5);
        
        linkList.insertAfter(2, 3);
        linkList.insertBefore(5, 0);
        
        System.out.print("print list from head: ");
        linkList.displayListOrder();
        
        System.out.println();
        System.out.print("print list from rear: ");
        linkList.displayListInvOrder();
        
        System.out.println();
        System.out.print("delete 0 node: ");
        linkList.delNode(0);
        linkList.displayListOrder();
        
        System.out.println();        
        System.out.print("length of list: ");
        System.out.println(linkList.listLength());
        
        System.out.print("find 3's index: ");
        System.out.print(linkList.find(3));
        
        /***the Console print**************************************

			print list from head: 1 2 3 4 0 5 
			print list from rear: 5 0 4 3 2 1 
			delete 0 node: 1 2 3 4 5 
			length of list: 5
			find 3's index: 3
			
         */
	}
}
