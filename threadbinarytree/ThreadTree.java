package threadbinarytree;

import java.util.LinkedList;
import java.util.Queue;

public class ThreadTree {
	
	private Node head;
	private Node pre;//线索前驱
	
	public ThreadTree()
	{
		this.head=new Node('#');
		this.pre=head;
	}
	
	//层序序列建立二叉树
	public void createBinaryTree(char[] data)
	{
		this.head.setLChild(new Node(data[0]), true);
		this.head.setRChild(head, true);
		Queue<Node> queue = new LinkedList<Node>();
		queue.offer(head.getLChild());
		Node ptree,ctree;
		for(int i=1;i<data.length;i+=2)
		{
			ptree=queue.poll();
			if(data[i]!='#')
			{
				ctree=new Node(data[i]);
				ptree.setLChild(ctree, true);
				queue.offer(ctree);
			}
			if(data[i+1]!='#')
			{
				ctree=new Node(data[i+1]);
				ptree.setRChild(ctree, true);
				queue.offer(ctree);
			}
		}
	}
	
	//前序线索化
	public void createPreOrderThreadTree(Node root)
	{
		if(root!=null)
		{
			if(root.getLChild()==null)
				root.setLChild(this.pre, false);
			if(this.pre.getRChild()==null)
				pre.setRChild(root, false);
			this.pre=root;
			if(root.getLTag()==true)
			this.createPreOrderThreadTree(root.getLChild());
			if(root.getRTag()==true)
			this.createPreOrderThreadTree(root.getRChild());
		}
	}
	
	//遍历最末节点与头节点间线索
	public void createThread()
	{
		if(this.pre.getRChild()==null)
			this.pre.setRChild(this.head, false);
	}
	
	public Node prePreNext(Node p)
	{
		if(p.getRTag()==false)
			return p.getRChild();
		else if(p.getLTag()==true)
			return p.getLChild();
		else
			return p.getRChild();
	}
	
	public void prePreOrder()
	{
		Node temp=this.head;
		do
		{
			temp=prePreNext(temp);
			if(temp!=this.head)
				System.out.print(temp.getData());
		}while(temp!=this.head);
	}
	
	public Node preInNext(Node p,Node root)
	{
		Node temp=p;
		if(p.getRTag()==true)
		{
			temp=p.getRChild();
			while(temp.getLTag()==true)
			{
				temp=temp.getLChild();
			}
			return temp;
		}
		else
		{
			if(temp.getLTag()==false)
			{
				if(temp.getLChild().getLChild()==temp)
					return temp.getLChild();
				else
				{
					Node parent;
					do
					{
					temp=getParent(temp,root);
					if(temp==null)
						return null;
					parent=getParent(temp,root);
					if(parent!=null&&parent.getLChild()==temp)
					{
						return parent;
					}
					}while(parent!=root);
					return null;
				}
			}
			else
			{
				if(getParent(temp,root)!=null&&getParent(temp,root).getLChild()==temp)
					return getParent(temp,root);
				else
				{
					Node parent;
					do
					{
					temp=getParent(temp,root);
					if(temp==null)
						return null;
					parent=getParent(temp,root);
					if(parent!=null&&parent.getLChild()==temp)
					{
						return parent;
					}
					}while(parent!=root);
					return null;
				}
			}
		}
	}
	
	public void preInOrder()
	{
		Node temp=this.head;
		do
		{
			temp=preInNext(temp,this.head.getLChild());
			if(temp!=null&&temp!=head)
				System.out.print(temp.getData());
		}while(temp!=null&&temp!=head);
	}
	
	public Node prePostNext(Node p,Node root)
	{
		Node temp=p;
		if(temp.getLTag()==false)
		{
			if(temp.getLChild().getLChild()==temp)
			{
				temp=temp.getLChild();
				if(temp.getRTag()==true)
				{
					temp=temp.getRChild();
					while(temp.getLTag()==true||temp.getRTag()==true)
					{
						while(temp.getLTag()==true)
							temp=temp.getLChild();
						if(temp.getRTag()==true)
							temp=temp.getRChild();
					}		
				}
				return temp;
			}
			else if(temp.getLChild().getLChild().getRChild()==temp) //兄弟节点无儿子
				return temp.getLChild().getLChild();
			else if(temp.getLChild().getRChild()==temp&&temp.getLChild().getRTag()==true) //无兄弟节点
				return temp.getLChild();
			else
			{
				return getParent(temp,root);
			}
		}
		else
		{
			if(getParent(temp,root).getLChild()==temp)
			{
				temp=getParent(temp,root);
				if(temp.getRTag()==true)
				{
					temp=temp.getRChild();
					while(temp.getLTag()==true||temp.getRTag()==true)
					{
						while(temp.getLTag()==true)
							temp=temp.getLChild();
						if(temp.getRTag()==true)
							temp=temp.getRChild();
					}		
				}
				return temp;
			}
			else
				return getParent(temp,root);
		}
	}
	
	public void prePostOrder()
	{
		Node temp=this.head;
		while(temp.getLTag()==true||temp.getRTag()==true)
			{
				while(temp.getLTag()==true)
					temp=temp.getLChild();
				if(temp.getRTag()==true)
					temp=temp.getRChild();
			}
		System.out.print(temp.getData());
		if(temp.getData()!=this.head.getLChild().getData())
		do
		{
			temp=prePostNext(temp,this.head.getLChild());
			System.out.print(temp.getData());
			if(temp.getData()==this.head.getLChild().getData())
				break;
		}while(true);
	}
	
	public Node getParent(Node p,Node root)
	{
		Node ret;
		if(p==root)
			return null;
		else
		{
			if(root.getLChild()==p||root.getRChild()==p)
				return root;
			if(root.getLTag()==true)
			{
				ret=getParent(p,root.getLChild());
				if(ret!=null)
					return ret;
			}
			if(root.getRTag()==true)
			{
				ret=getParent(p,root.getRChild());
				if(ret!=null)
					return ret;
			}
		}
		return null;
	}
	
	//后序线索树建立
	public void createPostOrderThreadTree(Node root)
	{
		if(root!=null)
		{
			this.createPostOrderThreadTree(root.getLChild());
			this.createPostOrderThreadTree(root.getRChild());
			if(root.getLChild()==null)
				root.setLChild(this.pre, false);
			if(this.pre.getRChild()==null)
				pre.setRChild(root, false);
			this.pre=root;
		}
	}
	
	public Node postPreNext(Node p)
	{
		if(p.getLTag()==true)
			return p.getLChild();
		else if(p.getRTag()==true)
			return p.getRChild();
		else
		{
			while(true)
			{
				Node parent=this.getParent(p, this.head.getLChild());
				if(parent==null) return null;
				if(parent.getLTag()==true&&parent.getLChild()==p)
					if(parent.getRTag()!=false)
						return parent.getRChild();
				p=parent;
			}
		}
	}
	
	public void postPreOrder()
	{
		Node temp=this.head;
		do
		{
			temp=postPreNext(temp);
			if(temp!=null)
			System.out.print(temp.getData());
		}while(temp!=null);
	}
	
	public Node postInNext(Node p)
	{
		Node temp=p;
		if(p.getRTag()==true)
		{
			temp=p.getRChild();
			while(temp.getLTag()==true)
			{
				temp=temp.getLChild();
			}
			return temp;
		}
		else
		{
			Node parent=this.getParent(p, this.head.getLChild());
			if(parent==null) return null;
			if(parent.getLTag()==true&&parent.getLChild()==p)
				return parent;
			else
			{
				while(true)
				{
					p=parent;
					parent=this.getParent(p, this.head.getLChild());
					if(parent==null) return null;
					if(parent.getLTag()==true&&parent.getLChild()==p)
						return parent;
				}
			}
		}
	}
	
	public void postInOrder()
	{
		Node temp=this.head;
		do
		{
			temp=postInNext(temp);
			if(temp!=null)
			System.out.print(temp.getData());
		}while(temp!=null);
	}
	
	public Node postPostNext(Node p)
	{
		if(p.getRTag()==false)
			return p.getRChild();
		else
		{
			Node parent=this.getParent(p, this.head.getLChild());
			if(parent.getLTag()==true&&parent.getLChild()==p)
			{
				if(parent.getRTag()==false)
					return parent;
				else
				{
					parent=parent.getRChild();
					while(parent.getLTag()==true||parent.getRTag()==true)
					{
						if(parent.getLTag()==true)
							parent=parent.getLChild();
						else
							parent=parent.getRChild();
					}
					return parent;
				}
			}
			else return parent;
		}
	}
	
	public void postPostOrder()
	{
		Node temp=this.head;
		while(temp.getLTag()==true||temp.getRTag()==true)
		{
			if(temp.getLTag()==true)
				temp=temp.getLChild();
			else
				temp=temp.getRChild();
		}
		System.out.print(temp.getData());
		do
		{
			temp=postPostNext(temp);
			System.out.print(temp.getData());
		}while(temp.getData()!=this.head.getLChild().getData());
	}
	
	public static void PrintBtree(Node head,int level)
	{
		if (head == null)
			return;
		PrintBtree(head.getRChild(),level + 1);
		if (level != 0)
		{
			for (int i = 0; i < level - 1; i++)
				System.out.print("|\t");
			System.out.println("|------"+head.getData());
		}
		else
			System.out.println(head.getData());
		PrintBtree(head.getLChild(),level + 1);
	}
	
	public Node getHead()
	{
		return this.head;
	}

}
