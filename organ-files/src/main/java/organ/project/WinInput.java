package organ.project;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.GridBagConstraints;
import java.awt.Insets;


/**
 * WinInput
 */
public class WinInput extends JDialog implements ActionListener {
  private  JTextField textField = new JTextField(20);
  private JButton btnConfirmar = new JButton("Confirmar");
  private String textInput;
  WinInput(){
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    int height = dimension.height;
    int width = dimension.width;
    setSize(width/2, height);

    setModal(true);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setLayout(new java.awt.GridBagLayout());
    setLocationRelativeTo(null);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10,10,10,10);
    gbc.fill = GridBagConstraints.NONE;

    gbc.gridx = 0;
    add(textField, gbc);

    gbc.gridy = 1;
    gbc.fill = GridBagConstraints.NONE;
    add(btnConfirmar, gbc);

    textField.addActionListener(this);
    btnConfirmar.addActionListener(this);
  };
  public String getText() {
    return this.textInput;
  }

  @Override
  public void actionPerformed(ActionEvent e){
    this.textInput = textField.getText();
    this.dispose();
  };
}
