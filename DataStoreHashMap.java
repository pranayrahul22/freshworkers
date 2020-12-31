import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.io.File;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;


/**
 * Call bootStrap method for initialization
 * Then do operations on the data store
 * Then call persist method to store in the file
 */
public class DataStoreHashMap implements DataStore {

    Map<String, DataEntity> data = new HashMap<String, DataEntity>();
    String filePath = "./";
    String fileName = "dataStore.data";

    public DataStoreHashMap(String fileName, String filePath){
        this.fileName = fileName;
        this.filePath = filePath;
    }

    public DataStoreHashMap(){

    }

    /**
     * Returns true if addition is success into the data store
     * else returns false
     */
    @Override
    public boolean add(String key, DataStore.DataEntity dataEntity) {
        if(data.get(key) != null){
            // Key already present
            return false;
        }
        data.put(key, dataEntity);
        return true;
    }

    /**
     * Returns true if addition is success into the data store
     * else returns false
     */
    @Override
    public boolean add(String key, String value) {
        if(data.get(key) != null){
            // Key already present
            return false;
        }
        data.put(key, new DataEntity(value));
        return true;
    }

    /**
     * Returns false if key doesn't exist
     * else returns true
     */
    @Override
    public boolean remove(String key) {

        return data.remove(key) != null;
    }

    /**
     * Returns null if key is not present
     */
    @Override
    public String get(String key) {
        DataEntity d = data.get(key);
        return d != null ? d.getData() : null;
    }
    
    @Override
    public void persist() throws IOException{
        File dataFile = new File(filePath + "/" + fileName);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataFile));
        oos.writeObject(data);
        oos.close();
    }

    @Override
    public void bootstrap() throws IOException, Exception{
        File dataFile = new File(filePath + "/" + fileName);
        dataFile.createNewFile();
        ObjectInputStream oos = new ObjectInputStream(new FileInputStream(dataFile));
        Object o =oos.readObject();
        if(o != null)
            this.data = (Map<String, DataEntity>) o;
        oos.close();
    }
}
