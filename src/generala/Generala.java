package generala;
import java.util.Random;
import java.util.Scanner;

public class Generala {
    public static boolean aprobado;
    public static Integer valorDado, cantidadJugadores, numeroTiradas = 3, turnosTotales = 1,turnosRestantes = 1, tablaDePuntajes[][];
    public static Random rnd = new Random();
    public static Scanner sca;
    public static String[] nombreJugadores;
    public static String valorInput="";
    public static Integer jugadaGuardada[],primeraTirada[];
    public static String Eleccion, letra;   // poner minuscula en Eleccion
    public static Integer eleccionInteger;

    
    public static void TirarDados(Integer cantidadDados) {
        jugadaGuardada = new Integer[5];
        primeraTirada = new Integer [cantidadJugadores];    
        sca=new Scanner (System.in);
        
        do{
            System.out.println("Para tirar los dados, escribí la letra t y luego enter.");
            valorInput = sca.nextLine();
            // 116 es el numero relacionado a la letra t
            if ((int)valorInput.charAt(0)==116) {
                if(cantidadDados == 1) {
                    valorDado = rnd.nextInt(6)+1;
                    jugadaGuardada[0] = valorDado;
                    System.out.print("Valor del dado es " + jugadaGuardada[0] + "\n");
                } else {
                    for (int i = 1; i < cantidadDados + 1; i++) {
                       valorDado = rnd.nextInt(6)+1;
                       jugadaGuardada[i - 1] = valorDado;
                       System.out.print("Valor del dado número " + i + " es " + jugadaGuardada[i - 1] + "\n");
                    }                    
                }
                
                aprobado=true;
                //System.out.println("Bien "+aprobado + valorInput);
            } else {
                aprobado=false;
                //System.out.println("mal "+ aprobado + valorInput);
            }
        }
        while(aprobado == false);
    }
    public static void AsignarJugadores() {
        sca=new Scanner (System.in);
        do{
            System.out.println("El número de jugadores tiene que ser entre 2 y 4.");
            System.out.println("Indicá el número de jugadores: ");
            cantidadJugadores = sca.nextInt();
            if( cantidadJugadores > 1 && cantidadJugadores < 5) {
                aprobado=true;
                //System.out.println("Bien "+aprobado);
            } else {
                aprobado=false;
                //System.out.println("mal "+aprobado);
            }
        } while(aprobado==false);
        
        System.out.println("El número de jugadores es: " + cantidadJugadores); 
    }
    public static void QuienEmpiezaAJugar() {
        nombreJugadores = new String [cantidadJugadores];
        // chequear esto mayorNumero = new Integer [cantidadJugadores];
        
        for (int i = 1; i < cantidadJugadores + 1; i++) {
            System.out.println("");
            System.out.println("Escribí el nombre del jugador " + i);
            nombreJugadores[i - 1] = sca.next();
            //System.out.println("El nombre del jugador " + i  + " es " + nombreJugadores[i - 1] );
        }
        for( int i = 1; i < cantidadJugadores + 1; i++) {
            System.out.println("");
            System.out.println(nombreJugadores[i - 1] + " tiene que tirar un dado.");
            TirarDados(1);
            //El valor que sacó cada usuario lo guardo en otra variable para desp calcularle el mayor
            primeraTirada[i - 1] = jugadaGuardada[0];
        //    System.out.println("Imprimo el valor de la primera tirada de esta persona " + primeraTirada[i - 1]);
        }
        // primeraTirada[0] da NULL, no deberia pasar eso. CHEQUEAR.
        //System.out.println(primeraTirada[0]);
        //mayorNumero[i - 1] = valorDado;
        //System.out.println("El mayor numero es: " + mayorNumero[0] + ", el segudo mayor es " + mayorNumero[1]);
    }
    public static void Turno() {
        for (int i = 0; i < cantidadJugadores; i++) {
            System.out.println("");
            System.out.println("Es el turno de " + nombreJugadores[i] + ". Le quedan 3 tiradas.");
            PrimerTirada();
            //System.out.println("A " + nombreJugadores[i] + " le quedan 2 tiradas.");
            //segundaTirada();
            //System.out.println("A " + nombreJugadores[i] + " le queda 1 tirada.");
            //tercerTirada()
        }
    }
    public static void PrimerTirada() { 
        numeroTiradas --;             
        TirarDados(5);      
        //Cambio el valor de aprobado para empezar a hacer el Do While de limpieza de caracteres
        aprobado = false;
        do {
        // Muestro los valores de los 5 dados
        System.out.println("Los dados que tiraste mostraron estos números");
        System.out.println(jugadaGuardada[0] + " " + jugadaGuardada[1] + "  " + jugadaGuardada[2] + " " + jugadaGuardada[3] + " " + jugadaGuardada[4]);    
        System.out.println("Qué dados querés descartar?");
        System.out.println("1, 2, 3, 4, 5 o 0 para ninguno");
        Eleccion = sca.next();
        LimpiarDatos();
        // En EvitarRepeticiones() se asigna aprobado=false si el usuario repite números
        EvitarRepeticiones();
        } while (aprobado == false);
    }
    
    public static void LimpiarDatos() {
        //sacarle los caracteres innecesarios, los necesarios son 0,1,2,3,4,5. 
        // El caracter 0 es igual a 48, el 1 es igual a 49 y asi sucesivamente
        for (int i=0; i<Eleccion.length();i++){
            if ((int)Eleccion.charAt(i)==48||(int)Eleccion.charAt(i)==49||(int)Eleccion.charAt(i)==50||(int)Eleccion.charAt(i)==51||(int)Eleccion.charAt(i)==52||(int)Eleccion.charAt(i)==53){
            } else {
                letra=Character.toString(Eleccion.charAt(i));
                Eleccion=Eleccion.replace(letra,"");
                i=i-1;
            }
        }
        //System.out.println("Eleccion despues de limpiarle los datos es " + Eleccion);
    }
    public static void EvitarRepeticiones() {
        // Eleccion, que es un string, lo transformo a un array
        String[] arrayEleccion = Eleccion.split("");

        String otraVezEleccion = "";                

        for (int i = 0 ; i < Eleccion.length(); i++){

            //System.out.println("La posición " + i + " del array es " + arrayEleccion[i]);

            // cada elemento de arrayEleccion lo paso de string a integer
            eleccionInteger = Integer.parseInt(arrayEleccion[i]); 

            for (int j = 0 ; j < Eleccion.length() ; j++ ) {

                // Si elije solo un valor no necesito chequear si repitió valores, le pongo aprobado
                if (Eleccion.length() == 1) {
                    aprobado = true;
                } else {
                    // Chequeo si los valores son iguales. 
                    if ( i != j ) {
                        if ( Integer.parseInt(arrayEleccion[i]) == Integer.parseInt(arrayEleccion[j])) {
                            /* si son iguales, osea el usuario repitió los numeros,
                            obligo al usuario a elegir de vuelta los dados que se quiere quedar
                            repito el bucle con aprobado es false.*/
                            aprobado = false;
                            //System.out.println(Integer.parseInt(arrayEleccion[i]) + " es igual a " + Integer.parseInt(arrayEleccion[j]));
                            System.out.println("No podes repetir el numero de los dados");

                            break; 
                        } else {
                            //Si no son iguales termina el do while con el aprobado true
                            //System.out.println(Integer.parseInt(arrayEleccion[i]) + " no es igual a " + Integer.parseInt(arrayEleccion[j]));
                            aprobado = true;
                        }
                    }
                }
            }

        }
        // convierto array eleccion otra vez en eleccion, solo para chequear que funciona
        //otraVezEleccion = otraVezEleccion.join("", arrayEleccion);    
        //System.out.println("Otravezeleccion es: " + otraVezEleccion);
    }
    
    public static void main(String[] args) {
        System.out.println("Empieza el juego.");
        System.out.println("");
        AsignarJugadores();
        QuienEmpiezaAJugar();
        for ( int i = 0; i < turnosTotales; i++) {
            System.out.println("");
            // Chequear este calculo del + 1
            System.out.println("Es el turno número " + (turnosTotales + 1 - turnosRestantes + "."));
            Turno();
            turnosRestantes --;
        }
        //AsigarPuntaje();
        System.out.println("");
        System.out.println("Terminó el juego.");
    }
}
