package com.migate.util;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.web.WebView;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

//ngl gemni helped on this part

public class ScreenshotUtil {
    public static void captureWebPage(WebView webView, File file) {
        WritableImage image = webView.snapshot(new SnapshotParameters(), null);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void captureVisibleArea(WebView webView, File file) {
        WritableImage image = webView.snapshot(null, null);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}