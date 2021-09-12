//   DO NOT combine separate interfaces and classes in the same file! create separate files
package behavior_patterns;

import java.util.ArrayList;

interface componentInt {
//    creates an interface for components (objects trying to communicate with each other ex. shooter telling drivetrain to stop before firing)
    void setMediator(concreteMediator mediator);
//    returns the id of the component
    int getId();
}

class component1 implements  componentInt {
//    stores a mediator object to which requests will be sent
    private concreteMediator mediator;

    @Override
    public void setMediator(concreteMediator mediator) {
        this.mediator = mediator;
    }
//    returns id of the component for registration in the mediator
    @Override
    public int getId() {
        return 1;
    }

    protected void addStringList(ArrayList<String> list) {
        mediator.addArray(list);
    }

    protected void addNum(int num) {
        mediator.plus(num);
    }
}

class component2 implements  componentInt {
    private concreteMediator mediator;

    @Override
    public void setMediator(concreteMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public int getId() {
        return 2;
    }

    protected void deleteStringList() {
        mediator.deleteArray();
    }

    protected void subtractNum(int num) {
        mediator.minus(num);
    }
}

class component3 implements  componentInt {
    private concreteMediator mediator;

    @Override
    public void setMediator(concreteMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public int getId() {
        return 3;
    }

    public void post() {
        System.out.println("Array: " +  mediator.readArray());
        System.out.println("Num: " + mediator.readNum());
    }

    protected void clear() {
        mediator.clear();
    }
}
class component4 implements  componentInt {
    private concreteMediator mediator;

    @Override
    public void setMediator(concreteMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public int getId() {
        return 4;
    }

    protected void post() {
//        although redundant with component 3, this shows how a function from one component can go through the moderator to run a function in a different component
        mediator.post();
    }
}

class component5 implements componentInt{ // Data storage component
    //    general data storage following the single responsibility principle, allows components to store data through the mediator without having to modify each other
    private concreteMediator mediator;
    private ArrayList<ArrayList<String>> list = new ArrayList<>();
    private int num = 0;

    public void addList(ArrayList<String> list) {
//        adds arraylists to the arraylist arraylist
        this.list.add(list);
    }

    public void deleteList() {
//        deletes arraylists from the arraylist arraylist
        list.remove(list.size() - 1);
    }

    public void setNum(int num) {
        this.num += num;
    }

    public ArrayList<ArrayList<String>> getList() {
        return list;
    }

    public int getNum() {
        return num;
    }

    public void clear() {
        list.clear();
        num = 0;
    }

    @Override
    public void setMediator(concreteMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public int getId() {
        return 5;
    }
}

interface mediatorInt {
//    creates an interface for mediators if more than one are required (not necessary in this case, but can help prevent god objects from being created)
    void registerComponent(componentInt component);
    void addArray(ArrayList<String> arrayList);
    void deleteArray();
    ArrayList<ArrayList<String>> readArray();
    void plus(int num);
    void minus(int num);
    int readNum();
    void clear();
    void post();
    void run(int numP, int numM, ArrayList<String> list1, ArrayList<String> list2);
}

class concreteMediator implements mediatorInt {
//    instantiate all the objects that the mediator is sending data between
    private component1 comp1;
    private component2 comp2;
    private component3 comp3;
    private component4 comp4;
    private component5 comp5;

    @Override
    public void registerComponent(componentInt component) {
//        gives each component access to the mediator
        component.setMediator(this);
        switch (component.getId()) {
//            registers all components so that the mediator has access to all of them
//            case 1 -> is 1 line shorthand for case 1: \n stuff; \n break;
            case 1 -> comp1 = (component1)component;
            case 2 -> comp2 = (component2)component;
            case 3 -> comp3 = (component3)component;
            case 4 -> comp4 = (component4)component;
            case 5 -> comp5 = (component5)component;
        }
    }

    @Override
    public void addArray(ArrayList<String> arrayList) {
        comp5.addList(arrayList);
    }

    @Override
    public void deleteArray() {
        comp5.deleteList();
    }

    @Override
    public ArrayList<ArrayList<String>> readArray() {
        return comp5.getList();
    }

    @Override
    public void plus(int num) {
        comp5.setNum(num);
    }

    @Override
    public void minus(int num) {
        comp5.setNum(-num);
    }

    @Override
    public int readNum() {
        return comp5.getNum();
    }

    @Override
    public void clear() {
        comp5.clear();
    }

    @Override
    public void post() {
        comp3.post();
    }

    @Override
    public void run(int numP, int numM, ArrayList<String> list1, ArrayList<String> list2) {
//        the functions that the client calls to interact with the components and tell them to do things
        comp1.addNum(numP);
        comp2.subtractNum(numM);
        comp1.addStringList(list1);
        comp2.deleteStringList();
        comp1.addStringList(list2);
        comp4.post();
        comp3.clear();
    }
}

public class mediator {
    private ArrayList<String> list1 = new ArrayList<>();
    private ArrayList<String> list2 = new ArrayList<>();
//    restricts direct object to object communication and forces communication only through the mediator
//    allows for rapid reuse of classes as they are concretely tied to specific implementations
//    the more components, the more the need for a mediator
//    can become a god object if made specific enough
    private void createLists() {
//        creates the arraylists which will be stored in the component 5 arraylist
        for (int i = 0; i < 10; i++) {
            list1.add("Ex string 1");
            list2.add("Ex string 2");
            list1.add("Ex string 3");
            list2.add("Ex string 4");
        }
    }
    public void main() {
//        creates the arraylists
        createLists();
//        instantiates the mediator
        mediatorInt mediator = new concreteMediator();
//        registers all components to the mediator
        mediator.registerComponent(new component1());
        mediator.registerComponent(new component2());
        mediator.registerComponent(new component3());
        mediator.registerComponent(new component4());
        mediator.registerComponent(new component5());
//        tells the mediator what methods to run
        mediator.run(1410, 254, list1, list2);
    }
}
