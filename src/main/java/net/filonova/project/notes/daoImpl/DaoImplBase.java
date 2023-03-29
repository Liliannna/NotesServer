package net.filonova.project.notes.daoImpl;

import net.filonova.project.notes.mappers.*;
import net.filonova.project.notes.utils.PersistenceConfig;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DaoImplBase {

    @Autowired
    private PersistenceConfig config;

    protected SqlSession getSession() {
        try {
            return config.sqlSessionFactory().openSession();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    protected CommentMapper getCommentMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(CommentMapper.class);
    }

    protected NoteMapper getNoteMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(NoteMapper.class);
    }

    protected SectionMapper getSectionMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(SectionMapper.class);
    }

    protected UserMapper getUserMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(UserMapper.class);
    }

    protected AdminMapper getAdminMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(AdminMapper.class);
    }

}
