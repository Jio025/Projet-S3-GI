package ca.usherbrooke.fgen.api.mapper;


import ca.usherbrooke.fgen.api.business.Message;
import jakarta.ws.rs.PathParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import jakarta.ws.rs.core.Response;

@Mapper
public interface MessageMapper {

    List<Message> select(@Param("trimesterId") String trimesterId,
                         @Param("profileId") String profileId,
                         @Param("unit")String unit,
                         @Param("id") Integer id);
    Message selectOne(@Param("id") Integer id);
    void deleteOne(@Param("id") Integer id);
    List<Message> allMessages();
    void insertMessage(@Param("message") Message message);
    Integer getNewId();
    //TODO: changer
    Integer choose(@Param("id") int id);
    //TODO: valider
    int ajouterDockerfile(
            @Param("id") int id,
            @Param("tagId") int tagId,
            @Param("hash") String hash,
            @Param("path") String path,
            @Param("description") String description
    );

    String getDockerfileURL(
            @Param("dockerfileID") int id
    );
}
