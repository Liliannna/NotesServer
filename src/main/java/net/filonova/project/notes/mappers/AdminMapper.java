package net.filonova.project.notes.mappers;

import net.filonova.project.notes.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AdminMapper {
    @Update("UPDATE user SET role = 'SUPER' WHERE id = #{idUser}")
    void addRole(int idUser);

    @Select("SELECT * FROM user WHERE id IN (SELECT idUser FROM session WHERE id = #{idSessionForAdmin})")
    User getAdminById(int idSessionForAdmin);
}
