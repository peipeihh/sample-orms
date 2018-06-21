package com.pphh.demo.xml.dao;

import com.pphh.demo.xml.mapper.EmployeeMapper;
import com.pphh.demo.po.EmployeeEntity;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * EmployeeDao
 *
 * @author huangyinhuang
 * @date 6/20/2018
 */
public class EmployeeDao {

    static Logger logger = LoggerFactory.getLogger(EmployeeDao.class);
    private SqlSessionFactory sqlSessionFactory;

    public EmployeeDao() {
        try {
            String MYBATIS_CONFIG_XML = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(MYBATIS_CONFIG_XML);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            logger.error("初始化sql session factory失败。", e);
        }
    }

    public EmployeeEntity getById(long id) {
        EmployeeEntity employee = null;

        if (sqlSessionFactory != null) {
            SqlSession session = sqlSessionFactory.openSession(true);
            try {
                EmployeeMapper imageMapper = session.getMapper(EmployeeMapper.class);
                employee = imageMapper.selectById(id);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            } finally {
                session.close();
            }
        }

        return employee;
    }

    public List<EmployeeEntity> readAll() {
        List<EmployeeEntity> employees = null;

        if (sqlSessionFactory != null) {
            SqlSession session = sqlSessionFactory.openSession(true);
            try {
                EmployeeMapper imageMapper = session.getMapper(EmployeeMapper.class);
                employees = imageMapper.selectAll();
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            } finally {
                session.close();
            }
        }

        return employees;
    }

    public boolean insert(EmployeeEntity newEmployee) {
        boolean bSuccess = false;

        if (sqlSessionFactory != null) {
            SqlSession session = sqlSessionFactory.openSession(true);
            try {
                EmployeeMapper imageMapper = session.getMapper(EmployeeMapper.class);
                bSuccess = imageMapper.insert(newEmployee) != 0;
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            } finally {
                session.close();
            }
        }

        return bSuccess;
    }

    public void update(EmployeeEntity newEmployee) {

        if (sqlSessionFactory != null) {
            SqlSession session = sqlSessionFactory.openSession(true);
            try {
                EmployeeMapper imageMapper = session.getMapper(EmployeeMapper.class);
                imageMapper.update(newEmployee);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            } finally {
                session.close();
            }
        }

    }

    public void delete(long id) {

        if (sqlSessionFactory != null) {
            SqlSession session = sqlSessionFactory.openSession(true);
            try {
                EmployeeMapper imageMapper = session.getMapper(EmployeeMapper.class);
                imageMapper.delete(id);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            } finally {
                session.close();
            }
        }

    }

    public int count() {
        int total = 0;

        if (sqlSessionFactory != null) {
            SqlSession session = sqlSessionFactory.openSession(true);
            try {
                EmployeeMapper imageMapper = session.getMapper(EmployeeMapper.class);
                total = imageMapper.count();
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            } finally {
                session.close();
            }
        }

        return total;
    }

}
