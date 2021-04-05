package br.gov.sp.fatec.ecommerce.controller;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.gov.sp.fatec.ecommerce.entity.Pedido;
import br.gov.sp.fatec.ecommerce.exception.RegistroNaoEncontradoException;
import br.gov.sp.fatec.ecommerce.repository.PedidoRepository;
import br.gov.sp.fatec.ecommerce.service.SegurancaService;

//metodos que estiverem aqui podem ser rotas (serviços)
@RestController
//permite acesso externo de qualquer lugar se não colocar as origens. 
@RequestMapping(value = "/pedido")
@CrossOrigin
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepo;

    @Autowired
    private SegurancaService segurancaService;

    @JsonView(View.PedidoLista.class)
    @GetMapping
    public List<Pedido> buscarClientes(){
        return segurancaService.listarPedidos();
    }

    @JsonView(View.PedidoLista.class)
    @GetMapping(value = "/nome")
    public Pedido buscarPedidoPorNome(@RequestParam(value = "nome") String nome){
        return segurancaService.buscarPedidoPorNome(nome);
    }

    @JsonView(View.PedidoLista.class)
    @GetMapping(value = "/id/{id}")
    public Pedido buscarPedidoPorId(@PathVariable("id") Long id){
        return segurancaService.buscarPedidoPorId(id);
    }

    @PostMapping
    public ResponseEntity<Pedido> cadastrarNovoPedido(@RequestBody Pedido pedido,
    UriComponentsBuilder uriComponentsBuilder) throws Exception {
        pedido = segurancaService.criarPedido(pedido.getNome(), pedido.getValor(), "pedro@fatec.com.br");
        HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setLocation(
                uriComponentsBuilder.path("/pedido/id/" + pedido.getId()).build().toUri());
        
        return new ResponseEntity<Pedido>(pedido, responseHeaders, HttpStatus.CREATED);
    }   

    @DeleteMapping("/id/{id}")
    public String deletePedido(@PathVariable(value = "id") Long id) {
        Pedido pedido = pedidoRepo.buscaPedidoPorId(id);
        if(pedido != null){
            pedidoRepo.delete(pedido);
            return "Pedido excluído com sucesso";
        }               
        throw new RegistroNaoEncontradoException("Pedido não encontrado!");            
    }

    
   

    
    
}
