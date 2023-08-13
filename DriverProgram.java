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
    }

    public int asignarLocalidad() {
    }
}

class Localidad {
    private Comprador swiftie;

    public Localidad() {
    }

    public void solicitudColetos(Comprador swiftie, Validador validador) {
        this.swiftie = swiftie;
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