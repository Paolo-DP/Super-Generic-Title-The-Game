/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADTs.Graph;

import java.util.ArrayList;
//import java.util.Iterator;

/**
 *
 * @author student
 */
public class GraphDP <E>{
    int MAX_V = 100;
    private ArrayList<E> vertices = new ArrayList<>();
    private int[][] edges = new int[MAX_V][MAX_V];
    int numvertex = 0;
    
    public void Graph(){
        for(int i=0; i<MAX_V; i++){
            for(int j=0; j<MAX_V; j++){
                edges[i][j] = 0;
            }
        }
    }
    
    public void addVertex(E newvert){
        if(!vertices.contains(newvert)){
            vertices.add(newvert);
            numvertex++;
        }
    }
    
    public void addconnection(E tail, E head, int weight){
        if(vertices.contains(tail) && vertices.contains(head)){
            edges[vertices.indexOf(tail)][vertices.indexOf(head)] = weight;
        }
    }
    
    public void removeconnection(E tail, E head){
        if(vertices.contains(tail) && vertices.contains(head)){
            edges[vertices.indexOf(tail)][vertices.indexOf(head)] = 0;
        }
    }
    
    public void removeVertex(E vertex){
        if(vertices.contains(vertex)){
        int v = vertices.indexOf(vertex);
        for(int i=v; i<vertices.size(); i++){
            for(int j=0; j<vertices.size(); j++){
                edges[i][j]=0;
            }
        }
        vertices.remove(v);
        }
    }
    
    public boolean hasedge(E tail, E head){
        return edges[vertices.indexOf(tail)][vertices.indexOf(head)] > 0;
    }
    
    public int getedgeweight(E tail, E head){
        if(hasedge(tail, head)){
            return edges[vertices.indexOf(tail)][vertices.indexOf(head)];
        }
        else
            return Integer.MAX_VALUE;
    }
    
    public ArrayList getAdjecency(E tail){
        ArrayList<E> nextto = new ArrayList<>();
        int i=vertices.indexOf(tail);
        for(int j=0; j<vertices.size(); j++){
            if(edges[i][j]>0){
                nextto.add(vertices.get(j));
            }
        }
        
        return nextto;
        
    }
    
    public int getNumConnections(E vertex){
        int v = vertices.indexOf(vertex);
        int numconnect=0;
        for(int j=0; j<vertices.size(); j++){
            if(edges[v][j]>0){
                numconnect++;
            }
        }
        return numconnect;
    }
    
    public ArrayList getVertices(){
        return vertices;
    }
    
    public int getnumvert(){
        return vertices.size();
    }
    public int getIndexV(E vert){
        return vertices.indexOf(vert);
    }
    
    public boolean contains(E vert){
        return vertices.contains(vert);
    }
    public boolean isEmpty(){
        return vertices.isEmpty();
    }
    
}
