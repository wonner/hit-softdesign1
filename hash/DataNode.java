package hash;

public class DataNode<V> implements Cloneable{
	
	String key;
	V otherfield;
	
	public DataNode(String key,V otherfield)
	{
		this.key=key;
		this.otherfield=otherfield;
	}
	
	public String getKey()
	{
		return this.key;
	}
	
	public void setKey(String key)
	{
		this.key=key;
	}
	
	public V getValue()
	{
		return this.otherfield;
	}
	
	public void setValue(V value)
	{
		this.otherfield=value;
	}
	
	public DataNode<V> clone() {  
        DataNode<V> stu = null;  
        try{  
             stu = (DataNode<V>)super.clone();  
        }catch(CloneNotSupportedException e) {  
            e.printStackTrace();  
        }  
       return stu;  
    }  

}
