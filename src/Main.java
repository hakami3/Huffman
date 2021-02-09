

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.DropMode;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class Main extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the frame.
	 */
	

	public static String filename = "";		//파일 이름
	public static String str= "";			//JEditorPane에 출력될 텍스트


	public Main() {
		setTitle("Text Compressor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 700);
		JEditorPane editorPane = new JEditorPane();
		editorPane.setEditable(false);
		editorPane.setDropMode(DropMode.INSERT);
		editorPane.getScrollableTracksViewportHeight();
		getContentPane().add(editorPane);
		JScrollPane scroll = new JScrollPane(editorPane);
		getContentPane().add(scroll);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);



		JMenu mnNewMenu = new JMenu("\uBA54\uB274");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("\uD30C\uC77C \uC5F4\uAE30");
		mnNewMenu.add(mntmNewMenuItem);
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser("C:\\Users\\SHIN\\Documents\\hufftxt");
				chooser.showDialog(null, "확인");
				FileReader read = null;
				int data = 0;
				try {
					if (chooser.getSelectedFile() == null)
						return;
					read = new FileReader(chooser.getSelectedFile().toString());
					
					while (true) {
						try {
							data = read.read();
							if (data == -1)
								break;
							else {
								char data1 = (char) data;
								str = str + data1;
							}
							
							
							
							
								
							
						
							
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				
				editorPane.setText(str);
				filename = chooser.getSelectedFile().getAbsolutePath();		//파일의 절대경로를 얻음
			}
		});



		JMenuItem mntmNewMenuItem_1 = new JMenuItem("\uC885\uB8CC");
		mnNewMenu.add(mntmNewMenuItem_1);
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			{
				System.exit(0);
			}
			}
		});

		
		
		JMenu mnNewMenu_1 = new JMenu("\uC555\uCD95");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("\uC555\uCD95 \uC2E4\uD589");
		mnNewMenu_1.add(mntmNewMenuItem_2);
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
						if (filename == "")
						return;
						System.out.println(filename);
						Encode.main(filename);
						JOptionPane.showMessageDialog(null, "압축 시간: " +Encode.totalTime + "초\n"+ "평균 비트수: "+Encode.averagebits
					    +"Bits\n"+"압축률: "+Encode.comprate+"%", "압축 완료!", JOptionPane.INFORMATION_MESSAGE);



				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("\uC555\uCD95 \uD574\uC81C");
		mnNewMenu_1.add(mntmNewMenuItem_3);
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				int data = 0;
				String str = "";
				long startTime = System.currentTimeMillis();
				Decode.main(null);
				@SuppressWarnings("resource")
				FileReader read = new FileReader("압축//압축파일.txt");
				while (true) {
					data = read.read();
					if (data == -1)
						break;
					else {
						char data1 = (char) data;
						str = str + data1;
					}
				}
				editorPane.setText(str);
				long endTime = System.currentTimeMillis();
				float totalTime = ((endTime - startTime) / 1000.0f);
				JOptionPane.showMessageDialog(null, "압축해제 시간: " +totalTime +" 초", "압축해제 완료!", JOptionPane.INFORMATION_MESSAGE);

				
			} catch (IOException e1) {
				e1.printStackTrace();
			
			}
		}
		});
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("\uD30C\uC77C \uC815\uBCF4");
		mnNewMenu_1.add(mntmNewMenuItem_4);
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "총 글자 수: "+str.length(), "파일 정보", JOptionPane.INFORMATION_MESSAGE);		//공백을 포함한 총 글자 수
			}
		});
	}
			

}
