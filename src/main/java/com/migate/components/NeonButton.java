package com.migate.components;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.effect.Glow;
import javafx.util.Duration;

// 1st file without errors
// I thought 😥

public class NeonButton extends Button {
    private final Timeline glowAnimation;

    public NeonButton(String text) {
        super(text);
        getStyleClass().add("neon-button");

        // Glow animation
        Glow glow = new Glow();
        glow.setLevel(0.5);
        setEffect(glow);

        glowAnimation = new Timeline(
                new KeyFrame(Duration.ZERO, e -> glow.setLevel(0.5)),
                new KeyFrame(Duration.seconds(0.5), e -> glow.setLevel(0.8)),
                new KeyFrame(Duration.seconds(1), e -> glow.setLevel(0.5))
        );
        glowAnimation.setCycleCount(Animation.INDEFINITE);

        // Start animation on hover
        setOnMouseEntered(e -> glowAnimation.play());
        setOnMouseExited(e -> {
            glowAnimation.stop();
            glow.setLevel(0.5);
        });
    }
}