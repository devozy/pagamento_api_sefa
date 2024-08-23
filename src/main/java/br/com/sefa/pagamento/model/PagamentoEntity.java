package br.com.sefa.pagamento.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity(name = "TBL_PAGAMENTO")
public class PagamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CODIGO_DEBITO", nullable = false)
    private Integer codigoDebito;

    @Column(name = "CPF_CNPJ", nullable = false)
    private String cpfCnpj;

    @Column(name = "NUMERO_CARTAO")
    private String numeroCartao = "";

    @Column(name = "VALOR_PAGAMENTO", nullable = false)
    private Double valorPagamento;

    @Column(name = "TIPO_PAGAMENTO", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoPagamentoEnum tipoPagamento;

    @Column(name = "STATUS_PAGAMENTO", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusPagamentoEnum statusPagamento;

    @Column(nullable = false)
    private boolean ativo = true;

    public PagamentoEntity() {}

    public PagamentoEntity(Integer codigoDebito, String numeroCartao, String cpfCnpj,
                           Double valorPagamento, TipoPagamentoEnum tipoPagamento, 
                           StatusPagamentoEnum statusPagamento) {
        this.codigoDebito = codigoDebito;
        this.numeroCartao = numeroCartao;
        this.cpfCnpj = cpfCnpj;
        this.valorPagamento = valorPagamento;
        this.tipoPagamento = tipoPagamento;
        this.statusPagamento = statusPagamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCodigoDebito() {
        return codigoDebito;
    }

    public void setCodigoDebito(Integer codigoDebito) {
        this.codigoDebito = codigoDebito;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public Double getValorPagamento() {
        return valorPagamento;
    }

    public void setValorPagamento(Double valorPagamento) {
        this.valorPagamento = valorPagamento;
    }

    public TipoPagamentoEnum getTipoPagamentoEnum() {
        return tipoPagamento;
    }

    public void setTipoPagamentoEnum(TipoPagamentoEnum tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public StatusPagamentoEnum getStatusPagamentoEnum() {
        return statusPagamento;
    }

    public void setStatusPagamentoEnum(StatusPagamentoEnum statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PagamentoEntity that = (PagamentoEntity) o;
        return Objects.equals(id, that.id) &&
               Objects.equals(codigoDebito, that.codigoDebito) &&
               Objects.equals(cpfCnpj, that.cpfCnpj) &&
               Objects.equals(valorPagamento, that.valorPagamento) &&
               Objects.equals(tipoPagamento, that.tipoPagamento) &&
               Objects.equals(statusPagamento, that.statusPagamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codigoDebito, cpfCnpj, valorPagamento, tipoPagamento, statusPagamento);
    }
}
