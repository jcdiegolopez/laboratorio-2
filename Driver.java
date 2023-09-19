import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.io.FileWriter;
import java.io.IOException;

/**
 * La clase Driver es la clase principal del programa que permite gestionar cursos y salones.
 */
class Driver{
    /**
     * El método main es el punto de entrada del programa.
     * 
     * @param args Los argumentos de línea de comandos (no se utilizan en este programa).
     */
    public static void main(String[] args){
        ArrayList<Curso> cursos = new ArrayList<Curso>();
        ArrayList<Sede> sedes = new ArrayList<Sede>();
        Scanner myScanner = new Scanner(System.in);
        boolean running = true;

        while(running){
            System.out.println("MENU PRINCIPAL");
            System.out.println("1. Cargar Cursos");
            System.out.println("2. Cargar Salones");
            System.out.println("3. Consultar salon");
            System.out.println("4. Consultar curso");
            System.out.println("5. Asignar Cursos");
            System.out.println("6. Mostrar Informe de Cursos");
            System.out.println("7. Salir del programa");
            System.out.print("Ingrese la opción: ");
            int opt = myScanner.nextInt();
            myScanner.nextLine();

            switch (opt) {
                case 1:
                    System.out.println("CARGAR CURSOS CSV");
                
                    
                    try (Scanner scanner = new Scanner(new File("cursos.csv"))) {
                        while (scanner.hasNextLine()) {
                            String linea = scanner.nextLine();
                            String[] valores = linea.split("\\|");
                            int idSede = Integer.parseInt(valores[1]);
                            if(buscarSede(idSede, sedes) == null){
                                sedes.add(new Sede(idSede));
                            }
                            cursos.add(new Curso(Integer.parseInt(valores[0]),Integer.parseInt(valores[1]),valores[2],Integer.parseInt(valores[3]),Integer.parseInt(valores[4]),valores[5],Integer.parseInt(valores[6])));
                            
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                      
                    


                    break;
                case 2:
                    System.out.println("CARGAR SALONES DESDE CONSOLA");
                
                    
                    try (Scanner scanner = new Scanner(new File("salon.csv"))) {
                        while (scanner.hasNextLine()) {
                            String linea = scanner.nextLine();
                            String[] valores = linea.split("\\|");
                            int idSede = Integer.parseInt(valores[0]);
                            if(buscarSede(idSede, sedes) == null){
                                sedes.add(new Sede(idSede));
                            } 

                            for(Sede sede: sedes){
                                if(sede.getIdSede() == idSede){
                                    sede.agregarSalon(new Salon(idSede,valores[1].charAt(0),Integer.parseInt(valores[2]),Integer.parseInt(valores[3]),Integer.parseInt(valores[4])));
                                }
                            }
                            
                            
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    break;
                case 3:
                    System.out.println("CONSULTAR SALON");
                    System.out.println("Formato: <id_sede> | <id salon> | <edifcio> | <nivel> ");
                    String entrada = myScanner.next();
                    String[] ingreso = entrada.split("\\|");
                    if (ingreso.length != 4) {
                        System.out.println("Error: Deben ingresars 4 valores separados por '|'.");   
                    }
                    try {
                        int idSede = Integer.parseInt(ingreso[0].trim());
                        int idSalon = Integer.parseInt(ingreso[1].trim());
                        char edificio = ingreso[2].trim().charAt(0);
                        int nivel = Integer.parseInt(ingreso[3].trim());
                        
                        Curso[][] horarioSalon = buscarSede(idSede, sedes).getSalon(idSalon).getHorario();

                        for(int i = 0; i < horarioSalon.length; i++) {
                            String strdia;
                            switch (i){
                                case 0:{
                                    strdia = "Lunes";
                                    break;
                                }
                                case 1:{
                                    strdia = "Martes";
                                    break;
                                }
                                case 2:{
                                    strdia = "Miercoles";
                                    break;
                                }
                                case 3:{
                                    strdia = "Jueves";
                                    break;
                                }
                                case 4:{
                                    strdia = "Viernes";
                                    break;
                                }
                                case 5:{
                                    strdia = "Sabado";
                                    break;
                                }
                                default: {
                                    strdia = "not found";
                                }
                                
                            }
                            System.out.println(strdia);
                            for(int j = 0; j < horarioSalon[i].length; j++){
                                String texto;
                                if(horarioSalon[i][j] == null) {
                                    texto = "libre";
                                }else{
                                    texto = "Id: " + horarioSalon[i][j].getIdCurso(); 
                                }
                                System.out.println("\t"+ convertirHorario(j) + " => " + texto);
                            }
                        }
                        

    

                    } catch (NumberFormatException e) {
                        System.out.println("Error: Los valores numéricos no son válidos: " + e);
                    }
                    break;
                case 4:
                    System.out.println("Ingrese el id del curso que desea consultar: ");
                    int cursoId = myScanner.nextInt();
                    Curso myCurso =  null;
                    for(Curso curso: cursos){
                        if(curso.getIdCurso() == cursoId){
                            myCurso = curso;
                        }
                    }
                    try {
                        System.out.println(myCurso);
                    } catch (Exception e) {
                        System.out.println("No existe este curso: " + e);
                    }
                    break;
                case 5:
                    for (Curso curso : cursos) {
                        if(curso.getAsignado() == false){
                            boolean asignado = true;
                            String[] days = curso.getDias().split("\\,");
                            int[] indexDays = new int[days.length];
                            for(int h = 0; h < days.length; h++){indexDays[h] = dayToNumber(days[h]); }
                            Sede sede = buscarSede(curso.getIdSede(), sedes);
                            for(Salon salon: sede.getSalones()){
                                Curso[][] horario = salon.getHorario();
                                
                                for(int i = 0; i < horario.length; i++) {
                                    if(isIn(i,indexDays) == true){
                                        for(int j = convertirHoraAMEntero(curso.getHorario()); j < (convertirHoraAMEntero(curso.getHorario())+curso.getDuracion()); j++){
                                            if(horario[i][j] != null || salon.getCapacidad() < curso.getCantidadEstudiantes()) {
                                                asignado = false;
                                                break;
                                            }
                                        }
                                    }
                                }

                                if(asignado == true){
                                    for(int i = 0; i < horario.length; i++) {
                                        if(isIn(i,indexDays) == true){
                                        for(int j = convertirHoraAMEntero(curso.getHorario()); j < (convertirHoraAMEntero(curso.getHorario())+curso.getDuracion()); j++){
                                            horario[i][j] = curso; 
                                        }
                                    }
                                    }
                                    curso.setAsignado(true);
                                    break;
                                }
                                }

                                
                            }
                        }
                    break;
                case 6:
                    ArrayList<Curso> cursosAsignados = new ArrayList<Curso>();
                    ArrayList<Curso> cursosNoAsignados = new ArrayList<Curso>();
                    ArrayList<String[]> dataCSV = new ArrayList<String[]>();
                    
                    int idsalon = 0;
                
                    for (Curso curso : cursos) {
                        if (curso.getAsignado()) {
                            cursosAsignados.add(curso);
                        } else {
                            cursosNoAsignados.add(curso);
                        }
                    }
                
                    System.out.println("Cursos asignados:");
                    if (cursosAsignados.isEmpty()) {
                        System.out.println("Ningún curso se ha asignado a un salón.");
                    } else {
                        System.out.println("Nombre curso            |            Id Salon            ");
                        for (Curso curso : cursosAsignados) {    
                            for(Sede sede: sedes){
                                for(Salon salon: sede.getSalones()){
                                    Curso[][] horario = salon.getHorario();
                                    for(Curso[] dia: horario){
                                        for(Curso periodo: dia){
                                            if(periodo != null){
                                            if(periodo.getIdCurso() == curso.getIdCurso() && sede.getIdSede() == periodo.getIdSede()) {
                                                idsalon = salon.getIdSalon();
                                                break;
                                            }
                                        }
                                        }
                                        break;
                                    }
                                    break;
                                }
                            }
                            try {
                                String[] actualar = {curso.getNombreCurso(),String.valueOf(idsalon)};
                                dataCSV.add(actualar);
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                            
                            
                        }

                        try (FileWriter escritor = new FileWriter("export.csv")) {
                            for (String[] fila : dataCSV) {
                                for (int i = 0; i < fila.length; i++) {
                                    escritor.append(fila[i]);
                                    if (i < fila.length - 1) {
                                        escritor.append(",");
                                    }
                                }
                                escritor.append("\n");
                            }
                            System.out.println("Archivo CSV creado correctamente.");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                
                    System.out.println("\nCursos no asignados:");
                    if (cursosNoAsignados.isEmpty()) {
                        System.out.println("Todos los cursos han sido asignados.");
                    } else {
                        for (Curso curso : cursosNoAsignados) {
                            System.out.println(curso.getNombreCurso() + " - No se pudo asignar un salón.");
                        }
                    }
                    break;
                case 7:
                    System.out.println("¡Hasta luego!");
                    running = false;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        }

        myScanner.close();
    }


    /**
     * Convierte un número de hora en formato entero a un horario legible.
     * 
     * @param numero El número de hora en formato entero.
     * @return Una cadena que representa el horario en formato legible.
     */
    public static String convertirHorario(int numero) {
        switch (numero) {
            case 0:
                return "7am - 8am";
            case 1:
                return "8am - 9am";
            case 2:
                return "9am - 10am";
            case 3:
                return "10am - 11am";
            case 4:
                return "11am - 12pm";
            case 5:
                return "12pm - 1pm";
            case 6:
                return "1pm - 2pm";
            case 7:
                return "2pm - 3pm";
            case 8:
                return "3pm - 4pm";
            case 9:
                return "4pm - 5pm";
            case 10:
                return "5pm - 6pm";
            case 11:
                return "6pm - 7pm";
            case 12:
                return "7pm - 8pm";
            case 13:
                return "8pm - 9pm";
            default:
                return "Horario no válido";
        }
    }
    
        
    /**
     * Convierte una hora en formato entero a su equivalente en un arreglo.
     * 
     * @param hora La hora en formato entero.
     * @return El valor equivalente en el arreglo (índice).
     */
    public static int convertirHoraAMEntero(int hora) {
        if (hora >= 7 && hora <= 20) {
            int valorEquivalente = hora - 7;
            return valorEquivalente;
        } else {
            return -1;
        }
    }

    /**
     * Verifica si un número está en un arreglo de números.
     * 
     * @param n           El número que se busca.
     * @param arraynum    El arreglo de números en el que se busca.
     * @return true si el número se encuentra en el arreglo, false en caso contrario.
     */
    public static boolean isIn(int n, int[]arraynum){
        for(int num : arraynum){
            if(n == num){
                return true;
            }
        }
        return false;
    }

    /**
     * Busca una sede por su ID en una lista de sedes.
     * 
     * @param idSede El ID de la sede que se busca.
     * @param sedes  La lista de sedes en la que se realiza la búsqueda.
     * @return La sede encontrada o null si no se encuentra.
     */
    public static Sede buscarSede(int idSede, ArrayList<Sede> sedes){
        for(Sede sede : sedes){
            if(sede.getIdSede() == idSede){
                return sede;
            }
        }
        return null;

    }

    /**
     * Convierte el nombre de un día de la semana a su equivalente numérico.
     * 
     * @param day El nombre del día de la semana en minúsculas.
     * @return El número correspondiente al día o 404 si no se encuentra.
     */
    public static int dayToNumber(String day){
        switch(day.toLowerCase()) {
            case "lunes":
                return 0;
            case "martes":
                return 1;
            case "miercoles":
                return 2;
            case "jueves":
                return 3;
            case "viernes":
                return 4;
            case "sabado":
                return 5;
            default:
                return 404;
        }
    
    }
    
}
