package Dominio;

import Dominio.Preferencia;
import Dominio.Vino;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-30T22:22:58")
@StaticMetamodel(Categoria.class)
public class Categoria_ { 

    public static volatile SingularAttribute<Categoria, String> clave;
    public static volatile CollectionAttribute<Categoria, Preferencia> preferenciaCollection;
    public static volatile CollectionAttribute<Categoria, Vino> vinoCollection;
    public static volatile SingularAttribute<Categoria, String> nombre;

}