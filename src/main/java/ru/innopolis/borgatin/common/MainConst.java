package ru.innopolis.borgatin.common;

/**
 * Класс предназначен для хранения всех констант приложения
 */
public class MainConst {

    //Общие числовые константы
    public static final int CONST_ZERO = 0;
    public static final int CONST_ONE = 1;
    public static final int CONST_TWO = 2;
    public static final int CONST_THREE = 3;
    public static final int CONST_FOUR = 4;
    public static final int CONST_FIVE = 5;


    //Общие строковые константы
    public static final String CONST_TRUE = "true";
    public static final String URL_PATTERN_ALL = "/*";
    public static final String URL_PATTERN_ROOT = "/";



    //Константы полей SQL
    public static final String SQL_FIELD_ID = "id";
    public static final String SQL_FIELD_TOPIC = "topic";
    public static final String SQL_FIELD_DESCRIPTION = "description";
    public static final String SQL_FIELD_DURATION = "duration";
    public static final String SQL_FIELD_LESSON_DATE = "lesson_date";
    public static final String SQL_FIELD_FIRSTNAME = "firstname";
    public static final String SQL_FIELD_LASTNAME = "lastname";
    public static final String SQL_FIELD_GENDER = "gender";
    public static final String SQL_FIELD_BIRTHDATE = "birthdate";
    public static final String SQL_FIELD_USERNAME = "username";
    public static final String SQL_FIELD_PASSWORD = "pass";
    public static final String SQL_FIELD_USER_ROLE = "user_role";
    public static final String SQL_FIELD_STUDENT_ID = "student_id";


    //Константы из настроек
    public static final String DRIVER_NAME_SQL= "org.postgresql.Driver";
    public static final String SQL_URL = "jdbc:postgresql://localhost:5434/testDB";
    public static final String SQL_DB_USER = "postgres";
    public static final String SQL_DB_PASSWORD = "1Qwerty";
    public static final String SPRING_SECURITY_FILTER_NAME = "springSecurityFilterChain";
    public static final String SPRING_ENCODING_FILTER_NAME = "encodingFilter";
    public static final String ENCODING_PARAM_NAME = "encoding";
    public static final String ENCODING_UTF8_PARAM_VALUE = "UTF-8";
    public static final String FORCE_ENCODING_PARAM_NAME = "forceEncoding";
    public static final String DISPATCHER_SERVLET_NAME = "dispatcher";

//    public static final String ;
//    public static final String ;

}
