/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralnet;

/**
 *
 * @author Nicno90
 */
public class Layer
{
   // private double net;
    public double[] input;
    public Node[] node;
    
    public Layer(int numNodes, int numIn)
    {
        node = new Node[numNodes];
        
        for (int k = 0; k < node.length; k++)
        {
            node[k] = new Node(numIn);
        }
        input = new double[numIn];
    }
    
    
    public void feed()
    {
        double net;
        for (int k = 0; k < node.length; k++)
        {
            net = node[k].getValue();
            
            for (int i = 0; i < node[i].weights.length; i++)
            {
                net += input[i] * node[k].weights[i];
            }
            node[k].setValue(sig(net));
        }
    }
    
    private double sig(double net)
    {
        return 1/(1+Math.exp(-net));
    }
    
    public double[] outputVector()
    {
        double[] vec = new double[node.length];
        
        for (int k = 0; k < node.length; k++)
        {
            vec[k] = node[k].output;
        }
        return vec;
    }
    
    public double[] fire()
    {
        feed();
        return outputVector();
    }
    
    public Node[] getNode()
    {
        return node;
    }
    
    
}
