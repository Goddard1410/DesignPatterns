//   DO NOT combine separate interfaces and classes in the same file! create separate files
package behavior_patterns;

import java.util.Objects;
import java.util.HashMap;

abstract class handler {
//    base handler class
    private handler next;

    public handler nextHandler(handler next) {
//        bserverlds the queue/chain of handlers
        this.next = next;
        return next;
    }

//    method increments down the chain, passing potentially modified data to next handler
    public abstract boolean check(String data);

//    runs check on next handler of chain or ends chain if this is the last handler
    protected boolean checkNext(String data) {
         if (next == null) {
            return true;
        }
        return next.check(data);
    }
}

class handler1 extends handler {
    private final int checkSum1;
    private final int checkSum2;

    public handler1(int checkSum1, int checkSum2) {
//        objects can also be passed through for external functions
        this.checkSum1 = checkSum1;
        this.checkSum2 = checkSum2;
    }

    public boolean check(String data) {
//        logic in this method modifies data/ends progression if needed
        if (checkSum1 != data.length() || checkSum2 != data.length()) {
//            example logic which takes a given checkSum and compares it to the length of the data
            System.out.println("Checksum caught an error!");
            return false;
        }
        return checkNext(data);
    }
}

class handler2 extends handler {
    public boolean check(String data) {
//        logic in this method modifies data/ends progression if needed
        if (Objects.equals(data, "special data")) {
//            example logic which stops the chain and ends positively if a specific type of data is entered
            System.out.println("Special Output!");
            return true;
        }
        System.out.println("Regular Output");
        return checkNext(data);
    }
}

class handler3 extends handler {
    private server server;

    public handler3(server server) {
        this.server = server;
    }

    public boolean check(String data) {
//        logic in this method modifies data/ends progression if needed
//        example logic which verifies data from the server object
        if (!server.hasData(data)) {
            System.out.println("Data is not registered to server");
            return false;
        }

        return checkNext(data);
    }
}

class server {
    private HashMap<String, Integer> dataMap = new HashMap<>();
    private handler handler;
//    a class for starting the chain, storing, and accessing data

    public void sethandler(handler handler) {
        this.handler = handler;
    }

    public boolean start(String data) {
        if (handler.check(data)) {
            System.out.println("Chain successful!");
            return true;
        }
        return false;
    }

    public void register(String data, int num) {
        dataMap.put(data, num);
    }

    public boolean hasData(String data) {
        return dataMap.containsKey(data);
    }
}

public class chain_of_responsibility {
//    queues and executes a series of tasks with variable outputs
//    composed of a chain of handlers which each decide to alter, end, and/or pass on the request
//    isolates handlers and prevents messy concrete ties between multiple different ones
//    can be structured so that data is only processed by one handler, ending the process
//    one abstract class for all handlers allows for easy management and sequencing
    private static server server;

    private static void init() {
//        registers all valid inputs (checked in handler 3)
        server = new server();
        server.register("1410", 254);
        server.register("special data", 118);

//        instantiates all handlers in the chain in any order wanted
        handler handler = new handler1(4, 12);
        handler.nextHandler(new handler2()).nextHandler(new handler3(server));
        
//        gives the chain to the server, allowing the server to start the chain
        server.sethandler(handler);
    }

    public static void run() {
        init();
//        tells the server to start the chain created above
        server.start("1410");
    }
}
