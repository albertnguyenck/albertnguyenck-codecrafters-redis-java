void main(String[] args) {
    try (var serverSocket = new ServerSocket(6379)) {
        System.out.println("Server started on port 6379");
        serverSocket.setReuseAddress(true);
        Socket client = serverSocket.accept();
        handleClient(client);
    } catch (IOException e) {
        System.out.println("IOException: " + e.getMessage());
    }
}

void handleClient(Socket client) {
    try (var in = client.getInputStream(); var out = client.getOutputStream()) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
        while (true) {
            String line = reader.readLine();
            if (line == null) {
                break;
            }

            if (line.equals("*1")) {
                reader.readLine();
                String command = reader.readLine();
                switch (command) {
                    case "PING" -> out.write("+PONG\r\n".getBytes(StandardCharsets.UTF_8));
                    default -> System.out.println("Unknown command: " + command);
                }
                out.flush();
            }
        }
    } catch (IOException e) {
        System.out.println("IOException while handling client: " + e.getMessage());
    }
}
