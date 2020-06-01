package GUI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Label;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegPanel extends JPanel {
	private JTextField textField=new JTextField(20);
	private JPasswordField passwordField=new JPasswordField(20);
	private JButton btnRegister;
	private JButton btnBack;
	public RegPanel(String s,boolean p) {
		
		FlowLayout f=new FlowLayout();
		setLayout(f);
		add(new Label(s));
		if(!p)
		{
			add(textField);
		}
		else
		{
			add(passwordField);
		}
		
	}

}
