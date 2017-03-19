package kmp;

import java.io.PrintStream;
import java.util.Scanner;

public class KmpTest
{
  public static void main(String[] args)
  {
    System.out.print("please enter the main string:");
    Scanner in = new Scanner(System.in);
    String mainString = in.next();
    char[] mainstring = mainString.toCharArray();
    System.out.print("please enter the substring:");
    String subString = in.next();
    char[] substring = subString.toCharArray();

    long starttime = System.nanoTime();
    int index = naiveMatcher(mainstring, substring);
    long endtime = System.nanoTime();
    if (index == -1)
      System.out.println("naive-string-matcher match fail");
    else {
      System.out.println("success,the position is " + index + ",朴素算法运行时间：" + (endtime - starttime) + "ns");
    }

    starttime = System.nanoTime();
    index = RKMatcher(mainString, subString);
    endtime = System.nanoTime();
    if (index == -1)
      System.out.println("rabin-karp match fail");
    else {
      System.out.println("success,the position is " + index + ",Rabin-Krap算法运行时间：" + (endtime - starttime) + "ns");
    }

    starttime = System.nanoTime();
    int[] next = getNext(substring);
    index = indexKmp(mainstring, substring, next);
    endtime = System.nanoTime();
    if (index == -1)
      System.out.println("kmp match fail");
    else
      System.out.println("success,the position is " + index + ",KMP算法运行时间：" + (endtime - starttime) + "ns");
  }

  public static int naiveMatcher(char[] mainstring, char[] substring)
  {
    for (int i = 0; i < mainstring.length - substring.length + 1; i++)
    {
      for (int j = 0; j < substring.length; j++)
      {
        if (mainstring[(i + j)] != substring[j]) break;
        if (j == substring.length - 1) return i;
      }
    }
    return -1;
  }

  public static int RKMatcher(String mainString, String subString)
  {
    long subhash = subString.hashCode();
    long mainhash = mainString.substring(0, 0 + subString.length()).hashCode();
    char[] mainstring = mainString.toCharArray();
    for (int i = 0; i < mainString.length() - subString.length() + 1; i++)
    {
      if (subhash == mainhash)
      {
        if (subString.equals(mainString.substring(i, i + subString.length())))
          return i;
      }
      if (i + subString.length() >= mainString.length())
        continue;
      mainhash = mainhash * 31L - mainstring[i] * ()Math.pow(31.0D, subString.length()) + mainstring[(i + subString.length())];
    }

    return -1;
  }

  public static int[] getNext(char[] substring)
  {
    int[] next = new int[substring.length];
    int i = 0; int j = -1;
    next[0] = -1;
    while (i < substring.length - 1)
    {
      while ((j > -1) && (substring[i] != substring[j]))
        j = next[j];
      i++; j++;
      if (substring[i] == substring[j]) next[i] = next[j]; else
        next[i] = j;
    }
    return next;
  }

  public static int indexKmp(char[] mainstring, char[] substring, int[] next)
  {
    int i = 0; int j = 0;
    while ((i < mainstring.length) && (j < substring.length))
    {
      if ((j == -1) || (mainstring[i] == substring[j]))
      {
        i++; j++;
      } else {
        j = next[j];
      }
    }
    if (j > substring.length - 1) {
      return i - substring.length;
    }
    return -1;
  }
}