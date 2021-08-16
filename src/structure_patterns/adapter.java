//   DO NOT combine separate interfaces and classes in the same file! create separate files
package structure_patterns;

import java.util.Objects;

class objFinal {
//    object which takes configured data for some purpose
    private String data;

    public objFinal(String data) {
        this.data = data;
    }
    public String getData() {
        return data;
    }
    public boolean sameType(objConfigured obj) {
//        do something with the configured data being inputted
//        in this case compare the datatypes in order to prove that adapter is working correctly
        try {
            System.out.println(Objects.equals(this.getData(), obj.getData()));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

class objConfigured {
//    data coming from adapter returned in this object
//    correct datatype to interact with objFinal
    private String data;

    public objConfigured() {}

    public objConfigured(String data) {
        this.data = data;
    }
    public String getData() {
        return data;
    }
}

class objRaw {
//    data going into adapter
//    incorrect datatype for objFinal
    private double data;

    public objRaw(double data) {
        this.data = data;
    }
    public double getData() {
        return data;
    }
}

public class adapter extends objConfigured {
//    creates the ability for one object's interface to be converted so that a different object can understand it
//    takes one type of data and converts it into a different type
//    more instances can be created for multiple data inputs and outputs
    private objRaw raw;

    public adapter(objRaw raw) {
        this.raw = raw;
    }

    @Override
    public String getData() {
        String result;
//        add logic to transform datatype
        result = String.valueOf(raw.getData());
        return result;
    }
}

class run {
    public void run() {
        objFinal fin = new objFinal("2.54");
        objConfigured conf = new objConfigured("1.18");
        objRaw raw = new objRaw(14.10);

//        will be true as fin and conf use the same datatype
        if (fin.sameType(conf)) {System.out.println("Datatypes are equivalent");}
//        fin.sameType(raw) will not compile as objFinal is expecting a objConfigured
//        instantiated an adapter to transform raw to configured
        adapter adapter = new adapter(raw);
//        will be true as adapter as transformed the raw obj into a configured one with the correct data type
        if (fin.sameType(adapter)) {System.out.println("Datatypes are equivalent");}

    }
}
