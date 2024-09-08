package org.code.persistence;

import javafx.scene.chart.PieChart;
import org.code.model.entities.Chanel;
import org.code.model.entities.Message;
import org.code.model.entities.Users;

import java.time.LocalDate;
import java.time.LocalTime;

public class Settlement {
    private static final Long EDITAIS = 1L;
    public static void implement() {

        //povoando a tabela dos canis com os três canais principais
        Chanel chanel_edital = new Chanel(null, "EDITAIS", "Canal destinado a divulgação de editais");
        Chanel chanel_vagas = new Chanel(null, "VAGAS", "Canal destinado a publicação de vagas de emprego");
        Chanel chanel_chat = new Chanel(null, "CHAT", "Canal destinado a interação entre todos os usuários");

        try{
            Chanel chanelTest = DataService.findByChanelId(EDITAIS);

            //se o canal não existir, consequentemente, nenhum existe, então, são inseridos
            if (chanelTest == null) {
                DataService.saveItem(chanel_edital);
                DataService.saveItem(chanel_vagas);
                DataService.saveItem(chanel_chat);
            }
        } catch (NullPointerException err) {
            System.out.println("Erro ao povoar canais: " + err.getMessage());
        }

    }
}
