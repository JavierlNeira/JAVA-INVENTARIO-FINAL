package inventario.java.Service;

import java.util.List;

import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inventario.java.Repository.CategoryReposity;
import inventario.java.entities.Categoria;
import jakarta.validation.Valid;

@Service
public class CategoriaService {

    @Autowired
    private CategoryReposity categoryRepository;

    public List<Categoria> getAllCategorias() {

        List<Categoria> categorias = categoryRepository.findAll();
        if (categorias.isEmpty()) {
            throw new IllegalArgumentException("No hay categorías disponibles en la base de datos.");
        }
        return categoryRepository.findAll();
    }

    public Categoria actualizarCategoria(Categoria categoria) {
        Optional<Categoria> categoriaOptional = categoryRepository.findById(categoria.getId_categoria());
        if (categoriaOptional.isEmpty()) {
            throw new IllegalArgumentException(
                    "No se puede actualizar dado que no existe el ID numero " + categoria.getId_categoria()
                            + " En la tabla Categoria, primero cree la categoria");
        }
        if (categoria.getNombre_categoria() == null
                || !Pattern.matches("^[a-zA-Z]+$", categoria.getNombre_categoria())) {
            throw new IllegalArgumentException(
                    "El nombre de la categoría solo puede contener letras y no puede estar vacío.");
        }

        if (categoria.getNombre_proveedor_categoria() == null
                || !Pattern.matches("^[a-zA-Z]+$", categoria.getNombre_proveedor_categoria())) {
            throw new IllegalArgumentException("El nombre del proveedor de la categoría solo puede contener letras.");
        }

        return categoryRepository.save(categoria);
    }

    public void deletecategoriabyid(Long id) {
        Optional<Categoria> categoriaOptional = categoryRepository.findById(id);
        if (categoriaOptional.isEmpty()) {
            throw new IllegalArgumentException(
                    "No se encontró la categoría con el ID especificado. No se puede eliminar.");
        }
        categoryRepository.deleteById(id);
    }

    public Categoria creaProducto(@Valid Categoria categoria) {

        if (categoria.getNombre_categoria() == null
                || !Pattern.matches("^[a-zA-Z]+$", categoria.getNombre_categoria())) {
            throw new IllegalArgumentException(
                    "El nombre de la categoría solo puede contener letras y no puede estar vacío.");
        }

        if (categoria.getNombre_proveedor_categoria() == null
                || !Pattern.matches("^[a-zA-Z]+$", categoria.getNombre_proveedor_categoria())) {
            throw new IllegalArgumentException("El nombre del proveedor de la categoría solo puede contener letras.");
        }

        return categoryRepository.save(categoria);
    }

}
