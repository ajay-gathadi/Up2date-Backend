package com.up2date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.awt.Desktop;
import java.net.URI;
import java.net.InetAddress;

@Component
public class BrowserLauncher {
    @Value("${server.port}")
    private String serverPort;

    @EventListener(ApplicationReadyEvent.class)
    public void launchBrowserAfterStartup(){
        String hostAddress = "localhost";

        String url = "http://" + hostAddress + ":" + serverPort;
        System.out.println("Application ready. Launching browser at: " + url);

        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URI(url));
            } catch (Exception e) {
                System.err.println("Failed to launch browser automatically: " + e.getMessage());
                System.err.println("Please open your browser and navigate to: " + url);
            }
        } else {
            System.out.println("Desktop Browse not supported. Please open your browser and navigate to: " + url);
        }
    }
}
