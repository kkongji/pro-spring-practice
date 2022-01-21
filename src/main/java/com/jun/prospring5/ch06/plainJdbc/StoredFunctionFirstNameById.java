package com.jun.prospring5.ch06.plainJdbc;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlFunction;

import javax.sql.DataSource;
import java.sql.Types;

public class StoredFunctionFirstNameById extends SqlFunction<String> {
    private static final String SQL = "select getfirstnamebyid(?)";

    public StoredFunctionFirstNameById(DataSource dataSource) {
        super(dataSource, SQL);
        super.declareParameter(new SqlParameter(Types.INTEGER));
        compile();
    }
}
