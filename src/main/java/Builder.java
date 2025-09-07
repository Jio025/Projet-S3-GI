import java.util.List;
import java.util.Map;

public class Builder {
    public String buildFromConfig(DockerConfig config){

        // KEEP THOSE VARIABLE / PUT THEM AS CONSTANTS
        String baseImage = config.getBaseImage();
        List<String> packages = config.getPackages();
        List<String> commands = config.getCommands();

        System.out.println("baseImage: " + baseImage);

        for(int i=0; i < packages.size(); i++) {
            System.out.println("Package #" + i + " " + packages.get(i));
        }
        for(int i=0; i< commands.size(); i++){
            System.out.println("Command #" + i + " " + commands.get(i));
        }
        // The above was for testing purposes and could be removed
        System.out.println("****************************DOCKERFILE****************************");

        // Mapping the distros with their package installers (will be replaced by a JSON or YAML)
        Map<String, String> packageManagers = Map.of(
            "Ubuntu", "apt-get update && apt-get install -y --no-install-recommends \\\n",
            "Debian", "apt-get update && apt-get install -y \\\n--no-install-recommends \\\n",
            "Alpine", "apk add --no-cache"
        );

        // We now try to write the docker file string
        StringBuilder dockerfile = new StringBuilder();

        // Initial comment line
        dockerfile.append("# ").append("This DOCKERFILE was generated automatically by an" +
                " automatic DOCKERFILE generator").append("\n");
        // FROM LINE FOR BASE IMAGE OF DOCKERFILE
        dockerfile.append("FROM ").append(config.getBaseImage()).append("\n");

        // We install the needed packages
        String oskey = baseImage.contains("Alpine") ? "Alpine" : "Ubuntu";
        String installer = packageManagers.get(oskey);
        dockerfile.append("RUN ").append(installer);
        for(int i =0; i< packages.size(); i++){
            dockerfile.append(packages.get(i)).append(" ");
        }
        dockerfile.append("\\\n").append("&& rm -rf /var/lib/apt/lists/*");

        // We make the needed commands
        dockerfile.append("\n");
        for(int i=0; i< commands.size(); i++){
            dockerfile.append("RUN ").append(commands.get(i)).append("\n");
        }


        return(dockerfile.toString());
    }
}
