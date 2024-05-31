package inventario.java.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import inventario.java.Service.CategoriaService;
import inventario.java.entities.Categoria;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/categorias")
@RequiredArgsConstructor
public class CategoryController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<Categoria> listartodo() {
        return categoriaService.getAllCategorias();
    }

    @PostMapping
    public ResponseEntity<String> crear(@Valid @RequestBody Categoria categoria) {
        categoriaService.creaProducto(categoria);
        return ResponseEntity.ok("Categoria creada con exito");
    }

    @PutMapping("editar/{id}")
    public Categoria autualizar(@RequestBody Categoria categoria, @PathVariable Long id) {
        categoria.setId_categoria(id);
        return categoriaService.actualizarCategoria(categoria);
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        categoriaService.deletecategoriabyid(id);
        return ResponseEntity.status(HttpStatus.OK).body("Categoría eliminada correctamente");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

}
