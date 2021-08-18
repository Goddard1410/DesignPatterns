//   DO NOT combine separate interfaces and classes in the same file! create separate files
package structure_patterns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

interface common {
//    creates a common interface for all components in the composite to use
    int getInt();
    boolean getBool();
    void setData(int num, boolean bool);
    void selectInt();
    void selectBool();
    void post();
}

abstract class baseObject implements common {
//    the "trunk of the tree", from which all other objects will branch out
    public int num;
    public boolean bool;
    private boolean intSelected = false;

    baseObject(int num, boolean bool) {
        this.num = num;
        this.bool = bool;
    }

    @Override
    public int getInt() {
        return num;
    }

    @Override
    public boolean getBool() {
        return bool;
    }

    @Override
    public void setData(int num, boolean bool) {
        this.num = num;
        this.bool = bool;
    }

    @Override
    public void selectInt() {
        intSelected = true;
    }

    @Override
    public void selectBool() {
        intSelected = false;
    }

    @Override
    public void post() {
        if (intSelected) {
            System.out.println("Base Int: " + getInt());
        } else {
            System.out.println("Base Bool: " + getBool());
        }
    }
}

class obj1 extends baseObject {
//    a "branch" object extending the base Object, adding its own aspects
    private final int modifier = 254;
    obj1(int num, boolean bool) {
//        accesses the num and bool variables from baseObject
        super(num, bool);
    }
//    modifies data provided from base with internal modifier making this object unique
    @Override
    public int getInt() {
        return num * modifier;
    }

    @Override
    public boolean getBool() {
        if (getInt() > 1410) {
            return bool;
        } else {
            return false;
        }
    }

    @Override
    public void post() {
        System.out.println("Obj1 Int: " + getInt());
        System.out.println("Obj1 Bool: " + getBool());
    }
}

class obj2 extends baseObject {
    //    a different "branch" object extending the base Object, adding its own aspects
    private final int modifier = 118;
    obj2(int num, boolean bool) {
//        accesses the num and bool variables from baseObject
        super(num, bool);
    }
    //    modifies data provided from base with internal modifier making this object unique
    @Override
    public int getInt() {
        return num * modifier;
    }

    @Override
    public boolean getBool() {
        if (getInt() > 1619) {
            return true;
        } else {
            return bool;
        }
    }

    @Override
    public void post() {
        System.out.println("Obj2 Int: " + getInt());
        System.out.println("Obj2 Bool: " + getBool());
    }
}

class compoundObj extends baseObject {
//    creates a compound object which implements pieces of other "branch" objects when creating its own aspects
//    creates a list of children of the base object
    protected List<baseObject> children = new ArrayList<>();

//    ... adds the ability for anything inheriting from the baseObject to be called as a component
//    functions below allow the addition and deletion of "branch" objects as a reference for the compound object to call form
    compoundObj(baseObject... components) {
//        resets num to 0 and bool to false
        super(0, false);
        add(components);
    }
    public void add(baseObject component) {
        children.add(component);
    }
    public void add(baseObject... components) {
        children.addAll(Arrays.asList(components));
    }
    public void remove(baseObject component) {
        children.remove(component);
    }
    public void remove(baseObject... components) {
        children.removeAll(Arrays.asList(components));
    }
    public void clear() {
        children.clear();
    }

    @Override
    public int getInt() {
        if (children.size() == 0) {
//            makes sure that there are child objects to call from
            return 0;
        }
//        takes all children, gets their num values, and adds it to the compound object's num variable
        for (baseObject child: children) {
            num += child.getInt();
        }
        return num;
    }

    @Override
    public boolean getBool() {
//        creates a count variable for true and false values from each child
        int count = 0;
        if (children.size() == 0) {
//            makes sure that there are child objects to call from
            return false;
        }
//        takes all children, gets their num values, and adds it to the compound object's num variable
        for (baseObject child: children) {
            if (child.getBool()) {
                count++;
            } else {
                count--;
            }
        }
//        returns true if the total count is even and false if it is odd
        bool = count % 2 == 0;
        return bool;
    }
//    sets data to the same values for all children
    @Override
    public void setData(int num, boolean bool) {
        for (baseObject child: children) {
            child.setData(num, bool);
        }
    }

//    sets selection for all children and also the base object
    @Override
    public void selectInt() {
        super.selectInt();
        for (baseObject child: children) {
            child.selectInt();
        }
    }
    @Override
    public void selectBool() {
        super.selectBool();
        for (baseObject child: children) {
            child.selectBool();
        }
    }
//    tells all children to post
    @Override
    public void post() {
        for (baseObject child: children) {
            child.post();
        }
    }
}
//creates a class which takes all the objects in the "tree" and executes them
class client {
    private executor ex;
    private compoundObj compObj = new compoundObj();

    public client() {
        ex = new executor();
    }
    public void loadObjs(baseObject... objs) {
        compObj.clear();
        compObj.add(objs);
        ex.post();
    }

    private class executor {
        int getInt() {
            return compObj.getInt();
        }
        boolean getBool() {
            return compObj.getBool();
        }
        void post() {
            compObj.post();
        }
    }
}

public class composite {
//    allows for composition of multiple objects into connected structures
//    structures can be treated as one object
//    only functions when the core model of your code can be represented as a "tree" (all objects linked, either themselves or through other connections, to one main hub)
//    uses a common interface for each "branch" allowing for easy obtaining of common data
    public void run() {
//        instantiates the client
        client c = new client();
//        loads all objs using the client, creating a tree of objs and compound objs
        c.loadObjs(
                new obj1(21, true),

                new compoundObj(
                        new obj1(1410, false),
                        new obj2(1410, true)
                ),
                
                new compoundObj(
                        new obj2(118, true),
                        new obj2(254, true),
                        new obj1(118, false)
                )
        );
    }
}
