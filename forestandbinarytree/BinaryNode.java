package forestandbinarytree;

public class BinaryNode {
	
	private String data;
	private BinaryNode lchild;
	private BinaryNode rchild;
	
	public BinaryNode(String data)
	{
		this.data=data;
	}
	
	public String getData()
	{
		return this.data;
	}
	
	public void setLchild(BinaryNode lchild)
	{
		this.lchild=lchild;
	}
	
	public BinaryNode getLchild()
	{
		return this.lchild;
	}
	
	public void setRchild(BinaryNode rchild)
	{
		this.rchild=rchild;
	}
	
	public BinaryNode getRchild()
	{
		return this.rchild;
	}

}
