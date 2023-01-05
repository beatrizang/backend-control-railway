/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.control.control.auth;

import com.control.control.models.entity.Usuario;
import com.control.control.models.services.IUsuarioService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

/**
 *
 * @author Beatriz
 */
@Component
public class InfoAdicionalToken implements TokenEnhancer{
    
    @Autowired
    private IUsuarioService usuarioService;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oaat, OAuth2Authentication oaa) {
       
        Usuario usuario = usuarioService.findByUsername(oaa.getName());
        
        Map<String,Object> info = new HashMap<>();
        info.put("info_adicional", "Hola que tal".concat(oaa.getName()));
        info.put("nombre_usuario", usuario.getId() +" : "+ usuario.getUsername());
        
        
        ((DefaultOAuth2AccessToken) oaat).setAdditionalInformation(info);
        
        return oaat;
    }
    
}
