import java.util.List;

// The DockerConfig objects contain what is needed to convert or build the Dockerfile
public class DockerConfig {
    private String baseImage;
    private List<String> packages;
    private List<String> commands;
    // Getters
    public String getBaseImage() {
        return baseImage;
    }
    public List<String> getPackages(){
        return packages;
    }
    public List<String> getCommands(){
        return commands;
    }
    // Setters
    public void setBaseImage(String baseImage){
        this.baseImage = baseImage;
    }
    public void setPackages(List<String> packages){
        this.packages = packages;
    }
    public void setCommands(List<String> commands){
        this.commands = commands;
    }
}
