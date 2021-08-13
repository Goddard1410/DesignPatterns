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
interface common_interface {
    //    create a common interface for all functions to call upon, trying to be as encompassing as possible
    void print_int();
}
// or
abstract class common_abstract {
    public void print_abs() {
        System.out.println("New object creation");
    };
}

class runNewInt implements common_interface{
    public void print_int() {
        System.out.println("New object creation");
    }
}

class runNewAbs extends common_abstract {
//    prints out New object creation
    public void print_abs() {}
}

