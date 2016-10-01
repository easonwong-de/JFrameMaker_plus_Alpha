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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class fm {
	static JFrame frm;
	static JPanel Title,Tools,conPanel,setPanel,codPanel;
	static JDesktopPane desk;
	static JInternalFrame f;
	static TreeMap<String,com> com;
	static JComboBox<String> set1;
	static JTextArea cod1;
	static com comPointer;
	//文本 橫坐標 縱坐標 寬 高 字體名稱 字體風格 字號 紅 綠 藍
	static JTextField set2;
	static JSlider set3,set4,set5,set6;
	static JComboBox<String> set7,set8;
	static JSlider set9,set10,set11,set12;
	static JComponent[] SET;
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
		frm.setTitle("JFrameMaker++ 正體版 - 內測版1.0.2");
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
		JLabel title=new JLabel("JFrameMaker++ 正體版 - 內測版1.0.2");
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
		con4.setFont(new Font("Serif",0,15));
		con4.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
		JScrollPane setPane=new JScrollPane(setPanel);
		setPane.getVerticalScrollBar().setUnitIncrement(17);
		set1=new JComboBox<String>();
		set1.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){
				if(e.getStateChange() == ItemEvent.SELECTED){
					if(set1.getSelectedItem().toString()!=null){
						comPointer=com.get(set1.getSelectedItem().toString().split(" ")[1]);
						Object[] data=comPointer.get();
						set2.setText(data[0]+"");
						set3.setValue((int)data[1]);
						set4.setValue((int)data[2]);
						set5.setValue((int)data[3]);
						set6.setValue((int)data[4]);
						set7.setSelectedItem(data[5]);
						set8.setSelectedIndex((int) data[6]);
						set9.setValue((int) data[7]);
						set10.setValue((int) data[8]);
						set11.setValue((int) data[9]);
						set12.setValue((int) data[10]);
						for(int i=0;i<SET.length;i++){
					    	SET[i].setEnabled(true);
					    }
					}
				}
			}
		});
		//文本 橫坐標 縱坐標 寬 高 字體名稱 字體風格 字號 紅 綠 藍
		setPanel.add(set1);
		
		set2=new JTextField();
		set3=new JSlider(0,300-20,0);
		set4=new JSlider(0,200-20,0);
		set5=new JSlider(20,300-20,150);
		set6=new JSlider(20,200-20,30);
		set7=new JComboBox<String>(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());
		set8=new JComboBox<String>(new String[]{"一般","加粗","傾斜","加粗且傾斜"});
		set9=new JSlider(0,22,12);
		set10=new JSlider(0,255,0);
		set11=new JSlider(0,255,0);
		set12=new JSlider(0,255,0);
	    
		SET=new JComponent[]{set2,set3,set4,set5,set6,set7,set8,set9,set10,set11,set12};
		String[] items="文本 橫坐標 縱坐標 寬 高 字體名稱 字體風格 字號 紅 綠 藍".split(" ");
		for(int i=0;i<SET.length;i++){
	    	SET[i].setEnabled(false);
	    	setPanel.add(new JLabel(items[i]));
			setPanel.add(SET[i]);
	    }
		
		set2.addKeyListener(new KeyAdapter(){
		    public void keyReleased(KeyEvent e) {
		    	comPointer.setText(set2.getText());
		    }
	    });
		
		//文本 橫坐標 縱坐標 寬 高 字體名稱 字體風格 字號 紅 綠 藍
		set3.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				comPointer.c.setLocation(set3.getValue(),comPointer.c.getY());
				set5.setMaximum(f.getWidth()-8-comPointer.c.getX());
			}
		});
		set3.setPaintTicks(true);
		set3.setMajorTickSpacing(100);
		set3.setMinorTickSpacing(10);
		set3.setPaintLabels(true);
		
		set4.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				comPointer.c.setLocation(comPointer.c.getX(),set4.getValue());
				set6.setMaximum(f.getHeight()-8-comPointer.c.getY());
			}
		});
		set4.setPaintTicks(true);
		set4.setMajorTickSpacing(100);
		set4.setMinorTickSpacing(10);
		set4.setPaintLabels(true);
		
		set5.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				comPointer.c.setSize(set5.getValue(), comPointer.c.getHeight());
			}
		});
		set5.setPaintTicks(true);
		set5.setMajorTickSpacing(100);
		set5.setMinorTickSpacing(10);
		set5.setPaintLabels(true);
		
		set6.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				comPointer.c.setSize(comPointer.c.getWidth(),set6.getValue());
				int pt=comPointer.c.getHeight()*72/96;
				if(pt>=100){
					set9.setMajorTickSpacing(100);
					set9.setMinorTickSpacing(10);
				}else{
					set9.setMajorTickSpacing(10);
					set9.setMinorTickSpacing(1);
				}
				set9.setMaximum(pt);
			}
		});
		set6.setPaintTicks(true);
		set6.setMajorTickSpacing(100);
		set6.setMinorTickSpacing(10);
		set6.setPaintLabels(true);
		
		//文本 橫坐標 縱坐標 寬 高 字體名稱 字體風格 字號 紅 綠 藍
		set7.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){
				if(e.getStateChange() == ItemEvent.SELECTED){
					comPointer.c.setFont(new Font(set7.getSelectedItem().toString()
							,comPointer.c.getFont().getStyle(),comPointer.c.getFont().getSize()));
				}
			}
		});
		
		set8.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){
				if(e.getStateChange() == ItemEvent.SELECTED){
					comPointer.c.setFont(new Font(comPointer.c.getFont().getFontName()
							,set8.getSelectedIndex(),comPointer.c.getFont().getSize()));
				}
			}
		});
		
		set9.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				comPointer.c.setFont(new Font(comPointer.c.getFont().getFontName()
						,comPointer.c.getFont().getStyle(),set9.getValue()));
			}
		});
		set9.setPaintTicks(true);
		set9.setMajorTickSpacing(10);
		set9.setMinorTickSpacing(1);
		set9.setPaintLabels(true);
		
		//文本 橫坐標 縱坐標 寬 高 字體名稱 字體風格 字號 紅 綠 藍
		set10.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				comPointer.c.setForeground(new Color(set10.getValue()
						,comPointer.c.getForeground().getGreen(),comPointer.c.getForeground().getBlue()));
			}
		});
		set10.setPaintTicks(true);
		set10.setMajorTickSpacing(51);
		set10.setMinorTickSpacing(17);
		set10.setPaintLabels(true);
		
		set11.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				comPointer.c.setForeground(new Color(comPointer.c.getForeground().getRed()
						,set11.getValue(),comPointer.c.getForeground().getBlue()));
			}
		});
		set11.setPaintTicks(true);
		set11.setMajorTickSpacing(51);
		set11.setMinorTickSpacing(17);
		set11.setPaintLabels(true);
		
		set12.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				comPointer.c.setForeground(new Color(comPointer.c.getForeground().getRed()
						,comPointer.c.getForeground().getGreen(),set12.getValue()));
			}
		});
		set12.setPaintTicks(true);
		set12.setMajorTickSpacing(51);
		set12.setMinorTickSpacing(17);
		set12.setPaintLabels(true);
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
		Tools.add("set", setPane);
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
		final JLabel codButton=new JLabel("檢視代碼",JLabel.CENTER);
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
				codButton.setText("檢視代碼");
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
				codButton.setText("檢視代碼");
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
				codButton.setText("整理代碼");
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
		    public void componentResized(ComponentEvent e) {
		    	set3.setMaximum(f.getWidth()-20);
				set4.setMaximum(f.getHeight()-20);
				set5.setMaximum(f.getWidth()-8-comPointer.c.getX());
				set6.setMaximum(f.getHeight()-8-comPointer.c.getY());
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
