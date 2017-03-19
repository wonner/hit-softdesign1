package priorqueue;

public class BinaryHeap
{
  private int[] heap = new int[2000];
  private int n;

  public boolean heapFull()
  {
    return this.n == 1999;
  }

  public void insert(int level)
  {
    if (!heapFull())
    {
      int i = this.n + 1;
      while ((i != 1) && (level > this.heap[(i / 2)]))
      {
        this.heap[i] = this.heap[(i / 2)];
        i /= 2;
      }
      this.heap[i] = level;
      this.n += 1;
    }
  }

  public int delete()
  {
    int parent = 1; int child = 2;

    if (this.n != 0)
    {
      int level = this.heap[1];
      int tmp = this.heap[(this.n--)];
      while (child < this.n)
      {
        if ((child < this.n) && (this.heap[child] < this.heap[(child + 1)]))
          child++;
        if (tmp > this.heap[child]) break;
        this.heap[parent] = this.heap[child];
        parent = child;
        child *= 2;
      }
      this.heap[parent] = tmp;
      return level;
    }
    return -1;
  }

  public int search()
  {
    if (this.n == 0) {
      return -1;
    }
    return this.heap[1];
  }
}