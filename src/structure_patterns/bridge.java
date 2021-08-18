//   DO NOT combine separate interfaces and classes in the same file! create separate files
package structure_patterns;

interface implementation {
//    creates interface for all items (implementations)
    double aspect1();
    void aspect2();
    void aspect3(boolean data);
    void post();
}

class object1 implements implementation {
//    implementations being created, the base object for abstractions to add aspects to
    private String info;
    private boolean data;

    @Override
    public double aspect1() {
        return 254;
    }

    @Override
    public void aspect2() {
        info = "This is an aspect of this implementation";
    }

    @Override
    public void aspect3(boolean data) {
        this.data = data;
    }

    @Override
    public void post() {
        System.out.println("Object1: " + info + " " + data);
    }
}

class object2 implements implementation {
    private double info;
    private boolean data;

    @Override
    public double aspect1() {
        return 1410;
    }

    @Override
    public void aspect2() {
        info = 118;
    }

    @Override
    public void aspect3(boolean data) {
        this.data = data;
    }

    @Override
    public void post() {
        System.out.println("Object2: " + info + " " + data);
    }
}

interface abstraction {
//    creates interface for abstractions (control layers being added to original implementation)
    void aspect1();
    void aspect2();
}

class layer1 implements abstraction {
//    abstraction which takes attributes from implementation and modifies them for a different purpose
//    imp is protected so that later layers can access the same implementation instantiation
    protected implementation imp;

    public layer1() {}

    public layer1(implementation imp) {
        this.imp = imp;
    }

    @Override
    public void aspect1() {
//        takes in value of aspect1 and if it is 1410, aspect3 = true otherwise it is false
        imp.aspect3(imp.aspect1() == 1410);
    }

    @Override
    public void aspect2() {
//        changes internal variables per specific implementations
        imp.aspect2();
    }
}

class layer2 extends layer1 {
//    a second abstraction which builds upon the first, itself building on the implementation
    public layer2(implementation imp) {
//        accesses the implementation instantiation from layer1
        super.imp = imp;
    }
//    an extra aspect added by this layer
    public void post() {
        System.out.println("Layer2 Posting");
        imp.post();
    }
}


public class bridge {
//    allows for separation of closely related classes into hierarchies with aspects
//    allows for connections of those hierarchies instead of creating separate classes for each combination of aspects (ei. spaghetti code)
//    hierarchies are created as abstractions for higher-level layer addition (an aspect of the item being created) ei. a GUI and
//    implementations for platform construction and execution (the item that is being created and the work being done) ei. an API
public void init(implementation imp) {
//    creates an abstraction which uses the implementation's post with modified data from l1's aspect1 method
//    "bridge" is created between layer1 and object1
    layer1 l1 = new layer1(imp);
    l1.aspect1();
    imp.post();
//    creates an abstraction which inherits not only the implementation's methods, but also layer1's allowing for multi-layered objects while keeping the originals intact
//    creates a bridge between layer1 and layer2 (2 total bridges)
    layer2 l2 = new layer2(imp);
    l2.aspect2();
    l2.post();
}
    public void run() {
        init(new object1());
        init(new object2());
    }
}
    