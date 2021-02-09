
public class Heap {
	@SuppressWarnings("rawtypes")
	private Comparable[] data;
	private int size;

	public Heap()
	{
		data = new Comparable[100];		
		size = 0;
	}

	public Heap(@SuppressWarnings("rawtypes") Comparable[] array) // array를 바로 힙에 넣음
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
			compareDown(i);     // 자식이 있는 노드중 가장 아래 노드부터 루트노드까지 내릴수 있는 만큼 내림
	}

	@SuppressWarnings("unchecked")
	private void compareDown(int index)  // 자식노드와 비교해 내려감
	{
		int child = index * 2; 
		if (child > size) // 자식노드가 없으면
			return;
		
		if (child != size && data[child].compareTo(data[child + 1]) > 0 ) // 오른쪽 자식노드의 노드값이 왼쪽보다 작으면 왼쪽 노드가 아닌 오른쪽 노드를 비교
			child++;											
		
		if (data[index].compareTo(data[child]) > 0) // 현재 노드가 비교하는 노드보다 작으면
		{
			swap(index, child); // 노드를 교환함
			compareDown(child); // 바꾼 노드에 대해서 반복
		}
		return;
	}

	@SuppressWarnings("unchecked")
	private void compareUp(int index) // 부모노드와 비교해 올라감
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
	private void swap(int index1, int index2) // 노드 두개값을 바꿈
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
	public Comparable removeMin() 	//힙에서 가장 작은 노드 반환
	{
		if (size < 1)
		{
			System.out.println("Error");
			System.exit(0);
		}
		Comparable minValue = data[1];
		data[1] = data[size--]; 
		compareDown(1); //노드를 내림
		return minValue;
	}

	public void insert(@SuppressWarnings("rawtypes") Comparable x)
	{
		if (++size == data.length) // 크기를 늘렸을때 현재 array보다 길다면
			Makedouble();		
		data[size] = x; 
		compareUp(size); // 노드를 올림
	}

	private void Makedouble() // array를 두배로만든다 
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
