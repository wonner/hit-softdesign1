# 2.1 利用线索的前序线索二叉树遍历 #
二叉树节点属性如下
	
	private Node lchild;
	private boolean ltag;//false代表线索
	private char data;
	private Node rchild;
	private boolean rtag;

## 2.1.1 前序遍历后继 ##
1. 若节点无右子树，后继节点为右线索指向节点
2. 若节点有左子树，后继节点为该左子树
3. 否则后继节点为右子树
    
.
	
	if(p.getRTag()==false)
			return p.getRChild();
	else if(p.getLTag()==true)
			return p.getLChild();
	else
			return p.getRChild();

## 2.1.2 中序遍历后继 ##

本着尽量使用线索进行遍历的原则。

  若节点有右子树，后继节点为右子树的最左儿子。当节点无右子树时，若该节点为其父节点的左儿子，后继节点为该父节点，若节点为父节点右儿子，后继为该节点祖先节点。下面就是否可以使用线索进行讨论

1. 若节点无子树，判断该节点是否为父亲节点的左子树，若是，则后继节点为该父亲节点（可使用线索），其余情况必须引入getParent函数寻找祖先节点
2. 若节点只有左子树，只能通过getParent函数，节点是父节点左儿子即该父节点为后继，右儿子则祖先为后继节点（另：以上节点必须为祖先节点的左子树内节点）

'

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
以下是getParent函数的代码

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

## 2.1.3 后序遍历后继 ##

  若该节点为其父节点的左儿子，后继为兄弟节点的最左子树，若为右儿子，后继为其父节点。下面就可使用线索的几种情况进行讨论

1. 当节点无左子树时，节点若为父节点左儿子，可利用线索，若为右儿子，只有当兄弟节点无儿子或无兄弟节点时可使用线索
2. 当节点有左子树时，只能使用getPatent函数

可用根节点data值判断遍历是否结束，由于后序遍历第一个节点的寻找不符合以上规则，需在循环输出时修改少许内容

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

	
# 2.2 利用线索的后续线索二叉树遍历 #

## 2.2.1 前序遍历 ##

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
1. 若左子树不为空后继为左子树
2. 否则若右子树不为空后继为右子树
3. 两树皆空寻找父节点，直到找到其为父节点左子树，返回父节点右子树头节点

## 2.2.2 中序遍历 ##

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

  若节点有右子树，后继节点为右子树的最左儿子。当节点无右子树时，若该节点为其父节点的左儿子，后继节点为该父节点，若节点为父节点右儿子，后继为一直向上寻找祖先节点，其为祖先左子树中节点。

## 2.2.3 后序遍历 ##
	
	public Node postPostNext(Node p)
	{
		if(p.getRTag()==false)
			return p.getRChild();
		else
		{
			Node parent=this.getParent(p, this.head.getLChild());
			if(parent.getLChild()==p)
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
	
1. 若节点无右子树，后继节点为右线索指向节点
2. 若有右子树则找到父节点，若其为父节点左儿子，则后继节点为右儿子最底层节点，若为父节点右儿子，则该父节点为后继节点

