package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import views.Principal;

/**
 *
 * @author INSS
 */
public class PrincipalActionListener implements ActionListener {

    private Principal frame;
    public String data;
    public int resultadoJanelas;
    public String EnderecoArquivo, EnderecoTemporario, bufferIn;
    public ArrayList<String> Numero = new ArrayList<>();
    public ArrayList<String> Identificador = new ArrayList<>();
    public ArrayList<String> Operador = new ArrayList<>();
    public ArrayList<String> Separador = new ArrayList<>();
    public ArrayList<String> Palavra = new ArrayList<>();

    public PrincipalActionListener(Principal frame) {
        this.frame = frame;
    }

    public PrincipalActionListener() {

    }

    private static final String ARQUIVO1 = "jogador1.txt";

    private void escreverJogador1(String date) throws IOException {

        FileWriter fileWriter = new FileWriter(ARQUIVO1, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        String data = (new java.util.Date()).toString();
        String msg = "\n" + date;
        bufferedWriter.write(msg);
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    public static String lerJogador1() throws IOException {
        FileReader fileReader = new FileReader(ARQUIVO1);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String linha = null;
        while (bufferedReader.ready()) {
            linha = bufferedReader.readLine();
            //System.out.println( linha );
        }

        bufferedReader.close();
        return linha;

    }

    private void AbrirArquivo() {
        try {
            FileReader fileReader = new FileReader(EnderecoArquivo);
            BufferedReader reader = new BufferedReader(fileReader);
            while ((data = reader.readLine()) != null) {
                frame.Area_Texto.append(data + "\n");
            }
            fileReader.close();
            reader.close();
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
    }

    private void SalvarArquivo() {
        try {
            FileWriter escrever;
            escrever = new FileWriter(new File(EnderecoArquivo));
            escrever.write(frame.Area_Texto.getText());
            escrever.close();
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }

    }

    private void Fechar() {
        String message = "Tem certeza que deseja sair do analisador?";
        String title = "Confirmação";
        int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            System.exit(0);
        }

    }

    private void MenssagemPercaDados() {
        String message = "Você vai perder os dados na caixa de texto, deseja prosseguir?";
        String title = "Confirmação";
        int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            frame.Area_Texto.setText("");
            frame.Area_Texto.setText("  Begin (int);"
                    + "\n       " + "var"
                    + "  " + " x,i,z:inteiro"
                    + "\n     " + "begin"
                    + "\n       " + "escreva('Digite o numero: ');"
                    + "\n       " + "leia(x);"
                    + "\n       " + "escreva('Digite o numero: ')"
                    + "\n       " + "leia(i);"
                    + "\n       " + "escreva('Resultado: ');"
                    + "\n     " + "fim"
                    + "\n" + "  End");
        }
    }
 /**
Metodo que evalua nuestro caracter si existe y nos retorna
verdadero para los separadores
y
falso para los operadores
*/
public static boolean Caracter(char c){
	if(c=='(') return true;
	else if(c==')')return true;
	else if(c=='<')return false;
	else if(c=='>')return false;
	else return false;
}

/**
retornamos nuestro caracter de operador
*/
public static char Operador(char c){
	char car=' ';
	if(c=='<')car='<';
	else if(c=='>')car='>';
        else if(c=='=')car='=';
//        else if(c=='<>')car='<>';
	return car;
}

/**
retornamos nuestro caracter de separador
*/
public static char Separador(char c){
	char car=' ';
	if(c=='(') car='(';
	else if(c==')')car=')';
        else if(c=='}')car='}';
        else if(c=='{')car='{';
	return car;
}

public static boolean FinalLine(char c){
	
	if(c=='\n') return true;
	else return false;
}


public static boolean palavraReservada(String cad){
	//AQUI FICA A TABELA DE PALAVRAS RESERVADAS
	if(cad.equalsIgnoreCase("Begin")) return true;
        else if(cad.equalsIgnoreCase("begin")) return true;
	else if(cad.equalsIgnoreCase("if")) return true;
	else if(cad.equalsIgnoreCase("fim")) return true;
	else if(cad.equalsIgnoreCase("var")) return true;
	else if(cad.equalsIgnoreCase("case")) return true;
        else if(cad.equalsIgnoreCase("of")) return true;
	else if(cad.equalsIgnoreCase("then")) return true;
	else if(cad.equalsIgnoreCase("do")) return true;
	else if(cad.equalsIgnoreCase("while")) return true;
	else if(cad.equalsIgnoreCase("senao"))return true;
	else if(cad.equalsIgnoreCase("read"))return true;
	else if(cad.equalsIgnoreCase("write")) return true;
	else if(cad.equalsIgnoreCase("procedure")) return true;
	else if(cad.equalsIgnoreCase("call")) return true;
	else if(cad.equalsIgnoreCase("fim"))return true;
        else if(cad.equalsIgnoreCase("end"))return true;
        else if(cad.equalsIgnoreCase("#"))return true;
        else if(cad.equalsIgnoreCase("##"))return true;
	else return false;
}


    @SuppressWarnings({})
    private void Analise() throws IOException {

        EnderecoTemporario = System.getProperty("java.io.tmpdir") + "temp.txt";
        File arquivo = new File(EnderecoTemporario);
        FileWriter fw = new FileWriter(arquivo);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(frame.Area_Texto.getText());
        bw.flush();
        bw.close();

        try {
            DataInputStream in = new DataInputStream(new FileInputStream(EnderecoTemporario));//leemos nuestro archivo de entrada
            try {
                while ((bufferIn = in.readLine()) != null) {//mientras no lleguemos al fin del archivo...
                    int i = 0;
                    String cad = bufferIn.trim();
                    //eliminamos los espacios en blanco al incio o al final (pero no a la mitad)
                    while (i < cad.length()) {//recorremos la línea
                        char t = cad.charAt(i);//vamos leyendo caracter por caracter
                        if (Character.isDigit(t)) {//comprobamos si es un digito
                            String ora = "";
                            ora += t;
                            int j = i + 1;
                            while (Character.isDigit(cad.charAt(j))) {
                                //mientras el siguiente elemento sea un numero
                                ora += cad.charAt(j);//concatenamos
                                j++;
                                if (j == cad.length()) {
                                    break;//rompemos si llegamos al final de la línea
                                }
                            }
                            i = j;//movemos a nuestra variable i en la cadena
                            System.out.println("Número-->" + ora);
                            Numero.add(ora);
                            continue;//pasamos al siguiente elemento
                        }//end if si es Digito
                        else if (Character.isLetter(t)) {//comprobamos si es una letra
                            String ora = "";
                            ora += t;
                            int j = i + 1;
                            while (Character.isLetterOrDigit(cad.charAt(j))) {
  							 //mientras el siguiente elemento sea una letra o un digito
                                //ya que las variables pueden ser con numeros
                                ora += cad.charAt(j);
                                j++;
                                if (j == cad.length()) {
                                    break;
                                }
                            }
                            i = j;
                            if (palavraReservada(ora)) {//comprobamos si es una palabra reservada
                                System.out.println("Palabra reservada=" + ora);
                                Palavra.add(ora);
                            } else {//caso contrario es un identificador o variable
                                System.out.println("Identificador-->" + ora);
                                Identificador.add(ora);
                            }
                            continue;
                        }//end if si es variable
                        else if (!Character.isLetterOrDigit(t)) {
                            if(FinalLine(t)){
                              
                                System.out.println("Fim de linha..");
                            
                            }

                             //si no es letra ni digito entonces...
                            else if (Caracter(t)) {//¿es separador?
                                System.out.println("Separador-->" + Separador(t));
                                String c = "" + Separador(t);
                                Separador.add(c);
                            } else {//¿o es un operador?
                                System.out.println("Operador-->" + Operador(t));
                                String c = "" + Operador(t);
                                Operador.add(c);
                            }
                            i++;
                            continue;
                        }//end if si es diferente de letra y digito
                    }
                }//end while
            } catch (IOException e) {
            }
        } catch (FileNotFoundException e) {
        }

    }

    
    private void jMenuItem5ActionPerformed() {										  

  		try{
		   Analise();
		   DefaultTableModel var = (DefaultTableModel) frame.Tabela.getModel();
                  // var.setNumRows(0);
                   
                   
		   for(int i=0;i<Palavra.size();i++){
			   var.addRow(new String[]{Palavra.get(i)});
		   }
		   for(int i=0;i<Identificador.size();i++){
			   var.addRow(new String[]{null,Identificador.get(i)});
		   }
		   for(int i=0;i<Separador.size();i++){
			   var.addRow(new String[]{null,null,Separador.get(i)});
		   }
		   for(int i=0;i<Operador.size();i++){
			   var.addRow(new String[]{null,null,null,Operador.get(i)});
		   }
		   for(int i=0;i<Numero.size();i++){
			   var.addRow(new String[]{null,null,null,null,Numero.get(i)});
		   }

		  // var.addRow(new String[]{palavra,identificador,"oi",Numero,"oi","oi,","oii"});

  		}
  		catch(Exception e){
		   JOptionPane.showMessageDialog(null, "Houve um erro: "+e.getMessage());
  		}
  	 }  
    
    
    public void actionPerformed(ActionEvent e) {

        if ("Abrir".equals(e.getActionCommand())) {

            JFileChooser openFile = new JFileChooser();
            openFile.showOpenDialog(openFile);
            resultadoJanelas = JFileChooser.OPEN_DIALOG;

            if (JFileChooser.APPROVE_OPTION == resultadoJanelas) {
                EnderecoArquivo = openFile.getSelectedFile().toString();
                AbrirArquivo();
            }

        } else if ("Salvar".equals(e.getActionCommand())) {

//            JOptionPane.showMessageDialog(null, "Teste");
            JFileChooser saveFile = new JFileChooser();
            saveFile.showSaveDialog(saveFile);
            resultadoJanelas = JFileChooser.OPEN_DIALOG;
            if (JFileChooser.APPROVE_OPTION == resultadoJanelas) {
                EnderecoArquivo = saveFile.getSelectedFile().toString();
                SalvarArquivo();
                JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
            }

        } else if ("Sair".equals(e.getActionCommand())) {

            Fechar();

        } else if ("Analisar".equals(e.getActionCommand())) {
            
            jMenuItem5ActionPerformed();

        } else if ("Sobre".equals(e.getActionCommand())) {

            MenssagemPercaDados();

        }
    }

}
