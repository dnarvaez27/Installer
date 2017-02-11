package dnarvaez27.installer.interfaz;

import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import dnarvaez27.installer.recursos.InstallerConstants;
import dnarvaez27.installer.recursos.InstallerUtilities;

public class PanelBienvenida extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 6240401617910614655L;

	private JLabel texto;

	private JButton boton;

	private InterfazInstalador interfaz;

	public PanelBienvenida( InterfazInstalador interfaz )
	{
		this.interfaz = interfaz;

		setLayout( new BorderLayout( ) );

		JPanel principal = new JPanel( );
		principal.setLayout( new BorderLayout( ) );
		principal.setBackground( InstallerConstants.BLANCO );
		principal.setBorder( BorderFactory.createEmptyBorder( 5, 5, 5, 5 ) );

		texto = new JLabel( );
		texto.setFont( new Font( "Caviar Dreams", Font.BOLD, 14 ) );
		texto.setHorizontalAlignment( SwingConstants.CENTER );
		texto.setForeground( InstallerConstants.NEGRO );

		JPanel panelBoton = new JPanel( );
		panelBoton.setBackground( null );

		boton = new JButton( );
		boton.setContentAreaFilled( false );
		boton.setOpaque( true );
		boton.setBackground( null );
		boton.setFocusPainted( false );
		boton.setBorderPainted( false );
		boton.setCursor( InstallerConstants.HAND );
		boton.addActionListener( this );

		panelBoton.add( boton );

		principal.add( texto, BorderLayout.NORTH );
		principal.add( panelBoton, BorderLayout.CENTER );
		add( principal, BorderLayout.CENTER );
	}

	public void actualizar( String txt, URL iconPath )
	{
		txt = "<html>" + txt.replace( "\n", "<br>" ) + "<br><center><h4>Haz Click en el Icono para continuar</h4></center>";
		texto.setText( txt );

		try
		{
			BufferedImage bi = ImageIO.read( iconPath );
			bi = InstallerUtilities.resize( bi, 250, 250 );
			ImageIcon icon = new ImageIcon( bi );
			boton.setIcon( icon );
		}
		catch( Exception e )
		{
			e.printStackTrace( );
		}
	}

	@Override
	public void actionPerformed( ActionEvent e )
	{
		interfaz.showPage( 2 );
	}
}