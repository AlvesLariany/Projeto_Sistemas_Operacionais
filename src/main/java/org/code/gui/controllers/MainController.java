package org.code.gui.controllers;

import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.code.application.App;
import org.code.gui.util.Alerts;
import org.code.gui.util.ImageUtil;
import org.code.model.entities.Chanel;
import org.code.model.entities.Message;
import org.code.model.entities.Users;
import org.code.model.util.HashUtil;
import org.code.model.util.TokenChanelUtil;
import org.code.model.util.TokenUserUtil;
import org.code.persistence.DataService;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class MainController {
    protected static final Long EDITAIS = 1L;
    protected static final Long VAGAS = 2L;

    protected static final Long CHAT = 3L;
    protected static final int CANAIS = 1;
    protected static final int PROFILE = 2;
    private boolean orientation = true;

    @FXML
    private ImageView imageView;

    @FXML
    private Label titlePanel;

    @FXML
    private ScrollPane scrollPaneMain;

    @FXML
    private VBox contentScrollPane;

    @FXML
    private HBox chatBar;

    @FXML
    private TextField contentChatBar;

    @FXML
    private VBox expandedMenu;

    @FXML
    private HBox rootElement;

    @FXML VBox profileVbox;

    @FXML
    private VBox vboxDefault;

    private static int actualPane;

    private VBox paneViewSuport;

    private boolean menuIsactive = true;


    @FXML
    private void toggleMenu() {
        FadeTransition ft = new FadeTransition(Duration.millis(300), expandedMenu);

        RotateTransition rotateTransition = new RotateTransition();

        rotateTransition.setNode(imageView);
        rotateTransition.setDuration(Duration.seconds(.3));
        rotateTransition.setCycleCount(1);
        rotateTransition.setAutoReverse(false);

        if (menuIsactive) {
            if (orientation) {
                rotateTransition.setByAngle(90);
                orientation = !orientation;
            }
            else {
                rotateTransition.setByAngle(-90);
                orientation = !orientation;
            }

            rotateTransition.play(); // Iniciar a rotação

            if (expandedMenu.isVisible()) {
                ft.setFromValue(1.0);
                ft.setToValue(0.0);
                ft.setOnFinished(e -> expandedMenu.setVisible(false));
            } else {
                expandedMenu.setVisible(true);
                ft.setFromValue(0.0);
                ft.setToValue(1.0);
            }

            ft.play();
            rotateTransition.play();
        }

        onAnyChanelClicked();
    }

    private void setTitleChanel(Long id) {
        if (titlePanel.isVisible()) {
            String title = DataService.getTitleChanel(id);
            if (title != null) {
                titlePanel.setText(title);
            }
            else {
                System.out.println("Erro ao carregar titulo da página");
            }
        }
    }

    private String dateFormater(String dateIn) {
        String dateDefault = "yyyy-MM-dd";
        String dateOutput = "dd/MM/yyyy";

        String dateFormatted = null;

        DateTimeFormatter dtm = DateTimeFormatter.ofPattern(dateDefault);
        DateTimeFormatter dtmOutput = DateTimeFormatter.ofPattern(dateOutput);

        try {
            // Analisar a string de entrada usando o padrão de entrada
            LocalDate date = LocalDate.parse(dateIn, dtm);

            // Formatá-lo para o novo padrão
            dateFormatted = date.format(dtmOutput);


        } catch (DateTimeParseException e) {
            System.out.println("Erro ao converter a data: " + e.getMessage());
        }
        return dateFormatted;
    }

    private void loadInView(List<Message> messages) {
        messages.forEach(message -> {
            try {
                //pegando usuário associado a mensagem
                Users users = DataService.findByHashEmail(message.getId_users().getEmail());

                FXMLLoader fxmlLoader = null;
                VBox messageElement = null;

                //usado para definir o tamanho da margem esquerda da mensagem
                boolean user_self = true;

                //adicionando estilo diferente se a mensagem foi enviada pelo usuário logado
                if (users.getEmail().equals(TokenUserUtil.getUserToken())) {
                    fxmlLoader = new FXMLLoader(getClass().getResource("/gui/views/components/MessageViewSelf.fxml"));
                }
                else {
                    fxmlLoader = new FXMLLoader(getClass().getResource("/gui/views/components/MessageViewOther.fxml"));
                    user_self = false;
                }

                messageElement = fxmlLoader.load();

                if (user_self) {
                    VBox.setMargin(messageElement, new Insets(10, 0, 10, 700));
                }
                else {
                    VBox.setMargin(messageElement, new Insets(10, 0, 10, 100));
                }

                MessageControl messageControl = fxmlLoader.getController();

                Image image = ImageUtil.getImageWithEmailUser(users.getEmail());

                String hour = message.getHour().toString();
                String hourFormated = hour.substring(0, 8);

                //adicionando o conteudo do banco na mensagem
                messageControl.setContent(users.getName(), dateFormater(message.getDate().toString()), hourFormated, message.getContent(), image);

                //adicionando a mensagem no scrollpane
                if (messageElement != null) {
                    contentScrollPane.getChildren().add(messageElement);
                }
            } catch (IOException error) {
                System.out.println("Error in load message: " + error.getMessage());
            }
        });
    }

    private void clearScrollPane() {
        if (contentScrollPane != null) {
            contentScrollPane.getChildren().clear();
        }
    }

    private void checkAndSetChatBar() {
        if (titlePanel != null) {
            chatBar.setVisible(titlePanel.getText().equals("CHAT"));
        }
    }

    private void loadMessagesInChanel(Long id) {
        Chanel chanel = DataService.findByChanelId(id);

        List<Message> messages = DataService.findMessageById(chanel);

        clearScrollPane();
        loadInView(messages);
    }

    private void preparateView(Long chanelId) {
        //define o titulo da página
        setTitleChanel(chanelId);

        //define o canal atual
        TokenChanelUtil.setToken(chanelId);

        //limpa a tela
        clearScrollPane();

        //verificar se o canal é o chat, permite acessoa a barra de digitação
        checkAndSetChatBar();

        //carrega as mensagens do canal clicado
        loadMessagesInChanel(chanelId);
    }

    @FXML
    public void onEnditaisButtonClicked() {
        preparateView(EDITAIS);
    }

    @FXML
    public void onChatButtonClicked() {
        preparateView(CHAT);
    }

    @FXML
    public void onEmployeeButtonClicked() {
        preparateView(VAGAS);
    }

    @FXML
    public void onUserSubmitMessage() {
        String input = contentChatBar.getText();

        if (input.isEmpty()) {
            Alerts.showAlert("Falha", null, "Erro ao enviar mensagem, campo está vazio", Alert.AlertType.WARNING);
        }
        else {
            //usuario que enviou
            Users users = DataService.findByHashEmail(TokenUserUtil.getUserToken());
            Chanel chanel = DataService.findByChanelId(TokenChanelUtil.getToken());

            if (users != null && chanel != null) {
                Message message = new Message(null, LocalTime.now(), LocalDate.now(), input, users, chanel);

                DataService.saveItem(message);

                loadMessagesInChanel(CHAT);
                contentChatBar.setText("");

            }
            else {
                Alerts.showAlert("Erro", null, "Erro ao enviar a mensagem, tente novamente", Alert.AlertType.WARNING);
            }
        }
    }

    @FXML
    public void onProfileButtonClicked() {
        if (actualPane != PROFILE) {
            alternateChanelInProfile(PROFILE);
        }
    }

    @FXML
    public void onAnyChanelClicked() {
        if (actualPane != CANAIS) {
            alternateChanelInProfile(CANAIS);
        }
    }

    private double calculateWdtht() {
        return (rootElement.getWidth()) - 200;
    }

    private void alternateChanelInProfile(int idPane) {
        if (idPane == CANAIS) {

            rootElement.getChildren().remove(1);

            VBox chanelDefaultView = paneViewSuport;

            rootElement.getChildren().add(chanelDefaultView);

            actualPane = CANAIS;

            menuIsactive = true;

            if (!expandedMenu.isVisible()) {
                toggleMenu();
            }

            onEnditaisButtonClicked();

        } else if (idPane == PROFILE) {
            paneViewSuport = vboxDefault;

            rootElement.getChildren().remove(1);

            HBox profileView = null;

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/views/ProfileView.fxml"));
                profileView = fxmlLoader.load();

            } catch (IOException error) {
                System.out.println("Error ao carregar vbox do profile: " + error.getMessage());
            }

            profileView.setPrefWidth(calculateWdtht());

            Scene scene = App.getStageMainReference().getScene();

            scene.widthProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    HBox root = (HBox) rootElement.getChildren().get(1);
                    root.setPrefWidth(calculateWdtht());
                }
            });

            rootElement.getChildren().add(profileView);

            if (expandedMenu.isVisible()) {
                toggleMenu();
            }
            menuIsactive = false;
            actualPane = PROFILE;
        }
    }


    private void onEnterPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            onUserSubmitMessage();
        }
    }

    /*TODO: Reduzir codigo, funções iguais chamadar da mesma forma nos métodos
    *  verificar se o scroll pane foi até o seu limite inferior
    * pensar no perfil dos usuários especiais */
    @FXML
    private void initialize() {
        setTitleChanel(EDITAIS);
        TokenChanelUtil.setToken(EDITAIS);

        checkAndSetChatBar();

        //carregar mensagens do banco com base no ID do canal (Editais)
        actualPane = CANAIS;
        toggleMenu();

        //evento que monitora o VBox dentro do scroll pane, serve para deslizar a tela para baixo de form autometica
        contentScrollPane.heightProperty().addListener((obs, oldHeight, newHeight) -> {
            scrollPaneMain.setVvalue(1.0);
        });

        contentChatBar.setOnKeyPressed(event -> onEnterPressed(event));
    }
}
