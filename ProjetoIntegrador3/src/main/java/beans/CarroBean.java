package beans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import mapeamento.Carro;
import mapeamento.Cliente;
import mapeamento.Marca;
import mapeamento.Modelo;
import service.CarroService;
import service.ClienteService;
import service.MarcaService;
import service.ModeloService;

/**
 *
 * @author Gabriel
 */
@Named
@ViewScoped
public class CarroBean implements Serializable {

    @EJB
    private CarroService carroService;
    @EJB
    private ModeloService modeloService;
    @EJB
    private MarcaService marcaService;
    @EJB
    private ClienteService clienteService;

    private Carro carro = new Carro();
    private Cliente cliente;
    private Marca marca;
    private Modelo modelo;
    private String placa;
    private int anoFabric;

    @PostConstruct
    private void init() {
        buscarMarcas();
        buscarModelos();

    }

    public String doVoltar() {
        return "Index.xhtml";
    }

    public void limpar() {
        cliente = null;
        marca = null;
        modelo = null;
        placa = "";
        anoFabric = 0;
    }

    public void salvar() {
        //Metodos de validação para salvar

        if (this.placa.isEmpty() || this.placa.isBlank()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Warning", "É necessario adicionar uma placa para registrar o carro."));
            return;
        }
        if (this.anoFabric == 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Warning", "É necessario adicionar um ano de fabricação."));
            return;
        }
        if (this.placa.isEmpty() || this.placa.isBlank()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Warning", "É necessario adicionar uma placa para registrar o carro."));
            return;
        }
        if (this.modelo == null || this.modelo.getModCod() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Warning", "É necessario adicionar um modelo para registrar o carro."));
            return;
        }

        carro.setCarPlaca(this.placa);
        carro.setCarAnofabric(this.anoFabric);
        carro.setCarCliid(this.cliente);
        carro.setCarModcod(this.modelo);

        carroService.salvar(carro);
        limpar();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "info", "Cadastro efetuado com sucesso."));

    }

    public List<Marca> buscarMarcas() {
        String sqlQuery = "SELECT * FROM MARCA m ";

        return marcaService.executeNativeQuery(sqlQuery, Marca.class
        );
    }

    public List<Cliente> buscarClientes() {
        String sqlQuery = "SELECT * FROM ClIENTE C ";

        return clienteService.executeNativeQuery(sqlQuery, Cliente.class
        );
    }

    public List<Modelo> buscarModelos() {
        String sqlQuery;
        if (this.marca != null) {
            sqlQuery = "SELECT * FROM MODELO mo WHERE MOD_MARCCOD = ".concat(String.valueOf(this.marca.getMarcCod().toString()));

            return modeloService.executeNativeQuery(sqlQuery, Modelo.class
            );
        }
        return null;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getAnoFabric() {
        return anoFabric;
    }

    public void setAnoFabric(int anoFabric) {
        this.anoFabric = anoFabric;
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

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
