package com.concessionaria_api.service;

import com.concessionaria_api.config.RecursoDuplicadoException;
import com.concessionaria_api.config.RecursoNaoEncontradoException;
import com.concessionaria_api.dto.ClienteRequestDTO;
import com.concessionaria_api.dto.ClienteResponseDTO;
import com.concessionaria_api.model.Cliente;
import com.concessionaria_api.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteResponseDTO cadastrar(ClienteRequestDTO dto) {

        if(clienteRepository.existsByCpf(dto.cpf())) {
            throw new RecursoDuplicadoException("Já existe um cliente cadastrado com esse CPF.");
        }

        Cliente cliente = Cliente.builder()
                .nome(dto.nome())
                .email(dto.email())
                .cpf(dto.cpf())
                .telefone(dto.telefone())
                .build();

        return toResponse(clienteRepository.save(cliente));
    }

    public List<ClienteResponseDTO> listar() {
        List<Cliente> clientes = clienteRepository.findAll();

        return clientes.stream()
                .map((s) -> toResponse(s)).toList();

    }

    public ClienteResponseDTO buscarPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não foi encontrado."));

        return ClienteResponseDTO.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .email(cliente.getEmail())
                .cpf(cliente.getCpf())
                .telefone(cliente.getTelefone())
                .build();
    }

    public Void deletar(Long id) {
        ClienteResponseDTO cliente = buscarPorId(id);

        if (cliente == null) {
            throw new RecursoNaoEncontradoException("Cliente não encontrado.");
        } else {
            clienteRepository.deleteById(id);
        }
        return null;
    }

    public ClienteResponseDTO toResponse (Cliente cliente) {
        return ClienteResponseDTO.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .email(cliente.getEmail())
                .cpf(cliente.getCpf())
                .telefone(cliente.getTelefone())
                .build();
    }

}
