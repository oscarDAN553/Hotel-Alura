package hotel.alura.api.controller;

import hotel.alura.api.usuario.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
@Autowired
private UsuarioRepository usuarioRepository;
    @PostMapping
    public ResponseEntity<RetornoUsuario> crearUsuario(@RequestBody @Valid DTOUsuario dtoUsuario, UriComponentsBuilder uriComponentsBuilder){
        System.out.println(dtoUsuario);
        Usuario usuario = usuarioRepository.save(new Usuario(dtoUsuario));
        RetornoUsuario retornoUsuario = new RetornoUsuario(usuario);
        URI url = uriComponentsBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(url).body(retornoUsuario);
    }

    @GetMapping
    public ResponseEntity<List<RetornoUsuario>> listadoUsuarios(){
        //return usuarioRepository.findAll().stream().map(RetornoUsuario:: new).toList();
        //return usuarioRepository.findByActivoTrue().stream().map(RetornoUsuario:: new).toList();
        return ResponseEntity.ok(usuarioRepository.findByActivoTrue().stream().map(RetornoUsuario:: new).toList());
    }

    @PutMapping
    @Transactional
    public ResponseEntity<RetornoUsuario> actualizarUsuario(@RequestBody @Valid DTOActualizarUsuario dtoActualizarUsuario){
        Usuario usuario = usuarioRepository.getReferenceById(dtoActualizarUsuario.id());
        usuario.actualizarDatos(dtoActualizarUsuario);
        //return new RetornoUsuario(usuario);
        return ResponseEntity.ok().body(new RetornoUsuario(usuario));
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity DesactivarUsuario(@RequestBody @Valid DTOActualizarUsuario dtoActualizarUsuario){
        Usuario usuario = usuarioRepository.getReferenceById(dtoActualizarUsuario.id());
        usuario.desactivarUsuario();
        //return new RetornoUsuario(usuario);
        return ResponseEntity.noContent().build();
    }

}
