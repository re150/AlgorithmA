package org.yahir.A;

import org.yahir.A.node.Node;

import javax.swing.*;
import java.awt.*;

public class DemoPanel  extends JPanel {
    final  int maxCol = 15;
    final int maxRow = 10;
    final int nodeSize = 70;
    final int screenWidth = nodeSize * maxCol;
    final int screenHeight = nodeSize * maxRow;
    //node
    Node[][] node = new Node[maxCol][maxRow];
    Node startNode, goalNode, currentNode;
    public DemoPanel (){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setLayout(new GridLayout(maxRow,maxCol));

        //place Nodes
        int col  = 0;
        int row = 0;

        while (col < maxCol && row < maxRow){
            node[col][row] = new Node(col,row);
            this.add(node[col][row]);

            col++;
            if(col == maxCol){
                col = 0;
                row++;
            }
        }

        // set start and goal node
        setStartNode(6,6);
        setGoalNode(11,3);
    }
    private void setStartNode(int col,int row){
        node[col][row].setAsStart();
        startNode = node[col][row];
        currentNode = startNode;
    }
    private void setGoalNode(int col,int row){
        node[col][row].setAsGoal();
        goalNode = node[col][row];
    }

}
