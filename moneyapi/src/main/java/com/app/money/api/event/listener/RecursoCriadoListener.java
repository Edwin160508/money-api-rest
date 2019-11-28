package com.app.money.api.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.app.money.api.event.RecursoCriadoEvent;

/**
 * Classe responsável por ouvir evento RecursoCriadoEvent
 * 
 * @author edwin
 *
 */
@Component
public class RecursoCriadoListener implements ApplicationListener<RecursoCriadoEvent>{

	@Override
	public void onApplicationEvent(RecursoCriadoEvent recursoCriadoEvent) {
		HttpServletResponse response = recursoCriadoEvent.getResponse(); 
		Long codigo = recursoCriadoEvent.getId();
		
		adcionarHeaderLocation(response, codigo);
		
	}

	/**
	 * 
	 * Método responsável por adicionar Header Location apos requisição cadastro
	 * 
	 * @param response
	 * @param codigo
	 */
	private void adcionarHeaderLocation(HttpServletResponse response, Long codigo) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{codigo}").buildAndExpand(codigo).toUri();
		response.setHeader("Location", uri.toASCIIString());
	}

}
