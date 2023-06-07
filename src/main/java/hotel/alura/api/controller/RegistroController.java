package hotel.alura.api.controller;

import hotel.alura.api.registro.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/registro")
public class RegistroController {
    @Autowired
    private RegistroRepository registroRepository;// se crea una instancia autoconectada en spring al repositorio donde se creara la persistencia en la bd

    @PostMapping
    public ResponseEntity<RetornoRegistro> crearRegistro(@RequestBody @Valid DTORegistro dtoRegistro, UriComponentsBuilder uriComponentsBuilder){
        System.out.println(dtoRegistro);
        Registro registro = registroRepository.save(new Registro(dtoRegistro));//se guarda la entidad registro en la db y retorna el objeto creado
        RetornoRegistro retornoRegistro = new RetornoRegistro(registro);// se usa un objeto de retorno para no interactuar direnctamente con la entidad regstro
        URI url = uriComponentsBuilder.path("/registro/{id}").buildAndExpand(registro.getId()).toUri();// se crea el path dinaminamente y se la asisgna el # de id
        return ResponseEntity.created(url).body(retornoRegistro);// se retorna un status 201 created un la url del registro creado y un objeto de retorno con los valores
    }


    @GetMapping
    public ResponseEntity<List<RetornoRegistro>> listadoRegistros(){
        //return registroRepository.findAll().stream().map(RetornoRegistro:: new).toList();
        List<RetornoRegistro> registros = registroRepository.findByActivoTrue().stream().map(RetornoRegistro:: new).toList();// en este metodo se modifico el comportaminto de la interfaz RegistroRepository para buscar en la bd vos valores que cumplieran con la condicion
        return ResponseEntity.ok().body(registros);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<RetornoRegistro> actualizarRegistro(@RequestBody @Valid DTOActualizarRegistro dtoActualizarRegistro){
        Registro registro = registroRepository.findByIdRegistroContaining(dtoActualizarRegistro.id_registro());
        registro.actualizarDatos(dtoActualizarRegistro);
        //return new RetornoRegistro(registro);
        return ResponseEntity.ok().body(new RetornoRegistro(registro));
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity DesactivarRegistro(@RequestBody @Valid DTOActualizarRegistro dtoActualizarRegistro){
        System.out.println(dtoActualizarRegistro);
        Registro registro = registroRepository.findByIdRegistroContaining(dtoActualizarRegistro.id_registro());
        registro.desactivarRegistro();
        //return new RetornoRegistro(registro);
        //return ResponseEntity.ok().body(new RetornoRegistro(registro));
        return ResponseEntity.noContent().build();
    }





}
