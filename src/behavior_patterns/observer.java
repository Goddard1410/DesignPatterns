//   DO NOT combine separate interfaces and classes in the same file! create separate files
package behavior_patterns;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

interface subscriber {
    void update(String id, String update);
}

class subscriber1 implements subscriber {
//    subscriber posts a notification to an email if the publisher updates
    String email;

    public subscriber1(String email) {
//        takes a user email to be notified
        this.email = email;
    }

    @Override
    public void update(String id, String update) {
//        system out represents the sending of the email to notify the user
        System.out.println("To: " + email + " Content: The publisher has updated to " + update);
    }
}

class publisher {
    Map<String, List<subscriber>> subscribers = new HashMap<>();

    public publisher() {

    }

    public void subscribe(String id, subscriber subscriber) {
        List<subscriber> subs = subscribers.get(id);
        subs.add(subscriber);
    }

    public void unsubscribe(String id, subscriber subscriber) {
        List<subscriber> subs = subscribers.get(id);
        subs.remove(subscriber);
    }

    public void notify(String id, String update) {

    }
}

public class observer {
//    allows for the updating of outside classes of the events of the "publisher" class
//    classes can "subscribe" to the changes of other classes
//    multiple publishers can use a common interface which can allow for quicker subscription
//    mediator is very similar, however observers add extra security by implementing a 1 way dataflow
}
