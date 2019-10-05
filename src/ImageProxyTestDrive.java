

import java.net.*;
import javax.swing.*;
import java.util.*;

public class ImageProxyTestDrive {
	ImageComponent imageComponent;
	JFrame frame = new JFrame("Home Automation Controller");
	JMenuBar menuBar;
	JMenu menu;
	Hashtable<String, String> scenes = new Hashtable<String, String>();

	public static void main (String[] args) throws Exception {
		ImageProxyTestDrive testDrive = new ImageProxyTestDrive();
	}

	public ImageProxyTestDrive() throws Exception {
		scenes.put("Patio Lights","https://www.intuav.com/wp-content/uploads/Lights1.png");
		scenes.put("Home Theatre","https://i.pinimg.com/originals/a5/ca/6b/a5ca6b6dec749af7da45b72b637e7324.jpg");
		scenes.put("All Lights Off","https://www.secur-tek.com/wp-content/uploads/2017/11/1.jpg");
		scenes.put("Kitchen Light On","https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/kitchen-lighting-ideas-baskets-1562014692.jpg");
		scenes.put("Garage Closed","https://cdn.website.thryv.com/1ac23bba2e354dcea49a55eab7f83bfc/dms3rep/multi/desktop/1420872-The-Door-Man.w640.jpg");
		
		URL initialURL = new URL((String)scenes.get("All Lights Off"));
		menuBar = new JMenuBar();
		menu = new JMenu("Home Automation Scenes");
		menuBar.add(menu);
		frame.setJMenuBar(menuBar);

		for (Enumeration<String> e = scenes.keys(); e.hasMoreElements();) {
			String name = (String)e.nextElement();
			JMenuItem menuItem = new JMenuItem(name);
			menu.add(menuItem); 
			menuItem.addActionListener(event -> {
				imageComponent.setIcon(new ImageProxy(getSceneUrl(event.getActionCommand())));
				frame.repaint();
			});
		}

		Icon icon = new ImageProxy(initialURL);
		imageComponent = new ImageComponent(icon);
		frame.getContentPane().add(imageComponent);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600,450);
		frame.setVisible(true);

	}

	URL getSceneUrl(String name) {
		try {
			return new URL((String)scenes.get(name));
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
