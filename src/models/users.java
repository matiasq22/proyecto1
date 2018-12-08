package models;

import java.util.Date;

/**
 *
 * @author Luis Fernando Martínez
 * www.programaresfacil.co
 */
public class users {
    private int id;
    private String user;
    private String password;
    private String type_user;
    private String description;
    private String email;
    private int id_type;
    private Date fecha;
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    private static users instance;
   
    /*
    public Usuario(int idusuario, String usuario, String nombreapellido, String identificacion, String idtipous ){
        this.IDUsuario = idusuario;
        this.Usuario = usuario;
        this.NombreApellidos = nombreapellido;
        this.Identificacion = identificacion;
        this.IdTipoUsuario = idtipous;
    }*/
    
    public users(){
        
    }
    
    
    /**
     * @return the IDUsuario
     */
    public int getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    /**
     * @param IDUsuario the IDUsuario to set
     */
    public void setId(int Id) {
        this.id = Id;
    }
       /**
     * @return the NombreApellidos
     */
    public String getPassword() {
        return password;
    }
    /**
     * @param NombreApellidos the NombreApellidos to set
     */
    public void setPassword(String Password) {
        this.password = Password;
    }
    /**
     * @return the Identificacion
     */
    public String getType_user() {
        return type_user;
    }
    /**
     * @param Identificacion the Identificacion to set
     */
    public void setType_user(String Type_user) {
        this.type_user = Type_user;
    }
    /**
     * @return the IdTipoUsuario
     */
    public String getEmail() {
        return email;
    }
    /**
     * @param email the IdTipoUsuario to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getDescription() {
        return description;
    }

    public int getId_type() {
        return id_type;
    }

    public void setId_type(int id_type) {
        this.id_type = id_type;
    }
    /**
     * @param email the IdTipoUsuario to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    public static users getInstance() {
        if (instance == null)
            instance = new users();
        return instance;
    }
}