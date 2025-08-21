package com.mycompany.graphsvisualisationanduses;

import java.awt.BorderLayout;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import utilityclassespackage.Edge;
import utilityclassespackage.Node;
import views.GraphView;

/**
 *
 * @author dieca
 */
public class GraphsVisualisationAndUses {

    public static void main(String[] args) {
        // Run in the Graphics Interface thread
        /* Lambda expression to queue the task to be run on the Event Dispatch
        Thread, which is used to manage the UI-related task in swing applications
        to prevent issues at the moment of modifying something of the UI*/
        SwingUtilities.invokeLater(() -> {
            //Create program's main window
            JFrame frame = new JFrame("Graphs Visualization and Uses");
            GraphView graphView = new GraphView(); //Panel that where the graph is going to be displayed

            JLabel instructionsLabel = new JLabel("Click izquierdo para agregar un nodo y doble click entre nodos para agregar aristas y ponderación / Click derecho para borrar un nodo.");
            instructionsLabel.setHorizontalAlignment(JLabel.CENTER);

//            Node node1 = new Node(1, new Point(100, 100));
//            Node node2 = new Node(2, new Point(300, 100));
//            Node node3 = new Node(3, new Point(200, 200));
//            Node node4 = new Node(4, new Point(400, 200));
//            Node node5 = new Node(5, new Point(450, 300));
//
//            //Add Nodes to graph's view
//            graphView.addNode(node1);
//            graphView.addNode(node2);
//            graphView.addNode(node3);
//            graphView.addNode(node4);
//            graphView.addNode(node5);
//
//            //Create links between nodes
//            //Edge between node1 and node2
//            graphView.addEdge(new Edge(node1, node2, 50));
//            //Edge between node2 and node3
//            graphView.addEdge(new Edge(node2, node3, 20));
//            //Edge between node3 and node1
//            graphView.addEdge(new Edge(node3, node1, 30));
//            //Edge bewtween node4 and node5
//            graphView.addEdge(new Edge(node4, node5, 25));
//            //Edge between node 5 and node1
//            graphView.addEdge(new Edge(node5, node1, 44));
            
            //Frame config
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(graphView, BorderLayout.CENTER);
            frame.add(instructionsLabel, BorderLayout.NORTH);
            frame.add(graphView, BorderLayout.CENTER); 
            frame.setSize(1920, 1080);
            frame.setVisible(true);
        });

    }
}
