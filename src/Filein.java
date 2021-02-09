import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Filein {
	private DataInputStream in;
	private int buffer;
	private int location;
	


	public Filein(InputStream is) throws IOException
	{
		location = 8;		//�ѹ���Ʈ�� 8��Ʈ�̹Ƿ� 
		in = new DataInputStream(is);
	}
	private int readBit() throws IOException		// ��Ʈ������ �����͸� ó���Ѵ�
	{
		if(location == 8)
		{
			buffer = in.read();
			if(buffer == -1)
				return -1;
			location = 0;
		}
		int result;
		
		if((buffer & (1<<location)) == 0)
		{
			result = 0;
			location++;
		}
		else
		{
			result = 1;
			location++;
		}
		return result;
	}
	@SuppressWarnings("static-access")
	public int readCode(Hufftree tree) throws IOException		//�ѹ���Ʈ �о��
	{
		String huffcode = "";
		int bit = readBit();
		int decoding;
		
		while(true)
		{
			huffcode = huffcode + bit;
			decoding = tree.ReturnChar(huffcode);
			if( bit == -1 || decoding==tree.ERROR)
			{
				System.exit(0);
			}
			else if (decoding == tree.NOTVALUE)
			{
				bit = readBit();
				continue;
			}
			else
				return decoding;
		}
	}

	public void close() throws IOException
	{
		in.close();
	}
}
