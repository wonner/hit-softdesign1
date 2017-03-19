package Skiplist;

import java.io.PrintStream;
import java.util.Scanner;

public class SkiplistTest
{
  public static void main(String[] args)
  {
    Skiplist list = Skiplist.makeNull();

    Scanner in = new Scanner(System.in);
    System.out.print("please enter the numbers:");
    String inputString = in.next();
    String[] stringArray = inputString.split(",");
    for (int i = 0; i < stringArray.length; i++)
    {
      list = list.insert(Integer.parseInt(stringArray[i]), list);
    }int ch;
    do {
      System.out.println("1.search");
      System.out.println("2。insert");
      System.out.println("3.delete");
      System.out.println("4.print the skiplist");
      System.out.println("0.exit");
      ch = in.nextInt();

      switch (ch)
      {
      case 1:
        System.out.print("please enter the number:");
        int data = in.nextInt();
        int prelevel = list.search(data, list);
        if (prelevel != -1) System.out.println("find it,the highest level is" + prelevel); else
          System.out.println("not found");
        break;
      case 2:
        System.out.print("please enter the number:");
        int data = in.nextInt();
        list = list.insert(data, list);
        break;
      case 3:
        System.out.print("please enter the number:");
        int data = in.nextInt();
        Skiplist top = list.delete(data, list);
        if (top != null)
        {
          list = top;
          System.out.println("success");
        } else {
          System.out.println("fail");
        }break;
      case 4:
        list.printlist(list);
      }
    }
    while (ch != 0);
  }
}