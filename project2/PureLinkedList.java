/**
* Class implements a Linked List
*
*@author Zach 'TG' Thoroughgood
*/

//Generics required to implement a linkedlist
public class PureLinkedList<Top>{
	
/**
*@param private Top value
* This is the head of the linkedlist
*
*@param private PureLinkedList<Top> tail
*This is the tail of the PureLinkedList
*/
	//the value at the head of this list
	private Top value;
	
	//the tail of this list 
	private PureLinkedList<Top> tail;
	
	//One-parameter constructor to create a Linked List
	
	public PureLinkedList(Top value){
		this.value = value;
		tail = null;
	}
	
/**
*@param add() method
*@param takes Top value
*@return void
*The add method adds a value to the linkedlist
*/
	// Add method increasing the LinkedList by 1
	public void add(Top value){
		if(this.tail == null){
			PureLinkedList<Top> node = new PureLinkedList<Top>(value);
			node.tail = this.tail;
			this.tail = node;
		}else{
			this.tail.add(value);
		}
	}
/**
*@param method isLast()
*@returns boolean
*This method returns whether the variable is last or not
*/

	//isLast() method returns if the variable is last or not
	public boolean isLast(){
		if(this.tail == null){
			System.out.println("This is the last item");
			return true;
		}else{
			System.out.println("This is not the last item");
			return false;
		}
	}
	
	
	//Length() method returns an Integer of the number in our LinkedList
	public int length(){
		if(this.tail == null){
			return 1;
		}else{
			return this.tail.length()+ 1;
		}
	}
	
	//getFirst() method
	public Top getFirst(){
		this.value = value;
		return this.value;
	}
	
	//setFirst() method
	public Top setFirst(Top item){
		this.value = value;
		value = item;
		return item;
	}
	
	//getTail() method points the head of the LinkedList to the tail
	//This removes the first value of the LinkedList
	public PureLinkedList<Top> getTail(){
		return this.tail;
	}
	
	public void setTail(PureLinkedList<Top> item){
		this.tail = item;
	}
	
	public void PureLinkedList(){
		
	}//End of Class method
	
	
	public Top get(int x){
		int length = this.length();
		//Top item = this.tail.get(x - 1);
		if(x == 0){
			return this.value;
		}
		if (x >= length || x < 0){
			throw new IndexOutOfBoundsException("Your index has too much chowdah"); 
		} else{
			return this.tail.get(x - 1);
		}
	}
	
	public void set(int index, Top element){
		int length = this.length();
		if (index >= length || index < 0){
			throw new IndexOutOfBoundsException("Your index has too much chowdah"); 
		}
		if(index == 0){
			this.value = element;
		}else{
			this.tail.set(index - 1, element);
		}
	}
	
	public boolean equals(PureLinkedList<Top> item){
		if(this.length() != item.length()){
			return false;
		}else if(item.tail == null && this.tail == null){
			return item.getFirst().equals(this.value);
		}else if(item.getFirst().equals(this.value) && item.getTail().equals(this.tail)){
			return true;
		}
		return false;
	}
	
	//Equals for obj 
	public boolean equals(Object obj) {
    try {
        PureLinkedList<Top> other = (PureLinkedList<Top>) obj;
        return this.equals(other);
    } catch (ClassCastException e) {
        return false;
    }
}
	/*
for(int j = 0; j == x; j++){
	Top node = value;
	return node;
	}
	*/
	

	
	// Public methods for Project2
	
	/**
	* Returns a String version of this.
	*
	*@return A String description of this.
	*/
	
	public String toString(){
		if(this.tail == null){
			return "[" + this.value + "]";
			
		} else {
			String tailString = this.tail.toString();
			String tailMinusLeftBracket = tailString.substring(1);
			return "[" + this.value + ", " + tailMinusLeftBracket;
		}
	}//End of toString method
	
	/*
	
	//isLast Method
	public boolean isLast(){
		this.value = value;
		int index = LinkedList.get(value);
		if( index == LinkedList.size()){
			return true;
		}else{
			return false;
		}
	}
	
	*/
	
	//Main method for testing purposes
	/** 
	* Unit test for class()
	*@param args Arguments used to test this class.
	*/
	public static void main(String[] args){
		
		//Output of this test should be a string containing "hi"
		PureLinkedList<String> strings = new PureLinkedList<String>("hi");
		System.out.println(strings.toString());
		System.out.println(strings.isLast());
		
		//Tests the add() method with two elements in the Linked List
		String hi = "Hi";
		strings.add(hi);
		System.out.println(strings.toString());
		
		//Test the add() method with more than two elements in the Linked List
		String hello = "howdy";
		String hola = "hola";
		strings.add(hi);
		System.out.println(strings.toString());
		strings.add(hello);
		System.out.println(strings.toString());
		strings.add(hola);
		System.out.println(strings.toString());
		
		
		System.out.println(strings.isLast());
		//Should return five as a value
		System.out.println(strings.length());
		
		System.out.println(strings.getFirst());
		
		
		System.out.println("New first element is " + strings.setFirst("hey"));
		System.out.println(strings);
		
		System.out.println("New first element is " + strings.setFirst("Chow"));
		System.out.println(strings);
		
		
		//System.out.println(strings.getTail(strings));
		
		
		PureLinkedList<String> wings = new PureLinkedList<String>("hehe");
		String chick = "haha";
		String leg = "hoho";
		wings.add(chick);
		wings.add(leg);
		
		wings.isLast();
		System.out.println("Test" + wings);
		//strings.getTail(wings);
		System.out.println(wings);
		
		strings.setTail(wings);

		/*
		//Output of this test should be 5 if it works right
		PureLinkedList<Integer> integers = new PureLinkedList<Integer>(5);
		System.out.println(integers.toString());
		//Test works!
		*/
		
	}//End of Main Method (testing)
	
}//End of class