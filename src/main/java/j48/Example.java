package j48;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by kete on 9/21/16.
 */
public class Example {

    Map<String, Object> attributeValues = new HashMap<String, Object>();

    public Example(String[] attributes, String[] value) {
        for(int i = 0, length = value.length; i < length; i++) {
            attributeValues.put(attributes[i], value[i]);
        }
    }

    public void addExample(String attr, String value) {
        attributeValues.put(attr, value);
    }

    private String getAttributeValuesToString(){
        String value = "[";
        Iterator it = attributeValues.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            value += "( "+ (String) pair.getKey() + ": "+ pair.getValue() + ")";
        }
        return value;
    }

    @Override
    public String toString() {
        return "Example{" +
                "attributeValues=" + attributeValues +
                '}';
    }
}
