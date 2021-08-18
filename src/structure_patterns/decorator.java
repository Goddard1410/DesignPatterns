//   DO NOT combine separate interfaces and classes in the same file! create separate files
package structure_patterns;

interface commonInt {
//    creates a common interface for decorator and concrete component (obj being wrapped) to use
    void setData(String data);
    String getData();
}

class concreteComponent implements commonInt {
//    base object being
    private String data;

    @Override
    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String getData() {
        return data;
    }
}

class baseDecorator implements commonInt {
//    base decorator class (basically an adapter between the concrete component and the concrete decorators)
    private commonInt wrappee;
//    intakes the concrete component being wrapped
    baseDecorator(commonInt wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public void setData(String data) {
        wrappee.setData(data);
    }

    @Override
    public String getData() {
        return wrappee.getData();
    }
}

class decorator1 extends baseDecorator {
//    this decorator adds Unofficial © FRC Team 1410 Patent Pending Trademark to the end of the string
    boolean dataAdded = false;

    public decorator1(commonInt wrappee) {
        super(wrappee);
    }

    @Override
    public void setData(String data) {
        super.setData(data + " Unofficial © FRC Team 1410 Patent Pending Trademark");
        dataAdded = true;
    }

    @Override
    public String getData() {
//        removes the added data from the string if it has been added
        if (dataAdded) {
            return super.getData().substring(super.getData().length() - 52);
        } else {
            return super.getData();
        }
    }
}

class decorator2 extends baseDecorator {
    //    this decorator makes all data uppercase
    String originalData = null;

    public decorator2(commonInt wrappee) {
        super(wrappee);
    }

    @Override
    public void setData(String data) {
        originalData = data;
        super.setData(data.toUpperCase());
    }

    @Override
    public String getData() {
//        resets the data of the string via the original data saved internally
        if (originalData != null) {
            return originalData;
        } else {
            return super.getData();
        }
    }
}

public class decorator {
//    creates wrapper classes which let you easily add, edit, and delete behaviors from objects without editing the objects themselves
//    inheritance is static, meaning that you cannot modify the original class at runtime with it, only extend upon it
//    aggregation (obj1 contains obj2, obj2 is independent of 1) and composition (obj1 consists of obj2, 1 constructs and deconstructs 2, 2 is not independent of 1) can help solve this issue
//    multiple decorators can be used at once and can be interchanged
    public void run() {
//        instantiated the decorators and the concrete component nested within
        String input = "Text to be edited.";
        baseDecorator modified = new decorator1(new decorator2(new concreteComponent()));

        modified.setData(input);
        commonInt plain = new concreteComponent();
//        data is at first changed, then reset
        System.out.println("Input: " + input);
        System.out.println("Modified: " + plain.getData());
        System.out.println("Unmodified: " + modified.getData());
    }
}
