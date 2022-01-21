package com.jun.prospring5.ch06.plainJdbc;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

public class UpdateSinger extends SqlUpdate {
    private static final String SQL_UPDATE_SINGER =
            "update SINGER set" +
                    " first_name=:first_name," +
                    " last_name=:last_name," +
                    " birth_date=:birth_date" +
                    " where id=:id";

    public UpdateSinger(DataSource ds) {
        super(ds, SQL_UPDATE_SINGER);
        super.declareParameter(new SqlParameter("first_name", Types.VARCHAR));
        super.declareParameter(new SqlParameter("last_name", Types.VARCHAR));
        super.declareParameter(new SqlParameter("birth_date", Types.DATE));
        super.declareParameter(new SqlParameter("id", Types.INTEGER));
    }
}
