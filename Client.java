public class Client {
    public static void main(String[] args) throws Exception{
        DataStore d = new DataStoreHashMap();
        d.bootstrap();
        d.add("a", "100");
        d.add("b", "500");
        d.remove("c");
        d.remove("a");

        d.persist();

        System.out.println(d.get("a"));
        System.out.println(d.get("b"));
        System.out.println(d.get("c"));
    }
}
