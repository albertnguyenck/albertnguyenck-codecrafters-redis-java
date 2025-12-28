void main(String[] args) {
    try (var serverSocket = new ServerSocket(6379)) {
        System.out.println("Server started on port 6379");
        serverSocket.setReuseAddress(true);
        Socket client = serverSocket.accept();
        try (var out = client.getOutputStream()) {
            out.write("+PONG\r\n".getBytes(StandardCharsets.UTF_8));
            out.flush();
        }
    } catch (IOException e) {
        System.out.println("IOException: " + e.getMessage());
    }
}
