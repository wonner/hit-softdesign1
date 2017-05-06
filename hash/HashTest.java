package hash;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;




public class HashTest {

	public static void main(String[] args) throws IOException {
		
		FileReader fr=new FileReader("F://666/score1.txt"); //文件位置
		BufferedReader br=new BufferedReader(fr);
		String str;
		str=br.readLine();
		long starttime,endtime;
		
		ClosedList<Integer> hashlist1=new ClosedList<>((int)(Integer.parseInt(str)/0.75));//0.75为hash表填充因子
		ClosedList<Integer> hashlist2=new ClosedList<>((int)(Integer.parseInt(str)/0.75));
		OpenList<Integer> openhashlist1=new OpenList<>(Integer.parseInt(str));
		OpenList<Integer> openhashlist2=new OpenList<>(Integer.parseInt(str));
		
		while((str=br.readLine())!=null)
		{
			String[] stringlist=str.split(" ");
			DataNode<Integer> temp=new DataNode<Integer>(stringlist[0],Integer.parseInt(stringlist[1]));
			OpenListNode<Integer> tmp=new OpenListNode<Integer>(temp);
			hashlist1.insert(temp);
			hashlist2.insert_random(temp);
			openhashlist1.insert(tmp);
			openhashlist2.insert_random(tmp);
		}
		
		Scanner in = new Scanner(System.in);
		int ch;
	    do {
	      System.out.println("1.search");
	      System.out.println("2。insert");
	      System.out.println("3.delete");
	      System.out.println("4.modify");
	      System.out.println("0.exit");
	      ch = in.nextInt();
	      String[] stringlist;
	     

	      switch (ch)
	      {
	      case 1:
	        System.out.print("please enter the key:");
	        str= in.nextLine();
	        str= in.nextLine();
	        Integer value = hashlist1.search(str);
	        if (value != null) System.out.println("闭散列求余find it,the value is" + value); 
	        else
	          System.out.println("not found");
	        
	        value = hashlist2.search_random(str);
	        if (value != null) System.out.println("闭散列随机find it,the value is" + value); 
	        else
	          System.out.println("not found");
	        
	        value = openhashlist1.search(str);
	        if (value != null) System.out.println("开散列求余find it,the value is" + value); 
	        else
	          System.out.println("not found");
	        
	        value = openhashlist2.search_random(str);
	        if (value != null) System.out.println("开散列随机find it,the value is" + value); 
	        else
	          System.out.println("not found");
	        break;
	      case 2:
	        System.out.print("please enter the key and value:");
	        str= in.nextLine();
	        str= in.nextLine();
	        stringlist=str.split(" ");
	        DataNode<Integer> temp=new DataNode<Integer>(stringlist[0],Integer.parseInt(stringlist[1]));
	        OpenListNode<Integer> tmp=new OpenListNode<Integer>(temp);
	        hashlist1.insert(temp);
	        System.out.println("闭散列求余success");
	        hashlist2.insert_random(temp);
	        System.out.println("闭散列随机success");
	        openhashlist1.insert(tmp);
	        System.out.println("开散列求余success");
	        openhashlist2.insert_random(tmp);
	        System.out.println("开散列随机success");
	        
	        break;
	      case 3:
	        System.out.print("please enter the key");
	        str= in.nextLine();
	        str= in.nextLine();
	        if (hashlist1.delete(str))
	        {
	          System.out.println("闭散列求余success");
	        } else {
	          System.out.println("闭散列求余fail");
	        }
	        
	        if (hashlist2.delete_random(str))
	        {
	          System.out.println("闭散列随机success");
	        } else {
	          System.out.println("闭散列随机fail");
	        }
	        
	        if (openhashlist1.delete(str))
	        {
	          System.out.println("开散列求余success");
	        } else {
	          System.out.println("开散列求余fail");
	        }
	        
	        if (openhashlist2.delete_random(str))
	        {
	          System.out.println("开散列随机success");
	        } else {
	          System.out.println("开散列随机fail");
	        }
	        break;
	      case 4:
	    	  System.out.print("please enter the key and value:");
	    	  str= in.nextLine();
		      str= in.nextLine();
		      stringlist=str.split(" ");
		      if(hashlist1.modify(stringlist[0],Integer.parseInt(stringlist[1])))
		    	  System.out.println("闭散列求余success");
		      else
		    	  System.out.println("闭散列求余fail");
		      
		      if(hashlist2.modify_random(stringlist[0],Integer.parseInt(stringlist[1])))
		    	  System.out.println("闭散列随机success");
		      else
		    	  System.out.println("闭散列随机fail");
		      
		      if(openhashlist1.modify(stringlist[0],Integer.parseInt(stringlist[1])))
		    	  System.out.println("开散列求余success");
		      else
		    	  System.out.println("开散列求余fail");
		      
		      if(openhashlist2.modify_random(stringlist[0],Integer.parseInt(stringlist[1])))
		    	  System.out.println("开散列随机success");
		      else
		    	  System.out.println("开散列随机fail");
		      break;

	      }
	    }
	    while (ch != 0);
		
		//散列函数测试
	    File f=new File("F://666/testdata1.txt");
	    f.delete();
	    FileWriter fw = new FileWriter(f, true);
	    PrintWriter pw = new PrintWriter(fw);
	    
	    int n=100;
	    ClosedList<Integer> modhash=new ClosedList<>((int)(n/0.75));//0.75为hash表填充因子
		ClosedList<Integer> randomhash=new ClosedList<>((int)(n/0.75));
		OpenList<Integer> openmodhash=new OpenList<>(n);
		int data=1;
		for(int i=0;i<n;i++)
		{
			Random random=new Random();
	    	data=random.nextInt(n*10);
	    	pw.println(data);
		    pw.flush();
		    DataNode<Integer> temp=new DataNode<Integer>(Integer.toString(data),null);
		    OpenListNode<Integer> tmp=new OpenListNode<Integer>(temp);
			modhash.insert(temp);
			randomhash.insert_random(temp);
			openmodhash.insert(tmp);
			//data+=(int)(n/0.75);
		}
		pw.close();
		fr=new FileReader("F://666/testdata1.txt"); //文件位置
		br=new BufferedReader(fr);
		
		for(int i=0;i<n;i++)
		{
			str=br.readLine();
			modhash.search(str);
			randomhash.search_random(str);
			openmodhash.search(str);
		}
		System.out.println("求余法平均查找长度："+(1.0*modhash.getModlength()/n));
		System.out.println("随机法平均查找长度："+(1.0*randomhash.getRandomlength()/n));
		
		System.out.println("开散列表求余平均查找长度："+(1.0*openmodhash.getSearchlength()/n));

	}

}
