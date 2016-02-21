package storage;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-01-04T17:08:23")
@StaticMetamodel(Customers.class)
public class Customers_ { 

    public static volatile SingularAttribute<Customers, String> password;
    public static volatile SingularAttribute<Customers, Boolean> isBanned;
    public static volatile SingularAttribute<Customers, Boolean> isAdmin;
    public static volatile SingularAttribute<Customers, String> username;

}