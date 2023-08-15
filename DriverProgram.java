
/**
 * Esta clase representa un programa de gestión de ventas de 
 * boletos para un evento musical.
 * 
 * @author Cristian Túnchez
 * @version 1.0
 * @since 14-08-2023
 */

import java.util.Scanner;
import java.util.Random;

public class DriverProgram {
    /**
     * Punto de entrada del programa.
     * 
     * @param args Argumentos de línea de comandos (no utilizados en este programa)
     */
    public static void main(String[] args) {

        Comprador cliente = null;
        Validador validador = new Validador();
        Scanner teclado = new Scanner(System.in);
        int salir = 0;

        do {
            System.out.println("\n---------- THE ERAS TOUR: TAYLOR SWIFT ----------");
            System.out.println(
                    "1. Nuevo comprador\n2. Nueva solicitud de boletos\n3. Consultar disponibilidad total\n4. Consultar disponibilidad individual\n5. Reporte de caja\n6. Salir");
            System.out.println("[Sistema]: Igresa una opción:");
            int opt = teclado.nextInt();
            teclado.nextLine();
            switch (opt) {
                case 1: {
                    cliente = new Comprador();
                    System.out.println("\n[Sistema]: Ingresa tu nombre: ");
                    String nombreString = teclado.nextLine();
                    cliente.setNombre(nombreString);
                    System.out.println("[Sistema]: Ingresa tu email: ");
                    String emailString = teclado.nextLine();
                    cliente.setEmail(emailString);
                    System.out.println("[Sistema]: Ingresa la cantidad de boletos que deseas: ");
                    int numBoletos = teclado.nextInt();
                    teclado.nextLine();
                    cliente.setCantidadBoletos(numBoletos);
                    System.out.println("[Sistema]: Ingresa tu presupuesto (en dólares): ");
                    int presupuesto = teclado.nextInt();
                    teclado.nextLine();
                    cliente.setPresupuestoMax(presupuesto);
                    System.out.println("[Sistema]: ¡Ticket creado!");
                    break;
                }
                case 2: {
                    if (cliente != null) {
                        System.out.println("\n[Sistema]: Iniciando solicitud para comprar boletos...");
                        if (cliente.getTicket() == 0) {
                            if (cliente.solicitarCompra()) {
                                Localidad validacion = new Localidad();
                                validacion.solicitudBoletos(cliente, validador);
                                break;
                            } else {
                                String valorTicket = "" + cliente.getTicket();
                                System.out
                                        .println(
                                                "[Sistema]: Lo siento, tu ticket (" + valorTicket
                                                        + ") no participa para la compra de boletos.");
                                break;
                            }
                        } else {
                            String valorTicket = "" + cliente.getTicket();
                            System.out.println("[Sistema]: Lo siento, tu ticket (" + valorTicket
                                    + ") ya ha sido usado o no participa para la compra de boletos.");
                            break;
                        }
                    }
                    System.out.println("\n[Sistema]: Debes registrarte primero.");
                    break;
                }
                case 3: {
                    String disponiblesLocal1 = "" + validador.getLocalidad1();
                    String vendidosLocal1 = "" + (20 - validador.getLocalidad1());
                    System.out.println("\n---------- LOCALIDAD 1 ----------\nBoletos Vendidos: " + vendidosLocal1
                            + "\nBoletos Disponibles: " + disponiblesLocal1);
                    String disponiblesLocal5 = "" + validador.getLocalidad5();
                    String vendidosLocal5 = "" + (20 - validador.getLocalidad5());
                    System.out.println("\n---------- LOCALIDAD 5 ----------\nBoletos Vendidos: " + vendidosLocal5
                            + "\nBoletos Disponibles: " + disponiblesLocal5);
                    String disponiblesLocal10 = "" + validador.getLocalidad10();
                    String vendidosLocal10 = "" + (20 - validador.getLocalidad10());
                    System.out.println("\n---------- LOCALIDAD 10 ----------\nBoletos Vendidos: " + vendidosLocal10
                            + "\nBoletos Disponibles: " + disponiblesLocal10);
                    break;
                }
                case 4: {
                    System.out.println("\n[Sistema]: ¿En qué loclaidad deseas revisar disponibilidad? (1, 5, 10)");
                    int optLocal = teclado.nextInt();
                    teclado.nextLine();
                    switch (optLocal) {
                        case 1: {
                            String disponiblesLocal1 = "" + validador.getLocalidad1();
                            System.out.println("[Sistema]: En este momento hay " + disponiblesLocal1
                                    + " tickets disponibles en Localidad 1.");
                            break;
                        }
                        case 5: {
                            String disponiblesLocal5 = "" + validador.getLocalidad5();
                            System.out.println("[Sistema]: En este momento hay " + disponiblesLocal5
                                    + " tickets disponibles en Localidad 5.");
                            break;
                        }
                        case 10: {
                            String disponiblesLocal10 = "" + validador.getLocalidad10();
                            System.out.println("[Sistema]: En este momento hay " + disponiblesLocal10
                                    + " tickets disponibles en Localidad 10.");
                            break;
                        }
                        default: {
                            System.out.println("\n[Sistema]: Lo siento, esa Localidad no existe.");
                            break;
                        }
                    }
                    break;
                }
                case 5: {
                    String totalLocal1 = "" + (100 * (20 - validador.getLocalidad1()));
                    String totalLocal5 = "" + (500 * (20 - validador.getLocalidad5()));
                    String totalLocal10 = "" + (1000 * (20 - validador.getLocalidad10()));
                    String totalGeneral = "" + ((100 * (20 - validador.getLocalidad1()))
                            + (500 * (20 - validador.getLocalidad5())) + (1000 * (20 - validador.getLocalidad10())));
                    System.out.println("\n---------- REPORTE DE CAJA ----------\nTotal Localidad 1: $" + totalLocal1
                            + "\nTotal Localidad 5: $" + totalLocal5 + "\nTotal Localidad 10: $" + totalLocal10
                            + "\nTotal General: $" + totalGeneral);
                    break;
                }
                case 6: {
                    salir = 6;
                    teclado.close();
                    break;
                }
                default: {
                    System.out.println("[Sistema]: Lo siento, esa no es una opción del menú.");
                    break;
                }
            }
        } while (salir != 6);
    }
}

/**
 * Esta clase representa a un comprador interesado en adquirir boletos para el
 * evento.
 */
class Comprador {
    private String nombre;
    private String email;
    private int cantidadBoletos;
    private int presupuestoMax;
    private int ticket;

    public Comprador() {
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCantidadBoletos(int cantidadBoletos) {
        this.cantidadBoletos = cantidadBoletos;
    }

    public void setPresupuestoMax(int presupuestoMax) {
        this.presupuestoMax = presupuestoMax;
    }

    public void setTicket(int ticket) {
        this.ticket = ticket;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public int getCantidadBoletos() {
        return cantidadBoletos;
    }

    public int getPresupuestoMax() {
        return presupuestoMax;
    }

    public int getTicket() {
        return ticket;
    }

    /**
     * Genera un número de ticket aleatorio y verifica si este número cae dentro de
     * un rango permitido para la compra.
     *
     * @return {@code true} si el número de ticket está dentro del rango permitido,
     *         {@code false} en caso contrario.
     */
    public boolean solicitarCompra() {
        Random numero = new Random();
        ticket = numero.nextInt(15000) + 1;
        int A = numero.nextInt(15000) + 1;
        int B = numero.nextInt(15000) + 1;
        int rangoMin = Math.min(A, B);
        int rangoMax = Math.max(A, B);

        return ticket >= rangoMin && ticket <= rangoMax;
    }

    /**
     * Asigna aleatoriamente una localidad para el comprador.
     *
     * @return El número de la localidad asignada: 1 para Localidad 1, 5 para
     *         Localidad 5, 10 para Localidad 10.
     */
    public int asignarLocalidad() {
        Random numero = new Random();
        int localidad = numero.nextInt(3);
        if (localidad == 0) {
            return 1;
        } else if (localidad == 1) {
            return 5;
        } else {
            return 10;
        }
    }
}

/**
 * Esta clase representa una localidad del evento.
 */
class Localidad {
    private Comprador swiftie;

    public Localidad() {
    }

    /**
     * Procesa la solicitud de compra de boletos para el comprador dado en una
     * localidad específica.
     *
     * @param swiftie   El comprador que realiza la solicitud de compra de boletos.
     * @param validador El validador que contiene información sobre la
     *                  disponibilidad de boletos en las localidades.
     */
    public void solicitudBoletos(Comprador swiftie, Validador validador) {
        this.swiftie = swiftie;
        int localidad = swiftie.asignarLocalidad();
        int boletosDeseados = swiftie.getCantidadBoletos();
        int presupuesto = swiftie.getPresupuestoMax();

        switch (localidad) {
            // código para la localidad 1
            case 1: {
                int boletosLocal1 = validador.getLocalidad1();
                // Validar espacio
                if (boletosLocal1 <= 0) {
                    System.out.println("Lo sentimos, " + swiftie.getNombre() + " tu ticket (" + swiftie.getTicket()
                            + ") participa, pero tu localidad asignada (1) está agotada.");
                    break;
                }
                // Validar disponibilidad de boletos deseados
                int boletosAVender = Math.min(boletosDeseados, boletosLocal1);

                // c. Validar precio
                int boletosPosibles = presupuesto / 100;
                boletosAVender = Math.min(boletosAVender, boletosPosibles);

                if (boletosAVender != 0) {
                    String totalPrecio = "" + (boletosAVender * 100);
                    String totalBoletos = "" + boletosAVender;
                    System.out.println("[Sistema]: ¡Felicidades! " + swiftie.getNombre() + " tu ticket ("
                            + swiftie.getTicket() + ") participa para la compra de boletos.");
                    System.out.println(
                            "\n---------- RESUMEN DE COMPRA ----------\nLocalidad Asignada: 1\nPrecio de la loclaidad: $100\nPresupuesto Inicial: $"
                                    + presupuesto + "\nTotal para esta Compra: $" + totalPrecio
                                    + "\nBoletos iniciales Deseados: " + boletosDeseados + "\nBoletos Comprados: "
                                    + totalBoletos);
                    validador.setLocalidad1(validador.getLocalidad1() - boletosAVender);
                    break;
                }
                System.out.println("Lo sentimos, " + swiftie.getNombre() + " tu ticket (" + swiftie.getTicket()
                        + ") participa, pero tu presupuesto es insuficiente para un boleto para un boleto en la Localidad 1 ($100).");
                break;
            }
            // código para la localidad 5
            case 5: {
                int boletosLocal5 = validador.getLocalidad5();
                // Validar espacio
                if (boletosLocal5 <= 0) {
                    System.out.println("Lo sentimos, " + swiftie.getNombre() + " tu ticket (" + swiftie.getTicket()
                            + ") participa, pero tu localidad asignada (5) está agotada.");
                    break;
                }
                // Validar disponibilidad de boletos deseados
                int boletosAVender = Math.min(boletosDeseados, boletosLocal5);

                // c. Validar precio
                int boletosPosibles = presupuesto / 500;
                boletosAVender = Math.min(boletosAVender, boletosPosibles);

                if (boletosAVender != 0) {
                    String totalPrecio = "" + (boletosAVender * 500);
                    String totalBoletos = "" + boletosAVender;
                    System.out.println("[Sistema]: ¡Felicidades! " + swiftie.getNombre() + " tu ticket ("
                            + swiftie.getTicket() + ") participa para la compra de boletos.");
                    System.out.println(
                            "\n---------- RESUMEN DE COMPRA ----------\nLocalidad Asignada: 5\nPrecio de la loclaidad: $500\nPresupuesto Inicial: $"
                                    + presupuesto + "\nTotal para esta Compra: $" + totalPrecio
                                    + "\nBoletos iniciales Deseados: " + boletosDeseados + "\nBoletos Comprados: "
                                    + totalBoletos);
                    validador.setLocalidad5(validador.getLocalidad5() - boletosAVender);
                    break;
                }
                System.out.println("Lo sentimos, " + swiftie.getNombre() + " tu ticket (" + swiftie.getTicket()
                        + ") participa, pero tu presupuesto es insuficiente para un boleto en la Localidad 5 ($500).");
                break;
            }
            // código para la localidad 10
            case 10: {
                int boletosLocal10 = validador.getLocalidad10();
                // Validar espacio
                if (boletosLocal10 <= 0) {
                    System.out.println("Lo sentimos, " + swiftie.getNombre() + " tu ticket (" + swiftie.getTicket()
                            + ") participa, pero tu localidad asignada (10) está agotada.");
                    break;
                }
                // Validar disponibilidad de boletos deseados
                int boletosAVender = Math.min(boletosDeseados, boletosLocal10);

                // c. Validar precio
                int boletosPosibles = presupuesto / 1000;
                boletosAVender = Math.min(boletosAVender, boletosPosibles);

                if (boletosAVender != 0) {
                    String totalPrecio = "" + (boletosAVender * 1000);
                    String totalBoletos = "" + boletosAVender;
                    System.out.println("[Sistema]: ¡Felicidades! " + swiftie.getNombre() + " tu ticket ("
                            + swiftie.getTicket() + ") participa para la compra de boletos.");
                    System.out.println(
                            "\n---------- RESUMEN DE COMPRA ----------\nLocalidad Asignada: 10\nPrecio de la loclaidad: $1000\nPresupuesto Inicial: $"
                                    + presupuesto + "\nTotal para esta Compra: $" + totalPrecio
                                    + "\nBoletos iniciales Deseados: " + boletosDeseados + "\nBoletos Comprados: "
                                    + totalBoletos);
                    validador.setLocalidad10(validador.getLocalidad10() - boletosAVender);
                    break;
                }
                System.out.println("Lo sentimos, " + swiftie.getNombre() + " tu ticket (" + swiftie.getTicket()
                        + ") participa, pero tu presupuesto es insuficiente para un boleto para un boleto en la Localidad 10 ($1000).");
                break;
            }
            default: {
                System.out.println("No hemos podido encontrar tu solicitud.");
                break;
            }
        }
    }
}

/**
 * Esta clase se encarga de validar la disponibilidad de boletos en las
 * diferentes localidades.
 */
class Validador {
    private int localidad1 = 20;
    private int localidad5 = 20;
    private int localidad10 = 20;

    public Validador() {
    }

    public void setLocalidad1(int localidad1) {
        this.localidad1 = localidad1;
    }

    public void setLocalidad5(int localidad5) {
        this.localidad5 = localidad5;
    }

    public void setLocalidad10(int localidad10) {
        this.localidad10 = localidad10;
    }

    public int getLocalidad1() {
        return localidad1;
    }

    public int getLocalidad5() {
        return localidad5;
    }

    public int getLocalidad10() {
        return localidad10;
    }
}