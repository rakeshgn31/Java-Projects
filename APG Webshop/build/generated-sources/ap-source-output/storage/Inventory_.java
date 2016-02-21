package storage;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-01-04T17:08:23")
@StaticMetamodel(Inventory.class)
public class Inventory_ { 

    public static volatile SingularAttribute<Inventory, Integer> productQuantity;
    public static volatile SingularAttribute<Inventory, String> productName;
    public static volatile SingularAttribute<Inventory, Double> productPrice;
    public static volatile SingularAttribute<Inventory, String> productCategory;

}