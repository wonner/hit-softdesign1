package dynamic_huffman;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Stack;

public class HuffmanTree {
	
	private ArrayList<HuffmanNode> leaf;
	
	public HuffmanTree()
	{
		this.leaf=new ArrayList<>();
		this.leaf.add(new HuffmanNode(null,0,null,null,null));
	}
	
	public void coding() throws IOException
	{
		FileReader fr=new FileReader("F://666/article.txt");
		BufferedReader br=new BufferedReader(fr);
		int temp=br.read();
		do
		{
			int index=this.encoding((char)temp);
			this.rebuildHuffman(index);
		}while((temp=br.read())!=-1);
	}
	
	public void decoding() throws IOException
	{
		FileReader fr1=new FileReader("F://666/code.txt");
		BufferedReader br1=new BufferedReader(fr1);
		FileReader fr2=new FileReader("F://666/list.txt");
		BufferedReader br2=new BufferedReader(fr2);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
        		new FileOutputStream("F://666/decoding.txt", true)));
		int temp=br1.read();
		do
		{
			HuffmanNode p=this.leaf.get(0);
			int i=1;
			do
			{
				if(p.getCh()==null&&p.getLchild()==null)
				{
					char ch=(char)br2.read();
					i=this.leaf.size();
					this.leaf.add(new HuffmanNode(ch,1,i-1,null,null)); 
					this.leaf.add(new HuffmanNode(null,0,i-1,null,null));
					this.leaf.get(i-1).setWLR(1,i+1,i);
					bw.write(ch);
					bw.flush();
					temp=br1.read();
					break;
				}
				else if(p.getCh()!=null)
				{
					bw.write(p.getCh());
					bw.flush();
					p.addWeight();
					break;
				}
				else
				{
					if((char)temp=='0')
					{
						i=p.getLchild();
						p=this.leaf.get(p.getLchild());
					}
					else
					{
						i=p.getRchild();
						p=this.leaf.get(p.getRchild());
					}
					temp=br1.read();
				}
			}while(true);
			this.rebuildHuffman(i);
		}while(temp!=-1);
	}
	
	public int encoding(char ch) throws IOException
	{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
        		new FileOutputStream("F://666/list.txt", true)));
        BufferedWriter bws = new BufferedWriter(new OutputStreamWriter(
        		new FileOutputStream("F://666/code.txt", true)));
		int i=0;
		while(i<this.leaf.size())
		{
			if(this.leaf.get(i).getCh()!=null&&this.leaf.get(i).getCh()==ch)
				break;
			i++;
		}
		if(i==this.leaf.size())
		{
			this.leaf.add(new HuffmanNode(ch,1,i-1,null,null)); //新加入字符
			this.leaf.add(new HuffmanNode(null,0,i-1,null,null));//新的零端
			this.leaf.get(i-1).setWLR(1,i+1,i); //旧的零端变为（子）树根
			bw.write(ch); //向新加入字符文件中写入
			bw.flush();
		}
		else
		{
			this.leaf.get(i).addWeight();
		}
		//编码
		int temp=i;
		Integer parent=this.leaf.get(temp).getParent();
		Stack<Character> stack=new Stack<>();
		while(parent!=null)
		{
			if(this.isLchild(temp, parent))
				stack.push('0');
			else
				stack.push('1');
			temp=parent;
			parent=this.leaf.get(temp).getParent();
		}
		while(!stack.isEmpty())
		{	
			bws.write(stack.pop());
		}
		bws.flush();	
		
		return i;
	}
	
	public void rebuildHuffman(int i)
	{
		Integer parent=this.leaf.get(i).getParent();
		if(this.getLchildWeight(parent)>this.getRchildWeight(parent))
			this.changeLRchild(parent); //保持左儿子权值小于右儿子
		parent=this.leaf.get(i).getParent();
		Integer ancestor=this.leaf.get(parent).getParent();
		while(ancestor!=null)//一直循环到树根
		{
			int sibling=this.findSibling(parent, ancestor);
			if(this.leaf.get(sibling).getWeight()<this.leaf.get(i).getWeight())
			{
				if(this.isLchild(sibling, ancestor))
					this.leaf.get(ancestor).setLchild(i);
				else
					this.leaf.get(ancestor).setRchild(i);
				this.leaf.get(i).setParent(ancestor);
				if(this.isLchild(i, parent))
					this.leaf.get(parent).setLchild(sibling);
				else
					this.leaf.get(parent).setRchild(sibling);
				this.leaf.get(sibling).setParent(parent);
			}
			this.leaf.get(parent).setWeight(this.getLchildWeight(parent)+this.getRchildWeight(parent));
			if(this.getLchildWeight(ancestor)>this.getRchildWeight(ancestor))
				this.changeLRchild(ancestor);
			i=this.leaf.get(ancestor).getRchild();
			parent=this.leaf.get(i).getParent();
			ancestor=this.leaf.get(parent).getParent();
		}
		this.leaf.get(parent).setWeight(this.getLchildWeight(parent)+this.getRchildWeight(parent));
		if(this.getLchildWeight(parent)>this.getRchildWeight(parent))
			this.changeLRchild(parent);
	}
	
	
	
	
	public int getLchildWeight(int parent)
	{
		return this.leaf.get(this.leaf.get(parent).getLchild()).getWeight();
	}
	
	public int getRchildWeight(int parent)
	{
		return this.leaf.get(this.leaf.get(parent).getRchild()).getWeight();
	}
	
	public void changeLRchild(int parent)
	{
		int temp=this.leaf.get(parent).getLchild();
		this.leaf.get(parent).setLchild(this.leaf.get(parent).getRchild());
		this.leaf.get(parent).setRchild(temp);
	}
	
	public int findSibling(int parent,int ancestor)
	{
		if(this.leaf.get(ancestor).getLchild()==parent)
			return this.leaf.get(ancestor).getRchild();
		else
			return this.leaf.get(ancestor).getLchild();
	}
	
	public boolean isLchild(int parent,int ancestor)
	{
		if(this.leaf.get(ancestor).getLchild()==parent)
			return true;
		else
			return false;
	}

}
