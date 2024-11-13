import javax.swing.*;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

/*
        String[] opc = {"agregar_producto", "buscar_producto", "eliminar_producto"};
        Runnable[] ac = new Runnable[]{
                () -> repositor.agregar_producto(stock),
                () -> repositor.buscar_producto(stock),
                () -> repositor.eliminar_producto(stock),
        };
        MisFunciones.interfaz(ac, opc, stock::toString);
*/

public class MisFunciones {

    public static Object eligirObjDeLista(List list) {

        if (list.isEmpty()) return null;

        return JOptionPane.showInputDialog(null,
                "elige", "Ursol",
                JOptionPane.QUESTION_MESSAGE, null, list.toArray(), 0);

    }

    public static int eligirEn(String[][] matriz) {

        String[] listaElementosEnMatriz = new String[matriz.length];
        for (int i = 0; i < matriz.length; i++) {
            listaElementosEnMatriz[i] = Arrays.toString(matriz[i]);
        }

        String elementoEligido = (String) JOptionPane.showInputDialog(null,
                "Elege...", "Ursol",
                JOptionPane.QUESTION_MESSAGE, null, listaElementosEnMatriz, null);

        if (elementoEligido != null) {
            int item = Arrays.asList(listaElementosEnMatriz).indexOf(elementoEligido);
            return item;
        }

        return -1;

    }

    public static boolean isNumeroDe_1_10000000(String str) {
        if (str.isEmpty()) {
            return false;
        } else {
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) < '0' || str.charAt(i) > '9') return false;
            }
            int num = Integer.parseInt(str);
            if (num < 1 || num > 1000000000) return false;
        }
        return true;
    }

    public static boolean isDoubleMasCero(String str) {
        int cantComas = 0;
        if (str.isEmpty()) {
            return false;
        } else {
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                    if (str.charAt(i) != '.') {
                        return false;
                    } else {
                        cantComas++;
                    }
                    if (cantComas > 1) return false;
                }
            }
            double num = Double.parseDouble(str);
            if (num < 0) return false;
        }
        return true;
    }

    public static int pedirNumeroMasCero(String msg) {
        String str;
        do {
            str = JOptionPane.showInputDialog(null, msg);
            if (str == null) return -1;
        } while (str.isEmpty() || !isNumeroDe_1_10000000(str));
        return Integer.parseInt(str);
    }

    public static double pedirDoubleMasCero(String msg) {
        String str;
        do {
            str = JOptionPane.showInputDialog(null, msg);
            if (str == null) return -1;
        } while (str.isEmpty() || !isDoubleMasCero(str));
        return Double.parseDouble(str);
    }

    public static String pedirStrNoVacio(String msg) {
        String str;
        do {
            str = JOptionPane.showInputDialog(null, msg);
            if (str == null) return null;
        } while (str.trim().isEmpty());
        return str;
    }

    public static LocalDate pedirFechaMasDeHoy(String msg, String msgError) {
        LocalDate fechaEntr;
        do {
            fechaEntr = LocalDate.parse(JOptionPane.showInputDialog(null, msg));
            if (fechaEntr.isBefore(LocalDate.now())) {
                JOptionPane.showMessageDialog(null, msgError);
            }
        } while (fechaEntr.isBefore(LocalDate.now()));
        return fechaEntr;
    }

    public static LocalDate pedirFechaMenosDeAhora(String msg) {

        LocalDate fechaEntr = null;
        boolean isFormatoDate;
        boolean isFechaMenosDeAhora;
        boolean isFechaExiste;

        do {
            String fechaStr = JOptionPane.showInputDialog(null, msg + ". Formato: yyyy-MM-dd");
            isFormatoDate = true;
            isFechaMenosDeAhora = true;
            isFechaExiste =true;

            if (fechaStr != null && !fechaStr.trim().isEmpty()) {

                if (fechaStr.length() == 10) {
                    for (int i = 0; i < fechaStr.length(); i++) {
                        boolean isUnDigit = (fechaStr.charAt(i) >= '0' && fechaStr.charAt(i) <= '9');
                        if (i < 4 && !isUnDigit) isFormatoDate = false;
                        if (i == 4 && fechaStr.charAt(i) != '-') isFormatoDate = false;
                        if (i > 4 && i < 7 && !isUnDigit) isFormatoDate = false;
                        if (i == 7 && fechaStr.charAt(i) != '-') isFormatoDate = false;
                        if (i > 7 && i < 10 && !isUnDigit) isFormatoDate = false;
                    }
                }else isFormatoDate = false;


                if (isFormatoDate) {
                    if (isFechaExiste(fechaStr)) {
                        fechaEntr = LocalDate.parse(fechaStr);
                        if (fechaEntr.isAfter(LocalDate.now())) {
                            JOptionPane.showMessageDialog(null, "fecha no puede ser en futuro");
                            isFechaMenosDeAhora = false;
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Fecha no existe");
                        isFechaExiste = false;
                    }


                } else JOptionPane.showMessageDialog(null, "Formato no correcto");
            }


        } while (!isFechaMenosDeAhora || !isFormatoDate || !isFechaExiste);

        return fechaEntr;

    }

    public static LocalDate pedirFechaMasDeOtraFecha(LocalDate otraFecha, String msg) {
        LocalDate fecha;
        do {
            fecha = pedirFechaMenosDeAhora(msg);
            if (fecha==null) return null;
            if (fecha.isBefore(otraFecha)) {
                JOptionPane.showMessageDialog(null, "la fecha debe ser mayor que la fecha previa");
            }
        } while (fecha.isBefore(otraFecha));
        return fecha;
    }

    private static int obtenerDiasEnMes(int year, int month) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                if (Year.of(year).length() == 366) {
                    return 29;
                } else {
                    return 28;
                }
            default:
                return 0;
        }
    }

    private static boolean isFechaExiste(String fechaStr) {
        String[] partes = fechaStr.split("-");
        int year = Integer.parseInt(partes[0]);
        int month = Integer.parseInt(partes[1]);
        int day = Integer.parseInt(partes[2]);

        if (month < 1 || month > 12) {
            return false;
        }

        if (day < 1) {
            return false;
        }

        return day <= obtenerDiasEnMes(year, month);
    }

    public static void interfaz(Runnable[] ac, String[] opc, Supplier<String> report) {
        List<String> opcList = new ArrayList<>(Arrays.asList(opc));
        opcList.addFirst("Salir");
        int select = 0;
        do {
            select = JOptionPane.showOptionDialog(
                    null,
                    report.get(),
                    "Ursol",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opcList.toArray(),
                    opcList.getFirst()
            );

            if (select > 0) ac[select - 1].run();

        } while (select > 0);
    }


}
