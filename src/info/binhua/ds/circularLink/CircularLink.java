package info.binhua.ds.circularLink;

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
      Link<T> curLink = front;
      
      newLink.next = front;
      front = newLink;
      //just one node then next itself
      if(curLink == null) newLink.next = front;
      else{
		  while(curLink.next != newLink.next){//find the end
    		  curLink = curLink.next;
    	  }
    	  curLink.next = front;// link the start
      }
  }
   
  //insert the newlink at back of the list
  public <T> void insertRear(T obj){
      Link<T> newLink = new Link<T>(obj);
      //if the linklist is empty, newlink is the front
      if(isEmpty())front = newLink;
      else{//else find the place(the rear) to insert
          Link<T> temp = front;
          while(temp.next != front) temp = temp.next;
          temp.next = newLink;
      }
      newLink.next = front;
  }
   
  //insert the newObj at the front of the given obj
  public <T> boolean insertBefore(T obj, T newObj){
      Link<T> newLink = new Link<T>(newObj);
      Link<T> curLink = front;
      Link<T> preCurLink = front;
      //if the linklist is empty or just equal first node
      if(isEmpty()) return false; // not find
      if(front.data == obj)//the linklist has just equals the first node
    	  insertHead(newObj);
      else{
    	  //list have one node but not equals obj
    	  if(front.next == front) return false;
          //find the gived obj curLink.next != front && 
          while (!isEquals(curLink.data, obj)){
              preCurLink = curLink;
              curLink = curLink.next;
              if(curLink == front) return false; // can not find obj in the list
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
      if(isEmpty()) return false;
      else{
          while(!isEquals(curLink.data, obj)){
              curLink = curLink.next;
              if(curLink == front) return false; // can not find obj in the list
          }
          newLink.next = curLink.next;
          curLink.next = newLink;
          return true;
      }
  }
   
  //delete the given node
  //true is success, false is failed
  public <T> boolean delNode(T obj){
      Link<T> preCurLink = null;
      Link<T> curLink = front;
      if(isEmpty()) return false; //empty list then return
      //equals the first node then delete the first node
      if(isEquals(front.data, obj)){ 
    	  if(curLink.next == front) { //delete the first node
    		  front = null;
    		  return true;
    	  }
    	  while(curLink.next != front) {
    		  curLink = curLink.next;
    	  }
    	  front = front.next;
    	  curLink.next = front;
          return true;
      }
      while(!isEquals(curLink.data, obj)){
          preCurLink = curLink;
          curLink = curLink.next;
          if(curLink == front) return false; //not find
      }
      if(curLink.next == front) {//delete the end node 
    	  preCurLink.next = front;
    	  return false;
      }
      preCurLink.next = curLink.next; //delete the middle node
      return true;
  }
  
  //the length of the linklist
  public int listLength(){
      int n = 0;
      Link temp = front;
      if(temp.next == front) return 1;
      while(temp.next != front){
          ++n;
          temp = temp.next;
      }
      return n+1;
  }
  
  //find the link of the given index p
  // assume the p's index has the link and the front is the first node
  public <T> Link<T> findLink(LinkList list, int p){
	  Link<T> tempLink = list.front;
	  int flag = 1;
	  while(flag<p){
		  tempLink = tempLink.next;
		  flag++;
	  }
	  return tempLink;
  }
   
  //is empty
  public boolean isEmpty(){
      return front==null;
  }
   
  //simple judgement if objs equals
  public <T> boolean isEquals(T obj1, T obj2){
      return obj1.equals(obj2) || obj1 == obj2;
  }
  
  //display the list
  public <T> void displayList(LinkList list){
	  Link tempLink = list.front;
	  if(tempLink == null) {
		  System.out.println("its null!");
		  return;
	  }
	  if(tempLink == tempLink.next){//just one node
		  tempLink.displayLink();
	  }else{
		  do {
			  tempLink.displayLink();
			  tempLink = tempLink.next;
			} while (tempLink.next != list.front);//one circle
		  tempLink.displayLink();
	  }
  }
}

public class CircularLink {
	
	//p: the start position
	//k: the kill number
	//return luchy man
	public static <T> Link<T> josephus(LinkList list, int p, int k){
		int flag = 1;
		Link<T> tempLink = list.findLink(list, p); 	//get the first position
		while(tempLink.next != tempLink){ 			//just one can live
			while(flag<k){ 							// count from p until k
				tempLink = tempLink.next;
				flag++;
			}
			list.delNode(tempLink.data); 			// kill the kth man
			tempLink = tempLink.next; 				// restart from kth next man 
			flag = 1;
		}
		return tempLink;
	}
	
	//test
	public static void main(String[] args) {
		LinkList linkList = new LinkList();
		linkList.insertRear("赵一");
		linkList.insertRear("郑二");
		linkList.insertRear("张三");
		linkList.insertRear("李四");
		linkList.insertRear("王五");
		linkList.insertRear("周六");
		linkList.insertRear("吴七");
		linkList.insertRear("钱八");
		
		System.out.print("all men: ");
		linkList.displayList(linkList);
		
		System.out.println();
		System.out.print("lucky man: ");
		josephus(linkList, 2, 5).displayLink();
		
		/***the Console print**************************************

		 	all men: 赵一 郑二 张三 李四 王五 周六 吴七 钱八 
			lucky man: 李四 
			
		 */
	}
}
