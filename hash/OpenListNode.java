package hash;

public class OpenListNode<V> implements Cloneable{
	
	private DataNode<V> data;
	private OpenListNode<V> next;
	
	public OpenListNode(DataNode<V> data)
	{
		this.data=data;
	}
	
	public DataNode<V> getData()
	{
		return this.data;
	}
	
	public OpenListNode<V> getNext()
	{
		return this.next;
	}
	
	public void setNext(OpenListNode<V> next)
	{
		this.next=next;
	}
	
	 public OpenListNode<V> clone() {  
		          OpenListNode<V> stu = null;  
		         try{  
		              stu = (OpenListNode<V>)super.clone();   //浅复制  
		          }catch(CloneNotSupportedException e) {  
		             e.printStackTrace();  
		         }  
		          stu.data = (DataNode<V>)data.clone();   //深度复制  
		          return stu;  
		      }  
}
