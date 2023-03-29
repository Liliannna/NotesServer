package net.filonova.project.notes.daoImpl;

import net.filonova.project.notes.dao.CommonDao;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CommonDaoImpl extends DaoImplBase implements CommonDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonDaoImpl.class);

    @Override
    public void clear() {
        LOGGER.debug("Clear DataBase");
        try(SqlSession sqlSession = getSession()) {
            try{
                getCommentMapper(sqlSession).deleteAll();
                getNoteMapper(sqlSession).deleteAll();
                getSectionMapper(sqlSession).deleteAll();
                getUserMapper(sqlSession).deleteAll();
                getUserMapper(sqlSession).deleteAllSession();
            } catch (RuntimeException ex) {
                LOGGER.debug("Can`t clear DataBase");
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }
}
