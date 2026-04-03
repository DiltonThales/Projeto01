package com.fai.aprendendospring.business;

import com.fai.aprendendospring.infrastructure.entity.Usuario;
import com.fai.aprendendospring.infrastructure.exeptions.ConlictException;
import com.fai.aprendendospring.infrastructure.exeptions.ResourceNotFoundExcepton;
import com.fai.aprendendospring.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor //gera um constreutor que possui os campos private final
public class UsuarioService {


    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public Usuario salvaUsuario(Usuario usuario) {
        try {
            emailExiste(usuario.getEmail());
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
            return usuarioRepository.save(usuario);

        } catch (Exception e) {
            throw new ConlictException("Email já cadastrado", e.getCause());

        }
    }

    public void emailExiste(String email){
        try {
            boolean existe = verificaEmailExistente(email);
            if (existe){
                throw new ConlictException("Email já cadastrado"  + email);
            }
        }catch (ConlictException e){
            throw new ConlictException("Email já cadastrado" + e.getCause());
        }
    }

    public  boolean verificaEmailExistente(String email){
        return usuarioRepository.existsByEmail(email);
    }

    public Usuario buscarUsuarioPorEmail(String email){
        return usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundExcepton("Email não encontrado" + email));
    }

    public void deletaUsuarioPorEmail(String email){
        usuarioRepository.deleteByEmail(email);
    }
}
