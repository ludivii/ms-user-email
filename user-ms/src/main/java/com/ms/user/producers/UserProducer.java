package com.ms.user.producers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ms.user.dto.EmailDTO;
import com.ms.user.models.UserModel;

@Component
public class UserProducer {

	final RabbitTemplate rabbitTemplate;
	
	public UserProducer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	@Value(value = "${broker.queue.email.name}")
	private String routingKey;
	
	public void publishMessageEmail(UserModel userModel) {
		var emailDTO = new EmailDTO();
		emailDTO.setUserId(userModel.getUserId());
		emailDTO.setEmailTo(userModel.getEmail());
		emailDTO.setSubject("Cadastro realizado com sucesso!");
		emailDTO.setText(userModel.getName() + " seja bem vindo(a)! \nAgradecemos o seu cadastro, aproveite agora todos os recursos da nossa plataforma!");
		
		rabbitTemplate.convertAndSend("", routingKey, emailDTO);
	}
}