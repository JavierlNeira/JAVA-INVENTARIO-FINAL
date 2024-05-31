package inventario.java.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import inventario.java.Service.ProductService;
import inventario.java.entities.Producto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api/v1/productos")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Producto> listartodo() {
        return productService.getAllProducts();
    }

    @PostMapping
    public ResponseEntity<?> crearProducto(@Valid @RequestBody Producto producto) {
        try {
            productService.creaProducto(producto);
            return ResponseEntity.ok("Producto creado con éxito.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("editar/{id}")
    public Producto autualizar(@RequestBody Producto producto, @PathVariable Long id) {
        producto.setId_producto(id);
        return productService.actulizaProducto(producto);
    }

    @DeleteMapping("eliminar/{id}")
     public ResponseEntity<String> eliminar(@PathVariable Long id) {
        productService.deleteproductoById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Producto Eliminado correctamente");
    }

       @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }


}
