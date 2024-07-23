package gamingclaus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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



public class CurrencyFrame extends JFrame implements ActionListener{
    private JPanel toppanel;
    private JLabel toplabel;
    private JPanel midPanel;
    private JPanel convertingPanel;
    private JLabel convertingLabel;
    private JLabel convertingLabel1;
    private JLabel convertingLabel2;
    private JComboBox convertingCurrencyBox;
    private JPanel convertedPanel;
    private JPanel leftPanel;
    private JPanel leftPanel1;
    private JPanel leftPanel2;
    private JLabel convertedLabel;
    private JLabel note;
    //private JComboBox convertedCurrecyBox;
    private CurrencyConvertApi currencyConvertApi;
    private JTextField converting_currency;
    private JTextField converted_currency;
    private JButton submit;



    CurrencyFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(650,650);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        currencyConvertApi = new CurrencyConvertApi();
        convertingCurrencyBox = new JComboBox<>();
        //convertedCurrecyBox = new JComboBox<>();

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
        midPanel.setLayout(new FlowLayout());
        midPanel.setBackground(new Color(0x202020));
        midPanel.setVisible(true);

        //----------------------Sub Panel for holding the Converting Components------------------------------------
       
     
        convertingPanel = new JPanel();
        convertingPanel.setLayout(new BoxLayout(convertingPanel, BoxLayout.Y_AXIS));
        convertingPanel.setPreferredSize(new Dimension(500, 500)); // Adjusted size for visibility
        convertingPanel.setOpaque(false);


        leftPanel = new JPanel();
        leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setOpaque(false);

        // Converting Label with HTML for line breaks and different font sizes
        convertingLabel = new JLabel("<html>STEP 1:<br><span style='font-size:10px'>Enter the amount you <br>want to convert to</span></html>");
        convertingLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        convertingLabel.setForeground(Color.white);

        note = new JLabel("<html><span style='font-size:9px'>Note:The following currency is default set to USD</span></html>");
        note.setFont(new Font("Arial", Font.PLAIN, 8));
        note.setForeground(Color.white);

        // Text Field with a more reasonable size
        converting_currency = new JTextField();
        converting_currency.setPreferredSize(new Dimension(100, 25)); // Increased size for better visibility

       
        // Add components to the leftPanel
        leftPanel.add(convertingLabel);
        leftPanel.add(converting_currency);
        leftPanel.add(note);


        //second part of the conversion layer 
        leftPanel1 = new JPanel();
        leftPanel1.setLayout(new FlowLayout((FlowLayout.LEFT)));
        leftPanel1.setBorder(new EmptyBorder(10,0,0,0));
        leftPanel1.setOpaque(false);

        //adding the second step for the conversion
        convertingLabel1 = new JLabel("<html>STEP 2:<br><span style='font-size:10px'>Select the currency which <br>you want to convert to</span></html>");
        convertingLabel1.setFont(new Font("Arial", Font.PLAIN, 20));
        convertingLabel1.setForeground(Color.white);

        // Submit Button
        submit = new JButton("Submit");
        submit.setFocusable(false);
        submit.addActionListener(this);
        submit.setFont(new Font("Calibri", Font.BOLD, 12));
 

        leftPanel1.add(convertingLabel1);
        leftPanel1.add(convertingCurrencyBox);
        leftPanel1.add(submit);

        //second part of the conversion layer 
        leftPanel2 = new JPanel();
        leftPanel2.setLayout(new FlowLayout((FlowLayout.LEFT)));
        leftPanel2.setBorder(new EmptyBorder(15,0,0,0));
        leftPanel2.setOpaque(false);

        //adding the second step for the conversion
        convertingLabel2 = new JLabel("<html>STEP 3:<br><span style='font-size:10px'>Result:</span></html>");
        convertingLabel2.setFont(new Font("Arial", Font.PLAIN, 20));
        convertingLabel2.setForeground(Color.white);
     

        converted_currency = new JTextField();
        converted_currency.setPreferredSize(new Dimension(400,20));
        converted_currency.setEditable(false);
        converted_currency.setOpaque(false);
        converted_currency.setFont(new Font("Calibri",Font.BOLD,15));
        converted_currency.setForeground(Color.CYAN);

        leftPanel2.add(convertingLabel2);
        leftPanel2.add(converted_currency);

        convertingPanel.add(leftPanel);
        convertingPanel.add(leftPanel1);
        convertingPanel.add(leftPanel2);



        //-----------------------------------------------------------------------------------------------

     

        midPanel.add(convertingPanel);

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
                    //convertedCurrecyBox.addItem(currencykey);
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }

    }








    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==submit){
            try {
                ConvertingCurrencyHanlder();
            } catch (IOException e) {
                
            } catch (InterruptedException e) {
    
            }
        }
    }

    private void ConvertingCurrencyHanlder() throws IOException, InterruptedException{
        try {
                String currentcurrencyname = (String) convertingCurrencyBox.getSelectedItem();
                double currentconvertingrate = Double.valueOf(converting_currency.getText());
                float apicurrencyfetched =(float) currencyConvertApi.getConversionRates(currentcurrencyname);

                converted_currency.setText(currentconvertingrate*apicurrencyfetched +" " +currentcurrencyname);
            } 
        catch (NumberFormatException e) {
            System.out.println("Invalid Type!! Try Putting on Numbers.");
            converting_currency.setText(null);
        }
    }





}
