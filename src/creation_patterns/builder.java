package creation_patterns;

enum objType {
//    ex. of custom datatype
    type1, type2, type3
}

class clsType {
//    Custom class with functions which can be added
    public void post() {
        System.out.println("Custom class which can be added to object");
    }
}

class Product1 {
//    One object to be created with the builders
    private final boolean bool;
    private final int num;
    private final double doub;
    private final String str;
    private final objType type;
    private final clsType cls;

    public Product1(boolean bool, int num, double doub, String str, objType type, clsType cls) {
        this.bool = bool;
        this.num = num;
        this.doub = doub;
        this.str = str;
        this.type = type;
        this.cls = cls;
    }

//    Takes data and turns it into some function
    public String post() {
        String data = "";
        data += bool + " ,";
        data += num + " ,";
        data += doub + " ,";
        data += str + " ,";
        data += type + " ,";
        data += cls + " ,";
        return data;
    }
}

class Product2 {
    //    Second object to be created with the builders
    private final boolean bool;
    private final int num;
    private final double doub;
    private final String str;
    private final objType type;
    private final clsType cls;


    public Product2(boolean bool, int num, double doub, String str, objType type, clsType cls) {
        this.bool = bool;
        this.num = num;
        this.doub = doub;
        this.str = str;
        this.type = type;
        this.cls = cls;
    }

    //    Takes data and turns it into some different function
    public String post() {
        String data = "";
        data += bool + " ,";
        data += num + " ,";
        data += doub + " ,";
        data += str + " ,";
        data += type + " ,";
        data += cls + " ,";
        return data;
    }
}

interface build {
//    interface for all builders
    void setComp1(boolean bool);
    void setComp2(int num);
    void setComp3(double doub);
    void setComp4(String str);
    void setComp5(objType type);
    void setComp6(clsType cls);
}

//   Creates builders for object creation
class builder1 implements build {
    private boolean bool;
    private int num;
    private double doub;
    private String str;
    private objType type;
    private clsType cls;

    @Override
    public void setComp1(boolean bool) {
        this.bool = bool;
    }
    @Override
    public void setComp2(int num) {
        this.num = num;
    }
    @Override
    public void setComp3(double doub) {
        this.doub = doub;
    }
    @Override
    public void setComp4(String str) {
        this.str = str;
    }
    @Override
    public void setComp5(objType type) {
        this.type = type;
    }
    @Override
    public void setComp6(clsType cls) {
        this.cls = cls;
    }
//     returns new object variation
    public Product1 result() {
        return new Product1(bool, num, doub, str, type, cls);
    }
}

class builder2 implements build {
    private boolean bool;
    private int num;
    private double doub;
    private String str;
    private objType type;
    private clsType cls;

    @Override
    public void setComp1(boolean bool) {
        this.bool = bool;
    }
    @Override
    public void setComp2(int num) {
        this.num = num;
    }
    @Override
    public void setComp3(double doub) {
        this.doub = doub;
    }
    @Override
    public void setComp4(String str) {
        this.str = str;
    }
    @Override
    public void setComp5(objType type) {
        this.type = type;
    }
    @Override
    public void setComp6(clsType cls) {
        this.cls = cls;
    }
    public Product2 result() {
        return new Product2(bool, num, doub, str, type, cls);
    }

}

class director {
//    assembles all of the pieces together to create creation_patterns.one function that does it all for each variation
    public void constructVar1(build builder) {
        builder.setComp1(true);
        builder.setComp2(1410);
        builder.setComp3(1.18);
        builder.setComp4("Var1");
        builder.setComp5(objType.type1);
        builder.setComp6(new clsType());
    }
//    creates as many variations as needed
    public void constructVar2(build builder) {
        builder.setComp1(false);
        builder.setComp2(1619);
        builder.setComp3(2.54);
        builder.setComp4("Var2");
        builder.setComp5(objType.type3);
//        You can pass in parameters to the class if needed
        builder.setComp6(new clsType());
    }
}

public class builder {
//    used for constructing complex objects step by step with different types and representations being the goal
//    prevents the overuse of subclasses for individual variants of an item or constructors with a lot of parameters
//    implements multiple "creation_patterns.builder" objects which are used when creating the desired object
    public void run() {
        director dir = new director();
        builder1 b1 = new builder1();
        builder2 b2 = new builder2();

//      calls construct function, activating builders and creating object as product 1 (p1)
        dir.constructVar1(b1);
        Product1 p1 = b1.result();
        System.out.println(p1.post());
//      different creation_patterns.builder's take on the same data from variation 1
        dir.constructVar1(b2);
        Product2 p2 = b2.result();
        System.out.println(p2.post());

        //      calls construct function, activating builders and creating object as product 1 (p1)
        dir.constructVar2(b1);
        Product1 p1b = b1.result();
        System.out.println(p1b.post());
//      different creation_patterns.builder's take on the same data from variation 2
        dir.constructVar2(b2);
        Product2 p2b = b2.result();
        System.out.println(p2b.post());
    }
}
