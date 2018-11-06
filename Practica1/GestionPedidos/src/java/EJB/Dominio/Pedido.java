/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB.Dominio;

import EJB.Dominio.EstadoPedido;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author vicrojo
 * @author ismpere
 */
@Entity
@Table(name = "PEDIDO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p")
    , @NamedQuery(name = "Pedido.findByPeId", query = "SELECT p FROM Pedido p WHERE p.peId = :peId")
    , @NamedQuery(name = "Pedido.findByImporte", query = "SELECT p FROM Pedido p WHERE p.importe = :importe")
    , @NamedQuery(name = "Pedido.findByFecha", query = "SELECT p FROM Pedido p WHERE p.fecha = :fecha")
    , @NamedQuery(name = "Pedido.findByPeNif", query = "SELECT p FROM Pedido p WHERE p.peNif = :peNif")
    , @NamedQuery(name = "Pedido.findByPeReferencia", query = "SELECT p FROM Pedido p WHERE p.peReferencia = :peReferencia")})
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PE_ID")
    private Integer peId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "IMPORTE")
    private Float importe;
    @Size(max = 20)
    @Column(name = "FECHA")
    private String fecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "PE_NIF")
    private String peNif;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PE_REFERENCIA")
    private int peReferencia;
    @JoinColumn(name = "PE_ESTADO", referencedColumnName = "EP_CLAVE")
    @ManyToOne(optional = false)
    private EstadoPedido peEstado;

    public Pedido() {
    }

    public Pedido(Integer peId) {
        this.peId = peId;
    }

    public Pedido(Integer peId, String peNif, int peReferencia) {
        this.peId = peId;
        this.peNif = peNif;
        this.peReferencia = peReferencia;
    }

    public Integer getPeId() {
        return peId;
    }

    public void setPeId(Integer peId) {
        this.peId = peId;
    }

    public Float getImporte() {
        return importe;
    }

    public void setImporte(Float importe) {
        this.importe = importe;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getPeNif() {
        return peNif;
    }

    public void setPeNif(String peNif) {
        this.peNif = peNif;
    }

    public int getPeReferencia() {
        return peReferencia;
    }

    public void setPeReferencia(int peReferencia) {
        this.peReferencia = peReferencia;
    }

    public EstadoPedido getPeEstado() {
        return peEstado;
    }

    public void setPeEstado(EstadoPedido peEstado) {
        this.peEstado = peEstado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (peId != null ? peId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.peId == null && other.peId != null) || (this.peId != null && !this.peId.equals(other.peId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EJB.Persistencia.Pedido[ peId=" + peId + " ]";
    }
    
}
