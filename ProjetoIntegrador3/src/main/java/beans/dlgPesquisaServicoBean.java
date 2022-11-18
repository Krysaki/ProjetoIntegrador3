/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import mapeamento.Agenda;
import org.primefaces.PrimeFaces;
import service.AgendaService;

@Named
@ViewScoped
public class dlgPesquisaServicoBean implements Serializable {

    @Inject
    private GerBean gerBean;

    @EJB
    private AgendaService agendaService;

    private List<Agenda> listAgenda;

    @PostConstruct
    public void init() {
        listAgenda = gerBean.getAgendaList();
    }

    public List<Agenda> getListAgenda() {
        return listAgenda;
    }

    public void setListAgenda(List<Agenda> listAgenda) {
        this.listAgenda = listAgenda;
    }

    public void selectCarFromDialog(Agenda agenda) {
        PrimeFaces.current().dialog().closeDynamic(agenda);
    }

    public void excluir(Agenda agenda) {
        agendaService.excluir(agenda);
        gerBean.getAgendaList().remove(agenda);
    }

}
