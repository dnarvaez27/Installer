package dnarvaez27.installer.mundo;

import java.io.File;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Observable;
import java.util.Observer;

import dnarvaez27.installer.recursos.InstallerConstants;
import dnarvaez27.installer.recursos.InstallerUtilities;

public class InstaladorMundo extends Observable
{
	/**
	 * Define el nombre del programa/aplicación a instalar
	 */
	private String nombreApp;

	/**
	 * Define la ruta donde se instalará el programa
	 */
	private String path;

	/**
	 * Define el contrato de instalación del programa
	 */
	private String contrato;

	/**
	 * Define la ruta de las imagenes del instalador
	 */
	private URL[ ] imgPaths;

	/**
	 * Define la ruta del icono del programa
	 */
	private URL pathIcon;

	/**
	 * Define el tamano de la instalación
	 */
	public static double fileSize;

	/**
	 * Define la información del soporte del programa
	 */
	private String soporte;

	/**
	 * Define la ruta y los nombres de los archivos a descargar
	 */
	private LinkedHashMap<String, String> archivos;

	/**
	 * Define la cantidad que se ha descargado
	 */
	private long totalDescargado;

	/**
	 * Constructor del Mundo del Instalador
	 * 
	 * @param nombreApp Nombre del programa
	 * @param len Cantidad de imagenes 4
	 * @param pathIcon Ruta del icono del programa
	 */
	public InstaladorMundo( String nombreApp, int len, URL pathIcon )
	{
		this.nombreApp = nombreApp;
		imgPaths = new URL[ len ];
		this.pathIcon = pathIcon;
	}

	/**
	 * Retorna los datos del soporte del programa
	 * 
	 * @return Los datos del soporte del programa
	 */
	public String getSoporte( )
	{
		return soporte;
	}

	/**
	 * Define el soporte del programa
	 * 
	 * @param soporte Datos de soporte del programa
	 */
	public void setSoporte( String soporte )
	{
		this.soporte = soporte;
	}

	/**
	 * Retorna el contraro del programa
	 * 
	 * @return Contrato del programa
	 */
	public String getContrato( )
	{
		return contrato;
	}

	/**
	 * Define el contrato del programa
	 * 
	 * @param contrato Contrato del programa
	 */
	public void setContrato( String contrato )
	{
		this.contrato = contrato;
	}

	/**
	 * Retorna el tamano de la instalación
	 * 
	 * @return Tamano de la instalación
	 */
	public double getFileSize( )
	{
		return fileSize;
	}

	/**
	 * Deifne el tamano de la instalación
	 * 
	 * @param size Tamano de la instalación
	 */
	public void setFileSize( double size )
	{
		fileSize = size;
	}

	/**
	 * Retorna la ruta del icono del programa
	 * 
	 * @return Ruta del icono del programa
	 */
	public URL getPathIcon( )
	{
		return pathIcon;
	}

	/**
	 * Define la ruta del icono del programa
	 * 
	 * @param pathIcon Ruta del icono del programa
	 */
	public void setPathIcon( URL pathIcon )
	{
		this.pathIcon = pathIcon;
	}

	/**
	 * Define la ruta de la imagen en la posición dada por parámetro
	 * 
	 * @param i Posición de la imagen
	 * @param imgPaths Ruta de la imagen
	 */
	public void setImageIcon( int i, URL imgPaths )
	{
		this.imgPaths[ i ] = imgPaths;
	}

	/**
	 * Retorna las rutas de las imagenes
	 * 
	 * @return Rutas de las imagenes
	 */
	public URL[ ] getImgPaths( )
	{
		return imgPaths;
	}

	/**
	 * Retorna el mensaje de bienvenida al instalador
	 * 
	 * @return Mensaje de bienvenida al instalador
	 */
	public String getMessageBienvenida( )
	{
		String mensaje = "";

		mensaje += "<h1><center>Bienvenido al instalador de  " + nombreApp + "</center></h1>\n";
		mensaje += "Te guiaremos por el proceso de instalación del prodcuto";

		return mensaje;
	}

	/**
	 * Retorna el mensaje de ubicación de la instalación
	 * 
	 * @return Mensaje de ubicación de la instalación
	 */
	public String getMessageUbicacion( )
	{
		String mensaje = "";
		mensaje += "Se instalará " + nombreApp + " en la carpeta que aparece a contunuación. ";
		mensaje += "Para instalarlo en una carpeta diferente, haga click en Explorar ";
		mensaje += "y seleccione otra carpeta. ";
		mensaje += "Haga click en instalar para empezar la instalación";

		return mensaje;
	}

	/**
	 * Retorna el mensaje de la ubicación 2 de la instalación: <br>
	 * Tamano disponible, Tamano que se utilizará y Requerimientos
	 * 
	 * @return Mensaje de Ubicación 2
	 */
	public String getMessageUbicacion2( )
	{
		String mensaje = "";

		File f = new File( InstallerConstants.DEFAULT_PATH );
		mensaje += "Hay " + InstallerUtilities.bytesToGiga( f.getFreeSpace( ) ) + " Gigas disponibles\n";
		mensaje += "Tamaño aproximado: " + ( fileSize != 0 ? InstallerUtilities.round( fileSize, 2 ) + " Megas" : "N/A" ) + "\n";
		mensaje += "Esta instalación requiere una conexión a internet.";

		return mensaje;
	}

	/**
	 * Retorna el mensaje de instalación
	 * 
	 * @return Mensaje de instalación
	 */
	public String getMessageInstalacion( )
	{
		String mensaje = "";

		mensaje += "<h1>Gracias por instalar " + nombreApp + "</h1>\n";
		mensaje += "Espere unos segundos mientras se instala " + nombreApp + "\n";
		mensaje += "Cualquier inconveniente que tenga, porfavor contacte con soporte.\n\n";
		mensaje += soporte;

		return mensaje;
	}

	/**
	 * Retorna el nombre del programa
	 * 
	 * @return Nombre del programa
	 */
	public String getNombreApp( )
	{
		return nombreApp;
	}

	/**
	 * Define el nombre del programa
	 * 
	 * @param nombreApp Nomrbe del programa
	 */
	public void setNombreApp( String nombreApp )
	{
		this.nombreApp = nombreApp;
	}

	/**
	 * Retorna la ubicación de la instalación
	 * 
	 * @return Ubicación de la instalación
	 */
	public String getPath( )
	{
		return path;
	}

	/**
	 * Define la ubicación de la instalación
	 * 
	 * @param path Ubicación de la instalación
	 */
	public void setPath( String path )
	{
		this.path = path;
	}

	/**
	 * <b>Key:</b> Ubicacion relativa + nombre del archivo y extensión.
	 * <br>
	 * <b><i> Si el archivo esta en el folder madre la ubicacion relativa es vacia</b></i>
	 * <br>
	 * <b>Value:</b> URL desde donde se descargará
	 * 
	 * @return Hashmap donde se ingresarán los datos
	 */
	public void setArchivos( LinkedHashMap<String, String> archivos )
	{
		this.archivos = archivos;
	}

	/**
	 * Instala los archivos
	 * 
	 * @param observer Obserer que monitorea el estado de la instalación
	 */
	public void instalar( Observer observer )
	{
		addObserver( observer );
		double fileSizeBytes = fileSize * 1024 * 1024;
		if( path != null )
		{
			try
			{
				File p = new File( path );
				if( !p.exists( ) )
				{
					p.mkdirs( );
				}

				for( String ub : archivos.keySet( ) )
				{
					String nombre = archivos.get( ub );
					String pathF = path + ub;
					System.out.println( nombre + "\n" + pathF );
					File pa = new File( pathF );
					if( !pa.getPath( ).contains( "." ) )
					{
						pa.mkdirs( );
					}
					else
					{
						totalDescargado += InstallerUtilities.downloadFrom( nombre, pathF );
						int porcentaje = ( int ) ( totalDescargado * 100 / fileSizeBytes );
						setChanged( );
						notifyObservers( porcentaje + " %" );
					}
				}
			}
			catch( Exception e )
			{
				e.printStackTrace( );
			}
		}
	}

	/**
	 * Elimina todos los archivos instalados
	 * 
	 * @return
	 */
	public boolean eliminarArchivos( )
	{
		boolean elimino = false;
		if( path != null )
		{
			File dir = new File( path );
			if( dir.isDirectory( ) )
			{
				for( File f : dir.listFiles( ) )
				{
					eliminarArchivosDe( f );
					f.delete( );
				}
				dir.delete( );
			}
			elimino = !dir.exists( );
		}
		return elimino;
	}

	/**
	 * Elimina los archivos de un directorio
	 * 
	 * @param file Directorio de donde se eliminarán los archivos
	 */
	private void eliminarArchivosDe( File file )
	{
		if( file.isDirectory( ) )
		{
			for( File f : file.listFiles( ) )
			{
				if( f.isDirectory( ) )
				{
					eliminarArchivosDe( f );
				}
				f.delete( );
			}
		}
	}
}