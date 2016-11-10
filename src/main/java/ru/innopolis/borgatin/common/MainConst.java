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
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ANONYMOUS = "ROLE_ANONYMOUS";
    public static final String VIEW_VARIABLE_LIST = "list";
    public static final String VIEW_VARIABLE_SORT_TYPE = "sortType";
    public static final String VIEW_VARIABLE_FILTER = "filter";
    public static final String VIEW_VARIABLE_BIRTHDAY = "birthday";
    public static final String VIEW_VARIABLE_MSG_ERROR = "msgError";
    public static final String VIEW_MSG_ALL_STUDENTS_RECORDED = "Все студенты записаны на занятие";
    public static final String VIEW_VARIABLE_STUDENT_ID = "studentId";
    public static final String CONST_DATE_FORMAT = "dd.MM.yyyy";
    public static final String CONST_DATE_SQL_FORMAT = "yyyy-MM-dd";

    public static final String CONST_DATE_FIELD = "date";
    public static final String CONST_STUDENT = "student";
    public static final String CONST_LESSON = "lesson";

    public static final String CONST_ID = "id";





    //URL константы
    public static final String CONST_URL_LESSONS = "/lessons";
    public static final String CONST_URL_STUDENTS = "/students";
    public static final String CONST_URL_ALL = "/all";
    public static final String CONST_URL_VIEW_BY_ID = "/view/{id}";
    public static final String CONST_URL_EDIT_BY_ID = "/edit/{id}";
    public static final String CONST_URL_DEL_BY_ID = "/del/{id}";
    public static final String CONST_URL_SORT_BY_SORT_TYPE = "/sort/{sortType}";
    public static final String CONST_URL_ADD_STUDENT = "/add-student";
    public static final String CONST_URL_ADD_LESSON = "/add-lesson";
    public static final String CONST_URL_EDIT_STUDENTS_BY_LESSON_ID = "/edit-students/{id}";
    public static final String CONST_URL_EDIT_ADD_STUDENTS_BY_LESSONS = "/{id}/add-students";
    public static final String CONST_URL_EDIT_ADD_STUDENT_BY_LESSONS = "/{id}/add-student";
    public static final String CONST_URL_EDIT_DEL_STUDENT_BY_LESSONS = "/{id}/del/{studentId}";
    public static final String CONST_URL_LOGIN = "/login";


    public static final String CONST_URL_REDIRECT_ALL = "redirect:../all";



    public static final String CONST_URL_ADD = "/add";
    public static final String CONST_URL_UPDATE = "/update";
    public static final String CONST_URL_FILTER = "/filter";


    //Константы для view-jsp
    public static final String CONST_VIEW_LOGIN = "login";
    public static final String CONST_VIEW_WELCOME = "welcome";
    public static final String CONST_VIEW_ALL_STUDENTS = "allStudents";
    public static final String CONST_VIEW_ALL_LESSONS = "allLessons";
    public static final String CONST_VIEW_STUDENT = "viewStudent";
    public static final String CONST_VIEW_LESSON = "viewLesson";
    public static final String CONST_VIEW_ADD_STUDENTS = "addStudents";
    public static final String CONST_VIEW_ADD_LESSONS = "addLessons";
    public static final String CONST_VIEW_EDIT_STUDENTS = "editStudents";
    public static final String CONST_VIEW_EDIT_LESSONS = "editLessons";
    public static final String CONST_VIEW_EDIT_STUDENTS_FOR_LESSON = "editStudentsForLesson";
    public static final String CONST_VIEW_ADD_STUDENTS_FOR_LESSON = "addStudentsForLesson";








    //Константы  SQL
    public static final String SQL_TABLE_LESSON = "Lesson";
    public static final String SQL_TABLE_STUDENT = "students";
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
