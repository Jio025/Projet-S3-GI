public class Main {
    public static void main(String[] args) {
        Fetcher fetcher = new Fetcher();
        Builder builder = new Builder();
        DockerFileService service = new DockerFileService(fetcher, builder);
        service.generateDockerFile(1);
        System.out.println("Program Terminated");
    }
}