package utilityclassespackage;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author dieca
 */
import java.awt.Point;
public class Node {
    //Each node requires a specific identificator
    private int id;
    //Position of the point inside the graph
    private Point position;
    
    private String nodeName;
    
    //Class constructor
    public Node(int id, Point position){
        this.id = id;
        this.position = position;
        this.nodeName= "Node" + id;
    }
    
    //Getter Metods and Setter Metods
    public int getId(){
        return id;
    }
    
    public Point getPosition(){
        return position;
    }
    
    public String getName(){
        return nodeName;
    }
}
