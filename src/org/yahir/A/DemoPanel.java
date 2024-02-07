package org.yahir.A;

import org.yahir.A.node.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class DemoPanel  extends JPanel {
    final  int maxCol = 15;
    final int maxRow = 10;
    final int nodeSize = 70;
    final int screenWidth = nodeSize * maxCol;
    final int screenHeight = nodeSize * maxRow;
    //node
    Node[][] node = new Node[maxCol][maxRow];
    Node startNode, goalNode, currentNode;
    ArrayList<Node> openList = new ArrayList<>();
    ArrayList<Node> checkedList = new ArrayList<>();
    //others
    boolean goalReached = false;
    int step = 0;
    public DemoPanel (){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setLayout(new GridLayout(maxRow,maxCol));
        this.addKeyListener(new KeyHandler(this));
        this.setFocusable(true);
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

    public void search(){
        if(goalReached == false){

            int col = currentNode.col;
            int row = currentNode.row;

            currentNode.setAsChecked();
            checkedList.add(currentNode);
            openList.remove(currentNode);
             // open the up node

            if(row -1  >= 0)openNode(node[col][row -1]);
            // open the left node
           if(col - 1 >= 0) openNode(node[col - 1][row]);
            //open the down node
           if(row + 1 < maxRow) openNode(node[col][row +1]);
            //open the right node
            if(col + 1< maxCol)openNode(node[col + 1][row]);

            //find the best node
            int bestNodeIndex = 0;
            int bestNodefCost = 999;

            for(int i = 0; i < openList.size(); i++){
                    //check id this node's f cost is better
                if(openList.get(i).fCost < bestNodefCost){
                    bestNodeIndex = i;
                    bestNodefCost = openList.get(i).fCost;
                }
                //if f cost is equals , check the G cost
                else if(openList.get(i).fCost == bestNodefCost){
                    if(openList.get(i).gCost < openList.get(bestNodeIndex).gCost){
                        bestNodeIndex = i ;
                    }
                }
            }
                //after the loop
            currentNode = openList.get(bestNodeIndex);
            if(currentNode == goalNode){
                goalReached = true;
            }

        }
    }

    public void autoSearch(){
        while (!goalReached && step < 300){

            int col = currentNode.col;
            int row = currentNode.row;

            currentNode.setAsChecked();
            checkedList.add(currentNode);
            openList.remove(currentNode);
            //open the up node

            if(row -1  >= 0)openNode(node[col][row -1]);
            // open the left node
            if(col - 1 >= 0) openNode(node[col - 1][row]);
            //open the down node
            if(row + 1 < maxRow) openNode(node[col][row +1]);
            //open the right node
            if(col + 1< maxCol)openNode(node[col + 1][row]);

            //find the best node
            int bestNodeIndex = 0;
            int bestNodefCost = 999;

            for(int i = 0; i < openList.size(); i++){
                //check id this node's f cost is better
                if(openList.get(i).fCost < bestNodefCost){
                    bestNodeIndex = i;
                    bestNodefCost = openList.get(i).fCost;
                }
                //if f cost is equals , check the G cost
                else if(openList.get(i).fCost == bestNodefCost){
                    if(openList.get(i).gCost < openList.get(bestNodeIndex).gCost){
                        bestNodeIndex = i ;
                    }
                }
            }
            //after the loop
            currentNode = openList.get(bestNodeIndex);
            if(currentNode == goalNode){
                goalReached = true;
                trackThePath();
            }
        }
        step++;
    }
    private void  openNode(Node node){
        if(node.open == false && node.checked == false && node.solid == false){
            //cuando el nodo no este abierto se agrega a las lista
            node.setAsOpen();
            node.parent = currentNode;
            openList.add(node);
        }
    }
    private void trackThePath(){
        //bacjtrack and draw the best path
        Node current = goalNode;
        while (current != startNode){
            current = current.parent;
            if(current != startNode){
                current.setAsPath();
            }
        }
    }
}
