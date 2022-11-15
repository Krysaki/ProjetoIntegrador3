package beans;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import mapeamento.Cliente;
import org.primefaces.PrimeFaces;
import service.ClienteService;

/**
 *
 * @author Gabriel
 */
@Named
@ViewScoped
public class ClienteBean implements Serializable {

    @EJB
    private ClienteService clienteService;

    private String nome;
    private String email;
    private String cpf;
    private long telefone;

    @PostConstruct
    public void init() {
    }

    public String doVoltar() {
        return "Index.xhtml";
    }

    public String doCadastroCarro() {
        return "cadastroCarro.xhtml";
    }

    public String doCadastroServico() {
        return "cadastroServico.xhtml";
    }

    public void limpar() {
        nome = "";
        email = "";
        cpf = "";
        telefone = 0;
    }

    public void salvar() {
        Cliente cliente = new Cliente();

        if (this.nome.isEmpty() || this.nome.isBlank()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Warning", "É necessario adicionar uma nome para registrar o cliente."));
            return;
        }

        if (this.cpf.isEmpty() || this.cpf.isBlank()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Warning", "É necessario adicionar uma CPF para registrar o cliente."));
            return;
        }

        if (this.telefone == 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Warning", "É necessario adicionar uma telefone para registrar o cliente."));
            return;
        }

        if (email != null && !email.isEmpty()) {
            cliente.setCliEmail(this.email);
        }
        cliente.setCliCpf(this.cpf);
        cliente.setCliNome(this.nome);
        cliente.setCliTelefone(this.telefone);
        cliente.setCliId(0);
        clienteService.salvar(cliente);

    }

    public static void showDlg(String widgetVarDlg) {
        primeFacesExecute("PF('" + widgetVarDlg + "').show();");
    }

    public static void primeFacesExecute(String summary) {
        PrimeFaces.current().executeScript(summary);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public long getTelefone() {
        return telefone;
    }

    public void setTelefone(long telefone) {
        this.telefone = telefone;
    }

}
