package Dominio;

import Dominio.Categoria;
import Dominio.DenominacionOrigen;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-30T22:22:58")
@StaticMetamodel(Preferencia.class)
public class Preferencia_ { 

    public static volatile SingularAttribute<Preferencia, Categoria> categoria;
    public static volatile SingularAttribute<Preferencia, DenominacionOrigen> iddenominacion;
    public static volatile SingularAttribute<Preferencia, Integer> id;
    public static volatile SingularAttribute<Preferencia, String> nifabonado;

}