package j48;

import com.sun.corba.se.spi.activation.IIOP_CLEAR_TEXT;
import org.apache.commons.lang.ArrayUtils;

import java.util.*;

import static com.sun.corba.se.spi.activation.IIOP_CLEAR_TEXT.value;

/**
 * Created by kete on 9/21/16.
 */
public class TreeDecisionCreator {

    public Node getTree(DataSet dataSet, String targetAttribute){
        Node tree = new Node();
        List<Example> examples = dataSet.examples;
        String[] attributes = dataSet.attributesHeader;
        Map<String, Integer> classifierMap = dataSet.getClassifierQuantities();

        if( attributes == null || attributes.length <= 1 || examples.isEmpty()) {
            Node valueMost = getValueMost(examples, classifierMap);
            return valueMost ;

        } else if(classifierMap.size() == 1){
            //se tem apenas uma classificacao, retorna ela.
            String value = String.valueOf(classifierMap.keySet().toArray()[0]);
            tree.question = value;
            tree.value = value;
        } else {
            AttributeInfo bestAttribute = dataSet.getBestAttribute((String[]) ArrayUtils.removeElement(attributes, targetAttribute));

            String bestAttrLabel = bestAttribute.getName();

            System.out.println("Best: "+bestAttrLabel);

            tree.question = bestAttrLabel;
            String[] newAttributes = (String[]) ArrayUtils.removeElement(attributes, bestAttribute.getName());

            Map<String, AttributeChildInfo> childs = bestAttribute.childs;

            for(String key: childs.keySet()) {
                List<Example> exs = getExampleByAttributeChild(bestAttrLabel, childs.get(key), examples);
                DataSet newDataSet = getNewDataSet(exs, newAttributes, targetAttribute);
                Node subtreeChild = new Node();
                subtreeChild.question = key;
                Node subtree = getTree(newDataSet, targetAttribute);
                subtreeChild.addNode(subtree);
                tree.addNode(subtreeChild);
            }

        }

        return tree;
    }

    private DataSet getNewDataSet(List<Example> exs, String[] attributes, String targetAttribute) {
        DataSet dataSet = new DataSet(targetAttribute);
        dataSet.setAttributesHeader(attributes);
        dataSet.setExamples(exs);
        return dataSet;
    }

    private List<Example> getExampleByAttributeChild(String bestAttribute, AttributeChildInfo child, List<Example> exemples) {
        List<Example> exs = new ArrayList<Example>();
        for(Example e: exemples) {
            if(child.getName().equals(e.attributeValues.get(bestAttribute))){
                exs.add(e);
            }
        }

        return exs;
    }

    private Node getValueMost(List<Example> examples, Map<String, Integer> countAttr) {
        Node tree = new Node();
        String mostValueAttr = null;
        int mostValue = -1;

        for(String key: countAttr.keySet()) {
            Integer value = (Integer) countAttr.get(key);
            if(mostValue < value){
                mostValue = value;
                mostValueAttr = key;
            }
        }

        tree.question = mostValueAttr;
        tree.value = mostValueAttr;

        return tree;

    }
}
