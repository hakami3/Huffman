import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Fileout {
    private DataOutputStream out;
    private int buffer;
    private int location;


	public Fileout(OutputStream os) throws IOException
	{
		buffer = 0;
		location = 0;
		out = new DataOutputStream(os);
	}
	private void writeBit(char c) throws IOException
	{
		if(c == '1')
		{
			buffer = buffer | (1<<location); // �Ѻ�Ʈ�� ����Ʈ �������� ���ۿ� ����
			location++;
		}
		else if( c == '0')
			location++;
		else if( c != '0')
			return;
		
		if(location == 8)		//8��Ʈ�� ä��������
			flush();
	}
	public void writeCode(String code) throws IOException
	{
		for(int i=0;i<code.length();i++)
			writeBit(code.charAt(i)); 
	}
	public void flush() throws IOException
	{
		if(location == 0)
			return;
		out.write(buffer);    // 8��Ʈ�� ä�������� �ѹ���Ʈ ���
		location = 0;
		buffer = 0;
	}
	public void close() throws IOException
	{
		flush();
		out.close();
	}
}
