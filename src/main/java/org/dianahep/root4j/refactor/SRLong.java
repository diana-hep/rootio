package org.dianahep.root4j.refactor;

import org.dianahep.root4j.core.*;
import org.dianahep.root4j.interfaces.*;
import java.io.*;

public class SRLong extends SRSimpleType {
    String name;
    TBranch b;
    TLeaf l;

    SRLong(String name,TBranch b,TLeaf l){
        super(name,b,l);
    }

    @Override void read(RootInput buffer)throws IOException{
        array.add((int)entry,buffer.readLong());
        entry+=1L;
    }

    @Override void read()throws IOException{
        RootInput buffer = b.setPosition(l,entry);
        array.add((int)entry,buffer.readLong());
        entry+=1L;
    }

    @Override void readArray(RootInput buffer, int size)throws IOException{
        Long arr[]=new Long[size];
        for (int i=0;i<size;i++){
            arr[i]=buffer.readLong();
        }
        array.add((int)entry,arr);
        entry+=1L;
    }

    @Override void readArray(int size)throws IOException{
        RootInput buffer = b.setPosition(l,entry);
        Long arr[]=new Long[size];
        for (int i=0;i<size;i++){
            arr[i]=buffer.readLong();
        }
        array.add((int)entry,arr);
        entry+=1L;
    }

    @Override boolean hasNext(){
        return entry<b.getEntries();
    }

}