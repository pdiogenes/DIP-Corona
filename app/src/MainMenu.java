
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import processing.Threshold;
import utilities.Grayscale;
import utilities.MatConversion;

/**
 *
 * @author Pedro
 */
public class MainMenu extends javax.swing.JFrame {

    MainImage mainImage;
    HistogramImage histogramImage;
    JPanel sampleArea;
    JLabel imgLabel;
    Mat sample, sampleT;
    BufferedImage image, sampleImage;
    Mat img;
    int imgw, imgh;
    JFrame imageFrame = null;
    int threshValue;

    public MainMenu() {
        initComponents();
        loadLibraries();
        sample = new Mat();
        sampleT = new Mat();
        img = new Mat();
    }

    public static void loadLibraries() {
        try {
            InputStream in = null;
            File fileOut = null;
            String osName = System.getProperty("os.name");
            String opencvpath = System.getProperty("user.dir");
            if (osName.startsWith("Windows")) {
                int bitness = Integer.parseInt(System.getProperty("sun.arch.data.model"));
                if (bitness == 32) {
                    opencvpath = opencvpath + "\\opencv\\x86\\";
                } else if (bitness == 64) {
                    opencvpath = opencvpath + "\\opencv\\x64\\";
                } else {
                    opencvpath = opencvpath + "\\opencv\\x86\\";
                }
            } else if (osName.equals("Mac OS X")) {
                opencvpath = opencvpath + "Your path to .dylib";
            }
            System.load(opencvpath + Core.NATIVE_LIBRARY_NAME + ".dll");
        } catch (Exception e) {
            throw new RuntimeException("Failed to load opencv native library", e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        lblGuide = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuAbrirImg = new javax.swing.JMenuItem();
        menuHist = new javax.swing.JMenu();
        menuCreate = new javax.swing.JMenuItem();
        menuProc = new javax.swing.JMenu();
        menuFind = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PID-Corona");

        lblGuide.setText("Seleciona uma imagem em Arquivo > Abrir Imagem");

        jMenu1.setText("Arquivo");

        menuAbrirImg.setText("Abrir Imagem");
        menuAbrirImg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAbrirImgActionPerformed(evt);
            }
        });
        jMenu1.add(menuAbrirImg);

        jMenuBar1.add(jMenu1);

        menuHist.setText("Histogram");
        menuHist.setActionCommand("Zoom");
        menuHist.setEnabled(false);

        menuCreate.setText("Create Histogram");
        menuCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCreateActionPerformed(evt);
            }
        });
        menuHist.add(menuCreate);

        jMenuBar1.add(menuHist);

        menuProc.setText("Processar");
        menuProc.setEnabled(false);

        menuFind.setText("Achar Virus");
        menuFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFindActionPerformed(evt);
            }
        });
        menuProc.add(menuFind);

        jMenuBar1.add(menuProc);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addGap(72, 72, 72).addComponent(lblGuide).addContainerGap(74,
                        Short.MAX_VALUE)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addGap(30, 30, 30).addComponent(lblGuide).addContainerGap(33,
                        Short.MAX_VALUE)));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuCreateActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_menuCreateActionPerformed
        this.show_histogram();
    }// GEN-LAST:event_menuCreateActionPerformed

    private void menuFindActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_menuFindActionPerformed
        // mainImage.test();
        this.show_threshold();
    }// GEN-LAST:event_menuFindActionPerformed

    private void menuAbrirImgActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItem3ActionPerformed
        JFileChooser fc = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("Imagens Permitidas", "jpg", "png", "tiff");
        fc.setFileFilter(filter);
        // fc.setCurrentDirectory(new File("./src/images"));
        int result = fc.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            Mat image = Imgcodecs.imread(file.getPath());
            Mat imageBW = new Mat(); 
            Imgproc.cvtColor(image, imageBW, Imgproc.COLOR_RGB2GRAY);
            imgw = imageBW.width();
            imgh = imageBW.height();
            this.img = imageBW;
            this.show_image();
        }
    }// GEN-LAST:event_jMenuItem3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
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
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JLabel lblGuide;
    private javax.swing.JMenuItem menuAbrirImg;
    private javax.swing.JMenuItem menuCreate;
    private javax.swing.JMenuItem menuFind;
    private javax.swing.JMenu menuHist;
    private javax.swing.JMenu menuProc;
    // End of variables declaration//GEN-END:variables

    public void show_image() {
        // checks if theres already one opened
        if (imageFrame != null) {
            imageFrame.dispose();
        }
        // create main frame
        imageFrame = new JFrame("Imagem");
        imageFrame.getContentPane().setLayout(null);
        imageFrame.setSize(imgw + 500, imgh + 80);

        // create class for main image
        mainImage = new MainImage(this.img, this);

        // adds instructions
        JLabel instructions = new JLabel("Scroll to zoom, click and drag on the image to select a sample");
        instructions.setBounds(10, imgh + 25, imageFrame.getWidth(), 20);
        imageFrame.getContentPane().add(instructions);

        // creates a panel in which the sample image will be drawn
        sampleArea = new JPanel();
        sampleArea.setBounds(imgw + 50, 10, imgw, imgh);
        imageFrame.getContentPane().add(sampleArea);

        // adds m ain image to container

        mainImage.setBounds(10, 10, imgw, imgh);
        imageFrame.getContentPane().add(mainImage);
        imageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // shows main image frame
        imageFrame.setVisible(true);
        imageFrame.setResizable(false);

        // enables the option to show the image's histogram
        menuHist.setEnabled(true);
        lblGuide.setText("Abra o hisograma da imagem em Histogram > Draw Histogram");

        // enables the option to process the image
        menuProc.setEnabled(true);

    }

    public void show_histogram() {
        // creates a frame for the histogram
        JFrame histFrame = new JFrame("Histograma");
        histogramImage = new HistogramImage(this.image);

        // creates a panel for the histogram to be drawn on
        JPanel histogram = new JPanel();
        histogram.setLayout(new BoxLayout(histogram, BoxLayout.LINE_AXIS));
        histogram.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        histogram.add(histogramImage);
        Container hcontent = histFrame.getContentPane();

        // creates the histogram chart and adds it to the panel
        hcontent.add(histogramImage.createChart(this.image), BorderLayout.CENTER);

        // sets the screen size
        histFrame.setSize(1100, 900);

        // opens the frame
        histFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        histFrame.setVisible(true);
    }

    public void show_threshold() { // https://docs.opencv.org/3.4/db/d8e/tutorial_threshold.html
        // creating the frame for threshold value selection
        JFrame thresh = new JFrame("Threshold");
        JPanel slider = new JPanel();
        slider.setLayout(new BoxLayout(slider, BoxLayout.PAGE_AXIS));
        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainImage.setSampleMat(sampleT);
                thresh.dispose();
            }

        });

        // adding labels
        slider.add(new JLabel("Valor do Limiar"));
        // Create Trackbar to choose Threshold value
        JSlider sliderThreshValue = new JSlider(0, 255, 0);
        sliderThreshValue.setMajorTickSpacing(50);
        sliderThreshValue.setMinorTickSpacing(10);
        sliderThreshValue.setPaintTicks(true);
        sliderThreshValue.setPaintLabels(true);
        // adds the slider to the frame
        slider.add(sliderThreshValue);

        // get the OpenCV matrix for the sample iamge
        try {
            sample = MatConversion.BufferedImage2Mat(Grayscale.getGray(sampleImage));
        } catch (IOException e) {
        }

        // changes threshold depending on slider value
        sliderThreshValue.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                threshValue = source.getValue();
                sampleT = Threshold.threshold(sample, threshValue);
                sampleImage = (BufferedImage) HighGui.toBufferedImage(sampleT);
                imgLabel.setIcon(new ImageIcon(sampleImage));
                thresh.repaint();
            }
        });

        thresh.getContentPane().add(slider, BorderLayout.PAGE_START);
        imgLabel = new JLabel(new ImageIcon(HighGui.toBufferedImage(sample)));
        thresh.getContentPane().add(imgLabel, BorderLayout.CENTER);
        thresh.getContentPane().add(btnConfirmar, BorderLayout.PAGE_END);

        // sets the window size
        int windowW = Math.max(400, sample.width() * 2);
        int windowH = Math.max(400, sample.height() * 2 + 100);

        thresh.setSize(windowW, windowH);
        thresh.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        thresh.setVisible(true);
    }

    public void drawSample(BufferedImage area) {
        Graphics g = sampleArea.getGraphics();
        sampleImage = area;
        g.clearRect(0, 0, imgw, imgh);
        g.drawImage(area, 0, 0, null);
    }

}
