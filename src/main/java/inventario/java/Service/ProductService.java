package inventario.java.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inventario.java.Repository.CategoryReposity;
import inventario.java.Repository.ProductRepository;
import inventario.java.entities.Categoria;
import inventario.java.entities.Producto;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryReposity categoryRepository; 

    // Mostrar todos los productos
    public List<Producto> getAllProducts() {
        List<Producto> producto = productRepository.findAll();
        if (producto.isEmpty()) {
            throw new IllegalArgumentException("No hay Productos disponibles en la base de datos.");
        }
        return productRepository.findAll();
    }

    // Crear un producto
    public Producto creaProducto(Producto producto) {
        Long idCategoria = producto.getId_categoria();
        Optional<Categoria> categoriaOptional = categoryRepository.findById(idCategoria);
        if (categoriaOptional.isEmpty()) {
            throw new IllegalArgumentException(
                    "La categoría asociada al producto no existe. No se puede crear el registro.");
        }
        if (producto.getNombre_producto() == null
                || !Pattern.matches("^[a-zA-Z]+$", producto.getNombre_producto())) {
            throw new IllegalArgumentException(
                    "El nombre del producto solo puede contener letras y no puede estar vacío.");
        }
        if (producto.getPrecio() == null || producto.getPrecio() <= 0) {
            throw new IllegalArgumentException("El PRECIO debe ser un número positivo válido.");
        }

        return productRepository.save(producto);
    }

    // Editar un producto
    public Producto actulizaProducto(Producto producto) {
        Optional<Producto> producOptional = productRepository.findById(producto.getId_producto());
        if (producOptional.isEmpty()) {
            throw new IllegalArgumentException(
                    "No se puede actualizar dado que no existe el ID numero " + producto.getId_producto()
                            + " En la tabla Producto, primero cree el PRODUCTO");
        }
        Long idCategoria = producto.getId_categoria();
        Optional<Categoria> categoriaOptional = categoryRepository.findById(idCategoria);
        if (categoriaOptional.isEmpty()) {
            throw new IllegalArgumentException(
                    "La categoría asociada al producto no existe. No se puede crear el registro.");
        }
        if (producto.getNombre_producto() == null
                || !Pattern.matches("^[a-zA-Z]+$", producto.getNombre_producto())) {
            throw new IllegalArgumentException(
                    "El nombre del producto solo puede contener letras y no puede estar vacío.");
        }
        if (producto.getPrecio() == null || producto.getPrecio() <= 0) {
            throw new IllegalArgumentException("El PRECIO debe ser un número positivo válido.");
        }
        return productRepository.save(producto);
    }

    // Eliminar un producto por su ID
    public void deleteproductoById(Long id) {
        Optional<Producto> producOptional = productRepository.findById(id);
        if (producOptional.isEmpty()) {
            throw new IllegalArgumentException(
                    "No se encontró el Producto con el ID especificado. No se puede eliminar.");
        }
        productRepository.deleteById(id);

    }

}
