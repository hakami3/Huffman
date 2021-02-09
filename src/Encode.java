import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class Encode {
	
	public static String freqFileName = "����//������.txt";
	public static String outputFileName = "����//��������.txt";
	public static float count = 0;

	public static float comprate;		//�����
	public static float averagebits;	//��պ�Ʈ��
	public static float totalTime;		//����ð�
	
	@SuppressWarnings("static-access")
	public static void main(String inputFileName) throws IOException
	{
		File newfile = new File("����");		//���� ������ ���� ���� ����
		if(!newfile.exists())
			newfile.mkdirs();
		
		long startTime = System.currentTimeMillis();
		
		Hufftree tree = new Hufftree();
		
		InputStream fin = new BufferedInputStream(new FileInputStream(inputFileName));
		tree.Freq(fin);		
		
		fin.close();

		
		DataOutputStream freqOut = new DataOutputStream(new FileOutputStream(freqFileName));
		tree.WriteFreq(freqOut);		//���������̺� �ۼ�
		
		freqOut.close();
		
		fin = new BufferedInputStream(new FileInputStream(inputFileName));
        Fileout fout = new Fileout(new FileOutputStream(outputFileName));

        
        int Totalcount = 0;
        int buf = fin.read();
		count ++;
		String code = "";
		
		while(buf != -1)
		{
			code = tree.Huffcode(buf);
			Totalcount += code.length();
			fout.writeCode(code);
			buf=fin.read();
			count++;
		}
		fout.writeCode(tree.Huffcode(tree.EOF));
		fout.close();
		freqOut.close();
		
		File orifile = new File(inputFileName);
		File aftfile = new File("����//��������.txt");
		float orisize = orifile.length();
		float aftsize = aftfile.length();
		comprate = (orisize-aftsize) / orisize*100;
		averagebits = (Totalcount/count);
		long endTime = System.currentTimeMillis();
		totalTime =  (( endTime - startTime )/1000.0f) ;
	}
}
