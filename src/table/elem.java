package table;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by MOULID.
 */
public class elem {
    private SimpleStringProperty etat,a,b;

    public elem(){}
    public elem(String etat, String a, String b){
        this.etat=new SimpleStringProperty(etat);
        this.a=new SimpleStringProperty(a);
        this.b=new SimpleStringProperty(b);
    }

    public String getEtat() {
        return etat.get();
    }

    public SimpleStringProperty etatProperty() {
        return etat;
    }

    public String getA() {
        return a.get();
    }

    public SimpleStringProperty aProperty() {
        return a;
    }

    public String getB() {
        return b.get();
    }

    public SimpleStringProperty bProperty() {
        return b;
    }

    public void setEtat(String etat) {
        this.etat.set(etat);
    }

    public void setA(String a) {
        this.a.set(a);
    }

    public void setB(String b) {
        this.b.set(b);
    }
}
