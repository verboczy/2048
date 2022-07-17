package hu.verboczy.config;

import game.Game;
import game.GameField;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringAppConfig {

    @Bean
    public Game game() {
        return new Game(new GameField(4));
    }
}
