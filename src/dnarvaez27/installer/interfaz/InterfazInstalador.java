package dnarvaez27.installer.interfaz;

import java.net.URL;
import java.util.LinkedHashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;

import dnarvaez27.installer.mundo.InstaladorMundo;
import dnarvaez27.installer.recursos.InstallerConstants;
import dnarvaez27.installer.recursos.InstallerUtilities;

public class InterfazInstalador extends JFrame
{
	private static final long serialVersionUID = 2979709929098072861L;

	private PanelPrincipal panelPrincipal;

	private InstaladorMundo instalador;

	private JPanel contenedor;

	private JLabel imagen;

	public void iniciar( )
	{
		showPage( 1 );

		setSize( 700, 550 );
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		setLocationRelativeTo( null );
		setVisible( true );
	}

	public InterfazInstalador( String appName, URL[ ] imgPaths, URL pathIcon )
	{
		inicializar( appName, imgPaths, pathIcon );
	}

	public InterfazInstalador( String appName, URL[ ] imgPaths, URL pathIcon, double fileSize )
	{
		inicializar( appName, imgPaths, pathIcon );
		setFileSize( fileSize );
		actualizar( );
	}

	private void inicializar( String appName, URL[ ] imgPaths, URL pathIcon )
	{
		setTitle( "Instalador " + appName );
		setLayout( new BorderLayout( ) );
		setIconImage( new ImageIcon( pathIcon ).getImage( ) );
		instalador = new InstaladorMundo( appName, imgPaths.length, pathIcon );

		for( int i = 0; i < imgPaths.length; i++ )
		{
			instalador.setImageIcon( i, imgPaths[ i ] );
		}

		imagen = new JLabel( );

		contenedor = new JPanel( );
		contenedor.setBackground( InstallerConstants.BLANCO );

		panelPrincipal = new PanelPrincipal( this );

		contenedor.add( panelPrincipal );

		add( contenedor, BorderLayout.CENTER );
		add( imagen, BorderLayout.WEST );
	}

	public void setArchivos( LinkedHashMap<String, String> archivos )
	{
		instalador.setArchivos( archivos );
	}

	public void setContrato( String cont )
	{
		instalador.setContrato( cont );
		actualizar( );
	}

	public void setFileSize( double size )
	{
		instalador.setFileSize( size );
	}

	public void setSoporte( String sop )
	{
		instalador.setSoporte( sop );
	}

	public void actualizar( )
	{
		showPage( panelPrincipal.getActual( ) );
	}

	private ImageIcon resize( URL img )
	{
		ImageIcon image = null;
		try
		{
			BufferedImage im = ImageIO.read( img );
			im = InstallerUtilities.resize( im, 270, 520 );
			image = new ImageIcon( im );
		}
		catch( Exception e )
		{
			e.printStackTrace( );
		}
		return image;
	}

	public void showPage( int p )
	{
		switch( p )
		{
			case 1:
				panelPrincipal.show( p );
				imagen.setIcon( resize( instalador.getImgPaths( )[ 0 ] ) );
				break;
			case 2:
				panelPrincipal.show( p );
				imagen.setIcon( resize( instalador.getImgPaths( )[ 1 ] ) );
				break;
			case 3:
				panelPrincipal.show( p );
				imagen.setIcon( resize( instalador.getImgPaths( )[ 2 ] ) );
				break;
			case 4:
				panelPrincipal.show( p );
				imagen.setIcon( resize( instalador.getImgPaths( )[ 3 ] ) );
				break;
			default:
				break;
		}
	}

	public InstaladorMundo getInstalador( )
	{
		return instalador;
	}
}