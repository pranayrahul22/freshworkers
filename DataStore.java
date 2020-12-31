import java.io.IOException;
import java.io.Serializable;

public interface DataStore{
    public boolean add(String key, DataEntity dataEntity);
    public boolean add(String key, String value);
    public boolean remove(String key);
    public String get(String key);
    public void bootstrap() throws IOException, Exception;
    public void persist() throws IOException;

    public static class DataEntity implements Serializable{
        String data;
        // Can add time to live functionality here
        public DataEntity(String data){
            this.data = data;
        }

        public String getData(){
            return data;
        }
    }
}