import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import javax.swing.JComboBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

class AdobePatcherClass extends JFrame {
	private JComboBox<String> comboBox;
	private JButton patchButton;
	private final String ae = "Adobe After Effects";
	private final String ps = "Adobe Photoshop";
	private final String pp = "Adobe Premiere Pro";
	private Color cyan = new Color(7, 217, 207); //#07d9cf
	private JButton btcopy= new JButton("Copy");
	private JButton ltcopy=new JButton("Copy");
	private JButton nancopy=new JButton("Copy");
	private final String btcAddress = "13aPwggxw6ViFh3qGhvSXiQr2zyfons7K6";
	private final String btcAddress1 = "13aPwggxw6ViFh3q";
	private final String btcAddress2 = "GhvSXiQr2zyfons7K6";
	private final String ltcAddress = "LTYL33r1Zr2fp57RZRzTZkaRJVDn3xg1jK";
	private final String ltcAddress1 = "LTYL33r1Zr2fp57RZ";
	private final String ltcAddress2 = "RzTZkaRJVDn3xg1jK";
	private final String nanoAddress = "xrb_14p8xbwjrw1bqofx5bgikkijb4nsqntw33x8wr3degmt7u3w8gxxuw858yxe";
	private final String nanoAddress1 = "xrb_14p8xbwjrw1bqofx5bgikkijb4nsq";
	private final String nanoAddress2 = "ntw33x8wr3degmt7u3w8gxxuw858yxe";
	ImageIcon btcIcon ;
	ImageIcon ltcIcon;
	ImageIcon nanoIcon;

	JLabel btcimg;
	JLabel ltcimg;
	JLabel nanoimg;

	AdobePatcherClass() {
		super("Adobe Patcher v1.0 Beta");
		this.getContentPane().setBackground(Color.DARK_GRAY);
		setSize(450, 220);
		getContentPane().setLayout(null);
		setResizable(false);

		// theme color

		// JLabel attributes
		JLabel lblChooseAdobeProduct = new JLabel("Choose Adobe Product:");
		lblChooseAdobeProduct.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		lblChooseAdobeProduct.setForeground(Color.WHITE);
		lblChooseAdobeProduct.setBounds(16, 25, 224, 53);
		getContentPane().add(lblChooseAdobeProduct);
		lblChooseAdobeProduct.setForeground(cyan);

		// patchButton attributes
		patchButton = new JButton("Patch!");
		patchButton.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 22));
		patchButton.setBounds(170, 103, 100, 25);
		getContentPane().add(patchButton);
		buttonPressed buttonListener = new buttonPressed();
		patchButton.addActionListener(buttonListener);
		patchButton.setVisible(false);
		patchButton.setBackground(cyan);
		patchButton.setBorderPainted(false);

		// buttons in comboBox
		String[] adobeProducts = new String[] { "None", ae, pp, ps };
		comboBox = new JComboBox<>(adobeProducts);
		comboBox.setBounds(253, 40, 150, 25);
		getContentPane().add(comboBox);
		comboBox.setBackground(Color.DARK_GRAY);
		comboBox.setForeground(cyan);

		comboListener cl = new comboListener();
		comboBox.addActionListener(cl);

		JLabel donate = new JLabel("Donate! :");

		donate.setForeground(cyan);
		donate.setBounds(10, 145, 60, 25);
		getContentPane().add(donate);

		int iconHeight = 149;

		MouseListener iconGlow = new iconGlow();

		btcimg = new JLabel();
		btcIcon = getResourceImage("bin/images/newIcons/btcDark.png", 21, 21);
		btcimg.setIcon(btcIcon);
		btcimg.setBounds(70, iconHeight - 2, 22, 22);
		getContentPane().add(btcimg);
		btcimg.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btcimg.addMouseListener(iconGlow);
		btcimg.setToolTipText("Bitcoin");

		ltcimg = new JLabel();
		ltcIcon = getResourceImage("bin/images/newIcons/ltcDark.png", 20, 20);
		ltcimg.setIcon(ltcIcon);
		ltcimg.setBounds(100, iconHeight, 20, 20);
		getContentPane().add(ltcimg);
		ltcimg.setCursor(new Cursor(Cursor.HAND_CURSOR));
		ltcimg.addMouseListener(iconGlow);
		ltcimg.setToolTipText("Litecoin");

		nanoimg = new JLabel();
		nanoIcon = getResourceImage("bin/images/newIcons/nanoDark.png", 20, 20);
		nanoimg.setIcon(nanoIcon);
		nanoimg.setBounds(130, iconHeight, 20, 20);
		getContentPane().add(nanoimg);
		nanoimg.setCursor(new Cursor(Cursor.HAND_CURSOR));
		nanoimg.addMouseListener(iconGlow);
		nanoimg.setToolTipText("Nano");

	}

	class iconGlow implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			if (btcimg.equals(arg0.getSource())) {
				ImageIcon darkbitcoinIcon = getResourceImage("bin/images/newIcons/btcDarkGlow.png", 21, 21);
				btcimg.setIcon(darkbitcoinIcon);
			}
			if (ltcimg.equals(arg0.getSource())) {
				ImageIcon darkLitecoinIcon = getResourceImage("bin/images/newIcons/ltcDarkGlow.png", 20, 20);
				ltcimg.setIcon(darkLitecoinIcon);

			}
			if (nanoimg.equals(arg0.getSource())) {
				ImageIcon darkNanoIcon = getResourceImage("bin/images/newIcons/nanoDarkGlow.png", 20, 20);
				nanoimg.setIcon(darkNanoIcon);
			}

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			if (btcimg.equals(arg0.getSource())) {
				btcimg.setIcon(btcIcon);
			}
			if (ltcimg.equals(arg0.getSource())) {
				ltcimg.setIcon(ltcIcon);
			}
			if (nanoimg.equals(arg0.getSource())) {
				nanoimg.setIcon(nanoIcon);
			}

		}

		@Override
		public void mousePressed(MouseEvent arg0) {

			copyAction copyListener = new copyAction();

			if (btcimg.equals(arg0.getSource())) {

				BufferedImage btcqr;
				try {
					btcqr = ImageIO.read(new File("bin/images/bitcoinqr.png"));
					JLabel btcqrCode = new JLabel(new ImageIcon(btcqr));
					btcqrCode.setAlignmentX(Component.CENTER_ALIGNMENT);
					JPanel address = new JPanel();
					address.setLayout(new BoxLayout(address, BoxLayout.Y_AXIS));
					address.add(new JLabel(btcAddress1));
					address.add(new JLabel(btcAddress2));
					JPanel bottomRow = new JPanel();
					bottomRow.setLayout(new FlowLayout());

					btcopy.addActionListener(copyListener);
					bottomRow.add(btcopy);
					bottomRow.add(address);

					JPanel messageObj = new JPanel();
					messageObj.setLayout(new BoxLayout(messageObj, BoxLayout.Y_AXIS));
					messageObj.add(btcqrCode);
					messageObj.add(bottomRow);

					JOptionPane.showMessageDialog(null, messageObj, "Bitcoin", JOptionPane.PLAIN_MESSAGE);

				} catch (IOException e) {
					e.printStackTrace();
				}

			}
			if (ltcimg.equals(arg0.getSource())) {
				BufferedImage ltcqr;
				try {
					ltcqr = ImageIO.read(new File("bin/images/litecoinqr.png"));
					JLabel ltcqrCode = new JLabel(new ImageIcon(ltcqr));
					ltcqrCode.setAlignmentX(Component.CENTER_ALIGNMENT);
					JPanel address = new JPanel();
					address.setLayout(new BoxLayout(address, BoxLayout.Y_AXIS));
					address.add(new JLabel(ltcAddress1));
					address.add(new JLabel(ltcAddress2));
					JPanel bottomRow = new JPanel();

					ltcopy.addActionListener(copyListener);

					bottomRow.add(ltcopy);
					bottomRow.add(address);

					JPanel messageObj = new JPanel();
					messageObj.setLayout(new BoxLayout(messageObj, BoxLayout.Y_AXIS));
					messageObj.add(ltcqrCode);
					messageObj.add(bottomRow);

					JOptionPane.showMessageDialog(null, messageObj, "Litecoin", JOptionPane.PLAIN_MESSAGE);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (nanoimg.equals(arg0.getSource())) {
				BufferedImage nanoqr;
				try {
					nanoqr = ImageIO.read(new File("bin/images/nanoqr.png"));
					JPanel messageObj = new JPanel();
					messageObj.setLayout(new BoxLayout(messageObj, BoxLayout.Y_AXIS));
					JLabel toadd = new JLabel(new ImageIcon(nanoqr));
					toadd.setAlignmentX(Component.CENTER_ALIGNMENT);
					messageObj.add(toadd);
					JLabel nanoaddress = new JLabel(nanoAddress1);
					JLabel nanoaddress2 = new JLabel(nanoAddress2);


					nancopy.addActionListener(copyListener);

					JPanel bottomRow = new JPanel(new FlowLayout());
					JPanel address = new JPanel();
					address.setLayout(new BoxLayout(address, BoxLayout.Y_AXIS));
					address.add(nanoaddress);
					address.add(nanoaddress2);
					bottomRow.add(nancopy);
					bottomRow.add(address);
					messageObj.add(bottomRow);

					JOptionPane.showMessageDialog(null, messageObj, "Nano", JOptionPane.PLAIN_MESSAGE);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
		}

	}

	public ImageIcon getResourceImage(String filepath, int x, int y) {
		Image rawImage = new ImageIcon(filepath).getImage();
		Image renderedImage = rawImage.getScaledInstance(x, y, Image.SCALE_SMOOTH);
		ImageIcon image = new ImageIcon(renderedImage);
		return image;
	}

	private class copyAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println(arg0.getSource());

			System.out.println(btcopy);

			if (btcopy.equals(arg0.getSource())) {
				StringSelection stringSelection = new StringSelection(btcAddress);
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(stringSelection, null);
			}

			else if (ltcopy.equals(arg0.getSource())) {
				StringSelection stringSelection = new StringSelection(ltcAddress);
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(stringSelection, null);
			}

			else if (nancopy.equals(arg0.getSource())) {
				StringSelection stringSelection = new StringSelection(nanoAddress);
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(stringSelection, null);
			}

			JOptionPane.showMessageDialog(null, "Address copied to clipboard!");

		}

	}

	class comboListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (comboBox.getSelectedItem() != "None")
				patchButton.setVisible(true);
			else
				patchButton.setVisible(false);
		}

	}

	class buttonPressed implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			String a = comboBox.getSelectedItem().toString();
			String address;
			int nodeNumber;

			switch (a) {
			case ae:
				System.out.println("After Effects");
				address = "C:\\Program Files\\Adobe\\Adobe After Effects CC 2018\\Support Files\\AMT\\application.xml";
				nodeNumber = 17;
				break;
			case pp:
				System.out.println("Premiere Pro");
				address = "C:\\Program Files\\Adobe\\Adobe Premiere Pro CC 2018\\AMT\\application.xml";
				nodeNumber = 15;
				break;
			case ps:
				System.out.println("photoshop");
				address = "C:\\Program Files\\Adobe\\Adobe Photoshop CC 2018\\AMT\\application.xml";
				nodeNumber = 18;
				break;
			default:
				address = "none";
				nodeNumber = -1;
			}

			int returnedValue = editText(nodeNumber, address);
			// 1 : Success

			// -1 : lineToEdit is negative for some reason. not expected to happen
			// -2 : DocBuilder methods didnt work properly
			// -3 : TrialSerialNumber includes letters
			// -4 : Error saving the file. Maybe program wasn't run as admin?

			System.out.println(returnedValue);

			Object[] cl = { "Close" };

			switch (returnedValue) {
			case 1:
				JOptionPane.showOptionDialog(null, "Patch was successful. Enjoy!", "Patched",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, cl, cl[0]);
				System.exit(0);
			case -1:
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(null, "Patch failed. Unknown error.\nAdobe Patcher will now close",
						"Patch failed", JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			case -2:
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(null,
						"Adobe folder was not found. Try reinstalling Adobe product.\nAdobe Patcher will now close.",
						"Patch failed", JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			case -3:
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(null, "Unexpected Error. Patcher will now close.", "Patch Failed",
						JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			case -4:
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(null,
						"Error finalizing patch. Did you right click on\nAdobe Patcher and click 'Run as administrator'?\n Patcher will now close.",
						"Patch failed", JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			default:
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(null,
						"Something went horribly wrong. Doesn't look like\nthis program will work for this PC. Patcher will now close.",
						"Patch failed", JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			}

		}
	}

	private int editText(int nodeNumber, String Address) {
		if (nodeNumber < 0) {
			return -1;
		}

		// sets up starting variables.

		// test
		/*
		 * if (nodeNumber == 18) return 1;
		 */
		// test

		String fileAddress = (Address);
		DocumentBuilderFactory docFactory;
		DocumentBuilder docBuilder;
		Document doc;

		// new method
		try {
			docFactory = DocumentBuilderFactory.newInstance();
			docBuilder = docFactory.newDocumentBuilder();
			doc = docBuilder.parse(fileAddress);
		} catch (Exception e) {
			return -2;
		}

		Random random = new Random();
		int a;
		int b;

		// extract TrialSerialNumber from text

		Node trialNumNode = doc.getElementsByTagName("Data").item(nodeNumber);
		String trialNum = trialNumNode.getTextContent();
		System.out.println(trialNum);

		// ensure no letters in trialNum
		if (!trialNum.matches("[0-9]+"))
			return -3;

		// change one digit to a random number
		// while loop ensures a digit isn't replaced by the same digit
		String newTrialNumber = trialNum;
		while (newTrialNumber.equals(trialNum)) {
			a = random.nextInt(9);// new number
			b = random.nextInt(23);// index to change
			newTrialNumber = trialNum.substring(0, b) + a + trialNum.substring(b + 1, 24);
		}
		trialNumNode.setTextContent(newTrialNumber);

		System.out.println(newTrialNumber);
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);

			Result dest = new StreamResult(Address);
			transformer.transform(source, dest);
		} catch (Exception e) {
			e.printStackTrace();
			return -4;
		}

		// if all goes well, return success value of 1
		return 1;
	}
}
