package br.com.sefa.pagamento.service;

import br.com.sefa.pagamento.dto.PagamentoDto;
import br.com.sefa.pagamento.model.PagamentoEntity;
import br.com.sefa.pagamento.model.StatusPagamentoEnum;
import br.com.sefa.pagamento.model.TipoPagamentoEnum;
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
        if ((pagamentoDto.getTipoPagamento() == TipoPagamentoEnum.CARTAO_CREDITO || 
             pagamentoDto.getTipoPagamento() == TipoPagamentoEnum.CARTAO_DEBITO) && 
            (pagamentoDto.getNumeroCartao() == null || pagamentoDto.getNumeroCartao().isEmpty())) {
            throw new RuntimeException("Numero do cartao e obrigatorio para pagamentos com cartao.");
        }

        if (pagamentoDto.getTipoPagamento() != TipoPagamentoEnum.CARTAO_CREDITO && 
            pagamentoDto.getTipoPagamento() != TipoPagamentoEnum.CARTAO_DEBITO && 
            pagamentoDto.getNumeroCartao() != null) {
            throw new RuntimeException("Numero do cartao nao e necessario para este tipo de pagamento.");
        }

        PagamentoEntity pagamentoEntity = convertToEntity(pagamentoDto);
        pagamentoEntity = pagamentoRepository.save(pagamentoEntity);
        return convertToDto(pagamentoEntity);
    }

    public PagamentoDto atualizarStatusPagamento(Long id, StatusPagamentoEnum novoStatus) {
        PagamentoEntity pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pagamento nao encontrado"));

        if (pagamento.getStatusPagamentoEnum() == StatusPagamentoEnum.PENDENTE_PROCESSAMENTO) {
            if (novoStatus != StatusPagamentoEnum.PROCESSADO_SUCESSO && novoStatus != StatusPagamentoEnum.PROCESSADO_FALHA) {
                throw new RuntimeException("O pagamento pendente so pode ser alterado para 'Processado com Sucesso' ou 'Processado com Falha'.");
            }
        } else if (pagamento.getStatusPagamentoEnum() == StatusPagamentoEnum.PROCESSADO_SUCESSO) {
            throw new RuntimeException("O status 'Processado com Sucesso' nao pode ser alterado.");
        } else if (pagamento.getStatusPagamentoEnum() == StatusPagamentoEnum.PROCESSADO_FALHA) {
            if (novoStatus != StatusPagamentoEnum.PENDENTE_PROCESSAMENTO) {
                throw new RuntimeException("O pagamento 'Processado com Falha' so pode ser alterado para 'Pendente de Processamento'.");
            }
        }

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
            pagamentos = pagamentoRepository.findAll();
        }

        return pagamentos.stream().map(this::convertToDto).collect(Collectors.toList());
    }
 
    

    public void excluirPagamentoLogicamente(Long id) {
        PagamentoEntity pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pagamento nao encontrado"));

        if (pagamento.getStatusPagamentoEnum() == StatusPagamentoEnum.PENDENTE_PROCESSAMENTO) {
            pagamento.setStatusPagamentoEnum(StatusPagamentoEnum.INATIVO);
            pagamentoRepository.save(pagamento);
        } else {
            throw new RuntimeException("Apenas pagamentos com status 'Pendente de Processamento' podem ser excluidos logicamente.");
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
        return entity;
    }
}
