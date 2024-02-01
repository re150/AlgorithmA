package org.yahir.A;

import org.yahir.A.node.Node;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayDeque;

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
        setStartNode(3,6);
        setGoalNode(11,3);
        //PLace solid nodes
        setSolidNode(10,2);
        setSolidNode(10,3);
        setSolidNode(10,4);
        setSolidNode(10,5);
        setSolidNode(10,6);
        setSolidNode(10,7);
        setSolidNode(6,2);
        setSolidNode(7,2);
        setSolidNode(8,2);
        setSolidNode(9,2);
        setSolidNode(11,7);
        setSolidNode(12,7);
        setSolidNode(6,1);

        // set cost
        setCostOnNodes();
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
    private void setSolidNode(int col, int row){
        node[col][row].setAsSolid();
    }

    private void setCostOnNodes(){
        int col = 0;
        int row = 0;
        while (col < maxCol && row < maxRow){
            getCost(node[col][row]);
            col++;
            if(col == maxCol){
                col =0;
                row++;
            }
        }
    }
    private void getCost(Node node){
        //calcular el costo G (la distacioa del nodo de start)
        int xDistance = Math.abs(node.col - startNode.col);
         int yDistance = Math.abs(node.row - startNode.row);
         node.gCost = xDistance  +yDistance;

         //Get H cost (the distance from the goal node)
        xDistance = Math.abs(node.col - goalNode.col);
        yDistance = Math.abs(node.row - goalNode.row);
        node.hCost = xDistance + yDistance;

        // get F cost (the toal cost)
        node.fCost = node.gCost + node.hCost;
        // Display the cost on node
        if(node != startNode && node != goalNode){
            node.setText("<html>F:" + node.fCost + "<br>G:"
            + node.gCost + "</html> ");
        }
    }
}
