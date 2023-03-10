package utcn.dobner.szabolcs.Data_Access;

import utcn.dobner.szabolcs.Connection.ConnectionFactory;
import utcn.dobner.szabolcs.Model.Client;
import utcn.dobner.szabolcs.Model.Order;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @param <T> -generic object
 */
public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;
    @SuppressWarnings("unchecked")
    public AbstractDAO(Class<T> type) {
        this.type = type;

    }

    /**
     * @param field- field's name
     * @param id- id of the column
     * @return returns query
     */
    private String createSelectQuery(String field,int id) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE ").append(field).append("=").append(id);
        return sb.toString();
    }

    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Field[] field=type.getDeclaredFields();
        String query = createSelectQuery(field[0].getName(),id);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * @param resultSet- result of the query
     * @return returns the list of objects
     */
    private ArrayList<T> createObjects(ResultSet resultSet) {
        ArrayList<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;

        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    field.setAccessible(true);
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * @param type type of class
     * @return correct name of table
     */
    private  String getTable(String type)
    {
        switch (type) {
            case "Client":
                return "client";
            case "Order":
                return "order";
            case "Product":
                return "product";
            default:
                System.out.println("Not good!" + type);
                break;
        }
        return "";
    }

    /**
     * @param t-generic object
     */
    public void insert(T t) {
        Connection dbConnection;
        PreparedStatement statement = null;
        try
        {
            dbConnection=ConnectionFactory.getConnection();
            StringBuilder queryBuild=new StringBuilder();
            queryBuild.append("INSERT INTO `").append(getTable(type.getSimpleName())).append("` ").append("(");
            for (Field field : type.getDeclaredFields()) {
                String fieldName = field.getName();
                queryBuild.append(fieldName);
                queryBuild.append(", ");
            }
            queryBuild.replace(queryBuild.length()-2,queryBuild.length(),"");
            queryBuild.append(") VALUES (");
            for (Field field : type.getDeclaredFields()) {
                field.setAccessible(true);
                Object value=field.get(t);
                if (field.getType().getSimpleName().equals("String"))
                    queryBuild.append("'").append(value).append("'");
                else queryBuild.append(value);
                queryBuild.append(", ");
            }
            queryBuild.replace(queryBuild.length()-2,queryBuild.length(),");");
            System.out.println(queryBuild);

            try {
                dbConnection = ConnectionFactory.getConnection();
                statement = dbConnection.prepareStatement(queryBuild.toString());
                statement.executeUpdate();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, type.getName() + "DAO:Insert " + e.getMessage());
            } finally {
                ConnectionFactory.close(statement);
                ConnectionFactory.close(dbConnection);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * @param t-generic object
     */
    public void update(T t) {

        Connection dbConnection;
        PreparedStatement statement = null;
        try
        {
            dbConnection=ConnectionFactory.getConnection();
            StringBuilder queryBuild=new StringBuilder();
            queryBuild.append("UPDATE `").append(type.getSimpleName()).append("` SET ");
            for (Field field : type.getDeclaredFields()) {
                field.setAccessible(true);
                String fieldName = field.getName();
                queryBuild.append(fieldName);
                queryBuild.append("=");
                Object value=field.get(t);
                if (field.getType().getSimpleName().equals("String"))
                    queryBuild.append("'").append(value).append("'");
                else queryBuild.append(value);
                queryBuild.append(", ");
            }
            queryBuild.replace(queryBuild.length()-2,queryBuild.length()," ");
            Field[] allFields = t.getClass().getDeclaredFields();
            allFields[0].setAccessible(true);
            Object val=allFields[0].get(t);

            queryBuild.append("WHERE ").append(allFields[0].getName()).append("=").append(val);
            try {
                dbConnection = ConnectionFactory.getConnection();
                statement = dbConnection.prepareStatement(queryBuild.toString());
                statement.executeUpdate();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, type.getName() + "DAO:Update " + e.getMessage());
            } finally {
                ConnectionFactory.close(statement);
                ConnectionFactory.close(dbConnection);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }

    /**
     * @param list list of entries
     * @return returns the JTable made
     */
    public JTable createTable(ArrayList<T> list)
    {
        int size=type.getDeclaredFields().length;
        String[] columns=new String[size];
        int i=0;
        for (Field field : type.getDeclaredFields())
        {
            field.setAccessible(true);
            columns[i++]=field.getName();
        }
        DefaultTableModel myModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;// all cells false
            }
        };
        myModel.setColumnIdentifiers(columns);
        for (Object o : list) {
            Object[] obj = new Object[size];
            int col = 0;
            for (java.lang.reflect.Field currentField : o.getClass().getDeclaredFields()) {
                currentField.setAccessible(true);
                try {
                    obj[col] = currentField.get(o);
                    col++;
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            myModel.addRow(obj);
        }
        JTable myTable = new JTable(myModel);
        return myTable;
    }

    /**
     * @return list of the query result
     */
    public ArrayList<T> findAll() {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM `");
        sb.append(type.getSimpleName());
        sb.append("`");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sb.toString());
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * @param id id of the entry which needs to be deleted
     */
    public void delete(int id)
    {
        Connection dbConnection;
        PreparedStatement statement = null;
        dbConnection=ConnectionFactory.getConnection();
        StringBuilder queryBuild=new StringBuilder();
        Field[] field=type.getDeclaredFields();
        queryBuild.append("DELETE FROM `").append(getTable(type.getSimpleName())).append("` WHERE ").append(field[0].getName()).append("=").append(id);
        try {
            dbConnection = ConnectionFactory.getConnection();
            statement = dbConnection.prepareStatement(queryBuild.toString());
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(dbConnection);
        }
        return;
    }




}
