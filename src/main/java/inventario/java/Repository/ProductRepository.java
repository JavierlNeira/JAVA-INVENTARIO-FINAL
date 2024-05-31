package inventario.java.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import inventario.java.entities.Producto;

public interface ProductRepository extends JpaRepository<Producto,Long>{


    
}

