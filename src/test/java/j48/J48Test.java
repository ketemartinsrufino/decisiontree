package j48;

import apple.laf.JRSUIUtils;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.crypto.Data;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kete on 9/22/16.
 */
public class J48Test {

    public static final String TARGET_ATTR = "meta";

    public static final String FOLDER = "/Users/kete/Documents/Unifor/_Aprendizagem Automatica/decisiontree/src/main/resources/j48/";
    @Test
    public void createDataSetFromFile() {
        List<String> fileContent = FileUtil.readInputFile(FOLDER + "vai-esperar.csv");

        DataSet dataSet = new DataSet(TARGET_ATTR);
        dataSet.generateDataSet(fileContent);

        System.out.println(dataSet);
        System.out.println(dataSet.examples);

        TreeDecisionCreator creator = new TreeDecisionCreator();
        Node tree = creator.getTree(dataSet, TARGET_ATTR);

        System.out.println(tree);
    }

    @Test
    public void testWhenThereIsOnlyOneClassificationValue() {
        List<String> fileContent = FileUtil.readInputFile(FOLDER + "vai-esperar-apenas-uma-classificacao.csv");

        DataSet dataSet = new DataSet(TARGET_ATTR);
        dataSet.generateDataSet(fileContent);

        TreeDecisionCreator creator = new TreeDecisionCreator();
        Node tree = creator.getTree(dataSet, "meta");
        Assert.assertEquals(tree.question, "s");
        Assert.assertEquals(tree.subtrees.size(), 0);
    }

    @Test
    public void testWhenThereIsNotAttributes() {
        List<String> fileContent = FileUtil.readInputFile(FOLDER + "vai-esperar.csv");

        DataSet dataSet = new DataSet(TARGET_ATTR);
        dataSet.generateDataSet(fileContent);
        dataSet.attributesHeader = null;

        TreeDecisionCreator creator = new TreeDecisionCreator();
        Node tree = creator.getTree(dataSet, "meta");
        Assert.assertEquals(tree.question, "s");
        Assert.assertEquals(tree.subtrees.size(), 0);
        System.out.println(tree);
    }

    @Test
    public void basura() {
        DataSet d = new DataSet(null);

        float x = (6f/12) * d.getEntropy(2f/6);
        float y = (4f/12) * d.getEntropy((float)4/4);
        float z = (2f/12) * d.getEntropy((float)0/2);
        System.out.println (x);
        System.out.println (y);
        System.out.println (z);
        System.out.println (1 - (x + y+ z));
    }
}
