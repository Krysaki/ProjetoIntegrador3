package mapeamento;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Mecanica
 */
@Entity
@Table(name = "CARRO")
public class Carro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "CAR_PLACA")
    private String carPlaca;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CAR_ANOFABRIC")
    private int carAnofabric;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "agendaCarplaca")
    private List<Agenda> agendaList;
    @JoinColumn(name = "CAR_CLIID", referencedColumnName = "CLI_ID")
    @ManyToOne(optional = false)
    private Cliente carCliid;
    @JoinColumn(name = "CAR_MODCOD", referencedColumnName = "MOD_COD")
    @ManyToOne(optional = false)
    private Modelo carModcod;

    public Carro() {
    }

    public Carro(String carPlaca) {
        this.carPlaca = carPlaca;
    }

    public Carro(String carPlaca, int carAnofabric) {
        this.carPlaca = carPlaca;
        this.carAnofabric = carAnofabric;
    }

    public String getCarPlaca() {
        return carPlaca;
    }

    public void setCarPlaca(String carPlaca) {
        this.carPlaca = carPlaca;
    }

    public int getCarAnofabric() {
        return carAnofabric;
    }

    public void setCarAnofabric(int carAnofabric) {
        this.carAnofabric = carAnofabric;
    }

    public List<Agenda> getAgendaList() {
        return agendaList;
    }

    public void setAgendaList(List<Agenda> agendaList) {
        this.agendaList = agendaList;
    }

    public Cliente getCarCliid() {
        return carCliid;
    }

    public void setCarCliid(Cliente carCliid) {
        this.carCliid = carCliid;
    }

    public Modelo getCarModcod() {
        return carModcod;
    }

    public void setCarModcod(Modelo carModcod) {
        this.carModcod = carModcod;
    }

    public String getItemLabel() {
        return "Modelo " + this.carModcod.getModModelo() + " - Placa " + this.carPlaca;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (carPlaca != null ? carPlaca.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Carro)) {
            return false;
        }
        Carro other = (Carro) object;
        if ((this.carPlaca == null && other.carPlaca != null) || (this.carPlaca != null && !this.carPlaca.equals(other.carPlaca))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mapeamento.Carro[ carPlaca=" + carPlaca + " ]";
    }

}
