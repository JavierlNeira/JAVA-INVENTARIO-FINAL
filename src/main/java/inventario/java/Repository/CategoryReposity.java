package inventario.java.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import inventario.java.entities.Categoria;

public interface CategoryReposity extends JpaRepository<Categoria, Long> {


    
}
