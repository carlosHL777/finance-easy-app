package br.com.fatecmaua.finance_easy_app.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.fatecmaua.finance_easy_app.model.MetaCategoriaEnum;
import br.com.fatecmaua.finance_easy_app.model.MetasFinanceiras;
import br.com.fatecmaua.finance_easy_app.repository.MetasRepository;

@Service
public class MetasService {

	@Autowired
	private final MetasRepository repMet;

	public MetasService(MetasRepository repMet) {
		this.repMet = repMet;
	}

	public MetasFinanceiras criar(MetasFinanceiras meta) { // criando meta
		meta.setPercentual(0.0);
		Double percentualConclusao = (meta.getValorArrecadado().doubleValue() / meta.getValorTotal().doubleValue()) * 100; // calculando percentual

		meta.setId(null);
		meta.setPercentual(Math.round(percentualConclusao * 100.0) / 100.0);
		return repMet.save(meta);
	}

	public List<MetasFinanceiras> listarPorCategorias(MetaCategoriaEnum categoria) {
		return repMet.findByCategoria(categoria);
	}

	public List<MetasFinanceiras> listarPorPeriodo(LocalDate inicio, LocalDate fim) {
		return repMet.findByDataBetween(inicio, fim);
	}

	public MetasFinanceiras metaMaiorValorArrecadado() {
	    return repMet.findTopByOrderByValorArrecadadoDesc()
	            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND
));
	}

	public MetasFinanceiras metaMenorValorArrecadado() {
	    return repMet.findTopByValorArrecadadoIsNotNullOrderByValorArrecadadoAsc()
	            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND
));
	}

	public MetasFinanceiras metaMaiorPercentual() {
	    return repMet.findTopByOrderByPercentualDesc()
	            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND
));
	}
	public MetasFinanceiras atualizarCategoriaPorId(Long id, MetaCategoriaEnum categoria) {
		Optional<MetasFinanceiras> busca = repMet.findById(id);
		
		if (!repMet.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		MetasFinanceiras meta = busca.get();
		meta.setCategoria(categoria);
		return repMet.save(meta);
		
	}
	
	public MetasFinanceiras atualizarValorArrecadadoPorId(Long id, Double valorArrecadado) {
		Optional<MetasFinanceiras> busca = repMet.findById(id);
		
		if (!repMet.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		MetasFinanceiras meta = busca.get();
		meta.setValorArrecadado(valorArrecadado);
		meta.setPercentual(0.0);
		Double percentualConclusao = (
				meta.getValorArrecadado().doubleValue() / meta.getValorTotal().doubleValue()
				) * 100; // calculando percentual atualizado
		meta.setPercentual(percentualConclusao);
		return repMet.save(meta);
		
	}
	
	public MetasFinanceiras removerMetaPorId(Long id) {
		Optional<MetasFinanceiras> busca = repMet.findById(id);
		
		if (!repMet.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		MetasFinanceiras meta = busca.get();
		repMet.deleteById(id);
		return meta;
	}

}
