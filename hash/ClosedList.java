package hash;

import java.util.Random;

public class ClosedList<V> {
	
	private DataNode<V>[] list;
	private long modsearchlength;
	private long randomsearchlength;
	
	public ClosedList(int n)
	{
		this.list=new DataNode[n];
		for(int i=0;i<n;i++)
			list[i]=new DataNode(null,null);
	}
	
	//求余法
	public void insert(DataNode<V> temp)
	{
		int index=temp.getKey().hashCode()%list.length;
		while(list[index].getKey()!=null)  //线性探测法
		{
			index=(index+1)%list.length;
		}
		list[index]=temp.clone();
	}
	
	public boolean delete(String str)
	{
		int index=str.hashCode()%list.length;
		int p=index;
		while(true)
		{
			while(list[index].getKey()==null)
			{
				index=(index+1)%list.length;
				if(index==p)
					return false;
			}
			if(list[index].getKey().equals(str))
				break;
			else
			{
				index=(index+1)%list.length;
				if(index==p)
					return false;
			}
		}
		list[index].setKey(null);
		return true;
	}
	
	public V search(String str)
	{
		int index=str.hashCode()%list.length;
		int p=index;
		this.modsearchlength++;
		while(true)
		{
			while(list[index].getKey()==null)
			{
				index=(index+1)%list.length;
				this.modsearchlength++;
				if(index==p)
					return null;
			}
			if(list[index].getKey().equals(str))
				break;
			else
			{
				index=(index+1)%list.length;
				this.modsearchlength++;
				if(index==p)
					return null;
			}
		}
		return list[index].getValue();
	}
	
	public boolean modify(String str,V value)
	{
		int index=str.hashCode()%list.length;
		int p=index;
		while(true)
		{
			while(list[index].getKey()==null)
			{
				index=(index+1)%list.length;
				if(index==p)
					return false;
			}
			if(list[index].getKey().equals(str))
				break;
			else
			{
				index=(index+1)%list.length;
				if(index==p)
					return false;
			}
		}
		list[index].setValue(value);
		return true;
	}
	
	//随机数法
	public void insert_random(DataNode<V> temp)
	{
		Random random=new Random(temp.getKey().hashCode());
		int index=(int)(random.nextDouble()*list.length);
		while(list[index].getKey()!=null)  //线性探测法
		{
			index=(index+1)%list.length;
		}
		list[index]=temp.clone();
	}
	
	public boolean delete_random(String str)
	{
		Random random=new Random(str.hashCode());
		int index=(int)(random.nextDouble()*list.length);
		int p=index;
		while(true)
		{
			while(list[index].getKey()==null)
			{
				index=(index+1)%list.length;
				if(index==p)
					return false;
			}
			if(list[index].getKey().equals(str))
				break;
			else
			{
				index=(index+1)%list.length;
				if(index==p)
					return false;
			}
		}
		list[index].setKey(null);
		return true;
	}
	
	public V search_random(String str)
	{
		Random random=new Random(str.hashCode());
		int index=(int)(random.nextDouble()*list.length);
		int p=index;
		this.randomsearchlength++;
		while(true)
		{
			while(list[index].getKey()==null)
			{
				index=(index+1)%list.length;
				this.randomsearchlength++;
				if(index==p)
					return null;
			}
			if(list[index].getKey().equals(str))
				break;
			else
			{
				index=(index+1)%list.length;
				this.randomsearchlength++;
				if(index==p)
					return null;
			}
		}
		return list[index].getValue();
	}
	
	public boolean modify_random(String str,V value)
	{
		Random random=new Random(str.hashCode());
		int index=(int)(random.nextDouble()*list.length);
		int p=index;
		while(true)
		{
			while(list[index].getKey()==null)
			{
				index=(index+1)%list.length;
				if(index==p)
					return false;
			}
			if(list[index].getKey().equals(str))
				break;
			else
			{
				index=(index+1)%list.length;
				if(index==p)
					return false;
			}
		}
		list[index].setValue(value);
		return true;
	}
	
	public long getModlength()
	{
		return this.modsearchlength;
	}
	
	public long getRandomlength()
	{
		return this.randomsearchlength;
	}


}
