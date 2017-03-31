package forestandbinarytree;

public class TreeNode {
	
	private String data;
	private Node firstchild;
	
	public void setData(String data)
	{
		this.data=data;
	}
	
	public String getData()
	{
		return this.data;
	}
	
	public void setFirstchild(Node child)
	{
		this.firstchild=child;
	}
	
	public Node getFirstchild()
	{
		return this.firstchild;
	}

}
