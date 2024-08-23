package br.com.sefa.pagamento.repository;

import br.com.sefa.pagamento.model.PagamentoEntity;
import br.com.sefa.pagamento.model.StatusPagamentoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagamentoRepository extends JpaRepository<PagamentoEntity, Long> {

    List<PagamentoEntity> findByCodigoDebito(Integer codigoDebito);

    List<PagamentoEntity> findByCpfCnpj(String cpfCnpj);

    List<PagamentoEntity> findByStatusPagamento(StatusPagamentoEnum statusPagamento);

    List<PagamentoEntity> findByAtivoTrue();

    List<PagamentoEntity> findByCodigoDebitoAndCpfCnpjAndStatusPagamento(
        Integer codigoDebito, String cpfCnpj, StatusPagamentoEnum statusPagamento
    );
}
