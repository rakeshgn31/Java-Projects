package storage;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-01-04T17:08:23")
@StaticMetamodel(OrderSummary.class)
public class OrderSummary_ { 

    public static volatile SingularAttribute<OrderSummary, byte[]> mapProductsvsQuantity;
    public static volatile SingularAttribute<OrderSummary, Integer> orderID;
    public static volatile SingularAttribute<OrderSummary, String> username;
    public static volatile SingularAttribute<OrderSummary, Double> purchaseAmount;

}