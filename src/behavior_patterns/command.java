//   DO NOT combine separate interfaces and classes in the same file! create separate files
package behavior_patterns;

import java.util.Random;

abstract class commandInterface {
//    creates a common interface for all commands to implement
    public receiver receive;

    commandInterface(receiver receive) {
        this.receive = receive;
    }
//    creates a method which contains the bulk of the command returning a boolean once the command is finished running
    public abstract boolean execute();
}

class command1 extends commandInterface {

    public command1(receiver receive) {
//        grabs the receiver object from the invoker so that it can be used
        super(receive);
    }

    @Override
    public boolean execute() {
//        this command modifies a string in the receiver (for frc think the mechanism)
        if (receive.string1 == null || receive.string1.isEmpty()) {
//            makes sure that there is a string to modify
            return false;
        }
//        in this case, the command converts the string to upper case
        receive.string1.toUpperCase();
        return true;
    }
}

class command2 extends commandInterface {

    public command2(receiver receive) {
        super(receive);
    }

    @Override
    public boolean execute() {
//        this command calls receiver (for frc think the mechanism) method
        try {
            receive.string1 = receive.getData();
            receive.post();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
}

class invoker {
//    a class which executes and logs commands to act on the receiver
//    acts as a mediator class controlling the commands and receiver
    private receiver receive;

//    runCmd method could be called in an iterator or chain of command
//    passes the receiver (mechanism) object to each command so that it can modify it or run methods
    public void runCmd1() {
        executeCommand(new command1(receive));
    }

    public void runCmd2() {
        executeCommand(new command2(receive));
    }

    private void executeCommand(commandInterface command) {
        if (command.execute()) {
            System.out.println("Command executed");
        } else {
            System.out.println("There was an error");
        }
    }
}

class receiver {
//    instantiated the internal variables and functions within the receiver (mechanism)
    public String string1;
    private Random rand = new Random();

    public void post() {
        System.out.println(string1);
    }

    public String getData() {
//        picks a random int between 0 and 999
        return rand.nextInt(1000) + "big datas";
    }
}

public class command {
//    turns a request for a class to complete some task into a separate object allowing for very tight control over when, how, and with what classes a request can execute
//    single responsibility principle, decouples class with executable functions from classes which call those functions
//    open closed principle, you can introduce new commands without breaking existing code
//    unifies multiple requests into one or two more condensed requests
//    when implementing a large number of commands, iterators and/or chains of responsibility should be used
    public static void main() {
        invoker invoke = new invoker();

        invoke.runCmd1();
        invoke.runCmd2();
    }
}
