import java.util.ArrayList;
import java.util.List;

/**
 *  The fetcher method is for getting the info of the SQL database
 *  This Method Outputs a DockerConfig objets with the information needed
 */
class Fetcher {
    DockerConfig fetchConfig(int configId){

        // Simulated DB QUERRY that will give us the information for every APP
        //{
        //    "base_image": "ubuntu:20.04",
        //    "packages": ["python3", "golang"],
        //    "commands": ["pip install flask"]
        //}

        DockerConfig dockerConfig = new DockerConfig();

        dockerConfig.setBaseImage("ubuntu:20.04");

        List<String> packageListString = new ArrayList<>();
        packageListString.add("python3");
        packageListString.add("python3-pip");
        packageListString.add("golang");
        packageListString.add("fortune");
        dockerConfig.setPackages(packageListString);

        List<String> commandsListString = new ArrayList<>();
        commandsListString.add("pip3 install flask");
        commandsListString.add("pip3 install numpy");
        dockerConfig.setCommands(commandsListString);

        System.out.println("Fetcher Task Ended");
        return dockerConfig;
    }
}
