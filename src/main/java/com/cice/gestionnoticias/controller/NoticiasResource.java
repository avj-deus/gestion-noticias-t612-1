package com.cice.gestionnoticias.controller;

import com.cice.gestionnoticias.controller.dto.NoticiaDTO;
import com.cice.gestionnoticias.service.NoticiasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *  Clase resource de Noticias.
 *  Vamos a definir todos los metodos de un CRUD
 */
@RestController
public class NoticiasResource {

    @Autowired
    NoticiasService noticiasService;

    /**
     * Este metodo sirve para crear un recurso nuevo
     *
     * Dado un modelo noticia, persistiremos esta en la DB y devolveremos como respuesta el mismo objeto
     * creado junto a su identificador unico.
     *
     * @param noticia DTO con la información de la noticia para dar de alta en el sistema
     * @return ResponseEntity<NoticiaDTO>
     */
    @RequestMapping(path = "/noticias", method = RequestMethod.POST)
    public ResponseEntity<NoticiaDTO> crearNoticias(@RequestBody NoticiaDTO noticia){
        ResponseEntity<NoticiaDTO> response = null;
        if(validateNoticia(noticia)){
            // La noticia es valida
            NoticiaDTO noticiaDTO = noticiasService.crearNoticia(noticia);
            response = ResponseEntity.ok(noticiaDTO);
        } else {
            response = ResponseEntity.badRequest().body(noticia);
        }
        return response;
    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(path = "/noticias/{id}", method = RequestMethod.GET)
    public ResponseEntity<NoticiaDTO> getNoticiasById(@RequestParam(name = "id") Long id){
        return null;
    }


    private boolean validateNoticia(NoticiaDTO noticia){
        return (noticia.getTitulo().isEmpty() || noticia.getCuerpo().isEmpty()) ? false : true;
    }

}
