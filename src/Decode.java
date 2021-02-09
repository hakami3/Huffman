import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;




public class Decode {
	public static String inputFileName = Encode.outputFileName;
	public static String freqFileName = Encode.freqFileName;
	public static String outputFileName = "����//��������.txt";
	public static int decount = 0;
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws IOException
	{
		
		Hufftree tree = new Hufftree();
		
		DataInputStream freqIn = new DataInputStream(new FileInputStream(freqFileName));
		tree.ReadFreq(freqIn);		//���������̺��� �о��
		
		freqIn.close();
		
		Filein fin = new Filein(new FileInputStream(inputFileName));
		OutputStream fout = new BufferedOutputStream(new FileOutputStream(outputFileName));	
		
		int currentChar = fin.readCode(tree);
		decount++;
		
		while(currentChar != tree.EOF)		//������ �ڵ��� ���϶�����
		{
			fout.write(currentChar);				//���ڷ� ���
			currentChar = fin.readCode(tree);
			decount++;
		}
		
		fin.close();

		fout.close();		
	}
}
