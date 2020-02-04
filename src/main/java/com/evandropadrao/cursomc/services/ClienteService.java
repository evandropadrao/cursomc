package com.evandropadrao.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evandropadrao.cursomc.ObjectNotFoundException;
import com.evandropadrao.cursomc.domain.Cliente;
import com.evandropadrao.cursomc.repositories.ClienteRepository;


@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	public Cliente get(Integer id) {
		
		Optional<Cliente> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName(), null));
	}
}
