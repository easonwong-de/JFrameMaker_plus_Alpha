import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.util.TreeMap;

import javax.swing.*;

public class fm {
	static JFrame frm;
	static JPanel Title,Tools,conPanel,setPanel,codPanel;
	static JDesktopPane desk;
	static JInternalFrame f;
	static TreeMap<String,com> com;
	static JComboBox<String> set1;
	static JTextArea cod1;
	static com comPointer;
	static JTextField[] SET;
	fm(){
		com=new TreeMap<String,com>();//僅提供查詢組件的功能
		comPointer=null;
		//============================
		frm=new JFrame();
		Insets screenInsets=Toolkit.getDefaultToolkit().getScreenInsets(frm.getGraphicsConfiguration());
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension sc=kit.getScreenSize();
		frm.setBounds(0,0,sc.width,sc.height-screenInsets.bottom);
		frm.setResizable(false);
		frm.setTitle("JFrameMaker++ 正體版 - 內測版1.0.1");
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setLayout(null);
		frm.setUndecorated(true);
		//============================
		Title=new JPanel();
		Title.setBounds(0,0,frm.getWidth(),45);
		frm.add(Title);
		Title.setBackground(new Color(245,245,245));//標題欄顏色
		Title.setLayout(null);
		//============================
		JLabel title=new JLabel("JFrameMaker++ 正體版 - 內測版1.0.1");
		title.setFont(new Font("宋体",0,20));
		title.setBounds(10,5,380,30);
		Title.add(title);
		//============================
		conPanel=new JPanel();
		conPanel.setLayout(new GridLayout(20,1,5,5));
		String[] comp=new String[]{"JButton按鈕","JLabel標籤","JTextField文本框","JTextArea文本域","JCheckBox複選欄"};
		final JComboBox<String> con1=new JComboBox<String>(comp);
		conPanel.add(con1);
		conPanel.add(new JLabel("組件名稱"));
		final JTextField con2=new JTextField();
		conPanel.add(con2);
		final JButton con3=new JButton("添加組件");
		con3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(!com.containsKey(con2.getText())|con2.getText()=="jf"){
					if(con2.getText().length()!=0){
						switch(con1.getSelectedIndex()){
						case 0:com.put(con2.getText(), new com("JButton",con2.getText()));
						set1.addItem("JButton "+con2.getText());break;
						case 1:com.put(con2.getText(), new com("JLabel",con2.getText()));
						set1.addItem("JLabel "+con2.getText());break;
						case 2:com.put(con2.getText(), new com("JTextField",con2.getText()));
						set1.addItem("JTextField "+con2.getText());break;
						case 3:com.put(con2.getText(), new com("JTextArea",con2.getText()));
						set1.addItem("JTextArea "+con2.getText());break;
						case 4:com.put(con2.getText(), new com("JCheckBox",con2.getText()));
						set1.addItem("JCheckBox "+con2.getText());break;
						}
						con2.setText(null);
					}else{
						Object[] options = {"   好的   "};
						JOptionPane.showOptionDialog(null,
						"組件的名字不可為空",
						"訊息",JOptionPane.ERROR_MESSAGE,JOptionPane.ERROR_MESSAGE,null,options,options[0]);
					}
				}else{
					Object[] options = {"   好的   "};
					JOptionPane.showOptionDialog(null,
					"組件的名稱有重複",
					"訊息",JOptionPane.ERROR_MESSAGE,JOptionPane.ERROR_MESSAGE,null,options,options[0]);
				}
			}
		});
		conPanel.add(con3);
		conPanel.add(new JLabel());
		final JLabel con4=new JLabel("=======單擊此處以獲取更多資訊=======",JLabel.CENTER);
		con4.setFont(new Font("Serif",0,15));con4.setCursor(new Cursor(Cursor.HAND_CURSOR));
		conPanel.add(con4);
		conPanel.add(new JLabel("Copyright © 2015-2016(民國百十四-民國百十五)",JLabel.CENTER));
		conPanel.add(new JLabel("WangYixin's Original Software.",JLabel.CENTER));
		conPanel.add(new JLabel("All rights reserved.",JLabel.CENTER));
		con4.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				try{
					URI uri=new URI("http://user.qzone.qq.com/2826509864/main");
					Desktop dtp=Desktop.getDesktop();
					if(Desktop.isDesktopSupported()&&dtp.isSupported(Desktop.Action.BROWSE)){
						dtp.browse(uri);
					}else{
						Object[] options = {"好的"};
						JOptionPane.showOptionDialog(null,
						"訪問失敗",
						"錯誤",JOptionPane.ERROR_MESSAGE,JOptionPane.ERROR_MESSAGE,
						null,options,options[0]);
					}
				}catch(Exception E){
					Object[] options = {"好的"};
					JOptionPane.showOptionDialog(null,
					"訪問失敗",
					"錯誤",JOptionPane.ERROR_MESSAGE,JOptionPane.ERROR_MESSAGE,
					null,options,options[0]);
				}
			}
			public void mouseEntered(MouseEvent e) {
				con4.setForeground(Color.RED);
			}
		    public void mouseExited(MouseEvent e) {
		    	con4.setForeground(Color.BLACK);
		    }
		});
		//============================
		setPanel=new JPanel();
		setPanel.setLayout(new GridLayout(24,1,0,0));
		SET=new JTextField[11];
		set1=new JComboBox<String>();
		set1.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){
				if(e.getStateChange() == ItemEvent.SELECTED){
					if(set1.getSelectedItem().toString()!=null){
						comPointer=com.get(set1.getSelectedItem().toString().split(" ")[1]);
						String[] str=comPointer.get();
						for(int i=0;i<SET.length;i++){
					    	SET[i].setText(str[i]);
					    	SET[i].setEnabled(true);
					    }
					}
				}
			}
		});
		setPanel.add(set1);
		String[] SETstr="文本 橫坐標 縱坐標 寬 高 字體名稱 字體風格 字號 紅 綠 藍".split(" ");
	    for(int i=0;i<SET.length;i++){
	    	SET[i]=new JTextField();
	    	SET[i].setEnabled(false);
	    	setPanel.add(new JLabel(SETstr[i]));
			setPanel.add(SET[i]);
	    }
	   SET[0].addKeyListener(new KeyAdapter(){
		    public void keyReleased(KeyEvent e) {
		    	comPointer.setText(SET[0].getText());
		    }
	    });
	   SET[1].addKeyListener(new KeyAdapter(){
	    	public void keyTyped(KeyEvent e) {
	    		int keyChar=e.getKeyChar();	
				if(keyChar>=KeyEvent.VK_0&&keyChar<=KeyEvent.VK_9||keyChar=='\b'){
				}else{e.consume();}
	    	}
			public void keyReleased(KeyEvent e) {
				int keyChar=e.getKeyChar();	
				if(keyChar>=KeyEvent.VK_0&&keyChar<=KeyEvent.VK_9||keyChar=='\b'){
					int X=new Integer("0"+SET[1].getText());
					if(X>f.getWidth()-40){
						SET[1].setText(""+(f.getWidth()-40));
						X=f.getWidth()-40;
					}
					comPointer.c.setLocation(X,comPointer.c.getY());
				}else{e.consume();}
			}
		});
	   SET[2].addKeyListener(new KeyAdapter(){
	    	public void keyTyped(KeyEvent e) {
	    		int keyChar=e.getKeyChar();	
				if(keyChar>=KeyEvent.VK_0&&keyChar<=KeyEvent.VK_9||keyChar=='\b'){
				}else{e.consume();}
	    	}
			public void keyReleased(KeyEvent e) {
				int keyChar=e.getKeyChar();	
				if(keyChar>=KeyEvent.VK_0&&keyChar<=KeyEvent.VK_9||keyChar=='\b'){
					int Y=new Integer("0"+SET[2].getText());
					if(Y>f.getHeight()-40){
						SET[2].setText(""+(f.getHeight()-40));
						Y=f.getHeight()-40;
					}
					comPointer.c.setLocation(comPointer.c.getX(),Y);
				}else{e.consume();}
			}
		});
	   SET[3].addKeyListener(new KeyAdapter(){
	    	public void keyTyped(KeyEvent e) {
	    		int keyChar=e.getKeyChar();	
				if(keyChar>=KeyEvent.VK_0&&keyChar<=KeyEvent.VK_9||keyChar=='\b'){
				}else{e.consume();}
	    	}
			public void keyReleased(KeyEvent e) {
				int keyChar=e.getKeyChar();	
				if(keyChar>=KeyEvent.VK_0&&keyChar<=KeyEvent.VK_9||keyChar=='\b'){
					int W=new Integer("0"+SET[3].getText());
					if(W<6){
						W=6;
					}
					comPointer.c.setSize(W, comPointer.c.getHeight());
				}else{e.consume();}
			}
		});
	   SET[4].addKeyListener(new KeyAdapter(){
	    	public void keyTyped(KeyEvent e) {
	    		int keyChar=e.getKeyChar();	
				if(keyChar>=KeyEvent.VK_0&&keyChar<=KeyEvent.VK_9||keyChar=='\b'){
				}else{e.consume();}
	    	}
			public void keyReleased(KeyEvent e) {
				int keyChar=e.getKeyChar();	
				if(keyChar>=KeyEvent.VK_0&&keyChar<=KeyEvent.VK_9||keyChar=='\b'){
					int H=new Integer("0"+SET[4].getText());
					if(H<6){
						H=6;
					}
					comPointer.c.setSize(comPointer.c.getWidth(),H);
				}else{e.consume();}
			}
		});
	   SET[5].addKeyListener(new KeyAdapter(){
		    public void keyReleased(KeyEvent e) {
		    	comPointer.c.setFont(new Font(SET[5].getText(),
		    			comPointer.c.getFont().getStyle(),comPointer.c.getFont().getSize()));
		    }
	    });
	   SET[6].addKeyListener(new KeyAdapter(){
	    	public void keyTyped(KeyEvent e) {
	    		int keyChar=e.getKeyChar();	
				if(keyChar>=KeyEvent.VK_0&&keyChar<=KeyEvent.VK_9||keyChar=='\b'){
				}else{e.consume();}
	    	}
			public void keyReleased(KeyEvent e) {
				int keyChar=e.getKeyChar();	
				if(keyChar>=KeyEvent.VK_0&&keyChar<=KeyEvent.VK_9||keyChar=='\b'){
					comPointer.c.setFont(new Font(comPointer.c.getFont().getFontName(),
							new Integer("0"+SET[6].getText()),comPointer.c.getFont().getSize()));
				}else{e.consume();}
			}
		});
	   SET[7].addKeyListener(new KeyAdapter(){
	    	public void keyTyped(KeyEvent e) {
	    		int keyChar=e.getKeyChar();	
				if(keyChar>=KeyEvent.VK_0&&keyChar<=KeyEvent.VK_9||keyChar=='\b'){
				}else{e.consume();}
	    	}
			public void keyReleased(KeyEvent e) {
				int keyChar=e.getKeyChar();	
				if(keyChar>=KeyEvent.VK_0&&keyChar<=KeyEvent.VK_9||keyChar=='\b'){
					comPointer.c.setFont(new Font(comPointer.c.getFont().getFontName(),
							comPointer.c.getFont().getStyle(),new Integer("0"+SET[7].getText())));
				}else{e.consume();}
			}
		});
	   SET[8].addKeyListener(new KeyAdapter(){
	    	public void keyTyped(KeyEvent e) {
	    		int keyChar=e.getKeyChar();	
				if(keyChar>=KeyEvent.VK_0&&keyChar<=KeyEvent.VK_9||keyChar=='\b'){
				}else{e.consume();}
	    	}
			public void keyReleased(KeyEvent e) {
				int keyChar=e.getKeyChar();	
				if(keyChar>=KeyEvent.VK_0&&keyChar<=KeyEvent.VK_9||keyChar=='\b'){
					int Red=new Integer("0"+SET[8].getText());
					if(Red>=255){
						SET[8].setText("255");
						Red=255;
					}
					int Blue=new Integer("0"+SET[9].getText());
					int Green=new Integer("0"+SET[10].getText());
					comPointer.c.setForeground(new Color(Red,Blue,Green));
				}else{e.consume();}
			}
		});
	   SET[9].addKeyListener(new KeyAdapter(){
	    	public void keyTyped(KeyEvent e) {
	    		int keyChar=e.getKeyChar();	
				if(keyChar>=KeyEvent.VK_0&&keyChar<=KeyEvent.VK_9||keyChar=='\b'){
				}else{e.consume();}
	    	}
			public void keyReleased(KeyEvent e) {
				int keyChar=e.getKeyChar();	
				if(keyChar>=KeyEvent.VK_0&&keyChar<=KeyEvent.VK_9||keyChar=='\b'){
					int Blue=new Integer("0"+SET[9].getText());
					if(Blue>=255){
						SET[9].setText("255");
						Blue=255;
					}
					int Red=new Integer("0"+SET[8].getText());
					int Green=new Integer("0"+SET[10].getText());
					comPointer.c.setForeground(new Color(Red,Blue,Green));
				}else{e.consume();}
			}
		});
	   SET[10].addKeyListener(new KeyAdapter(){
	    	public void keyTyped(KeyEvent e) {
	    		int keyChar=e.getKeyChar();	
				if(keyChar>=KeyEvent.VK_0&&keyChar<=KeyEvent.VK_9||keyChar=='\b'){
				}else{e.consume();}
	    	}
			public void keyReleased(KeyEvent e) {
				int keyChar=e.getKeyChar();	
				if(keyChar>=KeyEvent.VK_0&&keyChar<=KeyEvent.VK_9||keyChar=='\b'){
					int Green=new Integer("0"+SET[10].getText());
					if(Green>=255){
						SET[10].setText("255");
						Green=255;
					}
					int Red=new Integer("0"+SET[8].getText());
					int Blue=new Integer("0"+SET[9].getText());
					comPointer.c.setForeground(new Color(Red,Blue,Green));
				}else{e.consume();}
			}
		});
		//============================
		codPanel=new JPanel();
		codPanel.setLayout(null);
		JPanel cod=new JPanel();
		cod.setLayout(new GridLayout(1,1));
		cod.setBounds(5,5,290,frm.getHeight()-130);
		cod1=new JTextArea();
		cod1.setEditable(false);
		cod1.setFont(new Font("DialogInput",0,13));
		JScrollPane Sp=new JScrollPane(cod1);
		cod.add(Sp);
		codPanel.add(cod);
		JButton cod2=new JButton("拷貝代碼");
		cod2.setBounds(5,frm.getHeight()-120, 290, 30);
		codPanel.add(cod2);
		cod2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				StringSelection stsel = new StringSelection(cod1.getText().replaceAll("    ", "	"));
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stsel, stsel);
				Object[] options = {"   好的   "};
				JOptionPane.showOptionDialog(null,
				"已拷貝到您的剪貼板",
				"訊息",JOptionPane.INFORMATION_MESSAGE,JOptionPane.INFORMATION_MESSAGE,
				null,options,options[0]);
			}
		});
		//============================
		desk=new JDesktopPane();
		desk.setBounds(0,45,frm.getWidth()-300,frm.getHeight()-45);
		desk.setBackground(new Color(255,255,255));
		frm.add(desk);
		//============================
		Tools=new JPanel();
		Tools.setBounds(desk.getWidth(),75,300,frm.getHeight()-45);
		Tools.setBackground(new Color(220,220,220));
		final CardLayout card=new CardLayout();
		Tools.setLayout(card);
		Tools.add("con", conPanel);
		Tools.add("set", setPanel);
		Tools.add("cod", codPanel);
		frm.add(Tools);
		//============================
		final JLabel minButton=new JLabel("隱藏",JLabel.CENTER);
		minButton.setFont(new Font("宋体",0,20));
		minButton.setOpaque(true);
		minButton.setBackground(null);//隱藏按鈕顏色
		minButton.setBounds(frm.getWidth()-110,0,55,45);
		minButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				frm.setExtendedState(JFrame.ICONIFIED);
			}
			public void mouseEntered(MouseEvent e) {
				minButton.setBackground(new Color(0,191,255));//隱藏按鈕顏色
			}
		    public void mouseExited(MouseEvent e) {
		    	minButton.setBackground(null);//隱藏按鈕顏色
		    }
		});
		Title.add(minButton);
		//============================
		final JLabel closeButton=new JLabel("離開",JLabel.CENTER);
		closeButton.setFont(new Font("宋体",0,20));
		closeButton.setOpaque(true);
		closeButton.setBackground(null);//離開按鈕顏色
		closeButton.setBounds(frm.getWidth()-55,0,55,45);
		closeButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				System.exit(0);
			}
			public void mouseEntered(MouseEvent e) {
				closeButton.setBackground(new Color(255,0,0));//離開按鈕顏色
			}
		    public void mouseExited(MouseEvent e) {
		    	closeButton.setBackground(null);//離開按鈕顏色
		    }
		});
		Title.add(closeButton);
		//============================
		final JLabel conButton=new JLabel("添加組件",JLabel.CENTER);
		final JLabel setButton=new JLabel("設置組件",JLabel.CENTER);
		final JLabel codButton=new JLabel("查看代碼",JLabel.CENTER);
		//============================
		conButton.setFont(new Font("宋体",0,15));
		conButton.setOpaque(true);
		conButton.setBackground(null);//按鈕顏色
		conButton.setBounds(desk.getWidth(),45,100,30);
		conButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				conButton.setBackground(null);//按鈕顏色
				setButton.setBackground(new Color(245,245,245));//按鈕顏色
				codButton.setBackground(new Color(245,245,245));//按鈕顏色
				codButton.setText("查看代碼");
				card.show(Tools, "con");
			}
			public void mouseEntered(MouseEvent e) {
				conButton.setFont(new Font("宋体",0,17));
			}
		    public void mouseExited(MouseEvent e) {
		    	conButton.setFont(new Font("宋体",0,15));
		    }
		    public void mousePressed(MouseEvent e) {
		    	conButton.setForeground(Color.GRAY);
		    }
		    public void mouseReleased(MouseEvent e) {
		    	conButton.setForeground(Color.BLACK);
		    }
		});
		frm.add(conButton);
		//============================	
		setButton.setFont(new Font("宋体",0,15));
		setButton.setOpaque(true);
		setButton.setBackground(new Color(245,245,245));//按鈕顏色
		setButton.setBounds(desk.getWidth()+100,45,100,30);
		setButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				setButton.setBackground(null);//按鈕顏色
				conButton.setBackground(new Color(245,245,245));//按鈕顏色
				codButton.setBackground(new Color(245,245,245));//按鈕顏色
				codButton.setText("查看代碼");
				card.show(Tools, "set");
			}
			public void mouseEntered(MouseEvent e) {
				setButton.setFont(new Font("宋体",0,17));
			}
		    public void mouseExited(MouseEvent e) {
		    	setButton.setFont(new Font("宋体",0,15));
		    }
		    public void mousePressed(MouseEvent e) {
		    	setButton.setForeground(Color.GRAY);
		    }
		    public void mouseReleased(MouseEvent e) {
		    	setButton.setForeground(Color.BLACK);
		    }
		});
		frm.add(setButton);
		//============================
		codButton.setFont(new Font("宋体",0,15));
		codButton.setOpaque(true);
		codButton.setBackground(new Color(245,245,245));//按鈕顏色
		codButton.setBounds(desk.getWidth()+200,45,100,30);
		codButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				codButton.setBackground(null);//按鈕顏色
				conButton.setBackground(new Color(245,245,245));//按鈕顏色
				setButton.setBackground(new Color(245,245,245));//按鈕顏色
				codButton.setText("刷新代碼");
				cod1.setText("JFrame jf=new JFrame(\""+f.getTitle()+"\");\n" +
						"jf.setDefaultCloseOperation(3);\ntry{\n    UIManager.setLookAndFeel" +
						"(UIManager.getSystemLookAndFeelClassName());" +
						"\n}catch(Exception e){\n    e.printStackTrace();\n}"+
						"\njf.setLayout(null);\njf.setBounds("+f.getLocation().x+","+f.getLocation().y+","
						+f.getWidth()+","+f.getHeight()+");\n");
				for(int i=0;i<set1.getItemCount();i++){
					cod1.append(com.get(set1.getItemAt(i).split(" ")[1]).toString());
				}
				cod1.append("jf.setVisible(true);");
				card.show(Tools, "cod");
			}
			public void mouseEntered(MouseEvent e) {
				codButton.setFont(new Font("宋体",0,17));
			}
		    public void mouseExited(MouseEvent e) {
		    	codButton.setFont(new Font("宋体",0,15));
		    }
		    public void mousePressed(MouseEvent e) {
		    	codButton.setForeground(Color.GRAY);
		    }
		    public void mouseReleased(MouseEvent e) {
		    	codButton.setForeground(Color.BLACK);
		    }
		});
		frm.add(codButton);
		//============================
		frm.setVisible(true);
	}
	public static void main(String[] args) throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());//Change Look-and-feel
		new fm();
		f=new JInternalFrame("jf",true,false,false,false);
		f.setBounds(30,30,300,200);
		f.setLayout(null);
		desk.add(f);
		f.setVisible(true);
		f.addComponentListener(new ComponentAdapter() {
		    public void componentMoved(ComponentEvent e) {
		    	f.setLocation(30, 30);
		    }
		});
		f.addMouseListener(new MouseAdapter() {
		    public void mouseReleased(MouseEvent e) {
		    	for(int i=0;i<set1.getItemCount();i++){
					com c=com.get(set1.getItemAt(i).split(" ")[1]);
					if(c.c.getX()>=f.getWidth()||c.c.getY()>=f.getHeight()||c.c.getX()+c.c.getWidth()<=0||c.c.getY()+c.c.getHeight()<=0
			    			||c.c.getWidth()<=0||c.c.getHeight()<=0){
						f.setCursor(Cursor.getPredefinedCursor(0));
			    		c.destory();
			    	}
				}
		    }
		});
	}

}
