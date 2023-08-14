import java.util.Scanner;
import java.util.Random;

public class DriverProgram {

    public static void main(String[] args) {
    }
}

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

    public boolean solicitarCompra() {
        Random numero = new Random();
        ticket = numero.nextInt(15000) + 1;
        int A = numero.nextInt(15000) + 1;
        int B = numero.nextInt(15000) + 1;
        int rangoMin = Math.min(A, B);
        int rangoMax = Math.max(A, B);

        return ticket >= rangoMin && ticket <= rangoMax;
    }

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

class Localidad {
    private Comprador swiftie;

    public Localidad(Comprador swiftie) {
        this.swiftie = swiftie;
    }

    public void solicitudBoletos(Validador validador) {
        int localidad = swiftie.asignarLocalidad();
        int boletosDeseados = swiftie.getCantidadBoletos();
        int presupuesto = swiftie.getPresupuestoMax();

        switch (localidad) {
            case 1: {
                int boletosLocal1 = validador.getLocalidad1();
                // Validar espacio
                if (boletosLocal1 <= 0) {
                    System.out.println("Lo sentimos, los boletos para Localidad 1 están agotados.");
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
                    System.out.println("----- ¡FELICIDADES! COMPRA EXITOSA -----\nPreio total: Q." + totalPrecio
                            + ".00\nLocalidad: 1\nEntradas Totales: " + totalBoletos);
                    validador.setLocalidad1(validador.getLocalidad1() - boletosAVender);
                    break;
                }
                System.out.println("Lo sentimos, tu presupuesto es insuficiente para un boleto.");
                break;
            }
            case 5: {
                int boletosLocal5 = validador.getLocalidad5();
                // Validar espacio
                if (boletosLocal5 <= 0) {
                    System.out.println("Lo sentimos, los boletos para Localidad 5 están agotados.");
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
                    System.out.println("----- ¡FELICIDADES! COMPRA EXITOSA -----\nPreio total: Q." + totalPrecio
                            + ".00\nLocalidad: 5\nEntradas Totales: " + totalBoletos);
                    validador.setLocalidad5(validador.getLocalidad5() - boletosAVender);
                    break;
                }
                System.out.println("Lo sentimos, tu presupuesto es insuficiente para un boleto.");
                break;
            }
            case 10: {
                int boletosLocal10 = validador.getLocalidad10();
                // Validar espacio
                if (boletosLocal10 <= 0) {
                    System.out.println("Lo sentimos, los boletos para Localidad 10 están agotados.");
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
                    System.out.println("----- ¡FELICIDADES! COMPRA EXITOSA -----\nPreio total: Q." + totalPrecio
                            + ".00\nLocalidad: 10\nEntradas Totales: " + totalBoletos);
                    validador.setLocalidad10(validador.getLocalidad10() - boletosAVender);
                    break;
                }
                System.out.println("Lo sentimos, tu presupuesto es insuficiente para un boleto.");
                break;
            }
            default: {
                System.out.println("No hemos podido encontrar tu solicitud");
                break;
            }
        }
    }
}

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