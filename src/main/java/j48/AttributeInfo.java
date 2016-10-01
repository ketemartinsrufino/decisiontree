package j48;

import java.util.*;

/**
 * Created by kete on 9/22/16.
 */
public class AttributeInfo {

    public static final String POSITIVE = "s";
    public static final String NEGATIVE = "n";

    private float entropy;
    private String name;
    Map<String, AttributeChildInfo> childs = new HashMap<String, AttributeChildInfo>();

    public void addValueToChild(String childAttrName, String value){
        AttributeChildInfo child = (AttributeChildInfo) childs.get(childAttrName);
        if(!childs.containsKey(childAttrName)) {
            child = new AttributeChildInfo();
            child.setName(childAttrName);
            childs.put(childAttrName, child);
        }

        if(value.equals(POSITIVE)) {
            child.incrementPositiveValue();
        } else if(value.equals(NEGATIVE)) {
            child.incrementNegativeValue();
        } else {
            System.out.print("Value not expected [ " + value + " ]");
        }
    }

    public float getEntropy() {
        return entropy;
    }

    public void setGain(float entropy) {
        this.entropy = entropy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AttributeInfo{" +
                "entropy=" + entropy +
                ", name='" + name + '\'' +
                ", childs=" + childs +
                '}';
    }
}
