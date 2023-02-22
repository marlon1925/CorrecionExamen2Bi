import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class v1 {
    private JTextField CedulaTxt;
    private JTextField nombreTxt;
    private JTextField celularTxt;
    private JTextField emailTxt;
    private JComboBox nivelBox;
    private JComboBox GeneroBox;
    private JPanel panel;
    private JButton crearButton;
    private JButton buscarButton;
    private JButton actualizarButton;
    private JButton borrarButton;
    private JButton resetButton;
    Statement st;
    PreparedStatement ps;
    Connection con;

    ResultSet rs;


    public v1(){


        try{
            con = getConection();
            st = con.createStatement();
            rs = st.executeQuery("SELECT Nivel FROM colegio.nivel");

            while (rs.next()){
                nivelBox.addItem(rs.getString("Nivel"));
            }
        }catch (HeadlessException | SQLException f){
            System.out.println(f);
        }
        try{
            con = getConection();
            st = con.createStatement();
            rs = st.executeQuery("SELECT GENERO FROM colegio.genero");

            while (rs.next()){
                GeneroBox.addItem(rs.getString("GENERO"));
            }
        }catch (HeadlessException | SQLException f){
            System.out.println(f);
        }
        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                con = getConection();

                try{

                    String nivel = nivelBox.getSelectedItem().toString();
                    String genero = GeneroBox.getSelectedItem().toString();

                    ps = con.prepareStatement("INSERT INTO colegio.persona(Id_est,Nom_est,Cel_est,Email_est, Nivel, Genero) VALUES (?,?,?,?,?,?)");

                    ps.setString(1, CedulaTxt.getText());
                    ps.setString(2, nombreTxt.getText());
                    ps.setString(3, celularTxt.getText());
                    ps.setString(4, emailTxt.getText());
                    ps.setString(5, nivel);
                    ps.setString(6, genero);


                    int res = ps.executeUpdate();

                    if(res > 0){
                        JOptionPane.showMessageDialog(null, "Si sirve");
                        CedulaTxt.setText("");
                        nombreTxt.setText("");
                        celularTxt.setText("");
                        emailTxt.setText("");
                    }else {
                        JOptionPane.showMessageDialog(null, "No sirve");
                    }

                }catch (HeadlessException | SQLException f){
                    System.out.println(f);
                }
            }
        });




        borrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                con = getConection();
                try {
                    ps = con.prepareStatement("DELETE FROM colegio.persona WHERE Id_est ="+CedulaTxt.getText());
                    int res = ps.executeUpdate();

                    if(res > 0 ){
                        JOptionPane.showMessageDialog(null,"Se elemino con exito");
                        CedulaTxt.setText("");
                    }else{
                        JOptionPane.showMessageDialog(null,"Error, datos invalidos!! ERROR !!");
                    }
                }catch (HeadlessException | SQLException f){
                    System.out.println(f);
                }
            }
        });




        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    con = getConection();
                    ps = con.prepareStatement("UPDATE colegio.persona SET Nom_est = ?, Cel_est = ?, Email_est = ? WHERE Id_est ="+CedulaTxt.getText() );
                    String nivel = nivelBox.getSelectedItem().toString();
                    String genero = GeneroBox.getSelectedItem().toString();
                    ps.setString(1, nombreTxt.getText());
                    ps.setString(2, celularTxt.getText());
                    ps.setString(3, emailTxt.getText());
                    ps.setString(4, nivel);
                    ps.setString(5, genero);


                    System.out.println(st);
                    int res = ps.executeUpdate();

                    if(res > 0 ){
                        JOptionPane.showMessageDialog(null,"La actualización se realizado con EXITO!");
                        CedulaTxt.setText("");
                        nombreTxt.setText("");
                        celularTxt.setText("");
                        emailTxt.setText("");
                    }else{
                        JOptionPane.showMessageDialog(null,"Error, datos invalidos!! ERROR !!");
                    }
                }catch (HeadlessException | SQLException f){
                    System.out.println(f);
                }

            }
        });


        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Connection con;

                try{
                    con = getConection();
                    st = con.createStatement();
                    ResultSet rs;
                    rs=st.executeQuery("select * from colegio.persona where Id_est="+CedulaTxt.getText()+";");
                    while (rs.next()){
                        nombreTxt.setText(rs.getString("Nom_est"));
                        celularTxt.setText(rs.getString("Cel_est"));
                        emailTxt.setText(rs.getString("Email_est"));
                        GeneroBox.setSelectedItem(rs.getString("Genero"));
                        nivelBox.setSelectedItem(rs.getString("Nivel"));

                    }
                }catch (Exception s){

                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CedulaTxt.setText("");
                nombreTxt.setText("");
                celularTxt.setText("");
                emailTxt.setText("");
            }
        });
    }
    public static Connection getConection(){
        Connection con = null;
        String base= "colegio";
        String url = "jdbc:mysql://localhost:3306/" + base;
        String user = "root";
        String password = "marlon";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url,user,password);
        }catch (ClassNotFoundException | SQLException e){
            System.err.println(e);
        }
        return con;
    }


    public static void main (String[]args){
        JFrame frame = new JFrame("Base Datos");
        frame.setContentPane(new v1().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(600,300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
