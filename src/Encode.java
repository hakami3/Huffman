import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class Encode {
	
	public static String freqFileName = "압축//데이터.txt";
	public static String outputFileName = "압축//압축파일.txt";
	public static float count = 0;

	public static float comprate;		//압축률
	public static float averagebits;	//평균비트수
	public static float totalTime;		//수행시간
	
	@SuppressWarnings("static-access")
	public static void main(String inputFileName) throws IOException
	{
		File newfile = new File("압축");		//압축 파일을 위한 폴더 생성
		if(!newfile.exists())
			newfile.mkdirs();
		
		long startTime = System.currentTimeMillis();
		
		Hufftree tree = new Hufftree();
		
		InputStream fin = new BufferedInputStream(new FileInputStream(inputFileName));
		tree.Freq(fin);		
		
		fin.close();

		
		DataOutputStream freqOut = new DataOutputStream(new FileOutputStream(freqFileName));
		tree.WriteFreq(freqOut);		//데이터테이블 작성
		
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
		File aftfile = new File("압축//압축파일.txt");
		float orisize = orifile.length();
		float aftsize = aftfile.length();
		comprate = (orisize-aftsize) / orisize*100;
		averagebits = (Totalcount/count);
		long endTime = System.currentTimeMillis();
		totalTime =  (( endTime - startTime )/1000.0f) ;
	}
}
