package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import mapeamento.Agenda;
import mapeamento.Carro;
import mapeamento.Cliente;
import mapeamento.Marca;
import mapeamento.Modelo;
import service.AgendaService;

/**
 *
 * @author Gabriel
 */
@Named
@ViewScoped
public class PesquisaBean implements Serializable {

    @EJB
    private AgendaService agendaService;

    private Cliente cliente;
    private Marca marca;
    private Modelo modelo;
    private Date dataIni;
    private Date dataFim;

    private List<Agenda> agendaList = new ArrayList<>();

    public List<Agenda> retornoPesquisa() {
        this.agendaList = agendaService.executeNativeQuery(montaSql(), Agenda.class);

        if (this.agendaList.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Nenhum carro encontrado"));
            return null;
        } else {
            return agendaList;
        }
    }

    public String montaSql() {
        String sqlQuery = "SELECT * FROM AGENDA A "
                + " WHERE ";

        if (this.cliente == null && this.marca == null && this.modelo == null && this.dataIni == null && this.dataFim == null) {
            return "SELECT * FROM AGENDA A ";
        }

        int contadorAnd = 0;
        if (this.cliente != null) {
            if (contadorAnd == 0) {
                sqlQuery = sqlQuery + " A.AGENDA_CLIID = " + this.cliente.getCliId();
                contadorAnd++;
            } else {
                sqlQuery = sqlQuery + " AND A.AGENDA_CLIID = " + this.cliente.getCliId();

            }
        }
        if (this.marca != null) {
            if (contadorAnd == 0) {
                sqlQuery = sqlQuery + " A.AGENDA_MARCA = " + "'" + this.marca.getMarcCod() + "'";
                contadorAnd++;
            } else {
                sqlQuery = sqlQuery + " AND  A.AGENDA_MARCA = " + "'" + this.marca.getMarcCod() + "'";

            }
        }

        if (this.modelo != null) {
            if (contadorAnd == 0) {
                sqlQuery = sqlQuery + " A.AGENDA_MODELO = " + "'" + this.modelo.getModCod() + "'";
                contadorAnd++;
            } else {
                sqlQuery = sqlQuery + " AND  A.AGENDA_MODELO = " + "'" + this.modelo.getModCod() + "'";

            }
        }

        if (dataIni != null) {
            if (dataFim == null) {
                dataFim = new Date();
            }

            if (contadorAnd == 0) {
                sqlQuery = sqlQuery + " A.AGENDA_DATA BETWEEN " + dataIni + "AND" + dataFim;
                contadorAnd++;
            } else {
                sqlQuery = sqlQuery + " AND  A.AGENDA_MODELO = " + "'" + this.modelo.getModCod() + "'";

            }
        }

        return sqlQuery;
    }

    public List<Cliente> buscarClientes() {
        String sqlQuery = "SELECT * FROM ClIENTE C ";
        return agendaService.executeNativeQuery(sqlQuery, Cliente.class);
    }

    public List<Marca> buscarMarcas() {
        String sqlQuery = "SELECT * FROM MARCA m ";
        return agendaService.executeNativeQuery(sqlQuery, Marca.class);
    }

    public List<Modelo> buscarModelos() {
        String sqlQuery;
        if (this.marca != null) {
            sqlQuery = "SELECT * FROM MODELO mo WHERE MOD_MARCCOD = ".concat(String.valueOf(this.marca.getMarcCod().toString()));
            return agendaService.executeNativeQuery(sqlQuery, Modelo.class);
        }
        return null;
    }

    public List<Agenda> getAgendaList() {
        return agendaList;
    }

    public String doVoltar() {
        return "Index.xhtml";
    }

    public void setAgendaList(List<Agenda> agendaList) {
        this.agendaList = agendaList;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public Date getDataIni() {
        return dataIni;
    }

    public void setDataIni(Date dataIni) {
        this.dataIni = dataIni;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

}
