import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.UIManager;

public class AddProcessorGUI extends JFrame {

	// Opens GUI to add new item
	public static void openGUI() {
		AddProcessorGUI frame = new AddProcessorGUI();
		frame.setVisible(true);
	}

	// GUI Layout, buttons and listeners
	public AddProcessorGUI() {
		setResizable(false);
		setTitle("Add Processor");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 444, 275);

		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setLayout(null);

		JLabel model_label = new JLabel("Model:");
		model_label.setBounds(19, 63, 60, 14);
		model_label.setVerticalAlignment(SwingConstants.TOP);
		model_label.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(model_label);

		JTextField model = new JTextField();
		model.setBounds(108, 60, 96, 20);
		model.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(model);
		model.setColumns(10);

		JTextField clockSpeed = new JTextField();
		clockSpeed.setBounds(108, 85, 96, 20);
		clockSpeed.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(clockSpeed);
		clockSpeed.setColumns(10);

		JLabel voltage_label = new JLabel("Voltage:");
		voltage_label.setBounds(19, 88, 71, 14);
		voltage_label.setVerticalAlignment(SwingConstants.TOP);
		voltage_label.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(voltage_label);

		JLabel clockSpeed_label = new JLabel("Clock speed:");
		clockSpeed_label.setBounds(19, 113, 67, 14);
		clockSpeed_label.setHorizontalAlignment(SwingConstants.LEFT);
		clockSpeed_label.setVerticalAlignment(SwingConstants.TOP);
		panel.add(clockSpeed_label);

		JTextField voltage = new JTextField();
		voltage.setBounds(108, 110, 96, 20);
		voltage.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(voltage);
		voltage.setColumns(10);

		JTextPane processor_info = new JTextPane();
		processor_info.setEditable(false);
		processor_info.setBackground(UIManager.getColor("Button.background"));
		processor_info.setText("Insert data to all fields and click \"add\"");
		processor_info.setBounds(8, 11, 408, 41);
		panel.add(processor_info);

		JLabel stepping_label = new JLabel("Stepping:");
		stepping_label.setVerticalAlignment(SwingConstants.TOP);
		stepping_label.setHorizontalAlignment(SwingConstants.LEFT);
		stepping_label.setBounds(19, 137, 71, 14);
		panel.add(stepping_label);

		JTextField stepping = new JTextField();
		stepping.setHorizontalAlignment(SwingConstants.LEFT);
		stepping.setColumns(10);
		stepping.setBounds(108, 135, 96, 20);
		panel.add(stepping);

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 426, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE).addContainerGap()));

		// Add-button and listener
		JButton add_button = new JButton("Add");
		add_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// If input fields are empty, show prompt
				if (model.getText().equals("") || clockSpeed.getText().equals("") || voltage.getText().equals("")
						|| stepping.getText().equals("")) {
					JOptionPane.showMessageDialog(contentPane, "Type data into all fields", "Warning!",
							JOptionPane.ERROR_MESSAGE);
				} else {
					// Else run add-method with values taken from input-fields
					try {
						Methods.addProcessor(model.getText(), clockSpeed.getText(), voltage.getText(),
								stepping.getText());
						model.setText("");
						clockSpeed.setText("");
						voltage.setText("");
						stepping.setText("");

						// Catch errors
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		add_button.setBounds(8, 190, 89, 23);
		panel.add(add_button);
		contentPane.setLayout(gl_contentPane);

		// Button for clearing input-fields
		JButton clear_button = new JButton("Clear");
		clear_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setText("");
				clockSpeed.setText("");
				voltage.setText("");
				stepping.setText("");
			}
		});
		clear_button.setBounds(115, 190, 89, 23);
		panel.add(clear_button);

		// Button to close add-item window
		JButton close_button = new JButton("Close");
		close_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		close_button.setBounds(310, 190, 89, 23);
		panel.add(close_button);

	}
}
