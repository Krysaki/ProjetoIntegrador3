package mapeamento;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Mecanica
 */
@Entity
@Table(name = "AGENDA")
public class Agenda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    //@Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AGENDA_SEQ")
    @SequenceGenerator(name = "AGENDA_SEQ", sequenceName = "AGENDA_SEQ", allocationSize = 1)
    @Column(name = "AGENDA_COD")
    private Integer agendaCod;
    @Size(max = 500)
    @Column(name = "AGENDA_DESC")
    private String agendaDesc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AGENDA_DATA")
    @Temporal(TemporalType.DATE)
    private Date agendaData;
    @JoinColumn(name = "AGENDA_CARPLACA", referencedColumnName = "CAR_PLACA")
    @ManyToOne(optional = false)
    private Carro agendaCarplaca;
    @JoinColumn(name = "AGENDA_CLIID", referencedColumnName = "CLI_ID")
    @ManyToOne(optional = false)
    private Cliente agendaCliid;
    @Size(max = 500)
    @Column(name = "AGENDA_MARCA")
    private String agendaMarca;
    @Size(max = 500)
    @Column(name = "AGENDA_MODELO")
    private String agendaModelo;
    @Size(max = 500)
    @Column(name = "AGENDA_CLIENTE")
    private String agendaCliente;

    public Agenda() {
    }

    public Agenda(Integer agendaCod) {
        this.agendaCod = agendaCod;
    }

    public Agenda(Integer agendaCod, Date agendaData) {
        this.agendaCod = agendaCod;
        this.agendaData = agendaData;
    }

    public Integer getAgendaCod() {
        return agendaCod;
    }

    public void setAgendaCod(Integer agendaCod) {
        this.agendaCod = agendaCod;
    }

    public String getAgendaDesc() {
        return agendaDesc;
    }

    public void setAgendaDesc(String agendaDesc) {
        this.agendaDesc = agendaDesc;
    }

    public Date getAgendaData() {
        return agendaData;
    }

    public String getAgendaDataFormatada() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(agendaData);
    }

    public void setAgendaData(Date agendaData) {
        this.agendaData = agendaData;
    }

    public Carro getAgendaCarplaca() {
        return agendaCarplaca;
    }

    public void setAgendaCarplaca(Carro agendaCarplaca) {
        this.agendaCarplaca = agendaCarplaca;
    }

    public Cliente getAgendaCliid() {
        return agendaCliid;
    }

    public void setAgendaCliid(Cliente agendaCliid) {
        this.agendaCliid = agendaCliid;
    }

    public String getAgendaMarca() {
        return agendaMarca;
    }

    public void setAgendaMarca(String agendaMarca) {
        this.agendaMarca = agendaMarca;
    }

    public String getAgendaModelo() {
        return agendaModelo;
    }

    public void setAgendaModelo(String agendaModelo) {
        this.agendaModelo = agendaModelo;
    }

    public String getAgendaCliente() {
        return agendaCliente;
    }

    public void setAgendaCliente(String agendaCliente) {
        this.agendaCliente = agendaCliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (agendaCod != null ? agendaCod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Agenda)) {
            return false;
        }
        Agenda other = (Agenda) object;
        if ((this.agendaCod == null && other.agendaCod != null) || (this.agendaCod != null && !this.agendaCod.equals(other.agendaCod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mapeamento.Agenda[ agendaCod=" + agendaCod + " ]";
    }

}
