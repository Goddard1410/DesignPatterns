//   DO NOT combine separate interfaces and classes in the same file! create separate files
package creation_patterns;

final class singletonSingleThread {
//    declared final in scope in order to prevent duplicate creation
    private static singletonSingleThread instance;
    public String str;

    private singletonSingleThread(String str) {
//        emulates slow initialization to make sure that two objects were not created at the same time
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.str = str;
    }
//    making sure to only create a singleton if one is not currently instantiated
    public static singletonSingleThread getInstance(String str) {
        if (instance == null) {
            instance = new singletonSingleThread(str);
        }
        return instance;
    }
}

final class singletonMultiThread {
    //    declared final in scope in order to prevent duplicate creation
    private static singletonMultiThread instance;
    public String str;

    private singletonMultiThread(String str) {
//        emulates slow initialization to make sure that two objects were not created at the same time
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.str = str;
    }
    //    making sure to only create a singleton if one is not currently instantiated
    public static singletonMultiThread getInstance(String str) {
        if (instance == null) {
            instance = new singletonMultiThread(str);
        }
        return instance;
    }
}

public class singleton {
//    created to ensure that a class has only one instance that is globally accessible
//    creates a private default constructor to prevent multiple objects being created
//    allows for easy usage limitation and caping

//    creates two different objects, one for each thread
    static class Thread1 implements Runnable {
        @Override
    public void run() {
//            print should return val1 or val2, but not both
            singletonMultiThread sm = singletonMultiThread.getInstance("val1 multi");
            System.out.println(sm.str);
        }
    }

    static class Thread2 implements Runnable {
        @Override
        public void run() {
            singletonMultiThread sm = singletonMultiThread.getInstance("val2 multi");
            System.out.println(sm.str);
        }
    }

    public void run() {
        singletonSingleThread s1s = singletonSingleThread.getInstance("val1 single");
        singletonSingleThread s2s = singletonSingleThread.getInstance("val2 single");
//        print should return either two val1 or two val2, as only one singleton can exist at a time
        System.out.println(s1s + " " + s2s);

//        creates two different threads for the multithreaded singleton
        Thread thread1 = new Thread(new Thread1());
        Thread thread2 = new Thread(new Thread2());
        thread1.start();
        thread2.start();
    }
}
