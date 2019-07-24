/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.entities;

/**
 *
 * @author Elísio Kavaimunwa
 */
public enum Estado {
    FINDO("Findo"), EM_ANDAMENTO("Em andamento");

    private String extensao;

    private Estado(String extensao) {
        this.extensao = extensao;
    }

    public String getExtensao() {
        return extensao;
    }

    public void setExtensao(String extensao) {
        this.extensao = extensao;
    }
    
   // Método auxiliar para interagir com o enum
    public static Estado getEstensao(String id) {
        for (Estado e : values()) {
            if (e.getExtensao().equalsIgnoreCase(id)) {
                return e;
            }
        }
        return null;
    }

    
    
}
