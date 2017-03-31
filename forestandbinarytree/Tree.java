package forestandbinarytree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Tree {
	
	private TreeNode[] list;
	
	public Tree(int n)
	{
		this.list=new TreeNode[n];
		for(int i=0;i<n;i++)
		{
			this.list[i]=new TreeNode();
		}
	}
	
	public TreeNode getTreeNode(int index)
	{
		return this.list[index];
	}
	
	public TreeNode[] getTreeList()
	{
		return this.list;
	}
	
	
	public int searchIndex(String str)
	{
		int i;
		for(i=0;i<this.list.length;i++)
		{
			if(this.list[i].getData().equals(str))
				break;
		}
		if(i==this.list.length)
			return -1;
		else
			return i;
	}
	
	public String searchData(int index)
	{
		return this.list[index].getData();
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
	
	public static ArrayList<Tree> createTree() throws IOException
	{
		ArrayList<Tree> forest=new ArrayList<>();
		FileReader fr=new FileReader("F://666/tree.txt"); //ÎÄ¼þÎ»ÖÃ
		BufferedReader br=new BufferedReader(fr);
		String str;
		str=br.readLine();
		Tree temp;
		do
		{
			temp=new Tree(Integer.parseInt(str));
			str=br.readLine();
			String[] stringlist=str.split(",");
			for(int i=0;i<stringlist.length;i++)
			{
				temp.list[i].setData(stringlist[i]);
			}
			while(!(str=br.readLine()).equals("#"))
			{
				stringlist=str.split(",");
				int j=temp.searchIndex(stringlist[0]);
				for(int i=1;i<stringlist.length;i++)
				{
					Node child=new Node(temp.searchIndex(stringlist[i]),temp.list[j].getFirstchild());
					temp.list[j].setFirstchild(child);
				}
			}
			forest.add(temp);
		}while((str=br.readLine())!=null);
		return forest;
	}
	
	public static BinaryTree forestToBinary(ArrayList<Tree> forest)
	{
		BinaryTree binary=new BinaryTree();
		BinaryNode temp=null,head=null;
		Node p;
		for(int i=0;i<forest.size();i++)
		{
			for(int j=0;j<forest.get(i).list.length;j++)
			{
				p=forest.get(i).list[j].getFirstchild();
				if(j==0)
				{
					head=new BinaryNode(forest.get(i).list[j].getData());
					if(p!=null)
					{
						head.setLchild(new BinaryNode(forest.get(i).searchData(p.getData())));
						temp=head.getLchild();
						p=p.getNext();
					}
					while(p!=null)
					{
						temp.setRchild(new BinaryNode(forest.get(i).searchData(p.getData())));
						temp=temp.getRchild();
						p=p.getNext();
					}
				}
				else
				{
					if(p!=null)
					{
						temp=searchPosition(head,forest.get(i).list[j].getData());
						temp.setLchild(new BinaryNode(forest.get(i).searchData(p.getData())));
						temp=temp.getLchild();
						p=p.getNext();
					}
					while(p!=null)
					{
						temp.setRchild(new BinaryNode(forest.get(i).searchData(p.getData())));
						temp=temp.getRchild();
						p=p.getNext();
					}
				}
			}
			if(binary.getHead()==null)
				binary.setHead(head);
			else
			{
				temp=binary.getHead();
				while(temp.getRchild()!=null)
					temp=temp.getRchild();
				temp.setRchild(head);
			}
		}
		return binary;
	}
	
	public void printTree()
	{
		for(int i=0;i<this.list.length;i++)
		{
			System.out.print(this.list[i].getData()+" 's child: ");
			Node p=this.list[i].getFirstchild();
			while(p!=null)
			{
				System.out.print(searchData(p.getData())+" ");
				p=p.getNext();
			}
			System.out.println();
		}
	}

}
