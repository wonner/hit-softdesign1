package forestandbinarytree;

import java.util.ArrayList;

public class BinaryTree {
	
	private BinaryNode head;
	private static int count;
	
	public BinaryNode getHead()
	{
		return this.head;
	}
	
	public void setHead(BinaryNode head)
	{
		this.head=head;
	}
	
	public ArrayList<Tree> binaryToForest()
	{
		ArrayList<Tree> forest=new ArrayList<>();
		BinaryNode root,temp=head;
		while(temp!=null)
		{
			root=temp;
			temp=temp.getRchild();
			root.setRchild(null);
			count=0;
			nodeCount(root);
			Tree tree=new Tree(count);
			createTreeList(tree,root);
			createTreeChild(tree,root);
			forest.add(tree);
		}
		return forest;
	}
	
	public static void nodeCount(BinaryNode root)
	{
		if(root!=null)
		{
			count++;
			nodeCount(root.getLchild());
			nodeCount(root.getRchild());
		}
	}
	
	public static void createTreeList(Tree tree,BinaryNode root)
	{
		if(root!=null)
		{
			tree.getTreeNode(--count).setData(root.getData());
			createTreeList(tree,root.getLchild());
			createTreeList(tree,root.getRchild());
		}
	}
	
	public static BinaryNode searchPosition(BinaryNode root,String str)
	{
		BinaryNode temp=root;
		if(root!=null)
		{
			if(root.getData().equals(str))
				return temp;
			temp=searchPosition(root.getLchild(),str);
			if(temp!=null) return temp;
			temp=searchPosition(root.getRchild(),str);
			if(temp!=null) return temp;
		}
		return null;

	}
	
	public static void createTreeChild(Tree tree,BinaryNode root)
	{
		for(int i=0;i<tree.getTreeList().length;i++)
		{
			BinaryNode temp=searchPosition(root,tree.getTreeNode(i).getData());
			if(temp.getLchild()!=null)
			{
				temp=temp.getLchild();
				tree.getTreeNode(i).setFirstchild(new Node(tree.searchIndex(temp.getData()),tree.getTreeNode(i).getFirstchild()));
				temp=temp.getRchild();
				while(temp!=null)
				{
					tree.getTreeNode(i).setFirstchild(new Node(tree.searchIndex(temp.getData()),tree.getTreeNode(i).getFirstchild()));
					temp=temp.getRchild();
				}
			}
		}
	}
	
	
	public static void PrintBtree(BinaryNode head,int level)
	{
		if (head == null)
			return;
		PrintBtree(head.getRchild(),level + 1);
		if (level != 0)
		{
			for (int i = 0; i < level - 1; i++)
				System.out.print("|\t");
			System.out.println("|------"+head.getData());
		}
		else
			System.out.println(head.getData());
		PrintBtree(head.getLchild(),level + 1);
	}

}
