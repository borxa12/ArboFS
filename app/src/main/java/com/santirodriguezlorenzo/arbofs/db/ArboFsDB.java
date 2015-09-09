package com.santirodriguezlorenzo.arbofs.db;

import android.provider.BaseColumns;

/**
 * Created by Santi on 09/09/2015.
 */
public class ArboFsDB {

    /*
     * Nombre de la base de datos
     */
    public static final String DB_NAME = "arbofs.db";

    /*
     * version de la base de datos
     */
    public static final int DB_VERSION = 1;

    /**
     * Esta clase no debe ser instanciada
     */
    private ArboFsDB() {
    }

    /*
     * Definici�n de la tabla lugares
     */
    public static final class ArboFs implements BaseColumns {
        /**
         * Esta clase no debe ser instanciada
         */
        private ArboFs() {
        }

        /**
         * orden por defecto
         */
        public static final String DEFAULT_SORT_ORDER = "_ID ASC";

        /**
         * Abstracción de los nombres de campos y tabla a constantes para
         * facilitar cambios en la estructura interna de la BD
         */
        public static final String TABLE_NAME_PLAYERS = "players";

        public static final String _ID = "_id";
        public static final String NAME = "name";
        public static final String FULL_NAME = "fullName";
        public static final String ID_NAME = "idName";
        public static final String BIRTHDATE = "birthdate";
        public static final String FAVORITE = "favorite";
        public static final String HEIGHT = "height";
        public static final String DORSAL = "dorsal";
        public static final String NATIONALITY = "nationality";
        public static final String NICKNAME = "nickname";
        public static final String POSITION = "position";
        public static final String DESCRIPTION_ES = "descriptionEs";
        public static final String DESCRIPTION_GL = "descriptionGl";

    }


}
