//   DO NOT combine separate interfaces and classes in the same file! create separate files
package creation_patterns;

import java.util.ArrayList;
import java.util.List;

abstract class commonAbs {
//    abstract class for all objects to conform to
    public int num;
    public String str;

    public commonAbs () {}

    public commonAbs(commonAbs proto) {
//        if original has been instantiated then set internal variables equal to original's
        if (proto != null) {
            this.num = proto.num;
            this.str = proto.str;
        }
    }
    public abstract commonAbs clone();
}

class var1 extends commonAbs {
//    creates an object which can be cloned
    public int num;
    
    public var1() {}
    
    public var1(var1 proto) {
        super(proto);
        if(proto != null) {
            this.num = proto.num;
        }
    }
    public var1 clone() {
        return new var1(this);
    }
}

class var2 extends commonAbs {
    //    creates a different object which can be cloned
    public int num;
    public String str;

    public var2() {}

    public var2(var2 proto) {
        super(proto);
        if(proto != null) {
            this.num = proto.num;
            this.str = proto.str;
        }
    }
    public var2 clone() {
        return new var2(this);
    }
}

public class prototype {
//    allows the copying of objects without the original's class dependencies using abstract classes
//    objects supporting this copying or "cloning" are prototypes
//    can be used when combined with factories to create many prototypes quickly
    public void run() {
//        create array lists to keep track of originals and copies if needed
        List<commonAbs> regulars = new ArrayList<>();
        List<commonAbs> clones = new ArrayList<>();

//        create an object to act as the prototype
        var1 v1 = new var1();
        v1.num = 254;
        regulars.add(v1);
//        create clone of prototype
        var1 v1clone = v1.clone();
        clones.add(v1clone);

//        create a different object to act as the prototype
        var2 v2 = new var2();
        v2.num = 254;
        regulars.add(v2);
//        create clone of prototype
        var2 v2clone = v2.clone();
        clones.add(v2clone);

        System.out.println("Regulars: " + regulars);
        System.out.println("Clones: " + clones);
    }
}
