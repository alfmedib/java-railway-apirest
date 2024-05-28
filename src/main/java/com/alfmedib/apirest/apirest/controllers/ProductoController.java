package com.alfmedib.apirest.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alfmedib.apirest.apirest.entities.Producto;
import com.alfmedib.apirest.apirest.repositories.ProductoRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("/nombre")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
    @GetMapping
    public List<Producto> listaProductos(){
        return productoRepository.findAll();
        
    }

    @GetMapping("/{id}")
    public Producto findProducById(@PathVariable Long id){
        return productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontró el id enviado " + id));

    }

    
    @PostMapping
    public Producto crearProducto(@RequestBody Producto producto){
        return productoRepository.save(producto);

    }

    @PutMapping("/{id}")
    public Producto actualizarProducto(@PathVariable Long id, @RequestBody Producto detalleProducto){

        Producto producto = productoRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("El producto no fue encontrado"));

        producto.setNombre(detalleProducto.getNombre());
        producto.setPrecio(detalleProducto.getPrecio());

        return productoRepository.save(producto);

    }

    @DeleteMapping("/{id}")
    public String borrarProducto(@PathVariable Long id){

        Producto producto = productoRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("El producto no fue encontrado"));
        productoRepository.save(producto);
        return "El producto con el id: " + id + " fue eliminado correctamete";
    }

}
