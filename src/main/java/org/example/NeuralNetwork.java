package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class NeuralNetwork extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        return "";
    }

    @Override
    public String getBotToken() {
        return "";
    }
    @Override
    public void onUpdateReceived(Update update) {
        if (update.getMessage().getText().equals("/start")) {
            try {
                File f = new File("ids");
                FileWriter fw = new FileWriter(f, true);
                fw.write(update.getMessage().getFrom().getId() + "\n");
                fw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        sendText(update.getMessage().getFrom().getId(), update.getMessage().getText());
    }
    public void sendText(Long who, String what){
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString()) //Who are we sending a message to
                .text(what).build();    //Message content
        try {
            execute(sm);                        //Actually sending the message
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);      //Any error will be printed here
        }
    }

}
