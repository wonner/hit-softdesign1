package priorqueue;

public class PriorQueue
{
  private int level;
  private PriorQueue next;

  public PriorQueue(int level, PriorQueue next)
  {
    this.level = level;
    this.next = next;
  }

  public static PriorQueue makeNull()
  {
    return new PriorQueue(-1, null);
  }

  public static void insert(int level, PriorQueue head)
  {
    PriorQueue p = head;
    while ((p.next != null) && (p.next.level > level))
      p = p.next;
    p.next = new PriorQueue(level, p.next);
  }

  public static int delete(PriorQueue head)
  {
    if (head.next != null)
    {
      int level = head.next.level;
      head.next = head.next.next;
      return level;
    }
    return -1;
  }

  public static int search(PriorQueue head)
  {
    if (head.next != null) {
      return head.next.level;
    }
    return -1;
  }
}