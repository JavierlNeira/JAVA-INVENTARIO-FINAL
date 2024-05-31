package inventario.java.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_categoria;


    private String nombre_categoria;
    private String nombre_proveedor_categoria;

    @OneToMany(cascade = CascadeType.ALL)
    // @JoinColumn(name = "id_categoria")
    @JsonIgnore
    private List<Producto> productos = new ArrayList<>();

}
