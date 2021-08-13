//   DO NOT combine separate interfaces and classes in the same file! create separate files
package creation_patterns;

public class factory_method {
    //    replaces direct object construction calls ei.
    public void post() {
        System.out.println("Old object creation");
    }
}

class runOld {
    public static void go() {
        factory_method post = new factory_method();
        post.post();
    }
}
// with
interface commonInt {
    //    create a common interface for all functions to call upon, trying to be as encompassing as possible
    void print_int();
}
// or create a common abstract class if you want universal logic in functions
abstract class commonAbstract {
    public void print_abs() {
        System.out.println("New object creation");
    };
}

class runNewInt implements commonInt{
    public void print_int() {
        System.out.println("New object creation");
    }
}

class runNewAbs extends commonAbstract {
//    prints out New object creation
    public void print_abs() {}
}

