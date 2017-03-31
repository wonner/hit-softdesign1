# Adaptive Huffman coding 适应性霍夫曼编码 #

## 动态Huffman简介 ##

基于静态Huffman编码算法对输入的符号流进行编码，必须进行两次扫描，第一次扫描统计字符出现的概率，并创建Huffman树；第二次扫描是按照Huffman树的字符进行编码。并且在存储和传输Huffman编码时，必须先存储和传送Huffman树。这些问题使的静态Huffman编码在实际应用用的的较少。为了解决静态Huffman编码的缺点，产生了自适应Huffman编码，它只需要对输入的符号流进行一次扫描即可。这种方案在不需要事先构造 Huffman 树，而是随着编码的进行，逐步构造 Huffman 树。同时，这种编码方案对符号的统计也动态进行，随着编码的进行，同一个符号的编码可能发生改变（变得更长或更短）。**编码和解码使用相同的初始Huffman，每处理完一个字符，编码和解码使用相同的方法修改Huffman，所以没有必要为解码而保存Huffman的信息。**编码和解码一个字符所需的时间与该字符的编码长度成正比，所以动态哈夫曼编码可实时进行。

## 关键算法 ##

**1. FGK算法**

解码和编码需使用一个相同的初始huffman树，而考虑适应性霍夫曼编码的效能问题，更有效能的方法是确认编码端不会浪费空间在不存在的符号上，一般的霍夫曼编码容易做到是因为在建立霍夫曼树之前，会统计所有的资料，就能先算出各讯息的频率；相反的，适应性编码不一样，并不知资料出现的频率，因此假设所有讯息长度都一样是log_2⁡q个位元，伴随着统计资料的增加，讯息的编码长度会愈来愈短。但此方法有个缺点，在大部分情况会浪费许多空间，尤其在短讯息的情况，大部分未使用的符号会改变整个统计表，使得压缩结果大打折扣。

举例来说，符号源有256个符号，一开始的霍夫曼树就有这256个符号，且加权值设为1，即使连续读进16个e，也仍然需要四个位元以上来编码e，因为其他没出现的255个符号使得霍夫曼树的调整速度变慢。

所以本次算法采用一颗空的huffman树作为初始树，由单一叶子组成，成为0端，0端是用来代表未出现过的讯息，当每一个符号传递后，若符号存在于
树中，将对应权值加1，并调整其为一新的huffman树，若该符号不存在，则将0端分裂成一新的0端和包含该符号的叶子，随后进行调整。将新加入字符加入到统计表内，以便解码时使用。

    public void coding() throws IOException
	{
		FileReader fr=new FileReader("F://666/article.txt");
		BufferedReader br=new BufferedReader(fr);
		int temp=br.read();
		do
		{
			//先对符号编码后更新编码树
			int index=this.encoding((char)temp);//插入符号
			this.rebuildHuffman(index);//重构huffman树
		}while((temp=br.read())!=-1);
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

**2.vitter算法**

vitter算法可以说是动态huffman算法的核心，用于加入频率后huffman的重构。

引入兄弟属性概念：


1. 权重值较大的节点，节点编号也较大；
2. 父节点的节点编号总是大于子节点的节点编号 


![](http://i.imgur.com/Cf4Fz10.png)
![](http://i.imgur.com/uZulYOX.png)
![](http://i.imgur.com/K62ua5E.png)

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

解码函数

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
			}while(true);//读取code
			this.rebuildHuffman(i);//采用相同方法更新huffman
		}while(temp!=-1);
	}

## Adaptive Huffman与静态Huffman性能比较 ##

两者都采用二叉树存储形式，压缩率差异不大但Adaptive Huffman压缩率更小，因其对单个字符更新不能保持其长度最短。但所需传输的除了压缩code之外静态还需传输建好的huffman树，动态只需传输统计表即可，译码效率增强。