package com.migate.views;

import javafx.animation.*;
import javafx.scene.effect.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.util.Duration;
import javafx.event.Event;
import javafx.event.EventHandler;

public class WelcomeScreen extends StackPane {
    private EventHandler<Event> onLoadFinished;

    public WelcomeScreen() {
        createBackground();
        createLogo();
        startAnimations();
    }

    private void createBackground() {
        // Animated gradient background
        Rectangle bg = new Rectangle(1200, 800);
        LinearGradient gradient = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.rgb(10, 10, 40)),
                new Stop(1, Color.rgb(5, 5, 15)));
        bg.setFill(gradient);

        // Animated particles (behind the Migate name)
        for (int i = 0; i < 50; i++) {
            Circle particle = new Circle(2 + Math.random() * 3,
                    new Color(0, 1, 1, 0.3 + Math.random() * 0.7));
            particle.setEffect(new Glow(0.8));
            particle.setTranslateX(Math.random() * 1200);
            particle.setTranslateY(Math.random() * 800);

            Timeline anim = new Timeline(
                    new KeyFrame(Duration.seconds(0),
                            new KeyValue(particle.translateXProperty(), particle.getTranslateX()),
                            new KeyValue(particle.translateYProperty(), particle.getTranslateY())
                    ),
                    new KeyFrame(Duration.seconds(2 + Math.random() * 3),
                            new KeyValue(particle.translateXProperty(), Math.random() * 1200),
                            new KeyValue(particle.translateYProperty(), Math.random() * 800)
                    )
                    );
            anim.setCycleCount(Animation.INDEFINITE);
            anim.setAutoReverse(true);
            anim.play();

            getChildren().add(particle);
        }

        getChildren().add(bg);
    }

    private void createLogo() {
        Text logo = new Text("MIGATE");
        logo.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 80));
        logo.setFill(Color.TRANSPARENT);
        logo.setStroke(Color.CYAN);
        logo.setStrokeWidth(2);

        // Glowing animation (cyan color)
        DropShadow glow = new DropShadow();
        glow.setColor(Color.AQUAMARINE);
        glow.setSpread(0.6);

        Timeline glowAnim = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(glow.radiusProperty(), 30)),
                new KeyFrame(Duration.seconds(1), new KeyValue(glow.radiusProperty(), 50)),
                new KeyFrame(Duration.seconds(2), new KeyValue(glow.radiusProperty(), 30))
        );
        glowAnim.setCycleCount(Animation.INDEFINITE);
        glowAnim.play();

        logo.setEffect(glow);
        getChildren().add(logo);
    }

    private void startAnimations() {
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1.5), this);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.play();

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(3), e -> {
                    if (onLoadFinished != null) {
                        onLoadFinished.handle(new Event(Event.ANY));
                    }
                })
        );
        timeline.play();
    }

    public void setOnLoadFinished(EventHandler<Event> handler) {
        this.onLoadFinished = handler;
    }
}