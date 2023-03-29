package net.filonova.project.notes.mappers;

import net.filonova.project.notes.model.Section;
import net.filonova.project.notes.model.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface SectionMapper {
    @Insert("INSERT INTO section (idUser, name) VALUES (#{user.id}, #{section.name})")
    @Options(useGeneratedKeys = true, keyProperty = "section.id", keyColumn = "id")
    Integer insert(@Param("section") Section section, @Param("user") User user);

    @Update("UPDATE section SET name = #{section.name} WHERE id = #{section.id} AND idUser = #{user.id}")
    void update(@Param("section") Section section, @Param("user") User user);

    @Select("SELECT * FROM section WHERE id = #{idSection}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "notes", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.NoteMapper.getNotesByIdSection", fetchType = FetchType.LAZY))
    })
    Section getById(int idSection);

    @Select("SELECT * FROM section WHERE idUser = #{idUser}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "notes", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.NoteMapper.getNotesByIdSection", fetchType = FetchType.LAZY))
    })
    List<Section> getSectionByIdUser(int idUser);

    @Select("SELECT * FROM section")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "notes", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.notes.mappers.NoteMapper.getNotesByIdSection", fetchType = FetchType.LAZY))
    })
    List<Section> getAll();

    @Delete("DELETE FROM section WHERE id = #{section.id} AND idUser = #{user.id}")
    void deleteSection(@Param("section") Section section, @Param("user") User user);

    @Delete("DELETE FROM section")
    void deleteAll();
}
