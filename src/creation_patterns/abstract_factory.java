//   DO NOT combine separate interfaces and classes in the same file! create separate files
package creation_patterns;

interface one {
//    create an interface which generalizes items
//    creates first item hierarchy
    void print();
}

interface two {
    //    create an interface which generalizes items
    //    creates second item hierarchy
    void print();
}

class microOne implements one {
    public void print() {
        System.out.println("Micro One");
    }
}

class macroOne implements one {
    public void print() {
        System.out.println("Macro One");
    }
}

class microTwo implements two {
    public void print() {
        System.out.println("Micro Two");
    }
}

class macroTwo implements two {
    public void print() {
        System.out.println("Macro Two");
    }
}

interface Factory {
//    centralizes both families for implementation
    one createone();
    two createtwo();
}

class FactoryMicro implements Factory {

    @Override
    public one createone() {
        return new microOne();
    }

    @Override
    public two createtwo() {
        return new microTwo();
    }
}

class FactoryMacro implements Factory {

    @Override
    public one createone() {
        return new macroOne();
    }

    @Override
    public two createtwo() {
        return new macroTwo();
    }
}

public class abstract_factory {
    //      creates families of related objects, also allows new families to be added easily without modifying core code
    //      *warning* this can create unnecessary complexity and enterprise code if used on too small a scale
    private one o;
    private two t;
    
    public abstract_factory(Factory f) {
        o = f.createone();
        t = f.createtwo();
    }
    
    public void run() {
        o.print();
        t.print();
    }
}

