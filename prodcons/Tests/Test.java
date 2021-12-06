package prodcons.Tests;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import prodcons.MessageQueue;
import prodcons.v1.ProdConsBuffer;

public class Test {
    public static void main(String[] args){
        Properties properties = new Properties(); 
        try {
            properties.loadFromXML(Test.class.getResourceAsStream("options.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        } 
        System.out.println(properties);
        int nProd = Integer.parseInt(properties.getProperty("nProd")); 
        int nCons = Integer.parseInt(properties.getProperty("nCons"));
        int bufSz = Integer.parseInt(properties.getProperty("bufSz")); 
        int prodTime = Integer.parseInt(properties.getProperty("prodTime"));
        int consTime = Integer.parseInt(properties.getProperty("consTime")); 
        int minProd = Integer.parseInt(properties.getProperty("minProd"));
        int maxProd = Integer.parseInt(properties.getProperty("maxProd"));
        
        ProdConsBuffer buffer = new ProdConsBuffer(bufSz);

        Consumer consumers[] = new Consumer[nCons];
        Producer producers[] = new Producer[nProd];
        
        for(int i=0; i < nCons; i++){
            consumers[i] = new Consumer(buffer, i, consTime);
        }
        System.out.println("ncons: " + nCons);
        for(int i=0; i < nProd; i++){
            producers[i] = new Producer(buffer, i, prodTime, minProd, maxProd);
        }
        System.out.println("nprod: " + nProd);

    }    
}
