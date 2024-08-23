package br.com.sefa.pagamento.controller;

import br.com.sefa.pagamento.dto.PagamentoDto;
import br.com.sefa.pagamento.model.StatusPagamentoEnum;
import br.com.sefa.pagamento.service.PagamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagamentos")
@Tag(name = "Pagamentos", description = "Operacoes relacionadas aos pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping
    @Operation(summary = "Recebe um pagamento")
    public ResponseEntity<PagamentoDto> receberPagamento(
            @RequestBody PagamentoDto pagamentoDto) {
        PagamentoDto novoPagamento = pagamentoService.receberPagamento(pagamentoDto);
        return ResponseEntity.ok(novoPagamento);
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "Atualiza o status de um pagamento. Primeiro verifica se ha pagamentos para atualizar.")
    public ResponseEntity<PagamentoDto> atualizarStatusPagamento(
            @PathVariable Long id,
            @RequestParam StatusPagamentoEnum novoStatus) {
        PagamentoDto pagamentoAtualizado = pagamentoService.atualizarStatusPagamento(id, novoStatus);
        return ResponseEntity.ok(pagamentoAtualizado);
    }

    @GetMapping
    @Operation(summary = "Lista todos os pagamentos com filtros")
    public ResponseEntity<List<PagamentoDto>> listarPagamentos(
            @RequestParam(required = false) Integer codigoDebito,
            @RequestParam(required = false) String cpfCnpj,
            @RequestParam(required = false) StatusPagamentoEnum statusPagamento) {
        List<PagamentoDto> pagamentos = pagamentoService.listarPagamentos(codigoDebito, cpfCnpj, statusPagamento);
        return ResponseEntity.ok(pagamentos);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui um pagamento")
    public ResponseEntity<Void> excluirPagamentoLogicamente(
            @PathVariable Long id) {
        pagamentoService.excluirPagamentoLogicamente(id);
        return ResponseEntity.noContent().build();
    }
}
