package com.catcher.miniserver.server;

import com.catcher.miniserver.http.HttpParser;
import com.catcher.miniserver.http.HttpRequest;
import com.catcher.miniserver.http.HttpResponse;
import com.catcher.miniserver.http.ResponseWriter;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

final class ServerEngine {
    private final int port;
    private final RequestDispatcher requestDispatcher;
    private final ResponseWriter responseWriter = new ResponseWriter();

    ServerEngine(int port, RequestDispatcher requestDispatcher) {
        this.port = port;
        this.requestDispatcher = requestDispatcher;
    }

    void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server listening on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                handleClient(clientSocket);
            }

        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }

    private void handleClient(Socket clientSocket) {
        try (clientSocket) {
            System.out.println(
                    "Client connected: " + clientSocket.getRemoteSocketAddress()
            );

            HttpParser parser =
                    new HttpParser(clientSocket.getInputStream());

            HttpRequest parsedRequest = parser.parse();

            HttpResponse response = requestDispatcher.handleRequest(parsedRequest);

            byte[] byteResponse = responseWriter.write(response);
            OutputStream outputStream = clientSocket.getOutputStream();
            outputStream.write(byteResponse);
            outputStream.flush();

        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
