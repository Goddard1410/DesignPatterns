//   DO NOT combine separate interfaces and classes in the same file! create separate files
package structure_patterns;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

class context {
//    context, or the class for extrinsic state, contains data for specific objects
    private double num;
    private flyweight fly;

    public context(double num, flyweight fly) {
        this.num = num;
        this.fly = fly;
    }

    public void post() {
        fly.post(num);
    }
}

public class flyweight {
//    a way to conserve memory via the sharing of common parts between multiple objects
//    separates common features (intrinsic) and object-specific (extrinsic)
//    the flyweight represents an object which only stores intrinsic states
//    flyweight factories can be created to manage large numbers of flyweights

//    creates a storage for intrinsic state to be referenced later (memory-intensive aspects)
    private String str;
    private Color color; // color is a memory intensive aspect which is why it is included here

    public flyweight(String str, Color color) {
        this.str = str;
        this.color = color;
    }

//    posts some data from the context, none from the flyweight (Note Color vs color)
    public void post(double num) {
        System.out.println("Color Aspect: " + Color.GREEN + " Double Aspect: " + num);
    }
}

class flyweightFactory {
//    creates a factory from which to organize and manage flyweights
    static Map<String, flyweight> flyweights = new HashMap<>(); // a hashmap is like a dictionary in python, having a key and some data associated with it

    public static flyweight getFlyweight(String str, Color color) { // same data as is used when creating the flyweight object
//        Gets the data from inside the flyweight
        flyweight result = flyweights.get(str);
//        instantiates a flyweight if one does not currently exist (ei. turns it into a singleton)
        if (result == null) {
            result = new flyweight(str, color);
            flyweights.put(str, result);
        }
        return result;
    }
}

class contextCollection {
    //    creates the end goal of the flyweight-context relation, to create large numbers of context objects
    private List<context> conts = new ArrayList<>();
    // these variables are pulled from the context and flyweight classes (all the data that the finished flyweight-context pair requires)
    public void createContext(double num, String str, Color color) {
        flyweight fly = flyweightFactory.getFlyweight(str, color);
        context cont = new context(num, fly); // inputs the flyweight as a parameter for the context, allowing the context to pull data from it
        conts.add(cont);
    }
    //    iterates through all context objects and tells them all to print
    public void post() {
        for (context cont: conts) {
            cont.post();
        }
    }
}

class go {
//    creates both the number of contexts being created by the contextCollection and the number of different variations of contexts (different extrinsic states)
    static int contextCount = 10000;
    static int contextTypes = 5;

    public void run() {
        contextCollection collect = new contextCollection();
        for (int i = 0; i < Math.floor(contextCount/contextTypes); i++) {
            collect.createContext(Math.random(), "Extrinsic Context 1", Color.CYAN);
            collect.createContext(Math.random(), "Extrinsic Context 2", Color.BLACK);
            collect.createContext(Math.random(), "Extrinsic Context 3", Color.YELLOW);
            collect.createContext(Math.random(), "Extrinsic Context 4", Color.GREEN);
        }
        System.out.println(contextCount + " contexts created");
        collect.post();
    }
}