package prodcons.Tests;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import prodcons.MessageQueue;
import prodcons.v6.ProdConsBuffer;

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
        
        for(int i=0; i < nCons; i++)
            consumers[i] = new Consumer(buffer, i, consTime, 1);

        
        for(int i=0; i < nProd; i++)
            producers[i] = new Producer(buffer, i, prodTime, minProd, maxProd, 1);
        
    
        //========== V2 ===========
        try {
            for(int i=0; i < nProd; i++)
                producers[i].join();

            buffer.finish();

            for(int i=0; i < nCons; i++)
               consumers[i].interrupt();
           
            for(int i=0; i < nCons; i++)
               consumers[i].join();

        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("\nOK 1/3\n\n");


        //========== V5 ===========
        buffer = new ProdConsBuffer(10);
        Producer p1 = new Producer(buffer, 1, 1, 20, 20, 1);
        Consumer c1 = new Consumer(buffer, 1, 2, 5);
        Consumer c2 = new Consumer(buffer, 2, 2, 1);

        try{
            p1.join();
            c1.interrupt();
            c2.interrupt();
            c1.join();
            c2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        

        System.out.println("\nOK 2/3\n\n");
        
        Consumer c3, c4;
        Producer p2;
        buffer = new ProdConsBuffer(3);
        
        p1 = new Producer(buffer, 1, 1, 3, 3, 3);
        p2 = new Producer(buffer, 2, 1, 6, 6, 1);
        
        c1 = new Consumer(buffer, 1, 2, 3);
        c2 = new Consumer(buffer, 2, 2, 1);
        c3 = new Consumer(buffer, 3, 2, 1);
        c4 = new Consumer(buffer, 4, 2, 1);


        try{
            p1.join();
            p2.join();
            c1.interrupt();
            c2.interrupt();
            c3.interrupt();
            c4.interrupt();
            c1.join();
            c2.join();
            c3.join();
            c4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        
        System.out.println("\nOK 3/3\n\n");
    }    
}
