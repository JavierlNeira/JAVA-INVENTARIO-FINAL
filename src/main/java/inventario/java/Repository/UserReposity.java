package inventario.java.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import inventario.java.entities.Usuario;

public interface UserReposity extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByUsername(String username);
}
