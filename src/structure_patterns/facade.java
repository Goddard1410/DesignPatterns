//   DO NOT combine separate interfaces and classes in the same file! create separate files
package structure_patterns;
// adds a bunch of objects with methods, not all of which are needed when interacting
// the classes up to the facade can be pretty much anything that you want, the main thing is that the large number of classes and methods are condensed down to f.convertText()
class backEnd1 {
    private String name;
    private String type;

    public backEnd1(String name) {
        this.name = name;
        this.type = String.valueOf(name.length());
    }
    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
}

// interfaces can be added to library
interface backEndInt {
    String getType();
}
class backEnd2 implements backEndInt {
    public String type = "crab";

    @Override
    public String getType() {
        return type;
    }
}
class backEnd3 implements backEndInt {
    public String type = "music";

    @Override
    public String getType() {
        return type;
    }
}
class typeFactory {
    public static backEndInt type(backEnd1 b1) {
        String type = b1.getType();
        if (type.equals("3")) {
            return new backEnd2();
        } else {
            return new backEnd3();
        }
    }
}

public class facade {
//    an easy-to-use interface masking underlying complexity
//    includes features that are strictly necessary, limiting functionality
//    very useful when implementing complex libraries but only requiring a small amount of total functions
    public String convertText(String name) {
        backEnd1 b1 = new backEnd1(name);
        backEndInt sourceType = new typeFactory().type(b1);
        return sourceType.getType();
    }
}

class execute  {
    public void run() {
//        instantiates the facade and its 1 method, condensing all the backEnd objects, the interface, and the factory
        facade f = new facade();
        String convertedName = f.convertText("Kraken");
        System.out.println(convertedName);
    }
}
