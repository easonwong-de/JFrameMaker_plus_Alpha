import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.io.*;
import java.net.URI;
import java.util.TreeMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileSystemView;

public class fm {
	static JFrame frm;
	static JPanel Title,Tools,conPanel,setPanel,codPanel;
	static JDesktopPane desk;
	static JInternalFrame f;
	static TreeMap<String,com> com;
	static JComboBox<String> set1;
	static JTextArea cod1;
	static JButton saveSettings;
	static com comPointer;
	static int languChooser,colorChooser;
	static String frameName;
	//文本 橫坐標 縱坐標 寬 高 字體名稱 字體風格 字號 紅 綠 藍
	static JTextField set2;
	static JSlider set3,set4,set5,set6;
	static JComboBox<String> set7,set8;
	static JSlider set9,set10,set11,set12;
	static JComponent[] SET;
	
	//標題欄色，字體色，背景色，選項卡第二字體色，設定窗欄背景色，代碼視窗
	static final Color[][] colors={
		{new Color(200,200,200),Color.BLACK,new Color(230,230,230),Color.GRAY,new Color(217,217,217),new Color(240,240,240)},
		{new Color(80,80,80),Color.WHITE,new Color(56,56,56),Color.LIGHT_GRAY,new Color(120,120,120),new Color(70,70,70)}
		};
	static final String[][] langus={
		{"JFrameMaker++ 內測版1.0.4","按鈕","標籤","文本框","文本域","複選欄"/*5*/
			,"   好的   ","訊息","組件名稱","添加組件","組件的名字不可為空","組件的名稱有重複"/*11*/
			,"=======單擊此處以獲取更多資訊=======","訪問失敗","一般","加粗","傾斜","加粗且傾斜"/*17*/
			,"文本 橫坐標 縱坐標 寬 高 字體名稱 字體風格 字號 紅 綠 藍","拷貝代碼","已拷貝到您的剪貼板"/*20*/
			,"設定","隱藏","離開","添加組件","設定組件","檢視代碼","整理代碼","設定語言","設定質感","設定JFrame標題"/*30*/
			,"經典淺色","現代深色","保存設定","  取消  "/*34*/
			,"保存設定需要重新啟動JFrameMaker++，確認要暫時登出？"/*35*/
			,"選擇一個組件","新特性"/*37*/
			,"<html><h1>1.0.4更新：</h1><h3>2016年十月</h3><br>" +
				"<h2>更新了GUI</h2>" +
					"<p>&emsp調整了字體顏色以及字號，使文字更容易閱讀。<p>" +
				"<h2>新增了「新特性」窗口</h2>" +
					"<p>&emsp即此窗口。透過此窗口，您可以瞭解歷屆版本的更新及本版本最新特性。</p>" +
			"<h1>1.0.3更新：</h1><h3>2016年十月</h3><br>" +
				"<h2>更新了語言模塊</h2>" +
					"<p>&emsp新增了日語模式，適合更多用戶使用。<p>"+
				"<center><br><h5>Copyright © 2016 WangYixin's Original Software. <br>All rights reserved.</h5></center></html>"},
		{"JFrameMaker++ Alpha1.0.4","JButton","JLabel","JTextField","JTextArea","JCheckBox"
			,"  OK  ","Massage","Name of Component","Add to JFrame","The name cannot be empty","Duplicate component name"
			,"======Click here for more Information======","Access Failed","General","Bold","Italic","Bold both Italic"
			,"Text Abscissa Ordinate Width Height Font_Name Font_Style Font_Size Red Green Blue","Copy the code","Copied to your clipboard"
			,"Setting","Hide","Exit","Add","Settings","See code","Refresh","Language","Feeling","JFrame's title"
			,"Classical light","Modren Deep","Save changes","  Cancle  "
			,"To save your settings, you need to restart JFrameMaker++. \nAre you sure you want to log out temporarily?"
			,"Choose a component","What's new","<html><h1>1.0.4 Updated: </h1><h3>October 2016</h3><br>"+
					"<h2> Updated GUI </h2>" +
						"<p> &emsp Adjusted the font color to make the text easier to read. <p>" +
					"<h2> Window \"What's new\" added </h2>" +
						"<p> &emsp Ie this window. With this window, you can learn the latest features in this version " +
						"and the <br>&emsp updations of previous versions.</p>" +
					"<center><br><h5>Copyright © 2016 WangYixin's Original Software. <br>All rights reserved.</h5></center></html>"},
		{"JFrameMaker++ アルファ1.0.4","ボタン", "ラベル", "テキストフィールド","テキスト領域","チェックボックス"
			,"  OK  ","マッサージ","コンポーネントの名前","JFrameに追加","名前は空ではありません","重複するコンポーネント名"
			,"=============詳細情報=============", "アクセス失敗", "一般", "ボールド","イタリック", "太字と斜体"
			, "テキスト 緯度 経度 幅 高さ フォント名 フォントスタイル フォントサイズ 赤 緑 青","コードのコピー","クリップボードにコピーされた"
			,"設定","隠す","終了","追加","設定","コードを参照","リフレッシュ","言語","スタイル","JFrameのタイトル"
			,"クラシックライト", "モドレンディープ","変更を保存","キャンセル"
			,"設定を保存するには、JFrameMaker ++を再起動する必要があります。\n一時的にログアウトしてもよろしいですか？"
			,"コンポーネントを選択","新機能","<html><h1>1.0.4アップデート：</h1><h3>2016年10月</h3><br>" +
					"<h2>GUIを更新</h2>" +
						"<p>&emspテキストを読みやすくするためにフォントの色を調整しました。<p>" +
					"<h2>「新機能」ウィンドウを追加された</h2>" +
						"<p>&emspつまり、このウィンドウ。 このウィンドウでは、このバージョンの最新機能と<br>&emsp以前のバージョンのアップデー" +
						"トを知ることができます。</p>" +
					"<center><br><h5>Copyright © 2016 WangYixin's Original Software. <br>All rights reserved.</h5></center></html>"}
		};
	
	fm() throws Exception{
		com=new TreeMap<String,com>();//僅提供查詢組件的功能
		comPointer=null;
		//============================
		frm=new JFrame();
		Insets screenInsets=Toolkit.getDefaultToolkit().getScreenInsets(frm.getGraphicsConfiguration());
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension sc=kit.getScreenSize();
		frm.setBounds(0,0,sc.width,sc.height-screenInsets.bottom);
		frm.setResizable(false);
		frm.setTitle(langus[languChooser][0]);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setLayout(null);
		frm.setUndecorated(true);
		frm.setIconImage(ImageIO.read(this.getClass().getResource("/data/ICO.png")));
		//============================
		Title=new JPanel();
		Title.setBounds(0,0,frm.getWidth(),45);
		frm.add(Title);
		Title.setBackground(colors[colorChooser][0]);//標題欄顏色
		Title.setLayout(null);
		//============================
		JLabel title=new JLabel(langus[languChooser][0]);
		title.setFont(new Font("PMingLiU",0,20));
		title.setForeground(colors[colorChooser][1]);
		title.setBounds(10,5,380,30);
		Title.add(title);
		//============================
		conPanel=new JPanel();
		conPanel.setLayout(new GridLayout(20,1,5,5));
		String[] comp=new String[]{langus[languChooser][1],langus[languChooser][2]
				,langus[languChooser][3],langus[languChooser][4],langus[languChooser][5]};
		final JComboBox<String> con1=new JComboBox<String>(comp);
		conPanel.add(con1);
		JLabel label=new JLabel(langus[languChooser][8],JLabel.CENTER);
		label.setForeground(colors[colorChooser][1]);
		label.setFont(new Font("PMingLiU",0,14));
		conPanel.add(label);
		final JTextField con2=new JTextField();
		conPanel.add(con2);
		final JButton con3=new JButton(langus[languChooser][9]);
		con3.setOpaque(false);
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
						Object[] options = {langus[languChooser][6]};
						JOptionPane.showOptionDialog(null,
								langus[languChooser][10],
						langus[languChooser][7],JOptionPane.ERROR_MESSAGE,JOptionPane.ERROR_MESSAGE,null,options,options[0]);
					}
				}else{
					Object[] options = {langus[languChooser][6]};
					JOptionPane.showOptionDialog(null,
					langus[languChooser][11],
					langus[languChooser][7],JOptionPane.ERROR_MESSAGE,JOptionPane.ERROR_MESSAGE,null,options,options[0]);
				}
			}
		});
		conPanel.add(con3);
		conPanel.add(new JLabel());
		final JLabel con4=new JLabel(langus[languChooser][12],JLabel.CENTER);
		con4.setFont(new Font("PMingLiU",0,15));
		con4.setCursor(new Cursor(Cursor.HAND_CURSOR));
		conPanel.add(con4);
		conPanel.add(new JLabel("<html><center>Copyright © 2016 WangYixin's Original Software. " +
				"</br>All rights reserved.</center></html>"));
		con4.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				try{
					URI uri=new URI("https://github.com/Wang-Yatsing");
					Desktop dtp=Desktop.getDesktop();
					if(Desktop.isDesktopSupported()&&dtp.isSupported(Desktop.Action.BROWSE)){
						dtp.browse(uri);
					}else{
						Object[] options = {langus[languChooser][6]};
						JOptionPane.showOptionDialog(null,
						langus[languChooser][13],
						langus[languChooser][7],JOptionPane.ERROR_MESSAGE,JOptionPane.ERROR_MESSAGE,
						null,options,options[0]);
					}
				}catch(Exception E){
					Object[] options = {langus[languChooser][6]};
					JOptionPane.showOptionDialog(null,
					langus[languChooser][13],
					langus[languChooser][7],JOptionPane.ERROR_MESSAGE,JOptionPane.ERROR_MESSAGE,
					null,options,options[0]);
				}
			}
			public void mouseEntered(MouseEvent e) {
				con4.setForeground(Color.WHITE);
			}
		    public void mouseExited(MouseEvent e) {
		    	con4.setForeground(Color.BLACK);
		    }
		});
		//============================
		
		setPanel=new JPanel();
		setPanel.setLayout(new GridLayout(25,1,0,0));
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
		label=new JLabel(langus[languChooser][36],JLabel.CENTER);
		label.setForeground(colors[colorChooser][1]);
		label.setFont(new Font("PMingLiU",0,14));
		setPanel.add(label);
		setPanel.add(set1);
		
		set2=new JTextField();
		set3=new JSlider(0,300-150-10,0);
		set4=new JSlider(0,200-30-35,0);
		set5=new JSlider(20,300-10,150);
		set6=new JSlider(20,200-35,30);
		set7=new JComboBox<String>(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());
		set8=new JComboBox<String>(new String[]{langus[languChooser][14],langus[languChooser][15]
				,langus[languChooser][16],langus[languChooser][17]});
		set9=new JSlider(-22,22,12);
		set10=new JSlider(0,255,0);
		set11=new JSlider(0,255,0);
		set12=new JSlider(0,255,0);
	    
		SET=new JComponent[]{set2,set3,set4,set5,set6,set7,set8,set9,set10,set11,set12};
		String[] items=langus[languChooser][18].split(" ");
		for(int i=0;i<SET.length;i++){//添加組件到設定介面
	    	SET[i].setEnabled(false);
	    	label=new JLabel(items[i],JLabel.CENTER);
	    	label.setForeground(colors[colorChooser][1]);
	    	label.setFont(new Font("PMingLiU",0,14));
	    	setPanel.add(label);
			setPanel.add(SET[i]);
			label=null;
			if(i!=0)SET[i].setOpaque(false);
	    }
		
		set2.addKeyListener(new KeyAdapter(){
		    public void keyReleased(KeyEvent e) {
		    	comPointer.setText(set2.getText());
		    }
	    });
		
		//文本 橫坐標 縱坐標 寬 高 字體名稱 字體風格 字號 紅 綠 藍
		set3.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(comPointer!=null){
					comPointer.c.setLocation(set3.getValue(),comPointer.c.getY());
					set5.setMaximum(f.getWidth()-comPointer.c.getX()-10);
				}else{
					set5.setMaximum(f.getWidth()-10);
				}
			}
		});
		set3.setPaintTicks(true);
		set3.setMajorTickSpacing(100);
		set3.setMinorTickSpacing(10);
		set3.setPaintLabels(true);
		set3.setForeground(colors[colorChooser][1]);
		
		set4.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(comPointer!=null){
					comPointer.c.setLocation(comPointer.c.getX(),set4.getValue());
					set6.setMaximum(f.getHeight()-comPointer.c.getY()-35);
				}else{
					set6.setMaximum(f.getHeight()-35);
				}
			}
		});
		set4.setPaintTicks(true);
		set4.setMajorTickSpacing(100);
		set4.setMinorTickSpacing(10);
		set4.setPaintLabels(true);
		set4.setForeground(colors[colorChooser][1]);
		
		set5.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(comPointer!=null){
					comPointer.c.setSize(set5.getValue(), comPointer.c.getHeight());
					set3.setMaximum(f.getWidth()-comPointer.c.getWidth()-10);
				}else{
					set3.setMaximum(f.getWidth()-10);
				}
			}
		});
		set5.setPaintTicks(true);
		set5.setMajorTickSpacing(100);
		set5.setMinorTickSpacing(10);
		set5.setPaintLabels(true);
		set5.setForeground(colors[colorChooser][1]);
		
		set6.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(comPointer!=null){
					comPointer.c.setSize(comPointer.c.getWidth(),set6.getValue());
					set4.setMaximum(f.getHeight()-comPointer.c.getHeight()-35);
					int pt=comPointer.c.getHeight()*72/96;
					if(pt>=100){
						set9.setMajorTickSpacing(100);
						set9.setMinorTickSpacing(10);
					}else{
						set9.setMajorTickSpacing(10);
						set9.setMinorTickSpacing(1);
					}
					set9.setMaximum(pt);
					set9.setMinimum(-pt);
				}else{
					set4.setMaximum(f.getHeight()-35);
				}
			}
		});
		set6.setPaintTicks(true);
		set6.setMajorTickSpacing(100);
		set6.setMinorTickSpacing(10);
		set6.setPaintLabels(true);
		set6.setForeground(colors[colorChooser][1]);
		
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
		set9.setForeground(colors[colorChooser][1]);
		
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
		set10.setForeground(colors[colorChooser][1]);
		
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
		set11.setForeground(colors[colorChooser][1]);
		
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
		set12.setForeground(colors[colorChooser][1]);
		//============================
		codPanel=new JPanel();
		codPanel.setLayout(null);
		JPanel cod=new JPanel();
		cod.setLayout(new GridLayout(1,1));
		cod.setBounds(5,5,290,frm.getHeight()-130);
		cod1=new JTextArea();
		cod1.setEditable(false);
		cod1.setFont(new Font("DialogInput",0,13));
		cod1.setBackground(colors[colorChooser][5]);
		cod1.setForeground(colors[colorChooser][1]);
		JScrollPane Sp=new JScrollPane(cod1);
		cod.add(Sp);
		codPanel.add(cod);
		JButton cod2=new JButton(langus[languChooser][19]);
		cod2.setBounds(5,frm.getHeight()-120, 290, 30);
		codPanel.add(cod2);
		cod2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				StringSelection stsel = new StringSelection(cod1.getText().replaceAll("    ", "	"));
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stsel, stsel);
				Object[] options = {langus[languChooser][6]};
				JOptionPane.showOptionDialog(null,
				langus[languChooser][20],
				langus[languChooser][7],JOptionPane.INFORMATION_MESSAGE,JOptionPane.INFORMATION_MESSAGE,
				null,options,options[0]);
			}
		});
		//============================
		desk=new JDesktopPane();
		desk.setBounds(0,45,frm.getWidth()-300,frm.getHeight()-45);
		desk.setBackground(colors[colorChooser][2]);
		frm.add(desk);
		//============================
		Tools=new JPanel();
		Tools.setBounds(desk.getWidth(),75,300,frm.getHeight()-75);
		final CardLayout card=new CardLayout();
		Tools.setLayout(card);
		Tools.add("con", conPanel);
		conPanel.setBackground(colors[colorChooser][4]);
		Tools.add("set", setPane);
		setPanel.setBackground(colors[colorChooser][4]);
		Tools.add("cod", codPanel);
		codPanel.setBackground(colors[colorChooser][4]);
		frm.add(Tools);
		//============================
		final JLabel bigSetButton=new JLabel(langus[languChooser][21],JLabel.CENTER);
		bigSetButton.setFont(new Font("PMingLiU",0,20));
		bigSetButton.setOpaque(true);
		bigSetButton.setBackground(null);//設定按鈕顏色
		bigSetButton.setBounds(frm.getWidth()-165,0,55,45);
		bigSetButton.setForeground(colors[colorChooser][1]);
		bigSetButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				bigSetButton.setBackground(null);//設定按鈕顏色
				settings();
			}
			public void mouseEntered(MouseEvent e) {
				bigSetButton.setBackground(new Color(100,220,100));//設定按鈕顏色
			}
		    public void mouseExited(MouseEvent e) {
		    	bigSetButton.setBackground(null);//設定按鈕顏色
		    }
		});
		Title.add(bigSetButton);
		//============================
		final JLabel minButton=new JLabel(langus[languChooser][22],JLabel.CENTER);
		minButton.setFont(new Font("PMingLiU",0,20));
		minButton.setOpaque(true);
		minButton.setBackground(null);//隱藏按鈕顏色
		minButton.setBounds(frm.getWidth()-110,0,55,45);
		minButton.setForeground(colors[colorChooser][1]);
		minButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				frm.setExtendedState(JFrame.ICONIFIED);
			}
			public void mouseEntered(MouseEvent e) {
				minButton.setBackground(new Color(0,190,255));//隱藏按鈕顏色
			}
		    public void mouseExited(MouseEvent e) {
		    	minButton.setBackground(null);//隱藏按鈕顏色
		    }
		});
		Title.add(minButton);
		//============================
		final JLabel closeButton=new JLabel(langus[languChooser][23],JLabel.CENTER);
		closeButton.setFont(new Font("PMingLiU",0,20));
		closeButton.setOpaque(true);
		closeButton.setBackground(null);//離開按鈕顏色
		closeButton.setBounds(frm.getWidth()-55,0,55,45);
		closeButton.setForeground(colors[colorChooser][1]);
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
		final JLabel conButton=new JLabel(langus[languChooser][24],JLabel.CENTER);
		final JLabel setButton=new JLabel(langus[languChooser][25],JLabel.CENTER);
		final JLabel codButton=new JLabel(langus[languChooser][26],JLabel.CENTER);
		//============================
		conButton.setFont(new Font("PMingLiU",0,15));
		conButton.setOpaque(true);
		conButton.setBackground(colors[colorChooser][2]);//按鈕顏色
		conButton.setForeground(colors[colorChooser][1]);
		conButton.setBounds(desk.getWidth(),45,100,30);
		conButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				conButton.setBackground(colors[colorChooser][2]);//按鈕顏色
				setButton.setBackground(colors[colorChooser][0]);//按鈕顏色
				codButton.setBackground(colors[colorChooser][0]);//按鈕顏色
				codButton.setText(langus[languChooser][26]);
				card.show(Tools, "con");
			}
			public void mouseEntered(MouseEvent e) {
				conButton.setFont(new Font("PMingLiU",0,16));
			}
		    public void mouseExited(MouseEvent e) {
		    	conButton.setFont(new Font("PMingLiU",0,15));
		    }
		    public void mousePressed(MouseEvent e) {
		    	conButton.setForeground(colors[colorChooser][3]);
		    }
		    public void mouseReleased(MouseEvent e) {
		    	conButton.setForeground(colors[colorChooser][1]);
		    }
		});
		frm.add(conButton);
		//============================	
		setButton.setFont(new Font("PMingLiU",0,15));
		setButton.setOpaque(true);
		setButton.setBackground(colors[colorChooser][0]);//按鈕顏色
		setButton.setForeground(colors[colorChooser][1]);
		setButton.setBounds(desk.getWidth()+100,45,100,30);
		setButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				setButton.setBackground(colors[colorChooser][2]);//按鈕顏色
				conButton.setBackground(colors[colorChooser][0]);//按鈕顏色
				codButton.setBackground(colors[colorChooser][0]);//按鈕顏色
				codButton.setText(langus[languChooser][26]);
				card.show(Tools, "set");
			}
			public void mouseEntered(MouseEvent e) {
				setButton.setFont(new Font("PMingLiU",0,16));
			}
		    public void mouseExited(MouseEvent e) {
		    	setButton.setFont(new Font("PMingLiU",0,15));
		    }
		    public void mousePressed(MouseEvent e) {
		    	setButton.setForeground(colors[colorChooser][3]);
		    }
		    public void mouseReleased(MouseEvent e) {
		    	setButton.setForeground(colors[colorChooser][1]);
		    }
		});
		frm.add(setButton);
		//============================
		codButton.setFont(new Font("PMingLiU",0,15));
		codButton.setOpaque(true);
		codButton.setBackground(colors[colorChooser][0]);//按鈕顏色
		codButton.setForeground(colors[colorChooser][1]);
		codButton.setBounds(desk.getWidth()+200,45,100,30);
		codButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				codButton.setBackground(colors[colorChooser][2]);//按鈕顏色
				conButton.setBackground(colors[colorChooser][0]);//按鈕顏色
				setButton.setBackground(colors[colorChooser][0]);//按鈕顏色
				codButton.setText(langus[languChooser][27]);
				String title="";
				if(f.getTitle()!=null)
					title=f.getTitle();
				cod1.setText("JFrame jf=new JFrame(\""+title+"\");\n" +
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
				codButton.setFont(new Font("PMingLiU",0,16));
			}
		    public void mouseExited(MouseEvent e) {
		    	codButton.setFont(new Font("PMingLiU",0,15));
		    }
		    public void mousePressed(MouseEvent e) {
		    	codButton.setForeground(colors[colorChooser][3]);
		    }
		    public void mouseReleased(MouseEvent e) {
		    	codButton.setForeground(colors[colorChooser][1]);
		    }
		});
		frm.add(codButton);
		//============================
		frm.setVisible(true);
	}
	public static void settings(){
		final JDialog setFrame=new JDialog(frm,langus[languChooser][21], true);
		setFrame.setSize(300,350);
		setFrame.setResizable(false);
		setFrame.setLocationRelativeTo(null);
		setFrame.setLayout(new GridLayout(8,1,5,5));
		
		setFrame.add(new JLabel(langus[languChooser][28]));
		final JComboBox<String> langu=new JComboBox<String>(new String[]{"中文(中國)","English(US&UK)","日本語(日本)"});
		langu.setSelectedIndex(languChooser);
		setFrame.add(langu);
		langu.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED){
					saveSettings.setEnabled(true);
				}
			}
		});
		
		setFrame.add(new JLabel(langus[languChooser][29]));
		final JComboBox<String> color=new JComboBox<String>(new String[]{langus[languChooser][31],langus[languChooser][32]});
		color.setSelectedIndex(colorChooser);
		setFrame.add(color);
		color.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED){
					saveSettings.setEnabled(true);
				}
			}
		});
		
		setFrame.add(new JLabel(langus[languChooser][30]));
		final JTextField frameTitle=new JTextField(frameName);
		frameTitle.getDocument().addDocumentListener(new DocumentListener() {
			public void removeUpdate(DocumentEvent e) {
				saveSettings.setEnabled(true);
			}
			public void insertUpdate(DocumentEvent e) {
				saveSettings.setEnabled(true);
			}
			public void changedUpdate(DocumentEvent e) {
				saveSettings.setEnabled(true);
			}
		});
		setFrame.add(frameTitle);
		
		saveSettings=new JButton(langus[languChooser][33]);
		saveSettings.setEnabled(false);
		setFrame.add(saveSettings);
		saveSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] options = {langus[languChooser][6],langus[languChooser][34]};
				if(JOptionPane.showOptionDialog(null,
						langus[languChooser][35],
						langus[languChooser][7],JOptionPane.INFORMATION_MESSAGE,JOptionPane.INFORMATION_MESSAGE,
						null,options,options[0])==0){
					File file=new File(FileSystemView.getFileSystemView().getDefaultDirectory().getPath()+"/JFrameMaker/settings.dll");
					try{
						BufferedWriter writer=new BufferedWriter(new FileWriter(file,false));
						writer.write(langu.getSelectedIndex()+"");
						writer.newLine();
						writer.write(color.getSelectedIndex()+"");
						writer.newLine();
						writer.write(frameTitle.getText()+"");
						writer.close();
					}catch(Exception e1){
							e1.printStackTrace();
						}
					System.exit(0);
				}
			}
		});
		
		final JDialog seeFrame=new JDialog(frm,langus[languChooser][37], true);
		seeFrame.setSize(600,400);
		seeFrame.setResizable(false);
		seeFrame.setLocationRelativeTo(null);
		seeFrame.setLayout(new GridLayout(1,1));
		JLabel label=new JLabel(langus[languChooser][38]);
		label.setFont(new Font("PMingLiU",0,15));
		JScrollPane Sp=new JScrollPane(label);
		seeFrame.add(Sp);
		seeFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		seeFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				seeFrame.setVisible(false);
				setFrame.setVisible(true);
				setFrame.setLocationRelativeTo(null);
			}
		});
		
		JButton seeNewFun=new JButton(langus[languChooser][37]);
		setFrame.add(seeNewFun);
		seeNewFun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setFrame.setVisible(false);
				seeFrame.setVisible(true);
				seeFrame.setLocationRelativeTo(null);
			}
		});
		setFrame.setVisible(true);
	}
	public static void main(String[] args) throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		File dir=new File(FileSystemView.getFileSystemView().getDefaultDirectory().getPath()+"/JFrameMaker");
		File file=new File(FileSystemView.getFileSystemView().getDefaultDirectory().getPath()+"/JFrameMaker/settings.dll");
		if(!file.exists()&&!file.isFile()){
			if(!dir.exists()&&!dir.isDirectory())dir.mkdir();
			file.createNewFile();
			BufferedWriter writer=new BufferedWriter(new FileWriter(file,false));
			writer.write(0+"");
			writer.newLine();
			writer.write(0+"");
			writer.newLine();
			writer.write("jf");
			writer.close();
		}
		BufferedReader reader=new BufferedReader(new FileReader(file));
		languChooser=new Integer(reader.readLine());
		colorChooser=new Integer(reader.readLine());
		frameName=reader.readLine();
		reader.close();
		new fm();
		
		f=new JInternalFrame(frameName,true,false,false,false);
		f.setBounds(30,30,300,200);
		f.setLayout(null);
		desk.add(f);
		f.setVisible(true);
		f.addComponentListener(new ComponentAdapter() {
		    public void componentMoved(ComponentEvent e) {
		    	f.setLocation(30, 30);
		    }
		    public void componentResized(ComponentEvent e) {
		    	set3.setMaximum(f.getWidth()-10);
				set4.setMaximum(f.getHeight()-35);
				if(comPointer!=null){
					set3.setMaximum(f.getWidth()-comPointer.c.getWidth()-10);
					set4.setMaximum(f.getHeight()-comPointer.c.getHeight()-35);
					set5.setMaximum(f.getWidth()-comPointer.c.getX()-10);
					set6.setMaximum(f.getHeight()-comPointer.c.getY()-35);
				}else{
					set5.setMaximum(f.getWidth()-10);
					set6.setMaximum(f.getHeight()-35);
				}
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
