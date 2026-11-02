package br.com.fatecmaua.finance_easy_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fatecmaua.finance_easy_app.model.TipoEnum;
import br.com.fatecmaua.finance_easy_app.model.Transacoes;
import br.com.fatecmaua.finance_easy_app.repository.TransacoesRepository;

@Service
public class TransacoesService {

	@Autowired
	private final TransacoesRepository repTran;
	
	public TransacoesService(TransacoesRepository repTran) {
		this.repTran = repTran;
	}

    public Transacoes criar(Transacoes transacao) {
        // saldo anterior = valorAtual da última transação, ou 0 se for a primeira
        double saldoAnterior = repTran.findTopByOrderByIdDesc().get().getValorAtual();
                

        double valor = transacao.getValor();

        double novoSaldo = (transacao.getTipo() == TipoEnum.RENDA)
                ? saldoAnterior + valor
                : saldoAnterior - valor;

        transacao.setId(null);
        transacao.setValorAtual(novoSaldo);

        return repTran.save(transacao);
    }
	
	public List<Transacoes> listarTransacoes() {
		return repTran.findAll();
	}
	
}
