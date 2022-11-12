package beans;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import mapeamento.Agenda;
import mapeamento.Carro;
import mapeamento.Cliente;
import service.CarroService;
import service.ClienteService;
import service.AgendaService;

/**
 *
 * @author Gabriel
 */
@Named
@ViewScoped
public class AgendaBean implements Serializable {

    @EJB
    private AgendaService agendaService;

    @EJB
    private CarroService carroService;
    @EJB
    private ClienteService clienteService;

    private int agendaCod;
    private String agendaDesc;
    private LocalDate agendaData;
    private int agendaCliid;
    private String agendaCarplaca;
    private Cliente cliente;
    private Carro carro;

    public String doVoltar() {
        return "Index.xhtml";
    }

    public void salvar() {
        Agenda agenda = new Agenda();
        agenda.setAgendaCod(this.agendaCod);
        agenda.setAgendaDesc(this.agendaDesc);
        agenda.setAgendaData(Date.from(agendaData.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        agenda.setAgendaCliid(this.cliente);
        agenda.setAgendaCarplaca(this.carro);
        agenda.setAgendaMarca(this.carro.getCarModcod().getModMarccod().getMarcMarca());
        agenda.setAgendaModelo(this.carro.getCarModcod().getModModelo());
        agenda.setAgendaCliente(this.cliente.getCliNome());

        agendaService.salvar(agenda);
    }

    public List<Cliente> buscarCliente() {
        String sqlQuery = "SELECT * FROM CLIENTE cli";
        return clienteService.executeNativeQuery(sqlQuery, Cliente.class);
    }

    public List<Carro> buscarCarro() {
        if (this.cliente != null) {
            String sqlQuery = "SELECT * FROM CARRO c WHERE c.CAR_CLIID = ".concat(String.valueOf(this.cliente.getCliId().toString()));
            return carroService.executeNativeQuery(sqlQuery, Carro.class);
        }
        return null;
    }

    public int getAgendaCod() {
        return agendaCod;
    }

    public void setAgendaCod(int agendaCod) {
        this.agendaCod = agendaCod;
    }

    public String getAgendaDesc() {
        return agendaDesc;
    }

    public void setAgendaDesc(String agendaDesc) {
        this.agendaDesc = agendaDesc;
    }

    public LocalDate getAgendaData() {
        return agendaData;
    }

    public void setAgendaData(LocalDate agendaData) {
        this.agendaData = agendaData;
    }

    public int getAgendaCliid() {
        return agendaCliid;
    }

    public void setAgendaCliid(int agendaCliid) {
        this.agendaCliid = agendaCliid;
    }

    public String getAgendaCarplaca() {
        return agendaCarplaca;
    }

    public void setAgendaCarplaca(String agendaCarplaca) {
        this.agendaCarplaca = agendaCarplaca;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

}
