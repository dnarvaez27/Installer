package dnarvaez27.installer.recursos;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import com.dnarvaez27.resources.Resources;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import dnarvaez27.installer.interfaz.InterfazInstalador;
import dnarvaez27.installer.mundo.InstaladorMundo;

public class InstallerUtilities
{
	public static Resources resources = new Resources( InterfazInstalador.class, "imagenes" );
	
	/**
	 * Progreso de la descarga e instalación de los archivos
	 */
	private static int progreso = 0;
	
	/**
	 * Redimensiona una imagen
	 * 
	 * @param image BufferedImage que se quiere redimensionar
	 * @param width Ancho deseado
	 * @param height Alto deseado
	 * @return BufferedImage con las dimensiones deseadas
	 */
	public static BufferedImage resize( BufferedImage image, int width, int height )
	{
		BufferedImage bi = new BufferedImage( width, height, BufferedImage.TRANSLUCENT );
		Graphics2D g2d = ( Graphics2D ) bi.createGraphics( );
		g2d.addRenderingHints( new RenderingHints( RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY ) );
		g2d.drawImage( image, 0, 0, width, height, null );
		g2d.dispose( );
		return bi;
	}
	
	/**
	 * Realiza la conversion de Bytes a Gigabytes
	 * 
	 * @param bytes Cantidad de Bytes a ser transformados
	 * @return Cantidad expresada en Gigabytes.
	 */
	public static int bytesToGiga( long bytes )
	{
		bytes /= 1024;
		bytes /= 1024;
		bytes /= 1024;
		return ( int ) bytes;
	}
	
	public static double round( double valor, int lugares )
	{
		if( lugares < 0 )
		{
			throw new IllegalArgumentException( );
		}
		
		BigDecimal bd = new BigDecimal( valor );
		bd = bd.setScale( lugares, RoundingMode.HALF_UP );
		return bd.doubleValue( );
	}
	
	/**
	 * Verifica la conexión a internet
	 * 
	 * @return True o False
	 */
	public static boolean reachable( )
	{
		boolean reachable = false;
		try
		{
			Socket socket = null;
			try
			{
				socket = new Socket( "www.google.com", 80 );
				reachable = true;
			}
			finally
			{
				if( socket != null )
				{
					try
					{
						socket.close( );
					}
					catch( IOException e )
					{
						e.printStackTrace( );
					}
				}
			}
		}
		catch( Exception e )
		{
			System.err.println( "Utilidades.reachable( ): No se pudo conectar a internet" );
		}
		return reachable;
	}
	
	/**
	 * Realiza una descarga desde un url a una ruta especificada
	 * 
	 * @param url URL de donde se descargará
	 * @param path Ruta donde sera descargado el archivo
	 * @throws IOException
	 */
	public static long downloadFrom( String url, String path ) throws IOException
	{
		URL website = new URL( url );
		ReadableByteChannel rbc = Channels.newChannel( website.openStream( ) );
		FileOutputStream fos = new FileOutputStream( path );
		long bytes = fos.getChannel( ).transferFrom( rbc, 0, Long.MAX_VALUE );
		fos.close( );
		
		System.out.println( ( "KB: " + bytes / 1024 ) );
		sizeDownloaded( website ); // XXX
		
		return bytes;
	}
	
	/**
	 * Monitorea el tamanho total que será descargado de un URL
	 * 
	 * @param url URL del archivo que se quiere descargar
	 * @throws IOException
	 */
	private static void sizeDownloaded( URL url ) throws IOException
	{
		URLConnection connection = url.openConnection( );
		connection.connect( );
		int fileLenth = connection.getContentLength( );
		
		progreso += ( ( fileLenth / 1024 ) * 100 ) / ( InstaladorMundo.fileSize * 1024 );
		System.out.println( "( " + progreso + " % ) " + ( fileLenth / 1024 ) + " KB" );
	}
}