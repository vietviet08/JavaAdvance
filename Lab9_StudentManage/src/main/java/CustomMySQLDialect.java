
import org.hibernate.dialect.MySQL8Dialect;

public class CustomMySQLDialect extends MySQL8Dialect {

    @Override
    public String getTableTypeString() {
        return " ENGINE=MyISAM";
    }
}
