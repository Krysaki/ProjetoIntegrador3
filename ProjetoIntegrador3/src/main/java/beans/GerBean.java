package beans;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import mapeamento.Agenda;

/**
 *
 * @author Gabriel
 */
@Named
@SessionScoped
public class GerBean implements Serializable {

    private List<Agenda> agendaList;

    public List<Agenda> getAgendaList() {
        return agendaList;
    }

    public void setAgendaList(List<Agenda> agendaList) {
        this.agendaList = agendaList;
    }

}
