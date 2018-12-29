/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.mb;




import fenix.iure.dao.MunicipioDAO;
import fenix.iure.dao.ProvinciaDAO;
import fenix.iure.ejbs.MunicipioFacade;
import fenix.iure.ejbs.ProvinciaFacade;
import fenix.iure.entities.Municipio;
import fenix.iure.entities.Provincia;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIOutput;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;

/**
 *
 * @author desenvolvimento
 */
@ManagedBean(name = "municipioBean")
@SessionScoped
public class MunicipioMBean implements Serializable {

    private ProvinciaDAO provinciaDAO;
    private MunicipioDAO municipioDAO;

    private Municipio municipio;
    private Provincia provincia;
    private List<Municipio> municipios;
    private List<Provincia> provincias;

    /**
     * Creates a new instance of MunicipioBean
     */
    @Inject
    MunicipioFacade municipioFacade;
    
    @Inject
    ProvinciaFacade provinciaFacade;
    
    public MunicipioMBean() {

    }

    @PostConstruct
    public void init() {

        municipio = new Municipio();
        provincia = new Provincia();
        municipios = new ArrayList<>();
        provincias = new ArrayList<>();
        municipioDAO = new MunicipioDAO();
        provinciaDAO = new ProvinciaDAO();
        provincias = provinciaFacade.findAll();
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public void setMunicipios(List<Municipio> municipios) {
        this.municipios = municipios;
    }

    /**
     * Carregar a lista de provincias
     *
     * @return
     */
    public List<Provincia> getProvincias(){
        return provincias;
    }

    public void loadMunicipios() {
        municipios = municipioFacade.findByIdProvincia(provincia);
    }

    public void carregaMunicipiosDaProvincia(ValueChangeEvent event) {
        Provincia p = (Provincia) event.getNewValue();
        Integer id = p.getIdProvincia();
        System.out.print("Sigla>>>>>>" + event.getNewValue().toString());
        municipios = municipioFacade.findByIdProvincia(provincia);

    }

    /**
     *
     * @param event - carrega os municipios da proncia seleccionada
     */
    public void listaMunicipiosDaProvincia(AjaxBehaviorEvent event) {

        String dueDate = (String) ((UIOutput) event.getSource()).getValue();

        System.out.println("Provincia <<<<<=====" + dueDate);
        //municipios = municipioDAO.findByIdProvincia(provincia);
    }

    public void setProvincias(List<Provincia> provincias) {
        this.provincias = provincias;
    }

    public List<Municipio> getMunicipios() {
        return municipios;
    }

}
