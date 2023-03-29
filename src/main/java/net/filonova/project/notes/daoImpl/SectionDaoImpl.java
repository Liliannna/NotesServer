package net.filonova.project.notes.daoImpl;

import net.filonova.project.notes.dao.SectionDao;
import net.filonova.project.notes.model.Section;
import net.filonova.project.notes.model.User;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class SectionDaoImpl extends DaoImplBase implements SectionDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(SectionDaoImpl.class);

    @Override
    public Section insert(User user, Section section) {
        LOGGER.debug("DAO insert Section {}", section);
        try (SqlSession sqlSession = getSession()) {
            try {
                getSectionMapper(sqlSession).insert(section, user);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can`t insert Section {}, {}", section, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return section;
    }

    @Override
    public Section update(Section section, User user) {
        LOGGER.debug("DAO update Section {}", section);
        try (SqlSession sqlSession = getSession()) {
            try {
                getSectionMapper(sqlSession).update(section, user);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can`t update Section {}, {}", section, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return section;
    }

    @Override
    public Section getById(int idSection) {
        LOGGER.debug("DAO get Section by id {}", idSection);
        try (SqlSession sqlSession = getSession()) {
            try {
                return getSectionMapper(sqlSession).getById(idSection);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can`t get Section by id {}, {}", idSection, ex);
                throw ex;
            }
        }
    }

    @Override
    public void deleteSection(Section section, User user) {
        LOGGER.debug("DAO delete Section by id {}", section.getId());
        try (SqlSession sqlSession = getSession()) {
            try {
                getSectionMapper(sqlSession).deleteSection(section, user);
            } catch (RuntimeException ex) {
                LOGGER.debug("Can`t delete Section by id {}, {}", section.getId(), ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public List<Section> getAll() {
        LOGGER.debug("DAO get all Sections");
        try (SqlSession sqlSession = getSession()) {
            try {
                return getSectionMapper(sqlSession).getAll();
            } catch (RuntimeException ex) {
                LOGGER.debug("Can`t get all Sections, {}", ex);
                throw ex;
            }
        }
    }

    @Override
    public void deleteAll() {
        LOGGER.debug("DAO delete all Sections");
        try (SqlSession sqlSession = getSession()) {
            try {
                getSectionMapper(sqlSession).deleteAll();
            } catch (RuntimeException ex) {
                LOGGER.debug("Can`t delete all Sections, {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }
}
