package Skiplist;

import java.io.PrintStream;
import java.util.Random;

public class Skiplist
{
  private Skiplist nextlevel;
  private Skiplist nextnode;
  private int data;
  private static int level;

  public Skiplist(int data, Skiplist nextlevel, Skiplist nextnode)
  {
    this.data = data;
    this.nextlevel = nextlevel;
    this.nextnode = nextnode;
  }

  public static Skiplist makeNull()
  {
    level = 0;
    return new Skiplist(-1, null, new Skiplist(200, null, null));
  }

  public int search(int data, Skiplist top)
  {
    Skiplist p = top;
    int prelevel = level;
    while (true)
    {
      p = p.nextnode;

      while (p.nextnode.data > data)
      {
        if (p.data == data)
          return prelevel;
        if (p.nextlevel != null)
        {
          p = p.nextlevel;
          prelevel--;
        }
        else {
          return -1;
        }
      }
    }
  }

  public Skiplist delete(int data, Skiplist top) {
    Skiplist p = top;

    if (search(data, top) != -1)
    {
      while (p != null)
      {
        while (p.nextnode.data < data)
          p = p.nextnode;
        if (p.nextnode.data == data)
          p.nextnode = p.nextnode.nextnode;
        p = p.nextlevel;
      }
      while ((top.nextnode.nextnode == null) && (top.nextlevel != null))
      {
        top = top.nextlevel;
        level -= 1;
      }
      return top;
    }

    return null;
  }

  public Skiplist insert(int data, Skiplist top)
  {
    Random random = new Random();
    int insertlevel = 0;
    while (random.nextInt(2) == 1) {
      insertlevel++;
    }

    while (level < insertlevel)
    {
      top = new Skiplist(-1, top, new Skiplist(200, null, null));
      level += 1;
    }

    Skiplist p = top;
    Skiplist[] path = new Skiplist[level + 1];
    int i = level;
    while (i >= 0)
    {
      while (p.nextnode.data < data)
        p = p.nextnode;
      path[i] = p;
      p = p.nextlevel;
      i--;
    }

    i = 0;
    Skiplist q = null;
    while (i <= insertlevel)
    {
      Skiplist temp = q;
      q = new Skiplist(data, temp, path[i].nextnode);
      path[i].nextnode = q;
      i++;
    }

    return top;
  }

  public void printlist(Skiplist top)
  {
    int i = level;
    Skiplist p = top;
    while (i >= 0)
    {
      System.out.print("level " + i + ":");
      Skiplist q = p.nextnode;
      while ((q != null) && (q.nextnode != null))
      {
        System.out.print(q.data + " ");
        q = q.nextnode;
      }
      System.out.println();
      p = p.nextlevel;
      i--;
    }
  }
}