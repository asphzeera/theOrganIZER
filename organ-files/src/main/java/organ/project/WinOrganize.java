package organ.project;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WinOrganize extends JDialog implements ActionListener{

  JButton yesButton = new JButton("Sim");
  JButton noButton = new JButton("Não");
  JLabel label = new JLabel("Deseja organizar também as sub-pastas?");
  private boolean returnval;
  
  WinOrganize(){
    setModal(true);
    setTitle("O programa deve organizar arquivos dentro de pastas?");
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setLayout(new FlowLayout(FlowLayout.CENTER));
    setSize(300, 300);

    yesButton.addActionListener(this);
    noButton.addActionListener(this);

    yesButton.setBorderPainted(true);

    noButton.setForeground(Color.RED);

    add(label);
    add(yesButton);
    add(noButton);
  };


  @Override
  public void actionPerformed(ActionEvent e){
    if(e.getSource()==yesButton){
       returnval = true;
       dispose();
    }else if(e.getSource()==noButton){
      returnval = false;
      dispose();
    };
  };

  public boolean getResult(){
    return this.returnval;
  };
};
