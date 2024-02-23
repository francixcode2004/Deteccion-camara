package Controller;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import view.view_detecion;
public class view_controller implements ActionListener {
	private view_detecion vd;
	public view_controller(view_detecion vd) {
		super();
		this.vd = vd;
		this.vd.btn_escoger.addActionListener(this);
		this.vd.btn_img.addActionListener(this);
		this.vd.btn_cerrar.addActionListener(this);
		this.vd.btn_guardar.addActionListener(this);
		vd.btn_guardar.setEnabled(false);
		detectar_camara();

	}


	private void escoger_imagen() {
		cerrar_camara();
		Webcam webcam = Webcam.getDefault();
	    JFileChooser fileChooser = new JFileChooser();
	    fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	    FileNameExtensionFilter imgFilter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif", "png"); 
	    fileChooser.setFileFilter(imgFilter);
	    int result = fileChooser.showOpenDialog(null);
	    
	    if (result != JFileChooser.CANCEL_OPTION) {
	        File fileName = fileChooser.getSelectedFile();

	        if ((fileName == null) || (fileName.getName().equals(""))) {
	            JOptionPane.showMessageDialog(vd, "No se ha seleccionado ninguna imagen");
	        } else {
	            try {
	                ImageIcon originalIcon = new ImageIcon(fileName.getAbsolutePath());
	                
	                // Verifica si la cámara tiene la imagen invertida y ajusta la imagen seleccionada
	                if (webcam.getViewSize().height < 0) {
	                    originalIcon.setImage(originalIcon.getImage().getScaledInstance(-1, originalIcon.getIconHeight(), Image.SCALE_DEFAULT));
	                }
	                
	                Image originalImage = originalIcon.getImage();
	                Image scaledImage = originalImage.getScaledInstance(vd.lbl_imagen.getWidth(), vd.lbl_imagen.getHeight(), Image.SCALE_SMOOTH);
	                ImageIcon imagen = new ImageIcon(scaledImage);

	                vd.lbl_nombre_img.setText(fileName.getName());
	                vd.lbl_imagen.setIcon(imagen);
	                vd.lbl_imagen.setHorizontalAlignment(JLabel.CENTER);
	                vd.lbl_imagen.setVerticalAlignment(JLabel.CENTER);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}



	private void detectar_camara() {
	    try {
	        Webcam webcam = Webcam.getDefault();

	        if (webcam != null) {
	            vd.lbl_nombre.setText(webcam.getName());
	        } else {
	            vd.lbl_nombre.setText("No se ha detectado ninguna cámara");
	        }
	    } catch (NullPointerException e) {
	        vd.lbl_nombre.setText("Error al detectar cámara: " + e.getMessage());
	    }
	}

	private void abrir_camara() {
		vd.btn_guardar.setEnabled(true);
		Webcam webcam = Webcam.getDefault();
		webcam.setViewSize(WebcamResolution.VGA.getSize());
		WebcamPanel panel = new WebcamPanel(webcam,true);
		panel.setSize(webcam.getViewSize());
		panel.setFPSDisplayed(false);
		panel.setDisplayDebugInfo(false);
		panel.setImageSizeDisplayed(false);
		panel.setMirrored(true);
		panel.setBounds(0, 0, vd.pn_img.getWidth(), vd.pn_img.getHeight());
		panel.setVisible(true);
		vd.pn_img.add(panel);
	}
	private void cerrar_camara() {
		vd.btn_guardar.setEnabled(false);
		Webcam webcam = Webcam.getDefault();
		webcam.close();
		vd.pn_img.setBackground(Color.BLACK);
	}
	private void guardar_imagen() {
		Webcam webcam = Webcam.getDefault();
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Guardar Imagen");
		int userSelection = fileChooser.showSaveDialog(null);
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File archivo = fileChooser.getSelectedFile();
			String ruta = archivo.getAbsolutePath();
			if (!ruta.toLowerCase().endsWith(".png")) {
				archivo = new File(ruta + ".png");
			}
			try {
				ImageIO.write(webcam.getImage(), "PNG", archivo);
				JOptionPane.showMessageDialog(vd, "Imagen guardada en: " + archivo.getAbsolutePath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==vd.btn_escoger) {
			escoger_imagen();
		}else if(e.getSource()==vd.btn_img){
			abrir_camara();	
		}else if(e.getSource()==vd.btn_cerrar) {
			cerrar_camara();
		}else if(e.getSource()==vd.btn_guardar) {
			guardar_imagen();
		}
	}
}
