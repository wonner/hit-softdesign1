package priorqueue;

import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

public class PriorQueueTest
{
  public static void main(String[] args)
  {
    PriorQueue head = PriorQueue.makeNull();
    BinaryHeap bheap = new BinaryHeap();
    Scanner in = new Scanner(System.in);

    Random rand = new Random();

    for (int i = 0; i < 10; i++)
    {
      int randNum = rand.nextInt(100);

      PriorQueue.insert(randNum, head);
      bheap.insert(randNum);
    }

    int ch;
    do
    {
      System.out.println("1.search");
      System.out.println("2。insert");
      System.out.println("3.delete");
      System.out.println("0.exit");
      ch = in.nextInt();

      switch (ch)
      {
      case 1:
        int level = PriorQueue.search(head);
        if (level == -1) System.out.println("fail"); else {
          System.out.println("顺序链表：the highest level is" + level);
        }
        level = bheap.search();
        if (level == -1) System.out.println("fail"); else
          System.out.println("二叉堆：the highest level is" + level);
        break;
      case 2:
        System.out.print("please enter the priority:");
        int level = in.nextInt();

        long starttime = System.nanoTime();
        PriorQueue.insert(level, head);
        long endtime = System.nanoTime();
        System.out.println("顺序链式存储结构运行时间为：" + (endtime - starttime) + "ns");

        starttime = System.nanoTime();
        bheap.insert(level);
        endtime = System.nanoTime();
        System.out.println("二叉堆存储结构运行时间为：" + (endtime - starttime) + "ns");
        break;
      case 3:
        long starttime = System.nanoTime();
        int level = PriorQueue.delete(head);
        long endtime = System.nanoTime();
        if (level == -1)
          System.out.println("fail");
        else {
          System.out.println("success,delete level is " + level + ",顺序链式存储结构运行时间为：" + (endtime - starttime) + "ns");
        }
        starttime = System.nanoTime();
        level = bheap.delete();
        endtime = System.nanoTime();
        if (level == -1)
          System.out.println("fail");
        else
          System.out.println("success,delete level is " + level + ",二叉堆存储结构运行时间为：" + (endtime - starttime) + "ns");
      }
    }
    while (ch != 0);
  }
}