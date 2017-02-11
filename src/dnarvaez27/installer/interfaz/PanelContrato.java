package dnarvaez27.installer.interfaz;

import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.dnarvaez27.componentes.DialogoInformacion;
import com.dnarvaez27.componentes.ScrollColor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import dnarvaez27.installer.recursos.InstallerConstants;
import dnarvaez27.installer.recursos.InstallerUtilities;

public class PanelContrato extends JPanel implements MouseListener, ActionListener
{
	private static final long serialVersionUID = 3791619342294615005L;
	
	private JLabel texto;
	
	private JTextArea contrato;
	
	private ScrollColor scroll;
	
	private JCheckBox aceptar;
	
	private JButton atras;
	
	private JButton cancelar;
	
	private JButton siguiente;
	
	private InterfazInstalador interfaz;
	
	public PanelContrato( InterfazInstalador interfaz )
	{
		this.interfaz = interfaz;
		setLayout( new BorderLayout( ) );
		setBorder( BorderFactory.createEmptyBorder( 20, 20, 3, 20 ) );
		setBackground( InstallerConstants.BLANCO );
		
		texto = new JLabel( );
		texto.setBorder( BorderFactory.createEmptyBorder( 5, 5, 5, 5 ) );
		
		JPanel panelContrato = new JPanel( );
		panelContrato.setBackground( null );
		panelContrato.setLayout( new BorderLayout( ) );
		
		contrato = new JTextArea( );
		contrato.setEditable( false );
		contrato.setLineWrap( true );
		contrato.setBorder( BorderFactory.createEmptyBorder( 10, 10, 10, 10 ) );
		contrato.setWrapStyleWord( true );
		
		scroll = new ScrollColor( contrato, 0, InstallerConstants.BLANCO, InstallerConstants.GRIS, InstallerConstants.GRIS, InstallerConstants.BLANCO );
		scroll.setBorder( BorderFactory.createLineBorder( InstallerConstants.GRIS ) );
		scroll.setPreferredSize( new Dimension( 380, 366 ) );
		
		aceptar = new JCheckBox( "Acepto" );
		aceptar.setBackground( null );
		aceptar.setSelectedIcon( InstallerConstants.CHK_OK );
		aceptar.setIcon( InstallerConstants.CHK_OFF );
		aceptar.setFocusPainted( false );
		aceptar.setBorderPainted( false );
		aceptar.setCursor( InstallerConstants.HAND );
		aceptar.setForeground( InstallerConstants.NEGRO );
		
		panelContrato.add( scroll, BorderLayout.CENTER );
		panelContrato.add( aceptar, BorderLayout.SOUTH );
		
		atras = new JButton( InstallerConstants.ATRAS );
		cancelar = new JButton( InstallerConstants.CANCELAR );
		siguiente = new JButton( InstallerConstants.SIGUIENTE );
		
		JButton[ ] bot =
		{
				atras,
				cancelar,
				siguiente
		};
		
		for( JButton b : bot )
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
		
		panelBotones.add( atras );
		panelBotones.add( cancelar );
		panelBotones.add( siguiente );
		
		add( texto, BorderLayout.NORTH );
		add( panelContrato, BorderLayout.CENTER );
		add( panelBotones, BorderLayout.SOUTH );
	}
	
	public void actualizar( String txt, String cont )
	{
		txt = "<html>" + txt.replace( "\n", "<br>" );
		texto.setText( txt );
		
		contrato.setText( cont );
		contrato.setCaretPosition( 0 );
	}
	
	@Override
	public void actionPerformed( ActionEvent e )
	{
		String comando = e.getActionCommand( );
		
		if( comando.equals( InstallerConstants.SIGUIENTE ) )
		{
			if( aceptar.isSelected( ) )
			{
				interfaz.showPage( 3 );
			}
			else
			{
				String msg = "<html>";
				msg += "<p align=\"justify\"><h2>No se puede continuar con la instalación.</h2><br><br>";
				msg += "Si no se aceptan los términos del contrato, ";
				msg += "no se puede instalar el programa";
				URL img = InstallerUtilities.resources.getPathFile( "Error.png" );
				
				ImageIcon imageIcon = new ImageIcon( );
				try
				{
					BufferedImage bi = ImageIO.read( img );
					bi = InstallerUtilities.resize( bi, 150, 150 );
					imageIcon = new ImageIcon( bi );
				}
				catch( Exception e2 )
				{
					e2.printStackTrace( );
				}
				DialogoInformacion di = new DialogoInformacion( "Contrato", msg, "", InstallerConstants.ROJO, imageIcon );
				di.setButtonsText( InstallerConstants.ACEPTAR, "" );
				di.setForeground( InstallerConstants.BLANCO );
				di.mostrarDialogo( );
			}
		}
		else if( comando.equals( InstallerConstants.ATRAS ) )
		{
			interfaz.showPage( 1 );
		}
		else if( comando.equals( InstallerConstants.CANCELAR ) )
		{
			System.exit( 0 );
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
		
		if( but.getActionCommand( ).equals( InstallerConstants.SIGUIENTE ) )
		{
			if( !aceptar.isSelected( ) )
			{
				but.setBackground( InstallerConstants.ROJO );
				but.setBorder( BorderFactory.createLineBorder( InstallerConstants.ROJO ) );
			}
			else
			{
				but.setBackground( InstallerConstants.VERDE );
				but.setBorder( BorderFactory.createLineBorder( InstallerConstants.VERDE ) );
			}
			but.setForeground( InstallerConstants.BLANCO );
		}
		else
		{
			but.setBackground( InstallerConstants.GRIS );
			but.setForeground( InstallerConstants.BLANCO );
			but.setBorder( BorderFactory.createLineBorder( InstallerConstants.GRIS ) );
		}
	}
	
	@Override
	public void mouseExited( MouseEvent e )
	{
		JButton but = ( JButton ) e.getSource( );
		
		but.setBackground( InstallerConstants.BLANCO );
		but.setForeground( InstallerConstants.NEGRO );
		but.setBorder( BorderFactory.createLineBorder( InstallerConstants.NEGRO ) );
	}
}
