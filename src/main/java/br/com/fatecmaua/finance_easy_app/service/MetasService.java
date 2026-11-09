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
import br.com.fatecmaua.finance_easy_app.model.Usuario;
import br.com.fatecmaua.finance_easy_app.repository.MetasRepository;
import br.com.fatecmaua.finance_easy_app.repository.UsuarioRepository;

@Service
public class MetasService {

	@Autowired
	private final MetasRepository repMet;
	private final UsuarioRepository repUser;

	public MetasService(MetasRepository repMet, UsuarioRepository repUser) {
	    this.repMet = repMet;
	    this.repUser = repUser;
	}

	public MetasFinanceiras criar(MetasFinanceiras meta) {
	    if (meta.getUsuario() == null || meta.getUsuario().getId() == null) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
	                "Informe o usuario da meta: \"usuario\": { \"id\": 1 }");
	    }
	    if (meta.getValorTotal() == null || meta.getValorTotal() <= 0) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
	                "O valor total da meta deve ser maior que zero");
	    }
	    if (meta.getValorArrecadado() == null || meta.getValorArrecadado() < 0) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
	                "O valor arrecadado deve ser informado e não pode ser negativo");
	    }

	    Long usuarioId = meta.getUsuario().getId();
	    Usuario usuario = repUser.findById(usuarioId).orElseThrow(() ->
	            new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
	    meta.setUsuario(usuario);

	    double percentual = (meta.getValorArrecadado() / meta.getValorTotal()) * 100;
	    percentual = Math.min(percentual, 100.0);   // limitando o percentutal de conclusão
	    meta.setPercentual(Math.round(percentual * 100.0) / 100.0);

	    meta.setId(null);
	    return repMet.save(meta);
	}

	public List<MetasFinanceiras> listarMetas() {
		return repMet.findAll();
	}
	
	public List<MetasFinanceiras> listarPorCategorias(MetaCategoriaEnum categoria) {
		return repMet.findByCategoria(categoria);
	}

	public List<MetasFinanceiras> listarPorPeriodo(LocalDate inicio, LocalDate fim) {
		return repMet.findByDataBetween(inicio, fim);
	}

	public MetasFinanceiras metaMaiorValorArrecadado() {
	    return repMet.metaMaiorArrecadado()
	            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND
));
	}

	public MetasFinanceiras metaMenorValorArrecadado() {
	    return repMet.metaMenorArrecadado()
	            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND
));
	}

	public MetasFinanceiras metaMaiorPercentual() {
	    return repMet.maiorPercentual()
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
