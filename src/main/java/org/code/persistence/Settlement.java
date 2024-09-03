package org.code.persistence;

import org.code.model.entities.Chanel;
import org.code.model.entities.Message;
import org.code.model.entities.Users;

import java.time.LocalDate;
import java.time.LocalTime;

public class Settlement {
    public static void implement() {
        //exemplos de inserções nas tabelas do banco de dados

        Chanel chanel = new Chanel(null, "Chat", "Canal de comunicação entre alunos");
        Users users = null;
        //Message message = new Message(null, LocalTime.now(), LocalDate.now(), users, chanel);

        chanel.setOneUser(users);
        users.setOneChanel(chanel);

        try{
            DataService.saveItem(chanel);
            DataService.saveItem(users);
            //DataService.saveItem(message);
        } catch (Exception err) {
            System.out.println("Erro ao povoar: " + err.getMessage());
        }

    }
}
