package hash;

import java.util.Random;

public class OpenList<V> {
	
	private OpenListNode<V>[] list;
	private long searchlength;
	
	public OpenList(int n)
	{
		this.list=new OpenListNode[n];
	}
	
	//求余法
		public void insert(OpenListNode<V> temp)
		{
			int index=temp.getData().getKey().hashCode()%list.length;
			if(list[index]==null)
				list[index]=temp;
			else
			{
				temp.setNext(list[index].getNext());
				list[index].setNext(temp.clone());
			}
		}
		
		public boolean delete(String str)
		{
			int index=str.hashCode()%list.length;
			OpenListNode<V> pre=null;
			OpenListNode<V> temp=list[index];
			while(temp!=null&&!temp.getData().getKey().equals(str))
			{
				pre=temp;
				temp=temp.getNext();
			}
				
			if(temp==null)
				return false;
			else
			{
				if(pre==null)
					list[index]=temp.getNext();
				else
					pre.setNext(temp.getNext());
				return true;
			}
		}
		
		public V search(String str)
		{
			searchlength++;
			int index=str.hashCode()%list.length;
			OpenListNode<V> temp=list[index];
			while(temp!=null&&!temp.getData().getKey().equals(str))
			{
				searchlength++;
				temp=temp.getNext();
			}
			if(temp!=null)
				return temp.getData().getValue();
			else
				return null;
		}
		
		public boolean modify(String str,V value)
		{
			int index=str.hashCode()%list.length;
			OpenListNode<V> temp=list[index];
			while(temp!=null&&!temp.getData().getKey().equals(str))
				temp=temp.getNext();
			if(temp!=null)
			{
				temp.getData().setValue(value);
				return true;
			}
			else
				return false;
		}
		
		//随机数法
		public void insert_random(OpenListNode<V> temp)
		{
			Random random=new Random(temp.getData().getKey().hashCode());
			int index=(int)(random.nextDouble()*list.length);
			if(list[index]==null)
				list[index]=temp;
			else
			{
				temp.setNext(list[index].getNext());
				list[index].setNext(temp.clone());
			}
		}
		
		public boolean delete_random(String str)
		{
			Random random=new Random(str.hashCode());
			int index=(int)(random.nextDouble()*list.length);
			OpenListNode<V> pre=null;
			OpenListNode<V> temp=list[index];
			while(temp!=null&&!temp.getData().getKey().equals(str))
			{
				pre=temp;
				temp=temp.getNext();
			}
				
			if(temp==null)
				return false;
			else
			{
				if(pre==null)
					list[index]=temp.getNext();
				else
					pre.setNext(temp.getNext());
				return true;
			}
		}
		
		public V search_random(String str)
		{
			Random random=new Random(str.hashCode());
			int index=(int)(random.nextDouble()*list.length);
			OpenListNode<V> temp=list[index];
			while(temp!=null&&!temp.getData().getKey().equals(str))
				temp=temp.getNext();
			if(temp!=null)
				return temp.getData().getValue();
			else
				return null;
		}
		
		public boolean modify_random(String str,V value)
		{
			Random random=new Random(str.hashCode());
			int index=(int)(random.nextDouble()*list.length);
			OpenListNode<V> temp=list[index];
			while(temp!=null&&!temp.getData().getKey().equals(str))
				temp=temp.getNext();
			if(temp!=null)
			{
				temp.getData().setValue(value);
				return true;
			}
			else
				return false;
		}
		
		public long getSearchlength()
		{
			return this.searchlength;
		}
}
