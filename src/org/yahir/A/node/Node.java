package org.yahir.A.node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Node extends JButton implements ActionListener {

   Node parent;
   int col;
   int row;
   int gCost;
   int hCost;
   int fCost;
   boolean start;
   boolean goal;
   boolean solid ;
   boolean open;
   boolean checked ;

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

    @Override
    public void actionPerformed(ActionEvent e) {
        setBackground(Color.orange);
    }
}
