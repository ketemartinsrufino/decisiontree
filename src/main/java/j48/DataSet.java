package j48;

import org.apache.commons.lang.ArrayUtils;

import java.util.*;

/**
 * Created by kete on 9/22/16.
 */
public class DataSet {


    public static final String POSITIVE = "s";
    public static final String NEGATIVE = "n";

    String targetAttr;
    String[] attributesHeader;
    private final String symbolToSplitInfo = ",";
    List<Example> examples = new ArrayList<Example>();
    public Map generalValuesQuantities;

    public DataSet(String targetAttribute) {
        this.targetAttr = targetAttribute;
    }

    public DataSet generateDataSet(List<String> fileLines) {
        this.attributesHeader = fileLines.get(0).split(symbolToSplitInfo);
        String[] value;

        for(int i = 1; i < fileLines.size(); i++) {
            value = fileLines.get(i).split(symbolToSplitInfo);
            examples.add(new Example(attributesHeader, value));
        }

        this.generalValuesQuantities = getClassifierQuantities();

        return this;
    }

    // Mapeia a quantidade de classificadores.
    // Ex: { sim: 2, nao: 6 }
    public Map getClassifierQuantities(){
        return getClassifierQuantities(examples);
    }
    
    public Map getClassifierQuantities(List<Example> examples){
        Map countAttr = new HashMap<String, Integer>();
        for(Example e: examples) {
            String value = (String) e.attributeValues.get(this.targetAttr);
            Integer quant = (Integer) countAttr.get(value);
            if(quant == null) {
                quant = 0;
            }

            countAttr.put(value, ++quant);
        }
        this.generalValuesQuantities = countAttr;

        return countAttr;
    }

    public AttributeInfo getBestAttribute(String[] attributes) {
        Map<String, AttributeInfo> attributeEntropy = new HashMap<String, AttributeInfo>();
        AttributeInfo best = null;

        for(String a: attributes) {
            AttributeInfo attributeInfo = new AttributeInfo();
            attributeInfo.setName(a);
            for(Example e: examples) {
                attributeInfo.addValueToChild((String)e.attributeValues.get(attributeInfo.getName()),
                        (String)e.attributeValues.get(targetAttr));
            }
            attributeInfo.setGain(getInformationGain(attributeInfo, examples));
            attributeEntropy.put(a, attributeInfo);

            if(best == null) {
                best = attributeInfo;
            }

            if(attributeInfo.getEntropy() > best.getEntropy()) {
                best = attributeInfo;
            }
        }

        return best;
    }

    private float getInformationGain(AttributeInfo attributeInfo, List<Example> examples){
        Map quantities = this.generalValuesQuantities;
        int p = (int) quantities.get(POSITIVE);
        int n = (int) quantities.get(NEGATIVE);
        int totalClassifier = p + n;
        float totalExamples = (float)p/totalClassifier;

        return getEntropy(totalExamples) - getRest(attributeInfo, totalClassifier);

    }

    private float getRest(AttributeInfo attr, int totalClassifier) {
        float rest = 0;
        float restTemp = 0;
        float positiveNegativeValuesQuant = 0;

        Map<String, AttributeChildInfo> distinctValues = attr.childs;
        AttributeChildInfo element;

        for (String key : distinctValues.keySet()) {
            element = distinctValues.get(key);
            positiveNegativeValuesQuant = element.getPositiveQuantity() + element.getNegativeQuantity();
            restTemp = (positiveNegativeValuesQuant / totalClassifier);

            rest += restTemp * getEntropy((float)element.getPositiveQuantity()/positiveNegativeValuesQuant);

        }

        return rest;
    }

    public float getEntropy(float q) {
        if(q == 0 || q == 1) {
            return 0;
        }
        return (float) -(q * logBase2(q) + (1 - q) * logBase2(1 - q));
    }
    private float logBase2(float q){
        return (float) (Math.log(q)/Math.log(2));
    }

    public String[] getAttributesHeader() {
        return attributesHeader;
    }

    public void setAttributesHeader(String[] attributesHeader) {
        this.attributesHeader = attributesHeader;
    }

    public List<Example> getExamples() {
        return examples;
    }

    public void setExamples(List<Example> examples) {
        this.examples = examples;
    }

    public String getTargetAttr() {
        return targetAttr;
    }

    public void setTargetAttr(String targetAttr) {
        this.targetAttr = targetAttr;
    }

    @Override
    public String toString() {
        return "DataSet{"+
                "attributesHeader=" + Arrays.toString(attributesHeader) +
                ",/n examples=" + examples +
                ",/n targetAttr='" + targetAttr + '\'' +
                '}';
    }

}
