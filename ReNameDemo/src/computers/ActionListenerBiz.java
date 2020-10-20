package computers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import com.sun.xml.internal.ws.api.Component;

public class ActionListenerBiz implements ActionListener{
	public Component component;
	
	public JLabel label;
	public ActionListenerBiz(Component component,JLabel label) {
		this.component = component;
		this.label = label;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
