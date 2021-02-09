
public class Heap {
	@SuppressWarnings("rawtypes")
	private Comparable[] data;
	private int size;

	public Heap()
	{
		data = new Comparable[100];		
		size = 0;
	}

	public Heap(@SuppressWarnings("rawtypes") Comparable[] array) // array�� �ٷ� ���� ����
	{
		size = array.length; 
		data = new Comparable[array.length + 1]; 
		for (int i = 0; i < array.length; i++)
			data[i + 1] = array[i]; 
		buildHeap(); 
	}

	private void buildHeap() 
	{
		for (int i = size / 2; i > 0; i--)
			compareDown(i);     // �ڽ��� �ִ� ����� ���� �Ʒ� ������ ��Ʈ������ ������ �ִ� ��ŭ ����
	}

	@SuppressWarnings("unchecked")
	private void compareDown(int index)  // �ڽĳ��� ���� ������
	{
		int child = index * 2; 
		if (child > size) // �ڽĳ�尡 ������
			return;
		
		if (child != size && data[child].compareTo(data[child + 1]) > 0 ) // ������ �ڽĳ���� ��尪�� ���ʺ��� ������ ���� ��尡 �ƴ� ������ ��带 ��
			child++;											
		
		if (data[index].compareTo(data[child]) > 0) // ���� ��尡 ���ϴ� ��庸�� ������
		{
			swap(index, child); // ��带 ��ȯ��
			compareDown(child); // �ٲ� ��忡 ���ؼ� �ݺ�
		}
		return;
	}

	@SuppressWarnings("unchecked")
	private void compareUp(int index) // �θ���� ���� �ö�
	{
		int parent = index/2;
		
		if(parent < 1)
			return;
		if(data[index].compareTo(data[parent]) < 0)
		{
			swap(index,parent);
			compareUp(parent);
		}
	}

	@SuppressWarnings("rawtypes")
	private void swap(int index1, int index2) // ��� �ΰ����� �ٲ�
	{
		if (index1 > size || index2 > size)
		{
			System.out.println("Error");
			System.exit(0);
		} 
		else
		{
			Comparable temp;
			temp = data[index1];
			data[index1] = data[index2];
			data[index2] = temp;
		}
	}

	
	@SuppressWarnings("rawtypes")
	public Comparable removeMin() 	//������ ���� ���� ��� ��ȯ
	{
		if (size < 1)
		{
			System.out.println("Error");
			System.exit(0);
		}
		Comparable minValue = data[1];
		data[1] = data[size--]; 
		compareDown(1); //��带 ����
		return minValue;
	}

	public void insert(@SuppressWarnings("rawtypes") Comparable x)
	{
		if (++size == data.length) // ũ�⸦ �÷����� ���� array���� ��ٸ�
			Makedouble();		
		data[size] = x; 
		compareUp(size); // ��带 �ø�
	}

	private void Makedouble() // array�� �ι�θ���� 
	{
		@SuppressWarnings("rawtypes")
		Comparable[] newArray = new Comparable[data.length * 2];
		for (int i = 0; i < data.length; i++)
			newArray[i] = data[i];
		data = newArray;
	}

	public boolean Empty() 
	{
		return (size == 0);
	}
}
