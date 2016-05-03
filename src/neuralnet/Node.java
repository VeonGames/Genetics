package neuralnet;

import genes.Cell;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nicno90
 */
public class Node
{
    public double output;
    public double[] weights;
    public double threshold;
    
    public Node(int numNodes)
    {
        weights = new double[numNodes];
        initialWeights();
    }
    
    public void initialWeights()
    {
        threshold = Math.random() * 2 -1;
        for (int k = 0; k < weights.length; k++)
        {
            weights[k] = Math.random() * 2 -1;
        }
    }

    public double[] getWeights()
    {
        return weights;
    }

    public double getValue()
    {
        return threshold;
    }
    
    public void setValue(double output)
    {
        threshold = output;
    }
    
}
