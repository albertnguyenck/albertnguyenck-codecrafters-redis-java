void main(String[] args) {
    try (var serverSocket = new ServerSocket(6379)) {
        System.out.println("Server started on port 6379");
        serverSocket.setReuseAddress(true);
        serverSocket.accept();
    } catch (IOException e) {
        System.out.println("IOException: " + e.getMessage());
    }
}
