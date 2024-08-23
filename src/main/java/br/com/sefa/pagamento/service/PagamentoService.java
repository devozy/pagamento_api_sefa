
package br.com.sefa.pagamento.service;

import br.com.sefa.pagamento.dto.PagamentoDto;
import br.com.sefa.pagamento.model.PagamentoEntity;
import br.com.sefa.pagamento.model.StatusPagamentoEnum;
import br.com.sefa.pagamento.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    public PagamentoDto receberPagamento(PagamentoDto pagamentoDto) {
        PagamentoEntity pagamentoEntity = convertToEntity(pagamentoDto);
        pagamentoEntity = pagamentoRepository.save(pagamentoEntity);
        return convertToDto(pagamentoEntity);
    }

    public PagamentoDto atualizarStatusPagamento(Long id, StatusPagamentoEnum novoStatus) {
        PagamentoEntity pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pagamento nao encontrado"));
        pagamento.setStatusPagamentoEnum(novoStatus);
        pagamento = pagamentoRepository.save(pagamento);
        return convertToDto(pagamento);
    }

    public List<PagamentoDto> listarPagamentos(Integer codigoDebito, String cpfCnpj, StatusPagamentoEnum statusPagamento) {
        List<PagamentoEntity> pagamentos;
        if (codigoDebito != null && cpfCnpj != null && statusPagamento != null) {
            pagamentos = pagamentoRepository.findByCodigoDebitoAndCpfCnpjAndStatusPagamento(codigoDebito, cpfCnpj, statusPagamento);
        } else if (codigoDebito != null) {
            pagamentos = pagamentoRepository.findByCodigoDebito(codigoDebito);
        } else if (cpfCnpj != null) {
            pagamentos = pagamentoRepository.findByCpfCnpj(cpfCnpj);
        } else if (statusPagamento != null) {
            pagamentos = pagamentoRepository.findByStatusPagamento(statusPagamento);
        } else {
            pagamentos = pagamentoRepository.findByAtivoTrue();
        }
        return pagamentos.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public void excluirPagamentoLogicamente(Long id) {
        PagamentoEntity pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pagamento nao encontrado"));
        if (pagamento.isAtivo()) {
            pagamento.setAtivo(false);
            pagamentoRepository.save(pagamento);
        } else {
            throw new RuntimeException("Pagamento inativo");
        }
    }

    private PagamentoDto convertToDto(PagamentoEntity pagamento) {
        PagamentoDto dto = new PagamentoDto();
        dto.setId(pagamento.getId());
        dto.setCodigoDebito(pagamento.getCodigoDebito());
        dto.setCpfCnpj(pagamento.getCpfCnpj());
        dto.setNumeroCartao(pagamento.getNumeroCartao());
        dto.setValorPagamento(pagamento.getValorPagamento());
        dto.setTipoPagamento(pagamento.getTipoPagamentoEnum());
        dto.setStatusPagamento(pagamento.getStatusPagamentoEnum());
        dto.setAtivo(pagamento.isAtivo());
        return dto;
    }

    private PagamentoEntity convertToEntity(PagamentoDto dto) {
        PagamentoEntity entity = new PagamentoEntity();
        entity.setCodigoDebito(dto.getCodigoDebito());
        entity.setCpfCnpj(dto.getCpfCnpj());
        entity.setNumeroCartao(dto.getNumeroCartao());
        entity.setValorPagamento(dto.getValorPagamento());
        entity.setTipoPagamentoEnum(dto.getTipoPagamento());
        entity.setStatusPagamentoEnum(dto.getStatusPagamento());
        entity.setAtivo(dto.isAtivo());
        return entity;
    }
}
