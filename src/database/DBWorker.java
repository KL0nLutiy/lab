package database;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.sql.Date;
import java.util.*;

import database.entities.TTParams;
import oracle.jdbc.proxy.annotation.Pre;
import org.apache.log4j.Logger;
import sun.rmi.runtime.Log;

/**
 * Created by Vlad on 24.06.2016.
 */
public class DBWorker {

    private static final Logger log = Logger.getLogger(DBWorker.class);

    private Connection connection = null;

    public DBWorker() {
        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");

        } catch (ClassNotFoundException e) {
            log.error("DataBase driver problem: "+e.getMessage());
            e.printStackTrace();
            return;
        }

        log.info("User connected!");

        try {

            connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "system",
                    "11111111");

        } catch (SQLException e) {
            log.error("Cannot connect to database"+e.getMessage());
            e.printStackTrace();
        }
    }

    public long getId() {
        String sequenceId = "SELECT id_seq.nextval FROM DUAL";
        long id = 0L;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sequenceId);
            if(rs.next()) {
                id = rs.getLong(1);
            }
            log.info("Generated id: "+id);
        } catch (SQLException e) {
            log.error("Can't generate id: "+e.getMessage());
            e.printStackTrace();
        }
        return id;
    }

    public long getAttrAccessType(long attrId) {
        String attrAccessTypeSelect = "SELECT ATTR_ACCESS_TYPE FROM TT_ATTRIBUTES WHERE ATTR_ID = ?";
        long attrAccessType = 0L;
        try {
            PreparedStatement pstmt = connection.prepareStatement(attrAccessTypeSelect);
            pstmt.setLong(1,attrId);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                attrAccessType = rs.getLong(1);
            }
            log.info("Attr access type: "+attrAccessType+" for attrId: "+attrId);
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Can't get attr access type: "+e.getMessage());
        }

        return attrAccessType;
    }

    public Long getAttrIdForValue(String value){
        String attrIdSelect = "SELECT ATTR_ID FROM TT_PARAMS WHERE VALUE = ?";
        Long attrId=0L;
        try {
            PreparedStatement pstmt = connection.prepareStatement(attrIdSelect);
            pstmt.setString(1,value);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                attrId=rs.getLong(1);
            }
            log.info("Attr id: "+attrId+" for value: "+attrId);
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Can't get attr id: "+e.getMessage());
        }

        return attrId;
    }

    public String getPasswordForUsername(String username){
        String passwordSelect = "SELECT VALUE FROM TT_PARAMS WHERE OBJECT_ID = (SELECT OBJECT_ID FROM TT_PARAMS WHERE VALUE = ?) AND ATTR_ID = 3";
        String password = "";
        try {
            PreparedStatement pstmt = connection.prepareStatement(passwordSelect);
            pstmt.setString(1,username);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                password = rs.getString(1);
            }
            log.info("Password for user: "+username);
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Can't get password for user: "+username);
        }
        return password;
    }

    public boolean isAdmin(String username) {
        String adminSelect = "SELECT OBJECT_TYPE_ID FROM TT_OBJECTS INNER JOIN TT_PARAMS ON TT_OBJECTS.OBJECT_ID = TT_PARAMS.OBJECT_ID WHERE VALUE = ?";
        boolean isAdmin = false;
        try {
            PreparedStatement pstmt = connection.prepareStatement(adminSelect);
            pstmt.setString(1,username);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next() && rs.getLong(1)==1L){
                isAdmin = true;
            }
            log.info("User: "+username+" isAdmin: "+isAdmin);
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Can't get isAdmin for user: "+username);
        }
        return isAdmin;
    }

    public Map<Long,Map<String, String>> getGoods(long objectTypeId){

        String goodsSelect;

        if(objectTypeId==0) {
            goodsSelect =  "SELECT o.OBJECT_ID, a.NAME, CASE WHEN p.VALUE IS NULL THEN TO_CHAR(p.DATE_VALUE) ELSE p.VALUE END, o.NAME " +
                    "FROM TT_OBJECTS o, TT_ATTRIBUTES a, TT_PARAMS p " +
                    "WHERE p.OBJECT_ID = o.OBJECT_ID AND a.ATTR_ID = ANY " +
                    "(SELECT ATTR_ID FROM TT_ATTR_OBJECT_TYPES WHERE OBJECT_TYPE_ID = o.OBJECT_TYPE_ID AND OBJECT_TYPE_ID <> 1 AND OBJECT_TYPE_ID <> 2 AND OBJECT_TYPE_ID <> 9) " +
                    "AND p.ATTR_ID = a.ATTR_ID ORDER BY o.OBJECT_ID";
        } else {
            goodsSelect = "SELECT o.OBJECT_ID, a.NAME, CASE WHEN p.VALUE IS NULL THEN TO_CHAR(p.DATE_VALUE) ELSE p.VALUE END, o.NAME FROM TT_OBJECTS o, TT_ATTRIBUTES a, TT_PARAMS p WHERE o.OBJECT_TYPE_ID = ? " +
                    "AND p.OBJECT_ID = o.OBJECT_ID AND a.ATTR_ID = ANY (SELECT ATTR_ID FROM TT_ATTR_OBJECT_TYPES WHERE OBJECT_TYPE_ID = ?) AND p.ATTR_ID = a.ATTR_ID ORDER BY o.OBJECT_ID";
        }

        Map<Long,Map<String, String>> map = new TreeMap<>();
        try {
            ResultSet rs;
            if(objectTypeId==0) {
                Statement stmt = connection.createStatement();
                rs = stmt.executeQuery(goodsSelect);
            } else {
                PreparedStatement pstmt = connection.prepareStatement(goodsSelect);
                pstmt.setLong(1, objectTypeId);
                pstmt.setLong(2, objectTypeId);
                rs = pstmt.executeQuery();
            }
            HashMap<String, String> insideMap = null;
            long objectId=0L;
            while (rs.next()) {
                objectId = rs.getLong(1);
                if(map.get(objectId)==null) {
                    insideMap = new HashMap<>();
                    insideMap.put("name",rs.getString(4));
                    map.put(objectId,insideMap);
                }
                insideMap.put(rs.getString(2),rs.getString(3));
            }
            map.put(objectId,insideMap);
            log.info("Getting goods success");
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Getting goods failed: "+e.getMessage());
        }

        return map;
    }

    public String getAdminById(long objectId){
        String adminSelect = "SELECT NAME FROM TT_OBJECTS WHERE OBJECT_ID = ?";
        String admin = "";
        try {
            PreparedStatement pstmt = connection.prepareStatement(adminSelect);
            pstmt.setLong(1,objectId);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                admin=rs.getString(1);
            }
            log.info("Admin name: "+admin+" for object id: "+objectId);
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Can't get admin name for object id: "+objectId+" "+e.getMessage());
        }

        return admin;
    }

    public String getUsersName(String username){
        String nameSelect = "SELECT VALUE FROM TT_PARAMS WHERE OBJECT_ID = (SELECT OBJECT_ID FROM TT_PARAMS WHERE VALUE = ?) AND ATTR_ID = ANY(4,5)";
        String firstLastName = "";
        try {
            PreparedStatement pstmt = connection.prepareStatement(nameSelect);
            pstmt.setString(1,username);
            ResultSet rs = pstmt.executeQuery();
            for(int i = 0; rs.next(); i++){
                firstLastName+=rs.getString(1)+" ";
            }
            log.info("User fist, last name: "+firstLastName+" for user: "+username);
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Can't get user first, last name for user: "+username);
        }

        return firstLastName;
    }

    public Long getObjectIdForValue(String value) {
        String objectIdSelect = "SELECT OBJECT_ID FROM TT_PARAMS WHERE VALUE = ?";
        Long objectId = 0L;
        try {
            PreparedStatement pstmt = connection.prepareStatement(objectIdSelect);
            pstmt.setString(1,value);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                objectId = rs.getLong(1);
            }
            log.info("Get object id: "+objectId+" for value: "+value);
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Can't object id: "+objectId+" for value: "+value+" "+e.getMessage());
        }

        return objectId;
    }

    public List<OrderInfo> getOrderInfoForUser(String username){
        String orderInfoSelect = "SELECT i.VALUE, ii.NAME, iii.VALUE, o.VALUE, TO_CHAR(oo.DATE_VALUE)\n" +
                "\tFROM TT_OBJECTS ob\n" +
                "\tJOIN TT_REFERENCES ref ON ob.OBJECT_ID = ref.OBJECT_ID\n" +
                "  JOIN TT_REFERENCES ref2 ON ref.REFERENCE = ref2.REFERENCE\n" +
                "\tJOIN TT_PARAMS i ON ref2.OBJECT_ID = i.OBJECT_ID\n" +
                "\tJOIN TT_OBJECTS ii ON ref2.OBJECT_ID = ii.OBJECT_ID\n" +
                "\tJOIN TT_PARAMS iii ON ref2.OBJECT_ID = iii.OBJECT_ID\n" +
                "\tJOIN TT_PARAMS o ON ref.REFERENCE = o.OBJECT_ID\n" +
                "\tJOIN TT_PARAMS oo ON ref.REFERENCE = oo.OBJECT_ID\n" +
                "\tJOIN TT_ATTRIBUTES attr1 ON i.ATTR_ID = attr1.ATTR_ID\n" +
                "\tJOIN TT_ATTRIBUTES attr2 ON iii.ATTR_ID = attr2.ATTR_ID\n" +
                "\tJOIN TT_ATTRIBUTES attr3 ON o.ATTR_ID = attr3.ATTR_ID\n" +
                "\tJOIN TT_ATTRIBUTES attr4 ON oo.ATTR_ID = attr4.ATTR_ID\n" +
                "\tWHERE ob.OBJECT_ID = (SELECT OBJECT_ID FROM TT_OBJECTS WHERE NAME = ?) AND\n" +
                "\t(attr1.NAME = 'phone_id' OR attr1.NAME = 'accsesories_id' OR attr1.NAME = 'component_id') AND attr2.NAME = 'price' AND attr3.NAME = 'order_id' AND attr4.NAME = 'create_date'";
        List<OrderInfo> orderList = new ArrayList<>();

        try {
            PreparedStatement pstmt = connection.prepareStatement(orderInfoSelect);
            pstmt.setString(1,username);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                orderList.add(new OrderInfo(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
            }
            log.info("Order list for user: "+username+" success");
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Can't get order list for user: "+username+" "+e.getMessage());
        }

        return orderList;
    }

    public Long getVersionForObject(Long objectId){
        String versionSelect = "SELECT VERSION FROM TT_OBJECTS WHERE OBJECT_ID = ?";
        Long version = 0L;
        try {
            PreparedStatement pstmt = connection.prepareStatement(versionSelect);
            pstmt.setLong(1,objectId);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                version=rs.getLong(1);
            }
            log.info("Version: "+version+" for object "+objectId);
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Can't get version for object id:"+objectId);
        }

        return version;
    }

    public Long getObjectTypeIdForObject(Long objectId){
        String objectTypeIdSelect = "SELECT OBJECT_TYPE_ID FROM TT_OBJECTS WHERE OBJECT_ID = ?";
        Long objectTypeId = 0L;
        try {
            PreparedStatement pstmt = connection.prepareStatement(objectTypeIdSelect );
            pstmt.setLong(1,objectId);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                objectTypeId=rs.getLong(1);
            }
            log.info("Object type id: "+objectTypeId+" for object: "+objectId);
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Can't get object type id for object: "+objectId);
        }

        return objectTypeId;
    }

    public Long getAttrIdForName(String name){
        String attrIdSelect = "SELECT ATTR_ID FROM TT_ATTRIBUTES WHERE NAME = ?";
        Long attrId=0L;
        try {
            PreparedStatement pstmt = connection.prepareStatement(attrIdSelect);
            pstmt.setString(1,name);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                attrId=rs.getLong(1);
            }
            log.info("Attr id: "+attrId+" for name: "+name);
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Can't get attr id for name: "+name+" "+e.getMessage());
        }

        return attrId;
    }

    public List<String> getAttrsForGoods(Long goodsType){
        String selectAttr = "SELECT a.NAME FROM TT_ATTRIBUTES a JOIN TT_ATTR_OBJECT_TYPES aot ON a.ATTR_ID = aot.ATTR_ID WHERE aot.OBJECT_TYPE_ID = ?";
        List<String> list = new ArrayList<>();
        try {
            PreparedStatement pstmt = connection.prepareStatement(selectAttr);
            pstmt.setLong(1,goodsType);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
            }
            log.info("Attributes for goodsType: "+goodsType+" success");
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Can't get attributes for goodsType"+goodsType+" "+e.getMessage());
        }

        return list;
    }

    public void close(){
        try {
            connection.close();
            log.info("Connection closed success");
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Connection closed failed "+e.getMessage());
        }
    }
}
