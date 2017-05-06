package red_black_tree;



public class  Node<V> {
	
	private Node<V> lchild;
	private boolean color;  //0±íºÚ£¬1±íºì
	private String key;
	private V value;
	private Node<V> rchild;
	
	public Node(String key,V value)
	{
		this.key=key;
		this.value=value;
	}
	
	public void setLchild(Node<V> p)
	{
		this.lchild=p;
	}
	
	public Node<V> getLchild()
	{
		return this.lchild;
	}
	
	public Node<V> getRchild()
	{
		return this.rchild;
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
		return this.value;
	}
	
	public void setValue(V value)
	{
		this.value=value;
	}
	
	public void setRchild(Node<V> p)
	{
		this.rchild=p;
	}
	
	public void setBlackColor()
	{
		this.color=false;
	}
	
	public void setRedColor()
	{
		this.color=true;
	}
	
	public void setColor(boolean color)
	{
		this.color=color;
	}
	
	public boolean getColor()
	{
		return this.color;
	}
	
	public boolean isRed()
	{
		return color;
	}


}
