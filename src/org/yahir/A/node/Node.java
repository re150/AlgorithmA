package org.yahir.A.node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.OptionalInt;

public class Node extends JButton implements ActionListener {

   public Node parent;
   public int col;
   public int row;
   public int gCost;
   public int hCost;
   public int fCost;
   boolean start;
   boolean goal;
   public boolean solid ;
   public boolean open;
   public boolean checked ;
   public Node(int col, int row) {
    this.col = col;
    this.row = row;

    setBackground(Color.black);
    setBackground(Color.white);
    addActionListener(this);

   }
   public void setAsStart() {
       setBackground(Color.white);
       setBackground(Color.blue);
       setText("Start");
       start = true;
   }
    public void setAsGoal() {
        setBackground(Color.black);
        setBackground(Color.YELLOW);
        setText("Goal");
        goal = true;
    }

    public void setAsSolid(){
       setBackground(Color.black);
       setForeground(Color.black);
       solid = true;
    }
    public void  setAsOpen(){
       open = true;
    }
    public void setAsChecked(){
        if(start == false && goal == false){
            setBackground(Color.black);
            setBackground(Color.ORANGE);
        }
        checked = true;
    }
    public void setAsPath(){
       setBackground(Color.black);
       setBackground(Color.green);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        setBackground(Color.orange);
    }

}
