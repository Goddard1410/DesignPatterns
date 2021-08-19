//   DO NOT combine separate interfaces and classes in the same file! create separate files
package structure_patterns;

import java.util.HashMap;

interface serviceInt {
//    the interface for both the service (original object) and the proxy
    HashMap<String, Double> dataList(); // hashmaps can be created with object-based data as well (in place of Double)

    double getData(String id);
}

class service implements serviceInt {
//    creates a private hashmap which stores all the data to be accessed (original object)
//    imagine service as being in some far-off data center (not locally stored)
    private HashMap<String, Double> map = new HashMap<String, Double>();
    public service() {
        map.put("Datapoint 1", 2.54);
        map.put("Datapoint 2", 11.8);
        map.put("Datapoint 3", 141.0);
        map.put("Datapoint 4", 0.1619);
    }
//    returns the entire data list
    @Override
    public HashMap<String, Double> dataList() {
        delay();
        return map;
    }
//    returns only a specific datapoint if it exists, otherwise returns 0
    @Override
    public double getData(String id) {
        delay();
        try {
            return map.get(id);
        } catch (Exception ex) {
            System.out.println("Not a valid datapoint!");
            return 0;
        }
    }
//    this method is simulating the time it would take to contact a datacenter as opposed to a proxy's data cache method
    private void delay() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}

public class proxy implements serviceInt{
//    creates a placeholder for another object
//    controls access to original object as well as caching and lazy initialization (delays in execution of requests)

//    initializes the cache, or mediator list, for all the data from the original object
    private serviceInt servInt;
    private HashMap<String, Double> cache = new HashMap<String, Double>();

    public proxy() {
        this.servInt = new service();
    }

//    collects and then returns data from original service
    @Override
    public HashMap<String, Double> dataList() {
        if (cache.isEmpty()) {
            cache = servInt.dataList();
        }
        return cache;
    }

//    looks for data with the inputted id. If it doesn't find one, it adds it to the cache from the original object preventing access to said class and lowering compilation time
    @Override
    public double getData(String id) {
        double num = cache.get(id);
        if (num == 0) {
            num = servInt.getData(id);
            cache.put(id, num);
        }
        return num;
    }

    public void reset() {
        cache.clear();
    }
}

class init {
    public void run() {
        double start = System.currentTimeMillis();
//        first we run without proxy
//        simulates demand for popular datapoints
        serviceInt service = new service();
        System.out.println(service.dataList());
        System.out.println(service.getData("Datapoint 1"));
        System.out.println(service.getData("Datapoint 2"));
        System.out.println(service.getData("Datapoint 1"));

        double time = System.currentTimeMillis() - start;
        System.out.println("Non-Proxy Service Time: " + time);
//        then with the proxy with no delay time b/c of the cache
        start = System.currentTimeMillis();
        serviceInt proxy = new proxy();
        System.out.println(proxy.dataList());
        System.out.println(proxy.getData("Datapoint 1"));
        System.out.println(proxy.getData("Datapoint 2"));
        System.out.println(proxy.getData("Datapoint 1"));

        time = System.currentTimeMillis() - start;
        System.out.println("Proxy Service Time: " + time);
//        running the program, the proxy runs more quickly due to its local nature, and only has to access the original object once the datapoint is called for the first time, not for every single datapoint call
//        proxies can be used to speed up these times, format data (although adapters do a better job of it), regulate data access, etc.
    }
}