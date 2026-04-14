package OpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import Entidad.ParametrosConfig;
import Entidad.Usuario;
import Entidad.Glucemias;

public class OpenHelper extends SQLiteOpenHelper {

    //Query para crear tabla usuarios
    public static String usuarioCreacionTable = "CREATE TABLE IF NOT EXISTS usuarios(Email Text primary key, Nombre Text, Contrasenia Text)";
    public static String configCreacionTable = "CREATE TABLE IF NOT EXISTS parametros_config (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "email_usuario TEXT, " +
                    "tipo_insulina_rapida TEXT, " +
                    "tipo_insulina_basal TEXT, " +
                    "factor_correccion TEXT, " +
                    "umbral_min TEXT, " +
                    "umbral_max TEXT, " +
                    "relacion_insulina_hidratos TEXT, " +
                    "FOREIGN KEY(email_usuario) REFERENCES usuarios(Email)" +
                    ")";
    public static String glucemiaCreacionTable = "CREATE TABLE IF NOT EXISTS glucemias (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "email_usuario TEXT, " +
            "nivel_glucemia TEXT, " +
            "estacion_alimenticia TEXT, " +
            "horario TEXT, " +
            "fecha TEXT, " +
            "FOREIGN KEY(email_usuario) REFERENCES usuarios(Email)" +
            ")";

    public static String usuarioTable = "usuarios";
    public static String usuarioColumnaNombre = "Nombre";
    public static String usuarioColumnaEmail = "Email";
    public static String usuarioColumnaContrasenia = "Contrasenia";
    private ContentValues valores;

    public OpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //Creaeción tabla usuarios
        db.execSQL(usuarioCreacionTable);

        //Creaeción de tabla parametros_config
        db.execSQL(configCreacionTable);

        //Creaeción de tabla glucemias
        db.execSQL(glucemiaCreacionTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
    }

    //Metodo para dar de alta a los usuarios
    public long insertarUsuario(Usuario usuario)
    {
        long resultado;
          //Consulta si el email ya esta registrado
        String query = "SELECT * FROM " + usuarioTable + " WHERE " + usuarioColumnaEmail + " = ?";
        Cursor fila = this.getWritableDatabase().rawQuery(query, new String[]{usuario.getEmail()});
        if (fila.moveToFirst())
        {
            fila.close();
            return -2;
        }
        fila.close();

        //inserta un nuevo usuario en la tabla de usuarios
        valores = new ContentValues();
        valores.put(usuarioColumnaEmail, usuario.getEmail());
        valores.put(usuarioColumnaNombre, usuario.getNombre());
        valores.put(usuarioColumnaContrasenia, usuario.getContrasenia());
        resultado = this.getWritableDatabase().insert(usuarioTable, null, valores);

        return resultado;
    }

    //Metodo para consultar si existe un usuarios
    public boolean buscarUsuario(Usuario usuario)
    {
        String query = "SELECT * FROM " + usuarioTable + " WHERE " + usuarioColumnaEmail + " = ? AND " + usuarioColumnaContrasenia + " = ?";
        Cursor fila = this.getWritableDatabase().rawQuery(query, new String[]{usuario.getEmail(), usuario.getContrasenia()});

        if (!fila.moveToFirst())
        {
            fila.close();
            return false;
        }
        fila.close();

        return true;
    }

    //Metodo para dar de alta y guardare la configuración personalizada del tratamiento
    public long insertarConfiguracion(ParametrosConfig config)
    {
        long resultado;

        valores = new ContentValues();
        valores.put("email_usuario", config.getEmailUsuario());
        valores.put("tipo_insulina_rapida", config.getTipoInsulinaRapida());
        valores.put("tipo_insulina_basal", config.getTipoInsulinaBasal());
        valores.put("factor_correccion", config.getFactorCorreccion());
        valores.put("umbral_min", config.getUmbralMinCorreccion());
        valores.put("umbral_max", config.getUmbralMaxCorreccion());
        valores.put("relacion_insulina_hidratos", config.getRelacionInsulinaHidratos());

        resultado = this.getWritableDatabase().insert("parametros_config", null, valores);

        return resultado;
    }

    //Metodo para dar de alta y guardare la registros de glucemias
    public long insertarGlucemia(Glucemias glucemia)
    {
        long resultado;

        valores = new ContentValues();
        valores.put("email_usuario", glucemia.getEmailUsuario());
        valores.put("nivel_glucemia", glucemia.getNivelGlucemia());
        valores.put("estacion_alimenticia", glucemia.getEstacionAlimenticia());
        valores.put("horario", glucemia.getHorario());
        valores.put("fecha", glucemia.getFecha());

        resultado = this.getWritableDatabase().insert("glucemias", null, valores);

        return resultado;
    }
}
