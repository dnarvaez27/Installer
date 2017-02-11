package dnarvaez27.installer;

import java.io.BufferedReader;
import java.net.URL;
import java.util.LinkedHashMap;

import com.dnarvaez27.resources.Resources;

import dnarvaez27.installer.interfaz.InterfazInstalador;

/**
 * @author d.narvaez11
 */
public abstract class Instalador
{
	/**
	 * Instalador genérico
	 */
	private InterfazInstalador instalador;
	
	/**
	 * Lector de recursos
	 */
	private Resources resources;
	
	/**
	 * Nombre del programa a instalar
	 */
	protected String appName;
	
	/**
	 * Información del soporte del programa
	 */
	protected String supportContact;
	
	/**
	 * Ubicación del recurso del icono del programa
	 */
	protected String iconPath;
	
	/**
	 * Ubicación del recurso de las imagenes del programa. Cantidad: 4
	 */
	protected String[ ] imgsPaths;
	
	/**
	 * Ubicación del recurso del contrato y términos del programa.
	 */
	protected String contractPath;
	
	/**
	 * Tamaño aproximado de la descarga del programa en <b>MEGABYTES.
	 */
	protected double fileSize;
	
	/**
	 * Ubicación del recurso del archivo CSV con la información de los archivos a descargar.
	 */
	protected String filesPaths;
	
	/**
	 * <u>Se debe inicializar: </u><br>
	 * <b> 1. appName</b> String: Nombre del programa<br>
	 * <b> 2. supportContact</b> String: Información del soporte<br>
	 * <b> 3. iconPath</b> String: Ubicación del recurso del icono del programa<br>
	 * <b> 4. imgsPaths</b> String[ ]: Arreglo con las ubicaciones de las imagenes del programa ( 270, 520 )<br>
	 * <b> 5. contractPath</b> String: Ubicación del recurso del contrato del programa<br>
	 * <b> 6. fileSize</b> double: Tamaño aproximado del programa<br>
	 * <b> 7. filesPaths</b> String: Ubicación del archivo CSV con la información de las descargas<br>
	 * 
	 * @param mainClass Clase principal que contiene el método main
	 * @param mainRscFolder Nombre del directorio donde se encuentran los recursos
	 *        <b><u>Incluir slashes solo si hay subfolders</u></b>
	 */
	public Instalador( Class<?> mainClass, String mainRscFolder )
	{
		resources = new Resources( mainClass, mainRscFolder );
	}
	
	/**
	 * <u>Se debe inicializar: </u><b><br>
	 * <ol>
	 * <li>appName</b> <i>String:</i> Nombre del programa<b><br>
	 * <li>supportContact</b> <i>String:</i> Información del soporte<b><br>
	 * <li>iconPath</b> <i>String:</i> Ubicación del recurso del icono del programa<b><br>
	 * <li>imgsPaths</b> <i>String[ ]:</i> Arreglo con las ubicaciones de las imagenes del programa ( 270, 520 )<b><br>
	 * <li>contractPath</b> <i>String:</i> Ubicación del recurso del contrato del programa<b><br>
	 * <li>fileSize</b> <i>double:</i>Tamaño aproximado del programa<b><br>
	 * <li>filesPaths</b> <i>String:</i> Ubicación del archivo CSV con la información de las descargas<br>
	 * </ol>
	 * 
	 * @param mainClass Clase principal que contiene el método main
	 * @param mainRscFolder Nombre del directorio donde se encuentran los recursos
	 *        <b><u>Incluir slashes solo si hay subdirectorios</u></b>
	 */
	public Instalador( )
	{
		
	}
	
	/**
	 * @param mainClass Clase principal que contiene el método main
	 * @param mainRscFolder Nombre del directorio donde se encuentran los recursos
	 *        <b><u>Incluir slashes solo si hay subfolders</u></b>
	 */
	public void setResources( Class<?> mainClass, String mainRscFolder )
	{
		resources = new Resources( mainClass, mainRscFolder );
	}
	
	/**
	 * Transformación de los datos en el archivo CSV a HashMap<String, String> <br>
	 * La ubicación del recurso del archivo CSV se pasa por parámetro
	 * 
	 * @param path Ubicación del recurso del archivo CSV
	 * @return HashMap<String, String> con los datos necesarios para la descarga.
	 */
	private LinkedHashMap<String, String> archivosDesdeCSV( String path )
	{
		LinkedHashMap<String, String> arc = new LinkedHashMap<>( );
		try
		{
			BufferedReader bf = resources.getScannerFromResource( path );
			String linea = bf.readLine( );
			while( linea != null )
			{
				String[ ] casilla = linea.split( ";" );
				String nombre = casilla[ 0 ].replace( "/", "\\" );
				String url = "";
				if( casilla.length > 1 )
				{
					url = casilla[ 1 ];
				}
				arc.put( nombre, url );
				
				linea = bf.readLine( );
			}
		}
		catch( Exception e )
		{
			e.printStackTrace( );
		}
		return arc;
	}
	
	/**
	 * Escribe en un String, el contrato y términos del programa. <br>
	 * La ubicación del recurso del contrato se pasa por parámetro.
	 * 
	 * @param path Ubicación del recurso del archivo con el contrato
	 * @return Contrato y términos del programa.
	 */
	private String leerContrato( String path )
	{
		String contrato = resources.getTextFromResource( path );
		return contrato;
	}
	
	/**
	 * Crea un arreglo de URL con la ubicación de los recursos de las imágenes.
	 * 
	 * @return Arreglo de URL con la ubicación de los recursos de las imágenes.
	 */
	private URL[ ] imgsStringToURL( )
	{
		URL[ ] link = new URL[ 4 ];
		for( int u = 0; u < imgsPaths.length; u++ )
		{
			link[ u ] = resources.getPathFile( imgsPaths[ u ] );
		}
		return link;
	}
	
	/**
	 * Crea el instalador con la información suministrada anteriormente.<br>
	 * <b><u>PRE:</u></b> Se han inicializado:<br>
	 * <ol>
	 * <li><b> appName</b> <i>String:</i> Nombre del programa<br>
	 * <li><b> supportContact</b> <i>String:</i> Información del soporte<br>
	 * <li><b> iconPath</b> <i>String:</i> Ubicación del recurso del icono del programa<br>
	 * <li><b> imgsPaths</b> <i>String[ ]:</i> Arreglo con las ubicaciones de las imagenes del programa ( Dimensiones: 270, 520 )<br>
	 * <li><b> contractPath</b> <i>String:</i> Ubicación del recurso del contrato del programa<br>
	 * <li><b> fileSize</b> <i>double:</i> Tamaño aproximado del programa<br>
	 * <li><b> filesPaths</b> <i>String:</i> Ubicación del archivo CSV con la información de las descargas<br>
	 * <br>
	 * <b><u>POS:</u></b> Se inicializa y se crea la ventana del instalador.
	 */
	public void create( )
	{
		instalador = new InterfazInstalador( appName, imgsStringToURL( ), resources.getPathFile( iconPath ), fileSize );
		
		instalador.setSoporte( supportContact );
		instalador.setContrato( leerContrato( contractPath ) );
		instalador.setArchivos( archivosDesdeCSV( filesPaths ) );
		
		instalador.iniciar( );
	}
}