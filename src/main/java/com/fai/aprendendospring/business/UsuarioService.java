package com.fai.aprendendospring.business;

import com.fai.aprendendospring.infrastructure.entity.Usuario;
import com.fai.aprendendospring.infrastructure.exeptions.ConlictException;
import com.fai.aprendendospring.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor //gera um constreutor que possui os campos private final
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public Usuario salvaUsuario(Usuario usuario) {
        try {
            emailExiste(usuario.getEmail());
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
        return usuarioRepository.exitsByEmail(email);
    }
}
