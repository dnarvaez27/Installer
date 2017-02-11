package dnarvaez27.installer.recursos;

import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Cursor;

public class InstallerConstants
{
	public static final String DEFAULT_PATH = "C:\\Program Files\\";
	
	public static final String SIGUIENTE = "Siguiente";
	
	public static final String EXPLORAR = "Explorar";
	
	public static final String INSTALAR = "Instalar";
	
	public static final String ATRAS = "Atrás";
	
	public static final String CANCELAR = "Cancelar";
	
	public static final String ACEPTAR = "Aceptar";
	
	public static final String FINALIZAR = "Finalizar";
	
	public static final String SIN_CONEXION = "Sin Conexión a Internet";
	
	public static final Color BLANCO = new Color( 255, 255, 255 );
	
	public static final Color NEGRO = new Color( 0, 0, 0 );
	
	public static final Color GRIS = new Color( 33, 33, 33 );
	
	public static final Color ROJO = new Color( 240, 0, 50 );
	
	public static final Color VERDE = new Color( 180, 200, 0 );
	
	public static final Color AZUL = new Color( 0, 150, 150 );
	
	public static final Cursor HAND = new Cursor( Cursor.HAND_CURSOR );
	
	public static final ImageIcon BOX_CODE = new ImageIcon( InstallerUtilities.resources.getPathFile( "BoxCode.png" ) );
	
	public static final ImageIcon CHK_OK = new ImageIcon( InstallerUtilities.resources.getPathFile( "CheckON.png" ) );
	
	public static final ImageIcon CHK_OFF = new ImageIcon( InstallerUtilities.resources.getPathFile( "CheckOFF.png" ) );
	
	public static final ImageIcon AJAX = new ImageIcon( InstallerUtilities.resources.getPathFile( "Ajax.gif" ) );
}