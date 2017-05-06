package red_black_tree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class RedBlackTreeTest {

	public static void main(String[] args) throws IOException {
		// TODO �Զ����ɵķ������
		
		RedBlackTree<Integer> tree=new RedBlackTree<>();
		
		FileReader fr=new FileReader("F://666/score.txt"); //�ļ�λ��
		BufferedReader br=new BufferedReader(fr);
		String str;
		while((str=br.readLine())!=null)
		{
			String[] stringlist=str.split(" ");
			Node<Integer> temp=new Node<Integer>(stringlist[0],Integer.parseInt(stringlist[1]));
			tree.insert(temp);
		}
		Scanner in = new Scanner(System.in);
		int ch;
	    do {
	      System.out.println("1.search");
	      System.out.println("2��insert");
	      System.out.println("3.delete");
	      System.out.println("4.modify");
	      System.out.println("5.print the tree");
	      System.out.println("0.exit");
	      ch = in.nextInt();
	      String[] stringlist;

	      switch (ch)
	      {
	      case 1:
	        System.out.print("please enter the key:");
	        str= in.nextLine();
	        str= in.nextLine();
	        Integer value = tree.search(str);
	        if (value != null) System.out.println("find it,the value is" + value); 
	        else
	          System.out.println("not found");
	        break;
	      case 2:
	        System.out.print("please enter the key and value:");
	        str= in.nextLine();
	        str= in.nextLine();
	        stringlist=str.split(" ");
	        Node<Integer> temp=new Node<Integer>(stringlist[0],Integer.parseInt(stringlist[1]));
	        tree.insert(temp);
	        break;
	      case 3:
	        System.out.print("please enter the key");
	        str= in.nextLine();
	        str= in.nextLine();
	        if (tree.delete(str))
	        {
	          System.out.println("success");
	        } else {
	          System.out.println("fail");
	        }break;
	      case 4:
	    	  System.out.print("please enter the key and value:");
	    	  str= in.nextLine();
		      str= in.nextLine();
		      stringlist=str.split(" ");
		      if(tree.modify(stringlist[0],Integer.parseInt(stringlist[1])))
		    	  System.out.println("success");
		      else
		    	  System.out.println("fail");
		      break;
	      case 5:
	        tree.PrintBtree(tree.getHead().getLchild(), 0);
	      }
	    }
	    while (ch != 0);
		
		

	    //���ܲ���
	    RedBlackTree<Integer> testtree=new RedBlackTree<>();
	    File f=new File("F://666/testdata.txt");
	    f.delete();
	    FileWriter fw = new FileWriter(f, true);
	    PrintWriter pw = new PrintWriter(fw);
	    f.delete();
	    
	    
	    int n=10000;
	    for(int i=0;i<n;i++)
	    {
	    	Random random=new Random();
	    	int data=random.nextInt(n*10);
	    	pw.println(data);
		    pw.flush();
	    	Node<Integer> temp=new Node<Integer>(Integer.toString(data),null);
	    	testtree.insert(temp);
	    }
	    //testtree.PrintBtree(testtree.getHead().getLchild(), 0);
	    System.out.println("��AVL�Ƚϣ�");
	    System.out.println("�Ƚϴ���Ϊ��"+testtree.getComparetimes());
	    System.out.println("ƽ�����Ϊ��"+testtree.getBalancetimes());
	    
	    fr=new FileReader("F://666/testdata.txt");
	    br=new BufferedReader(fr);
	    long sum=0;
	    for(int i=0;i<n;i++)
	    {
	    	str=br.readLine();
	    	testtree.search(str);
	    	sum+=testtree.getComparetimes();
	    }
	    System.out.println("ƽ�����ҳ���Ϊ��"+(1.0*sum/n));
	    fr.close();
	    fr=new FileReader("F://666/testdata.txt");
	    br=new BufferedReader(fr);
	    testtree.setBalancetimes();
	    for(int i=0;i<n/2;i++)
	    {
	    	str=br.readLine();
	    	testtree.delete(str);
	    }
	    //testtree.PrintBtree(testtree.getHead().getLchild(), 0);
	    System.out.println("ɾ��һ��ڵ�ƽ�����Ϊ��"+testtree.getBalancetimes());
	    
		RedBlackTree<Integer> testtree1=new RedBlackTree<>();
		RedBlackTree<Integer> bsttree=new RedBlackTree<>();
		long starttime,endtime;
		starttime=System.currentTimeMillis();
		for(int i=0;i<10000;i++)
		{
			testtree1.insert(new Node<Integer>(Integer.toString(i),null));
		}
		endtime=System.currentTimeMillis();
		System.out.println("��BST�Ƚϣ�");
		System.out.println("���������ʱ�䣺"+(endtime-starttime)+"ms");
		starttime=System.currentTimeMillis();
		for(int i=0;i<10000;i++)
		{
			bsttree.BST_insert(new Node<Integer>(Integer.toString(i),null));
		}
		endtime=System.currentTimeMillis();
		System.out.println("BST����ʱ�䣺"+(endtime-starttime)+"ms");
		
		starttime=System.currentTimeMillis();
		for(int i=0;i<10000;i++)
		{
			testtree1.search(Integer.toString(i));
		}
		endtime=System.currentTimeMillis();
		System.out.println("���������ȫ����ʱ��"+(endtime-starttime)+"ms");
		starttime=System.currentTimeMillis();
		for(int i=0;i<10000;i++)
		{
			bsttree.search(Integer.toString(i));
		}
		endtime=System.currentTimeMillis();
		System.out.println("BST����ȫ����ʱ��"+(endtime-starttime)+"ms");

	}
	
	

}