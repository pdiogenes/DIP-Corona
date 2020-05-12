
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 *
 * @author Pedro
 */
public class MainMenu extends javax.swing.JFrame {

    JButton btnZoomIn, btnZoomOut, btnSelectSample, btnProcess;
    MainImage mainImage;
    HistogramImage histogramImage;
    ActionListener actionListener = new ActionListener() {

        /*
         * handling button functionality
         */
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnZoomIn) {

            } else if (e.getSource() == btnZoomOut) {

            } else if (e.getSource() == btnSelectSample) {

            } else if (e.getSource() == btnProcess) {

            }
        }
    };

    /**
     * Creates new form MainMenu
     */
    public MainMenu() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAbrirImagem = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnAbrirImagem.setText("Abrir Imagem");
        btnAbrirImagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirImagemActionPerformed(evt);
            }
        });

        jLabel1.setText("Trabalho PID");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAbrirImagem)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel1)))
                .addContainerGap(88, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(63, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAbrirImagem)
                .addGap(58, 58, 58))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAbrirImagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirImagemActionPerformed
        JFileChooser fc = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("jpg, png, tiff", "jpg", "png", "tiff");
        fc.setFileFilter(filter);
        int result = fc.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            BufferedImage img = null;
            try {
                // ler imagem em um BufferedImage
                img = ImageIO.read(file);
                this.show_image(img);
                // cria nova exibição de imagem
                this.dispose();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnAbrirImagemActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrirImagem;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables

    /*public void show_image(BufferedImage img) {
        // create main frame
        JFrame frame = new JFrame("Swing Paint");
        Container content = frame.getContentPane();
        // set layout on content pane
        content.setLayout(new BorderLayout());
        // create draw area
        mainImage = new MainImage(img);
        histogramImage = new HistogramImage();


        // add to content pane
        content.add(mainImage, BorderLayout.CENTER);
        content.add(histogramImage, BorderLayout.SOUTH);

        // create controls to apply colors and call clear feature
        JPanel controls2 = new JPanel();

        controls2.setLayout(new BoxLayout(controls2, BoxLayout.X_AXIS));

        // create panel
        // createPanel(controls);
        createPanel2(controls2);

        // add to content pane
        // content.add(controls, BorderLayout.NORTH);
        content.add(controls2, BorderLayout.NORTH);

        frame.setSize(800, 500);
        // can close frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // show the swing paint result
        frame.setVisible(true);

        // Now you can try our Swing Paint !!! Enjoy :D
    }*/
    
    public void show_image(BufferedImage img) {
        // create main frame
        JFrame frame = new JFrame("Swing Paint");
        JPanel controls = new JPanel();

        controls.setLayout(new BoxLayout(controls, BoxLayout.X_AXIS));
        createPanel(controls);
        
        
        mainImage = new MainImage(img);
        histogramImage = new HistogramImage();
        
        JPanel images = new JPanel();
        images.setLayout(new BoxLayout(images, BoxLayout.LINE_AXIS));
        images.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        images.add(mainImage);
        images.add(Box.createRigidArea(new Dimension(0, 10)));
        images.add(histogramImage);
        
        Container content = frame.getContentPane();
        content.add(controls, BorderLayout.NORTH);
        content.add(images, BorderLayout.CENTER);

        frame.setSize(800, 500);
        // can close frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // show the swing paint result
        frame.setVisible(true);
        
    }

    /*
     * handles button listeners and adds them to the control pane
     */
    private void createPanel(JPanel controls) {
        btnZoomIn = new JButton("Zoom In");
        btnZoomIn.addActionListener(actionListener);
        btnZoomOut = new JButton("Zoom Out");
        btnZoomOut.addActionListener(actionListener);
        btnSelectSample = new JButton("Select Sample");
        btnSelectSample.addActionListener(actionListener);
        btnProcess = new JButton("Find Viruses");
        btnProcess.addActionListener(actionListener);

        controls.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        controls.add(btnZoomIn);
        controls.add(btnZoomOut);
        controls.add(btnSelectSample);
        controls.add(btnProcess);
    }

}
