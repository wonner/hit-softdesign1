package red_black_tree;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RedBlackTree<V> {
	
	private Node<V> head;
	private int comparetimes;
	private int balancetimes;
	
	public RedBlackTree()
	{
		this.head=new Node<V>(null,null);
		head.setRchild(head);
		this.balancetimes=0;
		this.balancetimes=0;
	}
	
	public V search(String str)
	{
		Node<V> temp=head.getLchild();
		comparetimes=0;
		while(temp!=head)
		{
			comparetimes++;
			if(temp.getKey().equals(str))
				return temp.getValue();
			if(temp.getKey().length()>str.length())
				temp=temp.getLchild();
			else if(temp.getKey().length()<str.length())
				temp=temp.getRchild();
			else
			{
				if(temp.getKey().compareTo(str)>0)
					temp=temp.getLchild();
				else
					temp=temp.getRchild();
			}
		}
		return null;
	}
	
	public boolean modify(String str,V value)
	{

		Node<V> temp=head.getLchild();
		while(temp!=head)
		{
			if(temp.getKey().equals(str))
			{
				temp.setValue(value);
				return true;
			}
			if(temp.getKey().length()>str.length())
				temp=temp.getLchild();
			else if(temp.getKey().length()<str.length())
				temp=temp.getRchild();
			else
			{
				if(temp.getKey().compareTo(str)>0)
					temp=temp.getLchild();
				else
					temp=temp.getRchild();
			}
		}
		return false;
	}
	
	public void insert(Node<V> p)
	{
		ArrayList<Node<V>> list=new ArrayList<>(); //记录路径
		p.setLchild(head);
		p.setRchild(head);
		if(this.head.getLchild()==null)
		{
			p.setBlackColor();
			head.setLchild(p);
		}
		else
		{
			p.setRedColor();
			Node<V> pre=head;
			list.add(pre);
			Node<V> temp=head.getLchild();
			while(temp!=head)
			{
				pre=temp;
				list.add(pre);
				comparetimes++;  //记录比较次数
				if(temp.getKey().length()>p.getKey().length())
					temp=temp.getLchild();
				else if(temp.getKey().length()<p.getKey().length())
					temp=temp.getRchild();
				else
				{
					if(temp.getKey().compareTo(p.getKey())>0)
						temp=temp.getLchild();
					else
						temp=temp.getRchild();
				}
			}
			if(pre.getKey().length()>p.getKey().length())
				pre.setLchild(p);
			else if(pre.getKey().length()<p.getKey().length())
				pre.setRchild(p);
			else
			{
				if(pre.getKey().compareTo(p.getKey())>0)
					pre.setLchild(p);
				else
					pre.setRchild(p);
			}
			list.add(p);
			insertFixup(list);  
			
		}
	}
	
	public void insertFixup(ArrayList<Node<V>> list)
	{
		int pindex=list.size()-1;
		Node<V> p=list.get(pindex);
		Node<V> parent=list.get(pindex-1);
		Node<V> ancestor=list.get(pindex-2);
		while(parent.isRed())
		{
			if(ancestor.getLchild()==parent)
			{
				Node<V> sibling=ancestor.getRchild();
				if(sibling.isRed())
				{
					parent.setBlackColor();
					sibling.setBlackColor();
					ancestor.setRedColor();
					p=ancestor;
					pindex=pindex-2;
					parent=list.get(pindex-1);
					if(pindex-2>=0)
					ancestor=list.get(pindex-2);
				}
				else
				{
					if(parent.getRchild()==p)
					{
						leftRotate(parent.getRchild(),parent,ancestor);
						list.set(pindex, parent);
						list.set(pindex-1, p);
						p=list.get(pindex);
						parent=list.get(pindex-1);
					}
					Node<V> temp=list.get(pindex-3);
					parent.setBlackColor();
					ancestor.setRedColor();
					rightRotate(ancestor.getLchild(),ancestor,temp);
					ancestor=temp;
					list.remove(pindex-2);
				}
			}
			else
			{
				Node<V> sibling=ancestor.getLchild();
				if(sibling.isRed())
				{
					parent.setBlackColor();
					sibling.setBlackColor();
					ancestor.setRedColor();
					p=ancestor;
					pindex=pindex-2;
					parent=list.get(pindex-1);
					if(pindex-2>=0)
					ancestor=list.get(pindex-2);
				}
				else
				{
					if(parent.getLchild()==p)
					{
						rightRotate(parent.getLchild(),parent,ancestor);
						list.set(pindex, parent);
						list.set(pindex-1, p);
						p=list.get(pindex);
						parent=list.get(pindex-1);
					}
					Node<V> temp=list.get(pindex-3);
					parent.setBlackColor();
					ancestor.setRedColor();
					leftRotate(ancestor.getRchild(),ancestor,temp);
					ancestor=temp;
					list.remove(pindex-2);
				}
			}
		}
		this.head.getLchild().setBlackColor();
	}
	
	public boolean delete(String str)
	{
		Node<V> y=null,py=null,x=null,px=null;
		ArrayList<Node<V>> list=new ArrayList<>();
		list.add(head);
		Node<V> temp=head.getLchild();
		while(!temp.getKey().equals(str))
		{
			list.add(temp);
			if(temp.getKey().length()>str.length())
				temp=temp.getLchild();
			else if(temp.getKey().length()<str.length())
				temp=temp.getRchild();
			else
			{
				if(temp.getKey().compareTo(str)>0)
					temp=temp.getLchild();
				else
					temp=temp.getRchild();
			}
			if(temp==head)
				return false;
		}
		list.add(temp);
		if(temp.getLchild()==head||temp.getRchild()==head)
		{
			y=temp;
			py=list.get(list.size()-2);
		}
		else
		{
			y=temp.getRchild();
			list.add(y);
			py=temp;
			while(y.getLchild()!=head)
			{
				py=y;
				y=y.getLchild();
				list.add(y);
			}
		}
		if(y.getLchild()!=head)
			x=y.getLchild();
		else
			x=y.getRchild();
		list.set(list.size()-1, x);
		if(py.getLchild()==y)
			py.setLchild(x);
		else
			py.setRchild(x);
		if(y!=temp)
		{
			temp.setKey(y.getKey());
			temp.setValue(y.getValue());
		}
		if(!y.isRed())
			deleteFixup(list);
		return true;
	}
	
	public void deleteFixup(ArrayList<Node<V>> list)
	{
		int xindex=list.size()-1;
		Node<V> x=list.get(xindex);
		while(x!=head.getLchild()&&!x.isRed())
		{
			Node<V> px=list.get(xindex-1);
			Node<V> pa=list.get(xindex-2);
			if(px.getLchild()==x)
			{
				Node<V> w=px.getRchild();
				if(w.isRed())
				{
					w.setBlackColor();
					px.setRedColor();
					leftRotate(px.getRchild(),px,pa);
					w=px.getRchild();
				}
				if(!w.getLchild().isRed()&&!w.getRchild().isRed())
				{
					w.setRedColor();
					x=px;
					xindex--;
				}
				else 
				{
					if(!w.getRchild().isRed())
					{
						w.getLchild().setBlackColor();
						w.setRedColor();
						rightRotate(w.getLchild(),w,px);
						w=px.getRchild();
					}
					w.setColor(px.getColor());
					px.setBlackColor();
					w.getRchild().setBlackColor();
					x=head.getLchild();
				}
				
			}
			else
			{
				Node<V> w=px.getLchild();
				if(w.isRed())
				{
					w.setBlackColor();
					px.setRedColor();
					rightRotate(px.getLchild(),px,pa);
					w=px.getLchild();
				}
				if(!w.getRchild().isRed()&&!w.getLchild().isRed())
				{
					w.setRedColor();
					x=px;
					xindex--;
				}
				else 
				{
					if(!w.getLchild().isRed())
					{
						w.getRchild().setBlackColor();
						w.setRedColor();
						leftRotate(w.getRchild(),w,px);
						w=px.getLchild();
					}
					w.setColor(px.getColor());
					px.setBlackColor();
					w.getLchild().setBlackColor();
					x=head.getLchild();
				}
			}
		}
		x.setBlackColor();
	}
	
	public void leftRotate(Node<V> p,Node<V> parent,Node<V> ancestor)
	{
		balancetimes++;//记录调整次数
		parent.setRchild(p.getLchild());
		p.setLchild(parent);
		if(ancestor.getLchild()==parent)
			ancestor.setLchild(p);
		else
			ancestor.setRchild(p);
	}
	
	public void rightRotate(Node<V> p,Node<V> parent,Node<V> ancestor)
	{
		balancetimes++;//记录调整次数
		parent.setLchild(p.getRchild());
		p.setRchild(parent);
		if(ancestor.getLchild()==parent)
			ancestor.setLchild(p);
		else
			ancestor.setRchild(p);
	}
	
	public void PrintBtree(Node root,int level)
	{
		if (root == head)
			return;
		PrintBtree(root.getRchild(),level + 1);
		if (level != 0)
		{
			for (int i = 0; i < level - 1; i++)
				System.out.print("|\t");
			System.out.println("|------"+root.getKey());
		}
		else
			System.out.println(root.getKey());
		PrintBtree(root.getLchild(),level + 1);
	}
	
	public void BST_insert(Node<V> p)
	{
		if(this.head.getLchild()==null)
			head.setLchild(p);
		else
		{
			Node<V> pre=head;
			Node<V> temp=head.getLchild();
			while(temp!=null)
			{
				pre=temp;
				if(temp.getKey().length()>p.getKey().length())
					temp=temp.getLchild();
				else if(temp.getKey().length()<p.getKey().length())
					temp=temp.getRchild();
				else
				{
					if(temp.getKey().compareTo(p.getKey())>0)
						temp=temp.getLchild();
					else
						temp=temp.getRchild();
				}
			}
			if(pre.getKey().length()>p.getKey().length())
				pre.setLchild(p);
			else if(pre.getKey().length()<p.getKey().length())
				pre.setRchild(p);
			else
			{
				if(pre.getKey().compareTo(p.getKey())>0)
					pre.setLchild(p);
				else
					pre.setRchild(p);
			}
		}
	}
	
	public Node<V> getHead()
	{
		return this.head;
	}
	
	public int getComparetimes()
	{
		return this.comparetimes;
	}
	
	public int getBalancetimes()
	{
		return this.balancetimes;
	}
	
	public void setBalancetimes()
	{
		this.balancetimes=0;
	}

}
