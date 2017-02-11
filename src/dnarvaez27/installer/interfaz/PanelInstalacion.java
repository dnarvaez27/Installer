package dnarvaez27.installer.interfaz;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.dnarvaez27.componentes.DialogoInformacion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import dnarvaez27.installer.recursos.InstallerConstants;
import dnarvaez27.installer.recursos.InstallerUtilities;

public class PanelInstalacion extends JPanel implements MouseListener, ActionListener, Observer
{
	private static final long serialVersionUID = -7161772836117973062L;
	
	private JLabel ajax;
	
	private JLabel text;
	
	private InterfazInstalador interfaz;
	
	private JButton cancelar;
	
	private JButton finalizar;
	
	private boolean termino;
	
	private boolean reachable;
	
	private PanelInstalacion instancia;
	
	public PanelInstalacion( InterfazInstalador interfaz )
	{
		reachable = InstallerUtilities.reachable( );
		setLayout( new BorderLayout( ) );
		setBorder( BorderFactory.createEmptyBorder( 20, 20, 20, 20 ) );
		setBackground( InstallerConstants.BLANCO );
		
		this.interfaz = interfaz;
		
		String insTxt = interfaz.getInstalador( ).getMessageInstalacion( );
		insTxt = "<html><center>" + insTxt.replace( "\n", "<br>" );
		text = new JLabel( insTxt );
		
		ajax = new JLabel( InstallerConstants.AJAX );
		
		if( !reachable )
		{
			ajax.setIcon( null );
			ajax.setFont( getFont( ).deriveFont( Font.BOLD, 30 ) );
			ajax.setText( InstallerConstants.SIN_CONEXION );
			ajax.setHorizontalAlignment( SwingConstants.CENTER );
			termino = true;
		}
		
		cancelar = new JButton( InstallerConstants.CANCELAR );
		finalizar = new JButton( InstallerConstants.FINALIZAR );
		finalizar.setEnabled( false );
		
		JButton[ ] but =
		{
				cancelar,
				finalizar
		};
		
		for( JButton b : but )
		{
			b.setBackground( InstallerConstants.BLANCO );
			b.setForeground( InstallerConstants.NEGRO );
			b.setContentAreaFilled( false );
			b.setOpaque( true );
			b.setFocusPainted( false );
			b.setBorder( BorderFactory.createLineBorder( InstallerConstants.GRIS ) );
			b.setCursor( InstallerConstants.HAND );
			b.setPreferredSize( new Dimension( 80, 30 ) );
			b.addMouseListener( this );
			b.addActionListener( this );
		}
		
		JPanel panelBotones = new JPanel( );
		panelBotones.setBackground( null );
		
		panelBotones.add( cancelar );
		panelBotones.add( finalizar );
		
		add( text, BorderLayout.NORTH );
		add( ajax, BorderLayout.CENTER );
		add( panelBotones, BorderLayout.SOUTH );
		
		instancia = this;
	}
	
	public void actualizar( )
	{
		String insTxt = interfaz.getInstalador( ).getMessageInstalacion( );
		insTxt = "<html><center>" + insTxt.replace( "\n", "<br>" );
		text.setText( insTxt );
		if( reachable )
		{
			instalar( );
		}
	}
	
	public void instalar( )
	{
		new Thread( "Instalar" )
		{
			public void run( )
			{
				try
				{
					interfaz.getInstalador( ).instalar( instancia );
					finalizar.setEnabled( true );
					ajax.setIcon( null );
					ajax.setFont( getFont( ).deriveFont( Font.BOLD, 40 ) );
					ajax.setText( "Listo!" );
					termino = true;
				}
				catch( Exception e )
				{
					ajax.setIcon( null );
					ajax.setFont( getFont( ).deriveFont( Font.BOLD, 40 ) );
					ajax.setText( "Error" );
					termino = true;
				}
			}
		}.start( );
	}
	
	@Override
	public void actionPerformed( ActionEvent e )
	{
		String comando = e.getActionCommand( );
		
		if( comando.equals( InstallerConstants.FINALIZAR ) )
		{
			System.exit( 0 );
		}
		else if( comando.equals( InstallerConstants.CANCELAR ) )
		{
			if( !termino )
			{
				URL img = InstallerUtilities.resources.getPathFile( "Advertencia.png" );
				String msg = "<html><center><h2>";
				msg += "¿Seguro que desea salir e <br> ";
				msg += "interrumpir la instalación?</h2>";
				msg += "<h4>Se eliminarán los archivos<br>que se han instalado</h4>";
				msg += "</center></html>";
				
				ImageIcon imgIcon = new ImageIcon( );
				try
				{
					BufferedImage bi = ImageIO.read( img );
					bi = InstallerUtilities.resize( bi, 150, 150 );
					imgIcon = new ImageIcon( bi );
				}
				catch( IOException e1 )
				{
					e1.printStackTrace( );
				}
				
				DialogoInformacion di = new DialogoInformacion( "Salir", msg, "", InstallerConstants.AZUL, imgIcon );
				di.setForeground( InstallerConstants.BLANCO );
				boolean res = di.mostrarDialogo( );
				if( res )
				{
					boolean elimino = false;
					while( !elimino )
					{
						elimino = interfaz.getInstalador( ).eliminarArchivos( );
					}
					if( elimino )
					{
						System.exit( 0 );
					}
				}
			}
			else
			{
				System.exit( 0 );
			}
		}
	}
	
	@Override
	public void mouseClicked( MouseEvent e )
	{
	}
	
	@Override
	public void mousePressed( MouseEvent e )
	{
	}
	
	@Override
	public void mouseReleased( MouseEvent e )
	{
	}
	
	@Override
	public void mouseEntered( MouseEvent e )
	{
		JButton but = ( JButton ) e.getSource( );
		
		if( but.isEnabled( ) )
		{
			but.setBackground( InstallerConstants.GRIS );
			but.setForeground( InstallerConstants.BLANCO );
		}
	}
	
	@Override
	public void mouseExited( MouseEvent e )
	{
		JButton but = ( JButton ) e.getSource( );
		
		if( but.isEnabled( ) )
		{
			but.setBackground( InstallerConstants.BLANCO );
			but.setForeground( InstallerConstants.NEGRO );
		}
	}
	
	@Override
	public void update( Observable o, Object arg )
	{
		ajax.setText( String.valueOf( arg ) );
	}
}