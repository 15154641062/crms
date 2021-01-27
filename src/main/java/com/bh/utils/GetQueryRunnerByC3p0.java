package com.bh.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class GetQueryRunnerByC3p0 {
    private static ComboPooledDataSource dataSource = null;
    private static QueryRunner queryRunner=null;

    /**
     * 通过配置文件获取数据库连接池
     *
     * @return
     */
    public static Connection getConnectionPool() throws SQLException {
        dataSource = new ComboPooledDataSource("mysql-config");     //读取配置文件
        Connection connection = dataSource.getConnection();      //获取一个池连接并返回
        return connection;
    }

    /**
     * 获取数据源连接(通过c3p0连接池)
     * @return
     */
    public static DataSource getDataSource() {
        dataSource = new ComboPooledDataSource("mysql-config");     //读取配置文件
        return dataSource;
    }

    /**
     * 获取QueryRunner
     * @return
     */
    public static QueryRunner GetQueryRunner(){
        queryRunner=new QueryRunner(getDataSource());
        return queryRunner;
    }

}
