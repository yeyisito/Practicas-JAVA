/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import utilityclassespackage.Edge;
import utilityclassespackage.Node;

/**
 *
 * @author dieca
 */
public class GraphView extends javax.swing.JPanel {

    //Node list to be displayed inside the graph
    private List<Node> nodes;
    //Edge lsit to be displayed inside the graph
    private List<Edge> edges;
    //This "selectedNode" will keep track of the last selected node for creating new edges
    private Node selectedNode = null;

    //Inicialize node and edge lists
    public GraphView() {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();

        //Mouse listener listening for clicks XD
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //Right click listener, check if the click was triggered over a node
                if (SwingUtilities.isRightMouseButton(e)) {
                    Node nodeToDestroy = getNodeAtPosition(e.getPoint());

                    if (nodeToDestroy != null) {
                        deleteNode(nodeToDestroy);
                    }
                } else {
                    //Check if there is any existing node 
                    Node clickedNode = getNodeAtPosition(e.getPoint());
                    if (clickedNode != null) {
                        if (selectedNode != null && selectedNode != clickedNode) {
                            double weight = getRealNumberWeight();
                            if (weight > 0) {
                                edges.add(new Edge(selectedNode, clickedNode, weight));
                            }
                            //Reset the selected node
                            selectedNode = null;
                        }else{
                            selectedNode=clickedNode;
                        }
                    }else{
                        //No node clicked, add new one
                        Point newPosition = adjustPositionIfOverlapping(e.getPoint());
                        Node newNode = new Node(nodes.size()+1, newPosition);
                        nodes.add(newNode);
                        //reset selectedNode
                        selectedNode= null;
                    }
                }
                //Redraw view to correctly display all nodes
                repaint();
            }
        });
    }

    private Node getNodeAtPosition(Point point) {
        //Check how close was the click to a node's position
        for (Node node : nodes) {
            Point position = node.getPosition();
            //Check wether a node is close to a 15 pixels radius(from the mouse)
            if (point.distance(position) < 20) {
                //Return if found
                return node;
            }
        }
        //or not found
        return null;
    }

    private void deleteNode(Node nodeToDestroy) {
        //Remove node from the list
        nodes.remove(nodeToDestroy);
        //Remove any edges that had been linked to the node
        edges.removeIf(edge -> edge.getFrom().equals(nodeToDestroy) || edge.getTo().equals(nodeToDestroy));
    }

    private void handleLeftClick(Point clickPoint) {
        //Create new node at the clicked position/check overlapping chance
        Point newPosition = adjustPositionIfOverlapping(clickPoint);
        Node newNode = new Node(nodes.size() + 1, newPosition);
        nodes.add(newNode);
        //If a node was previously selected, promt for weight and add it to the edge
        if (selectedNode != null) {
            double weight = getRealNumberWeight();

            if (weight > 0) {
                edges.add(new Edge(selectedNode, newNode, weight));
            }
            //Reset after creating new edge
            selectedNode = null;
        } else {
            selectedNode = newNode;
        }
    }

    //Method that will check if perchance a node is overlapping
    private Point adjustPositionIfOverlapping(Point newPosition) {
        int overlapRadius = 20;

        //Check for overlap with existing nodes
        for (Node node : nodes) {
            Point position = node.getPosition();
            //If distance < 15 px
            if (newPosition.distance(position) < overlapRadius) {
                //Adjust to avoid overlapping
                newPosition = new Point(newPosition.x + overlapRadius, newPosition.y + overlapRadius);
                //Recursive method
                return adjustPositionIfOverlapping(newPosition);
            }
        }
        return newPosition;

    }

    public Double getRealNumberWeight() {
        while (true) {
            String input = JOptionPane.showInputDialog("Enter a weight for the edge:");

            // Check if the user pressed "Cancel"
            if (input == null) {
                return null; // User canceled, return null to indicate no weight was entered
            }

            try {
                // Parse the input to a double
                double weight = Double.parseDouble(input);

                // Check if the weight is a positive real number
                if (weight >= 0) {
                    return weight; // Valid weight, return it
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a positive number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                // Display error message if the input is not a valid number
                JOptionPane.showMessageDialog(null, "Please enter a valid number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //method to add new nodes and redraw the view
    public void addNode(Node node) {
        nodes.add(node);
        //redraw the panel when a new node is added
        repaint();
    }

    //Method to add new edges and redraw the view
    public void addEdge(Edge edge) {
        edges.add(edge);
        //Redraw the panel when a new edge is added
        repaint();
    }

    //Overrides to draw nodes and edges
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //Call to the original paintComponent method
        Graphics2D g2 = (Graphics2D) g;

        //Draw edges and weights
        g2.setColor(Color.darkGray);
        for (Edge edge : edges) {
            //Get positions of the start and destiny (nodes)
            Point from = edge.getFrom().getPosition();
            Point to = edge.getTo().getPosition();
            //Draw line using Grapics 2D class
            g2.drawLine(from.x, from.y, to.x, to.y);

            // Draw weight at the middle of the edge
            String weightText = String.valueOf(edge.getWeight());
            int midX = (from.x + to.x) / 2;
            int midY = (from.y + to.y) / 2;
            // Set color for the weight text
            g2.setColor(Color.RED);
            g2.drawString(weightText, midX, midY);
            // Reset color for next edge
            g2.setColor(Color.GRAY);
        }

        //Draw Nodes
        g2.setColor(Color.BLUE);
        for (Node node : nodes) {
            //Get Node position
            Point position = node.getPosition();
            //Draw a circle for the nodes using Graphics2D class
            //Center and node size
            g2.fillOval(position.x - 10, position.y - 20, 30, 30);

            g2.setColor(Color.BLACK);
            //Set name position
            g2.drawString(node.getName(), position.x, position.y + 25);
            g2.setColor(Color.BLUE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 497, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
