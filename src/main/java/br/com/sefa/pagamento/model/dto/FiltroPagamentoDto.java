package br.com.sefa.pagamento.dto;

public class FiltroPagamentoDto {

    private Integer codigoDebito;
    private String cpfCnpj;
    private String numeroCartao;
    private Double valorPagamento;
    private String tipoPagamento;
    private String statusPagamento;
 
    public FiltroPagamentoDto() {}

 
    public FiltroPagamentoDto(Integer codigoDebito, String cpfCnpj, String numeroCartao, 
                        Double valorPagamento, String tipoPagamento, String statusPagamento) {
        this.codigoDebito = codigoDebito;
        this.cpfCnpj = cpfCnpj;
        this.numeroCartao = numeroCartao;
        this.valorPagamento = valorPagamento;
        this.tipoPagamento = tipoPagamento;
        this.statusPagamento = statusPagamento;
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

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public String getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(String statusPagamento) {
        this.statusPagamento = statusPagamento;
    }
}
