package br.com.sefa.pagamento.service;

import br.com.sefa.pagamento.dto.PagamentoDto;
import br.com.sefa.pagamento.model.PagamentoEntity;
import br.com.sefa.pagamento.model.TipoPagamentoEnum;
import br.com.sefa.pagamento.model.StatusPagamentoEnum;
import br.com.sefa.pagamento.repository.PagamentoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PagamentoServiceTest {

    @Mock
    private PagamentoRepository pagamentoRepository;

    @InjectMocks
    private PagamentoService pagamentoService;

    PagamentoServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testReceberPagamento() {
    	
        PagamentoDto pagamentoDto = new PagamentoDto();
        pagamentoDto.setCodigoDebito(123);
        pagamentoDto.setCpfCnpj("12345678901");
        pagamentoDto.setNumeroCartao("1234567812345678");
        pagamentoDto.setValorPagamento(100.0);
        pagamentoDto.setTipoPagamento(TipoPagamentoEnum.CARTAO_CREDITO);
        pagamentoDto.setStatusPagamento(StatusPagamentoEnum.PENDENTE_PROCESSAMENTO); 

        PagamentoEntity pagamentoEntity = new PagamentoEntity();
        pagamentoEntity.setCodigoDebito(123);
        pagamentoEntity.setCpfCnpj("12345678901");
        pagamentoEntity.setNumeroCartao("1234567812345678");
        pagamentoEntity.setValorPagamento(100.0);
        pagamentoEntity.setTipoPagamentoEnum(TipoPagamentoEnum.CARTAO_CREDITO); 
        pagamentoEntity.setStatusPagamentoEnum(StatusPagamentoEnum.PENDENTE_PROCESSAMENTO); 

        when(pagamentoRepository.save(any(PagamentoEntity.class))).thenReturn(pagamentoEntity);

        PagamentoDto result = pagamentoService.receberPagamento(pagamentoDto);

        assertNotNull(result);
        assertEquals(pagamentoDto.getCodigoDebito(), result.getCodigoDebito());
        assertEquals(pagamentoDto.getCpfCnpj(), result.getCpfCnpj());
        assertEquals(pagamentoDto.getNumeroCartao(), result.getNumeroCartao());
        assertEquals(pagamentoDto.getValorPagamento(), result.getValorPagamento());
        assertEquals(pagamentoDto.getTipoPagamento(), result.getTipoPagamento());
        assertEquals(pagamentoDto.getStatusPagamento(), result.getStatusPagamento());
    }
}
