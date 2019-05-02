package Client.Model;

import com.sun.xml.internal.bind.v2.model.core.ID;

import java.sql.Statement;
import java.util.Random;

public class CreatID {
    private String ID = "";
    private Random create;
    private Statement statement;

    public CreatID(Statement statement) {
        this.statement = statement;
    }
        //验证账号的可用性
        public boolean check(String sql) {
            boolean check_id = false;
            try {
                statement.executeQuery(sql);
            } catch (Exception ex) {
                check_id = true;
            }
            return check_id;
        }

        //验证新ID的可用性
        public String checkID() {
            while (check("select icwy * from User where ID = '" + getID() + "'"))
                break;
            return ID;
        }

        public String getID() {
            ID = "666";
            create = new Random();
            for (int i = 0; i < 3; i++)
                ID = ID + create.nextInt(5);
            return ID;
        }

}
