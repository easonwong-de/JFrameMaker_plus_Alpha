import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class com {
	JComponent c;
	JInternalFrame f;
	String kind,name;
	private boolean resize;
	private int x,y;
	private Point p;
	private MouseAdapter moved,cursor;
	com(final String kind,final String name){
		this.kind=kind;
		this.name=name;
		this.f=fm.f;
		switch(this.kind){
		case "JButton":{c=new JButton(name);break;}
		case "JLabel":{c=new JLabel(name);c.setOpaque(true);c.setBackground(Color.WHITE);break;}
		case "JTextField":{c=new JTextField(name);break;}
		case "JTextArea":{c=new JTextArea(name);break;}
		case "JCheckBox":{c=new JCheckBox(name);c.setBackground(Color.WHITE);break;}
		}
		moved=new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				if(c.getWidth()-e.getX()<10&&c.getHeight()-e.getY()<10){
					x=c.getWidth()-e.getX();
					y=c.getHeight()-e.getY();
					resize=true;
				}else{
					p=e.getPoint();
					resize=false;
				}
			}
			public void mouseDragged(MouseEvent e) {
				c.setEnabled(false);
				if(resize){
					c.setSize(e.getX()+x,e.getY()+y);
					f.setCursor(Cursor.getPredefinedCursor(5));
				}else{
					c.setLocation(c.getX()+e.getX()-p.x,c.getY()+e.getY()-p.y);
					f.setCursor(Cursor.getPredefinedCursor(13));
				}
				if(fm.comPointer.name==name){
					fm.set3.setValue(c.getX());
					fm.set4.setValue(c.getY());
					fm.set5.setValue(c.getWidth());
					fm.set6.setValue(c.getHeight());
				}
			}
		    public void mouseReleased(MouseEvent e) {
		    	if(c.getX()>=f.getWidth()||c.getY()>=f.getHeight()||c.getX()+c.getWidth()<=0||c.getY()+c.getHeight()<=0
		    			||c.getWidth()<=0||c.getHeight()<=0){
					f.setCursor(Cursor.getPredefinedCursor(0));
		    		destory();
		    	}
		    }
		};
		cursor=new MouseAdapter(){
			public void mouseEntered(MouseEvent e) {
				e.getComponent().setEnabled(false);
			}
			public void mouseExited(MouseEvent e) {
				e.getComponent().setEnabled(true);
				f.setCursor(Cursor.getPredefinedCursor(0));
			}
			public void mouseMoved(MouseEvent e){
				if(c.getWidth()-e.getX()<10&&c.getHeight()-e.getY()<10)
					f.setCursor(Cursor.getPredefinedCursor(5));
				else
					f.setCursor(Cursor.getPredefinedCursor(13));
			}
		};
		c.addMouseListener(moved);
		c.addMouseMotionListener(moved);
		c.addMouseListener(cursor);
		c.addMouseMotionListener(cursor);
		f.add(c);
		c.setBounds(0,0,150,30);
	}
	/**
     * 獲取組件的各種屬性
     * @return 文本 橫坐標 縱坐標 寬 高 字體名稱 字體風格 字號 紅 綠 藍
     */
	public Object[] get(){
		Object[] data={getText(),c.getX(),c.getY(),c.getWidth(),c.getHeight(),
				c.getFont().getFontName(),c.getFont().getStyle(),c.getFont().getSize(),
				c.getForeground().getRed(),c.getForeground().getGreen(),c.getForeground().getBlue()};
		return data;
	}
	private String getText(){
		String text = null;
		switch(this.kind){
		case "JButton":{text=((JButton) c).getText();break;}
		case "JLabel":{text=((JLabel) c).getText();break;}
		case "JTextField":{text=((JTextField) c).getText();break;}
		case "JTextArea":{text=((JTextArea) c).getText();break;}
		case "JCheckBox":{text=((JCheckBox) c).getText();break;}
		}
		return text;
	}
	public void setText(String text){
		switch(this.kind){
		case "JButton":{((JButton) c).setText(text);break;}
		case "JLabel":{((JLabel) c).setText(text);break;}
		case "JTextField":{((JTextField) c).setText(text);break;}
		case "JTextArea":{((JTextArea) c).setText(text);break;}
		case "JCheckBox":{((JCheckBox) c).setText(text);break;}
		}
	}
	public String toString(){
		String str=kind+" "+name+"=new "+kind+"(\""+getText()+"\");\n"+f.getTitle()+".add("+name+");\n" +
				name+".setBounds("+c.getX()+","+c.getY()+","+c.getWidth()+","+c.getHeight()+");\n"
				+name+".setFont(new Font(\""+c.getFont().getFontName()+"\","+c.getFont().getStyle()+","+c.getFont().getSize()+"))\n"
				+name+".setForeground(" +
				"new Color("+c.getForeground().getRed()+","+c.getForeground().getGreen()+","+c.getForeground().getBlue()+"));\n";
		return str;
	}
	//文本 橫坐標 縱坐標 寬 高 字體名稱 字體風格 字號 紅 綠 藍
	public void destory(){
		c.setVisible(false);
		fm.com.remove(name);
		fm.set1.removeItem(kind+" "+name);
		if(fm.set1.getItemCount()==0){
			for(int i=0;i<fm.SET.length;i++){
		    	fm.SET[i].setEnabled(false);
		    }
		fm.set2.setText(null);
	    	fm.set3.setValue(0);
	    	fm.set4.setValue(0);
	    	fm.set5.setValue(0);
	    	fm.set6.setValue(0);
	    	fm.set7.setSelectedIndex(0);
	    	fm.set8.setSelectedIndex(0);
	    	fm.set9.setValue(0);
	    	fm.set10.setValue(0);
	    	fm.set11.setValue(0);
	    	fm.set12.setValue(0);
		fm.comPointer=null;
		}
	}
}
