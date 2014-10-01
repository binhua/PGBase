package info.binhua.ds.singlelink;
//link node
class Link<T>{
    public T data;
    public Link<T> next;
 
    public Link(T obj){
        this.data = obj;
        this.next = null;
    }
     
    public void displayLink(){
        System.out.print(data.toString()+" ");
    }
}
 
//the Link list
class LinkList{
    public Link front;
    public LinkList(){
        front = null;
    }
     
    //insert the newlink at start of the list
    public <T> void insertHead(T obj){
        Link<T> newLink = new Link<T>(obj);
        newLink.next =  front;
        front = newLink;
    }
     
    //insert the newlink at back of the list
    public <T> void insertRear(T obj){
        Link<T> newLink = new Link<T>(obj);
        //if the linklist is empty, newlink is the front
        if(isEmpty()) front = newLink;
        else{//else find the place(the rear) to insert
            Link<T> temp = front;
            while(temp.next != null) temp = temp.next;
            temp.next = newLink;
        }
    }
     
    //insert the newObj at the front of the given obj
    public <T> boolean insertBefore(T obj, T newObj){
        Link<T> newLink = new Link<T>(newObj);
        Link<T> curLink = front;
        Link<T> preCurLink = front;
        //if the linklist is empty
        if(isEmpty()) front = newLink;
        else{
            //find the gived obj
            while (!isEquals(curLink.data, obj)){
                preCurLink = curLink;
                curLink = curLink.next;
                if(curLink == null) return false; // can not find obj in the list
            }
            //the linklist has just equals the first node
            if(curLink == front){
                newLink.next = front;
                front = newLink;
                return true;
            }
            newLink.next = curLink;
            preCurLink.next = newLink;
            return true;
        }
		return true;
    }
     
    //insert the obj at the back of the given obj
    public <T> boolean insertAfter(T obj, T newObj){
        Link<T> newLink = new Link<T>(newObj);
        Link<T> curLink = front;
        if(isEmpty()) front = newLink;
        else{
            while(!isEquals(curLink.data, obj)){
                curLink = curLink.next;
                if(curLink == null) return false; // can not find obj in the list
            }
            newLink.next = curLink.next;
            curLink.next = newLink;
            return true;
        }
        return true;
    }
     
    //delete the given node
    //true is success, false is failed
    public <T> boolean delNode(T obj){
        Link<T> preCurLink = null;
        Link<T> curLink = front;
        if(isEmpty()) return false; //empty list then return
        if(isEquals(front.data, obj)){ //equals the first node then delete the first node
            front = front.next;
            return true;
        }
        while(curLink != null && !isEquals(curLink.data, obj)){
            preCurLink = curLink;
            curLink = curLink.next;
        }
        if(curLink == null) return false;
        preCurLink.next = curLink.next;
        return true;
    }
    
    //revrse the linklist
    //return newlinklist head node
    public <T> Link<T> revLinkList(Link<T> head){
    	Link<T> preLink = null;
    	Link<T> curLink = head;
    	Link<T> curLinkNext = curLink.next;
    	
    	while(curLinkNext != null){
    		curLink.next = preLink;
    		preLink = curLink;
    		curLink = curLinkNext;
    		curLinkNext = curLinkNext.next;
    	}
    	curLink.next = preLink;
    	return curLink;
    }
    
    //return the given node's index in the linklist
    //if not find return -1
    public <T> int find(T obj){
    	Link<T> temp = front;
    	int p = 0;
    	while(temp != null){
    		++p;
    		if(isEquals(temp.data, obj)) return p;
    		else temp = temp.next;
    	}
    	return -1;
    }
     
    //the length of the linklist
    public int listLength(){
        int n = 0;
        Link temp = front;
        while(temp!=null){
            n++;
            temp = temp.next;
        }
        return n;
    }
     
    //is empty
    public boolean isEmpty(){
        return front==null;
    }
     
    //simple judgement if objs equals
    public <T> boolean isEquals(T obj1, T obj2){
        return obj1.equals(obj2) || obj1 == obj2;
    }
}

// the polynome node
class PolyLink{
	public int c; // the coefficient
	public int i; // the index
	PolyLink next;
	
	public PolyLink(int c, int i) {
		this.c = c;
		this.i = i;
		this.next = null;
	}
}

//polynome list
class PolyLinkList{
	public PolyLink front;
	
	public PolyLinkList(){
		front = null;
	}
	
	// just insert ordered polynome 
	public void insert(PolyLink polyLink){
		polyLink.next = front;
		front = polyLink;
	}
	
	//print the polylinklist
	public void printPList(PolyLink curTempLink){
		StringBuilder c = new StringBuilder("");
		while(curTempLink != null){
			if(curTempLink == front)
				c.append(curTempLink.c+"X^"+curTempLink.i);
			else 
				c.append("+"+curTempLink.c+"X^"+curTempLink.i);
			curTempLink = curTempLink.next;
		}
		System.out.println(c.toString());
	}
	
	//two polynome add
	public PolyLink add(PolyLinkList result, PolyLink p1ListHead, PolyLink p2ListHead){
		while(p1ListHead != null && p2ListHead != null){
			if(p1ListHead.i == p2ListHead.i){
				int c = p1ListHead.c + p2ListHead.c;
				result.insert(new PolyLink(c, p1ListHead.i));
				p1ListHead = p1ListHead.next;
				p2ListHead = p2ListHead.next;
			}else if(p1ListHead.i < p2ListHead.i){
				result.insert(new PolyLink(p1ListHead.c, p1ListHead.i));
				p1ListHead = p1ListHead.next;
			}else{
				result.insert(new PolyLink(p2ListHead.c, p2ListHead.i));
				p2ListHead = p2ListHead.next;
			}
		}
		while(p1ListHead != null){
			result.insert(new PolyLink(p1ListHead.c, p1ListHead.i));
			p1ListHead = p1ListHead.next;
		}
		while(p2ListHead != null){
			result.insert(new PolyLink(p2ListHead.c, p2ListHead.i));
			p2ListHead = p2ListHead.next;
		}
		return result.front;
	}
	
	// two polynome multiplication
	public PolyLink multi(PolyLinkList result, PolyLink p1ListHead, PolyLink p2ListHead){
		PolyLink temp2 = p2ListHead;
		while(p1ListHead != null){
			while(temp2 != null){
				int c = p1ListHead.c * temp2.c;
				int i = p1ListHead.i + temp2.i;
				result.insert(new PolyLink(c, i));
				temp2 = temp2.next;
			}
			p1ListHead = p1ListHead.next;
			temp2 = p2ListHead;
		}
		PolyLink theFrontLink = result.front;
		
//		System.out.println("bufore merger: ");
//		printPList(theFrontLink);
		
		//merger the same index
		while (theFrontLink != null){
			PolyLink preCurLink = theFrontLink;
			PolyLink curLink = preCurLink.next;
			while(curLink != null){
				if(curLink.i == theFrontLink.i){//same index
					theFrontLink.c += curLink.c;
					preCurLink.next = curLink.next;
				}else{
					preCurLink = curLink;
				}
				curLink = curLink.next;
			}
			theFrontLink = theFrontLink.next;
		}
		return result.front;
	}
}

//test
public class SingleLink {
  public static void main(String[] args){
      LinkList linkList = new LinkList();
      linkList.insertRear(2);
      linkList.insertHead(1);
      linkList.insertAfter(2, 3);
      linkList.insertRear(4);
      linkList.insertRear(5);
       
      linkList.insertAfter(2, 100);
      linkList.delNode(5);
       
      System.out.println("the length is: "+ linkList.listLength());
      System.out.println("find 4's index: "+linkList.find(4));
      
      System.out.print("before reverse: ");
      Link tempLink = linkList.front;
      while( tempLink != null){
          tempLink.displayLink();
          tempLink = tempLink.next;
      }
      System.out.println();
      System.out.print("after reverse: ");
      Link tempLink2 = linkList.revLinkList(linkList.front);
      while( tempLink2 != null){
          tempLink2.displayLink();
          tempLink2 = tempLink2.next;
      }
      
      System.out.println();
      System.out.print("p1: ");
      PolyLinkList pll = new PolyLinkList();
      pll.insert(new PolyLink(34, 6));
      pll.insert(new PolyLink(43, 3));
      pll.insert(new PolyLink(2, 2));
      pll.insert(new PolyLink(1, 1));
      pll.printPList(pll.front);
      
      System.out.print("p2: ");
      PolyLinkList pll2 = new PolyLinkList();
      pll2.insert(new PolyLink(33, 7));
      pll2.insert(new PolyLink(4, 6));
      pll2.insert(new PolyLink(2, 4));
      pll2.insert(new PolyLink(12, 3));
      pll2.insert(new PolyLink(22, 1));
      pll2.printPList(pll2.front);
      
      PolyLinkList pll3 = new PolyLinkList();
      System.out.print("p1+p2: ");
      pll3.printPList(pll3.add(pll3, pll.front, pll2.front));
      System.out.print("p1Xp2: ");
      pll3.printPList(pll3.multi(pll3, pll.front, pll2.front));
      
      /***the Console print**************************************

       	the length is: 5
		find 4's index: 5
		before reverse: 1 2 100 3 4 
		after reverse: 4 3 100 2 1 
		p1: 1X^1+2X^2+43X^3+34X^6
		p2: 22X^1+12X^3+2X^4+4X^6+33X^7
		p1+p2: 33X^7+38X^6+2X^4+55X^3+2X^2+23X^1
		p1Xp2: 1122X^13+136X^12+1487X^10+646X^9+871X^7+558X^6+960X^4+41X^8+26X^5+99X^3+24X^2+23X^1
       
       */
  }
}
 