/*
* DockerFileService Class is for orchestrating the building of a Dockerfile
* from a database info fetched from a database of the requirements of every
* APP that will use the service
*/

public class DockerFileService {
    private Fetcher fetcher;
    private Builder builder;

    void generateDockerFile(int configId){
        DockerConfig config = fetcher.fetchConfig(configId);
        String dockerfile = builder.buildFromConfig(config);
        // We then write to the Dockerfile.
        System.out.println(dockerfile);
    }
}
