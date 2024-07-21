package gamingclaus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import java.util.List;



public class CurrencyFrame extends JFrame{
    private JPanel toppanel;
    private JLabel toplabel;
    private JPanel midPanel;
    private JPanel convertingPanel;
    private JLabel convertingLabel;
    private JPanel convertedPanel;
    private JLabel convertedLabel;
    private JComboBox convertingCurrencyBox;
    private JComboBox convertedCurrencyBox;
    private CurrencyConvertApi currencyConvertApi;
    private JTextField converting_currency;
    private JTextField converted_currency;


    CurrencyFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(650,650);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        currencyConvertApi = new CurrencyConvertApi();
        convertingCurrencyBox = new JComboBox<>();
        convertedCurrencyBox = new JComboBox<>();
        new CurrencyApiThread().execute();
       
        
        toppanel = new JPanel();
        toppanel.setLayout(new BorderLayout());
        toppanel.setPreferredSize(new Dimension(10,60));
        toppanel.setBackground(new Color(0x202020));
        toppanel.setBorder(BorderFactory.createDashedBorder(Color.green));
        toppanel.setVisible(true);

        ImageIcon origialIcon = new ImageIcon("D:\\CurrrencyConverter\\currencyconverter\\src\\main\\java\\gamingclaus\\greendollar.png");
        Image image = origialIcon.getImage();
        Image resizeImage = image.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(resizeImage); // this has the resized image from the origial icon


        toplabel = new JLabel("CURRENCY CONVERTER",SwingConstants.CENTER); // centers the label to the center within the text itself
        toplabel.setIcon(imageIcon);
        toplabel.setFont(new Font("Calibri",Font.BOLD,28));
        toplabel.setForeground(new Color(0x779933));


        toppanel.add(toplabel,BorderLayout.CENTER); // makes the toplabel in the center

        midPanel = new JPanel();
        midPanel.setBorder(new EmptyBorder(20, 0, 0, 0));
        midPanel.setLayout(new BoxLayout(midPanel,BoxLayout.Y_AXIS));
        midPanel.setBackground(new Color(0x202020));
        midPanel.setVisible(true);

        //----------------------Sub Panel for holding the Converting Components------------------------------------
       

        convertingPanel = new JPanel();
        convertingPanel.setLayout(new FlowLayout());
        convertingPanel.setPreferredSize(new Dimension(0,0));
        convertingPanel.setOpaque(false);

        convertingLabel = new JLabel("Converting Currency: ",SwingConstants.CENTER);
        convertingLabel.setFont(new Font("Arial",Font.PLAIN,20));
        convertingLabel.setForeground(Color.white);

        converting_currency = new JTextField();
        converting_currency.setPreferredSize(new Dimension(70,25));

        convertingPanel.add(convertingLabel);
        convertingPanel.add(converting_currency);
        convertingPanel.add(convertingCurrencyBox);
       
       
        //-----------------------------------------------------------------------------------------------

        //-------------------------Sub Panel for holding the Converted Components--------------------------------

      

        convertedPanel = new JPanel();
        convertedPanel.setLayout(new FlowLayout());
        convertedPanel.setPreferredSize(new Dimension(80,30));
        convertedPanel.setOpaque(false);

        convertedLabel = new JLabel("Converted Currency: ",SwingConstants.CENTER);
        convertedLabel.setFont(new Font("Arial",Font.PLAIN,20));
        convertedLabel.setForeground(Color.white);


        converted_currency = new JTextField();
        converted_currency.setPreferredSize(new Dimension(70,25));

        convertedPanel.add(convertedLabel);
        convertedPanel.add(converted_currency);
        convertedPanel.add(convertedCurrencyBox);

        //-------------------------------------------------------------------------------------------


        midPanel.add(convertingPanel);
        midPanel.add(convertedPanel);

        this.add(toppanel,BorderLayout.NORTH);
        this.add(midPanel,BorderLayout.CENTER);
        this.getContentPane().setBackground(new Color(0x242526));
        this.setVisible(true);
    }




    private class CurrencyApiThread extends SwingWorker<List<String> , Void>{


        @Override
        protected List<String> doInBackground() throws Exception{
            return currencyConvertApi.CurrencyValueKeys();
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void done(){
            try{
                List<String> currencykeys = get();
                for(String currencykey : currencykeys){
                    convertingCurrencyBox.addItem(currencykey);
                    convertedCurrencyBox.addItem(currencykey);
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }

    }






}