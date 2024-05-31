package inventario.java.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_producto;

    //@ManyToOne
    private Long id_categoria;
    private String nombre_producto;
    private Double precio;

}
