

package crud;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.util.Timer;
import java.util.TimerTask;
/**
 * 
 * @author Jhorman Gonzalez << Jhorman967@gmail.com >>
 */
public class ContadorImagesLogin {
    
   
    static int contador=0;
        
     public static void main( ) {
        
       
        int velocidad = 3;
   
        Timer timer;
        TimerTask tarea;
        
        int velmil = velocidad*1000;
        
        Login login = new Login();
        login.setVisible(true);
        
        tarea = new TimerTask(){
            @Override
            public void run (){
                Icon icono;
                switch(contador){
                    case 0:
                        contador = 1;
                        icono = new ImageIcon(getClass().getResource("/Images/estudiante-estudiando.jpg"));
                        login.ImagenLogin.setIcon(icono);
                        break;
                    
                    case 1: 
                        contador = 2;
                        icono = new ImageIcon(getClass().getResource("/Images/estudiantes-table.jpg"));
                        login.ImagenLogin.setIcon(icono);
                        break;
                    case 2: 
                        contador = 3;
                        icono = new ImageIcon(getClass().getResource("/Images/graduacion.jpg"));
                        login.ImagenLogin.setIcon(icono);
                        break;
                     case 3: 
                        contador = 1;
                        icono = new ImageIcon(getClass().getResource("/Images/estudiante-estudiando.jpg"));
                        login.ImagenLogin.setIcon(icono);
                        break;  
                }
            }
                  
        };
        
        timer = new Timer();
        timer.scheduleAtFixedRate(tarea,velmil,velmil);
    
 
    
    
}

};

