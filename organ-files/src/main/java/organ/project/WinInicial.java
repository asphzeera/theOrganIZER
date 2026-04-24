package organ.project;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;

import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

import java.awt.Color;
import java.awt.GridBagLayout;


public class WinInicial extends JDialog implements ActionListener {
  JButton button = new JButton("Selecionar");
  JButton close = new JButton("Fechar");
  JFileChooser fc = new JFileChooser();
  File selectDirectory;

  File file;

  WinInicial(){

    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    int height = dimension.height;
    int width = dimension.width;

    setSize(width/2, height);

    setModal(true);
    setTitle("Selecionar Pasta");
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setLayout(new FlowLayout(FlowLayout.CENTER));

    setLocationRelativeTo(null);

    setLayout(new java.awt.GridBagLayout()); 

    getContentPane().setBackground(new Color(240, 240, 240));

    button.addActionListener(this);
    add(button);

    close.addActionListener(this);
    add(close);
    
  };

  public File directoryPath(){
    return this.selectDirectory;
  };
  
  @Override
  public void actionPerformed(ActionEvent e){
    fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    fc.setAcceptAllFileFilterUsed(false);
    if(e.getSource()==button) {
      int returnVal = fc.showOpenDialog(this);
      if(returnVal == JFileChooser.APPROVE_OPTION){
         this.selectDirectory = fc.getSelectedFile();
         dispose();
      } else {
        System.out.println("Nada foi selecionado");
      };
    }

    if(e.getSource()==close){
      dispose();
    };
  }
}
