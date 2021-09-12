//   DO NOT combine separate interfaces and classes in the same file! create separate files
package behavior_patterns;

import java.util.Stack;

class originator {
//    original object with data that can be changed
    private double num;
    private boolean bool;
    private String str;

//    very useful for logging, .push(new stuff) .pop() get rid of the top of the stack
//    operates on a last in first out system, so the most recent input is on the "top" of the stack
//    history storage could be moved to a different class for cleaner code, however this would decrease security
    private Stack<backup> history = new Stack<>();

//      functions for changing data
    public void setNum(double num) {
        this.num = num;
    }

    public void setBool(boolean bool) {
        this.bool = bool;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public void createBackup() {
//        creates a new backup which is modeled after the data of the originator and stores it in a stack
        if (str != null) {
            backup b = new backup(this, num, bool, str);
            history.push(b);
        }
    }

    public void restore() {
//        takes the most recent element from the top of the stack and restores originator to that element's cashed state
        if (!history.isEmpty()) {
            backup b = history.peek();
            history.pop();
            b.restore();
        } else {
            System.out.println("Make a backup before restoring!");
        }
    }
}

class backup {
//    the memento created in createBackup, stores the previous state of originator for use if a revertion is needed
    private originator origin;
    private double num;
    private boolean bool;
    private String str;

    public backup(originator origin, double num, boolean bool, String str) {
        this.origin = origin;
        this.num = num;
        this.bool = bool;
        this.str = str;
    }

    public void restore() {
        origin.setNum(num);
        origin.setBool(bool);
        origin.setStr(str);
    }
}

class caretaker {
//    a mediator between the client and the originator, which interacts with only the originator object and has no access to the data of the mementos
    private originator origin;

    public void makeBackup() {
        origin.createBackup();
    }

    public void undo() {
        origin.restore();
    }

    public void setData(int num, boolean bool, String str) {
//        makes a backup before changing values so that reverting is possible
        makeBackup();
        origin.setNum(num);
        origin.setBool(bool);
        origin.setStr(str);
    }
}

public class memento {
//    saves and restores previous states of an object without reveling details of implementation
//    allows for tight access restrictions on objects while still allowing for backups
//    details about the original object are stored in the memento which only gives access to those details to the original object
//    can only be communicated to through a limited interface giving up only the necessary information
//    the command pattern has great use here when wanting to limit interaction with the memento even further
//    USE GARBAGE COLLECTION to destroy old mementos, otherwise no ram fo you
//    as you can see, this can cause spegetti code and
    public void main() {
        caretaker caretaker = new caretaker();

//        sets data for originator, then sets new data, then reverts to the old
        caretaker.setData(1410, true, "#1 at Utah");
        caretaker.setData(254, false, "#not at Utah");
        caretaker.undo();
    }
}
