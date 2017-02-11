package dnarvaez27.installer.interfaz;

import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.dnarvaez27.componentes.FileChooser;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import dnarvaez27.installer.recursos.InstallerConstants;

public class PanelUbicacion extends JPanel implements MouseListener, ActionListener
{
	private static final long serialVersionUID = -9081018411018514709L;
	
	private JLabel texto;
	
	private JLabel texto2;
	
	private JTextField fieldUbicacion;
	
	private JButton explorar;
	
	private JLabel image;
	
	private JButton instalar;
	
	private JButton cancelar;
	
	private JButton atras;
	
	private InterfazInstalador interfaz;
	
	public PanelUbicacion( InterfazInstalador interfaz )
	{
		this.interfaz = interfaz;
		setLayout( new BorderLayout( ) );
		setPreferredSize( new Dimension( 420, 500 ) );
		setBackground( null );
		setBorder( BorderFactory.createEmptyBorder( 3, 3, 3, 3 ) );
		
		JPanel principal = new JPanel( );
		principal.setLayout( new BorderLayout( ) );
		principal.setBackground( InstallerConstants.BLANCO );
		principal.setBorder( BorderFactory.createEmptyBorder( 8, 10, 8, 10 ) );
		
		texto = new JLabel( );
		texto.setFont( getFont( ).deriveFont( Font.BOLD, 13 ) );
		
		texto2 = new JLabel( );
		texto2.setFont( getFont( ).deriveFont( Font.BOLD, 13 ) );
		
		JPanel panelUbicacion = new JPanel( );
		panelUbicacion.setBackground( null );
		
		fieldUbicacion = new JTextField( );
		fieldUbicacion.setPreferredSize( new Dimension( 300, 30 ) );
		fieldUbicacion.setBorder( BorderFactory.createEmptyBorder( 2, 5, 2, 5 ) );
		fieldUbicacion.setBackground( InstallerConstants.GRIS );
		fieldUbicacion.setForeground( InstallerConstants.BLANCO );
		
		explorar = new JButton( InstallerConstants.EXPLORAR );
		explorar.setActionCommand( InstallerConstants.EXPLORAR );
		
		JPanel botones = new JPanel( );
		botones.setBackground( null );
		
		atras = new JButton( InstallerConstants.ATRAS );
		atras.setActionCommand( InstallerConstants.ATRAS );
		cancelar = new JButton( InstallerConstants.CANCELAR );
		cancelar.setActionCommand( InstallerConstants.CANCELAR );
		instalar = new JButton( InstallerConstants.INSTALAR );
		instalar.setActionCommand( InstallerConstants.INSTALAR );
		
		botones.add( atras );
		botones.add( cancelar );
		botones.add( instalar );
		
		JButton[ ] bot =
		{
				explorar,
				atras,
				cancelar,
				instalar
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
		
		image = new JLabel( InstallerConstants.BOX_CODE );
		
		JPanel panelInferior = new JPanel( );
		panelInferior.setLayout( new BorderLayout( ) );
		panelInferior.setBackground( null );
		
		panelInferior.add( image, BorderLayout.CENTER );
		panelInferior.add( texto2, BorderLayout.SOUTH );
		
		panelUbicacion.add( fieldUbicacion );
		panelUbicacion.add( explorar );
		panelUbicacion.setBorder( BorderFactory.createEmptyBorder( 20, 2, 2, 2 ) );
		
		principal.add( texto, BorderLayout.NORTH );
		principal.add( panelUbicacion, BorderLayout.CENTER );
		principal.add( panelInferior, BorderLayout.SOUTH );
		
		add( principal, BorderLayout.CENTER );
		add( botones, BorderLayout.SOUTH );
	}
	
	public void actualizar( String txt, String txt2 )
	{
		txt = "<html><p align=\"justify\">" + txt.replace( "\n", "<br>" ) + "</html>";
		texto.setText( txt );
		
		txt2 = "<html><p align=\"justify\">" + txt2.replace( "\n", "<br>" ) + "</html>";
		texto2.setText( txt2 );
		
		fieldUbicacion.setText( InstallerConstants.DEFAULT_PATH + interfaz.getInstalador( ).getNombreApp( ) );
		fieldUbicacion.setCaretPosition( fieldUbicacion.getText( ).length( ) );
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
		
		but.setBackground( InstallerConstants.GRIS );
		but.setForeground( InstallerConstants.BLANCO );
	}
	
	@Override
	public void mouseExited( MouseEvent e )
	{
		JButton but = ( JButton ) e.getSource( );
		
		but.setBackground( InstallerConstants.BLANCO );
		but.setForeground( InstallerConstants.NEGRO );
	}
	
	private void abrirFileChooser( )
	{
		FileChooser fileChooser = new FileChooser( InstallerConstants.DEFAULT_PATH, FileChooser.DIRECTORIES_ONLY );
		// JFileChooser fc = new JFileChooser( ConstantesInstalador.DEFAULT_PATH );
		// fc.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );
		boolean res = fileChooser.mostrarDialogo( );
		if( res )
		{
			File f = fileChooser.getSelectedFile( );
			String field = f.getAbsolutePath( );
			if( !field.endsWith( interfaz.getInstalador( ).getNombreApp( ) ) )
			{
				field += "\\" + interfaz.getInstalador( ).getNombreApp( );
			}
			fieldUbicacion.setText( field );
		}
	}
	
	@Override
	public void actionPerformed( ActionEvent e )
	{
		String comando = e.getActionCommand( );
		
		if( comando.equals( InstallerConstants.EXPLORAR ) )
		{
			abrirFileChooser( );
		}
		else if( comando.equals( InstallerConstants.ATRAS ) )
		{
			interfaz.showPage( 2 );
		}
		else if( comando.equals( InstallerConstants.CANCELAR ) )
		{
			System.exit( 0 );
		}
		else if( comando.equals( InstallerConstants.INSTALAR ) )
		{
			interfaz.getInstalador( ).setPath( fieldUbicacion.getText( ) );
			interfaz.showPage( 4 );
		}
	}
}