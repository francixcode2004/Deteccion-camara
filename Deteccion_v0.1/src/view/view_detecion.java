package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.view_controller;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
public class view_detecion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public final JLabel lbl_nombre = new JLabel("camara en uso");
	public JLabel lbl_imagen;
	public JButton btn_img;
	public JLabel lbl_nombre_img;
	public JButton btn_cerrar;
	public JPanel pn_img;
	public JButton btn_escoger;
	public JButton btn_guardar;
	
	
	/**
	 * Launch the application.
	 */
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					view_detecion frame = new view_detecion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public view_detecion() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(view_detecion.class.getResource("/img/camara-reflex-digital.png")));
		setTitle("Rostros");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 616, 397);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lbl_imagen = new JLabel("");
		lbl_imagen.setIcon(new ImageIcon(view_detecion.class.getResource("/img/fot de perfil.png")));
		lbl_imagen.setBackground(new Color(0, 0, 0));
		lbl_imagen.setBounds(364, 46, 226, 218);
		contentPane.add(lbl_imagen);
		
		btn_img = new JButton("Iniciar Camara");
		btn_img.setBounds(10, 285, 148, 23);
		contentPane.add(btn_img);
		lbl_nombre.setBounds(10, 0, 330, 31);
		contentPane.add(lbl_nombre);
		
		btn_escoger = new JButton("Escoger Imagen");
		btn_escoger.setBounds(452, 275, 138, 23);
		contentPane.add(btn_escoger);
		
		lbl_nombre_img = new JLabel("Nombre de la imagen:");
		lbl_nombre_img.setBounds(393, 21, 197, 14);
		contentPane.add(lbl_nombre_img);
		
		btn_cerrar = new JButton("Cerrar Camara");
		btn_cerrar.setBounds(10, 324, 148, 23);
		contentPane.add(btn_cerrar);
		
		pn_img = new JPanel();
		pn_img.setBackground(new Color(0, 0, 0));
		pn_img.setBounds(10, 49, 344, 215);
		contentPane.add(pn_img);
		
		btn_guardar = new JButton("Guardar");
		btn_guardar.setBounds(452, 324, 138, 23);
		contentPane.add(btn_guardar);
		new view_controller(this);
	}
}
