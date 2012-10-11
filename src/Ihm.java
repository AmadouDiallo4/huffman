import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
@SuppressWarnings("serial")
public class Ihm extends javax.swing.JFrame {
	private JPanel southPanel;
	private JPanel northPanel;
	private JButton boutonDecompresser;
	private JButton boutonCompressFast;
	private JButton boutonCompress;
	private JTextField fieldNomFichier;
	private JButton boutonExit;
	private JPanel centerPanel;
	private JLabel label = new JLabel("Entrez le nom de votre fichier :");

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Ihm inst = new Ihm();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public Ihm() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				southPanel = new JPanel();
				FlowLayout southPanelLayout = new FlowLayout();
				southPanelLayout.setAlignment(FlowLayout.RIGHT);
				getContentPane().add(southPanel, BorderLayout.SOUTH);
				southPanel.setLayout(southPanelLayout);
				{
					boutonExit = new JButton();
					southPanel.add(boutonExit);
					boutonExit.setText("Exit");
					boutonExit.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							System.out
									.println("boutonExit.actionPerformed, event="
											+ evt);
							System.exit(0);
						}
					});
				}
			}
			{
				northPanel = new JPanel();
				FlowLayout northPanelLayout = new FlowLayout();
				northPanelLayout.setAlignment(FlowLayout.LEFT);
				getContentPane().add(northPanel, BorderLayout.NORTH);
				northPanel.setLayout(northPanelLayout);
				{
					fieldNomFichier = new JTextField();
					northPanel.add(label);
					northPanel.add(fieldNomFichier);
					fieldNomFichier.setText("mon fichier");
				}
			}
			{
				centerPanel = new JPanel();
				GridBagLayout centerPanelLayout = new GridBagLayout();
				centerPanelLayout.rowWeights = new double[] { 0.1, 0.1, 0.1,
						0.1 };
				centerPanelLayout.rowHeights = new int[] { 7, 7, 7, 7 };
				centerPanelLayout.columnWeights = new double[] { 0.1, 0.1, 0.1,
						0.1 };
				centerPanelLayout.columnWidths = new int[] { 7, 7, 7, 7 };
				getContentPane().add(centerPanel, BorderLayout.CENTER);
				centerPanel.setLayout(centerPanelLayout);
				centerPanel.setPreferredSize(new java.awt.Dimension(390, 187));
				{
					boutonCompress = new JButton();
					centerPanel.add(boutonCompress, new GridBagConstraints(0,
							1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					boutonCompress.setText("Compresser");
					boutonCompress.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							boutonCompressActionPerformed(evt);
						}
					});
				}
				{
					boutonCompressFast = new JButton();
					centerPanel.add(boutonCompressFast, new GridBagConstraints(
							0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					boutonCompressFast.setText("CompressFast");
					boutonCompressFast.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							boutonCompressFastActionPerformed(evt);
						}
					});
				}
				{
					boutonDecompresser = new JButton();
					centerPanel.add(boutonDecompresser, new GridBagConstraints(
							0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					boutonDecompresser.setText("Decompresser");
					boutonDecompresser.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							try {
								boutonDecompresserActionPerformed(evt);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					});
				}
			}
			pack();
			setSize(400, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void boutonCompressActionPerformed(ActionEvent evt) {
		System.out.println("boutonCompress.actionPerformed, event=" + evt);

		System.out.println("compression de : " + fieldNomFichier.getText()
				+ " en cours");
		Compression.main(fieldNomFichier.getText());
		JOptionPane.showMessageDialog(null, "Compression de "
				+ fieldNomFichier.getText() + " termine", "42",
				JOptionPane.INFORMATION_MESSAGE);
	}

	private void boutonCompressFastActionPerformed(ActionEvent evt) {
		System.out.println("boutonCompressFast.actionPerformed, event=" + evt);

		System.out.println("compression rapide de : "
				+ fieldNomFichier.getText() + " en cours");
		CompressFast.main(fieldNomFichier.getText());
		JOptionPane.showMessageDialog(null, "Compression rapide de "
				+ fieldNomFichier.getText() + " termine", "42",
				JOptionPane.INFORMATION_MESSAGE);
	}

	private void boutonDecompresserActionPerformed(ActionEvent evt)
			throws IOException {
		System.out.println("boutonDecompresser.actionPerformed, event=" + evt);

		System.out.println("decompression de : " + fieldNomFichier.getText()
				+ " en cours");
		Decompression.main(fieldNomFichier.getText());
		JOptionPane.showMessageDialog(null, "Decompression de "
				+ fieldNomFichier.getText() + " termine", "42",
				JOptionPane.INFORMATION_MESSAGE);
	}

}
