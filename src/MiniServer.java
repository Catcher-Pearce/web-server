import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MiniServer {
    private int port;

    public MiniServer(int port) {
        this.port = port;
    }

    public void start() {
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

            // parse request
            // build response
            // send response

        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}