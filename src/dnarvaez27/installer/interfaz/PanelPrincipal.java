package dnarvaez27.installer.interfaz;

import java.net.URL;

import javax.swing.JPanel;

import java.awt.CardLayout;

import dnarvaez27.installer.recursos.InstallerConstants;

public class PanelPrincipal extends JPanel
{
	private static final long serialVersionUID = -1981226819238285452L;

	private PanelBienvenida panelBienvenida;

	private PanelContrato panelContrato;

	private PanelUbicacion panelUbicacion;

	private PanelInstalacion panelInstalacion;

	private CardLayout card;

	private InterfazInstalador interfaz;

	private int actual;

	public PanelPrincipal( InterfazInstalador interfaz )
	{
		setBackground( InstallerConstants.BLANCO );

		this.interfaz = interfaz;

		panelBienvenida = new PanelBienvenida( interfaz );

		panelContrato = new PanelContrato( interfaz );

		panelUbicacion = new PanelUbicacion( interfaz );

		panelInstalacion = new PanelInstalacion( interfaz );

		card = new CardLayout( );
		setLayout( card );

		add( panelBienvenida, "PanelBienvenida" );
		add( panelContrato, "PanelContrato" );
		add( panelUbicacion, "PanelUbicacion" );
		add( panelInstalacion, "PanelInstalacion" );
	}

	public int getActual( )
	{
		return actual;
	}

	public void show( int i )
	{
		String mensaje = "";
		URL iconPath = null;
		switch( i )
		{
			case 1:
				mensaje = interfaz.getInstalador( ).getMessageBienvenida( );
				iconPath = interfaz.getInstalador( ).getPathIcon( );
				panelBienvenida.actualizar( mensaje, iconPath );
				card.show( this, "PanelBienvenida" );
				actual = i;
				break;
			case 2:
				panelContrato.actualizar( "Contrato", interfaz.getInstalador( ).getContrato( ) );
				card.show( this, "PanelContrato" );
				break;
			case 3:
				mensaje = interfaz.getInstalador( ).getMessageUbicacion( );
				String mensaje2 = interfaz.getInstalador( ).getMessageUbicacion2( );
				panelUbicacion.actualizar( mensaje, mensaje2 );
				card.show( this, "PanelUbicacion" );
				actual = i;
				break;
			case 4:
				card.show( this, "PanelInstalacion" );
				panelInstalacion.actualizar( );
				actual = i;
				break;
		}
	}
}