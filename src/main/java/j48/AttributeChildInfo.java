package j48;

/**
 * Created by kete on 9/22/16.
 */
public class AttributeChildInfo {

    private String name;
    private Integer positiveQuantity = 0;
    private Integer negativeQuantity = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPositiveQuantity() {
        return positiveQuantity;
    }

    public void setPositiveQuantity(Integer positiveQuantity) {
        this.positiveQuantity = positiveQuantity;
    }

    public Integer getNegativeQuantity() {
        return negativeQuantity;
    }

    public void setNegativeQuantity(Integer negativeQuantity) {
        this.negativeQuantity = negativeQuantity;
    }

    public void incrementPositiveValue(){
        positiveQuantity++;
    }

    public void incrementNegativeValue(){
        negativeQuantity++;
    }

    @Override
    public String toString() {
        return "AttributeChildInfo{" +
                "name='" + name + '\'' +
                ", positiveQuantity=" + positiveQuantity +
                ", negativeQuantity=" + negativeQuantity +
                '}';
    }
}
